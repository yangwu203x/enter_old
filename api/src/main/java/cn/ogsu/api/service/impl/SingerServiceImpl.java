package cn.ogsu.api.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.api.service.ISingerService;
import cn.ogsu.api.util.PageData;

/**
 * 歌星的相关处理的service实现层
 * 
 * @author albert
 * @time 2016年9月13日
 */
@Service
public class SingerServiceImpl extends ServiceBase implements ISingerService {

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainAsyncSingerType() throws Exception {
		// 获取歌星类别数据
		return (List<PageData>) daoSupport.findForList("SingerMapper.asyncSingerTypes", null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainAsyncSinger(PageData pd) throws Exception {
		// 获取歌星的数据
		return (List<PageData>) daoSupport.findForList("SingerMapper.asyncSingers", pd);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainHotSingers() throws Exception {
		return (List<PageData>) daoSupport.findForList("SingerMapper.findHotSinger", null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initSingerCount() {
		try {
			List<PageData> list = (List<PageData>) daoSupport.findForList("SingerMapper.findSingerName", null);
			for (int i = 0; i < list.size(); i++) {
				System.err.println("list.get(i).get(singer_name):" + list.get(i).getString("singer_name"));
				daoSupport.findForObject("SingerMapper.initSingerCount", list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
