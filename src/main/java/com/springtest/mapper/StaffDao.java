package com.springtest.mapper;

import com.springtest.model.staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface StaffDao {
    public String selectPasswordById(String name);
    public ArrayList<staff> findAll();
    public int AddStaff(staff staff1);
    public staff selectAllById(Integer id);
    public int deleteById(Integer id);

}
