package cn.ogsu.api.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.ogsu.api.util.Base64;
import cn.ogsu.api.util.PageData;

/**
 * controller层的辅助层
 * 
 * @author albert
 *
 * @time 2016年9月5日
 */
public class ControllerBase {

	/**
	 * 解析一组列表形式的json格式的字符串
	 */
	@SuppressWarnings("rawtypes")
	protected List<PageData> getRequestListMap() throws Exception {
		// 获取请求的request对象
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		// 获取请求携带的json数据
		String jsonStr = request.getParameter("requestData");
		jsonStr = new String(Base64.decode(jsonStr), "utf-8");

		// json格式的字符串转换为对象
		Object json = new JSONTokener(jsonStr).nextValue();

		// 实例化封装数据的对象
		List<PageData> requestListMap = new ArrayList<PageData>();

		if (json instanceof JSONArray) {
			// 将请求携带的json数据转换为JSONArray对象
			JSONArray jsonArray = (JSONArray) json;

			PageData pd = null;
			// 开始封装数据
			Iterator it = jsonArray.iterator();
			while (it.hasNext()) {
				// 将JSONArray的单个{}转换为JSONObject对象
				JSONObject jsonObject = (JSONObject) it.next();
				// 调用方法解析因此而产生的JSONObject对象
				pd = resolveJsonObject(jsonObject);
				// 将对象放入集合中
				requestListMap.add(pd);
			}
			return requestListMap;
		}

		if (json instanceof JSONObject) {
			// 将请求携带的json数据转换为JSONObject对象
			JSONObject jsonObject = (JSONObject) json;
			// 调用方法解析因此而产生的JSONObject对象
			PageData pd = resolveJsonObject(jsonObject);
			// 将对象放入集合中
			requestListMap.add(pd);
		}
		return requestListMap;
	}

	/**
	 * 解析一个对象形式的json字符串
	 * 
	 * @return
	 */
	protected PageData getRequestMap() throws Exception {
		// 获取请求的request对象
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		// 获取请求携带的json数据
		String jsonStr = request.getParameter("requestData");
		System.err.println("jsonStr:" + jsonStr);
		jsonStr = new String(Base64.decode(jsonStr), "utf-8");
		System.err.println("jsonStr:" + jsonStr);
		// json格式的字符串转换为对象
		Object json = new JSONTokener(jsonStr).nextValue();
		PageData pd = new PageData();

		if (json instanceof JSONObject) {
			// 将请求携带的json数据转换为JSONObject对象
			JSONObject jsonObject = (JSONObject) json;
			// 调用方法解析因此而产生的JSONObject对象
			pd = resolveJsonObject(jsonObject);
		}
		if (json instanceof String) {
			System.err.println("json:" + json);
			JSONObject jsonObject = JSONObject.fromObject(json);
			pd = resolveJsonObject(jsonObject);
		}
		return pd;
	}

	/**
	 * 获取请求的json
	 * @return
	 */
	public String getJson() {
		try {
			// 获取请求的request对象
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			// 获取请求携带的json数据
			String jsonStr = request.getParameter("requestData");
			jsonStr = new String(Base64.decode(jsonStr), "utf-8");
			return jsonStr;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析JSONObject对象的数据放入map中
	 * 
	 * @param jsonObject
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public PageData resolveJsonObject(JSONObject jsonObject) {
		PageData pd = new PageData();
		// 开始解析
		String key = "";
		String value = "";
		for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
			// 获取键值对数据
			key = iter.next().toString();
			value = jsonObject.get(key).toString();
			pd.put(key, value);
		}
		return pd;
	}
}
