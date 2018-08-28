package com.good.plat.datacenter.game.mapper;

import java.util.List;

import com.good.plat.datacenter.game.entity.GameLanguage;
import com.good.plat.datacenter.game.entity.TBLogplatAttrValue;

public interface TBLogplatAttrValueMapper2 {
    List<TBLogplatAttrValue> selectAttrValueList(Integer attr_id);

	List<GameLanguage> getGameLanguageList(GameLanguage gl);
}