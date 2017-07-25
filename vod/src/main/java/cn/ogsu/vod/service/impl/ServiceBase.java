package cn.ogsu.vod.service.impl;
import javax.annotation.Resource;
import cn.ogsu.vod.dao.DaoSupport;
/**
 * @author albert
 * @time 2016年9月5日
 */
public class ServiceBase{
	/**
	 * 在父类中注入dao操作类
	 */
	@Resource
	protected DaoSupport daoSupport;
}
