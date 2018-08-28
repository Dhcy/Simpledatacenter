package com.good.plat.datacenter.game.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.good.plat.datacenter.game.entity.GameArea;

public interface GameAreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameArea record);

    int insertSelective(GameArea record);

    GameArea selectByPrimaryKey(Integer id);
    
    GameArea selectByAreaId(GameArea area);

    int updateByPrimaryKeySelective(GameArea record);

    int updateByPrimaryKey(GameArea record);
    
    /**
     * @Title: selectGameAreaList
     * @Description: 关联game查询gamearea列表
     * @param gameid : 为-1,null查询全部
     * List<GameArea>
     * @author dmw
     * @date 2016年1月14日 下午4:04:05
     */
    List<GameArea> selectGameAreaList(@Param(value = "gameid") Integer gameid);
    
    /**
     * @Title: selectAreaList
     * @Description: 查询gamearea列表
     * @param gameid : 为-1,null查询全部
     * @return
     * List<GameArea>
     * @author dmw
     * @date 2016年1月14日 下午6:21:31
     */
    List<GameArea> selectAreaList(@Param(value = "gameid") Integer gameid);
    
    /**
     * 根据游戏id跟区服id删除区服
     * @Title: deleteGameArea
     * @Description: TODO
     * @param area
     * @return
     * @throws Exception
     * int
     * @author hwj
     * @date 2017-5-23 下午05:00:29
     */
    int deleteGameArea(GameArea area)throws Exception;
}