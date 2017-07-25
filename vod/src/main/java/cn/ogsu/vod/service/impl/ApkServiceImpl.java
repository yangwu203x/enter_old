package cn.ogsu.vod.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.IApkService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * apk管理的service实现层
 * @author albert
 * @time 2016年9月10日
 */
@Service
public  class ApkServiceImpl extends ServiceBase implements IApkService {
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> findPageApk(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList("ApkMapper.listPageApk",page);
	}
	@Override
	public boolean addApkHistory(PageData pd) throws Exception {
		//获取文件夹目录
		String filePath=pd.getString("filePath");
		String apkPath=filePath+pd.getString("apkName");
		//保存文件下载的路径
		pd.put("apkPath",apkPath);
		pd.put("apkDownPath", "/api/apk/updateApk.do");
		return ((int)daoSupport.save("ApkMapper.addApk", pd))>0;
	}
	@Override
	public boolean deleteApk(PageData pd) throws Exception {
		String[] apkNos=pd.getString("apkNo").split(",");
		return ((int)daoSupport.delete("ApkMapper.deleteApks",apkNos))>0;
	}
}