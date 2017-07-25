package cn.ogsu.vod.controller;

import cn.ogsu.vod.entity.Menu;
import cn.ogsu.vod.service.IMenuService;
import cn.ogsu.vod.service.IRoleService;
import cn.ogsu.vod.util.PageData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * @Author leo_Yang【音特】
 * @Date 2017/4/25 0025 13:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/ApplicationContext.xml"})
public class AccountControllerTest {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Test
    public void accountLogin() throws Exception {
        PageData rolePrivilege=roleService.findSingleRole("1");
        List<Menu> menuList=menuService.obtainAllMenu();
        List<Menu> menuAccountList=menuService.obtainAccountMenu("1");
        System.out.println(rolePrivilege);
    }


}
