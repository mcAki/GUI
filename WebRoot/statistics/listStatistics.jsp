<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		
  	<link href="../css/criteria.css" rel="stylesheet" type="text/css">
	<link href="../css/sys.css" rel="stylesheet" type="text/css">
	<%@ include file="../common/incHead.jsp"%>
  </head>  
  <body>
  <%@ include file="../common/incBanner.jsp" %>
  <br/>
  <center><display:table export="true" id="idListTb" name="statisticsVOs"  style="width:900px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/statistics/list!listStatistics.do">
  			<display:setProperty name="paging.banner.placement" value="bottom" />
			<display:column property="goodsName" title="商品名称" />
          	<display:column property="supplyName" title="供货商"/>
          	<display:column property="stockPrice" title="进货价"/>
          	<display:column property="retailPrice" title="销售价"/>
          	<display:column property="goodsNo" title="销售数量"/>
          	<display:column property="stockTotal" title="进货总价"/>
          	<display:column property="retailTotal" title="销售总价"/>
          	<display:column property="profits" title="利润"/>
          	<display:column property="profitsTotal" title="合计利润"/>
          	<display:setProperty name="export.excel.filename" value="times.xls"/> 
		</display:table>
	    </center>
	<br/>	
	<br/>
	<script type="text/javascript">
			displaytagExportLink();
	    </script>
<a href="user!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
