package com.sys.volunteer.baseaction;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.exception.SystemException;
import com.sys.volunteer.json.JsonUtil;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.vo.UrlLinks;

/**
 * Action基础类,所有Action必须继承此action
 * 
 * @author Seraph.Yang
 * 
 */
public abstract class BaseAction extends ActionSupport {

	private static final long serialVersionUID = -904820719104480572L;

	public final static byte MSG_TYPE_NORMAL = 0;
	public final static byte MSG_TYPE_QUESTION = 1;
	public final static byte MSG_TYPE_NOTICE = 2;
	public final static byte MSG_TYPE_WARNING = 3;
	public final static byte MSG_TYPE_STOP = 4;

	public final static byte OP_TOKEN_NONE = 0;
	public final static byte OP_TOKEN_ADD = 1;
	public final static byte OP_TOKEN_UPDATE = 2;
	public final static byte OP_TOKEN_DEL = 3;
	public final static byte OP_TOKEN_LIST = 4;

	public final static String NOCACHE_HEADER_NAME = "Cache-Control";
	public final static String NOCACHE_HEADER_VALUE = "no-cache";

	private int id;

	protected String uuid;

	/**
	 * Logger.debug(Object message);//调试信息</br> Logger.info(Object message);//一般信息</br>
	 * Logger.warn(Object message);//警告信息</br> Logger.error(Object message);//错误信息</br>
	 * Logger.fatal(Object message);//致命错误信息
	 */
	protected Log log = LogFactory.getLog(this.getClass());
	protected PageView pageView;
	private String EXCEL_CONTENT_TYPE = "application/x-msexcel";
	/**
	 * 操作标记
	 */
	private byte operationToken;

	/**
	 * 发送消息,使用: return ShowMessage....
	 * 
	 * @param messageType
	 *            样式类型
	 * @param messageData
	 *            消息内容,可以用HTML
	 * @param redirectCaption
	 *            链接标题
	 * @param redirectUrl
	 *            链接地址
	 * @param redirectDelaySec
	 *            自动重定向时间
	 * @return
	 */
	public String ShowMessage(byte messageType, String messageData, String redirectCaption, String redirectUrl, int redirectDelaySec) {
		ActionContext.getContext().put("isMultipleMsg", false);
		ActionContext.getContext().put("message", messageData);

		return ShowMessage(messageType, messageData, redirectDelaySec, new UrlLinks(
			redirectCaption, redirectUrl));
	}

	/**
	 * 发送消息,使用: return ShowMessage....
	 * 
	 * @param messageType
	 *            样式类型
	 * @param messageData
	 *            消息内容,可以用HTML
	 * @param redirectDelaySec
	 *            自动重定向时间
	 * @param msgLinks
	 *            链接包(可添加多个)
	 * @return
	 */
	public String ShowMessage(byte messageType, String messageData, int redirectDelaySec, UrlLinks... msgLinks) {
		ActionContext.getContext().put("isMultipleMsg", true);
		ActionContext.getContext().put("message", messageData);
		// ActionContext.getContext().put("redirectDelaySec", redirectDelaySec);

		StringBuilder sb = new StringBuilder();
		if (msgLinks.length > 0) {
			for (int i = 0; i < msgLinks.length; i++) {
				sb.append("<li><a style='font-size: 14px;font-weight: blod;");
				sb.append(msgLinks[i].getStyle());
				sb.append("' class='msgLinksA'");
				sb.append(" target='");
				sb.append(msgLinks[i].isBlank() ? "_blank" : "_self");
				sb.append("' href='");
				sb.append(msgLinks[i].getUrl());
				sb.append("'>");
				sb.append(msgLinks[i].getCaption());
				sb.append("</a><li>");
			}
		} else {
			// todo:有时间处理一下这里默认返回
		}
		ActionContext.getContext().put("links", sb.toString());

		sb = new StringBuilder();
		if (redirectDelaySec > 0) {
			sb.append("<span id='idAutoJump'></span>");
			sb.append("<script type='text/javascript'>var n=" + redirectDelaySec + ";");
			sb.append("function mySetTime(){if(n>0){");
			sb.append("document.getElementById('idAutoJump').innerHTML='");
			sb.append("系统将在 <b>' + n + '</b> 秒后转至");
			sb.append(msgLinks[0].getCaption());
			sb.append("'; n--; setTimeout('mySetTime()',1000);");
			sb.append("}else{window.location.href='");
			sb.append(msgLinks[0].getUrl());
			sb.append("';}} mySetTime();</script>");
		}

		ActionContext.getContext().put("autoRedirect", sb.toString());

		return "showmessage";
	}

	public Map getApplication() {
		ActionContext ctx = ActionContext.getContext();
		return ctx.getApplication();
	}

	public Map getSession() {
		ActionContext ctx = ActionContext.getContext();
		return ctx.getSession();
	}

	public Users getSessionUser() {
		ActionContext ctx = ActionContext.getContext();
		Users user = (Users) ctx.getSession().get(Const.USER_SESSION_KEY);
		if (null == user) {
			throw new SystemException("请先登陆再使用");
		}
		return user;
	}

	/**
	 * 郭氏ACTION获取REQUEST方法 (注释此人没写-_-凸)
	 * 
	 * @return
	 */
	public HttpServletRequest getHttpServletRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 郭氏ACTION获取Context方法 (注释此人没写-_-凸)
	 * 
	 * @return
	 */
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	/**
	 * 郭氏ACTION获取Response方法 (注释此人没写-_-凸)
	 * 
	 * @return
	 */
	public HttpServletResponse getHttpServletResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 郭氏ACTION获取ContextObj方法 (注释此人没写-_-凸)
	 * 
	 * @return
	 */
	public Object getServletContextObj(String name) {
		return ServletActionContext.getServletContext().getAttribute(name);
	}

	/**
	 * 绕过Template,直接输出内容的简便函数.
	 */
	protected String render(String text, String contentType) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * 直接输出字符串.
	 */
	protected String renderText(String text) {
		return render(text, "text/plain;charset=UTF-8");
	}

	/**
	 * 直接输出字符串GBK编码.
	 */
	protected String renderHtmlGBK(String html) {
		return render(html, "text/html;charset=GBK");
	}

	/**
	 * 直接输出XML.
	 */
	protected String renderXML(String xml) {
		return render(xml, "text/xml;charset=UTF-8");
	}

	protected void setExportExcelResponse(String fileName) throws UnsupportedEncodingException {
		this.getHttpServletResponse().reset();
		this.getHttpServletResponse().setContentType(this.EXCEL_CONTENT_TYPE);
		this.getHttpServletResponse().setHeader("Content-disposition",
			"attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
	}

	/**
	 * 利用页面传过来的ID值删除或者更新列表,先不要批量删除
	 * 
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 输出JSON List格式的数据
	 * 
	 * @param list
	 * @param excludes
	 * @throws IOException
	 */
	protected void writeJsonList(List<?> list, String[] excludes) throws IOException {
		this.writeJsonList(list, excludes, DateUtil.CM_SHORT_DATE_FORMAT);
	}

	/**
	 * 输出JSON Object格式数据
	 * 
	 * @param object
	 * @param excludes
	 * @throws IOException
	 */
	protected void writeJsonObject(Object object, String[] excludes) throws IOException {
		JSONObject jsonObject = JSONObject.fromObject(object, JsonUtil.configJson(excludes,
			DateUtil.CM_SHORT_DATE_FORMAT));
		writeAjaxStr(jsonObject.toString());
	}

	/**
	 * 直接用Json写出一串字符串
	 * 
	 * @param value
	 *            字符串
	 * @throws IOException
	 */
	protected void writeJsonString(String value) throws IOException {
		writeAjaxStr("{\"result\":\"" + value.toString() + "\"}");
	}

	/**
	 * 直接用Json写出一个字符串数组
	 * 
	 * @param value
	 *            字符串数组
	 * @throws IOException
	 */
	protected void writeJsonStrings(String[] value) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i = 0; i < value.length; i++) {
			sb.append("\"val");
			sb.append(i);
			sb.append("\":\"");
			sb.append(value[i].toString());
			sb.append("\"");
			if (i < value.length - 1) {
				sb.append(",");
			}
		}
		sb.append("}");
		writeAjaxStr(sb.toString());
	}

	/**
	 * 输出JSON List格式的数据
	 * 
	 * @param list
	 * @param excludes
	 * @param dateFormate
	 * @throws IOException
	 */
	protected void writeJsonList(List<?> list, String[] excludes, String dateFormate)
			throws IOException {
		JSONArray jsonObjects = JSONArray.fromObject(list, JsonUtil.configJson(excludes,
			dateFormate));
		writeAjaxStr(jsonObjects.toString());
	}

	/**
	 * 输出JSON Object格式数据
	 * 
	 * @param object
	 * @param excludes
	 * @param dateFormate
	 * @throws IOException
	 */
	protected void writeJsonObject(Object object, String[] excludes, String dateFormate)
			throws IOException {
		JSONObject jsonObject = JSONObject.fromObject(object, JsonUtil.configJson(excludes,
			dateFormate));
		this.getHttpServletResponse().getWriter().write(jsonObject.toString());
		this.getHttpServletResponse().getWriter().flush();
	}

	/**
	 * 输出json字符串到控制台，未带格式的
	 * 
	 * @param jsonStr
	 * @throws IOException
	 */
	protected void writeAjaxStr(String jsonStr) throws IOException {
		this.getHttpServletResponse().setContentType(JsonUtil.JSON_CONTENT_TYPE);
		// this.getHttpServletResponse().setHeader(NOCACHE_HEADER_NAME,
		// NOCACHE_HEADER_VALUE);
		this.getHttpServletResponse().getWriter().write(jsonStr);
		this.getHttpServletResponse().getWriter().flush();
	}

	/**
	 * 操作标记,为了区分提交时候的操作，作用相当于?OperationToken= 要在页面提交的Form内设置一个 <s:hidden name="OperationToken"/>
	 * 
	 * @return
	 */
	public byte getOperationToken() {
		return operationToken;
	}

	/**
	 * 操作标记,为了区分提交时候的操作，作用相当于?OperationToken= 要在页面提交的Form内设置一个 <s:hidden name="OperationToken"/>
	 * 
	 * @param operationToken
	 */
	public void setOperationToken(byte operationToken) {
		this.operationToken = operationToken;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 拷贝业务Bean
	 * 
	 * @param dest
	 *            目标pojo
	 * @param source
	 *            拷贝pojo源
	 */
	public void copyProperties(Object dest, Object source) {
		try {
			BeanUtils.copyProperties(dest, source);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getStrutsActionName() {
		return ActionContext.getContext().getActionInvocation().getInvocationContext().getName();
	}

	public String getStrutsActionNamespace() {
		return ActionContext.getContext().getActionInvocation().getProxy().getNamespace();
	}

	public String getStrutsActionMethod() {
		return ActionContext.getContext().getActionInvocation().getProxy().getMethod();
	}
	/**
	 * 3个属性由文件上传使用
	 */
	private File upload;

	private String uploadContentType;

	private String uploadFileName;

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * response设置 主要用于xloadtree的ajax方式使用
	 */
	public void setResponse() {
		this.getHttpServletResponse().setHeader("Cache-Control", "no-store");
		this.getHttpServletResponse().setDateHeader("Expires", 0);
		this.getHttpServletResponse().setHeader("Pragma", "no-cache");
	}
}
