package com.good.plat.datacenter.datastat.mapper.buyplat;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.buyplat.BuyPlatAdvertiser;
import com.good.plat.datacenter.datastat.entity.buyplat.SubAdvertiseChannel;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlAdchannel;

public interface BuyPlatMapper {

	List selectBuyPlatSummary(BaseSearchData searchData);

	List selectBuyPlatDataQuery(BaseSearchData searchData);

	List selectBuyPlatActorRegisterTimeDist(BaseSearchData searchData);

	List selectBuyPlatActorRegisterInfo(BaseSearchData searchData);

	List selectBuyPlatActorLevelDist(BaseSearchData searchData);

	List selectAdvertiserList(BaseSearchData searchData);

	List<BuyPlatAdvertiser> selectAdvOfCPAList(BaseSearchData searchData);

	List<BuyPlatAdvertiser> selectAdvOfCPTList(BaseSearchData searchData);

	List selectAdvOfCPCList(BaseSearchData searchData);

	List selectAdvOfCPDList(BaseSearchData searchData);

	List selectMlchnlBuckleList(BaseSearchData searchData);

	List<TBLogplatMlchnlAdchannel> selectAdChannelList(BaseSearchData searchData);

	TBLogplatMlchnlAdchannel selectAdChannel(BaseSearchData searchData);

	TBLogplatMlchnlAdchannel selectAdChannelByChannelID(Integer id);

	int deleteAdChannelByIDAndChanneldID(BaseSearchData searchData);

	List<TBLogplatMlchnlAdchannel> selectUserAdchannelList(BaseSearchData searchData);

	List<SubAdvertiseChannel> selectSubChannelList(SubAdvertiseChannel subAdvertiseChannel);
}
