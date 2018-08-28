package com.good.plat.datacenter.datastat.mapper.buyplat;

import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlAdvertisers;

public interface TBLogplatMlchnlAdvertisersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatMlchnlAdvertisers record);

    int insertSelective(TBLogplatMlchnlAdvertisers record);

    TBLogplatMlchnlAdvertisers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatMlchnlAdvertisers record);

    int updateByPrimaryKey(TBLogplatMlchnlAdvertisers record);
}