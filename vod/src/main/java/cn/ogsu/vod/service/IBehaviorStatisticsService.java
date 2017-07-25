package cn.ogsu.vod.service;
import java.util.List;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 用户行为统计的service层
 * @author albert
 * @time 2016年9月22日
 */
public interface IBehaviorStatisticsService {
	/**
	 * 分页显示歌曲的点击量，下载量
	 */
	List<PageData> userSongStatistics(Page page) throws Exception;
	
	/**
	 * 根据客户统计表的主键获取那个用户对那首歌的点击量，下载量
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	List<PageData> userSongStatisticsById(Page page) throws Exception;
	/**
	 * 获取关注歌星的记录列表
	 */
	List<PageData> obtianAttentionSinger(Page page) throws Exception;
	/**
	 * 自动搜查
	 */
	List<String> autoSearch(PageData pd) throws Exception;
}