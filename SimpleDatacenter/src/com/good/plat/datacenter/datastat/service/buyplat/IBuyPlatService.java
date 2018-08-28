package com.good.plat.datacenter.datastat.service.buyplat;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
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

public interface IBuyPlatService {
	List<BuyPlatDataQuery> selectBuyPlatSummary(BaseSearchData searchData);
	List<BuyPlatDataQuery> selectBuyPlatDataQuery(BaseSearchData searchData);
	List<TBLogplatMlchnlRegisterStat> selectBuyPlatActorRegisterTimeDist(BaseSearchData searchData);
	List<TBLogplatMlchnlRegcountStat> selectBuyPlatActorRegisterInfo(BaseSearchData searchData);
	List<TBLogplatMlchnlActlvlStat> selectBuyPlatActorLevelDist(BaseSearchData searchData);
	
	//广告主管理
	/**
	 * 买量后台-广告主查询
	 * @Title: selectAdvertiserList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<TBLogplatMlchnlAdvertisers>
	 * @author moxw
	 * @date 2016年5月30日 下午5:18:34
	 */
	List<TBLogplatMlchnlAdvertisers> selectAdvertiserList(BaseSearchData searchData);
	/**
	 * 买量后台-广告主增加
	 * @Title: addAdvertiser
	 * @Description: TODO
	 * @param advertiser
	 * @return
	 * BaseMessage
	 * @author moxw
	 * @date 2016年5月30日 下午5:19:37
	 */
	BaseMessage addAdvertiser(TBLogplatMlchnlAdvertisers advertiser);
	/**
	 * 买量后台-广告主更新
	 * @Title: updateAdvertiser
	 * @Description: TODO
	 * @param advertiser
	 * @return
	 * BaseMessage
	 * @author moxw
	 * @date 2016年5月30日 下午5:19:44
	 */
	BaseMessage updateAdvertiser(TBLogplatMlchnlAdvertisers advertiser);
	/**
	 * 买量后台-广告主删除
	 * @Title: deleteAdvertiserById
	 * @Description: TODO
	 * @param id
	 * @return
	 * BaseMessage
	 * @author moxw
	 * @date 2016年5月30日 下午5:19:52
	 */
	BaseMessage deleteAdvertiserById(Integer id);
	
	/**
	 * 
	 * @Title: selectAdvOfCPAList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<BuyPlatAdvertiser>
	 * @author moxw
	 * @date 2016年5月30日 下午5:18:50
	 */
	List<BuyPlatAdvertiser> selectAdvOfCPAList(BaseSearchData searchData);
	List<BuyPlatAdvertiser> selectAdvOfCPTList(BaseSearchData searchData);
	List<BuyPlatAdvertiser> selectAdvOfCPCList(BaseSearchData searchData);
	List<BuyPlatAdvertiser> selectAdvOfCPDList(BaseSearchData searchData);
	
	//扣量
	/**
	 * 买量后台-扣量查询
	 * @Title: selectMlchnlBuckle
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<TBLogplatMlchnlBuckle>
	 * @author moxw
	 * @date 2016年5月30日 下午5:17:38
	 */
	List<TBLogplatMlchnlBuckle> selectMlchnlBuckleList(BaseSearchData searchData);
	
	/**
	 * 买量后台-扣量增加
	 * @Title: addMlchnlBuckle
	 * @Description: TODO
	 * @param buckle
	 * @return
	 * BaseMessage
	 * @author moxw
	 * @date 2016年5月30日 下午5:18:00
	 */
	BaseMessage addMlchnlBuckle(TBLogplatMlchnlBuckle buckle);
	/**
	 * 买量后台-扣量更新
	 * @Title: updateMlchnlBuckle
	 * @Description: TODO
	 * @param buckle
	 * @return
	 * BaseMessage
	 * @author moxw
	 * @date 2016年5月30日 下午5:18:14
	 */
	BaseMessage updateMlchnlBuckle(TBLogplatMlchnlBuckle buckle);
	/**
	 * 买量后台-扣量删除
	 * @Title: deleteMlchnlBuckleById
	 * @Description: TODO
	 * @param id
	 * @return
	 * BaseMessage
	 * @author moxw
	 * @date 2016年5月30日 下午5:18:25
	 */
	BaseMessage deleteMlchnlBuckleById(Integer id);
	
	BaseMessage disableMlchnlBuckleById(Integer id);
	BaseMessage enableMlchnlBuckleById(Integer id);
	BaseMessage updateAdChannel(TBLogplatMlchnlAdchannel adchannel);
	BaseMessage deleteAdChannel(Integer valueOf);
	TBLogplatMlchnlAdchannel selectAdChannelByChannelID(Integer id);
	List<TBLogplatMlchnlAdchannel> selectAdChannelList(BaseSearchData searchData);
	TBLogplatMlchnlAdchannel selectAdChannel(BaseSearchData searchData);
	BaseMessage deleteAdChannelByIDAndChanneldID(BaseSearchData searchData);
	List<TBLogplatMlchnlAdchannel> selectUserAdchannelList(BaseSearchData searchData);
	List<SubAdvertiseChannel> selectSubChannelList(SubAdvertiseChannel subchannel);
}
