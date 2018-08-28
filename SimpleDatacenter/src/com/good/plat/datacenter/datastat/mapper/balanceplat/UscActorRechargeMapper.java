package com.good.plat.datacenter.datastat.mapper.balanceplat;

import com.good.plat.datacenter.datastat.entity.balanceplat.UscActorRecharge;

public interface UscActorRechargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UscActorRecharge record);

    int insertSelective(UscActorRecharge record);

    UscActorRecharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UscActorRecharge record);

    int updateByPrimaryKey(UscActorRecharge record);
}