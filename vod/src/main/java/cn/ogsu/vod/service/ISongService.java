package cn.ogsu.vod.service;

import java.util.List;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 歌曲管理的service接口
 * @author albert
 * @time 2016年9月10日
 */
public interface ISongService {
	/**
	 * 分页获取歌曲列表信息
	 */
	List<PageData> songList(Page page) throws Exception;
	/**
	 * 获取单个歌曲信息
	 */
	PageData findSingleSong(String song_id) throws Exception;
	/**
	 * 增加歌曲信息
	 */
	boolean addSong(PageData pd) throws Exception;
	/**
	 * 更改歌曲信息
	 */
	boolean updateSong(PageData pd) throws Exception; 
	/**
	 * 显示排行信息
	 */
	List<PageData> rankList(Page page) throws Exception;
	/**
	 * 删除单个或删除多个
	 */
	boolean deleteSong(PageData pd) throws Exception;
	/**
	 * 修改审核状态
	 */
	boolean updateStatus(PageData pd) throws Exception;
	/**
	 * 自动补全
	 */
	List<String> autoSearch(PageData pd) throws Exception;
	/**
	 * 获取所有的歌曲信息用于导出
	 */
	List<PageData> obtainAllData() throws Exception;
	/**
	 * 从excel中导入数据
	 */
	boolean addImportSongs(List<PageData> pds) throws Exception;
	/**
	 * 获取需要导入的模板数据
	 */
	List<PageData> obtainTemplateData() throws Exception;
	/**
	 * 批量上传文件的时候执行的数据库操作
	 */
	boolean updateSongInfo(PageData pd) throws Exception;
	
	/**
	 * 根据歌曲名去获取可能的歌曲编号
	 * @param songName
	 * @return
	 * @throws Exception
	 */
	List<String> findIdByName(String songName) throws Exception;
}