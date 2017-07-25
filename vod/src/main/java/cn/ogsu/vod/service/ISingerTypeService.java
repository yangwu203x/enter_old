package cn.ogsu.vod.service;
import java.util.List;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 *歌手类型的相关任务的service接口
 * @author albert
 * @time 2016年9月14日
 */
public interface ISingerTypeService {
	/**
	 * 歌手类型的列表
	 */
	public List<PageData> singerTypeList(Page page) throws Exception;
	/**
	 * 歌手类型的编号和名字的集合所有数据
	 */
	public List<PageData> allSingerType() throws Exception;
	/**
	 * 根据歌曲类型编号获取单个歌手类型信息
	 */
	public PageData singerType(String singerTypeId) throws Exception;
	/**
	 * 增加歌手类型
	 */
	public boolean addSingerType(PageData pd) throws Exception;
	/**
	 * 修改歌手类型信息
	 */
	public boolean updateSingerType(PageData pd) throws Exception;
	/**
	 * 修改审核状态
	 */
	boolean updateStatus(PageData pd) throws Exception;
}