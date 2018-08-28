package com.good.plat.datacenter.common.redis.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.redis.service.RedisService;

/**
 * @ClassName: RedisServiceImpl
 * @Description: redis操作
 * @author dmw
 * @date 2016年1月7日 下午5:06:30
 */
@Service(value = "redisService")
public class RedisServiceImpl implements RedisService {

	@SuppressWarnings("unused")
	private static final Long LIVE_TIME = 86400L;	//24*60*60 缓存一天
	private static String redisCode = "utf-8";
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

	public RedisServiceImpl() {
	}

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	
	/**
	 * 通过key删除
	 */
	@Override
	public long del(final String... keys) {
		return (Long) redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				long result = 0;
				for (int i = 0; i < keys.length; i++) {
					result = connection.del(keys[i].getBytes());
				}
				return result;
			}
		});
	}

	
	/**
	 * @param key
	 * @param value
	 * @param liveTime 单位： 秒
	 */
	@Override
	public void set(final byte[] key, final byte[] value, final long liveTime) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(key, value);
				if (liveTime > 0) {
					connection.expire(key, liveTime);
				}
				return 1L;
			}
		});
	}

	/**
	 * @param key
	 * @param value
	 * @param liveTime 单位： 秒
	 */
	@Override
	public void set(String key, String value, long liveTime) {
		this.set(key.getBytes(), value.getBytes(), liveTime);
	}

	/**
	 * @param key
	 * @param value
	 */
	@Override
	public void set(String key, String value) {
		this.set(key, value, 0L);
	}

	/**
	 * @param key
	 * @param value
	 */
	@Override
	public void set(byte[] key, byte[] value) {
		this.set(key, value, 0L);
	}

	/**
	 * @param key
	 * @return
	 */
	@Override
	public String get(final String key) {
		return (String) redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				try {
					if (connection.get(key.getBytes()) != null) {
						return new String(connection.get(key.getBytes()), redisCode);
					} else {
						return null;
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return null;
				}
			}
		});
	}
	
	
	/**
	 * @param key
	 * @return
	 */
	public byte[] getBytes(final String key) {
		return (byte[]) redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public byte[] doInRedis(RedisConnection connection)
					throws DataAccessException {
				try {
					if (connection.get(key.getBytes()) != null) {
						return connection.get(key.getBytes());
					} else {
						return null;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
	}
	
	

	/**
	 * @param pattern
	 * @return
	 */
	@Override
	public Set<Object> keys(String pattern) {
		return redisTemplate.keys(pattern);

	}

	/**
	 * @param key
	 * @return
	 */
	@Override
	public boolean exists(final String key) {
		return (Boolean) redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.exists(key.getBytes());
			}
		});
	}

	/**
	 * @return
	 */
	@Override
	public String flushDB() {
		return (String) redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.flushDb();
				return "ok";
			}
		});
	}

	/**
	 * @return
	 */
	@Override
	public long dbSize() {
		return (Long) redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.dbSize();
			}
		});
	}

	/**
	 * @return
	 */
	@Override
	public String ping() {
		return (String) redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.ping();
			}
		});
	}
	
	
	/**
	 * @Title: objectToByteArray
	 * @Description: pojo转byte数组
	 * @param obj
	 * @throws IOException
	 * byte[]
	 * @author dmw
	 * @date 2016年1月11日 下午2:27:17
	 */
	public byte[] objectToByteArray(Object obj) throws IOException {
		
		ByteArrayOutputStream bos =  new ByteArrayOutputStream();
        ObjectOutputStream oos =  new ObjectOutputStream(bos);
        
        oos.writeObject(obj);
        byte[] byteArray = bos.toByteArray();
        oos.close();
        bos.close();
        
        return byteArray;
	}

	
	/**
	 * @Title: byteArrayToObject
	 * @Description: byte数组转pojo
	 * @param byteArray
	 * @throws Exception
	 * Object
	 * @author dmw
	 * @date 2016年1月11日 下午2:27:59
	 */
	public Object byteArrayToObject(byte[] byteArray) throws Exception {
		ByteArrayInputStream bis =  new ByteArrayInputStream(byteArray);
        ObjectInputStream inputStream =  new ObjectInputStream(bis);
        
        Object obj = inputStream.readObject();
        
        inputStream.close();
        bis.close();
        
        return obj;
	}
	
}
