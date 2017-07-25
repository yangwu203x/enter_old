package cn.ogsu.vod.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.service.IBehaviorStatisticsService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
import net.sf.json.JSONArray;
/**
 *	用户行为统计的controller
 * @author albert
 * @time 2016年9月22日
 */
@Controller
@RequestMapping("/behavior")
public class BehaviorStatisticsController  extends ControllerBase{
	@Resource
	private IBehaviorStatisticsService behaviorStatisticsService;
	
	@RequestMapping("/showUserStatistics")
	public ModelAndView showUserStatistics(Page page) throws Exception{
		ModelAndView mv=new ModelAndView();
		//分页显示歌曲的点击量，下载量
		List<PageData> userStatistics=behaviorStatisticsService.userSongStatistics(page);
		mv.addObject("statistics", userStatistics);
		mv.setViewName("statistic_list");
		return mv;            
	}
	
	@RequestMapping("/singleUserStatistics")
	public ModelAndView singleUserStatistics(Page page) throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=getPageData();
		page.setPd(pd);
		//根据客户统计表的主键获取那个用户对那首歌的点击量，下载量
		List<PageData> userStatistics=behaviorStatisticsService.userSongStatisticsById(page);
		mv.addObject("statistics", userStatistics);
		mv.setViewName("songStatic_list");
		return mv;
	}
	/**
	 * 显示关注歌星列表
	 */
	@RequestMapping("/showAttentionSinger")
	public ModelAndView showAttentionSinger(Page page) throws Exception{
		ModelAndView mv=new ModelAndView();
		//获取请求参数
		PageData pd=getPageData();
		page.setPd(pd);
		//获取分页数据
		List<PageData> attentionList=behaviorStatisticsService.obtianAttentionSinger(page);
		//添加模型数据
		mv.addObject("attentionList", attentionList);
		mv.addObject("attention", pd);
		mv.setViewName("attention_list");
		return mv;
	}
	/**
	 * 自动补全功能
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/autoSearch")
	public Object autoSearch()throws Exception{
		List<String> results=behaviorStatisticsService.autoSearch(getPageData());
		JSONArray json=JSONArray.fromObject(results);
		return json.toString();
	}
}