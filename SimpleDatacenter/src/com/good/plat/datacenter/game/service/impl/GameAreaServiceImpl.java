package com.good.plat.datacenter.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.redis.service.impl.RedisServiceImpl;
import com.good.plat.datacenter.game.entity.GameArea;
import com.good.plat.datacenter.game.mapper.GameAreaMapper;
import com.good.plat.datacenter.game.service.GameAreaService;

@Service(value="gameAreaService")
public class GameAreaServiceImpl implements GameAreaService {

	public GameAreaServiceImpl() {
	}
	
	@Autowired
	private GameAreaMapper gameAreaMapper;
	
	@Autowired
	private RedisServiceImpl redisService;

	@Override
	public GameArea findGameAreaById(Integer id) throws Exception {
		return gameAreaMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<GameArea> findGameAreaList(Integer gameid) throws Exception {
		List<GameArea> areaList = new ArrayList<GameArea>();
		areaList = gameAreaMapper.selectGameAreaList(gameid);
		
		/*if (gameid != -1 && gameid != null) {
			areaList = gameAreaMapper.selectGameAreaList(gameid);
		} else {
			//只有查询全部时才缓存
			if (redisService.getBytes("GAME_GAMEAREA_LIST") != null) {
				areaList = (List<GameArea>) redisService
						.byteArrayToObject(redisService.getBytes("GAME_GAMEAREA_LIST"));
			} else {
				areaList = gameAreaMapper.selectGameAreaList(gameid);
				if (areaList.size() > 0) {
					redisService.set("GAME_GAMEAREA_LIST".getBytes(), 
							redisService.objectToByteArray(areaList));
				}
			}
		}*/
		
		return areaList;
	}

	@Override
	public List<GameArea> findAreaList(Integer gameid) throws Exception {
		List<GameArea> areaList = new ArrayList<GameArea>();
		areaList = gameAreaMapper.selectGameAreaList(gameid);
		/*if (gameid != -1 && gameid != null) {
			areaList = gameAreaMapper.selectGameAreaList(gameid);
		} else {
			//只有查询全部时才缓存
			if (redisService.getBytes("GAMEAREA_LIST") != null) {
				areaList = (List<GameArea>) redisService
						.byteArrayToObject(redisService.getBytes("GAMEAREA_LIST"));
			} else {
				areaList = gameAreaMapper.selectAreaList(gameid);
				if (areaList.size() > 0) {
					redisService.set("GAMEAREA_LIST".getBytes(), 
							redisService.objectToByteArray(areaList));
				}
			}
		}*/
		
		return areaList;
	}

	@Override
	public BaseMessage addGameArea(GameArea area) throws Exception {
		BaseMessage mess = new BaseMessage();
		GameArea area2 = gameAreaMapper.selectByAreaId(area);
		
		if (area2 == null) {
			int status = gameAreaMapper.insertSelective(area);
			if (status == 1) {
				//添加成功的时候记得清空redis中数据
				//redisService.del("GAME_GAMEAREA_LIST", "GAMEAREA_LIST");
				mess.setStatus(status);
				mess.setMessage("添加成功");
			} else {
				mess.setStatus(0);
				mess.setMessage("添加区服失败");
			}
		} else {
			mess.setStatus(0);
			mess.setMessage("添加区服失败:已有相同ID的区服");
		}
		
		
		return mess;
	}

	@Override
	public BaseMessage delGameArea(GameArea area) throws Exception {
		BaseMessage mess = new BaseMessage();
		int status = gameAreaMapper.deleteGameArea(area);
		if (status == 1) {
			//删除成功的时候记得清空redis中数据
			//redisService.del("GAME_GAMEAREA_LIST", "GAMEAREA_LIST");
			mess.setStatus(status);
			mess.setMessage("删除成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("删除区服失败");
		}
		return mess;
	}

	@Override
	public BaseMessage editGameArea(GameArea area) throws Exception {
		return null;
	}

}
