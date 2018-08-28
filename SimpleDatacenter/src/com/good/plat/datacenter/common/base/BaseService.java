package com.good.plat.datacenter.common.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.good.plat.datacenter.common.redis.service.impl.RedisServiceImpl;

/**
 * @ClassName: BaseService
 * @Description: BaseService
 * @author dmw
 * @date 2016年1月11日 下午1:41:19
 */
public abstract class BaseService {

	public BaseService() {
	}
	
	@Autowired
	private RedisServiceImpl redisService;

	public RedisServiceImpl getRedisService() {
		return redisService;
	}

	public void setRedisService(RedisServiceImpl redisService) {
		this.redisService = redisService;
	}
	
}
