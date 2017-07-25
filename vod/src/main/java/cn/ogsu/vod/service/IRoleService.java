package cn.ogsu.vod.service;
import java.util.List;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 角色的service层接口
 * @author albert
 * @time 2016年10月8日
 */
public interface IRoleService {
	/**
	 * 分页获取的角色信息
	 */
	List<PageData> pageRole(Page page) throws Exception;
	/**
	 * 获取所有角色信息
	 */
	List<PageData> allRole() throws Exception;
	/**
	 * 获取单个角色信息
	 */
	PageData findSingleRole(String roleNo) throws Exception;
	/**
	 * 增加角色信息
	 */
	boolean addRole(PageData pd) throws Exception;
	/**
	 * 增加角色信息
	 */
	boolean updateRole(PageData pd) throws Exception;
	/**
	 * 删除角色信息
	 */
	boolean deleteRoles(PageData pd) throws Exception;
	/**
	 * 自动搜查
	 */
	List<String> autoSearch(PageData pd) throws Exception;
}