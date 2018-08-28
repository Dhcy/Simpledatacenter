package com.good.plat.datacenter.datastat.mapper.buyplat;

import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlBuckle;

public interface TBLogplatMlchnlBuckleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatMlchnlBuckle record);

    int insertSelective(TBLogplatMlchnlBuckle record);

    TBLogplatMlchnlBuckle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatMlchnlBuckle record);

    int updateByPrimaryKey(TBLogplatMlchnlBuckle record);
}