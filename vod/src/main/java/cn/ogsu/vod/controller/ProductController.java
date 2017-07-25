package cn.ogsu.vod.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.service.IProductService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 生产
 * @author albert
 * @time 2016年9月22日
 */
@Controller
@RequestMapping("/product")
public class ProductController  extends ControllerBase{
	@Resource
	private IProductService productService;
	/**
	 * 分页显示出货产品的信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showProduct")
	public ModelAndView showProductList(Page page) throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取请求数据
		PageData pd=getPageData();
		if(pd.get("start_time")!=null&&!("").equals(pd.getString("start_time").trim())){
			String start_time=pd.getString("start_time");
			start_time=start_time.substring(0,10)+" "+start_time.substring(10,start_time.length());
			pd.put("start_time",start_time);
		}
		if(pd.get("end_time")!=null&&!("").equals(pd.getString("end_time").trim())){
			String end_time=pd.getString("end_time");
			end_time=end_time.substring(0,10)+" "+end_time.substring(10,end_time.length());
			pd.put("end_time",end_time);
		}
		page.setPd(pd);
		//访问数据库操作
		List<PageData> productList=productService.obtainPageProduct(page);
		mv.addObject("product",pd);
		//封装数据
		mv.addObject("productList",productList);
		mv.setViewName("product_list");
		return mv;
	}
	/**
	 * 显示增加或修改页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showProductEdit")
	public ModelAndView showProductEdit(HttpServletRequest request) throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取要访问的视图
		 String viewName=request.getParameter("viewName");
		//如果请求是前往编辑歌曲页面
		 if(("2").equals(request.getParameter("operateType"))){
			 //获取出货产品的编号
			 String product_id=request.getParameter("product_id");
			 //执行数据库操作
			 PageData product=productService.obtainSingleProduct(product_id);
			 mv.addObject("product", product);
			 //增加请求路径
			 mv.addObject("requestUrl","updateProduct");
		 }else{
			 mv.addObject("requestUrl","addProduct");
		 }
		//设置视图
		mv.setViewName(viewName);
		return mv;
	}
	/**
	 * 增加出货产品信息
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addProduct")
	public Object addProduct(HttpServletRequest request) throws Exception{
		//获取请求数据
		PageData pd=new PageData();
		pd.put("mac_address",request.getParameter("mac_address"));
		pd.put("serial_number",request.getParameter("serial_number"));
		pd.put("delivery_time", request.getParameter("delivery_time"));
		pd.put("sale_time", request.getParameter("sale_time"));
		//执行增加操作
		return productService.addProduct(pd);
	}
	/**
	 * 修改出货产品信息
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateProduct")
	public Object updateProduct() throws Exception{
		//获取出货产品数据
		PageData pd=getPageData();
		//执行修改操作
		return productService.updateProduct(pd);
	}
	/**
	 * 删除产品操作
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("deleteProduct")
	public Object deleteProduct() throws Exception{
		PageData pd=getPageData();
		return productService.deleteProduct(pd);
	} 
}
