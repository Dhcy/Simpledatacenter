package com.good.plat.datacenter.datastat.controller.buyplat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.buyplat.BuyPlatAdvertiser;
import com.good.plat.datacenter.datastat.entity.buyplat.BuyPlatDataQuery;
import com.good.plat.datacenter.datastat.entity.buyplat.BuyPlatSummary;
import com.good.plat.datacenter.datastat.entity.buyplat.SubAdvertiseChannel;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlActlvlStat;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlAdchannel;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlAdvertisers;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlBuckle;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlRegcountStat;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlRegisterStat;
import com.good.plat.datacenter.datastat.mapper.buyplat.TBLogplatMlchnlAdchannelMapper;
import com.good.plat.datacenter.datastat.mapper.buyplat.TBLogplatMlchnlAdvertisersMapper;
import com.good.plat.datacenter.datastat.mapper.buyplat.TBLogplatMlchnlBuckleMapper;
import com.good.plat.datacenter.datastat.service.buyplat.IBuyPlatService;
import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.login.service.impl.LoginServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping(value="/buyplat")
public class BuyPlatController  extends BaseController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBuyPlatService buyPlatService;
	@Autowired
	TBLogplatMlchnlAdvertisersMapper tBLogplatMlchnlAdvertisersMapper;
	@Autowired
	TBLogplatMlchnlBuckleMapper tBLogplatMlchnlBuckleMapper;
	@Autowired 
	TBLogplatMlchnlAdchannelMapper tBLogplatMlchnlAdchannelMapper;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value="/selectBuyPlatSummary", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<BuyPlatSummary> selectBuyPlatSummary(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		List<BuyPlatSummary> list = null;
		//log
		Date opDate = new Date();
		String methodName = "selectBuyPlatSummary";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		try{
//			int channelid = Integer.valueOf(searchData.getChecktype1());
//			if(channelid > 0){
//				List<Channel> channelList = new LinkedList<Channel>();
//				Channel channel = new Channel();
//				channel.setId(channelid);
//				channelList.add(channel);
//				searchData.setChannelList(channelList );
//			}
			//list = buyPlatService.selectBuyPlatSummary(searchData);
			if(list != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(BuyPlatSummary e : list){
					e.setStartDataTxt(sdf.format(e.getStatdate()));
					e.setTermtypeName(parseTermTypeName(e.getTermtype()));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	@RequestMapping(value="/selectBuyPlatDataQuery", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map selectBuyPlatDataQuery(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		Map map = new HashMap();
		double sumOfPayRate = 0.0,sumOfRechgMoney = 0.0,sumOfRechgCount = 0.0,sumOfPayARPU = 0.0;
		List<BuyPlatDataQuery> list = null;
		//log
		sysAccessLogService.log(request, getClass(), "selectBuyPlatDataQuery", new Date(), searchData);
		//
		try{
			list = buyPlatService.selectBuyPlatDataQuery(searchData);
			double totalRegisterCount = 0.0;
			
			
			if(list != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(BuyPlatDataQuery e : list){
					e.setStartDataTxt(sdf.format(e.getStatdate()));
					e.setTermtypeName(parseTermTypeName(e.getTermtype()));
					//
					if(e.getRechgmoney() == null){
						e.setRechgmoney(0);
					}
					if(e.getTotalrechgmoney() == null){
						e.setTotalrechgmoney(0.0);
					}
					sumOfRechgMoney += e.getTotalrechgmoney();
					if(e.getRechgcount() == null){
						e.setRechgcount(0);
					}
					if(e.getTotalrechgcount() == null){
						e.setTotalrechgcount(0);
					}
					sumOfRechgCount += e.getTotalrechgcount();
					
					if(e.getAdcost() == null){
						e.setAdcost(0.0);
					}
					
					e.setCostIncomeRate(NumberUtil.div(e.getAdcost(),e.getRechgmoney().doubleValue()));
					
					if(e.getOldcount() == null){
						e.setOldcount(0);
					}
					
					if(e.getRegcount() == null){
						e.setRegcount(0);
					}
					totalRegisterCount += e.getRegcount();
					
					e.setOldRate(NumberUtil.div(e.getOldcount().doubleValue(),e.getRegcount().doubleValue()));
					
					e.setLvl3Rate(NumberUtil.div(e.getLvl3count().doubleValue(),1.0));
					e.setRegisterTransRate(NumberUtil.div(e.getRegisterTransRate().doubleValue(),1.0));
					e.setDay2Rate(NumberUtil.div(e.getDay2Rate().doubleValue(),1.0));
					e.setDay3Rate(NumberUtil.div(e.getDay3Rate().doubleValue(),1.0));
					e.setDay7Rate(NumberUtil.div(e.getDay7Rate().doubleValue(),1.0));
					e.setDay15Rate(NumberUtil.div(e.getDay15Rate().doubleValue(),1.0));
					e.setDay30Rate(NumberUtil.div(e.getDay30Rate().doubleValue(),1.0));
					e.setPayRate(NumberUtil.div(e.getPayRate().doubleValue(),1.0));
					e.setTotalrechgARPU(NumberUtil.div(e.getTotalrechgARPU().doubleValue(),1.0));
					e.setArpu(NumberUtil.div(e.getArpu().doubleValue(),1.0));
					e.setActregcountRate(NumberUtil.div(e.getActregcountRate().doubleValue(),1.0));
				}
				for(BuyPlatDataQuery e : list){
					if(e.getTotalrechgcount() == null){
						e.setTotalrechgcount(0);
					}
					e.setTotalrechgPayRate(NumberUtil.mul(NumberUtil.div(e.getTotalrechgcount().doubleValue(), totalRegisterCount),100.0));
				}
				sumOfPayRate = NumberUtil.mul(NumberUtil.div(sumOfRechgCount, totalRegisterCount),100.0);
				sumOfPayARPU = NumberUtil.div(sumOfRechgMoney, sumOfRechgCount);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		map.put("items", list);
		map.put("sumOfPayRate", sumOfPayRate);
		map.put("sumOfRechgMoney", sumOfRechgMoney);
		map.put("sumOfRechgCount", sumOfRechgCount);
		map.put("sumOfPayARPU", sumOfPayARPU);
		//return list;
		return map;
	}
	@RequestMapping(value="/selectBuyPlatActorRegisterTimeDist", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<TBLogplatMlchnlRegisterStat> selectBuyPlatActorRegisterTimeDist(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectBuyPlatActorRegisterTimeDist";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<TBLogplatMlchnlRegisterStat> list = null;
		try{
			int channelid = Integer.valueOf(searchData.getChecktype1());
			if(channelid > 0){
				List<Channel> channelList = new LinkedList<Channel>();
				Channel channel = new Channel();
				channel.setId(channelid);
				channelList.add(channel);
				searchData.setChannelList(channelList );
			}
			list = buyPlatService.selectBuyPlatActorRegisterTimeDist(searchData);
			if(list != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(TBLogplatMlchnlRegisterStat e : list){
					e.setStartDataTxt(sdf.format(e.getStatdate()));
					e.setTermtypeName(parseTermTypeName(e.getTermtype()));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		return list;
	}
	@RequestMapping(value="/selectBuyPlatActorRegisterInfo", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<TBLogplatMlchnlRegcountStat> selectBuyPlatActorRegisterInfo(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectBuyPlatActorRegisterInfo";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<TBLogplatMlchnlRegcountStat> list = null;
		try{
			int channelid = Integer.valueOf(searchData.getChecktype1());
			if(channelid > 0){
				List<Channel> channelList = new LinkedList<Channel>();
				Channel channel = new Channel();
				channel.setId(channelid);
				channelList.add(channel);
				searchData.setChannelList(channelList );
			}
			list = buyPlatService.selectBuyPlatActorRegisterInfo(searchData);
			if(list != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(TBLogplatMlchnlRegcountStat e : list){
					//e.setStartDataTxt(sdf.format(e.getStatdate()));
					//e.setTermtypeName(parseTermTypeName(e.getTermtype()));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		return list;
	}
	@RequestMapping(value="/selectBuyPlatActorLevelDist", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<TBLogplatMlchnlActlvlStat> selectBuyPlatActorLevelDist(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectBuyPlatActorLevelDist";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
	
		//
		List<TBLogplatMlchnlActlvlStat> list = null;
		try{
			int channelid = Integer.valueOf(searchData.getChecktype1());
			if(channelid > 0){
				List<Channel> channelList = new LinkedList<Channel>();
				Channel channel = new Channel();
				channel.setId(channelid);
				channelList.add(channel);
				searchData.setChannelList(channelList );
			}
			list = buyPlatService.selectBuyPlatActorLevelDist(searchData);
			if(list != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(TBLogplatMlchnlActlvlStat e : list){
					e.setStartDataTxt(sdf.format(e.getStatdate()));
					e.setTermtypeName(parseTermTypeName(e.getTermtype()));
					if(e.getActlvl_1_2() == null){
						e.setActlvl_1_2(0);
					}
					if(e.getActlvl_3_10() == null){
						e.setActlvl_3_10(0);
					}
					if(e.getActlvl_11_20() == null){
						e.setActlvl_11_20(0);;
					}
					if(e.getActlvl_21_30() == null){
						e.setActlvl_21_30(0);;
					}
					if(e.getActlvl_31_40() == null){
						e.setActlvl_31_40(0);;
					}
					if(e.getActlvl_41_50() == null){
						e.setActlvl_41_50(0);;
					}
					if(e.getActlvl_51_X() == null){
						e.setActlvl_51_X(0);;
					}
					if(e.getRegcount() == null){
						e.setRegcount(0);
					}
					e.setActlvl_1_2_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_1_2().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setActlvl_3_10_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_3_10().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setActlvl_11_20_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_11_20().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setActlvl_21_30_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_21_30().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setActlvl_31_40_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_31_40().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setActlvl_41_50_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_41_50().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setActlvl_51_X_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_51_X().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		return list;
	}
	@RequestMapping(value="/selectAdvertiser", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public TBLogplatMlchnlAdvertisers selectAdvertiser(@RequestBody TBLogplatMlchnlAdvertisers advertiser,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectAdvertiser";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, advertiser);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		try{
			advertiser = tBLogplatMlchnlAdvertisersMapper.selectByPrimaryKey(advertiser.getId());
			TBLogplatMlchnlAdchannel ad = buyPlatService.selectAdChannelByChannelID(advertiser.getAdchannel());
			advertiser.setChannelName(ad.getChannelName());
		}catch(Exception e){
			e.printStackTrace();
		}
		return advertiser;
	}
	@RequestMapping(value="/selectAdvertiserList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	List<TBLogplatMlchnlAdvertisers> selectAdvertiserList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectAdvertiserList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<TBLogplatMlchnlAdvertisers> list = null;
		try{
			list = buyPlatService.selectAdvertiserList(searchData);
			if(list != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for(TBLogplatMlchnlAdvertisers e : list){
					if(e.getStartdate() != null){
						e.setStartDataTxt(sdf.format(e.getStartdate()));
					}
					if(e.getEnddate() != null){
						e.setEndDataTxt(sdf.format(e.getEnddate()));
					}
					if(e.getAdType() == null){
						e.setAdType((byte) -1);
					}
					if(e.getPricingType() == null){
						e.setPricingType((byte) -1);
					}
					e.setTermtypeName(parseTermTypeName(e.getTermType()));
					e.setAdTypeName(parseAdvertiseName(Integer.valueOf(e.getAdType())));
					e.setPricingTypeName(parsePrizeType(Integer.valueOf(e.getPricingType())));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 终端类型,1:IOS;2:Android
	 * @param type
	 * @return
	 */
	public static String parseTermTypeName(Integer type){
		String typeName = null;
		type = type == null ? -1 : type;
		switch(type){
		case 1:
			typeName = "IOS";
			break;
		case 2:
			typeName = "Android";
			break;
		case 3:
			typeName = "Winphone";
			break;
		case 4:
			typeName = "BlackBerry";
			break;
		case 5:
			typeName = "Kjava";
			break;
		case 6:
			typeName = "Windows(PC)";
			break;
		case 7:
			typeName = "IOS越狱";
			break;
		default:
			typeName = "未定义";
			break;
		}
		return typeName;
	}
	
	private static String parsePrizeType(Integer type){
		String name = "未定义";
		type = type == null ? -1 : type;
		switch (type) {
		case 0:
			name = "点击";
			break;
		case 1:
			name = "下载";
			break;
		case 2:
			name = "注册";
			break;
		case 3:
			name ="激活";
		case 4:
			name ="天";
		case 5:
			name ="CPS";
		default:
			name = "未定义";
			break;
		}
		return name;
		/* {id:0,name:"点击"},
         {id:1,name:"下载"},
         {id:2,name:"注册"},
         {id:3,name:"激活"},
         {id:4,name:"天"}*/
	}
	
	private static String parseAdvertiseName(Integer id){
		String name = "未定义";
		id = id == null ? -1 : id;
		switch (id) {
		case 1:
			name = "CPA";
			break;
		case 2:
			name = "CPT";
			break;
		case 3:
			name = "CPC";
			break;
		case 4:
			name ="CPD";
		case 5:
			name ="CPS";
		default:
			name = "未定义";
			break;
		}
		return name;
	}
	
	
	@RequestMapping(value="/addAdvertiser", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addAdvertiser(@RequestBody TBLogplatMlchnlAdvertisers advertiser,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "addAdvertiser", new Date(), advertiser);
		//
		BaseMessage bm = null;
		try{
			advertiser.setAdcost((int)(advertiser.getBusicount() * advertiser.getPrice()));
		}catch(Exception e){
			bm.setMessage("计算广告费出错,请检查输入!");
			e.printStackTrace();
			return bm;
		}
		TBLogplatMlchnlAdchannel adchannel = null;
		adchannel = buyPlatService.selectAdChannelByChannelID(advertiser.getAdchannel());
		if(adchannel == null){
			adchannel = new TBLogplatMlchnlAdchannel();
			adchannel.setChannelid(advertiser.getAdchannel());
			adchannel.setChannelName(advertiser.getChannelName());
			buyPlatService.updateAdChannel(adchannel);
		}
		//冗余 channel 在 广告主表
		bm = buyPlatService.addAdvertiser(advertiser);
		
		return bm;
	}
	
	@RequestMapping(value="/updateAdvertiser", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage updateAdvertiser(@RequestBody TBLogplatMlchnlAdvertisers advertiser,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "updateAdvertiser";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, advertiser);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage bm = null;
		TBLogplatMlchnlAdchannel adchannel = null;
		adchannel = buyPlatService.selectAdChannelByChannelID(advertiser.getAdchannel());
		if(adchannel == null){
			adchannel = new TBLogplatMlchnlAdchannel();
			adchannel.setChannelid(advertiser.getAdchannel());
			adchannel.setChannelName(advertiser.getChannelName());
			buyPlatService.updateAdChannel(adchannel);
		}
		bm = buyPlatService.updateAdvertiser(advertiser);
		return bm;
	}
	
	@RequestMapping(value="/deleteAdvertiserById", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deleteAdvertiserById(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteAdvertiserById";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage bm = null;
		bm = buyPlatService.deleteAdvertiserById(Integer.valueOf(searchData.getChecktype1()));
		return bm;
	}
	
	@RequestMapping(value="/selectAdvOfCPAList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<BuyPlatAdvertiser> selectAdvOfCPAList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectAdvOfCPAList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<BuyPlatAdvertiser> list = null;
		try{
			
			list = buyPlatService.selectAdvOfCPAList(searchData);
			if(list != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(BuyPlatAdvertiser e : list){
					e.setStartDataTxt(sdf.format(e.getStatdate()));
					e.setTermtypeName(parseTermTypeName(e.getTermtype()));
					if(e.getDay2retation() == null){
						e.setDay2retation(0);
					}
					if(e.getDay3retation() == null){
						e.setDay3retation(0);
					}
					if(e.getDay7retation() == null){
						e.setDay7retation(0);
					}
					if(e.getDay15retation() == null){
						e.setDay15retation(0);
					}
					if(e.getDay30retation() == null){
						e.setDay30retation(0);
					}
					e.setDay2retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay2retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay3retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay3retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay7retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay7retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay15retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay15retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay30retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay30retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	
	
	
	@RequestMapping(value="/selectAdvOfCPTList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<BuyPlatAdvertiser> selectAdvOfCPTList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectAdvOfCPTList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<BuyPlatAdvertiser> list = null;
		try{
			list = buyPlatService.selectAdvOfCPTList(searchData);
			if(list != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(BuyPlatAdvertiser e : list){
					e.setStartDataTxt(sdf.format(e.getStatdate()));
					e.setTermtypeName(parseTermTypeName(e.getTermtype()));
					if(e.getDay2retation() == null){
						e.setDay2retation(0);
					}
					if(e.getDay3retation() == null){
						e.setDay3retation(0);
					}
					if(e.getDay7retation() == null){
						e.setDay7retation(0);
					}
					if(e.getDay15retation() == null){
						e.setDay15retation(0);
					}
					if(e.getDay30retation() == null){
						e.setDay30retation(0);
					}
					e.setDay2retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay2retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay3retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay3retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay7retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay7retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay15retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay15retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay30retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay30retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	@RequestMapping(value="/selectAdvOfCPCList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<BuyPlatAdvertiser> selectAdvOfCPCList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectAdvOfCPCList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<BuyPlatAdvertiser> list = null;
		try{
			list = buyPlatService.selectAdvOfCPCList(searchData);
			if(list != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(BuyPlatAdvertiser e : list){
					e.setStartDataTxt(sdf.format(e.getStatdate()));
					e.setTermtypeName(parseTermTypeName(e.getTermtype()));
					if(e.getDay2retation() == null){
						e.setDay2retation(0);
					}
					if(e.getDay3retation() == null){
						e.setDay3retation(0);
					}
					if(e.getDay7retation() == null){
						e.setDay7retation(0);
					}
					if(e.getDay15retation() == null){
						e.setDay15retation(0);
					}
					if(e.getDay30retation() == null){
						e.setDay30retation(0);
					}
					e.setDay2retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay2retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay3retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay3retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay7retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay7retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay15retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay15retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay30retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay30retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	@RequestMapping(value="/selectAdvOfCPDList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<BuyPlatAdvertiser> selectAdvOfCPDList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectAdvOfCPDList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<BuyPlatAdvertiser> list = null;
		try{
			list = buyPlatService.selectAdvOfCPDList(searchData);
			if(list != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(BuyPlatAdvertiser e : list){
					e.setStartDataTxt(sdf.format(e.getStatdate()));
					e.setTermtypeName(parseTermTypeName(e.getTermtype()));
					if(e.getDay2retation() == null){
						e.setDay2retation(0);
					}
					if(e.getDay3retation() == null){
						e.setDay3retation(0);
					}
					if(e.getDay7retation() == null){
						e.setDay7retation(0);
					}
					if(e.getDay15retation() == null){
						e.setDay15retation(0);
					}
					if(e.getDay30retation() == null){
						e.setDay30retation(0);
					}
					e.setDay2retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay2retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay3retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay3retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay7retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay7retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay15retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay15retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					e.setDay30retation_rate(NumberUtil.mul(NumberUtil.div(e.getDay30retation().doubleValue(), e.getRegcount().doubleValue()), 100.0));
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	@RequestMapping(value="/selectMlchnlBuckleList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<TBLogplatMlchnlBuckle> selectMlchnlBuckleList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectMlchnlBuckleList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<TBLogplatMlchnlBuckle> list = null;
		try{
			list = buyPlatService.selectMlchnlBuckleList(searchData);
			if(list != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for(TBLogplatMlchnlBuckle e : list){
					if(e.getStartdate() != null){
						e.setStartDataTxt(sdf.format(e.getStartdate()));
					}
					if(e.getEnddate() != null){
						e.setEndDataTxt(sdf.format(e.getEnddate()));
					}
					e.setTermtypeName(parseTermTypeName(e.getTermType()));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value="/selectMlchnlBuckle", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public TBLogplatMlchnlBuckle selectMlchnlBuckle(@RequestBody TBLogplatMlchnlBuckle buckle,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectMlchnlBuckle";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, buckle);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		try{
			buckle = tBLogplatMlchnlBuckleMapper.selectByPrimaryKey(buckle.getId());
			TBLogplatMlchnlAdchannel ad = buyPlatService.selectAdChannelByChannelID(buckle.getAdchannel());
			buckle.setChannelName(ad.getChannelName());
		}catch(Exception e){
			e.printStackTrace();
		}
		return buckle;
	}
	@RequestMapping(value="/addMlchnlBuckle", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addMlchnlBuckle(@RequestBody TBLogplatMlchnlBuckle buckle,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "addMlchnlBuckle";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, buckle);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage bm = null;
		TBLogplatMlchnlAdchannel adchannel = null;
		adchannel = buyPlatService.selectAdChannelByChannelID(buckle.getAdchannel());
		if(adchannel == null){
			adchannel = new TBLogplatMlchnlAdchannel();
			adchannel.setChannelid(buckle.getAdchannel());
			adchannel.setChannelName(buckle.getChannelName());
			buyPlatService.updateAdChannel(adchannel);
		}
		bm = buyPlatService.addMlchnlBuckle(buckle);
		
		return bm;
	}
	@RequestMapping(value="/updateMlchnlBuckle", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage updateMlchnlBuckle(@RequestBody TBLogplatMlchnlBuckle buckle,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "updateMlchnlBuckle";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, buckle);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage bm = null;
		TBLogplatMlchnlAdchannel adchannel = null;
		adchannel = buyPlatService.selectAdChannelByChannelID(buckle.getAdchannel());
		if(adchannel == null){
			adchannel = new TBLogplatMlchnlAdchannel();
			adchannel.setChannelid(buckle.getAdchannel());
			adchannel.setChannelName(buckle.getChannelName());
			buyPlatService.updateAdChannel(adchannel);
		}
		bm = buyPlatService.updateMlchnlBuckle(buckle);
		return bm;
	}
	
	@RequestMapping(value="/deleteMlchnlBuckleById", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deleteMlchnlBuckleById(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteMlchnlBuckleById";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage bm = null;
		bm = buyPlatService.deleteMlchnlBuckleById(Integer.valueOf(searchData.getChecktype1()));
		return bm;
	}
	
	@RequestMapping(value="/disableMlchnlBuckleById", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage disableMlchnlBuckleById(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "disableMlchnlBuckleById";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage bm = null;
		bm = buyPlatService.disableMlchnlBuckleById(Integer.valueOf(searchData.getChecktype1()));
		return bm;
	}
	
	@RequestMapping(value="/enableMlchnlBuckleById", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage enableMlchnlBuckleById(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "enableMlchnlBuckleById";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage bm = null;
		bm = buyPlatService.enableMlchnlBuckleById(Integer.valueOf(searchData.getChecktype1()));
		return bm;
	}
	
	@RequestMapping(value="/selectAdChannelList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<TBLogplatMlchnlAdchannel> selectAdChannelList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectAdChannelList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<TBLogplatMlchnlAdchannel> list = null;
		list = buyPlatService.selectAdChannelList(searchData);
		return list;
	}
	
	@RequestMapping(value="/selectAdChannel", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public TBLogplatMlchnlAdchannel selectAdChannel(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectAdChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		TBLogplatMlchnlAdchannel adChannel = null;
		adChannel = buyPlatService.selectAdChannel(searchData);
		return adChannel;
	}
	
	@RequestMapping(value="/updateAdChannel", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage updateAdChannel(@RequestBody TBLogplatMlchnlAdchannel adchannel,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "updateAdChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, adchannel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage bm = null;
		bm = buyPlatService.updateAdChannel(adchannel);
		return bm;
	}
	
	@RequestMapping(value="/deleteAdChannelByIDAndChanneldID", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deleteAdChannelByIDAndChanneldID(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteAdChannelByIDAndChanneldID";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage bm = null;
		bm = buyPlatService.deleteAdChannelByIDAndChanneldID(searchData);
		return bm;
	}
	
	@RequestMapping(value="/selectMyAdChannelList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<TBLogplatMlchnlAdchannel> selectMyAdChannelList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectMyAdChannelList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null,searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		int userid = LoginServiceImpl.getUserID(session);
		List<TBLogplatMlchnlAdchannel> list = null;
		searchData.setUserid(userid);
		list = buyPlatService.selectUserAdchannelList(searchData);
		return list;
	}
	
	@RequestMapping(value="/selectSubChannelList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SubAdvertiseChannel> selectSubChannelList(@RequestBody SubAdvertiseChannel subchannel,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectSubChannelList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null,subchannel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		int userid = LoginServiceImpl.getUserID(session);
		subchannel.setUserid(userid);
		List<SubAdvertiseChannel> subChannelList = null;
		subChannelList = buyPlatService.selectSubChannelList(subchannel);
		
		return subChannelList;
	}
	
	@RequestMapping(value="/selectMyAdvCPAList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<BuyPlatAdvertiser> selectMyAdvCPAList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectMyAdvCPAList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<BuyPlatAdvertiser> list = null;
		try{
			List<TBLogplatMlchnlAdchannel> adchannelList = LoginServiceImpl.getSessionAdvertiseChannel(session);
			if(adchannelList != null && adchannelList.size() != 0){
				for(TBLogplatMlchnlAdchannel ad : adchannelList){
					if(ad.getChannelid() != null && ad.getChannelid().equals(searchData.getChannelid())){
						list = selectAdvOfCPAList(searchData, session, request);
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	@RequestMapping(value="/selectMyAdvCPCList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<BuyPlatAdvertiser> selectMyAdvCPCList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectMyAdvCPCList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<BuyPlatAdvertiser> list = null;
		try{
			List<TBLogplatMlchnlAdchannel> adchannelList = LoginServiceImpl.getSessionAdvertiseChannel(session);
			if(adchannelList != null && adchannelList.size() != 0){
				for(TBLogplatMlchnlAdchannel ad : adchannelList){
					if(ad.getChannelid() != null && ad.getChannelid().equals(searchData.getChannelid())){
						list = selectAdvOfCPCList(searchData, session, request);
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	@RequestMapping(value="/selectMyAdvCPDList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<BuyPlatAdvertiser> selectMyAdvCPDList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectMyAdvCPDList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<BuyPlatAdvertiser> list = null;
		try{
			List<TBLogplatMlchnlAdchannel> adchannelList = LoginServiceImpl.getSessionAdvertiseChannel(session);
			if(adchannelList != null && adchannelList.size() != 0){
				for(TBLogplatMlchnlAdchannel ad : adchannelList){
					if(ad.getChannelid() != null && ad.getChannelid().equals(searchData.getChannelid())){
						list = selectAdvOfCPDList(searchData, session, request);
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	@RequestMapping(value="/selectMyAdvCPTList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<BuyPlatAdvertiser> selectMyAdvCPTList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectMyAdvCPTList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<BuyPlatAdvertiser> list = null;
		try{
			List<TBLogplatMlchnlAdchannel> adchannelList = LoginServiceImpl.getSessionAdvertiseChannel(session);
			if(adchannelList != null && adchannelList.size() != 0){
				for(TBLogplatMlchnlAdchannel ad : adchannelList){
					if(ad.getChannelid() != null && ad.getChannelid().equals(searchData.getChannelid())){
						list = selectAdvOfCPTList(searchData, session, request);
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
}
