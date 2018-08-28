package com.good.plat.datacenter.game.service;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.game.entity.GameArea;

/**
 * @ClassName: GameAreaService
 * @Description: 游戏区服操作接口
 * @author dmw
 * @date 2016年1月15日 下午2:15:13
 */
public interface GameAreaService {
	public GameArea findGameAreaById(Integer id) throws Exception;
	
	public List<GameArea> findGameAreaList(Integer gameid) throws Exception;
	
	public List<GameArea> findAreaList(Integer gameid) throws Exception;
	
	public BaseMessage addGameArea(GameArea area) throws Exception;
	
	public BaseMessage delGameArea(GameArea area) throws Exception;
	
	public BaseMessage editGameArea(GameArea area) throws Exception;
}
