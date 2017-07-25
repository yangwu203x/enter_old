package cn.ogsu.vod.service;
import java.util.List;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 *歌曲类别的service接口
 * @author albert
 * @time 2016年9月20日
 */
public interface ISongTypeService {
	/**
	 * 分页获取歌曲类型信息
	 */
	List<PageData> songTypeList(Page page) throws Exception;
	/**
	 * 根据编号获取歌曲类型信息
	 */
	PageData singleSongType(String typeId) throws Exception;
	/**
	 * 获取所有的歌曲类型信息
	 */
	List<PageData> allType()throws Exception;
	/**
	 * 修改歌曲类型信息
	 */
	boolean updateSongType(PageData songType) throws Exception;
	/**
	 * 增加歌曲类型信息
	 */
	boolean addSongType(PageData songType) throws Exception;
	/**
	 * 修改审核状态
	 */
	boolean updateStatus(PageData pd) throws Exception;
}