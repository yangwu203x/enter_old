package cn.ogsu.api.service.impl;

import org.springframework.stereotype.Service;
import cn.ogsu.api.service.IApkService;
import cn.ogsu.api.util.PageData;
/**
 * apk更新相关任务处理的service实现层
 * @author albert
 * @time 2016年9月13日
 */
@Service
public class ApkServiceImpl extends ServiceBase implements IApkService{

	@Override
	public PageData findLastApk() throws Exception {
		
		return (PageData) daoSupport.findForObject("ApkMapper.findLastApk", null);
	}

}
