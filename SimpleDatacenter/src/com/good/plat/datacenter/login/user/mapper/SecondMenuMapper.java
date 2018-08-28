package com.good.plat.datacenter.login.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.good.plat.datacenter.login.user.entity.SecondMenu;

public interface SecondMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecondMenu record);

    int insertSelective(SecondMenu record);

    SecondMenu selectByPrimaryKey(Integer id);
    
    SecondMenu selectBySecondMenu(SecondMenu menu);

    int updateByPrimaryKeySelective(SecondMenu record);

    int updateByPrimaryKey(SecondMenu record);
    
    List<SecondMenu> selectPermission(SecondMenu menu);
    
    List<SecondMenu> selectSecondMenuList(SecondMenu menu);
    
    List<SecondMenu> selectSecondMenuListByUser(@Param(value="userid") Integer userid);
}