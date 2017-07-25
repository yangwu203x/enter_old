package cn.ogsu.vod.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.IStatisticsService;
import cn.ogsu.vod.util.PageData;
/**
 * 统计歌曲歌手的点击率
 * @author albert
 * @time 2016年9月24日
 */
@Service
public class StatisticsServiceImpl extends ServiceBase implements IStatisticsService{
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> statisticsSongPlay(PageData pd) throws Exception {
		return (List<PageData>) daoSupport.findForList("StatisticsMapper.statisticsSongPlay",pd);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> statisticsSingerPlay(PageData pd) throws Exception {
		return (List<PageData>) daoSupport.findForList("StatisticsMapper.statisticsSingerOnePlay",pd);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> statisticsSingerTwoPlay(PageData pd) throws Exception {
		return (List<PageData>) daoSupport.findForList("StatisticsMapper.statisticsSingerTwoPlay",pd);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> statisticsSingerThreePlay(PageData pd) throws Exception {
		return (List<PageData>) daoSupport.findForList("StatisticsMapper.statisticsSingerThreePlay",pd);
	}
	@Override
	public int songPlayCount() throws Exception {
		return (int) daoSupport.findForObject("StatisticsMapper.groupSongCount", null);
	}
	@Override
	public int singerPlayCount() throws Exception {
		return (int) daoSupport.findForObject("StatisticsMapper.groupSingerCount", null);
	}
	@Override
	public int singerTwoPlayCount() throws Exception {
		return (int) daoSupport.findForObject("StatisticsMapper.groupSingeTworCount", null);
	}
	@Override
	public int singerThreePlayCount() throws Exception {
		return (int) daoSupport.findForObject("StatisticsMapper.groupSingerThreeCount", null);
	}
	@Override
	public int updateSongOrderTimes(List<PageData> pds) throws Exception {
		int count=0;
		for (int i = 0; i < pds.size(); i++) {
			Object obj=daoSupport.update("StatisticsMapper.updateSongOrdertimes",pds.get(i));
			if(obj!=null) count+=(int)obj;
		}
		return count;
	}
	@Override
	public int updateSingerOrderTimes(List<PageData> pds) throws Exception {
		int count=0;
		for (int i = 0; i < pds.size(); i++) {
			Object obj=daoSupport.update("StatisticsMapper.updateSingerOrdertimes", pds.get(i));
			if(obj!=null) count=(int)obj;
		}
		return count;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<String> songRankList() throws Exception {
		return (List<String>) daoSupport.findForList("StatisticsMapper.songRanks", null);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<String> singerRankList() throws Exception {
		return (List<String>) daoSupport.findForList("StatisticsMapper.singerRanks", null);
	}
	@Override
	public boolean addSongSingerRank(List<PageData> pds) throws Exception {
		return ((int)daoSupport.save("StatisticsMapper.addSongSingerRank",pds))>0;
	}
	@Override
	public boolean deleteRank() throws Exception {
		return ((int)daoSupport.delete("StatisticsMapper.deleteRank",null))>0;
	}
}