package com.springtest.service.serviceimpl;

import com.springtest.mapper.FriendDao;
import com.springtest.model.InfoMessage;
import com.springtest.model.friend;
import com.springtest.service.friendsServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class friendsServiceImpl implements friendsServiceDao {
    @Autowired
    FriendDao friendDao;

    public String findByusername(String name){
        return friendDao.findByusername(name);
    }

    public List<friend> findAll(){
        return friendDao.findAll();
    }

    public void unreadMessage(InfoMessage infoMessage){
        friendDao.unreadMessage(infoMessage);
    }

    public List<InfoMessage> findunreadMessage(String fromname, String toname){ return friendDao.findunreadMessage(fromname,toname);}

    public void upMessage(String fromname, String toname){ friendDao.upMessage(fromname,toname);}
}
