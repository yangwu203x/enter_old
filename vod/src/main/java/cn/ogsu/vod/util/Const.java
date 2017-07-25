package cn.ogsu.vod.util;

/**
 * 配置常量
 * @author albert
 *
 * @time 2016年9月6日
 */
public class Const {
	public static final String SESSION_USER = "sessionUser";					//session用的用户
	public static final String PAGE	= "admin/config/PAGE.txt";					//分页条数配置路径
	public static final String SESSION_MENU="sessionMenu";						//session缓存菜单
	public static final String ENCODER_ISO="ISO-8859-1";						//国际编码格式
    public static final String ENCODER_UTF="utf-8";								//utf-8编码
    public static final String PROPERTIESFILENAME="/config/other.properties";   //属性文件名
    public static final String APKFILELOCATION="apk.file.store";				//apk文件保存位置在属性文件中的key
    public static final String SONGFILELOCATION="song.file.store";				//音频文件保存位置在属性文件中的key
    public static final String HEADERFILELOCATION="header.file.store";			//歌手写真文件保存位置在属性文件中的key
    
    public static final String ADMIN_ROLE="1";									//超级管理员账户	角色		
    public static final String AUDIOLOCATION="audio";
    public static final String HEADERLOCATION="image";
    public static final String SESSION_ROLE="role";                             //角色权限
    
    public static final String BACKUPPROLOCATION="/config/backup.properties";   //备份与还原所需的属性文件位置
    public static final String FILE_SEPLATOR="/";								//文件分隔符
}
