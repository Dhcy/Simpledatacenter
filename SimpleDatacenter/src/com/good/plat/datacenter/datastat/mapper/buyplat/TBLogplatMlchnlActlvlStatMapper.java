package com.good.plat.datacenter.datastat.mapper.buyplat;

import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlActlvlStat;

public interface TBLogplatMlchnlActlvlStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatMlchnlActlvlStat record);

    int insertSelective(TBLogplatMlchnlActlvlStat record);

    TBLogplatMlchnlActlvlStat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatMlchnlActlvlStat record);

    int updateByPrimaryKey(TBLogplatMlchnlActlvlStat record);
}