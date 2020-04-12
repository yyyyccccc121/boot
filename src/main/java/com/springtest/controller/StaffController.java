package com.springtest.controller;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.springtest.mapper.StaffDao;
import com.springtest.model.staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class StaffController {

    @Autowired
    private StaffDao staffDao;

    @RequestMapping("staff/login")
    public String Login(String staffName, String staffPassword){
        String password = staffDao.selectPasswordById(staffName);
        //懒得判断了
        return "index";
    }

    @RequestMapping("staff/findAll")
    public String findAlll(Model model){
        ArrayList<staff> staffs = staffDao.findAll();
        model.addAttribute("staffs",staffs);
        return "staffManage";
    }

    @ResponseBody
    @RequestMapping("staff/addstaff")
    public JSONObject addstaff(String staffName, String staffPassword, int staffAge){
        staff staff1 = new staff();
        staff1.setName(staffName);
        staff1.setPassword(staffPassword);
        staff1.setAge(staffAge);
        int i = staffDao.AddStaff(staff1);
        String s;
        if(i>0)
            s= "添加成功";
        else
            s="添加失败";
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("s",s);
        return jsonObject;
    }

    @RequestMapping("staff/staffupdate/{id}")
    public String staffupdate(@PathVariable("id") Integer id, Model model){
        staff staff2 = staffDao.selectAllById(id);
        //System.out.println(staff2);
        model.addAttribute("staff2",staff2);
        return "staffUpdate";
    }

    @RequestMapping("staff/staffdelete/{id}")
    public String staffdelete(@PathVariable("id") Integer id){
        int i = staffDao.deleteById(id);
        return "redirect:/staff/findAll";
    }
}
