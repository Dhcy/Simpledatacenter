package com.good.plat.datacenter.independ.service.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhSocialContact;
/**
 * 银河社交
 * @ClassName: YhSocialContactService
 * @Description: TODO
 * @author hwj
 * @date 2018-5-16 下午02:48:20
 */
public interface YhSocialContactService {
	/**
	 * 查询拥有好友数量的玩家数
	 * @Title: selectActUserNumOfFriendsList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhSocialContact>
	 * @author hwj
	 * @date 2018-5-16 下午02:46:44
	 */
	public List<YhSocialContact> selectActUserNumOfFriendsList(IndependBaseSearchData searchData)throws Exception;
}
