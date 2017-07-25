package cn.ogsu.vod.service;
import java.util.List;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 *用户信息的service层接口
 * @author albert
 * @time 2016年9月22日
 */
public interface IUserInfoService {
	/**
	 * 获取分页用户信息
	 */
	List<PageData> obtainPageUser(Page page) throws Exception;
	/**
	 * 获取单个用户信息
	 */
	PageData obtainSingleUser(String user_id) throws Exception;
	/**
	 * 修改用户信息
	 */
	boolean addUser(PageData userInfo) throws Exception;
	/**
	 * 修改用户信息
	 */
	boolean updateUser(PageData userInfo) throws Exception;
	/**
	 * 删除多个用户信息
	 */
	boolean delUsers(PageData pd) throws Exception;
	/**
	 * 自动补全
	 */
	List<String> autoSearch(PageData pd) throws Exception;
}