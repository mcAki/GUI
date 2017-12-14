package com.sys.volunteer.common;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.util.HashtableOfObject;

import antlr.collections.impl.Vector;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.org.apache.bcel.internal.generic.NEW;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WriteExcelDataEx {
	public static final String WFONT_HEADER = "header";
	public static final String WFORMAT_HEADER = "header";
	public static final String WFONT_TITLE = "title";
	public static final String WFORMAT_TITLE = "title";
	public static final String WFONT_FOOTER = "footer";
	public static final String WFORMAT_FOOTER = "footer";
	public static final String WFONT_CONTENT = "content";
	public static final String WFORMAT_CONTENT = "content";

	/**
	 * excel表
	 */
	private WritableWorkbook workbook;

	/**
	 * 现在写的工作sheet
	 */
	private WritableSheet writingSheet;

	/**
	 * 字体格式
	 */
	private Hashtable<String, WritableFont> writeFonts;

	/**
	 * 单元格格式
	 */
	private Hashtable<String, WritableCellFormat> writeFormats;

	/**
	 * 构造器
	 * 
	 * @param outputStream
	 *            文件输出流 一般可以使用：<br>
	 *            getHttpServletResponse().getOutputStream()
	 * @throws IOException
	 */
	/*
	 * public static WriteExcelDataEx getNewExcelInstance(OutputStream outputStream) throws
	 * IOException { WriteExcelDataEx ex = new WriteExcelDataEx(outputStream); return ex; }
	 */

	/**
	 * 增加一个工作Sheet
	 */
	public WritableSheet addSheet(String sheetName, int index) {
		return workbook.createSheet(sheetName, index);
	}

	/**
	 * 选择要写的工作Sheet
	 * 
	 * @param sheetName
	 */
	public void selectWritingSheet(String sheetName) {
		writingSheet = workbook.getSheet(sheetName);
	}

	/**
	 * 选择要写的工作Sheet
	 * 
	 * @param sheetName
	 */
	public void selectWritingSheet(int index) {
		writingSheet = workbook.getSheet(index);
	}

	public WritableFont createFont(String fontName, int Size, Colour colour, boolean isBlod, boolean isItalic, boolean isUnderline) {
		return new WritableFont(WritableFont.createFont(fontName), Size,
			isBlod ? WritableFont.BOLD : WritableFont.NO_BOLD, isItalic,
			isUnderline ? UnderlineStyle.SINGLE : UnderlineStyle.NO_UNDERLINE, colour);
	}

	public WritableCellFormat createFormat(WritableFont writableFont, Border border, BorderLineStyle borderLineStyle, Alignment alignment, VerticalAlignment verticalAlignment, boolean isAutoWarp) {
		WritableCellFormat wFormat = new WritableCellFormat(writableFont);
		try {
			wFormat.setAlignment(alignment);
			wFormat.setVerticalAlignment(verticalAlignment);
			wFormat.setBorder(border, borderLineStyle);
			wFormat.setWrap(isAutoWarp);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wFormat;
	}

	/**
	 * 构造器
	 * 
	 * @param outputStream
	 *            文件输出流 一般可以使用：<br>
	 *            getHttpServletResponse().getOutputStream()
	 * @throws IOException
	 * @throws WriteException
	 */
	public WriteExcelDataEx(OutputStream outputStream, String defaultSheetName) throws IOException,
			WriteException {
		workbook = Workbook.createWorkbook(outputStream);

		writingSheet = workbook.createSheet(defaultSheetName, 0);

		writeFonts = new Hashtable<String, WritableFont>();
		writeFormats = new Hashtable<String, WritableCellFormat>();

		WritableFont wFont;
		WritableCellFormat wFormat;
		// 标题样式
		wFont = new WritableFont(WritableFont.createFont("黑体"), 16, WritableFont.BOLD, false,
			UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
		wFormat = new WritableCellFormat(wFont);
		wFormat.setAlignment(jxl.format.Alignment.CENTRE);
		wFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

		writeFonts.put(WFONT_HEADER, wFont);
		writeFormats.put(WFORMAT_HEADER, wFormat);

		// 表头样式
		wFont = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD, false,
			UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
		wFormat = new WritableCellFormat(wFont);
		wFormat.setAlignment(jxl.format.Alignment.CENTRE);
		wFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

		writeFonts.put(WFONT_TITLE, wFont);
		writeFormats.put(WFORMAT_TITLE, wFormat);

		// 内容样式
		wFont = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false,
			UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
		wFormat = new WritableCellFormat(wFont);
		wFormat.setAlignment(jxl.format.Alignment.CENTRE);
		wFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

		writeFonts.put(WFONT_CONTENT, wFont);
		writeFormats.put(WFORMAT_CONTENT, wFormat);

	}

	/**
	 * 写excel的模板方法
	 * 
	 * @param outputStream
	 * @param title
	 * @param dataMap
	 * @param dataList
	 * @throws Exception
	 */
	public void saveExcelData() throws Exception {
		try {

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
			// s:what a egg pain code, after catch a throwing exception and throw again
		} finally {
			workbook.write();
			workbook.close();
		}

	}

	/**
	 * 向excel写内容
	 * 
	 * @param colIndex
	 * @param rowIndex
	 * @param mergeColCells
	 *            合并至第几列（如果行或列有填写的话，必须把2个都一起写）
	 * @param mergeRowsCells
	 *            合并至第几行
	 * @param str
	 * @param format
	 * @return
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public Label addLabel2Cell(int colIndex, int rowIndex, int mergeColCells, int mergeRowsCells, String str, WritableCellFormat format)
			throws RowsExceededException, WriteException {

		if (mergeColCells > 0 || mergeRowsCells > 0) {
			writingSheet.mergeCells(colIndex, rowIndex, mergeColCells, mergeRowsCells);
		}

		Label lab = new Label(colIndex, rowIndex, str, format);

		writingSheet.addCell(lab);
		// if (mergeColCells != 0 || mergeRowsCells != 0) {
		// writingSheet.getWritableCell(colIndex, rowIndex).setCellFormat(format);
		// }
		return lab;
	}

	/**
	 * 向excel写内容(带对齐)
	 * 
	 * @param colIndex
	 * @param rowIndex
	 * @param str
	 * @param format
	 * @param l
	 * @param sheet
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public Label addLabel2Cell(int colIndex, int rowIndex, int mergeColCells, int mergeRowsCells, String str, Alignment align, VerticalAlignment vAlign, WritableCellFormat format)
			throws RowsExceededException, WriteException {

		format.setAlignment(align);
		format.setVerticalAlignment(vAlign);
		Label lab = addLabel2Cell(colIndex, rowIndex, mergeColCells, mergeRowsCells, str, format);
		return lab;

	}

	/**
	 * 原来jxl那个有BUG,改变不了行高的
	 * 
	 * @param h
	 */
	public void setDefaultRowHeight(int h) {

		try {
			for (int i = 0; i < 65536; i++) {
				writingSheet.setRowView(i, h);
			}
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ========================================================================
	// below are java's system egg pain rules 'getter' and 'setter'
	// after you sit through below code you'll been carefree get egg pain
	// ========================================================================

	/**
	 * @return the workbook
	 */
	public WritableWorkbook getWorkbook() {
		return workbook;
	}

	/**
	 * @param workbook
	 *            the workbook to set
	 */
	public void setWorkbook(WritableWorkbook workbook) {
		this.workbook = workbook;
	}

	/**
	 * @return the writeFonts
	 */
	public Hashtable<String, WritableFont> getWriteFonts() {
		return writeFonts;
	}

	/**
	 * @param writeFonts
	 *            the writeFonts to set
	 */
	public void setWriteFonts(Hashtable<String, WritableFont> writeFonts) {
		this.writeFonts = writeFonts;
	}

	/**
	 * @return the writeFormats
	 */
	public Hashtable<String, WritableCellFormat> getWriteFormats() {
		return writeFormats;
	}

	/**
	 * @param writeFormats
	 *            the writeFormats to set
	 */
	public void setWriteFormats(Hashtable<String, WritableCellFormat> writeFormats) {
		this.writeFormats = writeFormats;
	}

	/**
	 * @return the writingSheet
	 */
	public WritableSheet getWritingSheet() {
		return writingSheet;
	}

	/**
	 * @param writingSheet
	 *            the writingSheet to set
	 */
	public void setWritingSheet(WritableSheet writingSheet) {
		this.writingSheet = writingSheet;
	}

}
