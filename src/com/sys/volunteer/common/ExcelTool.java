package com.sys.volunteer.common;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.sys.volunteer.exception.SystemException;


/**
 * excel通用工具
 * @author guoxi
 *
 */
public class ExcelTool {

	/**
	 * 获得excel的Sheet
	 * @param filtPath
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static Sheet getSheetByFilePath(String filtPath,int sheetIndex) throws BiffException, IOException{
		Workbook workbook = Workbook.getWorkbook(new File(filtPath));
        Sheet sheet = workbook.getSheet(sheetIndex);
        return sheet;
	}
	
	/**
	 * 检查excel的头部，头部数组最好定义在const类中
	 * @param hearderTitle
	 * @param sheet
	 * @param beginRow
	 * @return
	 */
	public static boolean checkExcelHeader(String[] hearderTitle,Sheet sheet,int beginRow)throws SystemException{
		boolean tableHeadFlag = true;
        for (int i = 0; i < hearderTitle.length; i++)
        {
            String title = hearderTitle[i].trim();
            //beginRow开始读取的首行数
            if (!sheet.getCell(i, beginRow-1).getContents().trim().equals(title.trim()))
            {
               // System.out.println(sheet.getCell(i, beginRow).getCellFeatures().toString());
                throw new SystemException( "定义中的第"+i+"列["+hearderTitle[i].trim()+"] 不匹配上传的"+sheet.getCell(i, beginRow-1).getContents().trim());
            }
        }
        return tableHeadFlag;
	}
	
	/**
	 * 关闭Workbook
	 * @param workbook
	 */
	public static void closeWorkbook(Workbook workbook){
		workbook.close();
	}
	
}
