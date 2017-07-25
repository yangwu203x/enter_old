package cn.ogsu.vod.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 解析excel的辅助类
 * @author enter
 * @date 2016年10月10日
 */
public class ExcelUtil {
	
	/**
	 * 导出excel 数据来源于data
	 * @param data
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "resource" })
	public static void exportExcel(List<PageData> data,OutputStream output) throws Exception{
		//创建excel的文档对象
		HSSFWorkbook excel=new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=excel.createSheet();
		//代表excel的行对象
		HSSFRow row=null;
		//代表excel的列对象
		HSSFCell column=null;
		int index=0;
		for (int i = 0; i <data.size()+1; i++) {
			//创建excel行
			row=sheet.createRow(i);
			PageData pd=data.get(index);
			//获取map集合中所有的key
			Set collect=pd.keySet();
			int j=collect.size()-1;
			//创建表格标题
			if(i==0){
				for (Object obj:collect) {
					column=row.createCell(j);
					column.setCellValue(obj.toString());
					j--;
				}
			}else{
				j=collect.size()-1;
				for (Object obj:collect) {
					//创建excel列
					column=row.createCell(j);
					String value=pd.get(obj).toString();
					//设置单元格的值
					column.setCellValue(value);
					j--;
				}
				index++;
			}
		}
		excel.write(output);
		output.flush();
	}
	
	/**
	 * 导入excel 数据来源于excel
	 * @param stream
	 * @return
	 * @throws Exception
	 */
	public static List<PageData> importExcel(InputStream stream) throws Exception{
		List<PageData> pds=new ArrayList<>();
		List<String> title=new ArrayList<>();
		PageData pd=null;
		//根据指定的文件输入流导入Excel从而产生Workbook对象
		Workbook excel = new HSSFWorkbook(stream);
		//获取Excel文档中的第一个表单
		Sheet sheet = excel.getSheetAt(0);
		//是否在读取表格的第一行
		boolean rowInOne=true;
		//对Sheet中的每一行进行迭代
        for (Row row : sheet) {
        	if(!rowInOne){
        		int rowNum=0;
        		pd=new PageData();
        		for (Cell cell : row) {
    				String cellValue=cell.toString().trim();
    				String cellTitle=title.get(rowNum);
    				if(cellValue.indexOf("E")>-1&&validateField(cellTitle)){
    					DecimalFormat df = new DecimalFormat("0");  
    					cellValue = df.format(cell.getNumericCellValue()); 
    				}
    				if(cellValue.endsWith(".0")){
    					cellValue=cellValue.substring(0,cellValue.indexOf(".0"));
    				}
        			pd.put(cellTitle, cellValue);
        			rowNum++;
    			}
        		pds.add(pd);
        	}else{
        		for (Cell cell : row) {
            		String cellTitle=cell.getStringCellValue();
            		//添加标题
                	title.add(cellTitle);
    			}
        		rowInOne=false;
        	}
		}
        excel.close();
		return pds;
	}
	
	/**
	 * 跳过检查歌曲名和歌曲拼写
	 * @param fieldName
	 * @return
	 */
	public static boolean validateField(String fieldName){
		if(fieldName.equals("song_name")||fieldName.equals("song_spell")){
			return false;
		}
		return true;
	}
}

/*package cn.ogsu.vod.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import cn.ogsu.vod.entity.TemplateSong;

*//**
 * 解析excel的辅助类
 * @author enter
 * @date 2016年10月10日
 *//*
public class ExcelUtil {
	
	*//**
	 * 导出excel 数据来源于data
	 * @param data
	 * @throws Exception
	 *//*
	@SuppressWarnings("resource")
	public static void exportExcel(List<TemplateSong> songs,OutputStream output) throws Exception{
		//创建excel的文档对象
		HSSFWorkbook excel=new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=excel.createSheet();
		//代表excel的行对象
		HSSFRow row=null;
		//代表excel的列对象
		HSSFCell column=null;
		//创建excel的标题
		row=sheet.createRow(0);
		//准备列标题
		String[] columnTitles={"song_format","song_no","song_name","song_sz","spell","song_order",
				"lang_id","nature","scene","pixel","song_years","theme_id","classic_status",
				"type_id","volume_one","volume_two","strack","singer_id_one","singer_id_two",
				"singer_id_three","song_info","receiving_date"};
		for (int i = 0; i < columnTitles.length; i++) {
			column=row.createCell(i);
			column.setCellValue(columnTitles[i]);
		}
		TemplateSong song=null;
		for (int i = 0; i <songs.size(); i++) {
			song=songs.get(i);
			row=sheet.createRow((i+1));
			column=row.createCell(i);
			column.setCellValue(song.getSongFormat());
			column=row.createCell(1);
			column.setCellValue(song.getSongId());
			column=row.createCell(2);
			column.setCellValue(song.getSongName());
			column=row.createCell(3);
			column.setCellValue(song.getSongSz());
			column=row.createCell(4);
			column.setCellValue(song.getSpell());
			column=row.createCell(5);
			column.setCellValue(song.getSongOrder());
			column=row.createCell(6);
			column.setCellValue(song.getLangId());
			column=row.createCell(7);
			column.setCellValue(song.getNature());
			column=row.createCell(8);
			column.setCellValue(song.getScene());
			column=row.createCell(9);
			column.setCellValue(song.getPixel());
			column=row.createCell(10);
			column.setCellValue(song.getSongYears());
			column=row.createCell(11);
			column.setCellValue(song.getThemeId());
			column=row.createCell(12);
			column.setCellValue(song.getClassicStatus());
			column=row.createCell(13);
			column.setCellValue(song.getTypeId());
			column=row.createCell(14);
			column.setCellValue(song.getVolumeOne());
			column=row.createCell(15);
			column.setCellValue(song.getVolumeTwo());
			column=row.createCell(16);
			column.setCellValue(song.getStrack());
			column=row.createCell(17);
			column.setCellValue(song.getSingerIdOne());
			column=row.createCell(18);
			column.setCellValue(song.getSingerIdTwo());
			column=row.createCell(19);
			column.setCellValue(song.getSingerIdThree());
			column=row.createCell(20);
			column.setCellValue(song.getSongInfo());
			column=row.createCell(21);
			column.setCellValue(song.getReceivingDate());
		}
		excel.write(output);
		output.flush();
	}
	
	*//**
	 * 导入excel 数据来源于excel
	 * @param stream
	 * @return
	 * @throws Exception
	 *//*
	public static List<PageData> importExcel(InputStream stream) throws Exception{
		List<PageData> pds=new ArrayList<>();
		List<String> title=new ArrayList<>();
		PageData pd=null;
		//根据指定的文件输入流导入Excel从而产生Workbook对象
		Workbook excel = new HSSFWorkbook(stream);
		//获取Excel文档中的第一个表单
		Sheet sheet = excel.getSheetAt(0);
		//是否在读取表格的第一行
		boolean rowInOne=true;
		//对Sheet中的每一行进行迭代
        for (Row row : sheet) {
        	if(!rowInOne){
        		int rowNum=0;
        		pd=new PageData();
        		for (Cell cell : row) {
    				String cellValue=cell.toString().trim();
    				String cellTitle=title.get(rowNum);
    				if(cellTitle.equals("song_info")||cellTitle.equals("song_stroke")){
    					if(cellValue.equals("0.0")){
    						cellValue="";
    					}
    				}
    				if(cellValue.indexOf("E")>-1&&validateField(cellTitle)){
    					DecimalFormat df = new DecimalFormat("0");  
    					cellValue = df.format(cell.getNumericCellValue()); 
    				}
    				if(cellValue.endsWith(".0")){
    					cellValue=cellValue.substring(0,cellValue.indexOf(".0"));
    				}
        			pd.put(cellTitle, cellValue);
        			rowNum++;
    			}
        		pds.add(pd);
        	}else{
        		for (Cell cell : row) {
            		String cellTitle=cell.getStringCellValue();
            		//添加标题
                	title.add(cellTitle);
    			}
        		rowInOne=false;
        	}
		}
        excel.close();
		return pds;
	}
	
	*//**
	 * 跳过检查歌曲名和歌曲拼写
	 * @param fieldName
	 * @return
	 *//*
	public static boolean validateField(String fieldName){
		if(fieldName.equals("song_name")||fieldName.equals("song_spell")){
			return false;
		}
		return true;
	}
}
*/