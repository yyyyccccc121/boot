package com.springtest.mapper;

import com.springtest.model.InfoMessage;
import com.springtest.model.friend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Mapper
public interface FriendDao {
    ArrayList<friend> findAll();
    String findByusername(String name);
    void unreadMessage(InfoMessage infoMessage);
    List<InfoMessage> findunreadMessage(@Param("fromname") String fromname, @Param("toname") String toname);
    void upMessage(@Param("fromname") String fromname, @Param("toname") String toname);
}
