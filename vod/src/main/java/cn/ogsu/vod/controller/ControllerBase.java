package cn.ogsu.vod.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import cn.ogsu.vod.util.PageData;
import cn.ogsu.vod.util.Tools;
/**
 * controller层的辅助层
 * @author albert
 * @time 2016年9月5日
 */
public class ControllerBase{
	/**
	 * 获取请求数据,以键值对的形式放入map
	 */
	protected PageData getPageData(){
		//获取请求的request对象
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		PageData pd=new PageData(request);
		return pd;
	}
	/**
	 * 获取含有ip地址的请求数据,以键值对的形式放入map
	 */
	protected PageData getPageDataConIp(){
		//获取请求的request对象
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		PageData pd=new PageData(request);
		pd.put("lastLoginIp",Tools.getRemoteIp(request));
		return pd;
	}
}