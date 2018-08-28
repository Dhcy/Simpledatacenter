package com.good.plat.datacenter.common.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName: BaseController
 * @Description: 统一异常处理
 * @author dmw
 * @date 2016年2月24日 下午5:17:28
 */
public abstract class BaseController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler
	public String exception(HttpServletRequest request, 
			HttpServletResponse response, Exception e) {

		logger.info(e.getMessage());
		// 添加自己的异常处理逻辑，如日志记录　　　
		if (request.getHeader("accept").indexOf("application/json") > -1
				|| (request.getHeader("X-Requested-With") != null && request
						.getHeader("X-Requested-With")
						.indexOf("XMLHttpRequest") > -1)) {
			response.setStatus(500);
			response.setContentType("application/json;charset=utf-8");
			try {
				PrintWriter writer = response.getWriter();
				// 根据不同的异常类型可以返回不同界面
				String message = "";
				if (e instanceof HttpMessageNotReadableException) {
					message = "参数输入有误，请检查输入";
				} else {
					message = "系统出现错误，请联系技术人员";
				}
				writer.write("{\"status\":500,\"message\":\""+message+"\"}");
				writer.flush();
			} catch (IOException e1) {
				logger.debug(e1.getMessage());
				//e1.printStackTrace();
			}
			
			return null;
		} else {// 如果是普通请求
			response.setStatus(500);
			response.setContentType("application/json;charset=utf-8");
			try {
				PrintWriter writer = response.getWriter();
				// 根据不同的异常类型可以返回不同界面
				String message = "";
				if (e instanceof HttpMessageNotReadableException) {
					message = "参数输入有误，请检查输入";
				} else {
					message = "参数有误的非法请求";
				}
				writer.write("{\"status\":500,\"message\":\""+message+"\"}");
				writer.flush();
			} catch (IOException e1) {
				logger.debug(e1.getMessage());
				//e1.printStackTrace();
			}
			
			//request.setAttribute("exceptionMessage", e.getMessage());
			
		}

		return e.getMessage();
	}
}
