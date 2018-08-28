package com.good.plat.datacenter.common.Exception;
/**
 * 输入校验异常
 * @ClassName: InputValidException
 * @Description: TODO
 * @author hwj
 * @date 2017-5-11 上午09:32:36
 */
public class InputValidException extends RuntimeException {
	public InputValidException(){
		super();
	}
	public InputValidException(String message){
		super(message);
	}

}
