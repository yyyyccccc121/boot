package com.springtest.controller;

import com.springtest.model.InfoMessage;
import com.springtest.model.friend;
import com.springtest.service.friendsServiceDao;
import com.springtest.utils.JedisUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class SocketController {

    @Autowired
    private friendsServiceDao serviceDao;

    private SimpMessagingTemplate template;

    @Autowired
    public void CoordinationController(SimpMessagingTemplate t) {
        template = t;
    }

    @RequestMapping("/websocket/login")
    public String websocket(String name, String password, HttpServletRequest request, HttpServletResponse response){

        //保存用户名
        if(serviceDao.findByusername(name).equals(password)){
            Jedis jedis = JedisUtils.getJedis();
            jedis.set("13","141233");
            System.out.println("jedis.get(13)="+jedis.get("13"));

            Cookie cookie = new Cookie("username",name);
            cookie.setMaxAge(60*10);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "frontend/frontIndex";
        }
        return null;
    }

    @RequestMapping("/websocket/findAll")
    public String tofrontMessage(Model model){
        List<friend> friends = serviceDao.findAll();
        model.addAttribute("friends",friends);
        return "frontend/frontMessage";
    }




    @RequestMapping("/websocket/tosocket")
    public void toWebSocket(HttpServletRequest request, HttpServletResponse response, Model model)throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        String friendname = request.getParameter("friendname");
        Map<String, String> map = new HashMap<String,String>();
        map.put("jjjj", friendname);
        response.getWriter().write(map.toString());
    }

    @MessageMapping("/userChat")
    public void userChat(InfoMessage message) {
        System.out.println(message+"--------------");
        //找到需要发送的地址
        String dest = "/userChat/chat"+message.getToname();
        //发送用户的聊天记录
        System.out.println(dest);
        this.template.convertAndSend(dest, message);
    }

    @RequestMapping("/websocket/StoreToDatabase")
    public void StoreToDatabase(HttpServletRequest request, HttpServletResponse response)throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String content = request.getParameter("content");
        System.out.println("--------------"+from);
        //把消息存入数据库，状态设置为未读
        InfoMessage infoMessage = new InfoMessage(from,to,content,0);
        serviceDao.unreadMessage(infoMessage);
        String a="{}";
        response.getWriter().write(a);
    }

    @RequestMapping("/websocket/findunreadMessage")
    public void findunreadMessage(HttpServletRequest request, HttpServletResponse response)throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        String fromname = request.getParameter("from");
        String toname = request.getParameter("to");
        //从数据库查消息
        List<InfoMessage> infoMessageList = serviceDao.findunreadMessage(fromname,toname);
        List<Map<String,String>> rows = new ArrayList<Map<String,String>>();

        if(infoMessageList!=null) {
            //把状态设置为已读
            serviceDao.upMessage(fromname, toname);
            //装入List
            for(InfoMessage infoMessage:infoMessageList){
                Map<String, String> map = new HashMap<String, String>();
                map.put("fromname", infoMessage.getFromname());
                map.put("toname", infoMessage.getToname());
                map.put("content", infoMessage.getContent());
                rows.add(map);
            }
        }
//        else {
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("error", "没有消息");
//            rows.add(map);
//        }
        JSONArray json = JSONArray.fromObject(rows);
        response.getWriter().write(json.toString());
    }
}
