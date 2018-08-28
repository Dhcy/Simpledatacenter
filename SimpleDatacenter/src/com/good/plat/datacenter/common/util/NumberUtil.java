package com.good.plat.datacenter.common.util;

import java.math.BigDecimal;

import com.good.plat.datacenter.common.base.Validator;

public class NumberUtil {
	public static int DEFAULT_SCALE = 2;
	public static Double toFix(Double d,int scale){
		if(scale < 0){
			return null;
		}
		return new BigDecimal(d).setScale(scale).doubleValue();
	}
	private static final int DEF_DIV_SCALE = 2;

	/**
	 * 判断字符串是否为数字
	 * @param num
	 * @return
	 */
	public static boolean isNumberic(String num) {
		return (null == num || num.length() <= 0 || num.matches("\\d{1,}")) ? true
				: false;
	}
	
	public static Double multi(Double n,Double m,int scale){
		return NumberUtil.mul((n == null ? 0.0 : n), (m == null ? 0.0 : m), scale);
	}
	
	public static Double multi100(Double n,int scale){
		return NumberUtil.mul((n == null ? 0.0 : n), 100.0, scale);
	}
	
	public static Double ajustScale(Double n,int scale){
		return n == null ? null : NumberUtil.mul(n, 1.0, scale);
	}

	/**
	 * * 两个Double数相加 *
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double add(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b3 = new BigDecimal(b1.add(b2).doubleValue());
		return b3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	/**
	 * @Title: longAdd 
	 * @Description: 两个Long相加
	 * @param lo1
	 * @param lo2
	 * @date 2015年9月28日 下午2:52:32 
	 * @author dmw
	 */
	public static Long longAdd(Long lo1, Long lo2) {
		BigDecimal b1 = new BigDecimal(lo1.toString());
		BigDecimal b2 = new BigDecimal(lo2.toString());
		BigDecimal b3 = new BigDecimal(b1.add(b2).doubleValue());
		return b3.longValue();
	}

	/**
	 * * 两个Double数相减 *
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double sub(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b3 = new BigDecimal(b1.subtract(b2).doubleValue());
		return b3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	
	/**
	 * * 两个Double数相乘 保留两位小数*
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double mul(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b3 = new BigDecimal(b1.multiply(b2).doubleValue());
		return b3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static Double mul(Double v1, Double v2,int scale) {
		if(scale < 0){
			return null;
		}
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b3 = new BigDecimal(b1.multiply(b2).doubleValue());
		return b3.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	/**
	 * * 两个Double数相除 *
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double div(Double v1, Double v2) {
		if (v2 == 0.0) {
			return 0.0;
		}
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	

	/**
	 * * 两个Double数相除，并保留scale位小数 *
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return Double
	 */
	public static Double div(Double v1, Double v2, int scale) {
		if (v2 == 0.0) {
			return 0.0;
		}
		if (scale < 0) {
			throw new IllegalArgumentException (
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
		Double d = b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}

	/**
	 * @param v1
	 * @return 返回指定Double的负数
	 */
	public static Double neg(Double v1) {
		return sub(new Double(0), v1);
	}

	public static BigDecimal ajustScale(BigDecimal n, int scale) {
		return n == null ? null : new BigDecimal(ajustScale(n.doubleValue(), scale));
	}
	
}
