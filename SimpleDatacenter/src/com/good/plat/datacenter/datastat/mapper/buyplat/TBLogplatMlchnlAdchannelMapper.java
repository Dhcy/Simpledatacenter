package com.good.plat.datacenter.datastat.mapper.buyplat;

import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlAdchannel;

public interface TBLogplatMlchnlAdchannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatMlchnlAdchannel record);

    int insertSelective(TBLogplatMlchnlAdchannel record);

    TBLogplatMlchnlAdchannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatMlchnlAdchannel record);

    int updateByPrimaryKey(TBLogplatMlchnlAdchannel record);
}