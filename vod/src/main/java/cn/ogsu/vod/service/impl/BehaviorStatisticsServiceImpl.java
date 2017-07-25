package cn.ogsu.vod.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.IBehaviorStatisticsService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 *	用户的行为统计的service实现层
 * @author albert
 * @time 2016年9月22日
 */
@Service
public class BehaviorStatisticsServiceImpl extends ServiceBase implements IBehaviorStatisticsService {
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> userSongStatistics(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList(
				"BehaviorStatisticsMapper.listPageSongStatistics", page);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> userSongStatisticsById(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList(
				"BehaviorStatisticsMapper.findSongStatisticsById", page);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtianAttentionSinger(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList(
				"BehaviorStatisticsMapper.listPageAttentionSinger", page);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<String> autoSearch(PageData pd) throws Exception {
		List<String> results=null;
		String searchType=pd.getString("searchType");
		String searchDirect=pd.getString("searchDirect");
		if(searchType.equals("m")){
			if(searchDirect.equals("p")){
				results=(List<String>) daoSupport.findForList("BehaviorStatisticsMapper.autoPmacAddress", pd);
			}else if(searchDirect.equals("d")){
				results=(List<String>) daoSupport.findForList("BehaviorStatisticsMapper.autoDmacAddress", pd);
			}else if(searchDirect.equals("c")){
				results=(List<String>) daoSupport.findForList("BehaviorStatisticsMapper.autoCmacAddress", pd);
			}else{
				results=(List<String>) daoSupport.findForList("BehaviorStatisticsMapper.autoAmacAddress", pd);
			}
		}else{
			if(searchDirect.equals("p")){
				results=(List<String>) daoSupport.findForList("BehaviorStatisticsMapper.autoPSongName", pd);
			}else if(searchDirect.equals("d")){
				results=(List<String>) daoSupport.findForList("BehaviorStatisticsMapper.autoDSongName", pd);
			}else if(searchDirect.equals("c")){
				results=(List<String>) daoSupport.findForList("BehaviorStatisticsMapper.autoCSongName", pd);
			}else{
				results=(List<String>) daoSupport.findForList("BehaviorStatisticsMapper.autoASingerName", pd);
			}
		}
		return results;
	}
	
}
