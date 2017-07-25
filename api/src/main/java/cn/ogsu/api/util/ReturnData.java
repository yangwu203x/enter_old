package cn.ogsu.api.util;
/**
 * 封装的返回数据
 * @author albert
 *
 * @time 2016年9月5日
 */
public class ReturnData<T> {

	/**
	 * 用来表示成功或者失败的访问结果
	 */
	private int flag;
	
	/**
	 * 失败的原因
	 */
	private String msg;
	
	/**
	 * 请求成功的数据,T代表返回的数据类型
	 */
	private T data;
	
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "flag:" + flag + "||msg:" + msg + "||data:" + data;
	}

	public ReturnData(int flag,String msg){
		this.flag=flag;
		this.msg=msg;
		data=null;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
