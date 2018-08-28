package com.good.plat.datacenter.datastat.service.impl.buyplat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;

import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.buyplat.BuyPlatAdvertiser;
import com.good.plat.datacenter.datastat.entity.buyplat.BuyPlatDataQuery;
import com.good.plat.datacenter.datastat.entity.buyplat.BuyPlatSummary;
import com.good.plat.datacenter.datastat.entity.buyplat.GameCommonEntity;
import com.good.plat.datacenter.datastat.entity.buyplat.SubAdvertiseChannel;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlActlvlStat;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlAdchannel;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlAdvertisers;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlBuckle;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlRegcountStat;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlRegisterStat;
import com.good.plat.datacenter.datastat.mapper.buyplat.BuyPlatMapper;
import com.good.plat.datacenter.datastat.mapper.buyplat.TBLogplatMlchnlAdchannelMapper;
import com.good.plat.datacenter.datastat.mapper.buyplat.TBLogplatMlchnlAdvertisersMapper;
import com.good.plat.datacenter.datastat.mapper.buyplat.TBLogplatMlchnlBuckleMapper;
import com.good.plat.datacenter.datastat.service.buyplat.IBuyPlatService;

@Service(value="buyPlatService")
public class BuyPlatServiceImpl implements IBuyPlatService{
	
	@Autowired
	BuyPlatMapper buyPlatMapper;
	@Autowired
	TBLogplatMlchnlBuckleMapper mlchnlBuckleMapper;
	@Autowired
	TBLogplatMlchnlAdvertisersMapper mlchnlAdvertisersMapper;
	@Autowired
	TBLogplatMlchnlAdchannelMapper tBLogplatMlchnlAdchannelMapper;
	@Override
	public List<BuyPlatDataQuery> selectBuyPlatSummary(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List list = buyPlatMapper.selectBuyPlatSummary(searchData);
		return list;
	}
	
	@Override
	public List<BuyPlatDataQuery> selectBuyPlatDataQuery(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List list = buyPlatMapper.selectBuyPlatDataQuery(searchData);
		return list;
	}
	
	@Override
	public List<TBLogplatMlchnlRegisterStat> selectBuyPlatActorRegisterTimeDist(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List list = buyPlatMapper.selectBuyPlatActorRegisterTimeDist(searchData);
		return list;
	}

	@Override
	public List<TBLogplatMlchnlRegcountStat> selectBuyPlatActorRegisterInfo(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List list = buyPlatMapper.selectBuyPlatActorRegisterInfo(searchData);
		return list;
	}

	@Override
	public List<TBLogplatMlchnlActlvlStat> selectBuyPlatActorLevelDist(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List<TBLogplatMlchnlActlvlStat> list = buyPlatMapper.selectBuyPlatActorLevelDist(searchData);
		if(list != null){
			Double total = 0.0;
			for(TBLogplatMlchnlActlvlStat e : list){
				if(e == null){
					continue;
				}
				if(e.getActlvl_1_2() == null){
					e.setActlvl_1_2(0);
				}
				if(e.getActlvl_3_10() == null){
					e.setActlvl_3_10(0);
				}
				if(e.getActlvl_11_20() == null){
					e.setActlvl_11_20(0);
				}
				if(e.getActlvl_21_30() == null){
					e.setActlvl_21_30(0);
				}
				if(e.getActlvl_31_40() == null){
					e.setActlvl_31_40(0);
				}
				if(e.getActlvl_41_50() == null){
					e.setActlvl_41_50(0);
				}
				if(e.getActlvl_51_X() == null){
					e.setActlvl_51_X(0);
				}
				
				total += e.getActlvl_1_2();
				total += e.getActlvl_3_10();
				total += e.getActlvl_11_20();
				total += e.getActlvl_21_30();
				total += e.getActlvl_31_40();
				total += e.getActlvl_41_50();
				total += e.getActlvl_51_X();
				e.setUsercount(total.intValue());
				e.setActlvl_1_2_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_1_2().doubleValue(), total), 100.0, NumberUtil.DEFAULT_SCALE));
				e.setActlvl_3_6_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_3_10().doubleValue(), total), 100.0, NumberUtil.DEFAULT_SCALE));
				e.setActlvl_7_15_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_11_20().doubleValue(), total), 100.0, NumberUtil.DEFAULT_SCALE));
				e.setActlvl_16_20_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_21_30().doubleValue(), total), 100.0, NumberUtil.DEFAULT_SCALE));
				e.setActlvl_21_25_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_31_40().doubleValue(), total), 100.0, NumberUtil.DEFAULT_SCALE));
				e.setActlvl_26_30_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_41_50().doubleValue(), total), 100.0, NumberUtil.DEFAULT_SCALE));
				e.setActlvl_31_X_rate(NumberUtil.mul(NumberUtil.div(e.getActlvl_51_X().doubleValue(), total), 100.0, NumberUtil.DEFAULT_SCALE));
			
			}
		}

		return list;
	}

	@Override
	public List<TBLogplatMlchnlAdvertisers> selectAdvertiserList(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List list = buyPlatMapper.selectAdvertiserList(searchData);
		return list;
	}

	@Override
	public BaseMessage addAdvertiser(TBLogplatMlchnlAdvertisers advertiser) {
		BaseMessage bm = new BaseMessage();
		
		TBLogplatMlchnlAdvertisers ad = null;
		if(advertiser.getId() != null){
			ad = mlchnlAdvertisersMapper.selectByPrimaryKey(advertiser.getId());
		}
		if(ad != null){
			bm.setStatus(0);
			bm.setMessage("FOUND");
			return bm;
		}
		
		BaseSearchData searchData = new BaseSearchData();
		searchData.setGameid(advertiser.getGameid());
		searchData.setChannelid(advertiser.getAdchannel());
		searchData.setSubChannelid(advertiser.getSubchannel());
		List list = selectAdvertiserList(searchData );
		if(list == null || list.size() == 0){
			mlchnlAdvertisersMapper.insert(advertiser);
			bm.setStatus(1);
		}else{
			bm.setStatus(0);
			bm.setMessage("Already Exists!");
		}
		return bm;
	}
	
	@Override
	public BaseMessage updateAdvertiser(TBLogplatMlchnlAdvertisers advertiser) {
		BaseMessage bm = new BaseMessage();
		TBLogplatMlchnlAdvertisers ad = mlchnlAdvertisersMapper.selectByPrimaryKey(advertiser.getId());
		if(ad != null){
			mlchnlAdvertisersMapper.updateByPrimaryKey(advertiser);
			bm.setStatus(1);
			
		}else{
			bm.setStatus(0);
			bm.setMessage("NOT FOUND");
		}
		return bm;
	}
	
	@Override
	public BaseMessage deleteAdvertiserById(Integer id) {
		BaseMessage bm = new BaseMessage();
		TBLogplatMlchnlAdvertisers ad = mlchnlAdvertisersMapper.selectByPrimaryKey(id);
		if(ad == null){
			bm.setStatus(0);
			bm.setMessage("NOT FOUND");
			
		}else{
			mlchnlAdvertisersMapper.deleteByPrimaryKey(id);
			bm.setStatus(1);
		}
		return bm;
	}

	@Override
	public List<BuyPlatAdvertiser> selectAdvOfCPAList(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List<BuyPlatAdvertiser> list = buyPlatMapper.selectAdvOfCPAList(searchData);
		return list;
	}

	@Override
	public List<BuyPlatAdvertiser> selectAdvOfCPTList(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List<BuyPlatAdvertiser> list = buyPlatMapper.selectAdvOfCPTList(searchData);
		return list;
	}

	@Override
	public List<BuyPlatAdvertiser> selectAdvOfCPCList(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List list = buyPlatMapper.selectAdvOfCPCList(searchData);
		return list;
	}

	@Override
	public List<BuyPlatAdvertiser> selectAdvOfCPDList(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List list = buyPlatMapper.selectAdvOfCPDList(searchData);
		return list;
	}

	@Override
	public List<TBLogplatMlchnlBuckle> selectMlchnlBuckleList(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List list = buyPlatMapper.selectMlchnlBuckleList(searchData);
		return list;
	}

	@Override
	public BaseMessage addMlchnlBuckle(TBLogplatMlchnlBuckle buckle) {
		BaseMessage bm = new BaseMessage();
		TBLogplatMlchnlBuckle tbuckle = mlchnlBuckleMapper.selectByPrimaryKey(buckle.getId());
		if(tbuckle != null){
			bm.setStatus(0);
			bm.setMessage("FOUND");
			
		}else{
			mlchnlBuckleMapper.insert(buckle);
			bm.setStatus(1);
		}
		return bm;
	}

	@Override
	public BaseMessage updateMlchnlBuckle(TBLogplatMlchnlBuckle buckle) {
		BaseMessage bm = new BaseMessage();
		TBLogplatMlchnlBuckle tbuckle = mlchnlBuckleMapper.selectByPrimaryKey(buckle.getId());
		if(tbuckle != null){
			mlchnlBuckleMapper.updateByPrimaryKeySelective(buckle);
			bm.setStatus(1);
		}else{
			bm.setStatus(0);
			bm.setMessage("NOT FOUND");
		}
		return bm;
	}

	@Override
	public BaseMessage deleteMlchnlBuckleById(Integer id) {
		BaseMessage bm = new BaseMessage();
		TBLogplatMlchnlBuckle buckle = mlchnlBuckleMapper.selectByPrimaryKey(id);
		if(buckle != null){
			mlchnlBuckleMapper.deleteByPrimaryKey(id);
			bm.setStatus(1);
		}else{
			bm.setStatus(0);
			bm.setMessage("NOT FOUND");
		}
		return bm;
	}

	@Override
	public BaseMessage disableMlchnlBuckleById(Integer id) {
		BaseMessage bm = new BaseMessage();
		TBLogplatMlchnlBuckle buckle = mlchnlBuckleMapper.selectByPrimaryKey(id);
		if(buckle != null){
			buckle.setState(GameCommonEntity.STATE_DISABLED);
			mlchnlBuckleMapper.updateByPrimaryKey(buckle);
			bm.setStatus(1);
		}else{
			bm.setStatus(0);
			bm.setMessage("NOT FOUND");
		}
		return bm;
	}

	@Override
	public BaseMessage enableMlchnlBuckleById(Integer id) {
		BaseMessage bm = new BaseMessage();
		TBLogplatMlchnlBuckle buckle = mlchnlBuckleMapper.selectByPrimaryKey(id);
		if(buckle != null){
			buckle.setState(GameCommonEntity.STATE_ENABLED);
			mlchnlBuckleMapper.updateByPrimaryKey(buckle);
			bm.setStatus(1);
		}else{
			bm.setStatus(0);
			bm.setMessage("NOT FOUND");
		}
		return bm;
	}

	@Override
	public BaseMessage updateAdChannel(TBLogplatMlchnlAdchannel adchannel) {
		BaseMessage bm = new BaseMessage();
		try{
			if(tBLogplatMlchnlAdchannelMapper.selectByPrimaryKey(adchannel.getId()) != null){
				tBLogplatMlchnlAdchannelMapper.updateByPrimaryKeySelective(adchannel);
			}else{
				tBLogplatMlchnlAdchannelMapper.insertSelective(adchannel);
			}
			bm.setStatus(1);
		}catch(Exception e){
			bm.setStatus(0);
			bm.setMessage(e.getMessage());
		}
		return bm;
	}

	@Override
	public BaseMessage deleteAdChannel(Integer id) {
		BaseMessage bm = new BaseMessage();
		try{
			TBLogplatMlchnlAdchannel ad = tBLogplatMlchnlAdchannelMapper.selectByPrimaryKey(id);
			if(ad != null){
				tBLogplatMlchnlAdchannelMapper.deleteByPrimaryKey(id);
				bm.setStatus(1);
			}else{
				bm.setStatus(0);
				bm.setMessage("NOT FOUND");
			}
		}catch(Exception e){
			bm.setStatus(0);
			bm.setMessage(e.getMessage());
		}
		return bm;
	}

	@Override
	public List<TBLogplatMlchnlAdchannel> selectAdChannelList(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List<TBLogplatMlchnlAdchannel> list = null;
		list = buyPlatMapper.selectAdChannelList(searchData);
		return list;
	}

	@Override
	public TBLogplatMlchnlAdchannel selectAdChannelByChannelID(Integer id) {
		return buyPlatMapper.selectAdChannelByChannelID(id);
	}
	@Override
	public TBLogplatMlchnlAdchannel selectAdChannel(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		return buyPlatMapper.selectAdChannel( searchData);
	}

	@Override
	public BaseMessage deleteAdChannelByIDAndChanneldID(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		BaseMessage bm = new BaseMessage();
		try{
			TBLogplatMlchnlAdchannel ad = tBLogplatMlchnlAdchannelMapper.selectByPrimaryKey(Integer.valueOf(searchData.getChecktype1()));
			if(ad != null){
				buyPlatMapper.deleteAdChannelByIDAndChanneldID( searchData);
				bm.setStatus(1);
			}else{
				bm.setStatus(0);
				bm.setMessage("NOT FOUND");
			}
		}catch(Exception e){
			e.printStackTrace();
			bm.setStatus(0);
			bm.setMessage(e.getMessage());
		}
		return bm;
	}

	@Override
	public List<TBLogplatMlchnlAdchannel> selectUserAdchannelList(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List<TBLogplatMlchnlAdchannel> list = null;
		list = buyPlatMapper.selectUserAdchannelList(searchData);
		return list;
	}

	@Override
	public List<SubAdvertiseChannel> selectSubChannelList(SubAdvertiseChannel subchannel) {
		List<SubAdvertiseChannel> list = null;
		list = buyPlatMapper.selectSubChannelList(subchannel);
		return list;
	}
	
}
