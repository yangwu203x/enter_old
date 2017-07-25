package cn.ogsu.vod.service;
import java.util.List;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 账户信息
 * @author albert
 * @time 2016年9月6日
 */
public interface IAccountService {
	/**
	 * 获取账户信息
	 */
	public List<PageData> accountList(Page page) throws Exception;
	/**
	 * 自动补全搜索的账号
	 */
	List<String> autoSearch(PageData pd) throws Exception;
	/**
	 * 注册新账户信息
	 * @param 请求携带的数据
	 * @return 注册是否成功
	 * @throws Exception 注册失败时的异常数据
	 */
	public boolean addAccount(PageData data) throws Exception;
	/**
	 * 修改账户信息
	 * @param data 请求携带的数据
	 * @return 修改是否成功
	 * @throws Exception 修改失败时的异常
	 */
	public boolean updateAccount(PageData data) throws Exception;
	/**
	 * 账户登陆
	 */
	public PageData getAccount(PageData data) throws Exception;
	/**
	 * 根据编号获取账户信息
	 */
	public PageData getAccountByNo(String account) throws Exception;
	/**
	 * 删除账户
	 */
	public boolean deleteAccount(PageData paramData) throws Exception;
	/**
	 * 根据勾选给账户分配权限
	 */
	public boolean addPrivilege(PageData pd) throws Exception;
	/**
	 * 根据勾选删除账户权限
	 */
	public boolean delPrivilege(PageData pd) throws Exception;
}
