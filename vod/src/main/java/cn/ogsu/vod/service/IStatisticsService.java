package cn.ogsu.vod.service;
import java.util.List;
import cn.ogsu.vod.util.PageData;
/**
 * 对歌星歌手统计点击次数的service层接口
 * @author albert
 * @time 2016年9月24日
 */
public interface IStatisticsService {
	/**
	 * 获取需要统计歌曲点击率的总数量
	 */
	public int songPlayCount() throws Exception;
	/**
	 * 统计歌曲点播率
	 */
	public List<PageData> statisticsSongPlay(PageData pd) throws Exception;
	/**
	 * 获取需要统计歌手1点击率的总数量
	 */
	public int singerPlayCount() throws Exception;
	/**
	 * 统计歌手1点击率
	 */
	public List<PageData> statisticsSingerPlay(PageData pd) throws Exception;
	/**
	 * 获取需要统计歌手2点击率的总数量
	 */
	public int singerTwoPlayCount() throws Exception;
	/**
	 * 统计歌手2点击率
	 */
	public List<PageData> statisticsSingerTwoPlay(PageData pd) throws Exception;
	/**
	 * 获取需要统计歌手3点击率的总数量
	 */
	public int singerThreePlayCount() throws Exception;
	/**
	 * 统计歌手3点击率
	 */
	public List<PageData> statisticsSingerThreePlay(PageData pd) throws Exception;
	/**
	 * 批量修改歌曲的点击次数
	 */
	public int updateSongOrderTimes(List<PageData> pds) throws Exception;
	/**
	 * 批量修改歌手的点击次数
	 */
	public int updateSingerOrderTimes(List<PageData> pds) throws Exception;
	/**
	 * 获取歌曲排行的list
	 */
	public List<String> songRankList() throws Exception;
	/**
	 * 获取歌手排行的list
	 */
	public List<String> singerRankList() throws Exception;
	/**
	 * 增加歌曲歌星的排行
	 */
	public boolean addSongSingerRank(List<PageData> pds) throws Exception; 
	/**
	 * 删除原有排行
	 */
	public boolean deleteRank() throws Exception;
}