package cn.ogsu.vod.service;
import java.util.List;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * apk管理的service接口
 * @author albert
 * @time 2016年9月10日
 */
public interface IApkService {
	/**
	 * 分页获取apk版本信息
	 */
	public List<PageData> findPageApk(Page page) throws Exception;
	/**
	 * 上传apk文件信息,以及增加apk信息
	 */
	public boolean addApkHistory(PageData pd) throws Exception;
	/**
	 * 删除apk
	 */
	boolean deleteApk(PageData pd) throws Exception; 
}