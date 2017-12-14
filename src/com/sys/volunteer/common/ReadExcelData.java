package com.sys.volunteer.common;

import java.util.List;
import java.util.Map;

import jxl.Sheet;

public abstract class ReadExcelData {

	/**
	 * 每个类都要实现该方法，获得集合对象
	 * map作为外部传入的参数
	 * @return
	 */
	public abstract List getExcelList(int beginRow,Map map,Sheet sheet) throws Exception;

	/**
	 * 作为模板方法，继承该类的子类获得excel列表，有特殊要求时，每个子类再具体实现该方法
	 * 
	 * @param filtPath
	 * @param sheetIndex
	 * @param hearderTitle
	 * @param beginRow
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	public List getExcelList(String filtPath, int sheetIndex,
			String hearderTitle[], int beginRow,Map map) throws Exception {
		
		Sheet sheet = ExcelTool.getSheetByFilePath(filtPath, sheetIndex);
		
		ExcelTool.checkExcelHeader(hearderTitle, sheet, beginRow);
		
		List list = this.getExcelList(beginRow, map,sheet);
		return list;
	}
}
