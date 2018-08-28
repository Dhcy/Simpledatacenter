package com.good.plat.datacenter.game.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.good.plat.datacenter.game.entity.Game;

public interface GameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Game record);

    int insertSelective(Game record);

    int updateByPrimaryKeySelective(Game record);
    
    int updateByPrimaryKey(Game record);
    
    Game selectByPrimaryKey(Integer id);
    
    //根据projectid查询游戏列表,不关联项目,区服,projectid为-1或者null是查询全部
    List<Game> selectGameListByProjectId(@Param("projectid") Integer projectid);
    
    //根据projectid查询游戏列表,关联项目,区服,projectid为-1或者null是查询全部
    List<Game> selectProjectGameAreaList(@Param("projectid") Integer projectid);
    
    //根据projectid查询游戏列表,关联项目,projectid为-1或者null是查询全部
    List<Game> selectProjectGamesList(@Param("projectid") Integer projectid);
    
    //根据gameid查询游戏列表,关联区服,gameid为-1或者null是查询全部
    List<Game> selectGameAreaList(@Param("gameid") Integer gameid);
    
    //获取用户的游戏权限
    List<Game> selectGameByUserid(@Param("userid") Integer userid);
    //获取游戏信息
    public Game getGame(Game game);
    
}