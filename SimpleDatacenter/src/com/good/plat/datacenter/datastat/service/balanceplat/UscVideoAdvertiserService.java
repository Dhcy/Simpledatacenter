package com.good.plat.datacenter.datastat.service.balanceplat;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscAdvertIncome;

/**
 * 视频广告商业务
 * @ClassName: UscVideoAdvertiserService
 * @Description: TODO
 * @author hwj
 * @date 2018-2-28 下午05:07:20
 */
public interface UscVideoAdvertiserService {
	/**
	 * 查找视频广告商
	 * @Title: selectUscVideoAdvertiserList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<UscAdvertIncome>
	 * @author hwj
	 * @date 2018-2-28 下午05:08:08
	 */
	public List<UscAdvertIncome> selectUscVideoAdvertiserList(BaseSearchData searchData)throws Exception;
	/**
	 * 添加视频广告商
	 * @Title: insertUscVideoAdvertiser
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2018-2-28 下午05:09:15
	 */
	public BaseMessage insertUscVideoAdvertiser(UscAdvertIncome searchData)throws Exception;
	/**
	 * 修改视频广告商
	 * @Title: updateByPrimaryKeySelective
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2018-2-28 下午05:09:57
	 */
	public BaseMessage updateByPrimaryKeySelective(UscAdvertIncome searchData)throws Exception;
	/**
	 * 删除视频广告商
	 * @Title: deleteByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2018-2-28 下午05:10:39
	 */
	public BaseMessage deleteByPrimaryKey(Integer id)throws Exception;
	/**
	 * 获取视频广告商
	 * @Title: getVideoAdvertiser
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * UscAdvertIncome
	 * @author hwj
	 * @date 2018-3-1 上午09:39:46
	 */
	public UscAdvertIncome getVideoAdvertiser(Integer id)throws Exception;
	/**
	 * 查询广告商名称
	 * @Title: selectAdvertiserNameList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<String>
	 * @author hwj
	 * @date 2018-3-1 下午01:09:25
	 */
	public List<String> selectAdvertiserNameList(BaseSearchData searchData)throws Exception;

}
