package cn.ogsu.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import cn.ogsu.api.service.IProductService;
import cn.ogsu.api.util.Const;
import cn.ogsu.api.util.PageData;
import cn.ogsu.api.util.Tools;
/**
 * 产品的service实现层
 * @author albert
 * @time 2016年10月8日
 */
@Service
public class ProductServiceImpl extends ServiceBase implements IProductService{

	@Override
	public boolean addFirstTime(PageData pd) throws Exception {
		if(Const.EXECUTE_FLAG.equals(pd.getString("execute_flag"))){
			return false;
		}
		//不允许多次修改此时间，只允许第一次
		Object firstTime=daoSupport.findForObject("ProductMapper.findFirstTime",pd.getString("userId"));
		if(firstTime!=null &&!firstTime.toString().trim().equals("")) return false; 
		
		Date date=new Date(System.currentTimeMillis());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String first_time=sdf.format(date);
		pd.put("firstTime", first_time);
		Object result=daoSupport.update("ProductMapper.updateFirstTime", pd);
		int count=result==null?0:(int)result;
		return count>0;
	}

	@Override
	public int findFirstTime(PageData pd) throws Exception {
		int day=0;
		Object obj=daoSupport.findForObject("ProductMapper.findFirstTime", pd);
		if(obj!=null){
			Date date=(Date) obj;
			day=Tools.daysBetween(date);
		}
		return day;
	}

}
