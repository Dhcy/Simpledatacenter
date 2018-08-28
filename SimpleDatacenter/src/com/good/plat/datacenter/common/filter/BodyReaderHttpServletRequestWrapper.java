package com.good.plat.datacenter.common.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class BodyReaderHttpServletRequestWrapper extends
		HttpServletRequestWrapper {
	private final byte[] body;

	public BodyReaderHttpServletRequestWrapper(HttpServletRequest request)
			throws IOException {
		super(request);
		
		body = getRequestPostBytes(request);
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(body);
		return new ServletInputStream() {

			@Override
			public int read() throws IOException {
				return bais.read();
			}
		};
	}

	/**
	 * 描述:获取 post 请求的 byte[] 数组
	 * <pre>
	 * 举例：
	 * </pre>
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static byte[] getRequestPostBytes(HttpServletRequest request)
			throws IOException {
		int contentLength = request.getContentLength();
		if (contentLength < 0) {
			//return null;
			return new byte[0];
		}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength;) {

			int readlen = request.getInputStream().read(buffer, i,
					contentLength - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		return buffer;
	}


}
