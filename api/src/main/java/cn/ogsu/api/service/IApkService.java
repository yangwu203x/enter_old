package cn.ogsu.api.service;


import cn.ogsu.api.util.PageData;
/**
 * apk更新相关处理的service层接口
 * @author albert
 * @time 2016年9月13日
 */
public interface IApkService {

	/**
	 * 获取最新的apk信息
	 * @return
	 * @throws Exception
	 */
	public PageData findLastApk() throws Exception;
	
	
	
}
