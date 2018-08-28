package com.good.plat.datacenter.game.mapper;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.game.entity.TBLogplatGameVersion;

public interface TBLogplatGameVersionMapper2 {
    List<TBLogplatGameVersion> selectGameVersionByGameId(Integer gameid);
    List<TBLogplatGameVersion> selectGameVersionList(BaseSearchData searchData);
}