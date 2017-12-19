<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	     <link href="../css/main_new.css" rel="stylesheet" type="text/css" />
	     		<script type="text/javascript" src="../js/divselect.js"></script>  
	     
	<script type="text/javascript">
		function showSupplyInterface(){
		
			//计算弹出位置
			
			height = 500;
			width = 400;
			//barHeight=30;
			//top = (screen.Height/2) - (height/2) - barHeight;
			//left = (screen.width/2) - (width/2);
			
	
			//要查询的链接
			var url="${pageContext.request.contextPath}/supplyInterface/list!listInterfaceForSupply.do?";
			//为了处理缓存加入随机时间
			url += "&date=" + new Date().getTime();
			
			//弹出参数
			var features = "resizable=yes;center=yes;status=yes;fullscreen=0;scroll=yes;dialogWidth="+ width +"px;dialogHeight=" + height + "px";
			
			//创建传入参数
			var obj = new Object();
			obj.name = "参数";
			var returnVal = window.showModalDialog(url,obj,"dialogWidth=600px;dialogHeight=500px");
			if(!$.isEmptyObject(returnVal)){
				$("#userId").val(returnVal.uid);
				$("#userName").val(returnVal.username);
			}
		}
		
		$(document).ready(function(){
		
		   String.prototype.Trim = function()  
				{  
				return this.replace(/(^\s*)|(\s*$)/g, "");  
				}  
		
		    $(".goumai11").click(function(){
		       var goods =$("#inputselect").val();
		        if(goods.Trim()==""){
		           alert("您还没有选择商品类型");
		           return;
		        }
		        var goodsNo = $("#inputselect2").val();
		         if(goodsNo.Trim()==""){
		           alert("您还没有选择商品个数");
		           return;
		        }
		        var superId = $("#supplyId").val();
		        if(superId.Trim()==""){
		            alert("没有对应供应商，不能下单");
		           return;
		        }
		        strutsForm.submit();
		    });
		    
		    $(".13").click(function(){
		        var goods =$("#inputselect").val();
		        if(goods.Trim()==""){
		           alert("您还没有选择商品类型");
		           return;
		        }
		        var goodsNo = $("#inputselect2").val();
		         if(goodsNo.Trim()==""){
		           $("#show_sum_mes").html("请选择商品个数");
		           $("#show_sum_mes").show();
		           return;
		        }
		        $("#show_sum_mes").hide();
		        $.post( "/MPRS/order/doAddCardOrderCheckOutPrice.do", {goodsIdStr:goods,goodsNo:goodsNo},  function(data){
		            var array = data.split("\#");
		            if(array[0]=="error"){
		                 alert(array[1]);
		            }else if(array[0]=="success"){
		                 $("#supplyInterfaceId").attr("value",array[1]);
		                 $("#supplyId").attr("value",array[2]);
		                 $("#supplyId").html(array[0]);
		            }
		        });
		    });
		})
	</script>
  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  <%-- 
  
  	<s:form name="strutsForm" action="doAddCardOrder" namespace="/order" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:hidden name="mainorder.mainOrderId" />
  	<s:hidden name="orderdetail.orderDetailId" />
  	<s:token/>
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>卡密订单</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
      <tr>
      	<td align="right" >账户余额:</td>
	 	<td align="left"><s:textfield name="useableAccount" readonly="true"/></td>
       	<td>&nbsp;</td>
       	<td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
      	<td align="right" >选择卡种类:</td>
        <td align="left"><s:select name="goodsId" list="goodsList" listKey="goodsId" listValue="goodsName"></s:select></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
      	<td align="right" >购买数量:</td>
        <td align="left">
        	<s:select name="goodsNo" list="%{#{1:'1',2:'2',3:'3',4:'4',5:'5',6:'6',7:'7',8:'8',9:'9',10:'10'}}" listKey="key"></s:select>
        </td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	   <td align="right" >描述:</td>
  	   <td colspan="4" align="left"><s:textarea name="mainorder.desc" cssClass="txtW5" rows="4"></s:textarea></td>
      </tr>
      
      <tr>
  	    <td colspan="3" align="center"><s:submit icon="icon-apply" value="购买"></s:submit>
        </td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
		
  </table>
  </s:form>
   --%>
	<%@ include file="../common/incFooter.jsp" %>
	
	 <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
            <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj fixed">
                    <strong class="tqxj">卡密订单</strong>
                    <div class="update fixed">
                             <div class="upadeform" style="width: 650px">
                                   <s:form name="strutsForm" action="doAddCardOrder" namespace="/order" method="post">
                                   
                                   <s:hidden name="mainorder.mainOrderId" />
								  	<s:hidden name="orderdetail.orderDetailId" />
								  	<s:token/>
  	                                 <center><s:fielderror></s:fielderror></center>
                                  <table width="100%" border="0" align="center" cellspacing="0" >
								      <tr style="height: 50px;">
								      	<td align="right" >账户余额:</td>
									 	<td align="left" colspan="3"><s:textfield name="useableAccount" readonly="true"/>
									 	   
									 	</td>
								      </tr>
								      
								      <tr style="height: 50px;">
								      	<td align="right" >选择卡种类:</td>
								        <td align="left">
								           <div id="divselect8" class="divselect3 divselect" >
                                                              <cite>请选择商品</cite>
                                                                   <ul class="13" style="height: 200px; overflow: scroll;">
                                                                    <s:iterator value="goodsList" id="goodsList">   
																        <li > <a href="javascript:;" selectid="<s:property value='goodsId'/> "> <s:property value="goodsName"/> </a>
																         </li>
																       </s:iterator>
                                                                    </ul>
                                                      </div>
                                                       <input name="goodsIdStr" type="hidden" value="" id="inputselect"/>
                                                 
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect8","#inputselect");
													});
													</script>
								        </td>
								        
								       
								        
								      	<td align="right" >购买数量:</td>
								        <td align="left">
								           <div id="divselect10" class="divselect3 divselect" >
                                                              <cite>选择数量</cite>
                                                                   <ul class="13"  style="height: 150px; overflow: scroll;">
																        <li > <a href="javascript:;" selectid="1">1 </a></li>
																        <li > <a href="javascript:;" selectid="2">2 </a></li>
																        <li > <a href="javascript:;" selectid="3">3 </a></li>
																        <li > <a href="javascript:;" selectid="4">4 </a></li>
																        <li > <a href="javascript:;" selectid="5">5 </a></li>
																        <li > <a href="javascript:;" selectid="6">6 </a></li>
																        <li > <a href="javascript:;" selectid="7">7 </a></li>
																        <li > <a href="javascript:;" selectid="8">8 </a></li>
																        <li > <a href="javascript:;" selectid="9">9 </a></li>
																        <li > <a href="javascript:;" selectid="10">10 </a></li>
                                                                    </ul>
                                                      </div>
                                                       <input name="goodsNo" type="hidden" value="" id="inputselect2"/>
                                                       <input name="supplyId" type="hidden" value="" id="supplyId"/>
                                                       <input name="supplyInterfaceId" type="hidden" value="" id="supplyInterfaceId"/>
                                                
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect10","#inputselect2");
													});
													</script>
													<span id="show_sum_mes" style="display:none; color: red;"></span>
								        </td>
								      </tr>
								        <tr>
								            <td colspan="2" align="center"> 销售单价为：<span id="show_price"></span>元 </td>
								        </tr>
								      
								      <tr style="height: 50px;">
								  	   <td align="right" >描述:</td>
								  	   <td  align="left" colspan="3" >
								  	       <textarea name="mainorder.desc" cols="" rows="" class="beizhu">${mainorder.desc }</textarea>
								  	   </td>
								      </tr>
								        <tr>
								            <td colspan="2" align="center" height="20">  &nbsp; </td>
								        </tr>
								      
								      <tr style="height: 50px;">
								  	    <td  colspan="4" align="center"><button type="button" class="goumai11" > 提交</button>
								        </td>
								      </tr>
										
								  </table>
								  </s:form>
                             </div>
                    </div>
             </div>
          </div>
      </div> 
  </body>
</html>

