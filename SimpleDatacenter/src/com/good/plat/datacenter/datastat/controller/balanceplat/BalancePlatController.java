package com.good.plat.datacenter.datastat.controller.balanceplat;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.good.plat.datacenter.common.Exception.InputValidException;
import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.base.Value2NameParser;
import com.good.plat.datacenter.common.config.ConstantUtil;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.ExcelReader;
import com.good.plat.datacenter.common.util.FileHandleUtil;
import com.good.plat.datacenter.common.util.FinanceRptTemplate;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.datastat.entity.balanceplat.AppleBalance;
import com.good.plat.datacenter.datastat.entity.balanceplat.ChannelAccounting;
import com.good.plat.datacenter.datastat.entity.balanceplat.CheckBill;
import com.good.plat.datacenter.datastat.entity.balanceplat.CheckBillResult;
import com.good.plat.datacenter.datastat.entity.balanceplat.ContractFile;
import com.good.plat.datacenter.datastat.entity.balanceplat.DZPTChannelAccounting;
import com.good.plat.datacenter.datastat.entity.balanceplat.DZPTChannelCompanyInfo;
import com.good.plat.datacenter.datastat.entity.balanceplat.PayChannelAccounting;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscActorRecharge;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscAdvertIncome;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscFinacialStatementMonthly;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscFinacialStatementWeekly;
import com.good.plat.datacenter.datastat.entity.balanceplat.PayChannelAccounting.ChannelIncome;
import com.good.plat.datacenter.datastat.service.balanceplat.IBalancePlatService;
import com.good.plat.datacenter.datastat.service.balanceplat.UscVideoAdvertiserService;
import com.good.plat.datacenter.game.entity.Game;
import com.good.plat.datacenter.game.service.GameService;
import com.good.plat.datacenter.login.service.LoginService;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping(value = "/balanceplat")
public class BalancePlatController extends BaseController {
	@Autowired
	IBalancePlatService balancePlatService;
	// log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private GameService gameService;
	@Autowired
	private UscVideoAdvertiserService uscVideoAdvertiserService;
	
	@RequestMapping(value = "/addChannelAccounting", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public BaseMessage addChannelAccounting(@RequestBody DZPTChannelAccounting channelAccounting, HttpSession session,
			HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "addChannelAccounting", new Date(), channelAccounting);
		//
		System.out.println(channelAccounting);//客户端选了 2016-07-11  得到 2016-07-10T16:00:00.000Z
		return balancePlatService.addChannelAccounting(channelAccounting);
	}

	@RequestMapping(value = "/selectChannelAccounting", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public DZPTChannelAccounting selectChannelAccounting(@RequestBody DZPTChannelAccounting channelAccounting,
			HttpSession session, HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "selectChannelAccounting", new Date(), channelAccounting);
		//
		return balancePlatService.selectChannelAccounting(channelAccounting);
	}

	private BaseSearchData appendBaseSearchDataDateDay(BaseSearchData searchData) throws ParseException {
		// String yyyymmdd = "^(\\d{4})-(\\d{1,2})-(\\d{1,2})$";
		// Pattern pyyyymmdd = Pattern.compile(yyyymmdd);
		String yyyymm = "^(\\d{4})-(\\d{1,2})$";
		Pattern pyyyymm = Pattern.compile(yyyymm);
		Matcher matcher = null;
		matcher = pyyyymm.matcher(searchData.getStartdate());
		if (matcher.find()) {
			searchData.setStartdate(searchData.getStartdate() + "-01");
		}
		matcher = pyyyymm.matcher(searchData.getEnddate());
		if (matcher.find()) {
			searchData.setEnddate(searchData.getEnddate() + "-01");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date enddate = sdf.parse(searchData.getEnddate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(enddate);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		searchData.setEnddate(sdf.format(cal.getTime()) + " 23:59:59");
		return searchData;
	}

	@RequestMapping(value = "/updateChannelAccounting", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public BaseMessage updateChannelAccounting(@RequestBody DZPTChannelAccounting channelAccounting,
			HttpSession session, HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "updateChannelAccounting", new Date(), channelAccounting);
		//
		return balancePlatService.updateChannelAccounting(channelAccounting);
	}

	@RequestMapping(value = "/selectChannelAccountingList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<DZPTChannelAccounting> selectChannelAccountingList(@RequestBody BaseSearchData searchData,
			HttpSession session, HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "selectChannelAccountingList", new Date(), searchData);
		//
		List<DZPTChannelAccounting> list = null;
		try{
		searchData = appendBaseSearchDataDateDay(searchData);
		list = balancePlatService.selectChannelAccountingList(searchData);
		if (list != null) {
			for (DZPTChannelAccounting e : list) {
				if(e.getCoopmodel() != null){
					e.setCoopModelName(parsetCooperationModel(toInteger(e.getCoopmodel())));	
				}
				if(e.getGradient() != null){
					e.setGradientName(parsetGradient(toInteger(e.getGradient())));
				}
				if(e.getSettlementtype() != null){
					e.setSettlementtypeName(parsetSettlementType(toInteger(e.getSettlementtype())));
				}
				if(e.getIspayrate() != null){
					e.setIsFinalPayRate(parseFinalPayRate(e.getIspayrate().intValue()));
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	@RequestMapping(value = "/deleteChannelAccounting", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public BaseMessage deleteChannelAccounting(@RequestBody DZPTChannelAccounting channelAccounting,
			HttpSession session, HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "deleteChannelAccounting", new Date(), channelAccounting);
		//
		return balancePlatService.deleteChannelAccounting(channelAccounting);
	}

	private String parseFinalPayRate(Integer finalpayrate) {
		String txt = "未定义";
		try{
			int isFinalPayrate = finalpayrate.intValue();
			switch (isFinalPayrate) {
			case 0:
				txt = "否";
				break;
			case 1:
				txt = "是";
				break;
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return txt;
	}
	private String parsetSettlementType(Integer val) {

		String name = null;
		if (val == null)
			return name;
		switch (val) {
		case 0:
			name = "我方";
			break;
		case 1:
			name = "对方";
			break;
		case 2:
			name = "汉正结算";
			break;
		case -1:
			name = "不结算";
			break;
		default:
			name = "未定义";
			break;
		}
		return name;
	}

	private Integer toInteger(Object val) {
		try{
		if (val == null) {
			return null;
		} else if (val instanceof Byte) {
			return ((Byte) val).intValue();
		} else if (val instanceof Integer) {
			return (Integer) val;
		} else if (val instanceof String) {
			return Integer.valueOf((String) val);
		} else if(val instanceof BigDecimal){
			return ((BigDecimal)val).intValue();
		}
		}catch(Exception e){
			return null;
		}
		return (Integer) val;
	}

	private String parsetGradient(Integer val) {
		String name = null;
		if (val == null)
			return name;
		switch (val) {
		case 0:
			name = "否";
			break;
		case 1:
			name = "是";
			break;
		default:
			name = "未定义";
			break;
		}
		return name;
	}

	private String parsetCooperationModel(Integer val) {
		String name = null;
		if (val == null)
			return name;
		switch (val) {
		case 0:
			name = "CPS";
			break;
		case 1:
			name = "联运";
			break;
		default:
			name = "未定义";
			break;
		}
		return name;
	}

	@RequestMapping(value = "/selectAccountingList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ChannelAccounting> selectAccountingList(@RequestBody BaseSearchData searchData, HttpSession session,
			HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "selectAccountingList", new Date(), searchData);
		//
		List<ChannelAccounting> list = null;
		try{
		searchData = appendBaseSearchDataDateDay(searchData);
		list = balancePlatService.selectAccountingList(searchData);
		if (list != null) {
			for (ChannelAccounting e : list) {
				if(e.getCoopmodel() != null){
					e.setCoopModelName(parsetCooperationModel(toInteger(e.getCoopmodel())));	
				}
				if(e.getGradient() != null){
					e.setGradientName(parsetGradient(toInteger(e.getGradient())));
				}
				if(e.getSettlementtype() != null){
					e.setSettlementtypeName(parsetSettlementType(toInteger(e.getSettlementtype())));
				}
				if(e.getIspayrate() != null){
					e.setIsFinalPayRate(parseFinalPayRate(e.getIspayrate().intValue()));
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "/selectWeeklyAccountingList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ChannelAccounting> selectWeeklyAccountingList(@RequestBody BaseSearchData searchData, HttpSession session,
			HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "selectWeeklyAccountingList", new Date(), searchData);
		//
		List<ChannelAccounting> list = null;
		try{
			searchData = appendBaseSearchDataDateDay(searchData);
			
			list = balancePlatService.selectWeeklyAccountingList(searchData);
			if (list != null) {
				for (ChannelAccounting e : list) {//,b.coopmodel,b.finalpayrate,b.gradient,b.settlementtype,b.contractstartdate,b.constractenddate,b.ispayrate
					if(e.getCoopmodel() != null){
						e.setCoopModelName(parsetCooperationModel(toInteger(e.getCoopmodel())));	
					}
					if(e.getGradient() != null){
						e.setGradientName(parsetGradient(toInteger(e.getGradient())));
					}
					if(e.getSettlementtype() != null){
						e.setSettlementtypeName(parsetSettlementType(toInteger(e.getSettlementtype())));
					}
					if(e.getIspayrate() != null){
						e.setIsFinalPayRate(parseFinalPayRate(e.getIspayrate().intValue()));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 支付渠道
	 * @Title: selectPayChannelAccountingList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<PayChannelAccounting>
	 * @author hwj
	 * @date 2017-12-15 上午11:38:33
	 */
	@RequestMapping(value = "/selectPayChannelAccountingList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PayChannelAccounting> selectPayChannelAccountingList(@RequestBody BaseSearchData searchData,
			HttpSession session, HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "selectPayChannelAccountingList", new Date(), searchData);
		//
		List<PayChannelAccounting> rlist = new LinkedList();
		try{
			searchData = appendBaseSearchDataDateDay(searchData);
			rlist = balancePlatService.selectPayChannelAccountingList(searchData);
//			rlist=sumPayChannelAcouting(rlist, searchData);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rlist;
	}
	public List<PayChannelAccounting> sumPayChannelAcouting(List<PayChannelAccounting> rlist,BaseSearchData searchData){
		//汇总
		PayChannelAccounting totalPayAccounting=new PayChannelAccounting();
		//共有
		BigDecimal sumIncome=BigDecimal.ZERO;//总收入
		BigDecimal sumAlipayMoney=BigDecimal.ZERO;//支付宝
		BigDecimal sumCardPayMoney=BigDecimal.ZERO;//卡充值
		BigDecimal sumBankPayMoney=BigDecimal.ZERO;//银行汇款
		BigDecimal sumPpWalletPayMoney=BigDecimal.ZERO;//PP钱包
		BigDecimal sumGooglePayMoney=BigDecimal.ZERO;//googlepay
		BigDecimal sumAppstorePayMoney=BigDecimal.ZERO;//appstore
		BigDecimal sumFacebookPayMoney=BigDecimal.ZERO;//Facebook
		
		//新项目
		BigDecimal sumMo9PayMoney=BigDecimal.ZERO;//mo9支付
		BigDecimal sumUnionPayMoney=BigDecimal.ZERO;//银联TCL
		BigDecimal sumPay19Money=BigDecimal.ZERO;//19pay
		BigDecimal sumPpSdkPayMoney=BigDecimal.ZERO;//PP钱包SDK版
		BigDecimal sumWechatPayMoney=BigDecimal.ZERO;//微信支付
		BigDecimal sumMicrosoftPayMoney=BigDecimal.ZERO;//微软支付
		//旧项目
		BigDecimal sumMicroPayMoney=BigDecimal.ZERO;//小额支付
		BigDecimal sumOtherCarPayMoney=BigDecimal.ZERO;//其他卡
		BigDecimal sumMO9Money=BigDecimal.ZERO;////MO9
		BigDecimal sumNewAlipayMoney=BigDecimal.ZERO;//新支付宝充值
		//汇总数据
		if(rlist!=null&&!rlist.isEmpty()){
			for(int i=0;i<rlist.size();i++){
				PayChannelAccounting paychannel=rlist.get(i);
				sumIncome=sumIncome.add(paychannel.getMoney());//总收入
				//共有
				sumAlipayMoney=sumAlipayMoney.add(paychannel.getAlipayMoney());
				sumCardPayMoney=sumCardPayMoney.add(paychannel.getCardPayMoney());
				sumBankPayMoney=sumBankPayMoney.add(paychannel.getBankPayMoney());
				sumPpWalletPayMoney=sumPpWalletPayMoney.add(paychannel.getPpWalletPayMoney());
				sumGooglePayMoney=sumGooglePayMoney.add(paychannel.getGooglePayMoney());
				sumAppstorePayMoney=sumAppstorePayMoney.add(paychannel.getAppstorePayMoney());
				sumFacebookPayMoney=sumFacebookPayMoney.add(paychannel.getFacebookPayMoney());
				//新项目
				sumMo9PayMoney=sumMo9PayMoney.add(paychannel.getMo9PayMoney());
				sumUnionPayMoney=sumUnionPayMoney.add(paychannel.getUnionPayMoney());
				sumPay19Money=sumPay19Money.add(paychannel.getPay19Money());
				sumPpSdkPayMoney=sumPpSdkPayMoney.add(paychannel.getPpSdkPayMoney());
				sumWechatPayMoney=sumWechatPayMoney.add(paychannel.getWechatPayMoney());
				sumMicrosoftPayMoney=sumMicrosoftPayMoney.add(paychannel.getMicrosoftPayMoney());
				//旧项目
				sumMicroPayMoney=sumMicroPayMoney.add(paychannel.getMicroPayMoney());
				sumOtherCarPayMoney=sumOtherCarPayMoney.add(paychannel.getOtherCarPayMoney());
				sumMO9Money=sumMO9Money.add(paychannel.getMO9Money());
				sumNewAlipayMoney=sumNewAlipayMoney.add(paychannel.getNewAlipayMoney());
				
			}
		}
		totalPayAccounting.setMoney(sumIncome);
		List<ChannelAccounting> payChannelSumIncomeList=new ArrayList<ChannelAccounting>();
		List<String> payChannelFilterList=searchData.getFilterList();
		if(searchData.getChecktype1().equals("1")){
			//新项目
			if(payChannelFilterList!=null){
				//支付渠道为部分
				for(String payChanel:payChannelFilterList){
					ChannelAccounting income=null;
					if(payChanel.equals("支付宝")){
						income=new ChannelAccounting("支付宝",sumAlipayMoney);
					}else if(payChanel.equals("卡充值")){
						income=new ChannelAccounting("卡充值",sumCardPayMoney);
					}else if(payChanel.equals("银行汇款")){
						income=new ChannelAccounting("银行汇款",sumBankPayMoney);
					}else if(payChanel.equals("mo9支付")){
						income=new ChannelAccounting("mo9支付",sumMo9PayMoney);
					}else if(payChanel.equals("银联TCL")){
						income=new ChannelAccounting("银联TCL",sumUnionPayMoney);
					}else if(payChanel.equals("19pay")){
						income=new ChannelAccounting("19pay",sumPay19Money);
					}else if(payChanel.equals("PP钱包")){
						income=new ChannelAccounting("PP钱包",sumPpWalletPayMoney);
					}else if(payChanel.equals("PP钱包SDK版")){
						income=new ChannelAccounting("PP钱包SDK版",sumPpSdkPayMoney);
					}else if(payChanel.equals("微信支付")){
						income=new ChannelAccounting("微信支付",sumWechatPayMoney);
					}else if(payChanel.equals("微软支付")){
						income=new ChannelAccounting("微软支付",sumMicrosoftPayMoney);
					}else if(payChanel.equals("googlepay")){
						income=new ChannelAccounting("googlepay",sumGooglePayMoney);
					}else if(payChanel.equals("appstore")){
						income=new ChannelAccounting("appstore",sumAppstorePayMoney);
					}else if(payChanel.equals("Facebook")){
						income=new ChannelAccounting("Facebook",sumFacebookPayMoney);
					}
					payChannelSumIncomeList.add(income);
				}
			}else{
				//支付渠道为全部
				ChannelAccounting income1=new ChannelAccounting("支付宝", sumAlipayMoney);
				ChannelAccounting income2=new ChannelAccounting("卡充值", sumCardPayMoney);
				ChannelAccounting income3=new ChannelAccounting("银行汇款",sumBankPayMoney);
				ChannelAccounting income4=new ChannelAccounting("mo9支付", sumMo9PayMoney);
				ChannelAccounting income5=new ChannelAccounting("银联TCL", sumUnionPayMoney);
				ChannelAccounting income6=new ChannelAccounting("19pay", sumPay19Money);
				ChannelAccounting income7=new ChannelAccounting("PP钱包", sumPpWalletPayMoney);
				ChannelAccounting income8=new ChannelAccounting("PP钱包SDK版",sumPpSdkPayMoney);
				ChannelAccounting income9=new ChannelAccounting("微信支付", sumWechatPayMoney);
				ChannelAccounting income10=new ChannelAccounting("微软支付", sumMicrosoftPayMoney);
				ChannelAccounting income11=new ChannelAccounting("googlepay",sumGooglePayMoney);
				ChannelAccounting income12=new ChannelAccounting("appstore", sumAppstorePayMoney);
				ChannelAccounting income13=new ChannelAccounting("Facebook",sumFacebookPayMoney);
				payChannelSumIncomeList.add(income1);
				payChannelSumIncomeList.add(income2);
				payChannelSumIncomeList.add(income3);
				payChannelSumIncomeList.add(income4);
				payChannelSumIncomeList.add(income5);
				payChannelSumIncomeList.add(income6);
				payChannelSumIncomeList.add(income7);
				payChannelSumIncomeList.add(income8);
				payChannelSumIncomeList.add(income9);
				payChannelSumIncomeList.add(income10);
				payChannelSumIncomeList.add(income11);
				payChannelSumIncomeList.add(income12);
				payChannelSumIncomeList.add(income13);
			}
		}else{
			//旧项目
			if(payChannelFilterList!=null){
				for(String payChanel:payChannelFilterList){
					ChannelAccounting income=null;
					if(payChanel.equals("支付宝")){
						income=new ChannelAccounting("支付宝", sumAlipayMoney);
					}else if(payChanel.equals("卡充值")){
						income=new ChannelAccounting("卡充值",sumCardPayMoney);
					}else if(payChanel.equals("银行汇款")){
						income=new ChannelAccounting("银行汇款",sumBankPayMoney);
					}else if(payChanel.equals("小额支付")){
						income=new ChannelAccounting("小额支付",sumMicroPayMoney);
					}else if(payChanel.equals("其他卡")){
						income=new ChannelAccounting("其他卡",sumOtherCarPayMoney);
					}else if(payChanel.equals("MO9")){
						income=new ChannelAccounting("MO9",sumMO9Money);
					}else if(payChanel.equals("PP钱包")){
						income=new ChannelAccounting("PP钱包",sumPpWalletPayMoney);
					}else if(payChanel.equals("新支付宝充值")){
						income=new ChannelAccounting("新支付宝充值",sumNewAlipayMoney);
					}else if(payChanel.equals("googlepay")){
						income=new ChannelAccounting("googlepay",sumGooglePayMoney);
					}else if(payChanel.equals("appstore")){
						income=new ChannelAccounting("appstore",sumAppstorePayMoney);
					}else if(payChanel.equals("Facebook")){
						income=new ChannelAccounting("Facebook",sumFacebookPayMoney);
					}
					payChannelSumIncomeList.add(income);
				}
			}else{
				ChannelAccounting income1=new ChannelAccounting("支付宝",sumAlipayMoney);
				ChannelAccounting income2=new ChannelAccounting("卡充值",sumCardPayMoney);
				ChannelAccounting income3=new ChannelAccounting("银行汇款",sumBankPayMoney);
				ChannelAccounting income4=new ChannelAccounting("小额支付",sumMicroPayMoney);
				ChannelAccounting income5=new ChannelAccounting("其他卡",sumOtherCarPayMoney);
				ChannelAccounting income6=new ChannelAccounting("MO9", sumMO9Money);
				ChannelAccounting income7=new ChannelAccounting("PP钱包",sumPpWalletPayMoney);
				ChannelAccounting income8=new ChannelAccounting("新支付宝充值",sumNewAlipayMoney);
				ChannelAccounting income9=new ChannelAccounting("googlepay",sumGooglePayMoney);
				ChannelAccounting income10=new ChannelAccounting("appstore", sumAppstorePayMoney);
				ChannelAccounting income11=new ChannelAccounting("Facebook",sumFacebookPayMoney);
				payChannelSumIncomeList.add(income1);
				payChannelSumIncomeList.add(income2);
				payChannelSumIncomeList.add(income3);
				payChannelSumIncomeList.add(income4);
				payChannelSumIncomeList.add(income5);
				payChannelSumIncomeList.add(income6);
				payChannelSumIncomeList.add(income7);
				payChannelSumIncomeList.add(income8);
				payChannelSumIncomeList.add(income9);
				payChannelSumIncomeList.add(income10);
				payChannelSumIncomeList.add(income11);
			}
			
		}
		totalPayAccounting.setPayChannelIncomes(payChannelSumIncomeList);
		totalPayAccounting.setStatdate1("汇总");
		//添加汇总
		if(!rlist.isEmpty()){
			rlist.add(totalPayAccounting);
		}
		return rlist;
	}
	public static class ChannelIncomeEntity{
		private Integer channel;
		private BigDecimal money;
		
		public ChannelIncomeEntity(Integer channel, BigDecimal money) {
			super();
			this.channel = channel;
			this.money = money;
		}
		public Integer getChannel() {
			return channel;
		}
		public void setChannel(Integer channel) {
			this.channel = channel;
		}
		public BigDecimal getMoney() {
			return money;
		}
		public void setMoney(BigDecimal money) {
			this.money = money;
		}
		
	}
	private List<PayChannelAccounting> makePayChannelIncomeList(List<ChannelAccounting> list) {
		List<PayChannelAccounting> rlist = new LinkedList();
		if(list == null || list.size() == 0) return rlist;
		HashMap<Integer,BigDecimal> channelIncomeMap = new HashMap();
		for(ChannelAccounting e : list){
			//
			if(e.getMoney() == null){
				e.setMoney(new BigDecimal(0));
			}
			if(channelIncomeMap.containsKey(e.getChannel())){
				channelIncomeMap.put(e.getChannel(), channelIncomeMap.get(e.getChannel()).add(e.getMoney()));
			}else{
				channelIncomeMap.put(e.getChannel(), e.getMoney());
			}
		}
		final int selectChannelIncomeCount = 7;
		ArrayList<ChannelIncomeEntity> channelIncomeEntityList = new ArrayList<ChannelIncomeEntity>();
		Set<Entry<Integer, BigDecimal>> incomeSet = channelIncomeMap.entrySet();
		Iterator<Entry<Integer, BigDecimal>> it = incomeSet.iterator();
		while(it.hasNext()){
			Entry<Integer, BigDecimal> e = it.next();
			ChannelIncomeEntity ci = new ChannelIncomeEntity(e.getKey(), e.getValue());
			channelIncomeEntityList.add(ci);
		}
		Collections.sort(channelIncomeEntityList,new Comparator<ChannelIncomeEntity>() {
			public int compare(ChannelIncomeEntity o1, ChannelIncomeEntity o2) {
				return o2.getMoney().compareTo(o1.getMoney());
			}
		});
		//行转列
		//statdate,gameid,channel,currency,sum(money) as
		//money,channelName,gameName
		while(!list.isEmpty()){
			List<ChannelAccounting> gamedayAccountingList = new ArrayList<ChannelAccounting>();
			for (int i = 0; i >= 0 && i < list.size(); ++i) {
				ChannelAccounting e2 = list.get(i);
				ChannelAccounting e1 = gamedayAccountingList.size() == 0 ? null : gamedayAccountingList.get(0);
				if (e1 == null 
						|| (e1.getStatdate().equals(e2.getStatdate()) 
								&& e1.getGameid().equals(e2.getGameid())
								&& e1.getCurrency().equals(e2.getCurrency()))
						) {
					gamedayAccountingList.add(e2);
					list.remove(e2);
					i -= 1;
				}
			}
			
			if (!gamedayAccountingList.isEmpty()) {
				double gamedayTotalIncome = 0.0, otherChannelIncome = 0.0;
				PayChannelAccounting e = new PayChannelAccounting();
				for (int i = 0; i < gamedayAccountingList.size(); ++i) {
					ChannelAccounting e1 = gamedayAccountingList.get(i);
					if (e.getStatdate() == null) {
						e.setStatdate(e1.getStatdate());
					}
					if (e.getGameid() == null) {
						e.setGameid(e1.getGameid());
					}
					if (e.getGameName() == null) {
						e.setGameName(e1.getGameName());
					}
					if (e.getCurrency() == null) {
						e.setCurrency(e1.getCurrency());
					}
					e.setMoney(null2Zero(e.getMoney()).add(e1.getMoney()));
					boolean isOtherChannel = true;
					for(int j = 0; j < channelIncomeEntityList.size() && j < selectChannelIncomeCount; ++j){
						ChannelIncomeEntity ci  = channelIncomeEntityList.get(j);
						if(e1.getChannel().equals(ci.getChannel())){
							isOtherChannel = false;
						}
					}
					if(isOtherChannel){
						otherChannelIncome += e1.getMoney().doubleValue();
					}
				}
				List<ChannelIncome> channelIncomeList = new ArrayList<ChannelIncome>();
				
				for(int i = 0; i < channelIncomeEntityList.size() && i < selectChannelIncomeCount; ++i){
					ChannelIncomeEntity chanl = channelIncomeEntityList.get(i);
					ChannelAccounting accounting = null;
					for(int j = 0; j < gamedayAccountingList.size(); ++j){
						if(chanl.getChannel().equals(gamedayAccountingList.get(j).getChannel())){
							accounting = gamedayAccountingList.get(j);
							gamedayAccountingList.remove(j);
							break;
						}
					}
					if(accounting == null){
						channelIncomeList.add(new ChannelIncome());
					}else{
						ChannelIncome ci = new ChannelIncome();
						ci.setChannelid(accounting.getChannel());
						ci.setChannelName(accounting.getChannelName());
						ci.setMoney(accounting.getMoney().doubleValue());
						channelIncomeList.add(ci);
					}
				}
				while (channelIncomeList.size() < selectChannelIncomeCount) {
					channelIncomeList.add(new ChannelIncome());
				}
				//
				ChannelIncome allOtherChannelIncome = new ChannelIncome();
				allOtherChannelIncome.setChannelid(null);
				allOtherChannelIncome.setChannelName("其他");
				allOtherChannelIncome.setMoney(otherChannelIncome);
				channelIncomeList.add(allOtherChannelIncome);
				e.setChannelIncomes(channelIncomeList);
				//
				rlist.add(e);
			}
		}//list.isNotEmpty
		//第一行的 channel 不能为空
		if(rlist == null) return null;
		for(int i = 0; i < rlist.size(); ++i){
			List<ChannelIncome> clist = rlist.get(i).getChannelIncomes();
			for(int k = 0; k < clist.size(); ++k){
				ChannelIncome ci = clist.get(k);
				if(Validator.isNullOrBlank(ci.getChannelName())){
					for(int j = 0; j < rlist.size(); ++j){
						if(j == i) continue;
						List<ChannelIncome> cclist = rlist.get(j).getChannelIncomes();cclist.get(k).getChannelName();
						if(!Validator.isNullOrBlank(cclist.get(k).getChannelName())){
							ci.setChannelid(cclist.get(k).getChannelid());
							ci.setChannelName(cclist.get(k).getChannelName());
							break;
						}
					}
				}
			}
		}
		return rlist;
	}
	

	private BigDecimal null2Zero(BigDecimal val) {
		return val == null ? new BigDecimal(0) : val;
	}

	private double null2Zero(Double val) {
		return val == null ? 0.0 : val;
	}

	public static void main(String args[]) {
		String yyyymmdd = "^(\\d{4})-(\\d{1,2})-(\\d{1,2})$";
		String yyyymm = "^(\\d{4})-(\\d{1,2})$";
		String day = "2016-07-01";
		String day2 = "2016-07-01";
		Pattern p = Pattern.compile(yyyymmdd);
		Matcher matcher = p.matcher(day);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
		/*
		 * List list = new LinkedList(); list.add(1); list.add(3); list.add(2);
		 * Collections.sort(list,new Comparator<Integer>(){
		 * 
		 * @Override public int compare(Integer o1, Integer o2) { int r =
		 * o2.compareTo(o1); System.out.println(o2 +" ? " + o1 + "  " + r);
		 * return r; }
		 * 
		 * }); System.out.println(list.get(0) + " " + list.get(1));
		 */
	}

	@RequestMapping(value = "/updateBillingAccounting", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public BaseMessage updateBillingAccounting(@RequestBody UscFinacialStatementMonthly channelAccounting,
			HttpSession session, HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "updateBillingAccounting", new Date(), channelAccounting);
		//
		return balancePlatService.updateBillingAccounting(channelAccounting);
	}
	
	@RequestMapping(value = "/updateWeeklyBillingAccounting", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public BaseMessage updateWeeklyBillingAccounting(@RequestBody UscFinacialStatementWeekly channelAccounting,
			HttpSession session, HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "updateWeeklyBillingAccounting", new Date(), channelAccounting);
		//
		return balancePlatService.updateWeeklyBillingAccounting(channelAccounting);
	}
	
	public static boolean isNullOrEmpty(Object obj[]){
		return obj == null || obj.length == 0;
	}
	
	@RequestMapping(value = "/autoCheckBill", method = { RequestMethod.POST })
	@ResponseBody
//	public CheckBillResult checkBill(@RequestBody BaseSearchData searchData,
//			HttpSession session, HttpServletRequest request,@RequestParam("file") CommonsMultipartFile files[]) throws Exception {
	public CheckBillResult autoCheckBill(String startdate,String enddate,Integer channelid,Integer gameid,
			HttpSession session, HttpServletRequest request,@RequestParam("file") CommonsMultipartFile files[]) throws Exception {
		BaseSearchData searchData = new BaseSearchData();
		searchData.setGameid(gameid);
		searchData.setChannelid(channelid);
		searchData.setStartdate(startdate);
		searchData.setEnddate(enddate);
		searchData = appendBaseSearchDataDateDay(searchData);
		// log
		sysAccessLogService.log(request, getClass(), "checkBill", new Date(), startdate,enddate,channelid,gameid);
		try{
			//
			if(isNullOrEmpty(files)) return null;
			CommonsMultipartFile file = files[0];
			if(!file.isEmpty()){
				CheckBillResult result = new CheckBillResult();
				List<CheckBill> diffList = new LinkedList();
				int matchItemCount = 0;
				FileInputStream in = (FileInputStream) file.getInputStream();
				Workbook doc = ExcelReader.readWorkbook(in);
				List<String[]> dataList = ExcelReader.readDocument(doc, 0,true);
				//
				List<CheckBill> myBillList = selectSDKRechargeBillList(searchData,session,request);
				List<CheckBill> dsBillList = parseBill(dataList);
				myBillList = myBillList == null ? new LinkedList<CheckBill>() : myBillList;
				dsBillList = dsBillList == null ? new LinkedList<CheckBill>() : dsBillList;
				result.setMyBillItemCount(myBillList.size());
				result.setDsBillItemCount(dsBillList.size());
				//
				for(CheckBill my : myBillList){
					CheckBill ds = null;
					for(CheckBill t : dsBillList){
						if((my.getOrderid() != null && my.getOrderid().equals(t.getOrderid()))
								|| (my.getDstorderid() != null && my.getDstorderid().equals(t.getDstorderid()))){
							ds = t;
							dsBillList.remove(t);
							break;
						}
					}
					if(ds != null){
						if(my.getMoney().compareTo(ds.getMoney()) == 0){
							++matchItemCount;
						}else{
							my.setDiffDesc(CheckBillResult.CHECK_RESULT_MONEY_MISMATCH + "[" + ds.getMoney() + "]");
							diffList.add(my);
						}
					}else{
						my.setDiffDesc(CheckBillResult.CHECK_RESULT_3PART_MISSING);
						diffList.add(my);
					}
				}
				for(CheckBill ds : dsBillList){
					CheckBill my = null;
					for(CheckBill t : myBillList){
						if((ds.getOrderid() != null && ds.getOrderid().equals(t.getOrderid()))
								|| (ds.getDstorderid() != null && ds.getDstorderid().equals(t.getDstorderid()))){
							my = t;
							myBillList.remove(t);
							break;
						}
					}
					if(my != null){
						if(ds.getMoney().compareTo(my.getMoney()) == 0){
							++matchItemCount;
						}else{
							ds.setDiffDesc(CheckBillResult.CHECK_RESULT_MONEY_MISMATCH + "[" + my.getMoney() + "]");
							diffList.add(ds);
						}
					}else{
						ds.setDiffDesc(CheckBillResult.CHECK_RESULT_MISSING);
						diffList.add(ds);
					}
				}
				result.setMatchItemCount(matchItemCount);
				result.setDiffBillList(diffList);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return null;
	}
	
	public static Date parseDate(String date){
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d = null;
		if(d == null){
			try {
				d = sdf1.parse(date);
			} catch (ParseException e) {
			}
		}
		if(d == null){
			try {
				d = sdf2.parse(date);
			} catch (ParseException e) {
			}
		}
		if(d == null){
			try {
				d = sdf3.parse(date);
			} catch (ParseException e) {
			}
		}
		return d;
	}
	
	private List<CheckBill> parseBill(List<String[]> list){
		List<CheckBill> blist = null;
		if(list != null){
			blist = new LinkedList();
			for(String ss[] : list){
				CheckBill b = new CheckBill();
				b.setLogdate(parseDate(ss[0]));
				b.setOrderid(ss[1]);
				b.setDstorderid(ss[2]);
				b.setMoney(new BigDecimal(ss[3]));
				blist.add(b);
			}
		}
		return blist;
	}
	
	private List<CheckBill> selectSDKRechargeBillList(BaseSearchData searchData,
			HttpSession session, HttpServletRequest request) throws Exception{
		List<UscActorRecharge> list = null;
		List<CheckBill> rlist = null;
		list = balancePlatService.selectSDKRechargeBillList(searchData);
		if(list != null){
			rlist = new ArrayList(list.size());
			for(UscActorRecharge e : list){
				CheckBill b = new CheckBill(e);
				rlist.add(b);
			}
		}
		return rlist;
	}
	
	public static String buildRandomFileName(Date date,String filename){
		return date.getTime() + "-" + filename;
	}
	
	/**
	 * 
	 * @Title: selectAppleAutoBalance
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<AppleBalance>
	 * @author moxw
	 * @date 2016年8月25日 上午11:22:29
	 */
	@RequestMapping(value = "/selectAppleAutoBalance", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<AppleBalance> selectAppleAutoBalance(@RequestBody BaseSearchData searchData,
			HttpSession session, HttpServletRequest request) throws Exception{
		searchData = appendBaseSearchDataDateDay(searchData);
		sysAccessLogService.log(request, getClass(), "selectAppleAutoBalance", new Date(), searchData);
		List list = balancePlatService.selectAppleAutoBalance(searchData);
		return list;
	}
	
	
	/**
	 * 苹果对账导出
	 * @Title: exportAppleAutoBalance
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * List<AppleBalance>
	 * @author moxw
	 * @date 2016年8月25日 上午11:22:29
	 */
	@RequestMapping(value = "/exportAppleAutoBalance", method = { RequestMethod.POST})
	@ResponseBody
	public void exportAppleAutoBalance(@RequestBody BaseSearchData searchData,
			HttpSession session, HttpServletRequest request,HttpServletResponse response) throws Exception{
		searchData = appendBaseSearchDataDateDay(searchData);
		sysAccessLogService.log(request, getClass(), "exportAppleAutoBalance", new Date(), searchData);
		try{
			//
			List<List<Object[]>> list = new LinkedList();
			List<String> sheetNameList = new LinkedList();
			String filename = "苹果-谷得对账.xlsx";
			//
			List<AppleBalance> balancelist = selectAppleAutoBalance(searchData, session, request);
			//
			if(balancelist != null){
				String[] title = new String[] {"游戏","财年","财月","产品Id","苹果数量","谷得数量","差额","日期"};
				List<Object[]> datalist = new LinkedList();
				
				datalist.add(title);
				for(AppleBalance e : balancelist){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = e.getGameName();
					items[count++] = e.getFyear();
					items[count++] = e.getFperiod();
					items[count++] = e.getProductid();
					items[count++] = e.getAppleCount();
					items[count++] = e.getGoodCount();
					items[count++] = e.getDiffCount();
					if(!isNull(e.getStart()) && !isNull(e.getEnd())){
						items[count++] = DateUtil.format_yyyy_MM_dd(e.getStart()) + "~" + DateUtil.format_yyyy_MM_dd(e.getEnd());
					}else{
						count ++;
					}
					datalist.add(items);
				}
				list.add(datalist);
			}

			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			//ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			ExcelGenerator.fillWorkBook(w, list);
			
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
	 * 渠道自动对账导出
	 * @Title: exportAppleAutoBalance
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @param response
	 * @throws Exception
	 * void
	 * @author moxw
	 * @date 2016年11月4日 上午10:15:26
	 */
	@RequestMapping(value = "/exportChannelAutoBalance", method = { RequestMethod.POST})
	@ResponseBody
	public void exportChannelAutoBalance(@RequestBody BaseSearchData searchData,
			HttpSession session, HttpServletRequest request,HttpServletResponse response) throws Exception{
		searchData = appendBaseSearchDataDateDay(searchData);
		sysAccessLogService.log(request, getClass(), "exportChannelAutoBalance", new Date(), searchData);
		try{
			//
			List<List<Object[]>> list = new LinkedList();
			List<String> sheetNameList = new LinkedList();
			String filename = "渠道自动对账.xlsx";
			//
			List<DZPTChannelAccounting> rlist = selectChannelAccountingList(searchData, session, request);
			//
			if(rlist != null){
				String[] title = new String[] {"渠道名","项目名","游戏名","签约公司","合作模式","支付费率","实际支付费率","结算货币","是否阶梯","分成比例","结算类型","合同期","是否有效","合约状态","备注"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(DZPTChannelAccounting e : rlist){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = e.getChannelName();
					items[count++] = e.getProjectName();
					items[count++] = e.getGameName();
					items[count++] = e.getSignCompany();
					items[count++] = e.getCoopModelName();
					items[count++] = e.getPayrate();
					items[count++] = e.getIsFinalPayRate();
					items[count++] = Value2NameParser.parseCurrency(e.getCurrency());
					items[count++] = e.getGradientName();
					items[count++] = e.getSharingrate();
					items[count++] = e.getSettlementtypeName();
					if(!isNull(e.getContractstartdate()) && !isNull(e.getConstractenddate())){
						items[count++] = DateUtil.format_yyyy_MM_dd(e.getContractstartdate()) + "~" + DateUtil.format_yyyy_MM_dd(e.getConstractenddate());
					}else{
						count ++;
					}
					items[count++] = (e.getIsvalid() == 1 ? "有效" : "无效" );
					items[count++] = (e.getContract_status() == 1 ? "正常" : "终止合作");
					items[count++] =e.getMsg();
					datalist.add(items);
				}
				list.add(datalist);
			}

			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, list);
			
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
	 * 结算报表导出
	 * @Title: exportBillingAccounting
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @param response
	 * @throws Exception
	 * void
	 * @author moxw
	 * @date 2016年11月4日 上午10:17:49
	 */
	@RequestMapping(value = "/exportBillingAccounting", method = { RequestMethod.POST})
	@ResponseBody
	public void exportBillingAccounting(@RequestBody BaseSearchData searchData,
			HttpSession session, HttpServletRequest request,HttpServletResponse response) throws Exception{
		searchData = appendBaseSearchDataDateDay(searchData);
		sysAccessLogService.log(request, getClass(), "exportBillingAccounting", new Date(), searchData);
		try{
			//
			List<List<Object[]>> list = new LinkedList();
			List<String> sheetNameList = new LinkedList();
			String filename = "结算报表.xlsx";
			//
			List<ChannelAccounting> balancelist = selectAccountingList(searchData, session, request);
			//
			if(balancelist != null){
				String[] title = new String[] {"日期","渠道名","项目名","游戏名","签约公司","合作模式","收入流水","记录货币","人民币流水","支付费率","实际支付费率","税率","结算金额","是否阶梯","分成比例","分成金额","结汇货币"
						,"结汇汇率","结汇金额","结算类型","合同期"};
				List<Object[]> datalist = new LinkedList();
				
				datalist.add(title);
				for(ChannelAccounting e : balancelist){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = e.getStatdate();
					items[count++] = e.getChannelName();
					items[count++] = e.getProjectName();
					items[count++] = e.getGameName();
					items[count++] = e.getSignCompany();
					items[count++] = e.getCoopModelName();
					items[count++] = e.getMoney();
					items[count++] = Value2NameParser.parseCurrency(e.getCurrency());
					items[count++] = e.getRmb();
					items[count++] = e.getPayrate();
					items[count++] = e.getIsFinalPayRate();
					items[count++] = e.getTaxrate();
					items[count++] = e.getSettleMoney();
					items[count++] = e.getGradientName();
					items[count++] = e.getSharerate();
					items[count++] = e.getShareMoney();
					items[count++] = Value2NameParser.parseCurrency(e.getConfcurr());
					items[count++] = e.getExchrate();
					items[count++] = e.getForeignMoney();
					items[count++] = e.getSettlementtypeName();
					if(!isNull(e.getContractstartdate()) && !isNull(e.getConstractenddate())){
						items[count++] = DateUtil.format_yyyy_MM_dd(e.getContractstartdate()) + "~" + DateUtil.format_yyyy_MM_dd(e.getConstractenddate());
					}else{
						count ++;
					}
					datalist.add(items);
				}
				list.add(datalist);
			}

			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, list);
			
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
	 * 结算报表(周)导出
	 * @Title: exportBillingAccounting
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @param response
	 * @throws Exception
	 * void
	 * @author moxw
	 * @date 2016年11月4日 上午10:17:49
	 */
	@RequestMapping(value = "/exportBillingAccountingWeekly", method = { RequestMethod.POST})
	@ResponseBody
	public void exportBillingAccountingWeekly(@RequestBody BaseSearchData searchData,
			HttpSession session, HttpServletRequest request,HttpServletResponse response) throws Exception{
		searchData = appendBaseSearchDataDateDay(searchData);
		sysAccessLogService.log(request, getClass(), "exportBillingAccountingWeekly", new Date(), searchData);
		try{
			//
			List<List<Object[]>> list = new LinkedList();
			List<String> sheetNameList = new LinkedList();
			String filename = "结算报表(周).xlsx";
			//
			List<ChannelAccounting> balancelist = selectWeeklyAccountingList(searchData, session, request);
			//
			if(balancelist != null){
				String[] title = new String[] {"日期","渠道名","游戏名","合作模式","收入流水","记录货币","人民币流水","支付费率","实际支付费率","税率"
						,"结算金额","是否阶梯","分成比例","分成金额","结汇货币","结汇汇率","结汇金额","结算类型","合同期"};
				List<Object[]> datalist = new LinkedList();
				
				datalist.add(title);
				for(ChannelAccounting e : balancelist){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = e.getStatdate();
					items[count++] = e.getChannelName();
					items[count++] = e.getGameName();
					items[count++] = e.getCoopModelName();
					items[count++] = e.getMoney();
					items[count++] = Value2NameParser.parseCurrency(e.getCurrency());
					items[count++] = e.getRmb();
					items[count++] = e.getPayrate();
					items[count++] = e.getIsFinalPayRate();
					items[count++] = e.getTaxrate();
					items[count++] = e.getSettleMoney();
					items[count++] = e.getGradientName();
					items[count++] = e.getSharerate();
					items[count++] = e.getShareMoney();
					items[count++] = Value2NameParser.parseCurrency(e.getConfcurr());
					items[count++] = e.getExchrate();
					items[count++] = e.getForeignMoney();
					items[count++] = e.getSettlementtypeName();
					if(!isNull(e.getContractstartdate()) && !isNull(e.getConstractenddate())){
						items[count++] = DateUtil.format_yyyy_MM_dd(e.getContractstartdate()) + "~" + DateUtil.format_yyyy_MM_dd(e.getConstractenddate());
					}else{
						count ++;
					}
					datalist.add(items);
				}
				list.add(datalist);
			}

			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, list);
			
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
	 * 支付渠道导出
	 * @Title: exportPayChannelAccounting
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @param response
	 * @throws Exception
	 * void
	 * @author moxw
	 * @date 2016年11月4日 上午10:22:32
	 */
	@RequestMapping(value = "/exportPayChannelAccounting", method = { RequestMethod.POST})
	@ResponseBody
	public void exportPayChannelAccounting(@RequestBody BaseSearchData searchData,
			HttpSession session, HttpServletRequest request,HttpServletResponse response) throws Exception{
		searchData = appendBaseSearchDataDateDay(searchData);
		sysAccessLogService.log(request, getClass(), "exportPayChannelAccounting", new Date(), searchData);
		try{
			//
			List<List<Object[]>> list = new LinkedList();
			List<String> sheetNameList = new LinkedList();
			String filename = "支付渠道.xlsx";
//			//
//			List<PayChannelAccounting> balancelist = selectPayChannelAccountingList(searchData, session, request);
//			//
//			if(!isNull(balancelist)){
//				String[] title = null;//{"日期","游戏名","总收入"[,channel1,channel2}
//				List<String> titleList = new ArrayList(); 
//				titleList.addAll(Arrays.asList(new String[] {"日期","游戏名","总收入"}));
//				PayChannelAccounting tt=null;
//				if(!balancelist.isEmpty()){
//					tt = balancelist.get(0);
//					if(!isNull(tt.getPayChannelIncomes())){
//						for(ChannelAccounting ic : tt.getPayChannelIncomes()){
//							titleList.add(ic.getChannelName());
//						}
//					}	
//				}
//				title = titleList.toArray(new String[0]);
//				List<Object[]> datalist = new LinkedList();
//				datalist.add(title);
//				if(!isNull(tt)){
//					if(!isNull(tt.getPayChannelIncomes()) && tt.getPayChannelIncomes().size() != 0){
//						for(PayChannelAccounting e : balancelist){
//							Object[] items = new  Object[title.length];
//							int count = 0;
//							items[count++] = e.getStatdate1();
//							items[count++] = e.getGameName();
//							items[count++] = e.getMoney();
//							for(ChannelAccounting ic : e.getPayChannelIncomes()){
//								items[count++] = ic.getMoney();
//							}
//							datalist.add(items);
//						}
//					}
//				}
//				list.add(datalist);
//			}
			//广告商标题列表
			List<UscAdvertIncome> filterChannels=balancePlatService.selectUscAdvertChannelList(searchData);
			//视频收入
			List<UscAdvertIncome> incomes=balancePlatService.selectUscAdvertIncomeList(searchData);
			List<PayChannelAccounting> payChannelList =balancePlatService.selectPayChannelAccountingList(searchData);
			incomes=lineToColList(incomes);
			//title
			String[] title = null;//{"日期","游戏名","总收入"[,channel1,channel2}
			String[] title1=new String[]{"日期","游戏名","总收入"};
			//新项目标题
//			String[] newProjectTitle=ConstantUtil.newProjectPayChannelList;
			//旧项目标题
//			String[] oldProjectTitle=ConstantUtil.oldProjectPayChannelList;
			String[] payChannelTitle=null;
			List<String> titleList = new ArrayList<String>(); 
			titleList.addAll(Arrays.asList(title1));
			if(payChannelList!=null&&!payChannelList.isEmpty()){
				List<ChannelAccounting> payChannelIncomes=payChannelList.get(0).getPayChannelIncomes();
				if(!payChannelIncomes.isEmpty()){
					payChannelTitle=new String[payChannelIncomes.size()];
					for(int i=0;i<payChannelIncomes.size();i++){
						ChannelAccounting accouting=payChannelIncomes.get(i);
						payChannelTitle[i]=accouting.getChannelName();
					}
					titleList.addAll(Arrays.asList(payChannelTitle));
				}else{
					payChannelTitle=new String[0];
				}
			}
			
//			if(searchData.getChecktype1().equals("1")){
//				//新项目
//				titleList.addAll(Arrays.asList(newProjectTitle));
//			}else{
//				titleList.addAll(Arrays.asList(oldProjectTitle));
//			}
			
			if(filterChannels!=null){
				for(UscAdvertIncome e:filterChannels){
					//添加视频收入标题
					titleList.add(e.getName());
				}
			}
			title=titleList.toArray(new String[0]);
			//
			Map<String,PayChannelAccounting> result=new LinkedHashMap<String, PayChannelAccounting>();
			//支付渠道有数据，先对视频收入填充0
			if(payChannelList!=null&&!payChannelList.isEmpty()){
				for(PayChannelAccounting payChannel:payChannelList){
					String key=payChannel.getStatdate1()+"~"+payChannel.getGameName();
					if(filterChannels!=null){
						for(UscAdvertIncome e:filterChannels){
							//填充0
							ChannelAccounting accounting=new ChannelAccounting();
							accounting.setChannelName(e.getName());
							accounting.setMoney(new BigDecimal(0.00));
							payChannel.getVideoChannelIncomes().add(accounting);
						}
					}
					result.put(key, payChannel);
				}
			}
			//支付渠道有数据
			if(!result.isEmpty()){
				//视频收入有数据
				if(!incomes.isEmpty()){
					for(UscAdvertIncome income:incomes){
						String incomeKey=income.getDate()+"~"+income.getGameName();
						BigDecimal money=income.getMoney();
						List<ChannelAccounting> channels=new ArrayList<ChannelAccounting>();
						List<ChannelAccounting> videoChannels=income.getVideoChannelIncomes();
						//没有对应上渠道标题的收入填充0
						if(filterChannels!=null){
							for(UscAdvertIncome e:filterChannels){
								ChannelAccounting accounting=new ChannelAccounting();
								String name=e.getName();
								BigDecimal channelMoney=new BigDecimal(0.00);
								for(ChannelAccounting k:videoChannels){
									if(name.equals(k.getChannelName())){
										channelMoney=k.getMoney();
										break;
									}
								}
								accounting.setChannelName(name);
								accounting.setMoney(channelMoney);
								channels.add(accounting);
							}
						}
						//
						if(result.get(incomeKey)!=null){
						//匹配上
							result.get(incomeKey).setVideoChannelIncomes(channels);
							BigDecimal total=result.get(incomeKey).getMoney().add(money);//总收入
							result.get(incomeKey).setMoney(total);
						}else{
						//没匹配上
							PayChannelAccounting paychanel=new PayChannelAccounting();
							paychanel.setStatdate1(income.getDate());//日期
							paychanel.setGameName(income.getGameName());//游戏
							paychanel.setVideoChannelIncomes(channels);//设值视频渠道收入
							//查询没有对应渠道的收入填充0
							List<ChannelAccounting> payChanelIncomes=new ArrayList<ChannelAccounting>();
							for(int i=0;i<payChannelTitle.length;i++){
								ChannelAccounting channelIncome=new ChannelAccounting();
								channelIncome.setChannelName(payChannelTitle[i]);
								channelIncome.setMoney(new BigDecimal(0.00));
								payChanelIncomes.add(channelIncome);
							}
//							if(searchData.getChecktype1().equals("1")){
//								for(int i=0;i<newProjectTitle.length;i++){
//									ChannelAccounting channelIncome=new ChannelAccounting();
//									channelIncome.setChannelName(newProjectTitle[i]);
//									channelIncome.setMoney(new BigDecimal(0.00));
//									payChanelIncomes.add(channelIncome);
//								}
//							}else{
//								for(int i=0;i<oldProjectTitle.length;i++){
//									ChannelAccounting channelIncome=new ChannelAccounting();
//									channelIncome.setChannelName(oldProjectTitle[i]);
//									channelIncome.setMoney(new BigDecimal(0.00));
//									payChanelIncomes.add(channelIncome);
//								}
//							}
							//设值支付渠道的收入
							paychanel.setPayChannelIncomes(payChanelIncomes);
							paychanel.setMoney(money);
							result.put(incomeKey, paychanel);
						}
					}
				}
			}else{
			//支付渠道没数据,视频收入有数据	
				if(!incomes.isEmpty()){
					for(UscAdvertIncome income:incomes){
						String key=income.getDate()+"~"+income.getGameName();
						PayChannelAccounting paychanel=new PayChannelAccounting();
						paychanel.setStatdate1(income.getDate());//设值日期
						paychanel.setGameName(income.getGameName());//设值游戏
						List<ChannelAccounting> videoChannels=income.getVideoChannelIncomes();
						List<ChannelAccounting> channels=new ArrayList<ChannelAccounting>();
						//查询没有对应渠道的收入填充0
						if(filterChannels!=null){
							for(UscAdvertIncome e:filterChannels){
								ChannelAccounting accounting=new ChannelAccounting();
								String name=e.getName();
								BigDecimal channelMoney=new BigDecimal(0.00);
								for(ChannelAccounting k:videoChannels){
									if(name.equals(k.getChannelName())){
										channelMoney=k.getMoney();
										break;
									}
								}
								accounting.setChannelName(name);
								accounting.setMoney(channelMoney);
								channels.add(accounting);
							}
						}
						//
						paychanel.setVideoChannelIncomes(channels);//视频渠道收入
						paychanel.setMoney(income.getMoney());//总收入
						//支付渠道标题的收入填充0
						List<ChannelAccounting> payChanelIncomes=new ArrayList<ChannelAccounting>();
						for(int i=0;i<payChannelTitle.length;i++){
							ChannelAccounting channelIncome=new ChannelAccounting();
							channelIncome.setChannelName(payChannelTitle[i]);
							channelIncome.setMoney(new BigDecimal(0.00));
							payChanelIncomes.add(channelIncome);
						}
//						if(searchData.getChecktype1().equals("1")){
//							for(int i=0;i<newProjectTitle.length;i++){
//								ChannelAccounting channelIncome=new ChannelAccounting();
//								channelIncome.setChannelName(newProjectTitle[i]);
//								channelIncome.setMoney(new BigDecimal(0.00));
//								payChanelIncomes.add(channelIncome);
//							}
//						}else{
//							for(int i=0;i<oldProjectTitle.length;i++){
//								ChannelAccounting channelIncome=new ChannelAccounting();
//								channelIncome.setChannelName(oldProjectTitle[i]);
//								channelIncome.setMoney(new BigDecimal(0.00));
//								payChanelIncomes.add(channelIncome);
//							}
//						}
						//设值支付渠道的收入
						paychanel.setPayChannelIncomes(payChanelIncomes);
						result.put(key, paychanel);
					}
				}
				
			}
			List<PayChannelAccounting> exportDataList=new ArrayList<PayChannelAccounting>(result.values());
			System.out.println("合并后data"+JSONObject.toJSONString(exportDataList));
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);//标题
			if(exportDataList!=null&&!exportDataList.isEmpty()){
				for(PayChannelAccounting accounting:exportDataList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = accounting.getStatdate1();//日期
					items[count++] = accounting.getGameName();//游戏
					items[count++] = accounting.getMoney();//总收入
					//支付渠道收入
					for(ChannelAccounting pc:accounting.getPayChannelIncomes()){
						items[count++]=pc.getMoney();
					}
					//视频-渠道收入
					for(ChannelAccounting vc:accounting.getVideoChannelIncomes()){
						items[count++]=vc.getMoney();
					}
					datalist.add(items);
				}
			}
			list.add(datalist);
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, list);
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//视频收入-行转列
	private List<UscAdvertIncome> lineToColList(List<UscAdvertIncome> list){
		Map<String,List<UscAdvertIncome>> map=new LinkedHashMap<String, List<UscAdvertIncome>>();
		List<UscAdvertIncome> resultList=new ArrayList<UscAdvertIncome>();
		if(list!=null&&!list.isEmpty()){
			//分组
			for(UscAdvertIncome e:list){
				String groupKey=e.getDate()+"~"+e.getGameName();
				List<UscAdvertIncome> groupList=map.get(groupKey);
				if(groupList==null){
					groupList=new ArrayList<UscAdvertIncome>();
				}
				groupList.add(e);
				map.put(groupKey,groupList);
			}
			//
			Iterator<Entry<String, List<UscAdvertIncome>>> it=map.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, List<UscAdvertIncome>> entry=it.next();
				String key=entry.getKey();
				List<UscAdvertIncome> values=entry.getValue();
				UscAdvertIncome uscIncome=new UscAdvertIncome();
				BigDecimal total=new BigDecimal(0);
				if(values!=null){
					for(UscAdvertIncome income:values){
						total=total.add(income.getMoney());
						ChannelAccounting videoChannel=new ChannelAccounting();
						videoChannel.setChannelName(income.getName());
						videoChannel.setMoney(income.getMoney());
						//公司名-收入列表
						uscIncome.getVideoChannelIncomes().add(videoChannel);
					}
				}
				uscIncome.setMoney(total);//总收入
				uscIncome.setDate(key.split("~")[0]);//日期
				uscIncome.setGameName(key.split("~")[1]);//游戏名
				resultList.add(uscIncome);
			}
			}
		return resultList;
	}
	
	/**
	 * 自动对账导出
	 * 1.上传 excel 数据?
	 * @Title: exportAutoCheckBill
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @param response
	 * @throws Exception
	 * void
	 * @author moxw
	 * @date 2016年11月4日 上午10:24:04
	 */
	@RequestMapping(value = "/exportAutoCheckBill", method = { RequestMethod.POST})
	@ResponseBody
	public void exportAutoCheckBill(@RequestBody BaseSearchData searchData,
			HttpSession session, HttpServletRequest request,HttpServletResponse response) throws Exception{
		searchData = appendBaseSearchDataDateDay(searchData);
		sysAccessLogService.log(request, getClass(), "exportAutoCheckBill", new Date(), searchData);
		try{
			//
			List<List<Object[]>> list = new LinkedList();
			List<String> sheetNameList = new LinkedList();
			String filename = "自动对账.xlsx";
			//
			List<CheckBill> balancelist = selectSDKRechargeBillList(searchData, session, request);
			//
			List<String[]> dataList = null;
			//dataList = ExcelReader.readDocument(doc, 0,true);//第三方账单
			List<CheckBill> disanfangBillList = parseBill(dataList);
			//
			if(balancelist != null){
				String[] title = new String[] {"订单号","金额"};
				List<Object[]> datalist = new LinkedList();
				
				datalist.add(title);
				for(CheckBill e : balancelist){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = e.getOrderid();
					items[count++] = e.getMoney();
					
					datalist.add(items);
				}
				
				list.add(datalist);
			}

			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private boolean isNull(Object end) {
		return end == null;
	}
	
	/**
	 * 查询渠道公司信息
	 * @Title: selectChannelAccountInfoList
	 * @Description: TODO
	 * @param accountInfo
	 * @param request
	 * @return
	 * @throws Exception
	 * List<DZPTChannelAccountInfo>
	 * @author hwj
	 * @date 2017-5-4 上午10:46:48
	 */
	@RequestMapping(value = "/selectChannelCompanyInfoList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<DZPTChannelCompanyInfo> selectChannelCompanyInfoList(@RequestBody DZPTChannelCompanyInfo companyInfo,
			 HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "selectChannelCompanyInfoList", new Date(), companyInfo);
		//
		List<DZPTChannelCompanyInfo> list = null;
		try{
			list = balancePlatService.selectChannelCompanyInfoList(companyInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 添加渠道公司信息
	 * @Title: addChannelAccountInfo
	 * @Description: TODO
	 * @param accountInfo
	 * @param request
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-5-3 下午06:14:30
	 */
	@RequestMapping(value = "/addChannelCompanyInfo", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public BaseMessage addChannelCompanyInfo(@RequestBody DZPTChannelCompanyInfo companyInfo,
			 HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "addChannelCompanyInfo", new Date(), companyInfo);
		//
		BaseMessage message=null;
		try{
			message=balancePlatService.addChannelCompanyInfo(companyInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return message;
	}
	/**
	 * 修改公司信息
	 * @Title: updateChannelAccountInfo
	 * @Description: TODO
	 * @param accountInfo
	 * @param request
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-5-4 上午10:48:54
	 */
	@RequestMapping(value = "/updateChannelCompanyInfo", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public BaseMessage updateChannelCompanyInfo(@RequestBody DZPTChannelCompanyInfo companyInfo,
			 HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "updateChannelCompanyInfo", new Date(), companyInfo);
		//
		BaseMessage bm=null;
		try{
			bm=balancePlatService.updateChannelCompanyInfo(companyInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return bm;
	}
	
	/**
	 * 删除渠道公司信息
	 * @Title: deleteChannelAccountInfo
	 * @Description: TODO
	 * @param accountInfo
	 * @param request
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-5-4 上午10:51:21
	 */
	@RequestMapping(value = "/deleteChannelCompanyInfo", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public BaseMessage deleteChannelCompanyInfo(@RequestBody DZPTChannelCompanyInfo companyInfo,
			 HttpServletRequest request) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "deleteChannelCompanyInfo", new Date(), companyInfo);
		//
		BaseMessage bm=null;
		try{
			bm=balancePlatService.deleteChannelCompanyInfo(companyInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return bm;
	}
	
	/**
	 * 获取渠道公司
	 * @Title: selectChannelCompany
	 * @Description: TODO
	 * @param companyInfo
	 * @param request
	 * @return
	 * @throws Exception
	 * DZPTChannelCompanyInfo
	 * @author hwj
	 * @date 2017-5-4 下午02:31:55
	 */
	@RequestMapping(value = "/selectChannelCompany", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public DZPTChannelCompanyInfo selectChannelCompany(@RequestBody DZPTChannelCompanyInfo companyInfo,
			 HttpServletRequest request,HttpServletResponse response) throws Exception {
		// log
		sysAccessLogService.log(request, getClass(), "selectChannelCompany", new Date(), companyInfo);
		DZPTChannelCompanyInfo company=null;
		try{
			company = balancePlatService.selectChannelCompany(companyInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return company;
	}
	/**
	 * 导出渠道公司
	 * @Title: exportChannelCompanyList
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @param companyInfo
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-5-8 下午03:55:49
	 */
	@RequestMapping(value = "/exportChannelCompanyList", method = { RequestMethod.POST, RequestMethod.GET })
	public void exportChannelCompanyList(HttpServletRequest request,HttpServletResponse response,@RequestBody DZPTChannelCompanyInfo companyInfo)throws Exception{
		// log
		sysAccessLogService.log(request, getClass(), "exportChannelCompanyList", new Date(), companyInfo);
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "渠道公司.xlsx";
		//
		List<DZPTChannelCompanyInfo> companylist = balancePlatService.selectChannelCompanyInfoList(companyInfo);
		if(companylist!=null){
			List<Object[]> dataList=new LinkedList<Object[]>();
			String title[]=new String[]{"渠道名","纳税人识别号","注册地址","联系电话","开户行","银行账号","纳税资质"};
			dataList.add(title);
			for(DZPTChannelCompanyInfo info:companylist){
				Object[] items=new Object[title.length];
				int count=0;
				items[count++]=info.getChannelSimName();
				items[count++]=info.getTaxId();
				items[count++]=info.getAddress();
				items[count++]=info.getPhone();
				items[count++]=info.getBank();
				items[count++]=info.getBankAcount();
				items[count++]=info.getTaxType();
				dataList.add(items);
			}
			sheetNameList.add("渠道公司");
			list.add(dataList);
		}
		//
			Workbook w=ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, sheetNameList, list);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		
	}
	
	/**
	 * 导出游戏结算
	 * @Title: exportGameAccoutingList
	 * @Description: TODO
	 * @param searchData
	 * @param request
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-5-5 下午01:48:54
	 */
	@RequestMapping(value = "/exportGameAccoutingList", method = { RequestMethod.POST, RequestMethod.GET })
	public void exportGameAccoutingList(@RequestBody BaseSearchData searchData,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		// log
		sysAccessLogService.log(request, getClass(), "exportGameAccoutingList", new Date(), searchData);
		//获取当前月份的第一天
		String firstOfMonth=searchData.getStartdate()+"-01";
		searchData.setStartdate(firstOfMonth);
		//
		String path=request.getSession().getServletContext().getRealPath("/WEB-INF/classes/file/");
		//渠道下游戏结算列表
		List<Map<String, String>> channelGameAccountingList= balancePlatService.selectChannelGameAccountingList(searchData);
		FinanceRptTemplate template=new FinanceRptTemplate(firstOfMonth);
		template.setUrl(path);
		template.setBalancePlatService1(balancePlatService);
		OutputStream os=response.getOutputStream();
		//写入WorkBook
		template.out(os, channelGameAccountingList);
		
	}
	/**
	 * 上传合同文件
	 * @Title: uploadContractFile
	 * @Description: TODO
	 * @param searchData
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-5-10 下午03:25:34
	 */
	@RequestMapping(value="/uploadContractFile",method={RequestMethod.POST})
	@ResponseBody
	public BaseMessage uploadContractFile(HttpServletRequest request,HttpServletResponse response,@RequestParam("file") MultipartFile file)throws Exception{
		BaseMessage msg=new BaseMessage();
		ContractFile contractFile=new ContractFile();
		String channelId=request.getParameter("channelId");
		String description=request.getParameter("description");
		//保存目录
		String fileDir=FileHandleUtil.getContractFilePath();
		//文件名
		String fileName=file.getOriginalFilename();
		//保存到数据库的路径
		String savePath=fileDir+"/"+fileName;
		contractFile.setChannelId(Integer.valueOf(channelId));
		contractFile.setFilePath(savePath);
		contractFile.setDescription(description);
		System.out.println("上传参数："+JSONObject.toJSONString(contractFile));
		// log
		sysAccessLogService.log(request, getClass(), "uploadContractFile", new Date(), contractFile);
		try {
			
			if(file.isEmpty()||(fileName==null||fileName=="")){
				throw new InputValidException("上传失败，文件不能为空");
			}
			if(channelId==null||channelId==""){
				throw new InputValidException("渠道为空");
			}
			
			//查询数据库是否存在文件
			ContractFile contraFile=balancePlatService.selectByFilePath(contractFile);
			if(contraFile!=null){
				throw new InputValidException("文件名已存在,请删除旧文件重新上传或者修改文件名！");
			}else{
				//文件保存
				FileHandleUtil.saveFile(file, fileDir,fileName);
				//上传日期
				Date uploadDate=new Date();
				contractFile.setUploadDate(uploadDate);
				BaseMessage message= balancePlatService.addContractFile(contractFile);
				if(message.getStatus()==1){
					msg.setStatus(1);
				    msg.setMessage("文件上传成功");
				}else{
					msg.setStatus(0);
				    msg.setMessage("文件上传败");
				}
			}
		}catch (InputValidException e) {
			// TODO: handle exception
			msg.setStatus(0);
			msg.setMessage(e.getMessage());
			return msg;
		}catch (IOException e) {
			// TODO: handle exception
			msg.setStatus(0);
			msg.setMessage(e.getMessage());
			return msg;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setStatus(0);
			msg.setMessage("上传失败");
			return msg;
		}
	    return msg;
	}
	/**
	 * 合同文件列表
	 * @Title: selectContractFileList
	 * @Description: TODO
	 * @param request
	 * @param contractFile
	 * @return
	 * @throws Exception
	 * List<ContractFile>
	 * @author hwj
	 * @date 2017-5-11 下午05:12:59
	 */
	@RequestMapping(value="/selectContractFileList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<ContractFile> selectContractFileList(HttpServletRequest request,@RequestBody ContractFile contractFile)throws Exception{
		// log
		sysAccessLogService.log(request, getClass(), "selectContractFileList", new Date(), contractFile);
		 List<ContractFile> list=balancePlatService.selectContractFileList(contractFile);
		return list;
	}
	/**
	 * 下载合同文件
	 * @Title: downContractFile
	 * @Description: TODO
	 * @param request
	 * @param fileName
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-5-11 下午05:31:17
	 */
	@RequestMapping(value="/downContractFile",method={RequestMethod.POST,RequestMethod.GET})
	public void downContractFile(HttpServletRequest request,@RequestBody ContractFile contractFile,HttpServletResponse response)throws IOException{
		String fileName=contractFile.getFileName();
		String dir=FileHandleUtil.getContractFilePath();
		String filePath=dir+"/"+fileName;
		ByteArrayOutputStream baos=null;
		FileInputStream fis=null;
		BufferedInputStream bis=null;
		try {
			byte[] buffer=new byte[1024];
			File file=new File(filePath);
			baos=new ByteArrayOutputStream();
			fis=new FileInputStream(file);
			bis=new BufferedInputStream(fis);
			baos=new ByteArrayOutputStream();
			int len=0;
			while((len=bis.read(buffer))!=-1){
				baos.write(buffer, 0, len);
			}
			bis.close();
			baos.close();
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
//			String contentType=MediaType.MULTIPART_FORM_DATA_VALUE;
			HTTPUtil.responseFile(response, request, contentType, fileName, baos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 删除合同文件
	 * @Title: delContractFile
	 * @Description: TODO
	 * @param request
	 * @param contractFile
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-5-12 下午02:30:21
	 */
	@RequestMapping(value="/delContractFile",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public BaseMessage delContractFile(HttpServletRequest request,@RequestBody ContractFile contractFile)throws Exception{
		BaseMessage msg=new BaseMessage();
		String fileName=contractFile.getFileName();
		String dir=FileHandleUtil.getContractFilePath();
		String filePath=dir+"/"+fileName;
		Integer id=contractFile.getId();
		boolean result= FileHandleUtil.deleteFile(filePath);
		if(result==true){
			//文件删除成功
			msg=balancePlatService.deleteContractFile(id);
		}else{
			msg.setStatus(0);
			msg.setMessage("文件删除失败,该资源不存在！");
		}
		return msg;
	}
	/**
	 * 视频收入
	 * @Title: selectUscAdvertIncomeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * List<UscAdvertIncome>
	 * @author hwj
	 * @date 2018-2-3 上午10:16:22
	 */
	@RequestMapping(value="/selectUscAdvertIncomeList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<UscAdvertIncome> selectUscAdvertIncomeList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request){
		// log
		sysAccessLogService.log(request, getClass(), "selectUscAdvertIncomeList", new Date(), searchData);
		List<UscAdvertIncome> list=new ArrayList<UscAdvertIncome>();
		try {
			searchData = appendBaseSearchDataDateDay(searchData);
			list=balancePlatService.selectUscAdvertIncomeList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 导出视频收入
	 * @Title: exportUscAdvertIncomeList
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @param searchData
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-2-3 上午11:15:22
	 */
	@RequestMapping(value = "/exportUscAdvertIncomeList", method = { RequestMethod.POST, RequestMethod.GET })
	public void exportUscAdvertIncomeList(HttpServletRequest request,HttpServletResponse response,@RequestBody BaseSearchData searchData){
		// log
		sysAccessLogService.log(request, getClass(), "exportUscAdvertIncomeList", new Date(), searchData);
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "视频收入.xlsx";
		//
		try {
			searchData = appendBaseSearchDataDateDay(searchData);
			List<UscAdvertIncome> incomelist=balancePlatService.selectUscAdvertIncomeList(searchData);
			if(incomelist!=null){
				List<Object[]> dataList=new LinkedList<Object[]>();
				String title[]=new String[]{"日期","游戏","视频广告商","收入"};
				dataList.add(title);
				for(UscAdvertIncome income:incomelist){
					Object[] items=new Object[title.length];
					int count=0;
					items[count++]=DateUtil.format_yyyy_MM_dd(income.getStatdate()) ;
					items[count++]=income.getGameName();
					items[count++]=income.getName();
					items[count++]=income.getMoney();
					dataList.add(items);
				}
				sheetNameList.add("视频收入");
				list.add(dataList);
			}
			//
				Workbook w=ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
				ExcelGenerator.fillWorkBook(w, sheetNameList, list);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				w.write(baos);
				String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
				HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	/**
	 * 导出视频录入模板
	 * @Title: exportVideoEntryTemplate
	 * @Description: TODO
	 * @param session
	 * @param request
	 * @param response
	 * @param searchData
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-2-3 上午11:52:57
	 */
	@RequestMapping(value = "/exportVideoEntryTemplate", method = { RequestMethod.POST, RequestMethod.GET })
	public void exportVideoEntryTemplate(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody BaseSearchData searchData){
		// log
		sysAccessLogService.log(request, getClass(), "exportVideoEntryTemplate", new Date(), searchData);
		XSSFWorkbook wb=new XSSFWorkbook();
		try {
			List<Game> glist = loginService.getUserGameList(session, response);
			//所有广告商名称列表(searchData.checktype2=-1)
			List<String> aList=uscVideoAdvertiserService.selectAdvertiserNameList(searchData);
			
			List<String> gameNames=new ArrayList<String>();
			//组装游戏名列表
			if(glist!=null&&!glist.isEmpty()){
				for(Game game:glist){
					gameNames.add(game.getName());
				}
			}
			String[] names=gameNames.toArray(new String[0]);
			String[] advertiserNames=aList.toArray(new String[0]);
			wb=fillWorkBook(wb,names,advertiserNames);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , "视频录入", baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//填充录入模板
	private XSSFWorkbook fillWorkBook(XSSFWorkbook wb,String[] list,String[] list1){
		XSSFSheet sheet= wb.createSheet("视频录入");
		sheet.setColumnWidth(0, 22 * 256);
		sheet.setColumnWidth(1, 20 * 256);
		sheet.setColumnWidth(2, 20 * 256);
		XSSFRow row=sheet.createRow(0);//第1行
		XSSFCell cell1=row.createCell(0);//第1格
		cell1.setCellValue("月份（格式：2017-01）");
		XSSFCell cell2=row.createCell(1);//第2格
		cell2.setCellValue("游戏");
		XSSFCell cell3=row.createCell(2);//第3格
		cell3.setCellValue("视频广告商");
		XSSFCell cell4=row.createCell(3);//第4格
		cell4.setCellValue("收入");
		//下拉框作用区域（第2列所有行）
		sheet=ExcelReader.setXSSFValidation(wb,sheet, list,"hiddenSheet1",1,1,1000, 1, 1);//2到500行第2列下拉框
		sheet=ExcelReader.setXSSFValidation(wb,sheet, list1,"hiddenSheet2",2,1,1000, 2, 2);//2到500行第2列下拉框
		//
		CreationHelper createHelper= wb.getCreationHelper();
		//日期格式
		CellStyle cellStyle1 = wb.createCellStyle();
		cellStyle1.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm"));
		//数字格式
		CellStyle cellStyle2 = wb.createCellStyle();
		cellStyle2.setDataFormat(createHelper.createDataFormat().getFormat("0.00"));
		sheet.setDefaultColumnStyle(0, cellStyle1);
		sheet.setDefaultColumnStyle(3, cellStyle2);
		return wb;
	}  
	/**
	 * 上传视频收入
	 * @Title: uploadVideoIncomeFile
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 * BaseMessage
	 * @author hwj
	 * @date 2018-2-5 上午11:53:49
	 */
	@RequestMapping(value="/uploadVideoIncomeFile",method={RequestMethod.POST})
	@ResponseBody
	public BaseMessage uploadVideoIncomeFile(HttpServletRequest request,HttpServletResponse response,@RequestParam("file") MultipartFile file){
		sysAccessLogService.log(request, getClass(), "uploadVideoIncomeFile", new Date(), null);
		BaseMessage msg=new BaseMessage();
		InputStream is=null;
		Workbook wb=null;
		List<String[]> rowDatas=null;
		List<UscAdvertIncome> videoIncomeList=new ArrayList<UscAdvertIncome>();
		String[] fileType=new String[]{"xls","xlsx"};
		try{
			if(file==null){
				msg.setStatus(0);
				msg.setMessage("没有文件,请选择");
				return msg;
			}else{
				String fileName=file.getOriginalFilename();//fileName
				String extraName=fileName.substring(fileName.lastIndexOf(".")+1);//extraName
				List<String> fileTypeList=Arrays.asList(fileType);
				//不支持文件格式
				if(!fileTypeList.contains(extraName)){
					msg.setStatus(0);
					msg.setMessage("文件格式有误,请上传excel文件");
					return msg;
				}
					is=file.getInputStream();
					wb=ExcelReader.readWorkbook(is);
					rowDatas=ExcelReader.readSheet(wb, 0, 1,(short)0, (short)4,true);
					//参数校验
					if(rowDatas!=null&&!rowDatas.isEmpty()){
						for(String[] row:rowDatas){
							//sheet的c0~c3位null
							if(Validator.isNullOrBlank(row[0])
									||Validator.isNullOrBlank(row[1])
									||Validator.isNullOrBlank(row[2])
									||Validator.isNullOrBlank(row[3])){
								msg.setStatus(0);
								msg.setMessage("参数异常,请检查");
								return msg;
							}
						}
					}else{
						System.out.println("sheet is none data");
						msg.setStatus(0);
						msg.setMessage("请录入数据再上传");
						return msg;
					}
					//校验参数
					checkData(rowDatas);
					//组装数据
					for (String[] row:rowDatas) {
						Game game=new Game();
						game.setName(row[1]);//设置游戏名
						Game gameInfo=gameService.getGame(game);//根据游戏名获取游戏ID
						if(gameInfo==null){
							msg.setMessage("游戏不存在,请检查游戏名称");
							msg.setStatus(0);
							return msg;
						}
						UscAdvertIncome videoIncome=new UscAdvertIncome();
						videoIncome.setStatdate(DateUtil.parse_yyyy_MM_dd(row[0]));//日期
						videoIncome.setGameName(row[1]);//游戏名
						videoIncome.setGameid(gameInfo.getId());//游戏ID
						videoIncome.setName(row[2]);//广告商
						videoIncome.setMoney(new BigDecimal(row[3]));//收入
						videoIncomeList.add(videoIncome);
					}
			}
		 }catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				msg.setStatus(0);
				msg.setMessage("非法格式");
				return msg;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				msg.setStatus(0);
				msg.setMessage("读取失败");
				return msg;
			}catch (IllegalArgumentException e) {
				// TODO: handle exception
				e.printStackTrace();
				msg.setStatus(0);
				msg.setMessage("数据值格式有误，请检查!");
				return msg;
			}
		try {
			msg=balancePlatService.batchAddVideoIncomeData(videoIncomeList);
		} catch (RuntimeException e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setMessage("数据录入失败!");
			msg.setStatus(0);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setMessage("服务端出错!");
			msg.setStatus(0);
		}
		return msg;
	}
	//校验参数
	private void checkData(List<String[]> rowsData){
		for(String[] row:rowsData){
			if(!Validator.isValidDate(row[0],"yyyy-MM")){
				throw new IllegalArgumentException("日期格式异常");
			}
			if(!Validator.isNumber(row[3])){
				throw new IllegalArgumentException("非数值");
			}
		}
	}
	/**
	 * 视频收入-渠道列表
	 * @Title: selectUscAdvertChannelList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * List<UscAdvertIncome>
	 * @author hwj
	 * @date 2018-2-24 上午11:31:15
	 */
	@RequestMapping(value="/selectUscAdvertChannelList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<UscAdvertIncome> selectUscAdvertChannelList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request){	
		// log
		sysAccessLogService.log(request, getClass(), "selectUscAdvertChannelList", new Date(), searchData);
		List<UscAdvertIncome> list=new ArrayList<UscAdvertIncome>();
		try {
			searchData = appendBaseSearchDataDateDay(searchData);
			list=balancePlatService.selectUscAdvertChannelList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
