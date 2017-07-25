package cn.ogsu.vod.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
/**
 * 封装的map
 * @author albert
 *
 * @time 2016年9月7日
 */
public class PageData extends HashMap{
	
	private static final long serialVersionUID = -1891125652364426419L;

	Map map = null;
	
	HttpServletRequest request;
	public PageData(HttpServletRequest request){
		//获取请求参数的集合
		Map<String, String[]>  parameterMap = request.getParameterMap();
		//封装请求参数的集合，将数组类型的请求参数以逗号相连接
		Map<String, String> requestMap = new HashMap<String, String>(); 
		//保存数据的键值对
		String key = "";  
		String value = "";
		for (Entry<String, String[]> entry : parameterMap.entrySet()) {
			//获取请求参数的键值对
			key=entry.getKey();
			Object entryValue=entry.getValue();
			//转换数据以字符串的形式放入map
			if(null ==entryValue){
				requestMap.put(key,value);
				continue;
			}
			if(entryValue instanceof String[] ){
				//将字符串数组转换为以逗号相隔的字符串,通过Arrays.toString(Object[] o)方法得到的返回结果[aa ,bb ,cc ]
				value=Arrays.toString((String[])entryValue);
				value=value.replace("[","").replace("]", "").replace(" ","");
			}else{
				value=entryValue.toString();
			}
			//保存数据
			requestMap.put(key,value);
		}
		map=requestMap;
	}
	
	public PageData() {
		map = new HashMap();
	}
	
	@Override
	public Object get(Object key) {
		Object obj = null;
		if(map.get(key) instanceof Object[]) {
			Object[] arr = (Object[])map.get(key);
			obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	
	public String getString(Object key) {
		Object value=get(key);
		return value==null?"":value.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}
	
	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}
	
}
