package cn.ogsu.vod.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.entity.Range;
import cn.ogsu.vod.service.ISongLangService;
import cn.ogsu.vod.service.ISongService;
import cn.ogsu.vod.service.ISongTypeService;
import cn.ogsu.vod.service.IThemeService;
import cn.ogsu.vod.util.Const;
import cn.ogsu.vod.util.DbOperate;
import cn.ogsu.vod.util.ExcelUtil;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
import cn.ogsu.vod.util.Tools;
import cn.ogsu.vod.util.upload.IoUtil;
import cn.ogsu.vod.util.upload.StreamException;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 歌曲管理的controller
 * 
 * @author albert
 * @time 2016年9月10日
 */
@Controller
@RequestMapping("/song")
public class SongController extends ControllerBase {
	@Resource
	private ISongService songService;
	@Resource
	private ISongLangService songLangService;
	@Resource
	private ISongTypeService songTypeService;
	@Resource
	private IThemeService themeService;

	/**
	 * 显示歌曲信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("showSongList")
	public ModelAndView showSongList(Page page) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 获取请求参数
		PageData pd = getPageData();
		page.setPd(pd);
		// 获取分页数据
		List<PageData> songList = songService.songList(page);
		mv.addObject("song", pd);
		// 添加模型数据
		mv.addObject("songList", songList);
		mv.setViewName("song_list");
		return mv;
	}

	/**
	 * 跳到增加或修改歌曲信息页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showSongEdit")
	public ModelAndView showSongEdit(HttpServletRequest request) throws Exception {
		// 准备视图数据对象
		ModelAndView mv = new ModelAndView();
		// 获取要访问的视图
		String viewName = request.getParameter("viewName");
		// 获取所有的歌曲主题信息
		List<PageData> songThemes = themeService.allTheme();
		mv.addObject("songThemes", songThemes);
		List<PageData> songTypes = songTypeService.allType();
		mv.addObject("songTypes", songTypes);
		// 获取所有歌曲语种信息
		List<PageData> songLangs = songLangService.allSongLangs();
		mv.addObject("songLangs", songLangs);
		// 如果请求是前往编辑歌曲页面
		if (("2").equals(request.getParameter("operateType"))) {
			// 获取修改歌曲的编号
			String songId = request.getParameter("songId");
			// 执行数据库操作
			PageData song = songService.findSingleSong(songId);
			mv.addObject("song", song);
		} else {
		}
		mv.addObject("requestUrl", "editSong");
		// 设置视图
		mv.setViewName(viewName);
		return mv;
	}

	/**
	 * 验证文件
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private boolean validateFile(String fileName) throws Exception {
		if (!fileName.toLowerCase().endsWith("mpg") && !fileName.toLowerCase().endsWith("mkv")
				&& !fileName.toLowerCase().endsWith("mrs")) {
			return false;
		}
		return true;
	}

	/**
	 * 增加歌曲信息或者修改歌曲信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/editSong")
	public Object addSong(@RequestParam(value="songFile", required = false) MultipartFile songFile, HttpServletRequest request) throws Exception {
		if (songFile != null) {
			if (!validateFile(songFile.getOriginalFilename())) {
				return false;
			}
		}
		// 准备封装数据的对象
		PageData pd = new PageData();
		// 获取歌曲相关信息 如果这个值存在则为修改
		String oldSongId = request.getParameter("oldSongId");
		if (oldSongId != null) {
			pd.put("oldSongId", oldSongId);
		}
		// 获取新增加的歌曲编号或者是修改后的歌曲编号
		String songId = request.getParameter("songId");
		String fileName = Tools.obtainFileName(songFile.getOriginalFilename());
		if (!songId.equals(fileName)) {
			return false;
		}
		pd.put("songId", songId);
		String songName = request.getParameter("songName");
		pd.put("songName", songName);
		pd.put("songFormat", request.getParameter("songFormat"));
		pd.put("songSz", songName.length());
		String spell = request.getParameter("spell");
		pd.put("spell", spell);
		pd.put("songOrder", spell.substring(0, 1).toUpperCase());
		pd.put("langId", request.getParameter("langId"));
		pd.put("nature", request.getParameter("nature"));
		String scene = request.getParameter("scene");
		pd.put("songYears", request.getParameter("songYears"));
		pd.put("themeId", request.getParameter("themeId"));
		pd.put("classicStatus", request.getParameter("classicStatus"));
		pd.put("typeId", request.getParameter("typeId"));
		if (scene == null) {
			pd.put("scene", "-1");
			pd.put("pixel", "-1");
			pd.put("volumeOne", "0");
			pd.put("volumeTwo", "0");
			pd.put("strack", "0");
		} else {
			pd.put("scene", scene);
			pd.put("pixel", request.getParameter("pixel"));
			pd.put("volumeOne", request.getParameter("volumeOne"));
			pd.put("volumeTwo", request.getParameter("volumeTwo"));
			pd.put("strack", request.getParameter("strack"));
		}
		pd.put("singerIdOne", request.getParameter("singerIdOne"));
		if (request.getParameter("singerIdTwo") == null) {
			pd.put("singerIdTwo", "-1");
		} else {
			pd.put("singerIdTwo", request.getParameter("singerIdTwo"));
		}
		if (request.getParameter("singerIdThree") == null) {
			pd.put("singerIdThree", "-1");
		} else {
			pd.put("singerIdThree", "-1");
		}
		pd.put("receivingDate", request.getParameter("receivingDate"));
		pd.put("songInfo", request.getParameter("songInfo"));
		pd.put("songVersion", request.getParameter("songVersion"));
		// 获取项目所在的绝对路径
		String absolutePath = request.getServletContext().getRealPath("/");
		// 获取apk文件保存的路径
		String filePath = Tools.propertiesFileResolve(Const.SONGFILELOCATION);
		
		// 获取文件保存的绝对位置
		String pathName = absolutePath + filePath;
		String savingFileDir=Tools.obtainDirectoryName(pathName);
		pathName+=Const.FILE_SEPLATOR+savingFileDir;
		// 保存路径
		pd.put("song_path", Const.AUDIOLOCATION+Const.FILE_SEPLATOR +savingFileDir+ Const.FILE_SEPLATOR + songFile.getOriginalFilename());
		// 处理文件上传的路径
		File directory = new File(pathName);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		// 形成文件保存的绝对路径
		pathName += Const.FILE_SEPLATOR+songFile.getOriginalFilename();
		// 复制文件
		songFile.transferTo(new File(pathName));
		if (oldSongId != null) {
			pd.put("pathLevel", "yes");
			// 修改歌曲
			return songService.updateSong(pd);
		}
		// 增加歌曲
		return songService.addSong(pd);
	}

	/**
	 * 修改不带文件歌曲信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateSongOther")
	public Object updateSong(HttpServletRequest request) throws Exception {
		// 准备封装数据的对象
		PageData pd = new PageData();
		// 获取歌曲相关信息 如果这个值存在则为修改
		String oldSongId = request.getParameter("oldSongId");
		if (oldSongId != null) {
			pd.put("oldSongId", oldSongId);
		}
		// 获取新增加的歌曲编号或者是修改后的歌曲编号
		String songId = request.getParameter("songId");
		if (!oldSongId.equals(songId)) {
			// 没有修改歌曲就不允许修改歌曲编号
			return false;
		}
		pd.put("songId", songId);
		String songName = request.getParameter("songName");
		pd.put("songName", songName);
		pd.put("songFormat", request.getParameter("songFormat"));
		pd.put("songSz", songName.length());
		String spell = request.getParameter("spell");
		pd.put("spell", spell);
		pd.put("songOrder", spell.substring(0, 1).toUpperCase());
		pd.put("langId", request.getParameter("langId"));
		pd.put("nature", request.getParameter("nature"));
		String scene = request.getParameter("scene");
		pd.put("songYears", request.getParameter("songYears"));
		pd.put("themeId", request.getParameter("themeId"));
		pd.put("classicStatus", request.getParameter("classicStatus"));
		pd.put("typeId", request.getParameter("typeId"));
		if (scene == null) {
			pd.put("scene", "-1");
			pd.put("pixel", "-1");
			pd.put("volumeOne", "0");
			pd.put("volumeTwo", "0");
			pd.put("scene", "0");
		} else {
			pd.put("scene", scene);
			pd.put("pixel", request.getParameter("pixel"));
			pd.put("volumeOne", request.getParameter("volumeOne"));
			pd.put("volumeTwo", request.getParameter("volumeTwo"));
			pd.put("strack", request.getParameter("strack"));
		}
		pd.put("singerIdOne", request.getParameter("singerIdOne"));
		pd.put("singerIdTwo", request.getParameter("singerIdTwo"));
		pd.put("singerIdThree", request.getParameter("singerIdThree"));
		pd.put("receivingDate", request.getParameter("receivingDate"));
		pd.put("songInfo", request.getParameter("songInfo"));
		pd.put("songVersion", request.getParameter("songVersion"));
		// 增加歌曲
		return songService.updateSong(pd);
	}

	/**
	 * 显示歌曲歌星排行信息
	 */
	@RequestMapping("/showRank")
	public ModelAndView showRanking(Page page) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 获取请求参数
		PageData pd = getPageData();
		page.setPd(pd);
		// 获取分页数据
		List<PageData> rankingList = songService.rankList(page);
		mv.addObject("rank", pd);
		// 添加模型数据
		mv.addObject("rankList", rankingList);
		mv.setViewName("rank_list");
		return mv;
	}

	/**
	 * 删除歌曲操作
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/deleteSong")
	public Object deleteSong() throws Exception {
		PageData pd = getPageData();
		return songService.deleteSong(pd);
	}

	/**
	 * 审核
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/passCheck")
	public Object passCheck() throws Exception {
		PageData pd = getPageData();
		return songService.updateStatus(pd);
	}

	/**
	 * 自动补全功能
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/autoSearch")
	public Object autoSearch() throws Exception {
		List<String> results = songService.autoSearch(getPageData());
		JSONArray json = JSONArray.fromObject(results);
		return json.toString();
	}

	/**
	 * 备份歌曲表操作
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/backupSong")
	public Object backupSong() throws Exception {
		boolean result = false;
		InputStream stream = this.getClass().getResourceAsStream(Const.BACKUPPROLOCATION);
		Properties prop = new Properties();
		prop.load(stream);
		try {
			String dbSongPath = prop.getProperty("dbSongPath");
			stream.close();
			String tableName = "dbSongs";
			DbOperate.tableBackup(dbSongPath, tableName);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 还原歌曲表操作
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/restoreSong")
	public Object restoreSong() throws Exception {
		boolean result = false;
		InputStream stream = this.getClass().getResourceAsStream(Const.BACKUPPROLOCATION);
		Properties prop = new Properties();
		prop.load(stream);
		try {
			String dbSongPath = prop.getProperty("dbSongPath");
			stream.close();
			DbOperate.dbRecover(dbSongPath);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 导出歌曲数据至excel
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/exportToExcel")
	public void exportData(HttpServletResponse response) throws Exception {
		// 获取要导出的数据
		List<PageData> songs = songService.obtainAllData();
		response.setHeader("Content-disposition", "attachment; filename=song.xls");
		response.setContentType("application/msexcel");
		// 获取流
		OutputStream output = response.getOutputStream();
		ExcelUtil.exportExcel(songs, output);
		output.close();
	}

	/**
	 * 导入歌曲数据至数据库
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/importFromExcel")
	public Object importData(@RequestParam MultipartFile excelFile) throws Exception {
		InputStream stream = excelFile.getInputStream();
		List<PageData> songs = ExcelUtil.importExcel(stream);
		return songService.addImportSongs(songs);
	}

	/**
	 * 下载模板文件
	 * 
	 * @param
	 */
	@RequestMapping("/templateDownload")
	public void templateDownload(HttpServletResponse response) throws Exception {
		// 获取要导出的数据
		List<PageData> data = songService.obtainTemplateData();
		response.setHeader("Content-disposition", "attachment; filename=song.xls");
		response.setContentType("application/msexcel");
		// 获取流
		OutputStream output = response.getOutputStream();
		ExcelUtil.exportExcel(data, output);
		output.close();
	}

	/**
	 * 仅单个文件上传
	 * 
	 * @param songFile
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/uploadSongFile")
	public Object uploadSongFile(@RequestParam MultipartFile songFile, HttpServletRequest request) throws Exception {
		// 验证文件的合法性
		if (songFile != null) {
			if (!validateFile(songFile.getOriginalFilename())) {
				return false;
			}
		}
		// 获取歌曲编号
		String songId = request.getParameter("songId");
		String fileName = Tools.obtainFileName(songFile.getOriginalFilename());
		if (!songId.equals(fileName)) {
			return false;
		}
		PageData pd = new PageData();
		pd.put("songId", songId);
		// 获取项目所在的绝对路径
		String absolutePath = request.getServletContext().getRealPath("/");
		// 获取apk文件保存的路径
		String filePath = Tools.propertiesFileResolve(Const.SONGFILELOCATION);
		// 获取文件保存的绝对位置
		String pathName = absolutePath + filePath;
		String savingFileDir=Tools.obtainDirectoryName(pathName);
		pathName+=Const.FILE_SEPLATOR+savingFileDir;
		// 保存路径
		pd.put("song_path", Const.AUDIOLOCATION+Const.FILE_SEPLATOR +savingFileDir+ Const.FILE_SEPLATOR + songFile.getOriginalFilename());
		// 处理文件上传的路径
		File directory = new File(pathName);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		pathName += Const.FILE_SEPLATOR+songFile.getOriginalFilename();
		// 复制文件
		songFile.transferTo(new File(pathName));
		pd.put("pathLevel", "yes");
		// 增加歌曲文件
		return songService.updateSong(pd);
	}
	

	/**
	 * 批量上传歌曲文件
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/batchUpload")
	public void batchUploadFile(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 获取文件名
		String fileName = req.getParameter("name");
		if (fileName != null) {
			if (!validateFile(fileName)) {
				return;
			}
		}
		// 设置请求头
		doOptions(req, resp);
		// 获取口令
		String token = req.getParameter("token");
		// 获取项目所在的绝对路径
		String absolutePath = req.getServletContext().getRealPath("/");
		// 分析请求头数据
		Range range = IoUtil.parseRange(req);
		OutputStream out = null;
		InputStream in = null;
		PrintWriter writer = resp.getWriter();
		JSONObject json = new JSONObject();
		long start = 0L;
		boolean success = true;
		String message = "";
		//待保存的文件目录
		String savingFileDir=Tools.obtainDirectoryName(absolutePath+File.separator+Tools.propertiesFileResolve(Const.SONGFILELOCATION));
		String saveFileDir=absolutePath+Const.FILE_SEPLATOR+Tools.propertiesFileResolve(Const.SONGFILELOCATION)+Const.FILE_SEPLATOR+savingFileDir;
		 // 生成上传的文件片段
		File f = IoUtil.getTokenedFile(saveFileDir, token);
		try {
			if (f.length() != range.getFrom()) {
				throw new StreamException(StreamException.ERROR_FILE_RANGE_START);
			}
			out = new FileOutputStream(f, true);
			in = req.getInputStream();
			int read = 0;
			byte[] bytes = new byte[10240];
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			start = f.length();
		} catch (StreamException se) {
			success = StreamException.ERROR_FILE_RANGE_START == se.getCode();
			message = "Code: " + se.getCode();
		} catch (FileNotFoundException fne) {
			message = "Code: " + StreamException.ERROR_FILE_NOT_EXIST;
			success = false;
		} catch (IOException io) {
			message = "IO Error: " + io.getMessage();
			success = false;
		} finally {
			// 关闭输入输出流
			IoUtil.close(out);
			IoUtil.close(in);
			if (range.getSize() == start) {
				// 保存最终文件的文件句柄
				File destFile = new File(saveFileDir+Const.FILE_SEPLATOR + fileName);
				// 先清除该文件对象中的数据
				destFile.delete();
				/**
				 * mv 	move
				 */
				// 重新将文件片段保存
				f.renameTo(destFile);
				// 释放内存
				f = null;
				destFile = null;
				// 进行数据库操作
				if (start != 0) {
					// 获取歌曲的文件名
					PageData pd = new PageData();
					pd.put("songPath", Const.AUDIOLOCATION +Const.FILE_SEPLATOR+savingFileDir+Const.FILE_SEPLATOR + fileName);
					// 获取文件名不包含后缀名
					pd.put("songId", Tools.obtainFileName(fileName));
					songService.updateSongInfo(pd);
					range = null;
				}
			}
			try {
				if (success)
					json.put("start", start);
				json.put("success", success);
				json.put("message", message);
			} catch (JSONException localJSONException3) {
			}
			writer.write(json.toString());
			IoUtil.close(writer);
		}
	}

	/**
	 * 设置请求头参数
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Range,Content-Type");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
	}

	@RequestMapping("/showUpload")
	public String showFileUpload() {
		return "uploadFile";
	}

	/**
	 * 根据歌曲名去获取可能的歌曲编号
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/obtainIdByName")
	public Object obtainIdByName(HttpServletRequest request) throws Exception {
		String songName = request.getParameter("songName");
		// 获取可能的编号
		List<String> singerIds = songService.findIdByName(songName);
		return singerIds;
	}

}
