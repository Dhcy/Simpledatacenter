package com.good.plat.datacenter.datastat.service.impl.analysis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.WarningBaseSearchData;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningNotice;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningNoticeWithBLOBs;
import com.good.plat.datacenter.datastat.mapper.analysis.LogplatWarningNoticeMapper;
import com.good.plat.datacenter.datastat.service.analysis.IWarningNoticeService;

@Service("warningNoticeService")
public class WarningNoticeServiceImpl implements IWarningNoticeService {
	@Autowired
	LogplatWarningNoticeMapper logplatWarningNoticeMapper;
	
	@Override
	public List<LogplatWarningNoticeWithBLOBs> selectWarningNotice(WarningBaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List<LogplatWarningNoticeWithBLOBs> list = null;
		list = this.logplatWarningNoticeMapper.selectWarningNotice(searchData);
		return list;
	}

	@Override
	public List<LogplatWarningNoticeWithBLOBs> selectAllWarningNotice(int topNum) {
		List<LogplatWarningNoticeWithBLOBs> list = null;
		list = this.logplatWarningNoticeMapper.selectAllWarningNotice(topNum);
		return list;
	}

	@Override
	public BaseMessage updateReadWarningNotice(Integer id) {
		BaseMessage bm = new BaseMessage();
		if(id != null){
			LogplatWarningNoticeWithBLOBs notice = this.logplatWarningNoticeMapper.selectByPrimaryKey(id);
			if(notice != null){
				notice.setStatus(1);//
				this.logplatWarningNoticeMapper.updateByPrimaryKey(notice);
				bm.setStatus(1);
			}else{
				bm.setStatus(0);
				bm.setMessage("查询错误:没有找到对应预警通知");
			}
		}
		return bm;
	}
	@Override
	public BaseMessage saveOrUpdateWarningNotice(LogplatWarningNoticeWithBLOBs notice) {
		BaseMessage bm = new BaseMessage();
		if(notice != null){
			LogplatWarningNotice n = null;
			if(notice.getId() != null){
				n = this.logplatWarningNoticeMapper.selectByPrimaryKey(notice.getId());
			}
			if(n != null){
				this.logplatWarningNoticeMapper.updateByPrimaryKey(notice);
				bm.setStatus(1);
			}else{
				this.logplatWarningNoticeMapper.insert(notice);
				bm.setStatus(1);
			}
		}
		return bm;
	}
}
