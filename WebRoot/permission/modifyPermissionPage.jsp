
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../js/divselect.js"></script>
		<script type="text/javascript">
	
	$(document).ready(function() {
		$(".check_checkbox_permission").click(function(){
	    
	     var removeIdTemp = $("#removeIdTemp").val();
		      
		      if($(this).attr("checked")==false){
		          //$(this).attr("name","removeIds");
		          if(removeIdTemp.length<=0||removeIdTemp==""){
			          document.getElementById("removeIdTemp").value=$(this).val();
		          }else{
			          document.getElementById("removeIdTemp").value+=(","+$(this).val());
		          }
		      }
		     
		});
		$(".nocheck_checkbox_permission").click(function(){
		 var addIdTemp = $("#addIdTemp").val();
		      if($(this).attr("checked")==true){
		         // $(this).attr("name","addId");
		          if(addIdTemp.length<=0||addIdTemp==""){
			          document.getElementById("addIdTemp").value= $(this).val();
		          }else{
			          document.getElementById("addIdTemp").value+=(","+$(this).val());
		          }
		          
		      }
		    
		});
	})
	
</script>
		<style type="text/css">
 
       </style>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>

		<div id="comInfo" class="fixed">
			<div id="contain" class="fixed">
				<s:include value="../system/head_div.jsp"></s:include>
				<div class="tqyj fixed">
					<div class="cxjj fixed">
						<p class="jjcx">
							权限修改
						</p>

					</div>
					<div class="modify">
						<div class="cxjjtable3">
						<form action="/MPRS/permission/setPermission!addUserGroupByPermissionId.do">
						   
							   <input type="hidden" value="${usergroupId}" name="usergroupId"/>
							   <input type="hidden" value="" name="addId" id="addIdTemp"/>
							   <input type="hidden" value="" name="removeIds" id="removeIdTemp"/>
							<table border="1" cellpadding="3" cellspacing="0" style="width: 60%;margin:auto" class="list_tb_002">
							   <tr >
							     <th>功能ID</th>
							     <th>功能名称</th>
							     <th>功能类型 </th>
							     <th>操作  </th>
							   </tr>
							  <s:iterator value="permissionPageList">
							   <tr>
							     <td>${permissionId } </td>
							     <td>${description }</td>
							     <td> ${typeName }</td>
							     <td>
							         <c:if test="${userGroupId >0}">
								     	 <input class="check_checkbox_permission" type="checkbox"   value="${permissionId }" checked="checked"/> 
							         </c:if>
								     <c:if test = "${userGroupId ==null}">
								     	 <input class="nocheck_checkbox_permission" type="checkbox"    value="${permissionId }"/> 
								     </c:if>
							      </td>
							   </tr>
							  </s:iterator> 
							  <tr>
							    <td colspan="4">
							       <button type="submit" >提交</button>
							    </td>
							  </tr>
							</table>
						</form>
						</div>
					</div>
				</div>
			</div>
		</div>	
			<br />
			<br />
			<script type="text/javascript">
				displaytagExportLink();
			</script>
			<a href="user!add.html"></a>
			<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
