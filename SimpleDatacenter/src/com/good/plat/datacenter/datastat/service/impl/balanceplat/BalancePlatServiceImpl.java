package com.good.plat.datacenter.datastat.service.impl.balanceplat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.config.ConstantUtil;
import com.good.plat.datacenter.datastat.entity.balanceplat.AppleBalance;
import com.good.plat.datacenter.datastat.entity.balanceplat.ChannelAccounting;
import com.good.plat.datacenter.datastat.entity.balanceplat.ContractFile;
import com.good.plat.datacenter.datastat.entity.balanceplat.DZPTChannelAccounting;
import com.good.plat.datacenter.datastat.entity.balanceplat.DZPTChannelCompanyInfo;
import com.good.plat.datacenter.datastat.entity.balanceplat.PayChannelAccounting;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscActorRecharge;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscAdvertIncome;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscFinacialStatementMonthly;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscFinacialStatementWeekly;
import com.good.plat.datacenter.datastat.mapper.balanceplat.ContractFileMapper;
import com.good.plat.datacenter.datastat.mapper.balanceplat.DZPTChannelCompanyInfoMapper;
import com.good.plat.datacenter.datastat.mapper.balanceplat.DZPTChannelAccountingMapper;
import com.good.plat.datacenter.datastat.mapper.balanceplat.DZPTChannelAccountingMapper2;
import com.good.plat.datacenter.datastat.mapper.balanceplat.UscActorRechargeMapper2;
import com.good.plat.datacenter.datastat.mapper.balanceplat.UscAdvertIncomeMapper;
import com.good.plat.datacenter.datastat.mapper.balanceplat.UscFinacialStatementMonthlyMapper;
import com.good.plat.datacenter.datastat.mapper.balanceplat.UscFinacialStatementWeeklyMapper;
import com.good.plat.datacenter.datastat.mapper.balanceplat.UscFinancialAppleReportMapper2;
import com.good.plat.datacenter.datastat.service.balanceplat.IBalancePlatService;

@Service("balancePlatService")
public class BalancePlatServiceImpl implements IBalancePlatService {
	@Autowired
	DZPTChannelAccountingMapper channelAccountingMapper;
	@Autowired
	DZPTChannelAccountingMapper2 channelAccountingMapper2;
	@Autowired
	UscFinacialStatementMonthlyMapper uscFinacialStatementMonthlyMapper;
	@Autowired
	UscFinacialStatementWeeklyMapper uscFinacialStatementWeeklyMapper;
	@Autowired
	UscFinancialAppleReportMapper2 uscFinancialAppleReportMapper2;
	@Autowired
	UscActorRechargeMapper2 uscActorRechargeMapper2;
	//
	@Autowired
	private DZPTChannelCompanyInfoMapper dZPTChannelCompanyInfoMapper;
	@Autowired
	private ContractFileMapper contractFileMapper;
	@Autowired
	private UscAdvertIncomeMapper uscAdvertIncomeMapper;

	@Override
	public BaseMessage addChannelAccounting(DZPTChannelAccounting channelAccounting) throws Exception{
		BaseMessage bm = new BaseMessage();
		if(!isNullOrZero(channelAccounting.getIspayrate())){
			//channelAccounting.setFinalpayrate(channelAccounting.getPayrate());
		}
//		DZPTChannelAccounting ca = new DZPTChannelAccounting();
//		ca.setGameid(channelAccounting.getGameid());
//		ca.setChannel(channelAccounting.getChannel());
//		ca.setIsvalid(DZPTChannelAccounting.ISINVALID);
//		updateChannelAccountingBySelective(ca);
		int row = channelAccountingMapper.insert(channelAccounting);
		if(row == 1){
			bm.setStatus(1);
		}else{
			bm.setStatus(0);
		}
		return bm;
	}
	
	@Override
	public Integer updateChannelAccountingBySelective(DZPTChannelAccounting ca) throws Exception {
		return channelAccountingMapper2.updateChannelAccountingBySelective(ca);
	}

	public static void main(String args[]){
		Double b = 0.0;
		
		System.out.println(b);
		System.out.println(b.equals(0));
		System.out.println(b == 0);
	}
	
	private boolean isNullOrZero(Object ispayrate) {
		if(ispayrate == null){
			return true;
		}else if(ispayrate instanceof Integer){
			return (Integer)ispayrate == 0;
		}else if(ispayrate instanceof Long){
			return (Long)ispayrate == 0;
		}else if(ispayrate instanceof Double){
			return (Double)ispayrate == 0;
		}else if(ispayrate instanceof Short){
			return (Short)ispayrate == 0;
		}else if(ispayrate instanceof Byte){
			return (Byte)ispayrate == 0;
		}else if(ispayrate instanceof BigInteger){
			return ((BigInteger)ispayrate).intValue() == 0;
		}else if(ispayrate instanceof BigDecimal){
			return ((BigDecimal)ispayrate).doubleValue() == 0;
		}
		return false;
	}

	@Override
	public List<PayChannelAccounting> selectPayChannelAccountingList(BaseSearchData searchData) throws Exception {
		searchData.searchDataFilter(searchData);
		List<PayChannelAccounting> list = null;
		list = channelAccountingMapper2.selectPayChannelAccountingList(searchData);
		String checkType2=searchData.getChecktype2();
		//新项目	
		if(searchData.getChecktype1().equals("1")){
				if(list!=null&&!list.isEmpty()){
					for(PayChannelAccounting e:list){
						ArrayList<ChannelAccounting> channelIncomeList=new ArrayList<ChannelAccounting>();
							//支付渠道为全部
							ChannelAccounting income1=new ChannelAccounting("支付宝", e.getAlipayMoney());
							ChannelAccounting income2=new ChannelAccounting("卡充值", e.getCardPayMoney());
							ChannelAccounting income3=new ChannelAccounting("银行汇款", e.getBankPayMoney());
							ChannelAccounting income4=new ChannelAccounting("mo9支付", e.getMo9PayMoney());
							ChannelAccounting income5=new ChannelAccounting("银联TCL", e.getUnionPayMoney());
							ChannelAccounting income6=new ChannelAccounting("19pay", e.getPay19Money());
							ChannelAccounting income7=new ChannelAccounting("PP钱包", e.getPpWalletPayMoney());
							ChannelAccounting income8=new ChannelAccounting("PP钱包SDK版",e.getPpSdkPayMoney());
							ChannelAccounting income9=new ChannelAccounting("微信支付", e.getWechatPayMoney());
							ChannelAccounting income10=new ChannelAccounting("微软支付", e.getMicrosoftPayMoney());
							//
							ChannelAccounting income11=new ChannelAccounting("googlepay", e.getGooglePayMoney());
							ChannelAccounting income12=new ChannelAccounting("appstore", e.getAppstorePayMoney());
							ChannelAccounting income13=new ChannelAccounting("Facebook", e.getFacebookPayMoney());
							ChannelAccounting income14=new ChannelAccounting("steam", e.getSteamPayMoney());
							ChannelAccounting income15=new ChannelAccounting("windows", e.getWindowsPayMoney());
							channelIncomeList.add(income1);
							channelIncomeList.add(income2);
							channelIncomeList.add(income3);
							channelIncomeList.add(income4);
							channelIncomeList.add(income5);
							channelIncomeList.add(income6);
							channelIncomeList.add(income7);
							channelIncomeList.add(income8);
							channelIncomeList.add(income9);
							channelIncomeList.add(income10);
							channelIncomeList.add(income11);
							channelIncomeList.add(income12);
							channelIncomeList.add(income13);
							channelIncomeList.add(income14);
							channelIncomeList.add(income15);
							e.setPayChannelIncomes(channelIncomeList);
							String yyyy_mm=DateUtil.format_yyyy_MM(e.getStatdate());//格式为yyyy-mm
							e.setStatdate1(yyyy_mm);
					}
			}
		}else{
		//旧项目（世界，江湖，项目id为1和2）
			if(list!=null&&!list.isEmpty()){
				for(PayChannelAccounting e:list){
					ArrayList<ChannelAccounting> channelIncomeList=new ArrayList<ChannelAccounting>();
						ChannelAccounting income1=new ChannelAccounting("支付宝", e.getAlipayMoney());
						ChannelAccounting income2=new ChannelAccounting("卡充值", e.getCardPayMoney());
						ChannelAccounting income3=new ChannelAccounting("银行汇款", e.getBankPayMoney());
						ChannelAccounting income4=new ChannelAccounting("小额支付", e.getMicroPayMoney());
						ChannelAccounting income5=new ChannelAccounting("其他卡", e.getOtherCarPayMoney());
						ChannelAccounting income6=new ChannelAccounting("MO9", e.getMO9Money());
						ChannelAccounting income7=new ChannelAccounting("PP钱包", e.getPpWalletPayMoney());
						ChannelAccounting income8=new ChannelAccounting("新支付宝充值",e.getNewAlipayMoney());
						//两种渠道都有
						ChannelAccounting income11=new ChannelAccounting("googlepay", e.getGooglePayMoney());
						ChannelAccounting income12=new ChannelAccounting("appstore", e.getAppstorePayMoney());
						ChannelAccounting income13=new ChannelAccounting("Facebook", e.getFacebookPayMoney());
						channelIncomeList.add(income1);
						channelIncomeList.add(income2);
						channelIncomeList.add(income3);
						channelIncomeList.add(income4);
						channelIncomeList.add(income5);
						channelIncomeList.add(income6);
						channelIncomeList.add(income7);
						channelIncomeList.add(income8);
						channelIncomeList.add(income11);
						channelIncomeList.add(income12);
						channelIncomeList.add(income13);
						e.setPayChannelIncomes(channelIncomeList);
						String yyyy_mm=DateUtil.format_yyyy_MM(e.getStatdate());//格式为yyyy-mm
						e.setStatdate1(yyyy_mm);
					}
			}
	}
		//签约公司不等于全部时
		if(checkType2!="-1"){
			//签约公司对应支付渠道
			Map<String,List<String>> payChannelMap=ConstantUtil.companyChannelmap;
			List<String> payChannels=payChannelMap.get(checkType2);
			if(payChannels!=null){
				if(list!=null&&!list.isEmpty()){
					for(PayChannelAccounting e:list){
						List<ChannelAccounting> payChannelList=new LinkedList<ChannelAccounting>();
						List<ChannelAccounting> payChannelIncomes=e.getPayChannelIncomes();
						BigDecimal totalMoney=new BigDecimal(0.00);
						//获取签约公司的支付渠道
						for(ChannelAccounting payChannel:payChannelIncomes){
							if(payChannels.contains(payChannel.getChannelName())){
								payChannelList.add(payChannel);
								totalMoney=totalMoney.add(payChannel.getMoney());
							}
						}
						e.setMoney(totalMoney);
						e.setPayChannelIncomes(payChannelList);
					}
				}
			}
		
		}
		return list;
	}
	
	
	
	@Override
	public List<DZPTChannelAccounting> selectChannelAccountingList(BaseSearchData searchData) throws Exception {
		searchData.searchDataFilter(searchData);
		
		List<DZPTChannelAccounting> list = null;
		list = channelAccountingMapper2.selectChannelAccountingList(searchData);
		return list;
	}
	@Override
	public List<ChannelAccounting> selectWeeklyAccountingList(BaseSearchData searchData) throws Exception {
		searchData.searchDataFilter(searchData);
		if("-2".equals(searchData.getChecktype1())){
			searchData.setChecktype1(null);
		}
		if("-2".equals(searchData.getChecktype2())){
			searchData.setChecktype2(null);
		}
		if("-2".equals(searchData.getChecktype3())){
			searchData.setChecktype3(null);
		}
		List<ChannelAccounting> list = null;
		list = channelAccountingMapper2.selectWeeklyAccountingList(searchData);
		return list;
	}
	@Override
	public List<ChannelAccounting> selectAccountingList(BaseSearchData searchData) throws Exception {
		searchData.searchDataFilter(searchData);
		if("-2".equals(searchData.getChecktype1())){
			searchData.setChecktype1(null);
		}
		if("-2".equals(searchData.getChecktype2())){
			searchData.setChecktype2(null);
		}
		if("-2".equals(searchData.getChecktype3())){
			searchData.setChecktype3(null);
		}
		List<ChannelAccounting> list = null;
		list = channelAccountingMapper2.selectAccountingList(searchData);
		return list;
	}
	@Override
	public BaseMessage updateChannelAccounting(DZPTChannelAccounting channelAccounting) throws Exception {
		BaseMessage bm = new BaseMessage();
		if(!isNullOrZero(channelAccounting.getIspayrate())){
			channelAccounting.setFinalpayrate(channelAccounting.getPayrate());
		}
		DZPTChannelAccounting channelAccounting2 = channelAccountingMapper.selectByPrimaryKey(channelAccounting.getId());
		if(channelAccounting2 == null){
			bm.setStatus(0);
			bm.setMessage("Not Found");
		}else{
			channelAccountingMapper.updateByPrimaryKeySelective(channelAccounting);
			bm.setStatus(1);
		}
		return bm;
	}

	@Override
	public DZPTChannelAccounting selectChannelAccounting(DZPTChannelAccounting channelAccounting) throws Exception {
		DZPTChannelAccounting ca = channelAccountingMapper.selectByPrimaryKey(channelAccounting.getId());
		return ca;
	}

	@Override
	public BaseMessage updateBillingAccounting(UscFinacialStatementMonthly channelAccounting) throws Exception {
		BaseMessage bm = new BaseMessage();
		
		UscFinacialStatementMonthly channelAccounting2 = uscFinacialStatementMonthlyMapper.selectByPrimaryKey(channelAccounting.getId());
		if(channelAccounting == null){
			bm.setStatus(0);
			bm.setMessage("Not Found");
		}else{
			channelAccounting2.setPayrate(channelAccounting.getPayrate());
			channelAccounting2.setTaxrate(channelAccounting.getTaxrate());
			channelAccounting2.setSharerate(channelAccounting.getSharerate());
			channelAccounting2.setConfcurr(channelAccounting.getConfcurr());
			channelAccounting2.setExchrate(channelAccounting.getExchrate());
			
			uscFinacialStatementMonthlyMapper.updateByPrimaryKeySelective(channelAccounting2);
			bm.setStatus(1);
		}
		return bm;

	}
	
	@Override
	public BaseMessage updateWeeklyBillingAccounting(UscFinacialStatementWeekly channelAccounting) throws Exception {
		BaseMessage bm = new BaseMessage();
		
		UscFinacialStatementWeekly channelAccounting2 = uscFinacialStatementWeeklyMapper.selectByPrimaryKey(channelAccounting.getId());
		if(channelAccounting == null){
			bm.setStatus(0);
			bm.setMessage("Not Found");
		}else{
			channelAccounting2.setPayrate(channelAccounting.getPayrate());
			channelAccounting2.setTaxrate(channelAccounting.getTaxrate());
			channelAccounting2.setSharerate(channelAccounting.getSharerate());
			channelAccounting2.setConfcurr(channelAccounting.getConfcurr());
			channelAccounting2.setExchrate(channelAccounting.getExchrate());
			
			uscFinacialStatementWeeklyMapper.updateByPrimaryKeySelective(channelAccounting2);
			bm.setStatus(1);
		}
		return bm;

	}

	@Override
	public List<UscActorRecharge> selectSDKRechargeBillList(BaseSearchData searchData) throws Exception {
		searchData.searchDataFilter(searchData);
		List<UscActorRecharge> list = channelAccountingMapper2.selectSDKRechargeBillList(searchData);
		return list;
	}

	

	@Override
	public List<AppleBalance> selectAppleAutoBalance(BaseSearchData searchData) throws Exception {
		searchData.searchDataFilter(searchData);
		List<AppleBalance> list = new ArrayList();
		//
		List<AppleBalance> applelist = uscFinancialAppleReportMapper2.selectAppleRechargeBalanceList(searchData);	//apple recharge info
		List<AppleBalance> goodlist = uscActorRechargeMapper2.selectUscActorRechargeBalanceList(searchData);//good recharge info
		List<AppleBalance> appleLeftOverList = new ArrayList();
		applelist = applelist == null ? new LinkedList() : applelist;
		goodlist = goodlist == null ? new LinkedList() : goodlist;
		//
		for(AppleBalance a : applelist){
			boolean found = false;
			for(AppleBalance e : goodlist){
				//goodlist 的 AppleBalance 的 logdate 对应回 apple 财年财月
				if(a.getProductid().equals(e.getProductid())){
					found = true;
					goodlist.remove(e);
					//
					a.setAppleCount(a.getQuantity());
					a.setGoodCount(e.getQuantity());
					a.setDiffCount(a.getGoodCount() - a.getAppleCount());
					list.add(a);
					break;
				}
			}
			if(!found){
				appleLeftOverList.add(a);
			}
		}
		applelist.clear();
		//
		for(AppleBalance a : appleLeftOverList){
			a.setAppleCount(a.getQuantity());
			a.setGoodCount(0);
			a.setDiffCount(a.getGoodCount() - a.getAppleCount());
			list.add(a);
		}
		for(AppleBalance a : goodlist){
			a.setAppleCount(0);
			a.setStart(null);
			a.setEnd(null);
			a.setFyear(null);
			a.setFperiod(null);
			a.setGoodCount(a.getQuantity());
			a.setDiffCount(a.getGoodCount() - a.getAppleCount());
			list.add(a);
		}
		return list;
	}

	@Override
	public BaseMessage deleteChannelAccounting(DZPTChannelAccounting channelAccounting) throws Exception {
		BaseMessage bm = new BaseMessage();
		DZPTChannelAccounting ca = channelAccountingMapper.selectByPrimaryKey(channelAccounting.getId());
		if(ca == null){
			bm.setStatus(0);
			bm.setMessage("Not Found");
		}else{
			if(channelAccountingMapper.deleteByPrimaryKey(ca.getId()) == 1){
				bm.setStatus(1);
			}else{
				bm.setStatus(0);
			}
		}
		return bm;
	}

	@Override
	public List<DZPTChannelCompanyInfo> selectChannelCompanyInfoList(
			DZPTChannelCompanyInfo companyInfo) throws Exception {
		// TODO Auto-generated method stub
		List<DZPTChannelCompanyInfo> list=dZPTChannelCompanyInfoMapper.selectChannelCompanyInfoList(companyInfo);
		return list;
	}

	@Override
	public BaseMessage addChannelCompanyInfo(DZPTChannelCompanyInfo companyInfo)
			throws Exception {
		// TODO Auto-generated method stub
		BaseMessage message=new BaseMessage();
		//查找是否已存在渠道
		DZPTChannelCompanyInfo isExist=dZPTChannelCompanyInfoMapper.selectCompanyByChannelId(companyInfo.getChannelId());
		if(isExist!=null){
			message.setMessage("添加失败,渠道已存在！");
			message.setStatus(0);
		}else{
			int row=dZPTChannelCompanyInfoMapper.insertChannelCompanyInfo(companyInfo);
			if(row==1){
				message.setStatus(1);
				message.setMessage("添加成功");
			}else{
				message.setStatus(0);
				message.setMessage("添加失败");
			}
		}
		return message;
	}

	@Override
	public BaseMessage updateChannelCompanyInfo(
			DZPTChannelCompanyInfo companyInfo) throws Exception {
		// TODO Auto-generated method stub
		BaseMessage message=new BaseMessage();
		DZPTChannelCompanyInfo info=dZPTChannelCompanyInfoMapper.selectByPrimaryKey(companyInfo.getId());
		if(info==null){
			message.setStatus(0);
			message.setMessage("没找到！");
		}else{
			dZPTChannelCompanyInfoMapper.updateByPrimaryKey(companyInfo);
			message.setStatus(1);
		}
		return message;
	}

	@Override
	public BaseMessage deleteChannelCompanyInfo(
			DZPTChannelCompanyInfo companyInfo) throws Exception {
		// TODO Auto-generated method stub
		BaseMessage bm=new BaseMessage();
		DZPTChannelCompanyInfo info=dZPTChannelCompanyInfoMapper.selectByPrimaryKey(companyInfo.getId());
		if(info==null){
			bm.setStatus(0);
			bm.setMessage("not found");
		}else{
			int row=dZPTChannelCompanyInfoMapper.deleteByPrimaryKey(companyInfo.getId());
			if(row==1){
				bm.setStatus(1);
			}else{
				bm.setStatus(0);
				bm.setMessage("删除失败");
			}
		}
		return bm;
	}

	@Override
	public DZPTChannelCompanyInfo selectChannelCompany(
			DZPTChannelCompanyInfo companyInfo) throws Exception {
		// TODO Auto-generated method stub
		DZPTChannelCompanyInfo Info=dZPTChannelCompanyInfoMapper.selectByPrimaryKey(companyInfo.getId());
		return Info;
	}

	@Override
	public List<Map<String, String>> selectChannelGameAccountingList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		List<ChannelAccounting> channelGameAccountList=channelAccountingMapper2.selectChannelGameAccountingList(searchData);
		if(channelGameAccountList.size()>0){
			for(ChannelAccounting e:channelGameAccountList){
				Map<String,String> map=new HashMap<String, String>();
				map.put("channelId", e.getChannelId()!=null?e.getChannelId().toString():"");
				map.put("gameName", e.getGameName()!=null?e.getGameName():"");
				map.put("channelSimName", e.getChannelSimName()!=null?e.getChannelName():"");
				map.put("channelName", e.getChannelName()!=null?e.getChannelName():"");
				map.put("currency", e.getCurrency()!=null?e.getCurrency().toString():"");
				map.put("money", e.getMoney()!=null?e.getMoney().toString():"");
				map.put("payrate", e.getPayrate()!=null?e.getPayrate().toString():"");
				map.put("taxrate", e.getTaxrate()!=null?e.getTaxrate().toString():"");
				map.put("sharerate",e.getSharerate()!=null?e.getSharerate().toString():"");
				map.put("confcurr", e.getConfcurr()!=null?e.getConfcurr().toString():"");
				map.put("exchrate", e.getExchrate()!=null?e.getExchrate().toString():"");
				map.put("rmbrate", e.getRmbrate()!=null?e.getRmbrate().toString():"");
				list.add(map);
			}
			
			//按照渠道id来排序
			Collections.sort(list,new Comparator<Map<String, String>>() {
				public int compare(Map<String, String> o1, Map<String, String> o2) {
					return o1.get("channelId").compareTo(o2.get("channelId"));
				}
			});
		}
		return list;
	}

	@Override
	public DZPTChannelCompanyInfo selectCompanyByChannelId(
			DZPTChannelCompanyInfo companyInfo) throws Exception {
		// TODO Auto-generated method stub
		DZPTChannelCompanyInfo company=dZPTChannelCompanyInfoMapper.selectCompanyByChannelId(companyInfo.getChannelId());
		return company;
	}

	@Override
	public BaseMessage addContractFile(ContractFile contractFile)
			throws Exception {
		// TODO Auto-generated method stub
		BaseMessage msg=new BaseMessage();
		int result=contractFileMapper.insertContractFile(contractFile);
		if(result==1){
			msg.setStatus(1);
			msg.setMessage("添加成功");
		}else{
		msg.setStatus(0);
		msg.setMessage("添加失败");
		}
		return msg;
	}

	@Override
	public ContractFile selectByFilePath(ContractFile contractFile)
			throws Exception {
		// TODO Auto-generated method stub
		ContractFile file=contractFileMapper.selectByFilePath(contractFile);
		return file;
	}

	@Override
	public List<ContractFile> selectContractFileList(ContractFile contractFile)
			throws Exception {
		// TODO Auto-generated method stub
		List<ContractFile> list=contractFileMapper.selectContractFileList(contractFile);
		if(list.size()>0){
			for(ContractFile e:list){
				String filePath=e.getFilePath();
				if(filePath!=null){
					e.setFileName(filePath.substring(filePath.lastIndexOf("/")+1));
				}
			}
		}
		return list;
	}

	@Override
	public BaseMessage deleteContractFile(Integer id) throws Exception {
		// TODO Auto-generated method stub
		BaseMessage msg=new BaseMessage();
		ContractFile file=contractFileMapper.selectByPrimaryKey(id);
		if(file==null){
			msg.setStatus(0);
			msg.setMessage("删除失败，文件不存在");
		}else{
			int row=contractFileMapper.delectByPrimaryKey(id);
			if(row==1){
				msg.setStatus(1);
				msg.setMessage("删除成功");
			}else{
				msg.setStatus(0);
				msg.setMessage("删除失败");
			}
		}
		return msg;
	}

	@Override
	public List<DZPTChannelAccounting> selectWarningNoticeContractList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		List<DZPTChannelAccounting> list=channelAccountingMapper2.selectWarningNoticeContractList(searchData);
		return list;
	}

	@Override
	public List<UscAdvertIncome> selectUscAdvertIncomeList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<UscAdvertIncome> list=uscAdvertIncomeMapper.selectUscAdvertIncomeList(searchData);
		List<UscAdvertIncome> newList=new ArrayList<UscAdvertIncome>();
		if(list!=null&&!list.isEmpty()){
			for(UscAdvertIncome income:list){
				if(income.getStatdate()!=null){
					income.setDate(DateUtil.format_yyyy_MM(income.getStatdate()));
				}
			}
			//签约公司对应广告商列表
			List<String> advertiserNameList=uscAdvertIncomeMapper.selectAdvertiserNameList(searchData);
			if(advertiserNameList!=null){
				for(UscAdvertIncome income:list){
					//筛选签约公司对应的广告商
					if(advertiserNameList.contains(income.getName())){
						newList.add(income);
					}
				}
			}
			list=newList;
			
		}
		return list;
	}
	@Transactional
	@Override
	public BaseMessage batchAddVideoIncomeData(List<UscAdvertIncome> list) {
		// TODO Auto-generated method stub
		BaseMessage msg=new BaseMessage();
		try {
			for(UscAdvertIncome income:list){
				int status=uscAdvertIncomeMapper.insert(income);
			}
			msg.setMessage("添加成功");
			msg.setStatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		return msg;
	}

	@Override
	public List<UscAdvertIncome> selectUscAdvertChannelList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<UscAdvertIncome> list=uscAdvertIncomeMapper.selectUscAdvertChannelList(searchData);
		return list;
	}
	

}
