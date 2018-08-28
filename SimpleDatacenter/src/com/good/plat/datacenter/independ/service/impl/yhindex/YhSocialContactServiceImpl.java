package com.good.plat.datacenter.independ.service.impl.yhindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhSocialContact;
import com.good.plat.datacenter.independ.mapper.yhindex.YhSocialContactMapper;
import com.good.plat.datacenter.independ.service.yhindex.YhSocialContactService;
@Service(value="yhSocialContactService")
public class YhSocialContactServiceImpl implements YhSocialContactService {
	@Autowired
	private YhSocialContactMapper yhSocialContactMapper;
	@Override
	public List<YhSocialContact> selectActUserNumOfFriendsList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhSocialContact> friendsActUserNumList=yhSocialContactMapper.selectActUserNumOfFriendsList(searchData);
		return friendsActUserNumList;
	}

}
