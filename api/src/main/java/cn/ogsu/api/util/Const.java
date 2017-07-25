package cn.ogsu.api.util;

/**
 * 用以封装各种常量
 * 
 * @author albert
 * @time 2016年9月12日
 */
public class Const {

	public static final String SUCCESS_MSG = "请求成功"; // 请求成功的信息
	public static final String FAILED_MSG = "请求失败"; // 请求失败的信息
	public static final int SUCCESS_FLAG = 1; // 请求成功的标示
	public static final int FAILED_FLAG = 0; // 请求失败的标示
	public static final String FILE_NOT_FOUND = "文件未找到!"; // 文件找不到的提示
	public static final String FILE_CONTENT_TYPE = "application/octet-stream"; // 设置文件响应类型
	public static final String EXECUTE_FLAG = "0";

	/**
	 * 筛选条件
	 */
	public static final int SCREEN_LATELY = 1;
	public static final int SCREEN_SONG_A_Z = 2;
	public static final int SCREEN_SINGER_A_Z = 3;
	public static final int SCREEN_SELECTED = 4;
	public static final int SCREEN_NEW_SONG = 5;
	public static final int SCREEN_SONG_7080 = 6;
	public static final int SCREEN_SONG_HOT = 7;

	/**
	 * 数据来源
	 */
	public static final int SOURCE_COMMEND_SONGS = 1;	//推荐歌曲
	public static final int SOURCE_NEW_SONG = 2;		//新歌
	public static final int SOURCE_SONGS_LANG = 3;		//语种歌曲
	public static final int SOURCE_SONGS_THEME = 4;	//动态类别
	public static final int SOURCE_SONGS_CLASSIC = 5;	//经典歌曲
	public static final int SOURCE_SONGS_TYPE = 6;		//娱乐节目 
	public static final int SOURCE_SONGS_ORDERTIME = 7;	//热门歌曲

}
