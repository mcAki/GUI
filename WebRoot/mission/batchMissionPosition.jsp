<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
        <style type="text/css">
		* {
font-size:12px;
margin:0;
padding:0; 
} 
fieldset {
padding:10px;
margin:10px;
width:270px;
color:#333; 
border:#06c dashed 1px;
} 
legend {
color:#06c;
font-weight:800; 
background:#fff;
} 
ul {
list-style-type: none;
margin:8px 0 4px 0;
} 
li {
margin-top:4px;
}
        </style>
  </head>  
  <body>
  	<%@ include file="../common/incBanner.jsp" %>
  	<br/>
  	<br/>
  	<br/>
  	<br/>
  	<br/>
  	<br/>
  	<center>
  	
  	<s:form action="batchMissionPosition!batchMissionPersonal" namespace="/missionTeam" method="post" enctype="multipart/form-data">

     <fieldset>
       	<legend>只导队员，不导岗位</legend>
  		<table>
	  		</br>
	  		<tr>
	  			<td align="right">选择文件:</td>
				<td width="183" align="left" style="white-space: pre-line"><s:file name="upload" cssClass="txtW3"></s:file></td>
			</tr>
			
			<s:hidden name="missionId"></s:hidden>
		</table>
		</br>
		<s:submit  value="提交"></s:submit>

     </fieldset>
  	</s:form>
  	<br /><br />
  		<s:form action="batchMissionPosition!batchMissionPosition" namespace="/missionTeam" method="post" enctype="multipart/form-data">
     <fieldset>
       	<legend>完全导入队员与岗位</legend>

  		<table>
	  		</br>
	  		<tr>
	  			<td align="right">选择文件:</td>
				<td width="183" align="left" style="white-space: pre-line"><s:file name="upload" cssClass="txtW3"></s:file></td>
			</tr>
			
			<s:hidden name="missionId"></s:hidden>
		</table>
		</br>
		<s:submit  value="提交"></s:submit>
        </table>
    </fieldset>
  	</s:form>
  	</center>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>

