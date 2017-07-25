package cn.ogsu.vod.schedule;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import cn.ogsu.vod.service.IStatisticsService;
import cn.ogsu.vod.util.PageData;
/**
 *实现歌曲排行的任务类
 * @author albert
 * @time 2016年9月24日
 */
public class StatisticsRankQtz {
	private static final  Logger log=Logger.getLogger(StatisticsRankQtz.class); 
	@Resource
	private IStatisticsService statisticsService;
	public void executeJob(){
		try {
			//根据点击率来获取前100名歌曲的信息
			List<String> songRanks=statisticsService.songRankList();
			//根据点击率来获取前100名歌手的信息
			List<String> singerRanks=statisticsService.singerRankList();
			//获取到的歌曲数量
			int songCount=songRanks.size();
			//获取到的歌手数量
			int singerCount=singerRanks.size();
			int forCount=songCount>singerCount?singerCount:songCount;
			List<PageData> songSingerRanks=new ArrayList<>();
			PageData pd=null;
			for (int i = 0; i <forCount; i++) {
				pd=new PageData();
				pd.put("songId",songRanks.get(i));
				pd.put("singerId",singerRanks.get(i));
				songSingerRanks.add(pd);
			}
			if(songSingerRanks.size()<100){
				//此时代表排行的歌曲数量少于歌星
				if(forCount==songCount){
					boolean isContinue=true;
					while(isContinue){
						pd=new PageData();
						pd.put("songId","");
						pd.put("singerId",singerRanks.get(forCount));
						songSingerRanks.add(pd);
						forCount++;
						if(singerCount==forCount) isContinue=false;
					}
				}else{
					//此时代表排行的歌曲数量少于歌曲
					boolean isContinue=true;
					while(isContinue){
						pd=new PageData();
						pd.put("songId",songRanks.get(forCount));
						pd.put("singerId","");
						songSingerRanks.add(pd);
						forCount++;
						if(songCount==forCount) isContinue=false;
					}
				}
			}
			//删除原有排行
			statisticsService.deleteRank();
			//增加歌曲歌手排行
			statisticsService.addSongSingerRank(songSingerRanks);
		} catch (Exception e) {
			log.error("歌星歌曲排行的统计失败!原因:"+e.getMessage());
		}
	}
}