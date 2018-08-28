package com.good.plat.datacenter.game.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.game.entity.Game;
import com.good.plat.datacenter.game.entity.GameLanguage;
import com.good.plat.datacenter.game.entity.TBLogplatAttrValue;
import com.good.plat.datacenter.game.entity.TBLogplatGameVersion;

/**
 * @ClassName: GameService
 * @Description: 游戏查询接口
 * @author dmw
 * @date 2016年1月14日 上午10:15:04
 */
public interface GameService {
	
	/**
	 * @Title: findGameById
	 * @Description: 获取游戏信息
	 * @param gameid
	 * @throws Exception
	 * Game
	 * @author dmw
	 * @date 2016年1月14日 上午10:47:34
	 */
	public Game findGameById(Integer gameid) throws Exception;
	
	/**
	 * @Title: findGameList
	 * @Description: 获取游戏列表
	 * @throws Exception
	 * List<Project>
	 * @author dmw
	 * @date 2016年1月14日 上午10:16:45
	 */
	public List<Game> findGameList(Integer projectid) throws Exception;
	
	
	/**
	 * @Title: findProjectGameAreaList
	 * @Description: 获取游戏列表,关联查询project，area
	 * @param projectid : -1或则null查询全部
	 * @throws Exception
	 * List<Game>
	 * @author dmw
	 * @date 2016年1月14日 上午10:17:32
	 */
	public List<Game> findProjectGameAreaList(Integer projectid) throws Exception;

	
	/**
	 * @Title: findProjectGameList
	 * @Description: 获取游戏列表,关联查询project
	 * @param projectid : -1或则null查询全部
	 * @throws Exception
	 * List<Game>
	 * @author dmw
	 * @date 2016年1月14日 上午10:17:32
	 */
	public List<Game> findProjectGameList(Integer projectid) throws Exception;
	
	/**
	 * @Title: findGameAreaList
	 * @Description: 获取游戏列表,关联查询gamearea
	 * @param gameid : -1或则null查询全部
	 * @throws Exception
	 * List<Game>
	 * @author dmw
	 * @date 2016年1月14日 上午10:36:41
	 */
	public List<Game> findGameAreaList(Integer gameid) throws Exception;
	
	
	
	/**
	 * @Title: addGame
	 * @Description: 增加
	 * @param game
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年1月14日 上午10:36:41
	 */
	public BaseMessage addGame(Game game) throws Exception;
	
	
	/**
	 * @Title: deleteGame
	 * @Description: 删除
	 * @param id
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年1月14日 上午10:36:41
	 */
	public BaseMessage deleteGame(Integer id) throws Exception;
	
	
	/**
	 * @Title: editGame
	 * @Description: 修改
	 * @param game
	 * @throws Exception
	 * String
	 * @author dmw
	 * @date 2016年1月14日 上午10:36:41
	 */
	public String editGame(Game game) throws Exception;
	
	
	public List<TBLogplatAttrValue> findLanguageList() throws Exception;
	public List<TBLogplatGameVersion> findGameVersionList(Integer gameid) throws Exception;
	public BaseMessage deleteGameVersion(Integer version_id) throws Exception;
	public BaseMessage addGameVersion(TBLogplatGameVersion version) throws Exception;
	
	/**
	 * 
	 * @Title: addGameLanguage
	 * @Description: TODO
	 * @param gl
	 * attr_value : gameid
	 * attr_desc : language
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author moxw
	 * @date 2016年9月13日 下午6:07:57
	 */
	public BaseMessage addGameLanguage(TBLogplatAttrValue gl) throws Exception;
	public BaseMessage deleteGameLanguageById(Integer id) throws Exception;
	/**
	 * 
	 * @Title: getGameLanguageList
	 * @Description: TODO
	 * @param gl
	 * gameid[,attr_id]
	 * @return
	 * @throws Exception
	 * List<GameLanguage>
	 * @author moxw
	 * @date 2016年9月13日 下午5:54:22
	 */
	public List<GameLanguage> getGameLanguageList(GameLanguage gl) throws Exception;
	/**
	 * 修改游戏信息
	 * @Title: updateGame
	 * @Description: TODO
	 * @param game
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-11-21 上午09:36:10
	 */
	public BaseMessage updateGame(Game game)throws Exception;
	/**
	 * 获取游戏信息
	 * @Title: getGame
	 * @Description: TODO
	 * @param game
	 * @return
	 * Game
	 * @author hwj
	 * @date 2018-2-5 上午11:12:59
	 */
	public Game getGame(Game game);
}
