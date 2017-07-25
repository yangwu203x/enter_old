package cn.ogsu.vod.service;
import java.util.List;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 产品出货的service层接口
 * @author albert
 * @time 2016年9月22日
 */
public interface IProductService {
	/**
	 * 分页获取产品信息
	 */
	List<PageData> obtainPageProduct(Page page) throws Exception;
	/**
	 * 获取单个产品信息
	 */
	PageData obtainSingleProduct(String product_id) throws Exception;
	/**
	 * 增加产品出货信息
	 */
	boolean addProduct(PageData product) throws Exception;
	/**
	 * 修改产品出货记录
	 */
	boolean updateProduct(PageData product) throws Exception;
	/**
	 * 删除产品
	 */
	boolean deleteProduct(PageData product) throws Exception; 
}