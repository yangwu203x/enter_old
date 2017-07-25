package cn.ogsu.vod.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.service.IRoleService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
import net.sf.json.JSONArray;
/**
 * 角色处理的controller层
 * @author albert
 * @date 2016/10/08
 */
@Controller
@RequestMapping("/role")
public class RoleController extends ControllerBase{
	@Resource
	private IRoleService roleService;
	/**
	 * 显示角色列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/roleList")
	public ModelAndView showRoleList(Page page) throws Exception{
		//准备模型视图数据
		ModelAndView mv=new ModelAndView();
		PageData role=getPageData();
		page.setPd(role);
		//分页获取菜单信息
		List<PageData> roles=roleService.pageRole(page);
		mv.addObject("roles",roles);
		mv.addObject("role", role);
		mv.setViewName("role_list");
		return mv;
	}
	/**
	 * 显示添加或修改角色页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showRoleEdit")
	public ModelAndView showRoleEdit(HttpServletRequest request) throws Exception{
		ModelAndView mv=new ModelAndView();
		//往模型视图中添加数据
		if(request.getParameter("operateType").equals("2")){
			mv.addObject("requestUrl","modifyRole");
			//获取待修改的角色
			PageData role=roleService.findSingleRole(request.getParameter("roleNo"));
			mv.addObject("role",role);
		}else{
			mv.addObject("requestUrl","addRole");
		}
		mv.setViewName(request.getParameter("viewName"));
		return mv;
	}
	/**
	 * 增加角色
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addRole")
	public Object addRole() throws Exception{
		PageData role=getPageData();
		return roleService.addRole(role);
	}
	/**
	 * 修改角色操作
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/modifyRole")
	public Object updateRole() throws Exception{
		PageData pd=getPageData();
		return roleService.updateRole(pd);
	}
	/**
	 * 删除角色操作
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/deleteRole")
	public Object deleteRole() throws Exception{
		PageData pd=getPageData();
		return roleService.deleteRoles(pd);
	}
	/**
	 * 自动补全功能
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/autoSearch")
	public Object autoSearch()throws Exception{
		List<String> results=roleService.autoSearch(getPageData());
		JSONArray json=JSONArray.fromObject(results);
		return json.toString();
	}
}