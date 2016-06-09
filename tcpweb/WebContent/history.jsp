<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="refresh" content="5"> -->
<title>history data</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link href="./css/styles.css" rel="stylesheet" type="text/css"/>
<script src="./js/jQuery-2.1.4.min.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="./js/jquery.table2excel.min.js"></script>

<style type="text/css">
	#tblhistory tbody{
		display: block;
	    overflow-y: auto;
	    overflow-x: hidden;
	}
	#tblhistory thead{
		display: block;
	}
	#tblhistory thead tr th{
		width: 7%;
		min-width: 144px;
	}
	#tblhistory tbody tr td:FIRST-CHILD {
		text-align: left;
	    width: 15%;
	    min-width: 175px;
	}
	#tblhistory tbody tr td{
		width: 7.5%;
	    text-align: center; 
	    min-width: 146px;
	}
	
	#tblhistory thead th{
	    padding: 2px ;
	    border-left: 1px solid rgb(210, 210, 210);
	   
	    border-bottom: 1px solid rgb(210, 210, 210);
	    /* text-align: center;
	    min-width: 185px; */
	    height: 28px;
    
	}
	#tblhistory tbody td {
	    border-left: 1px solid rgb(210, 210, 210);
	    border-bottom: 1px solid rgb(210, 210, 210);
	    /* height: 10px;
	    padding: 20px; */
	    background-color: white;  
	   
	}
	#tblhistory tbody tr:nth-child(odd):hover td {
	     background-color: rgba(53, 187, 216, 0.39);
	}
	#tblhistory tbody tr:nth-child(even):hover td{
	     background-color: rgba(53, 187, 216, 0.39);
	}
</style>
</head>
<%
			
%>
<body style="overflow-x:hidden; ">
		<div class="backlink">
			<a href="./onlinestatus.jsp"><img src="./css/images/back.jpg" style="display:inline;max-height: 30px;max-width: 90px;"></a>
		</div>
		<div class="OnlineDataHeader">
            Meter Status History
        </div>
        <div class="historyBtnDiv">
            <label for="fd">From Date :</label>&nbsp;&nbsp;<input name="fd" type="text" id="fd">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <label for="td">To Date :</label>&nbsp;&nbsp;<input name="td" type="text" id="td">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <label for="td">Meter :</label>&nbsp;&nbsp;
            <select name="mId" id="mId">
              <option value="01">1</option>
			  <option value="07">7</option>
			  <option value="09">9</option>
            </select>
            <br>
            <button class="historyBtn" type="button" onclick="getHistory();" >Fetch History</button>
            <button class="historyBtn" type="button" onclick="exportToSheet();" >Export to sheet</button>
            <!-- <button type="button" onclick="" >Stop Server</button> -->
        </div>
        <!-- Server responses get written here -->
        <%-- <c:if test="${not empty requestScope.historyValue}"> --%>
	        <div class="HistoryDataDiv" id="messages">
	        	<table class="table2excel display" style="margin:auto;width: 95%;padding: 20px;border-collapse: collapse;font-weight: 500;" id="tblhistory">
	        		<thead style="font-size:14px;background-color: #B9B7BB;color:black">
	        			<tr>
	        				<th style="width:15%;">Date-Time</th>
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
	        		<tbody style="max-height:400px;">
	        				<%-- ${requestScope.historyValue} --%>
	        			
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
	       <%-- </c:if> --%>
       <script type="text/javascript">
       $(function() {
    	    $( "#fd" ).datepicker({
    	    	dateFormat: "yy-mm-dd"
    	    });
    	    $( "#td" ).datepicker({
    	    	dateFormat: "yy-mm-dd"
    	    });
       });
       function getHistory(){
           var fd = $("#fd").val();
           var td = $("#td").val();
           var mId = $("#mId").val();
           //alert("fd:"+fd+" td:"+td+" mId:"+mId);
           if( fd != "" && td != "" && mId != ""){
        	   $.ajax({
       	   		url:"./history",
       	   		type:'GET',
       	   		data:"fd="+fd+"&td="+td+"&mId="+mId,
       	   		success:function(result){
       			   			//alert("Successfully got data :"+result);
       	   					$("#tblhistory tbody").html(result);
       		     	    },
       	     	error: function(){		
       	     				alert("Error while request...");
       	     			}
       	        
       	      });
           }else{
        	   alert("Choose all values!!!") ;
           }
       }//end of send function
       function exportToSheet() {
			$(".table2excel").table2excel({
				exclude: ".noExl",
				name: "Meter Status History",
				filename: "meterhistory",
				fileext: ".xls",
				exclude_img: true,
				exclude_links: true,
				exclude_inputs: true
			});
		}//end of export to sheet function
       </script>
       
</body>
</html>