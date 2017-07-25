package cn.ogsu.vod.service.impl;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.IAccountService;
import cn.ogsu.vod.util.EncryptHandle;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
import cn.ogsu.vod.util.Tools;
/**
 * 账户信息的service
 * @author albert
 * @time 2016年9月6日
 */
@Service
public class AccountServiceImpl extends ServiceBase implements IAccountService {
	@Override
	public boolean addAccount(PageData data) throws Exception{
		//标示执行数据库操作是否成功
		boolean executeStatus=false;
		//获取登陆密码
		String password=data.getString("password");
		//处理字符串
		data.put("password", EncryptHandle.encrypt(password));
		//注册账户信息
		Object status=daoSupport.save("AccountMapper.addAccount",data);
		//判断执行是否成功
		if(status!=null &&Integer.valueOf(status.toString())>0) executeStatus=true;
		return executeStatus;
	}
	@Override
	public boolean updateAccount(PageData data) throws Exception{
		//标示执行数据库操作是否成功
		boolean executeStatus=false;
		String accountNo=data.getString("accountNo");
		//获取待修改账户信息
		PageData updateAccount=getAccountByNo(accountNo);
		//处理密码
		String password=EncryptHandle.encrypt(data.getString("oldPassword"));
		if(!password.equals(updateAccount.get("password"))){
			return false;
		}
		data.remove("oldPassword");
		//加密待修改密码
		data.put("password", EncryptHandle.encrypt(data.getString("password")));
		//修改账户信息
		Object status=daoSupport.update("AccountMapper.modifyAccount",data);
		//判断执行是否成功
		if(status!=null &&Integer.valueOf(status.toString())>0) executeStatus=true;
		return executeStatus;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> accountList(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList("AccountMapper.listPageAccount",page);
	}
	@Override
	public PageData getAccount(PageData data) throws Exception {
		//获取登陆密码
		String password=data.getString("password");
		//处理字符串
		data.put("password", EncryptHandle.encrypt(password));
		//执行登陆步骤
		Object obj=daoSupport.findForObject("AccountMapper.accountLogin", data);
		if(obj==null) return null;
		PageData pd=(PageData) obj;
		data.put("lastLoginTime", Tools.date2Str(new Date(System.currentTimeMillis())));
		data.put("accountNo", pd.get("accountNo"));
		//登陆成功后修改最后一次登陆的时间
		int count=(int) daoSupport.update("AccountMapper.modifyAccount",data);
		//修改登陆时间成功后返回登陆数据
		return count>0?pd:null;
	}
	@Override
	public PageData getAccountByNo(String account) throws Exception {
		return (PageData) daoSupport.findForObject("AccountMapper.findSingleAccount",account);
	}
	@Override
	public boolean deleteAccount(PageData paramData) throws Exception {
		Object obj=paramData.get("accountNos");
		String[] accountNos=null;
		//将从界面得到的编号字符串转换为数组
		if(obj!=null) accountNos=obj.toString().split(",");
		else return false;//如果得不到菜单编号的字符串则不存在删除操作
		return ((int)daoSupport.delete("AccountMapper.delAccount",accountNos))>0;
	}
	@Override
	public boolean addPrivilege(PageData pd) throws Exception {
		Object obj=pd.get("menuNo");
		String menuNo=null;
		if(obj!=null) menuNo=(String) obj;
		String[] menusNo=menuNo.split(",");
		pd.put("menuNo",menusNo);
		return ((int)daoSupport.save("AccountMapper.givePrivilege",pd))>0;
	}
	@Override
	public boolean delPrivilege(PageData pd) throws Exception {
		Object obj=pd.get("menuNo");
		String menuNo=null;
		if(obj!=null) menuNo=(String) obj;
		String[] menusNo=menuNo.split(",");
		pd.put("menuNo",menusNo);	
		return ((int)daoSupport.delete("AccountMapper.delPrivilege",pd))>0;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<String> autoSearch(PageData pd) throws Exception {
		return (List<String>) daoSupport.findForList("AccountMapper.autoAccountNo", pd);
	}
}