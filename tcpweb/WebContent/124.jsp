<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="5">
<title>Port 124</title>
<link href="./css/styles.css" rel="stylesheet" type="text/css"/>
<script src="./js/jQuery-2.1.4.min.js"></script>
</head>
<%
			//String prefix = "Captain";
			
			//String scAddr = application.getAttribute("addr").toString();
%>
<body>
	<%-- <p><%=prefix%>&nbsp;${requestScope.scValue}</p> --%>
	<%-- <p>Servlet Context valued Address: <%=scAddr %></p> --%>
	<%-- <p>Static valued Address: ${requestScope.scValue}</p> --%>
		<div class="serverDataDiv">
            <input class="serverDataTxt" type="text" id="messageinput" placeholder="Enter data for clients..."/>
        </div>
        <div class="serverDataBtnDiv">
            <button class="serverDataBtn" type="button" onclick="location.reload();" id="btnrefresh">Refresh Page</button>
            <button class="serverDataBtn" type="button" onclick="send();" >Send To Clients</button>
            <button class="serverDataBtn" type="button" onclick="history();" >Go To History</button>
            <!-- <button type="button" onclick="" >Stop Server</button> -->
        </div>
        <!-- Server responses get written here -->
        <c:if test="${not empty requestScope.scValue}">
	        <div class="outputDiv" id="messages">
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
	        			
	    					${requestScope.data}
						
						
	        		</tbody>
	        	</table>
	        	
	        </div>
        </c:if>
       <script type="text/javascript">
       setInterval(changeColor, 4000);//changes refresh button color in every 4 seconds
       function changeColor() {
    	   $("#btnrefresh").toggleClass('btncolor');

    	}
       
       function send(){
           var text = document.getElementById("messageinput").value;
           //alert(text);
           $.ajax({
   	   		url:"./page124",
   	   		type:'POST',
   	   		data:"serverData="+text+"&ownReq=yes",
   	   		success:function(response){
   	   				//alert(response);
   	   				//$("#messages").html(response); 
   	   				//$("#messageinput").val("");
   	   				 location.reload();
   	   			},error: function(){	 
   	   				alert('Error while request ..');
   			}
   	        
   	      });
       }//end of send function
       function history(){
    	   window.location = "./history.jsp";
       }//end of send function
       </script>
       
</body>
</html>