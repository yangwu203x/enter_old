package cn.ogsu.api.service;

import cn.ogsu.api.util.PageData;

/**
 * 产品的service接口
 *
 * @author albert
 * @time 2016年10月8日
 */
public interface IProductService {

	/**
	 * 增加第一次开机的时间
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	boolean addFirstTime(PageData pd) throws Exception;
	
	/**
	 * 获取有效天数
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	int findFirstTime(PageData pd) throws Exception;
}
