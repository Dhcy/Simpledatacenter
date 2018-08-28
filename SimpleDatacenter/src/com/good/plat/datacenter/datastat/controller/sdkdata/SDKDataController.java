package com.good.plat.datacenter.datastat.controller.sdkdata;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseResult;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.base.PropertiesUtil;
import com.good.plat.datacenter.common.base.SDKDataBaseSearchData;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.base.Value2NameParser;
import com.good.plat.datacenter.common.util.DESUtil;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.sdkdata.SdkDataSummary;
import com.good.plat.datacenter.datastat.entity.sdkdata.SdkDataVipDistInfo;
import com.good.plat.datacenter.datastat.entity.sdkdata.SdkNewsStat;
import com.good.plat.datacenter.datastat.service.sdkdata.SDKDataService;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
@Controller
@RequestMapping(value="/sdkdata")
public class SDKDataController extends BaseController {
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	SDKDataService sDKDataService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 数据总览
	 * @Title: selectSDKDataSummary
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:01
	 */
	@RequestMapping(value="/selectSDKDataSummary", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKDataSummary(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKDataSummary", new Date(), searchData);
		List<SdkDataSummary> list = sDKDataService.selectSDKDataSummary(searchData);
		list = ajustScaleOrMul100(list);
		return list;
	}
	
	/**
	 * 数据总览
	 * @Title: exportSDKDataSummary
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:01
	 */
	@RequestMapping(value="/exportSDKDataSummary", method={RequestMethod.POST})
	@ResponseBody
	public void exportSDKDataSummary(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		sysAccessLogService.log(request, getClass(), "exportSDKDataSummary", new Date(), searchData);
		try{
			List<SdkDataSummary> list = sDKDataService.selectSDKDataSummary(searchData);
			list = ajustScaleOrMul100(list);
			//
			List<List<Object[]>> excelDataList = new LinkedList();
			String filename = "数据总览.xlsx";
			//
			if(list != null){
				String[] title = new String[] {"日期","系统","总注册用户","总活跃用户","总充值金额","总充值人数","总充值次数","总arpu","总arpu","总付费率","累积付费玩家","总流失用户","总回流用户"};
				List<Object[]> sheetDataList = new ArrayList();
				sheetDataList.add(title);
				for(SdkDataSummary e : list){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = DateUtil.format2YYYY_MM_DD(e.getStatdate());
					items[count++] = Value2NameParser.parseOS(e.getOs());
					items[count++] = e.getAll_regist();
					items[count++] = e.getLogin();
					items[count++] = e.getAll_money();
					items[count++] = e.getAll_rechg();
					items[count++] = e.getRechg_time();
					items[count++] = e.getArrpu();
					items[count++] = e.getArpu();
					items[count++] = e.getPayrate();
					items[count++] = e.getTotal_recharge();
					items[count++] = e.getLost();
					items[count++] = e.getLost_return();
					sheetDataList.add(items);
				}
				excelDataList.add(sheetDataList);
			}
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, excelDataList);
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	

	/**
	 * 数据总览明细
	 * @Title: selectSDKDataSummaryDetail
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:10
	 */
	@RequestMapping(value="/selectSDKDataSummaryDetail", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKDataSummaryDetail(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKDataSummaryDetail", new Date(), searchData);
		List<SdkDataSummary> list = sDKDataService.selectSDKDataSummaryDetail(searchData);
		list = ajustScaleOrMul100(list);
		return list;
	}

	/**
	 * 新增数据
	 * @Title: selectSDKDataNewAddData
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:18
	 */
	@RequestMapping(value="/selectSDKDataNewAddData", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKDataNewAddData(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "", new Date(), searchData);
		List<SdkDataSummary> list = sDKDataService.selectSDKDataNewAddData(searchData);
		list = ajustScaleOrMul100(list);
		return list;
	}
	
	private List<SdkDataSummary> ajustScaleOrMul100(List<SdkDataSummary> list){
		if(!Validator.isNull(list)){
			for(SdkDataSummary e : list){
				//e.set(NumberUtil.ajustScale(e.get,NumberUtil.DEFAULT_SCALE));
				e.setNew_money(NumberUtil.ajustScale(e.getNew_money(),NumberUtil.DEFAULT_SCALE));
				e.setTotal_money(NumberUtil.ajustScale(e.getTotal_money(),NumberUtil.DEFAULT_SCALE));
				e.setTotal_vip_money(NumberUtil.ajustScale(e.getTotal_vip_money(),NumberUtil.DEFAULT_SCALE));
				e.setNew_vip_money(NumberUtil.ajustScale(e.getNew_vip_money(),NumberUtil.DEFAULT_SCALE));
				e.setVip_money(NumberUtil.ajustScale(e.getVip_money(),NumberUtil.DEFAULT_SCALE));
				e.setArrpu(NumberUtil.ajustScale(e.getArrpu(), NumberUtil.DEFAULT_SCALE));
				e.setArpu(NumberUtil.ajustScale(e.getArpu(), NumberUtil.DEFAULT_SCALE));
				//
				e.setPayrate(NumberUtil.multi100(e.getPayrate(), NumberUtil.DEFAULT_SCALE));
				e.setActive_regist_rate(NumberUtil.multi100(e.getActive_regist_rate(), NumberUtil.DEFAULT_SCALE));
				e.setRegist_actor_rate(NumberUtil.multi100(e.getRegist_actor_rate(), NumberUtil.DEFAULT_SCALE));
				e.setDay1rate(NumberUtil.multi100(e.getDay1rate(), NumberUtil.DEFAULT_SCALE));
				e.setDay3rate(NumberUtil.multi100(e.getDay3rate(), NumberUtil.DEFAULT_SCALE));
				e.setDay7rate(NumberUtil.multi100(e.getDay7rate(), NumberUtil.DEFAULT_SCALE));
				e.setDay30rate(NumberUtil.multi100(e.getDay30rate(), NumberUtil.DEFAULT_SCALE));
				e.setPv_uv_rate(NumberUtil.multi100(e.getPv_uv_rate(),NumberUtil.DEFAULT_SCALE));
				e.setCross_login_user_count_rate(NumberUtil.multi100(e.getCross_login_user_count_rate(),NumberUtil.DEFAULT_SCALE));
				e.setNew_actor_new_regist_rate(NumberUtil.multi100(e.getNew_actor_new_regist_rate(),NumberUtil.DEFAULT_SCALE));
				//
				e.setVip_avg_money(NumberUtil.ajustScale(e.getVip_avg_money(),NumberUtil.DEFAULT_SCALE));
				e.setVip_total_money_rate(NumberUtil.multi100(e.getVip_total_money_rate(),NumberUtil.DEFAULT_SCALE));
				e.setVip_total_avg_money(NumberUtil.ajustScale(e.getVip_total_avg_money(),NumberUtil.DEFAULT_SCALE));
				e.setVip_money_rate(NumberUtil.ajustScale(e.getVip_money_rate(),NumberUtil.DEFAULT_SCALE));
			}
		}
		return list;
	}
	
	/**
	 * 新增数据
	 * @Title: exportSDKDataSummary
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:01
	 */
	@RequestMapping(value="/exportSDKDataNewAddData", method={RequestMethod.POST})
	@ResponseBody
	public void exportSDKDataNewAddData(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		sysAccessLogService.log(request, getClass(), "exportSDKDataNewAddData", new Date(), searchData);
		try{
			List<SdkDataSummary> list = sDKDataService.selectSDKDataNewAddData(searchData);
			list = ajustScaleOrMul100(list);
			//
			List<List<Object[]>> excelDataList = new LinkedList();
			String filename = "新增数据.xlsx";
			//
			if(list != null){
				String[] title = new String[] {"日期","系统","新增激活","新增注册","注册转化率(%)","新增创角数","新增创角转化率(%)","新增充值人数","新增充值金额","新增付费率(%)"
						,"arppu","arpu","次日留存(%)","3日留存(%)","7日留存(%)","30日留存(%)"};
				List<Object[]> sheetDataList = new ArrayList();
				sheetDataList.add(title);
				for(SdkDataSummary e : list){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = DateUtil.format2YYYY_MM_DD(e.getStatdate());
					items[count++] = Value2NameParser.parseOS(e.getOs());
					items[count++] = e.getActivate();
					items[count++] = e.getGame_regist();
					items[count++] = e.getActive_regist_rate();
					items[count++] = e.getNew_actor();
					items[count++] = e.getRegist_actor_rate();
					items[count++] = e.getNew_rechg();
					items[count++] = e.getNew_money();
					items[count++] = e.getPayrate();
					items[count++] = e.getArrpu();
					items[count++] = e.getArpu();
					items[count++] = e.getDay1rate();
					items[count++] = e.getDay3rate();
					items[count++] = e.getDay7rate();
					items[count++] = e.getDay30rate();
					sheetDataList.add(items);
				}
				excelDataList.add(sheetDataList);
			}
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, excelDataList);
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 新增数据明细
	 * @Title: selectSDKDataNewAddDataDetail
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:44
	 */
	@RequestMapping(value="/selectSDKDataNewAddDataDetail", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKDataNewAddDataDetail(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKDataNewAddDataDetail", new Date(), searchData);
		List<SdkDataSummary> list = sDKDataService.selectSDKDataNewAddDataDetail(searchData);
		list = ajustScaleOrMul100(list);
		return list;
	}

	/**
	 * 登录分析
	 * @Title: selectSDKLoginAnalysis
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:50
	 */
	@RequestMapping(value="/selectSDKLoginAnalysis", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKLoginAnalysis(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKLoginAnalysis", new Date(), searchData);
		List<SdkDataSummary> list = sDKDataService.selectSDKLoginAnalysis(searchData);
		list = ajustScaleOrMul100(list);
		return list;
	}
	
	/**
	 * 登录分析
	 * @Title: exportSDKDataSummary
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:01
	 */
	@RequestMapping(value="/exportSDKLoginAnalysis", method={RequestMethod.POST})
	@ResponseBody
	public void exportSDKLoginAnalysis(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		sysAccessLogService.log(request, getClass(), "exportSDKLoginAnalysis", new Date(), searchData);
		try{
			List<SdkDataSummary> list = sDKDataService.selectSDKLoginAnalysis(searchData);
			list = ajustScaleOrMul100(list);
			//
			List<List<Object[]>> excelDataList = new LinkedList();
			String filename = "登录分析.xlsx";
			//
			if(list != null){
				String[] title = new String[] {"日期","账号登录方式","新增注册","新增充值人数","新增充值金额","总活跃用户","总充值人数","总充值金额","总流失用户","总回流用户"};
				List<Object[]> sheetDataList = new ArrayList();
				sheetDataList.add(title);
				for(SdkDataSummary e : list){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = DateUtil.format2YYYY_MM_DD(e.getStatdate());
					items[count++] = e.getWay();
					
					items[count++] = e.getRegsit();
					items[count++] = e.getNew_recharge();
					items[count++] = e.getNew_money();
					items[count++] = e.getTotal_login();
					items[count++] = e.getTotal_recharge();
					items[count++] = e.getTotal_money();
					items[count++] = e.getTotal_lost();
					items[count++] = e.getTotal_return();	
					sheetDataList.add(items);
				}
				excelDataList.add(sheetDataList);
			}
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, excelDataList);
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 浏览统计
	 * @Title: selectSDKBrowseSummary
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:55
	 */
	@RequestMapping(value="/selectSDKBrowseSummary", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKBrowseSummary(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKBrowseSummary", new Date(), searchData);
		List<SdkDataSummary> list=null;
		try {
			list = sDKDataService.selectSDKBrowseSummary(searchData);
			list = ajustScaleOrMul100(list);
			//修改总占比为详情占比的平均值
			if(list!=null){
				for(SdkDataSummary s: list){
					String startDate=DateUtil.format2YYYY_MM_DD(s.getStatdate());
					String endDate=DateUtil.format2YYYY_MM_DD(s.getStatdate());
					searchData.setStartdate(startDate);
					searchData.setEnddate(endDate);
					searchData.setCategoryId(s.getCategory_id());
					searchData.setPv(s.getPv());
					searchData.setUv(s.getUv());
					List<SdkDataSummary> details=null;
					details=selectSDKBrowseSummaryDetail(searchData,session,request);
					Double total=0.0;
					if(details!=null){
						for(SdkDataSummary detail:details){
							total+=detail.getPv_uv_rate();
						}
					}
					s.setPv_uv_rate(details.size()!=0?NumberUtil.ajustScale(total/details.size(), 2):0.0);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 浏览统计
	 * @Title: exportSDKDataSummary
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:01
	 */
	@RequestMapping(value="/exportSDKBrowseSummary", method={RequestMethod.POST})
	@ResponseBody
	public void exportSDKBrowseSummary(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		sysAccessLogService.log(request, getClass(), "exportSDKBrowseSummary", new Date(), searchData);
		try{
			List<SdkDataSummary> list = sDKDataService.selectSDKBrowseSummary(searchData);
			list = ajustScaleOrMul100(list);
			//
			List<List<Object[]>> excelDataList = new LinkedList();
			String filename = "浏览统计.xlsx";
			//
			if(list != null){
				String[] title = new String[] {"日期","界面ID","界面名称","浏览量","账号数","占比(%)"};
				List<Object[]> sheetDataList = new ArrayList();
				sheetDataList.add(title);
				for(SdkDataSummary e : list){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = DateUtil.format2YYYY_MM_DD(e.getStatdate());
					items[count++] = e.getPage_id();
					items[count++] = e.getPage_name();
					items[count++] = e.getPv();
					items[count++] = e.getUv();
					items[count++] = e.getPv_uv_rate();
					sheetDataList.add(items);
				}
				excelDataList.add(sheetDataList);
			}
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, excelDataList);
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 浏览统计明细
	 * @Title: selectSDKBrowseSummaryDetail
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:59
	 */
	@RequestMapping(value="/selectSDKBrowseSummaryDetail", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKBrowseSummaryDetail(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKBrowseSummaryDetail", new Date(), searchData);
		List<SdkDataSummary> list = sDKDataService.selectSDKBrowseSummaryDetail(searchData);
		list = ajustScaleOrMul100(list);
		Double d1=0.0;
		Double d2=0.0;
		if(list!=null){
			
			for(SdkDataSummary summary :list){
				if(searchData.getPv()==0){
					d1=0.0;
				}else{
					d1=summary.getPv().doubleValue()/searchData.getPv().doubleValue();
				}
				if(searchData.getUv()==0){
					d2=0.0;
				}else{
					d2=summary.getUv().doubleValue()/searchData.getUv().doubleValue();
				}
				//浏览量占比
				Double pvRate=NumberUtil.multi100(d1,2);
				//账号数占比
				Double uvRate=NumberUtil.multi100(d2,2);
				summary.setPv_rate(pvRate);
				summary.setUv_rate(uvRate);
			}
		}
		return list;
	}

	/**
	 * 下载统计
	 * @Title: 
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:56:02
	 */
	@RequestMapping(value="/selectSDKDownloadSummary", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKDownloadSummary(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKDownloadSummary", new Date(), searchData);
		List<SdkDataSummary> list = sDKDataService.selectSDKDownloadSummary(searchData);
		list = ajustScaleOrMul100(list);
		return list;
	}
	
	/**
	 * 下载统计
	 * @Title: 
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:01
	 */
	@RequestMapping(value="/exportSDKDownloadSummary", method={RequestMethod.POST})
	@ResponseBody
	public void exportSDKDownloadSummary(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		sysAccessLogService.log(request, getClass(), "exportSDKDownloadSummary", new Date(), searchData);
		try{
			List<SdkDataSummary> list = sDKDataService.selectSDKDownloadSummary(searchData);
			list = ajustScaleOrMul100(list);
			//
			List<List<Object[]>> excelDataList = new LinkedList();
			String filename = "下载统计.xlsx";
			//
			if(list != null){
				String[] title = new String[] {"日期","游戏","系统","下载次数","下载账号数","交叉登录数","交叉渗透率(%)","新增创角数","新增创角转化率(%)","新增充值金额"
						,"新增充值人数","新增付费率(%)","arpu","arrpu","次日留存(%)","3日留存(%)","7日留存(%)","30日留存(%)"};
				List<Object[]> sheetDataList = new ArrayList();
				sheetDataList.add(title);
				for(SdkDataSummary e : list){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = DateUtil.format2YYYY_MM_DD(e.getStatdate());
					
					items[count++] = e.getGameName() + "(" + e.getGameid() + ")";
					items[count++] = e.getOs();
					items[count++] = e.getDl_count();
					items[count++] = e.getDl_user_count();
					items[count++] = e.getCross_login();
					items[count++] = e.getCross_login_user_count_rate();
					items[count++] = e.getNew_actor();
					items[count++] = e.getNew_actor_new_regist_rate();
					items[count++] = e.getNew_money();
					items[count++] = e.getNew_recharge();
					items[count++] = e.getPayrate();
					items[count++] = e.getArpu();
					items[count++] = e.getArrpu();
					items[count++] = e.getDay1rate();
					items[count++] = e.getDay3rate();
					items[count++] = e.getDay7rate();
					items[count++] = e.getDay30rate();
					sheetDataList.add(items);
				}
				excelDataList.add(sheetDataList);
			}
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, excelDataList);
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载来源
	 * @Title: selectSDKDownloasdSourceSummary
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:56:04
	 */
	@RequestMapping(value="/selectSDKDownloasdSourceSummary", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKDownloasdSourceSummary(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKDownloasdSourceSummary", new Date(), searchData);
		List<SdkDataSummary> list = sDKDataService.selectSDKDownloasdSourceSummary(searchData);
		list = ajustScaleOrMul100(list);
		return list;
	}

	/**
	 * 下载来源
	 * @Title: 
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:01
	 */
	@RequestMapping(value="/exportSDKDownloasdSourceSummary", method={RequestMethod.POST})
	@ResponseBody
	public void exportSDKDownloasdSourceSummary(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		sysAccessLogService.log(request, getClass(), "exportSDKDownloasdSourceSummary", new Date(), searchData);
		try{
			List<SdkDataSummary> list = sDKDataService.selectSDKDownloasdSourceSummary(searchData);
			list = ajustScaleOrMul100(list);
			//
			List<List<Object[]>> excelDataList = new LinkedList();
			String filename = "下载来源.xlsx";
			//
			if(list != null){
				String[] title = new String[] {"日期","游戏","系统","下载次数","下载账号数"};
				List<Object[]> sheetDataList = new ArrayList();
				sheetDataList.add(title);
				for(SdkDataSummary e : list){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = DateUtil.format2YYYY_MM_DD(e.getStatdate());
					items[count++] = e.getGameName() + "(" + e.getGameid() + ")";
					items[count++] = Value2NameParser.parseOS(e.getOs());
					items[count++] = e.getDl_count();
					items[count++] = e.getDl_user_count();
					sheetDataList.add(items);
				}
				excelDataList.add(sheetDataList);
			}
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, excelDataList);
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载来源明细
	 * @Title: selectSDKDownloasdSourceDetail
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:56:08
	 */
	@RequestMapping(value="/selectSDKDownloasdSourceDetail", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKDownloasdSourceDetail(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKDownloasdSourceDetail", new Date(), searchData);
		List<SdkDataSummary> list = sDKDataService.selectSDKDownloasdSourceDetail(searchData);
		list = ajustScaleOrMul100(list);
		return list;
	}

	/**
	 * VIP新增
	 * @Title: selectSDKNewVip
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:56:11
	 */
	@RequestMapping(value="/selectSDKNewVip", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKNewVip(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKNewVip", new Date(), searchData);
		List<SdkDataSummary> list = sDKDataService.selectSDKNewVip(searchData);
		list = ajustScaleOrMul100(list);
		return list;
	}

	/**
	 * VIP新增
	 * @Title: 
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:01
	 */
	@RequestMapping(value="/exportSDKNewVip", method={RequestMethod.POST})
	@ResponseBody
	public void exportSDKNewVip(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		sysAccessLogService.log(request, getClass(), "exportSDKNewVip", new Date(), searchData);
		try{
			List<SdkDataSummary> list = sDKDataService.selectSDKNewVip(searchData);
			list = ajustScaleOrMul100(list);
			//
			List<List<Object[]>> excelDataList = new LinkedList();
			String filename = "VIP新增.xlsx";
			//
			if(list != null){
				String[] title = new String[] {"日期","系统","新增VIP数","新增VIP付费金额","平均付费金额","新增VIP次日留存(%)","新增VIP3日留存(%)","新增VIP7日留存(%)","新增VIP30日留存(%)"};
				List<Object[]> sheetDataList = new ArrayList();
				sheetDataList.add(title);
				for(SdkDataSummary e : list){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = DateUtil.format2YYYY_MM_DD(e.getStatdate());
					items[count++] = Value2NameParser.parseOS(e.getOs());
					items[count++] = e.getNew_vip();
					items[count++] = e.getNew_vip_money();
					items[count++] = e.getVip_avg_money();
					items[count++] = e.getDay1rate();
					items[count++] = e.getDay3rate();
					items[count++] = e.getDay7rate();
					items[count++] = e.getDay30rate();
					sheetDataList.add(items);
				}
				excelDataList.add(sheetDataList);
			}
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, excelDataList);
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * VIP新增明细
	 * @Title: selectSDKNewVipDetail
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:56:13
	 */
	@RequestMapping(value="/selectSDKNewVipDetail", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKNewVipDetail(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKNewVipDetail", new Date(), searchData);
		List<SdkDataSummary> list = sDKDataService.selectSDKNewVipDetail(searchData);
		list = ajustScaleOrMul100(list);
		return list;
	}

	
	/**
	 * VIP分布
	 * @Title: 
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:01
	 */
	@RequestMapping(value="/exportSDKVIPDistribute", method={RequestMethod.POST})
	@ResponseBody
	public void exportSDKVIPDistribute(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		sysAccessLogService.log(request, getClass(), "exportSDKVIPDistribute", new Date(), searchData);
		try{
			List<SdkDataVipDistInfo> list = sDKDataService.selectSDKVIPDistribute(searchData);
			//
			List<List<Object[]>> excelDataList = new LinkedList();
			String filename = "VIP分布.xlsx";
			//
			if(list != null){
				String[] title = new String[] {"日期","系统","VIP0","VIP1","VIP2","VIP3","VIP4","VIP5","VIP6","VIP7","VIP8","VIP9","VIP10","VIP11","VIP12"};
				List<Object[]> sheetDataList = new ArrayList();
				sheetDataList.add(title);
				for(SdkDataVipDistInfo e : list){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = DateUtil.format2YYYY_MM_DD(e.getStatdate());
					items[count++] = Value2NameParser.parseOS(e.getOs());
					
					items[count++] = e.getLvl0();
					items[count++] = e.getLvl1();
					items[count++] = e.getLvl2();
					items[count++] = e.getLvl3();
					items[count++] = e.getLvl4();
					items[count++] = e.getLvl5();
					items[count++] = e.getLvl6();
					items[count++] = e.getLvl7();
					items[count++] = e.getLvl8();
					items[count++] = e.getLvl9();
					items[count++] = e.getLvl10();
					items[count++] = e.getLvl11();
					items[count++] = e.getLvl12();
					
					sheetDataList.add(items);
				}
				excelDataList.add(sheetDataList);
			}
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, excelDataList);
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * VIP分布
	 * @Title: selectSDKVIPDistribute
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:56:19
	 */
	@RequestMapping(value="/selectSDKVIPDistribute", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataVipDistInfo> selectSDKVIPDistribute(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKVIPDistribute", new Date(), searchData);
		List<SdkDataVipDistInfo> list = sDKDataService.selectSDKVIPDistribute(searchData);
		return list;
	}
	

	/**
	 * VIP分布明细
	 * @Title: selectSDKVIPDistributeDetail
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:56:19
	 */
	@RequestMapping(value="/selectSDKVIPDistributeDetail", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataVipDistInfo> selectSDKVIPDistributeDetail(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKVIPDistributeDetail", new Date(), searchData);
		List<SdkDataVipDistInfo> list = sDKDataService.selectSDKVIPDistributeDetail(searchData);
		return list;
	}

	/**
	 * VIP统计
	 * @Title: selectSDKVIPSummary
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:56:22
	 */
	@RequestMapping(value="/selectSDKVIPSummary", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKVIPSummary(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKVIPSummary", new Date(), searchData);
		List<SdkDataSummary> list = sDKDataService.selectSDKVIPSummary(searchData);
		list = ajustScaleOrMul100(list);
		return list;
	}
	
	/**
	 * VIP统计
	 * @Title: 
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:55:01
	 */
	@RequestMapping(value="/exportSDKVIPSummary", method={RequestMethod.POST})
	@ResponseBody
	public void exportSDKVIPSummary(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		sysAccessLogService.log(request, getClass(), "exportSDKVIPSummary", new Date(), searchData);
		try{
			List<SdkDataSummary> list = sDKDataService.selectSDKVIPSummary(searchData);
			list = ajustScaleOrMul100(list);
			//
			List<List<Object[]>> excelDataList = new LinkedList();
			String filename = "VIP统计.xlsx";
			//
			if(list != null){
				String[] title = new String[] {"日期","系统","累积VIP总数","VIP累积付费总额","累积贡献率(%)","VIP累积充值人数","VIP累积平均付费","当日新增VIP数","VIP当日付费总额","VIP当日贡献率(%)"
						,"VIP当日充值人数","VIP日平均付费","流失VIP人数","回流VIP人数"};
				List<Object[]> sheetDataList = new ArrayList();
				sheetDataList.add(title);
				for(SdkDataSummary e : list){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = DateUtil.format2YYYY_MM_DD(e.getStatdate());
					items[count++] = Value2NameParser.parseOS(e.getOs());
					items[count++] = e.getTotal_vip();
					items[count++] = e.getTotal_vip_money();
					items[count++] = e.getVip_total_money_rate();
					items[count++] = e.getTotal_vip();
					items[count++] = e.getVip_total_avg_money();
					items[count++] = e.getNew_vip();
					items[count++] = e.getTotal_vip_money();
					items[count++] = e.getVip_money_rate();
					items[count++] = e.getVip_recharge();
					items[count++] = e.getVip_avg_money();
					items[count++] = e.getVip_lost();
					items[count++] = e.getVip_return();
					sheetDataList.add(items);
				}
				excelDataList.add(sheetDataList);
			}
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, excelDataList);
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * VIP统计明细
	 * @Title: selectSDKVIPSummaryDetail
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月7日 下午5:56:25
	 */
	@RequestMapping(value="/selectSDKVIPSummaryDetail", method={RequestMethod.POST})
	@ResponseBody
	public List<SdkDataSummary> selectSDKVIPSummaryDetail(@RequestBody SDKDataBaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectSDKVIPSummaryDetail", new Date(), searchData);
		List<SdkDataSummary> list = sDKDataService.selectSDKVIPSummaryDetail(searchData);
		list = ajustScaleOrMul100(list);
		return list;
	}
	
	/**
	 * Sdk 资讯
	 * @Title: selectSdkNewsList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<SdkNewsStat>
	 * @author moxw
	 * @date 2016年11月9日 上午11:34:54
	 */
	@RequestMapping(value="/selectSdkNewsList", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public BaseResult selectSdkNewsList(String data,HttpSession session,HttpServletRequest request) throws Exception {
		
		String dateFormat = "yyyy-MM-dd";
		BaseResult ret = new BaseResult();
		try{
			logger.info("data:{}",data);
			data = DESUtil.desedeDecoder(data, PropertiesUtil.getValue("SDK_NEWS_API_KEY"));
			logger.info("data:{}",data);
			sysAccessLogService.log(request, getClass(), "selectSdkNewsList", new Date(), data);
			//DESUtil.desedeDecoder(data, KEY);
			JSONObject d = null;
			d = (JSONObject) JSONObject.parse(data);
			String start = d.getString("start");
			String end = d.getString("end");
			String new_ids = d.getString("new_ids");
			Date startdate = DateUtil.parse(dateFormat , start);
			if(Validator.isNull(startdate)){
				ret.setStatus("1");
				ret.setMessage("start invalid");
				return ret;
			}
			Date enddate = DateUtil.parse(dateFormat , end);
			if(Validator.isNull(enddate)){
				ret.setStatus("1");
				ret.setMessage("end invalid");
				return ret;
			}
			if(Validator.isNullOrBlank(new_ids)){
				ret.setStatus("1");
				ret.setMessage("new_ids invalid");
				return ret;
			}
			String ids[] = new_ids.split(",");
			List<Integer> new_id_list = null;
			if(!Validator.isNull(ids)){
				new_id_list = new ArrayList();
				for(String id : ids){
					Integer i = Validator.toInteger(id);
					if(!Validator.isNull(i)){
						new_id_list.add(Integer.valueOf(id));
					}else{
						new_id_list = null;
						break;
					}
				}
			}
			if(Validator.isNull(new_id_list) || new_id_list.size() == 0){
				ret.setStatus("1");
				ret.setMessage("new_ids invalid");
				return ret;
			}
			SDKDataBaseSearchData searchData = new SDKDataBaseSearchData();
			searchData.setStartdate(DateUtil.format(startdate, dateFormat));
			searchData.setEnddate(DateUtil.format(enddate, dateFormat));
			searchData.setNews_ids(new_id_list);
			
			List<SdkNewsStat> list = sDKDataService.selectSdkNewsList(searchData);
			ret.setData(list);
			ret.setStatus("0");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("error:{}",e.getMessage());
		}
		return ret;
	}
	
	

	public SDKDataService getsDKDataService() {
		return sDKDataService;
	}

	public void setsDKDataService(SDKDataService sDKDataService) {
		this.sDKDataService = sDKDataService;
	}

	public ISysAccessLogService getSysAccessLogService() {
		return sysAccessLogService;
	}

	public void setSysAccessLogService(ISysAccessLogService sysAccessLogService) {
		this.sysAccessLogService = sysAccessLogService;
	}
	
	
}
