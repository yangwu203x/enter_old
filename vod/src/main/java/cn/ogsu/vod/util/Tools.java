package cn.ogsu.vod.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
/**
 * 一些常用操作的工具类
 * @author albert
 *
 * @time 2016年9月6日
 */
public class Tools {
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 把时间根据时、分、秒转换为时间段
	 * @param StrDate
	 */
	public static String getTimes(String StrDate){
		String resultTimes = "";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date now;
	    
	    try {
	    	now = new Date();
	    	Date date=df.parse(StrDate);
	    	long times = now.getTime()-date.getTime();
	    	long day  =  times/(24*60*60*1000);
	    	long hour = (times/(60*60*1000)-day*24);
	    	long min  = ((times/(60*1000))-day*24*60-hour*60);
	    	long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);
	        
	    	StringBuffer sb = new StringBuffer();
	    	//sb.append("发表于：");
	    	if(hour>0 ){
	    		sb.append(hour+"小时前");
	    	} else if(min>0){
	    		sb.append(min+"分钟前");
	    	} else{
	    		sb.append(sec+"秒前");
	    	}
	    		
	    	resultTimes = sb.toString();
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    return resultTimes;
	}
	
	/**
	 * 写txt里的单行内容
	 * @param fileP  文件路径
	 * @param content  写入的内容
	 */
	public static void writeFile(String fileP,String content){
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if(filePath.indexOf(":") != 1){
			filePath = File.separator + filePath;
		}
		try {
	        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");      
	        BufferedWriter writer=new BufferedWriter(write);          
	        writer.write(content);      
	        writer.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取txt里的单行内容
	 * @param fileP  文件路径
	 */
	public static String readTxtFile(String fileP) {
		try {
			
			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if(filePath.indexOf(":") != 1){
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { 		// 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), encoding);	// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			}else{
				System.err.println("找不到指定的文件,查看此路径是否正确:"+filePath);
			}
		} catch (Exception e) {
			System.err.println("读取文件内容出错");
		}
		return "";
	}
	
	/**
	 * 获取客户端登陆的ip
	 * @param request
	 * @return
	 */
	public static String getRemoteIp(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    return ip;
	}
	/**
	 * 将提交的字符串编码
	 * @param character
	 * @return
	 */
	public static String isoToUtf(String character) throws Exception{
		return new String(character.getBytes(Const.ENCODER_ISO),Const.ENCODER_UTF);
	}
	
	/**
	 * 属性文件解析,根据属性名在属性文件中获取属性值
	 * @param propertyName
	 * proLocation .properties所在的文件位置
	 * @return
	 * @throws Exception
	 */
	public static String propertiesFileResolve(String propertyName) throws Exception{
		//实例化属性文件对象
		Properties properties=new Properties();
		//获取流
		InputStream stream=Tools.class.getResourceAsStream(Const.PROPERTIESFILENAME);
		//加载流
		properties.load(stream);
		//根据所给的属性名获取值
		return properties.getProperty(propertyName);
	}

	/**
	 * 解析和还原数据库所需要的信息
	 * @return
	 * @throws Exception
	 */
	public static PageData resolveDbPro() throws Exception{
		//获取与属性文件相关的流
		InputStream stream=Tools.class.getResourceAsStream(Const.BACKUPPROLOCATION);
		//实例化属性文件对象
		Properties properties=new Properties();
		//加载流
		properties.load(stream);
		PageData pd=new PageData();
		pd.put("dbHost", properties.getProperty("dbHost"));
		pd.put("dbUser", properties.getProperty("dbUser"));
		pd.put("dbPass", properties.getProperty("dbPass"));
		pd.put("dbPort", properties.getProperty("dbPort"));
		pd.put("dbName", properties.getProperty("dbName"));
		stream.close();
		return pd;
	}
	
	/**
	 * 根据所给的文件名获取文件的后缀
	 * @param fileName
	 * @return .* 如.png
	 * @throws Exception
	 */
	public static String obtainFileSuffix(String fileName) throws Exception{
		//找寻最后一个.所在的位置
		int startIndex=fileName.lastIndexOf(".");
		String fileSuffix=fileName.substring(startIndex+1,fileName.length());
		return fileSuffix;
	}
	
	/**
	 * 根据所给的文件名获取文件的名称不包含后缀
	 * @param fileName
	 * @return xx.* 返回xx
	 * @throws Exception
	 */
	public static String obtainFileName(String fileName) throws Exception{
		//找寻最后一个.所在的位置
		int startIndex=fileName.lastIndexOf(".");
		return fileName.substring(0,startIndex);
	}
	
	/**
	 * 产生一个新的文件名
	 * @return
	 * @throws Exception
	 */
	public static String fileRename(String fileName) throws Exception{
		UUID uuid=UUID.randomUUID();
		//获取uuid
		String uuidString=uuid.toString();
		//去掉所有的-
		uuidString=uuidString.replaceAll("-","");
		//去一半长度
		uuidString=	uuidString.substring(uuidString.length()/2, uuidString.length());
		
		return uuidString+"."+obtainFileSuffix(fileName);
	}
	
	/**
	 * 解析数据库的一些属性值
	 * @return dbUser,dbPwd,dbName
	 */
	public static PageData resolveDbProperty() throws Exception{
		Properties pro=new Properties();
		//将数据库相关属性的属性文件读入流中
		InputStream is=Tools.class.getResourceAsStream("/config/dbconfig.properties");
		pro.load(is);
		PageData pd=new PageData();
		//解析备份与还原数据所需的数据库属性
		pd.put("dbUser",pro.getProperty("username"));
		pd.put("dbPwd",pro.getProperty("password"));
		pd.put("dbName",pro.getProperty("dbname"));
		pd.put("linuxpath",pro.getProperty("linuxpath"));
		pd.put("windowpath",pro.getProperty("windowpath"));
		pd.put("linuxMysqlPath",pro.getProperty("linuxMysqlPath"));
		pd.put("windowsMysqlPath",pro.getProperty("windowsMysqlPath"));
		return pd;
	}
	
	/**
	 * 备份数据库
	 * @param pd
	 * @return
	 * @throws Exception
	 *  comman="C:/Program Files/MySQL/MySQL Server 5.5/bin/mysql.exe -uroot -proot test";
	 */
	public static boolean backupDb(PageData pd) throws Exception{
		String dbUser=pd.getString("dbUser");
		String dbPwd=pd.getString("dbPwd");
		String dbName=pd.getString("dbName");
		String savePath=null;
		String mysqlPath=null;
		//字符串拼接命令
		String command=null;
		Properties prop=System.getProperties();
		String systemType=prop.getProperty("os.name");
		if(systemType!=null) systemType=systemType.toLowerCase();
		Runtime rt = Runtime.getRuntime();
		if(systemType.indexOf("linux")>=0){
			savePath=pd.getString("linuxpath");
			mysqlPath=pd.getString("linuxMysqlPath");
			
		}
		if(systemType.indexOf("windows")>=0){
			savePath=pd.getString("windowpath");
			mysqlPath=pd.getString("windowsMysqlPath");
			command=mysqlPath+" -u"+dbUser+" -p"+dbPwd+" "+dbName;
			savePath+="vod"+new SimpleDateFormat("yyyyMMdd").format(new Date())+".sql";
			 
			 Process child = rt.exec(command);
            InputStream in = child.getInputStream();
            InputStreamReader input = new InputStreamReader(in,"utf8");
            
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            
            BufferedReader br = new BufferedReader(input);
            while ((inStr = br.readLine()) != null) {     
                sb.append(inStr + "\r\n");     
            }     
            outStr = sb.toString(); 
            
            FileOutputStream fout = new FileOutputStream(savePath);
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");    
            writer.write(outStr);
            writer.flush();   
            
            in.close();     
            input.close();     
            br.close();     
            writer.close();     
            fout.close();     
			 
		}
		return true;
	}
	
	/**
	 * 计算文件夹下文件数量不超过10000的文件夹的名字
	 */
	public static String obtainDirectoryName(String rootPath){
		File rootDir=new File(rootPath);
		//获取指定根文件夹下的所有文件夹
		File[] dirs=rootDir.listFiles();
		File destDir=null;
		File tempDir=null;
		String savePath=null;
		for (int i = 0; i < dirs.length; i++) {
			tempDir=dirs[i];
			//获取这些文件夹下拥有10000个文件的文件夹对象
			if(tempDir.isDirectory()&&tempDir.listFiles().length<10000){
				destDir=tempDir;
			}
			tempDir=null;
		}
		if(destDir==null){
			savePath=System.currentTimeMillis()+"";
			destDir=new File(rootPath+Const.FILE_SEPLATOR+savePath);
			if(!destDir.exists()){
				destDir.mkdirs();
			}
		}else{
			savePath=destDir.getName();
		}
		dirs=null;
		rootDir=null;
		//返回该文件夹的名字
		return savePath;
	}
}
