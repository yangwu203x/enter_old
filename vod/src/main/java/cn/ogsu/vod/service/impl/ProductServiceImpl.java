package cn.ogsu.vod.service.impl;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.IProductService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
import cn.ogsu.vod.util.Tools;
/**
 * 产品出货的service实现层
 * @author albert
 * @time 2016年9月22日
 */
@Service
public class ProductServiceImpl extends ServiceBase implements IProductService{
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainPageProduct(Page page) throws Exception {
		List<PageData> products=(List<PageData>) daoSupport.findForList("ProductMapper.listPageProduct", page);
		PageData pd=null;
		for (int i = 0; i <products.size(); i++) {
			pd=products.get(i);
			pd.put("sale_time",Tools.date2Str((Date)pd.get("sale_time")));
		}
		return products;
	}
	@Override
	public PageData obtainSingleProduct(String product_id) throws Exception {
		PageData pd=(PageData) daoSupport.findForObject("ProductMapper.singleProduct",product_id);
		pd.put("sale_time",Tools.date2Str((Date)pd.get("sale_time")));
		return pd;
	}
	@Override
	public boolean addProduct(PageData product) throws Exception {
		Object obj=daoSupport.findForObject("UserInfoMapper.obtainUserId",product);
		if(obj==null) return false;
		product.put("userId",obj.toString());
		String delivery_time=product.getString("delivery_time");
		product.put("delivery_time", delivery_time.replace("|", " "));
		String sale_time=product.getString("sale_time");
		product.put("sale_time", sale_time.replace("|", " "));
		return ((int)daoSupport.save("ProductMapper.addProduct",product))>0;
	}
	@Override
	public boolean updateProduct(PageData product) throws Exception {
		Object obj=daoSupport.findForObject("UserInfoMapper.obtainUserId",product);
		if(obj==null) return false;
		product.put("userId",obj.toString());
		String delivery_time=product.getString("delivery_time");
		product.put("delivery_time", delivery_time.replace("|", " "));
		String sale_time=product.getString("sale_time");
		product.put("sale_time", sale_time.replace("|", " "));
		return ((int)daoSupport.update("ProductMapper.updateProduct", product))>0;
	}
	@Override
	public boolean deleteProduct(PageData product) throws Exception {
		String[] productNos=product.getString("productNo").split(",");
		return ((int)daoSupport.delete("ProductMapper.deleteProducts", productNos))>0;
	}
}
