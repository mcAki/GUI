package com.sys.volunteer.export;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.displaytag.Messages;
import org.displaytag.exception.BaseNestableJspTagException;
import org.displaytag.exception.SeverityEnum;
import org.displaytag.export.BinaryExportView;
import org.displaytag.model.Column;
import org.displaytag.model.ColumnIterator;
import org.displaytag.model.HeaderCell;
import org.displaytag.model.Row;
import org.displaytag.model.RowIterator;
import org.displaytag.model.TableModel;


/**
 * Excel exporter using POI HSSF.
 * @author Fabrizio Giustina
 * @author rapruitt
 * @version $Revision: 1143 $ ($Author: fgiust $)
 */
public class ExcelHssfView implements BinaryExportView
{

    /**
     * TableModel to render.
     */
    private TableModel model;

    /**
     * export full list?
     */
    private boolean exportFull;

    /**
     * include header in export?
     */
    private boolean header;

    /**
     * decorate export?
     */
    private boolean decorated;

    private int count = 0;
    
    private  int temp = 1;
    
    private OutputStream outTemp;
    
    private  HSSFWorkbook wb = new HSSFWorkbook();
    
    private int cellNum = 5000;
    /**
     * @see org.displaytag.export.ExportView#setParameters(TableModel, boolean, boolean, boolean)
     */
    public void setParameters(TableModel tableModel, boolean exportFullList, boolean includeHeader,
        boolean decorateValues)
    {
        this.model = tableModel;
        this.exportFull = exportFullList;
        this.header = includeHeader;
        this.decorated = decorateValues;
    }

    /**
     * @return "application/vnd.ms-excel"
     * @see org.displaytag.export.BaseExportView#getMimeType()
     */
    public String getMimeType()
    {
        return "application/vnd.ms-excel"; //$NON-NLS-1$
    }

    /**
     * @see org.displaytag.export.BinaryExportView#doExport(OutputStream)
     */
    
    
    public void doExportTemp(OutputStream out){
    	
    }
    
    public void doExport(OutputStream out) throws JspException
    {
    	outTemp = out;
        try
        {
            HSSFSheet sheet = wb.createSheet("sheet"+count);

            int rowNum = 0;
            int colNum = 0;

            if (this.header)
            {
                // Create an header row
                HSSFRow xlsRow = sheet.createRow(rowNum++);

                HSSFCellStyle headerStyle = wb.createCellStyle();
                headerStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);
                headerStyle.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
                HSSFFont bold = wb.createFont();
                bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                bold.setColor(HSSFColor.WHITE.index);
                headerStyle.setFont(bold);

                Iterator iterator = this.model.getHeaderCellList().iterator();

                while (iterator.hasNext())
                {
                    HeaderCell headerCell = (HeaderCell) iterator.next();

                    String columnHeader = headerCell.getTitle();

                    if (columnHeader == null)
                    {
                        columnHeader = StringUtils.capitalize(headerCell.getBeanPropertyName());
                    }

                    HSSFCell cell = xlsRow.createCell(colNum++);
                    cell.setCellValue(new HSSFRichTextString(columnHeader));
                    cell.setCellStyle(headerStyle);
                }
            }

            // get the correct iterator (full or partial list according to the exportFull field)
            RowIterator rowIterator = this.model.getRowIterator(this.exportFull);
            // iterator on rows
           List<RowIterator> list = new ArrayList<RowIterator>();
            while (rowIterator.hasNext())
            {
            	
            	 Row row = rowIterator.next();
                 HSSFRow xlsRow = sheet.createRow(rowNum++);
                 colNum = 0;

                 // iterator on columns
                 ColumnIterator columnIterator = row.getColumnIterator(this.model.getHeaderCellList());

                 while (columnIterator.hasNext())
                 {
                 	
                     Column column = columnIterator.nextColumn();

                     // Get the value to be displayed for the column
                     Object value = column.getValue(this.decorated);

                     HSSFCell cell = xlsRow.createCell(colNum++);
                     writeCell(value, cell);
                 }
//                 temp++;
//             	if(temp ==cellNum){
//             		addSheet(rowIterator);
//             		break;
//             	}
             	
            }

            // adjust the column widths
            int colCount = 0;
            while (colCount <= colNum)
            {
                sheet.autoSizeColumn((short) colCount++);
            }

            wb.write(outTemp);
        }
        catch (Exception e)
        {
            throw new ExcelGenerationException(e);
        }
    }
    
   
    
    /**
     * 超过一定行数增加一个表格
     * @param out
     * @throws JspException
     */
    public void addSheet(RowIterator rowIterator) throws JspException
    {
    	temp = 0;
    	count ++;
    	try
    	{
    		
    		HSSFSheet sheet = wb.createSheet("sheet"+count);
    		
    		int rowNum = 0;
    		int colNum = 0;
    			// Create an header row
    		if (this.header)
            {
                // Create an header row
                HSSFRow xlsRow = sheet.createRow(rowNum++);

                HSSFCellStyle headerStyle = wb.createCellStyle();
                headerStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);
                headerStyle.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
                HSSFFont bold = wb.createFont();
                bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                bold.setColor(HSSFColor.WHITE.index);
                headerStyle.setFont(bold);

                Iterator iterator = this.model.getHeaderCellList().iterator();

                while (iterator.hasNext())
                {
                    HeaderCell headerCell = (HeaderCell) iterator.next();

                    String columnHeader = headerCell.getTitle();

                    if (columnHeader == null)
                    {
                        columnHeader = StringUtils.capitalize(headerCell.getBeanPropertyName());
                    }

                    HSSFCell cell = xlsRow.createCell(colNum++);
                    cell.setCellValue(new HSSFRichTextString(columnHeader));
                    cell.setCellStyle(headerStyle);
                }
            }
    	
    		// get the correct iterator (full or partial list according to the exportFull field)
//    		RowIterator rowIterator = this.model.getRowIterator(this.exportFull);
    		// iterator on rows
    		
    		while (rowIterator.hasNext())
    		{
    			
            	
    			Row row = rowIterator.next();
    			HSSFRow xlsRow2 = sheet.createRow(rowNum++);
    			colNum = 0;
    			
    			// iterator on columns
    			ColumnIterator columnIterator = row.getColumnIterator(this.model.getHeaderCellList());
    			
    			while (columnIterator.hasNext())
    			{
    				Column column = columnIterator.nextColumn();
    				
    				// Get the value to be displayed for the column
    				Object value = column.getValue(this.decorated);
    				
    				HSSFCell cell = xlsRow2.createCell(colNum++);
    				
    				writeCell(value, cell);
    			}
    			
    			temp++;
            	if(temp ==cellNum){
            		addSheet(rowIterator);
            		return;
            	}
    		}
    		
    		wb.write(outTemp);
    	}
    	catch (Exception e)
    	{
    		throw new ExcelGenerationException(e);
    	}
    }

    /**
     * Write the value to the cell. Override this method if you have complex data types that may need to be exported.
     * @param value the value of the cell
     * @param cell the cell to write it to
     */
    protected void writeCell(Object value, HSSFCell cell)
    {
        if (value instanceof Number)
        {
            Number num = (Number) value;
            cell.setCellValue(num.doubleValue());
        }
        else if (value instanceof Date)
        {
            cell.setCellValue((Date) value);
        }
        else if (value instanceof Calendar)
        {
            cell.setCellValue((Calendar) value);
        }
        else
        {
            cell.setCellValue(new HSSFRichTextString(escapeColumnValue(value)));
        }
    }

    // patch from Karsten Voges
    /**
     * Escape certain values that are not permitted in excel cells.
     * @param rawValue the object value
     * @return the escaped value
     */
    protected String escapeColumnValue(Object rawValue)
    {
        if (rawValue == null)
        {
            return null;
        }
        String returnString = ObjectUtils.toString(rawValue);
        // escape the String to get the tabs, returns, newline explicit as \t \r \n
        returnString = StringEscapeUtils.escapeJava(StringUtils.trimToEmpty(returnString));
        // remove tabs, insert four whitespaces instead
        returnString = StringUtils.replace(StringUtils.trim(returnString), "\\t", "    ");
        // remove the return, only newline valid in excel
        returnString = StringUtils.replace(StringUtils.trim(returnString), "\\r", " ");
        // unescape so that \n gets back to newline
        returnString = StringEscapeUtils.unescapeJava(returnString);
        return returnString;
    }

    /**
     * Wraps IText-generated exceptions.
     * @author Fabrizio Giustina
     * @version $Revision: 1143 $ ($Author: fgiust $)
     */
    static class ExcelGenerationException extends BaseNestableJspTagException
    {

        /**
         * D1597A17A6.
         */
        private static final long serialVersionUID = 899149338534L;

        /**
         * Instantiate a new PdfGenerationException with a fixed message and the given cause.
         * @param cause Previous exception
         */
        public ExcelGenerationException(Throwable cause)
        {
            super(ExcelHssfView.class, Messages.getString("ExcelView.errorexporting"), cause); //$NON-NLS-1$
        }

        /**
         * @see org.displaytag.exception.BaseNestableJspTagException#getSeverity()
         */
        public SeverityEnum getSeverity()
        {
            return SeverityEnum.ERROR;
        }
    }

}
