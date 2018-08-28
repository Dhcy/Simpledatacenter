package com.good.plat.datacenter.datastat.mapper.buyplat;

import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlRegisterStat;

public interface TBLogplatMlchnlRegisterStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatMlchnlRegisterStat record);

    int insertSelective(TBLogplatMlchnlRegisterStat record);

    TBLogplatMlchnlRegisterStat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatMlchnlRegisterStat record);

    int updateByPrimaryKey(TBLogplatMlchnlRegisterStat record);
}