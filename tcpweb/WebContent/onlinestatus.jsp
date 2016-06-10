<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="refresh" content="5"> -->
<title>Online status</title>

<link href="./css/styles.css" rel="stylesheet" type="text/css"/>
<script src="./js/jQuery-2.1.4.min.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/jquery-ui.css">
<script type="text/javascript">
	setInterval(function () {myTimer()}, 1000);
	function myTimer() {
	    var date = new Date();
	    $("#idLabelDate").text($.datepicker.formatDate('MM dd, yy', date)+" "+date.toLocaleTimeString());
	}
</script>
<style type="text/css">
	 thead{
  background-color:#35BBD8;
     color: #FFF;
     padding: 0px;
     
    
 }
 thead th{
    padding: 2px ;
    border-left: 1px solid rgb(210, 210, 210);
   
    border-bottom: 1px solid rgb(210, 210, 210);
    text-align: center;
    min-width: 185px;
    height: 28px;
    
}
 
 td {
   white-space: nowrap;
    overflow: hidden;
    width: 130px;
    height: 30px;
    font-size: 28px;
    color:black;
    
}
table td {
    border-left: 1px solid rgb(210, 210, 210);
    border-bottom: 1px solid rgb(210, 210, 210);
    height: 10px;
    padding: 20px;
    background-color: white;  
   
}
table.display tbody tr:nth-child(odd):hover td {
     background-color: rgba(53, 187, 216, 0.39);
}
table.display tbody tr:nth-child(even):hover td{
     background-color: rgba(53, 187, 216, 0.39);
}
</style>
</head>
<%
%>
<body>
	
        <!-- <div class="serverDataBtnDiv">
            <button class="serverDataBtn" type="button" onclick="location.reload();" id="btnrefresh">Refresh Page</button>
            <button class="serverDataBtn" type="button" onclick="history();" >Go To History</button>
        </div> -->
        <div class="OnlineDataHeader">
            Online Status
        </div>
        <div class="Navigation">
        	<a href="./onlinestatus.jsp" id="linkrefresh">Refresh Page</a>
        	<label>|</label>
        	<a href="./history.jsp">See History</a>
        </div>
        <div class="DateDiv">
        	<label id="idLabelDate"></label>
        </div>
        <!-- Server responses get written here -->
	        <div class="OnlineDataDiv" id="messages">
	        	<table style="margin:auto;width: 100%;padding: 20px;border-collapse: collapse;font-weight: 500;" class = "display">
	        		<thead style="font-size:18px;background-color: #B9B7BB;color:black">
	        			<tr>
	        				<th>Meter ID</th>
	        				<th>Frequency (HZ)</th>
	        				<th>R-phase Voltage(KV)</th>
	        				<th>Y-phase Voltage(KV)</th>
	        				<th>B-phase Voltage(KV)</th>
	        				<th>R-phase Current(Amp)</th>
	        				<th>Y-phase Current(Amp)</th>
	        				<th>B-phase Current(Amp)</th>
	        				<th>Real Power(KW)</th>
	        				<th>Reactive Power(KVAR)</th>
	        				<th>Power Factor(PF)</th>
	        				<th>Active Import(KWH)</th>
	        				<th>Active Export(KWH)</th>
	        			</tr>
	        		</thead>
	        		<tbody>
	        			<c:if test="${not empty applicationScope.scValue}">
	        				${applicationScope.scValue}
	        			</c:if>
	        			<!-- <tr>
	        				<td>01</td>
	        				<td>49.996</td>
	        				<td>129.674</td>
	        				<td>130.818</td>
	        				<td>130.788</td>
	        				<td>267.52</td>
	        				<td>297.82</td>
	        				<td>265.04</td>
	        				<td>104.538</td>
	        				<td>133.99</td>
	        				<td>-0.968</td>
	        				<td>3101100</td>
	        				<td>3460680</td>
	        			</tr> -->
	        			
	        		</tbody>
	        	</table>
	        	
	        </div>
        <%-- <c:if test="${not empty applicationScope.scValue}">
	        <div class="OnlineDataDiv" id="messages">
	        	<table style="margin: auto;width: 100%;padding: 20px;">
	        		<thead>
	        			<tr>
	        				<th></th>
	        				<th></th>
	        				<th></th>
	        				<th>=HEX2DEC(value)</th>
	        				<th>start index</th>
	        				<th>no of byte to read</th>
	        			</tr>
	        		</thead>
	        		<tbody>
	    					${applicationScope.scValue}
	        		</tbody>
	        	</table>
	        	
	        </div>
        </c:if> --%>
        
       <script type="text/javascript">
       setInterval(changeColor, 4000);//changes refresh button color in every 4 seconds
       function changeColor() {
    	   $("#linkrefresh").toggleClass('btncolor');

    	}
       
       
       
       /* function history(){
    	   window.location = "./history.jsp";
       } */
       </script>
       
</body>
</html>