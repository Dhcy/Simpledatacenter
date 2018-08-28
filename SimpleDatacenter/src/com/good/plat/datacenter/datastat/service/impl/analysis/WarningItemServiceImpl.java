package com.good.plat.datacenter.datastat.service.impl.analysis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningItem;
import com.good.plat.datacenter.datastat.mapper.analysis.LogplatWarningItemMapper;
import com.good.plat.datacenter.datastat.service.analysis.IWarningItemService;

@Service(value="warningItemService")
public class WarningItemServiceImpl implements IWarningItemService{
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LogplatWarningItemMapper logplatWarningItemMapper;
	
	@Override
	public BaseMessage add(LogplatWarningItem item) {
		BaseMessage bm = new BaseMessage();
		if(item.getId() != null){
			LogplatWarningItem it = this.logplatWarningItemMapper.selectByPrimaryKey(item.getId());
			if(it != null){
				bm.setStatus(0);
				bm.setMessage("添加失败:已有相同id的警告条目");
				return bm;
			}
		}
		Integer result = this.logplatWarningItemMapper.insert(item);
		logger.debug("LogplatWarningItem id : {},result={}",item.getId(),result);
		bm.setStatus(1);
		return bm;
	}

	@Override
	public List<LogplatWarningItem> selectItems(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		List<LogplatWarningItem> list = null;
		list = this.logplatWarningItemMapper.selectItems(searchData);
		return list;
	}

	@Override
	public BaseMessage update(LogplatWarningItem item) {
		BaseMessage bm = new BaseMessage();
		if(item.getId() != null){
			LogplatWarningItem it = this.logplatWarningItemMapper.selectByPrimaryKey(item.getId());
			if(it == null){
				bm.setStatus(0);
				bm.setMessage("更新失败:无此id的警告条目");
				return bm;
			}else{
				this.logplatWarningItemMapper.updateByPrimaryKeySelective(item);
				bm.setStatus(1);
			}
		}
		return bm;
	}

	@Override
	public BaseMessage deleteByPrimaryKey(Integer id) {
		BaseMessage bm = new BaseMessage();
		if(id != null){
			LogplatWarningItem it = this.logplatWarningItemMapper.selectByPrimaryKey(id);
			if(it == null){
				bm.setStatus(0);
				bm.setMessage("更新失败:无此id的警告条目");
				return bm;
			}else{
				this.logplatWarningItemMapper.deleteByPrimaryKey(id);
				bm.setStatus(1);
			}
		}
		return bm;
	}
	@Override
	public List<LogplatWarningItem> selectItemsByEventID(Integer id) {
		return logplatWarningItemMapper.selectItemsByEventID(id);
	}

	@Override
	public LogplatWarningItem selectItemsByPrimaryID(Integer id) {
		return this.logplatWarningItemMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<LogplatWarningItem> selectAllItems(BaseSearchData searchData) {
		return this.logplatWarningItemMapper.selectAllItems(searchData);
	}
}
