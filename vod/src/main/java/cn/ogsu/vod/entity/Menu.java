package cn.ogsu.vod.entity;
import java.io.Serializable;
import java.util.List;
/**
 * 菜单的实体类
 * @author albert
 *
 * @time 2016年9月7日
 */
public class Menu implements Serializable{
	private static final long serialVersionUID = 8853889366660228448L;
	/**
	 * 菜单编号
	 */
	private int menuNo;
	/**
	 * 菜单名
	 */
	private String menuName;
	/**
	 * 顶级菜单图标
	 */
	private String menuIcon;
	/**
	 * 子级菜单访问的url
	 */
	private String menuUrl;
	/**
	 * 父菜单编号
	 */
	private int parentMenuNo;
	/**
	 * 所有的子菜单
	 */
	private List<Menu> subMenuList;
	public int getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public int getParentMenuNo() {
		return parentMenuNo;
	}
	public void setParentMenuNo(int parentMenuNo) {
		this.parentMenuNo = parentMenuNo;
	}
	public List<Menu> getSubMenuList() {
		return subMenuList;
	}
	public void setSubMenuList(List<Menu> subMenuList) {
		this.subMenuList = subMenuList;
	}
}