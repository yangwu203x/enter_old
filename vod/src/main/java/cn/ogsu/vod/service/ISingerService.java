package cn.ogsu.vod.service;
import java.util.List;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 歌手相关任务处理的service层接口
 * @author albert
 * @time 2016年9月14日
 */
public interface ISingerService {
	/**
	 * 分页获取歌手信息
	 */
	public List<PageData> obtainSingers(Page page) throws Exception;
	/**
	 * 获取单个歌手的信息
	 */
	public PageData obtainSinger(String singerId) throws Exception;
	/**
	 * 增加歌手的信息
	 */
	public boolean addSinger(PageData pd) throws Exception;
	/**
	 * 修改歌手的信息
	 */
	public boolean updateSinger(PageData pd) throws Exception;
	/**
	 * 获取所有歌手的名字和编号
	 */
	public List<PageData> obtainAllSinger() throws Exception;
	/**
	 * 删除单个
	 */
	boolean deleteSinger(String singerId) throws Exception;
	/**
	 * 修改审核状态
	 */
	boolean updateStatus(PageData pd) throws Exception;
	/**
	 * 自动搜查
	 */
	List<String> autoSearch(PageData pd) throws Exception;
	/**
	 * 根据歌星名去获取可能的歌星编号
	 * @param singerName
	 * @return
	 * @throws Exception
	 */
	List<Integer> findIdByName(String singerName) throws Exception;
}