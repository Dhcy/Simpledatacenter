package com.good.plat.datacenter.datastat.mapper.buyplat;

import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlRetention;

public interface TBLogplatMlchnlRetentionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatMlchnlRetention record);

    int insertSelective(TBLogplatMlchnlRetention record);

    TBLogplatMlchnlRetention selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatMlchnlRetention record);

    int updateByPrimaryKey(TBLogplatMlchnlRetention record);
}