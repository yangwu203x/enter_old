package cn.ogsu.vod.schedule;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import cn.ogsu.vod.service.IStatisticsService;
import cn.ogsu.vod.util.PageData;
/**
 *	实现统计数据的任务类
 * @author albert
 * @time 2016年9月23日
 */
public class StatisticsCountQtz {
	private static final Logger log=Logger.getLogger(StatisticsCountQtz.class);
	@Resource
	private IStatisticsService statisticsService;
	/**
	 * 执行任务的方法
	 */
	public void executeJob(){
		int pageIndex=1;
		int pageSize=500;
		PageData pd=null;
		int allCount=0;
		int pageCount=0;
		try {
			//获取歌曲分组统计后的总数量
			allCount=statisticsService.songPlayCount();
			//计算分页总数
			pageCount=(allCount%pageSize==0?allCount/pageSize:(allCount/pageSize)+1);
			//获取歌曲点击信息
			for (int i = 0; i <pageCount; i++) {
				pd=new PageData();
				//计算分页条件
				pd.put("startPos",(pageIndex-1)*pageSize);
				pd.put("endPos", pageIndex*pageSize);
				List<PageData> playSongs=statisticsService.statisticsSongPlay(pd);
				//批量修改歌曲的点击率
				statisticsService.updateSongOrderTimes(playSongs);
				pageIndex++;
			}
			pageIndex=1;
			//获取歌星1分组统计后的总数量
			allCount=statisticsService.singerPlayCount();
			//计算分页总数
			pageCount=(allCount%pageSize==0?allCount/pageSize:(allCount/pageSize)+1);
			//获取歌曲点击信息
			for (int i = 0; i <pageCount; i++) {
				pd=new PageData();
				//计算分页条件
				pd.put("startPos",(pageIndex-1)*pageSize);
				pd.put("endPos", pageIndex*pageSize);
				//获取关联歌曲的歌星点击次率
				List<PageData> playSingerOne=statisticsService.statisticsSingerPlay(pd);
				//批量修改歌手的点击率
				statisticsService.updateSingerOrderTimes(playSingerOne);
				pageIndex++;
			}
			pageIndex=1;
			//获取歌星分组统计后的总数量
			allCount=statisticsService.singerTwoPlayCount();
			//计算分页总数
			pageCount=(allCount%pageSize==0?allCount/pageSize:(allCount/pageSize)+1);
			//获取歌曲点击信息
			for (int i = 0; i <pageCount; i++) {
				pd=new PageData();
				//计算分页条件
				pd.put("startPos",(pageIndex-1)*pageSize);
				pd.put("endPos", pageIndex*pageSize);
				//获取关联歌曲的歌星2点击次率
				List<PageData> playSingerTwo=statisticsService.statisticsSingerTwoPlay(pd);
				//批量修改歌手的点击率
				statisticsService.updateSingerOrderTimes(playSingerTwo);
				pageIndex++;
			}
			pageIndex=1;
			//获取歌星3分组统计后的总数量
			allCount=statisticsService.singerTwoPlayCount();
			//计算分页总数
			pageCount=(allCount%pageSize==0?allCount/pageSize:(allCount/pageSize)+1);
			//获取歌曲点击信息
			for (int i = 0; i <pageCount; i++) {
				pd=new PageData();
				//计算分页条件
				pd.put("startPos",(pageIndex-1)*pageSize);
				pd.put("endPos", pageIndex*pageSize);
				//获取关联歌曲的歌星2点击次率
				List<PageData> playSingerThree=statisticsService.statisticsSingerThreePlay(pd);
				//批量修改歌手的点击率
				statisticsService.updateSingerOrderTimes(playSingerThree);
				pageIndex++;
			}
		} catch (Exception e) {
			log.error("歌星歌曲点击次数统计失败,原因:"+e.getMessage());
		}
	}
}