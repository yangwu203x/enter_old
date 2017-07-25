package cn.ogsu.vod.service;
import java.util.List;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 歌曲语种的service接口
 * @author albert
 * @time 2016年9月20日
 */
public interface ISongLangService {
	/**
	 * 分页获取歌曲语种信息
	 */
	List<PageData> pageSongLangs(Page page) throws Exception;
	/**
	 * 获取包含编号和name的所有歌曲语种信息
	 */
	List<PageData> allSongLangs() throws Exception;
	/**
	 * 获取单个歌曲语种信息
	 */
	PageData singleSongLang(String lang_id) throws Exception;
	/**
	 * 增加歌曲语种信息
	 */
	boolean addSongLang(PageData pd) throws Exception;
	/**
	 * 修改歌曲语种信息
	 */
	boolean updateSongLang(PageData pd) throws Exception;
	/**
	 * 修改审核状态
	 */
	boolean updateStatus(PageData pd) throws Exception;
}