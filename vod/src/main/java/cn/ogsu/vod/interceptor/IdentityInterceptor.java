package cn.ogsu.vod.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import cn.ogsu.vod.util.Const;
import cn.ogsu.vod.util.PageData;
/**
 * 负责登陆的拦截和权限的检查
 * @author albert
 * @time 2016年9月12日
 */
public class IdentityInterceptor extends HandlerInterceptorAdapter {
	 /**
     * 进行身份认证，在handler执行之前执行,在请求被处理器处理之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object obj) throws Exception {
        //判断是否为公开地址
        String url = request.getRequestURL().toString();
        if(url.contains("login.do")||url.contains("forwardLogin.do")) {
            return true;//是公开地址则放行
        }
        HttpSession session = request.getSession();
        PageData user = (PageData) session.getAttribute(Const.SESSION_USER);
        // 允许这两个路径的访问
       if(user!= null) {
        	//判断权限的分配的是否具有权限
        	if(url.contains("privilegeMenu")){
        		String userRole=user.getString("userRole");
        		if(Const.ADMIN_ROLE.equals(userRole)){
                    return true;
                }
        		//当没有权限操作菜单时不允许对菜单进行操作，防止恶意提高权限
        		return false;
        	}
        	//判断用户是否登录
            return true;
        }else {
            //不是公开地址则重定向到登录页面
        	response.sendRedirect(request.getContextPath()+"/welcome.jsp");
            return false;
        }
    }
    /**
     * postHandle在请求被处理之后，视图生成之间调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object obj, ModelAndView mv) throws Exception {
    }
    /**
     * afterCompletion在请求完全处理后调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse reponse, Object obj, Exception e)
            throws Exception {
    }
}
