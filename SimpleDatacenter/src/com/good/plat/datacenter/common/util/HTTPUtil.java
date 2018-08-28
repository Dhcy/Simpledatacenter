package com.good.plat.datacenter.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.CharBuffer;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;

import com.good.plat.datacenter.common.base.EmailPlatTool;
import com.good.plat.datacenter.game.entity.Channel;

public class HTTPUtil {
	static Logger logger = LoggerFactory.getLogger(HTTPUtil.class);

	/**
	 * ref JsonPostRequest.java
	 * 有参数的HTTP POST请求
	 * 
	 * @param urlPath
	 *            HTTP接口
	 * @param obj
	 *            请求参数为JSON对象
	 * @param connectTimeout
	 * millis
	 * @param readTimeout
	 * millis
	 * @return
	 * @author moxw
	 * @throws Exception
	 * @since 2015-08-13
	 */
	public static String post(String urlPath, String content, int connectTimeout, int readTimeout) throws Exception {
		logger.info("{}", urlPath);
		logger.info("{}", content);
		String retData = null;
		try {
			// 创建连接
			URL url = new URL(urlPath);
			HttpURLConnection connection = null;

			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			connection.setConnectTimeout(connectTimeout);// http链接建立超时时间
			connection.setReadTimeout(readTimeout);// http链接读取数据超时时间

			connection.connect();
			
			// POST请求
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			// out.writeBytes(content.toString());
			out.write(content.getBytes("utf-8"));
			// System.out.println(out.size());
			out.flush();
			out.close();
			// 读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null) {
				// lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			retData = sb.toString();
			reader.close();
			// 断开连接
			connection.disconnect();
		} catch (Exception e) {
			throw e;
		}
		return retData;
	}

	/**
	 * 
	 * @Title: responseFile
	 * @Description: 下载
	 * @param response
	 * @param request
	 * @param contentType
	 * @param filename
	 * @param baos
	 * @throws IOException
	 *             void
	 * @author moxw
	 * @date 2016年7月29日 下午6:16:45
	 */
	public static void responseFile(HttpServletResponse response, HttpServletRequest request, String contentType,
			String filename, ByteArrayOutputStream baos) throws IOException {
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Disposition", "attachment;filename=" + buildAttachmentFileName(request, filename));
		response.setHeader("Content-Length", baos.size() + "");
		baos.writeTo(response.getOutputStream());
	}

	/**
	 * 1. array 类型: instanceof , Array.isArray 2.
	 * 
	 * @Title: parseRequestParameters
	 * @Description: TODO
	 * @param request
	 * @param cls
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 *             Object
	 * @author dmw
	 * @throws ParseException
	 * @date 2016年7月27日 下午2:31:32
	 */
	public static Object parseRequestParameters(HttpServletRequest request, Class cls)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ParseException {
		Object newInstance = cls.newInstance();
		Field fields[] = ReflectUtil.getDeclaredFields(cls);
		fields = fields == null ? new Field[0] : fields;
		for (Field f : fields) {
			String fn = f.getName();
			if (isNull(request.getParameterValues(fn)) || isNull(request.getParameter(fn))) {
				continue;
			}
			String[] vals = request.getParameterValues(fn);
			String val = request.getParameter(fn);
			if (f.getType().isArray()) {
				f.getType().getComponentType();
				if (!isNull(vals)) {
					Object array = Array.newInstance(f.getType().getComponentType(), vals.length);
					Array.set(array, 0, vals.length);
					ReflectUtil.invokeMethodOnInstance(newInstance,
							ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()), array);
				}
			} else if (Collection.class.isAssignableFrom(f.getType())) {// List,Set
				if (!isNull(vals)) {
					Object array[] = cast2TargetType(vals, ReflectUtil.detectCollectFieldComponentType(cls, f), f);
					List list = new ArrayList();
					for (int i = 0; i < array.length; ++i) {
						list.add(array[i]);
					}
					// Arrays.asList(array)
					if (List.class.isAssignableFrom(f.getType())) {
						ReflectUtil.invokeMethodOnInstance(newInstance,
								ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()),
								list);
					} else if (Set.class.isAssignableFrom(f.getType())) {
						ReflectUtil.invokeMethodOnInstance(newInstance,
								ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()),
								new HashSet(list));
					}
				}
			} else if (Map.class.isAssignableFrom(f.getType())) {

			} else {
				if (!isNull(val)) {
					setValue(f, cls, newInstance, val);
				}
			}
		}
		return newInstance;
	}

	public static Object[] cast2TargetType(String[] vals, Class targetType, Field f)
			throws ArrayIndexOutOfBoundsException, IllegalArgumentException, ParseException {
		Object array = null;
		array = Array.newInstance(targetType, vals.length);
		for (int i = 0; i < vals.length; ++i) {
			String val = vals[i];
			if (targetType == Boolean.class) {
				Array.set(array, i, Byte.valueOf(val));
			} else if (targetType == Byte.class) {
				Array.set(array, i, Byte.valueOf(val));
			} else if (targetType == Short.class) {
				Array.set(array, i, Short.valueOf(val));
			} else if (targetType == Character.class) {
				Array.set(array, i, val.charAt(0));
			} else if (targetType == Integer.class) {
				Array.set(array, i, Integer.valueOf(val));
			} else if (targetType == Float.class) {
				Array.set(array, i, Float.valueOf(val));
			} else if (targetType == Long.class) {
				Array.set(array, i, Long.valueOf(val));
			} else if (targetType == Double.class) {
				Array.set(array, i, Double.valueOf(val));
			} else if (targetType == BigInteger.class) {
				Array.set(array, i, BigInteger.valueOf(Long.valueOf(val)));
			} else if (targetType == BigDecimal.class) {
				Array.set(array, i, BigDecimal.valueOf(Double.valueOf(val)));
			} else if (targetType == String.class) {
				Array.set(array, i, val);
			} else if (targetType == List.class) {

			} else if (targetType == Set.class) {

			} else if (targetType == Map.class) {

			} else if (targetType == Date.class) {
				Array.set(array, i, DateUtil.parsetDate_yyyy_MM_dd_HH_mm_ss(val));
			} else if (targetType == Calendar.class) {

			} else {
				// 自定义类型
				if (targetType == Channel.class) {
					Channel cn = new Channel();
					cn.setId(Integer.valueOf(val));
					Array.set(array, i, cn);
				}
			}
		}
		return (Object[]) array;
	}

	public static boolean isNull(Object obj) {
		return obj == null;
	}

	public static void setValue(Field f, Class cls, Object newInstance, String val)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, ParseException {
		String fn = f.getName();
		if (f.getType() == Boolean.class) {
			/* f.set(newInstance, Boolean.valueOf(val)); */
			ReflectUtil.invokeMethodOnInstance(newInstance,
					ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()),
					Boolean.valueOf(val));
		} else if (f.getType() == Byte.class) {
			ReflectUtil.invokeMethodOnInstance(newInstance,
					ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()),
					Byte.valueOf(val));
		} else if (f.getType() == Short.class) {
			ReflectUtil.invokeMethodOnInstance(newInstance,
					ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()),
					Short.valueOf(val));
		} else if (f.getType() == Character.class) {
			ReflectUtil.invokeMethodOnInstance(newInstance,
					ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()), val.charAt(0));
		} else if (f.getType() == Integer.class) {
			ReflectUtil.invokeMethodOnInstance(newInstance,
					ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()),
					Integer.valueOf(val));
		} else if (f.getType() == Float.class) {
			ReflectUtil.invokeMethodOnInstance(newInstance,
					ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()),
					Float.valueOf(val));
		} else if (f.getType() == Long.class) {
			ReflectUtil.invokeMethodOnInstance(newInstance,
					ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()),
					Long.valueOf(val));
		} else if (f.getType() == Double.class) {
			ReflectUtil.invokeMethodOnInstance(newInstance,
					ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()),
					Double.valueOf(val));
		} else if (f.getType() == BigInteger.class) {
			ReflectUtil.invokeMethodOnInstance(newInstance,
					ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()),
					BigInteger.valueOf(Long.valueOf(val)));
		} else if (f.getType() == BigDecimal.class) {
			ReflectUtil.invokeMethodOnInstance(newInstance,
					ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()),
					BigDecimal.valueOf(Double.valueOf(val)));
		} else if (f.getType() == String.class) {
			ReflectUtil.invokeMethodOnInstance(newInstance,
					ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()), val);
		} else if (f.getType() == List.class) {

		} else if (f.getType() == Set.class) {

		} else if (f.getType() == Map.class) {

		} else if (f.getType() == Date.class) {
			ReflectUtil.invokeMethodOnInstance(newInstance,
					ReflectUtil.getDeclaredMethod(cls, ReflectUtil.toSetterMethodName(fn), f.getType()),
					DateUtil.parsetDate_yyyy_MM_dd_HH_mm_ss(val));
		} else if (f.getType() == Calendar.class) {

		}
	}

	/**
	 * // ======================================================================
	 * //
	 * {"gameid":"58","startdate":"2016-7-20","enddate":"2016-7-27","termtypes":
	 * [1,2,3],"areas":[],"channelList":[]} //
	 * ====================================================================== //
	 * hello=1 //
	 * ======================================================================
	 * System.out.println(
	 * "======================================================================")
	 * ; System.out.println(HTTPUtil.parseRequestPostBody(request));
	 * System.out.println(
	 * "======================================================================")
	 * ; System.out.println(HTTPUtil.parseRequestQueryString(request));
	 * System.out.println(
	 * "======================================================================")
	 * ;
	 * 
	 * @Title: parseRequestPostBody
	 * @Description: TODO
	 * @param request
	 * @return
	 * @throws IOException
	 *             StringBuffer
	 * @author dmw
	 * @date 2016年7月27日 下午2:07:31
	 */
	public static StringBuffer parseRequestPostBody(HttpServletRequest request) throws IOException {
		InputStream in = request.getInputStream();
		// DataInputStream dis = new DataInputStream(in);//read primitive Java
		// data types from an underlying input stream in a machine-independent
		// way.
		InputStreamReader reader = new InputStreamReader(in);
		CharBuffer cbuf = CharBuffer.allocate(2048);
		StringBuffer sb = new StringBuffer();
		while (reader.read(cbuf) != -1) {
			cbuf.flip();
			sb.append(cbuf.toString());
			cbuf.clear();
		}
		return sb;
	}

	public static String parseRequestQueryString(HttpServletRequest request) {
		return request.getQueryString();
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getRequestURL(HttpServletRequest request) {
		return request.getRequestURI();
	}

	public static String buildAttachmentFileName(HttpServletRequest request, String filename)
			throws UnsupportedEncodingException {
		String agent = request.getHeader("User-Agent");
		boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
		if (isMSIE) {
			filename = URLEncoder.encode(filename, "UTF-8");
		} else {
			filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
		}
		return filename;
	}
}
