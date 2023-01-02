package cn.int1024.cat.common.util;

import cn.int1024.cat.enums.ResultCode;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

/**
 * @Description: 结果集
 * @Author: 双料特工·钏钐钾
 * @Date: 2022/10/19 16:40:00
 * @Version: 1.0
 */
public class Result<T> {
	/**
	 * 状态码
	 */
	@Getter
	private final int code;

	/**
	 * 返回的信息
	 */
	@Getter
	private final String message;

	/**
	 * 返回的数据
	 */
	@Getter
	private T data;

	/**
	 * 成功时候的调用（有数据）
	 */
	public static <T> Result<T> success(T data){
		return new Result<>(data);
	}

	/**
	 * 成功时候的调用（无数据）
	 */
	public static <T> Result<T> success(){
		return new Result<>();
	}

	/**
	 * 异常时候的调用（有msg参数）
	 */
	public static <T> Result<T> error(String msg){
		return new Result<>(msg);
	}

	/**
	 * 异常时候的调用（无msg参数）
	 */
	public static <T> Result<T> error(){
		return new Result<>("error");
	}

	public static <T> Result<T> error(int code, String msg){
		return new Result<>(code, msg);
	}

	public static <T> Result<T> error(int code, String msg, T data){
		return new Result<>(code, msg, data);
	}

	public static <T> Result<T> noPermission(T data) {
		return new Result<>(ResultCode.NO_PERMISSION.getCode(), ResultCode.NO_PERMISSION.getMsg(), data);
	}

	private Result(T data) {
		this.code = ResultCode.SUCCESS.getCode();
		this.message = ResultCode.SUCCESS.getMsg();
		this.data = data;
	}

	private Result() {
		this.code = ResultCode.SUCCESS.getCode();
		this.message = ResultCode.SUCCESS.getMsg();
	}

	private Result(String msg) {
		this.code = ResultCode.ERROR.getCode();
		this.message = msg;
	}

	private Result(int code, String msg) {
		this.code = code;
		this.message = msg;
	}

	private Result(int code, String msg, T data) {
		this.code = code;
		this.message = msg;
		this.data = data;
	}

	private Result(ResultCode code) {
		this.code = code.getCode();
		this.message = code.getMsg();
	}

	public JSON toJSON() {
		JSONObject object = new JSONObject();
		object.put("code", code);
		object.put("message", message);
		object.put("data", data);
		return object;
	}
}
