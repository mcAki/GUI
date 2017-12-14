package com.sys.volunteer.common;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public abstract class WriteExcelData {

	/**
	 * 写excel的模板方法
	 * @param outputStream
	 * @param title
	 * @param dataMap
	 * @param dataList
	 * @throws Exception 
	 */
	public void writeExcelData(OutputStream outputStream,String title,Map dataMap,List dataList) throws Exception{
		WritableWorkbook workbook = Workbook.createWorkbook(outputStream);
		try {			
			WritableSheet sheet = workbook.createSheet(title, 0);
			// 标题样式
			WritableFont headerFont = new WritableFont(WritableFont
					.createFont("黑体"), 12, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE);
			WritableCellFormat headerFormat = new WritableCellFormat(headerFont);
			headerFormat.setAlignment(jxl.format.Alignment.CENTRE);

			// 表头样式
			WritableFont titleFont = new WritableFont(WritableFont
					.createFont("宋体"), 10, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
			titleFormat.setAlignment(jxl.format.Alignment.CENTRE);
			titleFormat.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			// 内容样式
			WritableFont detFont = new WritableFont(
					WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			WritableCellFormat detFormat = new WritableCellFormat(detFont);
			detFormat.setAlignment(jxl.format.Alignment.CENTRE);
			detFormat.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			this.writeExcelData(dataMap, dataList, sheet, headerFormat, titleFormat, detFormat);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
			
		}
		finally{
			workbook.write();
			workbook.close();
		}
		
		
	}
	
	/**
	 * 向excel写内容
	 * @param colIndex
	 * @param rowIndex
	 * @param str
	 * @param format
	 * @param l
	 * @param sheet
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public void addCell(int colIndex,int rowIndex,int mergeColCells,int mergeRowsCells,String str,WritableCellFormat format,WritableSheet sheet) throws RowsExceededException, WriteException{
		if(mergeColCells!=0 || mergeRowsCells!=0)
			sheet.mergeCells(colIndex,rowIndex, mergeColCells, mergeRowsCells);
		Label l  = new Label(colIndex, rowIndex, str, format);
		sheet.addCell(l);
	}
	
	public abstract void writeExcelData(Map dataMap,List dataList,WritableSheet sheet,WritableCellFormat headerFormat,WritableCellFormat titleFont,WritableCellFormat detFont)throws Exception;
}
