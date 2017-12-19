package com.sys.volunteer.common;

import org.displaytag.decorator.TableDecorator;
/**
 * display标签扩展：鼠标经过行变色功能[超级简单、通用] 
 * 
 * @author Administrator
 *
 */
public class OverOutWrapperUtils extends TableDecorator {

	public OverOutWrapperUtils() {
		super();
	}

	@Override
	public String addRowId() {
		return "i_d\" onmouseover=\"if (typeof(window.m_over)=='function') window.m_over(this);\" onmouseout=\"if (typeof(window.m_out)=='function') window.m_out(this);\" title=\"";
	}

}
