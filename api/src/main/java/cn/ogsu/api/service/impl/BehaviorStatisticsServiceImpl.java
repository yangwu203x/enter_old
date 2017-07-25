package cn.ogsu.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.api.service.IBehaviorStatisticsService;
import cn.ogsu.api.util.PageData;

/**
 * 用户行为统计的service实现层
 * @author albert
 * @time 2016年9月5日
 */
@Service
public class BehaviorStatisticsServiceImpl extends ServiceBase implements IBehaviorStatisticsService{

	
	@Override
	public String findUserIdByFlag(PageData pd) throws Exception {
		return daoSupport.findForObject("BehaviorStatisticsMapper.findUserIdByFlag",pd).toString();
	}

	@Override
	public boolean syncDataFromClient(List<PageData> statistics) throws Exception {
		//在添加同步数据
		return ((int)daoSupport.save("BehaviorStatisticsMapper.syncDataFromClient", statistics))>0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> syncDataFromServer(PageData pd) throws Exception {
		Object userIdTemp=pd.get("userId");
		System.err.println(userIdTemp);
		Integer userId=Integer.valueOf(pd.getString("userId"));
		return (List<PageData>) daoSupport.findForList("BehaviorStatisticsMapper.syncDataFromServer", userId);
	}

	@Override
	public boolean updateCollect(List<PageData> statistics) throws Exception {
		//保存增加收藏的信息
		List<PageData> addCollects=new ArrayList<>();
		//保存取消收藏的信息
		List<PageData> cancelCollects=new ArrayList<>();
		int count=0;
		for (PageData pd : statistics) {
			String action=pd.getString("action");
			if(action.equals("1")){
				addCollects.add(pd);
			}else{
				cancelCollects.add(pd);
			}
		}
		if(addCollects.size()!=0){
			count+=(int)daoSupport.save("BehaviorStatisticsMapper.addCollect", addCollects);
		}
		if(cancelCollects.size()!=0){
			for (PageData pd : cancelCollects) {
				count+=(int)daoSupport.delete("BehaviorStatisticsMapper.cancelCollect", pd);
			}
			
		}
		return count>0;
	}

	@Override
	public boolean updateAppCollect(List<PageData> statistics) throws Exception {
		//保存增加收藏的信息
		List<PageData> addCollects=new ArrayList<>();
		//保存取消收藏的信息
		List<PageData> cancelCollects=new ArrayList<>();
		int count=0;
		for (PageData pd : statistics) {
			String action=pd.getString("action");
			if(action.equals("1")){
				addCollects.add(pd);
			}else{
				cancelCollects.add(pd);
			}
		}
		if(addCollects.size()!=0){
			count+=(int)daoSupport.save("BehaviorStatisticsMapper.addAppCollect", addCollects);
		}
		if(cancelCollects.size()!=0){
			for (PageData pd : cancelCollects) {
				count+=(int)daoSupport.delete("BehaviorStatisticsMapper.cancelAppCollect", pd);
			}
			
		}
		return count>0;
	}
	
	@Override
	public boolean addSingerAttention(List<PageData> pdList) throws Exception {
		List<PageData> attentionList=new ArrayList<>();
		List<PageData> cancelAttentionList=new ArrayList<>();
		for (PageData pd : pdList) {
			String action=pd.getString("action");
			if(action.equals("1")){
				attentionList.add(pd);
			}else{
				cancelAttentionList.add(pd);
			}
		}
		int cancelCount=0;
		int count=0;
		if(cancelAttentionList.size()!=0){
			//取消
			cancelCount=(int) daoSupport.delete("BehaviorStatisticsMapper.cancelAttention", cancelAttentionList);
		}
		if(attentionList.size()!=0){
			//增加
			count=(int) daoSupport.save("BehaviorStatisticsMapper.singerAttentionStatistics", attentionList);
		}
		//执行增加操作
		return (cancelCount+count)>0;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> singerAttentionList(PageData pd) throws Exception {
		return (List<PageData>) daoSupport.findForList("BehaviorStatisticsMapper.syncAttentionFromServer",pd);
	}
	
	/**
	 * 获取热门搜索关键字
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> findHotKey(PageData pd) throws Exception{
		return ((List<PageData>) daoSupport.findForList("BehaviorStatisticsMapper.syncHotKey", pd));
	}
}
