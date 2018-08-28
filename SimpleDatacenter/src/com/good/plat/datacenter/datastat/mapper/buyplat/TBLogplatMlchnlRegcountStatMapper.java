package com.good.plat.datacenter.datastat.mapper.buyplat;

import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlRegcountStat;

public interface TBLogplatMlchnlRegcountStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatMlchnlRegcountStat record);

    int insertSelective(TBLogplatMlchnlRegcountStat record);

    TBLogplatMlchnlRegcountStat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatMlchnlRegcountStat record);

    int updateByPrimaryKey(TBLogplatMlchnlRegcountStat record);
}