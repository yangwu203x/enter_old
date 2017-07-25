package cn.ogsu.vod.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil {

	public static boolean uploadToFtp(String url,int port,String username,String passwd,
			String path,String fileName,InputStream inputStream) throws IOException{
		boolean flag=false;
		FTPClient ftp=new FTPClient();
		try {
			if(port>-1){
				ftp.connect(url,port);
			}else{
				ftp.connect(url);//ftp默认的端口是21
			}
			if(ftp.login(username,passwd)){
				ftp.enterLocalActiveMode();
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				//创建目录,如果存在会返回失败
				ftp.makeDirectory(path);
				//切换目录
				ftp.changeWorkingDirectory(path);
				//上传文件 
				//FTP协议规定文件编码格式为ISO-8859-1
				fileName=new String(fileName.getBytes("utf-8"),"ISO-8859-1");
				OutputStream out=ftp.storeFileStream(fileName);
				byte[]byteArray=new byte[4096];
				int read=0;
				while((read=inputStream.read(byteArray))!=-1){
					out.write(byteArray,0,read);
				}
				out.close();
				ftp.logout();
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ftp.isConnected()){
				ftp.disconnect();
			}
		}
		return flag;
	}
	
	public static boolean downloadFromFtp(String url,int port,String 
			username,String password,String path,String fileName,String localpath) throws IOException{
		boolean flag=false;
		FTPClient ftp=new FTPClient();//org.apache.commons.net.ftp
		int reply;
		try {
			if(port>-1){
				ftp.connect(url,port);
			}else{
				ftp.connect(url);//ftp默认的端口是21
			}
			//很多人写的是用ftp.getReplyCode()给获取连接的返回值,但是这样会导致storeFileStream返回null
			ftp.login(username,password);
			ftp.enterLocalActiveMode();
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			reply=ftp.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)){
				ftp.disconnect();
				return flag;
			}
			//切换目录 此处可以判断,切换失败就说明ftp上面没有这个路径
			ftp.changeWorkingDirectory(path);
			//上传文件 
			OutputStream out=null;
			InputStream in=null;
			//创建本地的文件时候要把编码格式转回来
			fileName=new String(fileName.getBytes("ISO-8859-1"),"utf-8");
			File localFile=new File(localpath+fileName);
			out=new FileOutputStream(localFile);
			in=ftp.retrieveFileStream(fileName);
			byte[]byteArray=new byte[4096];
			int read=0;
			while((read=in.read(byteArray))!=-1){
				out.write(byteArray,0,read);
			}
			//这句很重要 要多次操作这个ftp的流的通道,要等他的每次命令完成
			ftp.completePendingCommand();
			out.flush();
			out.close();
			ftp.logout();
			flag=true;
		} catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(ftp.isConnected()){
				ftp.disconnect();
			}
		}
		return flag;
	}
}
