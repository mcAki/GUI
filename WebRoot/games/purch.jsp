<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
      <head>
	  </head>
  <body>
  
     <table width="40%" align="center">
        <img src="image/top.jpg"></img>
     </table>
  
     <form action="add!doAdd.html" name="myform" method="post" >
     <input type="hidden" name="gamesId" value="${param.gamesId}">
     <input type="hidden" name="gamesName" value="${param.gamesName}">
     <input type="hidden" name="gamesType" value="${param.gamesType}">
     <table width="40%" align="center">
        <tr>
          <td>游戏名称：</td> 
          <td>
             ${param.gamesName}
          </td>
        </tr>
        <tr>
          <td>购买的数量：</td> 
          <td>
            	<select name="games.count" id="count" onchange="sumPrice_zhichong(this.value)" class="input_select" style="width:35px;">	
			            <option value='1' selected="selected">1</option>
                </select>
          </td>
        </tr>
        <tr>
			<td >请选择面值：</td>
			<td>
				<select name="games.price" id="count" onchange="sumPrice_zhichong(this.value)" class="input_select" style="width:90px;">	
			            <option value='1' selected="selected">请选择面值</option>
			            <option value='10' >10元</option>
			            <option value='30' >30元</option>
			            <option value='50' >50元</option>
                </select>
			</td>
		</tr>
    
	    <tr>
			<td>充值账号：</td>
			<td>
			<input type='text' name="games.gamesNum" id="account" maxlength="25" class="input_text" />
			<font color="red">&nbsp;*&nbsp;请填写您所充值的玩家账号</font>
			</td>
	  </tr>
	  <tr>
			<td>确认账号：</td>
			<td>
			<input type='text' name="confirmaccount" id="confirmaccount" maxlength="25" class="input_text"/>
			<font color="red">&nbsp;*&nbsp;请填写您所充值的玩家账号</font>
			</td>
	  </tr>
	  <tr>
			<td align="right" >	
			    <a href="javascript:document.myform.submit(  )">
                    <img src="image/next.jpg"></img>
                </a>
			</td>
			<td align="left" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    <img src="image/back.jpg" onclick="javascript:history.go(-1);" style="cursor:pointer"/>
			</td>
	</tr>
     </table>
     
     </form>
  
     <p style=" width:69%" align="center">
            <span class="ui-icon ui-icon-circle-zoomin" style="float: left; margin-right: .3em; margin-top:.6em;"></span>
            <strong>
              &nbsp; &nbsp;   &nbsp;   &nbsp;   &nbsp;   &nbsp;   【24小时内最近十笔电信的交易记录】
            </strong>
      </p>
      
      <table border="1" style="line-height:20px; width:45%" id="tabRecentTranList" align="center">
            <tr style="height: 25px;">
                <th>序号</th>
                <th>交易时间</th>
                <th>客户号码</th>
                <th>交易金额</th>
                <th>客户参考余额</th>
                <th>交易状态</th>              
                <th id="rollBackDisplay">冲正</th>
            </tr>
            
        </table>
  </body>
</html>
