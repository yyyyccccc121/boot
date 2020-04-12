package com.springtest.service;

import com.springtest.model.InfoMessage;
import com.springtest.model.friend;

import java.util.List;

public interface friendsServiceDao {
    String findByusername(String name);
    List<friend> findAll();
    void unreadMessage(InfoMessage infoMessage);
    List<InfoMessage> findunreadMessage(String fromname, String toname);
    void upMessage(String fromname, String toname);
}
