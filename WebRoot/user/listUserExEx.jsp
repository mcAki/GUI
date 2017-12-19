<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		
  <title>商户信息</title>
  <link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
     <link href="../css/main_new.css" rel="stylesheet" type="text/css" />
		
		<%@ include file="../common/incHead.jsp"%>
	    <%@ include file="../common/incBanner.jsp" %>
	    <script type="text/javascript" src="../js/divselect.js"></script>     
	<script type="text/javascript">
		function showPersonalByUserId(userId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showPersonalByUserId.do?userId='
						+ userId , 700, 600);
		}
		function m_over(tr) {
		tr.className = tr.className + " " + "trmo";
	}
	function m_out(tr) {
		var cn = tr.className.replace(/\s+trmo/, '');
		tr.className = cn;
	}
	
		function showPersonalByUserId2(userId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showPersonalByUserId4.do?userId='
						+ userId , 700, 600);
		}
	</script>
	 <style type="text/css">
	  .formtable2 td{
	     font-size: 14px;
	 }
	 
	 </style>
  </head>  
  <body style="min-width: 800px">
  <!--  
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="550" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>商户信息</caption>
  	  <tr>
  	    <td colspan="4"></td>
      </tr>
     
      <tr>
	   <td align="right" >当前用户级别:</td>
	   <td align="left"><s:label>
	    					<c:choose>
	    						<c:when test="${users.usergroup.id==1}">超级管理员</c:when>
	    						<c:when test="${users.usergroup.id==2}">员工</c:when>
	    						<c:when test="${users.usergroup.id==3}">供货商</c:when>
	    						<c:when test="${users.usergroup.id==4}">一级商户</c:when>
	    						<c:when test="${users.usergroup.id==5}">二级商户</c:when>
	    					</c:choose>
		  	    		</s:label>
						</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
      </tr>
     
  	  <tr>
      	<td width="121" align="right">经销商名称:</td>
  	    <td align="left"><s:label name="users.userName"/></td>
  	    <td>&nbsp;</td>
  	   	<td>&nbsp;</td>
  	  </tr>
  	  
  	  <tr>
  	   <td align="right">状态:</td>
  	   <td align="left" >
			<c:choose>
				<c:when test="${users.state==0}">正常</c:when>
				<c:when test="${users.state==1}">冻结</c:when>
				<c:when test="${users.state==2}">禁用</c:when>
			</c:choose>
		</td>
  	   <td>&nbsp;</td>
  	   <td>&nbsp;</td>
  	  </tr>
  	  
      <tr>
  	   <td align="right">上级商户:</td>
  	   <td align="left" ><s:label name="parentUser.userName"/></td>
  	   <td>&nbsp;</td>
  	   <td>&nbsp;</td>
  	  </tr>
      
  	  <tr>
	   <td align="right" >性&nbsp;别:</td>
	   <td align="left"><s:label>
	    					<c:choose>
	    						<c:when test="${users.gender==1}">男</c:when>
	    						<c:when test="${users.gender==2}">女</c:when>
	    					</c:choose>
		  	    		</s:label>
						</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
      </tr>
      
      <tr>
  	   <td align="right">email:</td>
  	   <td align="left" ><s:label name="users.email"/></td>
  	   <td>&nbsp;</td>
  	   <td>&nbsp;</td>
  	  </tr>
      
      <tr>
  	    <td align="right">手机号码:</td>
  	    <td align="left" ><s:label name="users.mobile"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">账户余额:</td>
  	    <td align="left" ><s:label name="useraccount.balance"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">佣金余额:</td>
  	    <td align="left" ><s:label name="useraccount.commission"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
       <tr>
        <td align="right">可冲正次数:</td>
  	   	<td align="left" ><s:label name="users.reversalCount"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">最大可冲正次数:</td>
  	   	<td align="left" ><s:label name="users.maxReversalCount"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">终端机号:</td>
  	    <td align="left" ><s:label name="users.terminalNo"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
       
       <tr>
        <td align="right" >备&nbsp;注:</td>
  	    <td align="left" colspan="3"><s:label name="users.remark" rows="3"/></td> 	  
       </tr>
		
  </table>
  -->
  <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
             <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj1 tqyj fixed">
                     <strong class="tqxj">会员信息</strong>
                     <div class="hyxx1 hyxx" style="width:600px; min-height:300px; padding-top:30px; margin: 0 auto; "> 
                        
                            <table width="100%" border="2" bordercolor="blue" cellpadding="1" cellspacing="1" class="formtable2" >
                                 <tr align="center">
                                   <td colspan="4" align="center" style="font-size: 16px;font-weight: bold;"> 账户资料 </td>
                                 </tr>
                                 <tr align="center">
                                    <td width="120">代理店名称：</td>
                                    <td> 未添加 </td>
                                    <td>代理登录帐号：</td>
                                    <td> ${users.loginName }</td>
                                 </tr>
                                 <tr align="center">
                                    <td>联系人姓名：</td>
                                    <td> ${users.userName } </td>
                                    <td>加密key编码：</td>
                                    <td>${users.otp.keyId }</td>
                                 </tr>
                                  <tr align="center">
                                    <td>代理上级帐号：</td>
                                    <td>${parentUser.userName }</td>
                                    <td>终 端 编 号：</td>
                                    <td>${users.terminalNo }</td>
                                 </tr>
                                  <tr align="center">
                                    <td>联系人电话：</td>
                                    <td>${users.mobile }</td>
                                    <td>代理账号级别：</td>
                                    <td>
                                    <c:choose>
			    						<c:when test="${users.usergroup.groupType==1}">
                                                                                                     超级管理员
			    						</c:when>
			    						<c:when test="${users.usergroup.groupType==2}">
			    						 员工
			    						</c:when>
			    						<c:when test="${users.usergroup.groupType==3}">
			    						 供货商
			    						</c:when>
			    						<c:when test="${users.usergroup.groupType==4}">
			    						 一级商户
			    						</c:when>
			    						<c:when test="${users.usergroup.groupType==5}">
			    						 二级商户
			    						</c:when>
	    					</c:choose>
                                    
                                    </td>
                                 </tr>
                                 <tr align="center">
                                    <td>所属用户组名称：</td>
                                    <td>${users.usergroup.groupName }</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                 </tr>
                                 <tr align="center">
                                    <td>开户日期：</td>
                                    <td><fmt:formatDate value="${users.createDate }" pattern="yyyy-MM-dd HH:mm" /> </td>
                                    <td>代理帐号状态：</td>
                                    <td>
                                     <c:choose>
											<c:when test="${users.state==0}">
											  正常
                                          </c:when>
											<c:when test="${users.state==1}"> 
										          冻结
											</c:when>
											<c:when test="${users.state==2}"> 
											     禁用
											</c:when>
										</c:choose>
                                    </td>
                                 </tr>
                                  <tr align="center">
                                  
                                    <td>QQ号码：</td>
                                    <td colspan="3">${users.qq }</td>
                                 </tr>
                                  <tr align="center">
                                  
                                    <td>备注：</td>
                                    <td colspan="3">${users.remark }</td>
                                 </tr>
                                  <tr align="center">
                                   <td>操作：</td>
                                   <td colspan="3">
                                    <c:if test="${sessionScope.user.usergroup.groupType==1
									 ||(sessionScope.user.usergroup.groupType<users.usergroup.groupType&&sessionScope.user.usergroup.groupType<=2)}">
									 	<a href="/MPRS/users/page!addExEx.do?userId=${users.userId}">增加二级商户</a>
										<a href="/MPRS/users/modifyUser!viewUser.do?userId=${users.userId}">修改</a>&nbsp;
										<c:choose>
											<c:when test="${users.state==0}">
												<a href="/MPRS/users/list!delUser.do?userId=${users.userId}"
													onclick=javascript: return del();>禁用</a>
											</c:when>
											<c:when test="${users.state==2}">
												<a href="/MPRS/users/list!recoverUser.do?userId=${users.userId}">恢复</a>
											</c:when>
										</c:choose>
										&nbsp;<a href="/MPRS/users/refreshReversal.do?userId=${users.userId}">更新冲正次数</a>&nbsp;
										&nbsp;<a href="/MPRS/users/commitAccount.do?userId=${users.userId}">更新账户余额</a>&nbsp;
										<a href="/MPRS/users/page!addKey.do?userId=${users.userId}">设置加密key</a>
									</c:if>
								  <c:if test="${sessionScope.user.usergroup.groupType<=2}">
                              		  <a href="/MPRS/users/page!addExEx.do?userId=${users.userId}">增加二级商户</a>
                              		  <a href="/MPRS/usersutils/resetBusinessPassword.do?userId=${users.userId}">重置交易密码</a>
                              		  <a href="/MPRS/usersutils/resetPassword.do?userId=${users.userId}">重置登录密码</a>
                              		  
                                  </c:if>
									 <c:if test="${sessionScope.user.usergroup.groupType==1
									 || (sessionScope.user.userId==users.parentUsers.userId)}">
										<a href="/MPRS/users/page!recharge.do?userId=${users.userId}">设置额度</a>&nbsp;
										<a href="${pageContext.request.contextPath}/otp/page!testPage.do?userId=${users.userId }">测试密保</a>
									</c:if>
									<c:if test="${sessionScope.user.usergroup.groupType==1
									 ||(sessionScope.user.usergroup.groupType<users.usergroup.groupType&&sessionScope.user.usergroup.groupType<=2)
									 || (sessionScope.user.userId==users.parentUsers.userId)
									 || (sessionScope.user.userId==users.userId)}">
									   <c:if test="${users.usergroup.groupType!=5}">
									 	<a href="/MPRS/users/list!manageUserEx.do?userId=${users.userId}">查看</a>&nbsp;
									   </c:if>
									 	<a href="/MPRS/users/doDrawingCommission.do?userId=${users.userId}"  onclick="return window.confirm('您确定要提取吗？')">提取佣金</a>
									</c:if>
                                   

                                <%--
                                <c:if test="${sessionScope.user.usergroup.id==4&&sessionScope.user.userId==users.userId}">
                                <a href="/MPRS/users/page!addExEx.do?userId=${users.userId}">增加二级商户</a>
								&nbsp;<a href="/MPRS/users/commitAccount.do?userId=${users.userId}">更新账户余额</a>&nbsp;
                                </c:if>
                                <c:if test="${sessionScope.user.userId==users.parentUsers.userId }">
                                   <a href="/MPRS/users/page!recharge.do?userId=${users.userId}">设置额度</a>&nbsp;
                                </c:if>--%>
                                </td>
                            
                             </table>    
                     </div>

             </div>
          </div>
      
			<c:if test="${pageView!=null}">
		<div class="tqyj fixed" style="width: 100%; margin: 0 auto;">
                     <div class="cxjj fixed">
                        <p class="jjcx">下级商户列表</p>
                          <div class="rechargey cxjjtable" style="width:900px;">
								<div align="center">
									<s:form id="form0" name="form0"
									action="list!manageUserEx" namespace="/users"
									method="post">
									 <div class="cxjjtable3">
										<table>
											<tr>
												<td>终端机编号:&nbsp;</td>
												
												<td>&nbsp;<input class="sjyz" type="text" name="terminalNo" size="20" value="${param.terminalNo}"/>&nbsp;</td>
															<input type="hidden" name="userId" value="${users.userId}"/></td>
												<td>经销商:&nbsp;</td>
												<td>&nbsp;<input type="text" class="sjyz" name="username" size="10" value="${param.username}"/>&nbsp;</td>
												<td>&nbsp;电话号码:&nbsp;</td>
												<td>&nbsp;<input type="text" class="sjyz" name="mobile" size="20" value="${param.mobile}"/>&nbsp;</td>
												<td>&nbsp;用户状态:&nbsp;</td>
												<td>
												<!-- 
												<s:select name="state" list="%{#{0:'正常',1:'冻结',2:'禁用'}}"/>
												 -->
												   <div id="divselect10" class="divselect3 divselect">
                                                              <cite>选择用户状态</cite>
                                                               <ul >
                                                                 <li><a href="javascript:;" selectid="0">正常</a></li>
                                                                 <li><a href="javascript:;" selectid="1">冻结</a></li>
                                                                 <li><a href="javascript:;" selectid="2">禁用</a></li>
                                                              </ul>
                                                      </div>
                                                      <input name="state" type="hidden" value="" id="inputselect"/>
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect10","#inputselect");
													});
													</script>
												</td>
												<td>&nbsp;<button   class="sousuo1" type="submit" value="搜索"></button> </td>
											</tr>
										</table>
										</div>
										</s:form>
								</div>
							</div>
							
					</div>		
		<div class="modify">				
			<display:table id="idListTb" name="pageView" style="min-width:700px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb_002" decorator="com.sys.volunteer.common.OverOutWrapperUtils"
				requestURI="${pageContext.request.contextPath}/users/list!manageUserEx.do">
				<display:column property="userCode" title="编号" />
				<%--
				<display:column property="terminalNo" title="终端机号" />
				 --%>
				<display:column title="用户名">
					 <%--
					<a href="javascript:void(0);" onclick="showPersonalByUserId('${idListTb.userId}')">${idListTb.userName }</a>
				   --%>
					<a href="/MPRS/users/list!manageUserEx.do?userId=${idListTb.userId}" >${idListTb.userName }</a>
				</display:column>
				<%--
				<c:choose>
					<c:when test="${idListTb.parentUsers!=null}">
						<display:column property="parentUsers.userName" title="上级商户名" />
					</c:when>
				</c:choose>
				
				 --%>
				<display:column property="usergroup.groupName" title="所属组名" />
				<display:column title="状态" >
					<c:choose>
						<c:when test="${idListTb.state==0}">正常</c:when>
						<c:when test="${idListTb.state==1}">冻结</c:when>
						<c:when test="${idListTb.state==2}">禁用</c:when>
					</c:choose>
				</display:column>
				<display:column maxLength="11" property="mobile" title="联系电话" />

                 <%--
                 
				<display:column title="性别">
					<c:choose>
						<c:when test="${idListTb.gender==1}">男</c:when>
						<c:when test="${idListTb.gender==2}">女</c:when>
						<c:otherwise>未知</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="商户类别">
					<c:choose>
						<c:when test="${idListTb.userLevel.userLevelId==1}">A级</c:when>
						<c:when test="${idListTb.userLevel.userLevelId==2}">B级</c:when>
						<c:when test="${idListTb.userLevel.userLevelId==3}">C级</c:when>
						<c:when test="${idListTb.userLevel.userLevelId==4}">D级</c:when>
						<c:otherwise>无级别</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="defaultAlarm" title="警示界限" />
				<display:column title="佣金余额">
					<fmt:formatNumber maxFractionDigits="2" value="${idListTb.useraccount.commission }"/>
				</display:column>
				 --%>
				<display:column property="createDate" title="建立时间"
					format="{0,date,yy-MM-dd HH:mm}" />
				<display:column title="账户余额">
					<fmt:formatNumber maxFractionDigits="2" value="${idListTb.useraccount.balance }"/>
				</display:column>
				<display:column property="terminalNo" title="终端机号" />
				<display:column property="otp.keyId" title="加密key" />
				<display:column property="reversalCount" title="可冲正次数" />
				<%--
				
				<display:column property="maxReversalCount" title="最大可冲正次数" />
				
				<display:column title="操作">
					 <c:if test="${sessionScope.user.usergroup.id==1
					 ||(sessionScope.user.usergroup.id<idListTb.usergroup.id&&sessionScope.user.usergroup.id<=2)}">
						<a href="users/modifyUser!viewUser.do?userId=${idListTb.userId}">修改</a>&nbsp;
						<c:choose>
							<c:when test="${idListTb.state==0}">
								<a href="users/list!delUser.do?userId=${idListTb.userId}"
									onclick=javascript: return del();>禁用</a>
							</c:when>
							<c:when test="${idListTb.state==2}">
								<a href="users/list!recoverUser.do?userId=${idListTb.userId}">恢复</a>
							</c:when>
						</c:choose>
						&nbsp;<a href="users/refreshReversal.do?userId=${idListTb.userId}">更新冲正次数</a>&nbsp;
						&nbsp;<a href="users/commitAccount.do?userId=${idListTb.userId}">更新账户余额</a>&nbsp;
					</c:if>
					 <c:if test="${sessionScope.user.usergroup.id==1
					 || (sessionScope.user.userId==idListTb.parentUsers.userId)}">
						<a href="users/page!recharge.do?userId=${idListTb.userId}">设置额度</a>&nbsp;
						<a href="${pageContext.request.contextPath}/otp/page!testPage.do?userId=${idListTb.userId }">测试密保</a>
					</c:if>
					<c:if test="${sessionScope.user.usergroup.id==1
					 ||(sessionScope.user.usergroup.id<idListTb.usergroup.id&&sessionScope.user.usergroup.id<=2)
					 || (sessionScope.user.userId==idListTb.parentUsers.userId)
					 || (sessionScope.user.userId==idListTb.userId)}">
					 	<a href="users/list!manageUserEx.do?userId=${idListTb.userId}">查看</a>&nbsp;
					 	<a href="users/doDrawingCommission.do?userId=${idListTb.userId}"  onclick="return window.confirm('您确定要提取吗？')">提取佣金</a>
					</c:if>
				</display:column>
					  --%>
			</display:table>
		
			</div>
	</div>
	</c:if>
</div>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
