package com.good.plat.datacenter.game.controller;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningItem;
import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.game.entity.GameChannel;
import com.good.plat.datacenter.game.entity.GameChannelDetail;
import com.good.plat.datacenter.game.entity.GameChannels;
import com.good.plat.datacenter.game.entity.SubChannel;
import com.good.plat.datacenter.game.entity.TBLogplatGameChannel;
import com.good.plat.datacenter.game.mapper.TBLogplatGameChannelMapper;
import com.good.plat.datacenter.game.service.impl.ChannelServiceImpl;
import com.good.plat.datacenter.game.service.impl.SubChannelServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: ChannelController
 * @Description: 公司管理
 * @author dmw
 * @date 2016年1月19日 上午10:53:32
 */

@Controller
@RequestMapping("/channel")
public class ChannelController extends BaseController{

	@Autowired
	private ChannelServiceImpl channelService;
	
	@Autowired
	private SubChannelServiceImpl subChannelService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	
	@RequestMapping(value = "/channelList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Channel> findChannelList() throws Exception {
		
		//log
		Date opDate = new Date();
		String methodName = "findChannelList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Channel> channelList = channelService.findChannelList();
		return channelList;
	}
	
	/**
	 * @Title: getSelectChannels
	 * @Description: 获取筛选框channel数据
	 * @param channel
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Channel>
	 * @author dmw
	 * @date 2016年3月18日 下午3:21:23
	 */
	@RequestMapping(value = "/getSelectChannels", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Channel> getSelectChannels(@RequestBody Channel channel, 
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "getSelectChannels";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, channel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return channelService.getSelectChannels(session, channel);
		
	}
	
	
	@RequestMapping(value = "/channelSubChannelListByChannelId", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Channel> findChannelSubChannelListByChannelId(@RequestBody Channel channel) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "findChannelSubChannelListByChannelId";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, channel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<Channel> channelList = channelService.findChannelSubChannelListByChannelId(channel.getId());
		
		return channelList;
	}
	
	
	/**
	 * @Title: findChannelSubchannelList
	 * @Description: 获取筛选列表
	 * @param company
	 * @throws Exception
	 * List<Company>
	 * @author dmw
	 * @date 2016年1月19日 下午3:24:27
	 */
	@RequestMapping(value = "/channelSubChannelListByChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Channel> findChannelSubChannelListByChannel(@RequestBody Channel channel) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "findChannelSubChannelListByChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, channel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		
		List<Channel> channelList = channelService.findChannelSubChannelListByChannel(channel);
		
		return channelList;
	}
	

	/**
	 * @Title: deleteChannel
	 * @Description: 删除
	 * @param channel
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年1月12日 下午5:50:12
	 */
	@RequestMapping(value = "/deleteChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deleteChannel(@RequestBody Channel channel) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, channel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = channelService.deleteChannel(channel.getId());

		return mess;
	}
	
	@RequestMapping(value = "/addChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addChannel(@Validated @RequestBody Channel channel, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "addChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, channel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = channelService.addChannel(channel);
		}

		return mess;
	}
	
	
	@RequestMapping(value = "/updataChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage updataChannel(@Validated @RequestBody Channel channel, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "updataChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, channel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = channelService.editChannel(channel);
		}

		return mess;
	}
	
	@RequestMapping(value = "/getChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Channel getChannel(@RequestBody Channel channel) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "getChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, channel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		Channel cha = channelService.findChannelById(channel.getId());

		return cha;
	}
	
	
	@RequestMapping(value = "/getSubChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public SubChannel getSubChannel(@RequestBody SubChannel channel) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "getSubChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, channel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		SubChannel sub = subChannelService.findSubChannelById(channel.getId());

		return sub;
	}
	
	
	@RequestMapping(value = "/subChannelList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SubChannel> findSubChannelList(@RequestBody SubChannel subChannel) 
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "findSubChannelList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, subChannel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<SubChannel> subChannelList = subChannelService.findSubChannelList(subChannel);
		
		return subChannelList;
	}
	
	
	@RequestMapping(value = "/deleteSubChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deleteSubChannel(@RequestBody SubChannel subChannel) 
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteSubChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, subChannel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = subChannelService.deleteSubChannel(subChannel.getId());

		return mess;
	}
	
	
	@RequestMapping(value = "/addSubChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addSubChannel(@Validated @RequestBody SubChannel subChannel, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "addSubChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, subChannel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = subChannelService.addSubChannel(subChannel);
		}

		return mess;
	}
	
	
	@RequestMapping(value = "/updataSubChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage updataSubChannel(@Validated @RequestBody SubChannel subChannel, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "updataSubChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, subChannel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = subChannelService.editSubChannel(subChannel);
		}
		return mess;
	}
	
	@RequestMapping(value = "/gameChannelUI", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public GameChannel gameChannelUI(@RequestBody GameChannel gameChannel, 
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "gameChannelUI";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, gameChannel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return channelService.gameChannelUI(gameChannel, session);
		
	}
	
	@RequestMapping(value = "/gameChannelManage", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage gameChannelManage(@RequestBody GameChannel gameChannel) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "gameChannelManage";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, gameChannel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		//System.out.println(gameChannel.toString());
		mess = channelService.gameChannelManage(gameChannel);

		return mess;
	}
	
	@RequestMapping(value = "/updateGameChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage updateGameChannel(@RequestBody TBLogplatGameChannel gameChannel) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "updateGameChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, gameChannel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = null;
		mess = channelService.updateGameChannel(gameChannel);
		return mess;
	}
	
	@RequestMapping(value = "/selectGameChannelsList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<GameChannels> selectGameChannelsList(@RequestBody TBLogplatGameChannel gameChannel) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectGameChannelsList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, gameChannel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<GameChannels> list = channelService.selectGameChannels(gameChannel);
		return list;
	}
	
	@RequestMapping(value = "/selectGameChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public TBLogplatGameChannel selectGameChannel(@RequestBody TBLogplatGameChannel gameChannel) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectGameChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, gameChannel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		gameChannel = channelService.selectGameChannelById(gameChannel.getId());
//		if(gameChannel.getChannelid() != null && gameChannel.getGameid() != null){
//			 List<TBLogplatGameChannel> list = channelService.selectGameChannel(gameChannel);
//			 gameChannel = (list == null || list.size() == 0) ? null :  list.get(0);
//		}
		return gameChannel;
	}
	
	
	@RequestMapping(value = "/exportGameChannel", method = {RequestMethod.POST})
	public void exportGameChannel(@RequestBody TBLogplatGameChannel gameChannel,HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportGameChannel", new Date(), gameChannel);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "游戏渠道列表" + ".xlsx";
		//
		List<GameChannelDetail> gclist = channelService.selectGameChannelSubchannelRelationshipDetail(gameChannel);
		//
		if(gclist != null){
			String[] title = new String[] {"渠道简称","渠道全称","公司ID","全渠道子ID","服务器(类型)"
					,"接入服务器","接入游戏","SDK","SDK版本号","SDK下载地址"
					,"客户端类型","角标","开机画面","包名","客户端需求"
					,"强更地址","接入参数","其它备注"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(GameChannelDetail e : gclist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getChannelSimName();
				items[count++] = e.getChannelName();
				items[count++] = e.getChannelid();
				//subchannels
				StringBuffer subchannels = new StringBuffer();
				if(e.getSubChannelList() != null){
					for(SubChannel sub : e.getSubChannelList()){
						if(subchannels.length() != 0){
							subchannels.append(" ");
						}
						subchannels.append(sub.getSubchannelId());
					}
				}
				items[count++] = subchannels.toString();
				//
				items[count++] = e.getServertype();
				items[count++] = e.getJoinserver();
				items[count++] = e.getGameName();
				items[count++] = e.getSdkname();
				items[count++] = e.getSdkversion();
				items[count++] = e.getSdkdownloadaddr();
				items[count++] = e.getClientplattype();
				items[count++] = e.getIcon();
				items[count++] = e.getLaunchvideo();
				items[count++] = e.getPackagename();
				items[count++] = e.getClientpacktype();
				items[count++] = e.getUpdateaddr();
				items[count++] = e.getAccess_options();
				items[count++] = e.getRemark();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("已建立告警项");
		}
		//
		Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
		ExcelGenerator.fillWorkBook(w,sheetNameList, list);
		
		//
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		w.write(baos);
		String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		HTTPUtil.responseFile(response, request, contentType , filename, baos);
		
		
	}
	
}
