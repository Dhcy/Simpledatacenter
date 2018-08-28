package com.good.plat.datacenter.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.PropertiesUtil;
import com.good.plat.datacenter.common.redis.service.impl.RedisServiceImpl;
import com.good.plat.datacenter.game.entity.Game;
import com.good.plat.datacenter.game.entity.GameLanguage;
import com.good.plat.datacenter.game.entity.TBLogplatAttrValue;
import com.good.plat.datacenter.game.entity.TBLogplatGameVersion;
import com.good.plat.datacenter.game.mapper.GameMapper;
import com.good.plat.datacenter.game.mapper.TBLogplatAttrValueMapper;
import com.good.plat.datacenter.game.mapper.TBLogplatAttrValueMapper2;
import com.good.plat.datacenter.game.mapper.TBLogplatGameVersionMapper;
import com.good.plat.datacenter.game.mapper.TBLogplatGameVersionMapper2;
import com.good.plat.datacenter.game.service.GameService;

@Service(value="gameService")
public class GameServiceImpl implements GameService {
	public static int tb_logplat_attr_value_gamelanguage_attr_id = Integer.valueOf(PropertiesUtil.getValue("tb_logplat_attr_value_gamelanguage_attr_id"));
	public static String tb_logplat_attr_value_gamelanguage_desc = PropertiesUtil.getValue("tb_logplat_attr_value_gamelanguage_desc");
	public GameServiceImpl() {
	}
	
	@Autowired
	private RedisServiceImpl redisService;
	
	@Autowired
	private GameMapper gameMapper;
	@Autowired
	private TBLogplatAttrValueMapper2 attrValueMapper2;
	@Autowired
	private TBLogplatAttrValueMapper attrValueMapper;
	@Autowired
	private TBLogplatGameVersionMapper gameVersionMapper;
	@Autowired
	private TBLogplatGameVersionMapper2 gameVersionMapper2;
	@Override
	public Game findGameById(Integer gameid) throws Exception {
		Game game = gameMapper.selectByPrimaryKey(gameid);
		return game;
	}
	

	@Override
	public List<Game> findGameList(Integer projectid) throws Exception {
		List<Game> gameList = new ArrayList<Game>();
		gameList = gameMapper.selectGameListByProjectId(projectid);
		/*if (projectid != -1 && projectid != null) {
			gameList = gameMapper.selectGameListByProjectId(projectid);
		} else {
			if (redisService.getBytes("GAME_LIST") != null) {
				gameList = (List<Game>) redisService
						.byteArrayToObject(redisService.getBytes("GAME_LIST"));
			} else {
				gameList = gameMapper.selectGameListByProjectId(projectid);
				if (gameList.size() > 0) {
					redisService.set("GAME_LIST".getBytes(), 
							redisService.objectToByteArray(gameList));
				}
			}
		}*/
		return gameList;
	}

	@Override
	public List<Game> findProjectGameAreaList(Integer projectid)
			throws Exception {
		List<Game> gameList = new ArrayList<Game>();
		gameList = gameMapper.selectProjectGameAreaList(projectid);
		/*if (projectid != -1 && projectid != null) {
			gameList = gameMapper.selectProjectGameAreaList(projectid);
		} else {
			//查询全部时才缓存
			if (redisService.getBytes("PROJECT_GAME_AREA_LIST") != null) {
				gameList = (List<Game>) redisService
						.byteArrayToObject(redisService.getBytes("PROJECT_GAME_AREA_LIST"));
			} else {
				gameList = gameMapper.selectProjectGameAreaList(projectid);
				if (gameList.size() > 0) {
					redisService.set("PROJECT_GAME_AREA_LIST".getBytes(), 
							redisService.objectToByteArray(gameList));
				}
			}
		}*/
		
		return gameList;
	}

	@Override
	public List<Game> findProjectGameList(Integer projectid) throws Exception {
		List<Game> gameList = new ArrayList<Game>();
		gameList = gameMapper.selectProjectGamesList(projectid);
		/*if (projectid != -1 && projectid != null) {
			gameList = gameMapper.selectProjectGamesList(projectid);
		} else {
			//查询全部时才缓存
			if (redisService.getBytes("GAME_PROJECT_LIST") != null) {
				gameList = (List<Game>) redisService
						.byteArrayToObject(redisService.getBytes("GAME_PROJECT_LIST"));
			} else {
				gameList = gameMapper.selectProjectGamesList(projectid);
				if (gameList.size() > 0) {
					redisService.set("GAME_PROJECT_LIST".getBytes(), 
							redisService.objectToByteArray(gameList));
				}
			}
		}*/
		
		return gameList;
	}
	

	@Override
	public List<Game> findGameAreaList(Integer gameid) throws Exception {
		List<Game> gameList = new ArrayList<Game>();
		gameList = gameMapper.selectGameAreaList(gameid);
		/*if (gameid != -1 && gameid != null) {
			gameList = gameMapper.selectGameAreaList(gameid);
		} else {
			//查询全部时才缓存
			if (redisService.getBytes("GAME_AREA_LIST") != null) {
				gameList = (List<Game>) redisService
						.byteArrayToObject(redisService.getBytes("GAME_AREA_LIST"));
			} else {
				gameList = gameMapper.selectGameAreaList(gameid);
				if (gameList.size() > 0) {
					redisService.set("GAME_AREA_LIST".getBytes(), 
							redisService.objectToByteArray(gameList));
				}
			}
			
		}*/
		
		return gameList;
	}

	@Override
	public BaseMessage addGame(Game game) throws Exception {
		BaseMessage mess = new BaseMessage();
		
		Game ga = gameMapper.selectByPrimaryKey(game.getId());
		
		if (ga == null) {
			int status = gameMapper.insertSelective(game);
			if (status == 1) {
				//添加成功的时候记得清空redis中数据
				//redisService.del("GAME_LIST", "PROJECT_GAME_AREA_LIST", 
						//"GAME_PROJECT_LIST", "GAME_AREA_LIST");
				mess.setStatus(status);
				mess.setMessage("添加成功");
			} else {
				mess.setStatus(0);
				mess.setMessage("添加游戏失败");
			}
		} else {
			mess.setStatus(0);
			mess.setMessage("添加游戏失败:已有相同ID的游戏");
		}
		return mess;
	}

	@Override
	public BaseMessage deleteGame(Integer id) throws Exception {
		BaseMessage mess = new BaseMessage();
		int status = gameMapper.deleteByPrimaryKey(id);
		if (status == 1) {
			//删除成功的时候记得清空redis中数据
			//redisService.del("GAME_LIST", "PROJECT_GAME_AREA_LIST", 
					//"GAME_PROJECT_LIST", "GAME_AREA_LIST");
			mess.setStatus(status);
			mess.setMessage("删除成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("删除游戏失败");
		}
		return mess;
	}

	
	@Override
	public String editGame(Game game) throws Exception {
		return null;
	}


	@Override
	public List<TBLogplatAttrValue> findLanguageList() throws Exception {
		int langugage_attr_id = 13;
		List<TBLogplatAttrValue> list = attrValueMapper2.selectAttrValueList(langugage_attr_id);
		return list;
	}

	
	@Override
	public List<TBLogplatGameVersion> findGameVersionList(Integer gameid) throws Exception {
		List<TBLogplatGameVersion> vlist = null;
		Game game = gameMapper.selectByPrimaryKey(gameid);
		if(game != null){
			vlist = gameVersionMapper2.selectGameVersionByGameId(gameid);
			if(vlist != null){
				for(TBLogplatGameVersion gv : vlist){
					gv.setGameName(game.getName());
				}
			}
		}
		return vlist;
	}
	
	@Override
	public BaseMessage deleteGameVersion(Integer version_id) throws Exception {
		BaseMessage bm = new BaseMessage();
		TBLogplatGameVersion version = gameVersionMapper.selectByPrimaryKey(version_id);
		if(version != null && gameVersionMapper.deleteByPrimaryKey(version_id) == 1){
			bm.setStatus(1);
		}else{
			bm.setStatus(0);
		}
		return bm;
	}


	@Override
	public BaseMessage addGameVersion(TBLogplatGameVersion version) throws Exception {
		BaseMessage bm = new BaseMessage();
		if(gameVersionMapper.insertSelective(version) == 1){
			bm.setStatus(1);
		}else{
			bm.setStatus(0);
		}
		return bm;
	}


	@Override
	public BaseMessage addGameLanguage(TBLogplatAttrValue gl) throws Exception {
		BaseMessage bm = new BaseMessage();
		gl.setAttr_id(tb_logplat_attr_value_gamelanguage_attr_id);
		gl.setDescr(tb_logplat_attr_value_gamelanguage_desc);
		if(attrValueMapper.insertSelective(gl) == 1){
			bm.setStatus(1);
		}else{
			bm.setStatus(0);
		}
		return bm;
	}


	@Override
	public BaseMessage deleteGameLanguageById(Integer id) throws Exception {
		BaseMessage bm = new BaseMessage();
		TBLogplatAttrValue gl = attrValueMapper.selectByPrimaryKey(id);
		if(gl != null && attrValueMapper.deleteByPrimaryKey(gl.getId()) == 1){
			bm.setStatus(1);
		}else{
			bm.setStatus(0);
		}
		return bm;
	}


	@Override
	public List<GameLanguage> getGameLanguageList(GameLanguage gl) throws Exception {
		gl.setAttr_id(tb_logplat_attr_value_gamelanguage_attr_id);
		List<GameLanguage> list = null;
		list = attrValueMapper2.getGameLanguageList(gl);
		return list ;
	}


	@Override
	public BaseMessage updateGame(Game game) throws Exception {
		// TODO Auto-generated method stub、
		BaseMessage msg=new BaseMessage();
		int state=gameMapper.updateByPrimaryKey(game);
		if(state==0){
			msg.setStatus(0);
			msg.setMessage("修改失败,没这个游戏ID");
		}else{
			msg.setStatus(1);
			msg.setMessage("修改成功");
		}
		return msg;
	}


	@Override
	public Game getGame(Game game) {
		// TODO Auto-generated method stub
		Game gameInfo=gameMapper.getGame(game);
		return gameInfo;
	}

}
