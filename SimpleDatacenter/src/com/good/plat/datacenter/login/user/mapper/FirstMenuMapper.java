package com.good.plat.datacenter.login.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.login.user.entity.FirstMenu;

public interface FirstMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FirstMenu record);

    int insertSelective(FirstMenu record);

    FirstMenu selectByPrimaryKey(Integer id);
    
    FirstMenu selectByMenuName(@Param("menuName")String menuName);

    int updateByPrimaryKeySelective(FirstMenu record);

    int updateByPrimaryKey(FirstMenu record);
    
    List<FirstMenu> selectFirstMenuList();
    
    List<FirstMenu> selectMenuListByUserId(@Param("userId")Integer userId);
    
    List<FirstMenu> selectMenuListByUserIdAndMenuType(BaseSearchData baseSearchData);
    
    List<FirstMenu> selectMenuListByRoles(@Param("roleIds")List<Integer> roleIds);

	List<FirstMenu> firstUserMenuListByPlatId(BaseSearchData baseSearchData);
	
	List<FirstMenu> selectPlatFirstMenuList();
}