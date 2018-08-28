package com.good.plat.datacenter.datastat.mapper.buyplat;

import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlStat;

public interface TBLogplatMlchnlStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatMlchnlStat record);

    int insertSelective(TBLogplatMlchnlStat record);

    TBLogplatMlchnlStat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatMlchnlStat record);

    int updateByPrimaryKey(TBLogplatMlchnlStat record);
}