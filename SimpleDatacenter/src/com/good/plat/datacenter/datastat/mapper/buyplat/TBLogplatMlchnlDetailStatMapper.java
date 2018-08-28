package com.good.plat.datacenter.datastat.mapper.buyplat;

import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlDetailStat;

public interface TBLogplatMlchnlDetailStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatMlchnlDetailStat record);

    int insertSelective(TBLogplatMlchnlDetailStat record);

    TBLogplatMlchnlDetailStat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatMlchnlDetailStat record);

    int updateByPrimaryKey(TBLogplatMlchnlDetailStat record);
}