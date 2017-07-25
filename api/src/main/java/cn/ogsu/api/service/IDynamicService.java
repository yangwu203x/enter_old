package cn.ogsu.api.service;

import cn.ogsu.api.requestBean.RequestUserDynamic;
import cn.ogsu.api.responseBean.ResponseDynamic;

/**
 * 动态查询
 * @author Administrator
 *
 */
public interface IDynamicService {

	public ResponseDynamic queryDynamic(RequestUserDynamic request) throws Exception;
	
}
