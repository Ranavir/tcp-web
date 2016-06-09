
$(document).ready(function() {
	var type = "" ;
	var msisdn = "" ;
	var testFlag = "" ;
	var bcodeRef = "" ;
	var noOfLease = "" ;
	var gwNumber = "" ;
	var txtMsg = "" ;
	var timestamp = "" ;
	var priority = "" ;
	var smsRef = "" ;
	function initializeDisableField(){
		$("#typeOption").prop('disabled', true);
		$("#msisdn").prop('disabled', true);
		$("#testFlagOption").prop('disabled', true);
		$("#bcodeRef").prop('disabled', true);
		$("#noOfLease").prop('disabled', true);
		$("#gwNumber").prop('disabled', true);
		$("#txtMsg").prop('disabled', true);
		$("#timestamp").prop('disabled', true);
		$("#priorityOption").prop('disabled', true);
		$("#smsRef").prop('disabled', true);
	};
	initializeDisableField();
	function disableField(flag){
		initializeDisableField();
		if(flag==1){
			
		}
		if(flag==2){
			$("#typeOption").prop('disabled', false);
			$("#msisdn").prop('disabled', false);
			$("#testFlagOption").prop('disabled', false);
		}
		if(flag=="2a"){
			$("#bcodeRef").prop('disabled', false);
		}
		if(flag==3){
			$("#bcodeRef").prop('disabled', false);
			$("#noOfLease").prop('disabled', false);
		}
		if(flag==4){
			$("#bcodeRef").prop('disabled', false);
		}
		if(flag==5){
			
		}
		if(flag==6){
			$("#bcodeRef").prop('disabled', false);
			$("#gwNumber").prop('disabled', false);
			$("#txtMsg").prop('disabled', false);
			$("#timestamp").prop('disabled', false);
			$("#priorityOption").prop('disabled', false);
		}
		if(flag==7){
			$("#smsRef").prop('disabled', false);
		}
		if(flag==8){
			$("#smsRef").prop('disabled', false);
		}
		if(flag==9){
			$("#typeOption").prop('disabled', false);
			$("#msisdn").prop('disabled', false);
			$("#testFlagOption").prop('disabled', false);
			$("#gwNumber").prop('disabled', false);
			$("#txtMsg").prop('disabled', false);
			$("#timestamp").prop('disabled', false);
			$("#priorityOption").prop('disabled', false);
		}
		if(flag==10){
			$("#bcodeRef").prop('disabled', false);
			$("#txtMsg").prop('disabled', false);
		}
		if(flag==11){
			
		}
		if(flag==12){
			$("#gwNumber").prop('disabled', false);
			$("#msisdn").prop('disabled', false);
			$("#txtMsg").prop('disabled', false);
			$("#timestamp").prop('disabled', false);
			$("#priorityOption").prop('disabled', false);
		}
	}
	$("#reqType").on('change',function(e){
		var selectedReq = $('#reqType').val() ;
		if(selectedReq==1){
			disableField(1);
		}
		if(selectedReq==2){
			disableField(2);
		}
		if(selectedReq=="2a"){
			disableField("2a");
		}
		if(selectedReq==3){
			disableField(3);
		}
		if(selectedReq==4){
			disableField(4);
		}
		if(selectedReq==5){
			disableField(5);
		}
		if(selectedReq==6){
			disableField(6);
		}
		if(selectedReq==7){
			disableField(7);
		}
		if(selectedReq==8){
			disableField(8);
		}
		if(selectedReq==9){
			disableField(9);
		}
		if(selectedReq==10){
			disableField(10);
		}
		if(selectedReq==11){
			disableField(11);
		}
		if(selectedReq==12){
			disableField(12);
		}
	});
	$("#type").val($("#typeOption").val());
	$("#typeOption").on('change',function(e){
		$("#type").val($(this).val());
		alert($("#type").val());
	});
	$("#testFlag").val($("#testFlagOption").val());
	$("#testFlagOption").on('change',function(e){
		$("#testFlag").val($(this).val());
		alert($("#testFlag").val());
	});
	$("#priority").val($("#priorityOption").val());
	$("#priorityOption").on('change',function(e){
		$("#priority").val($(this).val());
		alert($("#priority").val());
	});
	
	$("#btn-request").on('click',function(e){
		var selectedReq = $('#reqType').val() ;
		if(selectedReq==1){
			alert("Request for : "+"Read Customer");
			readCustomer();
		}
		if(selectedReq==2){
			alert("Request for : "+"Create bCODE");
			type = $("#type").val();
			msisdn = $("#msisdn").val();
			testFlag = $("#testFlag").val();
			if(type!="" && msisdn!="" && msisdn.length==10 && testFlag!="")
				createBCODE(type,msisdn,testFlag);
		}
		if(selectedReq=="2a"){
			alert("Request for : "+"Read bCODE");
			bcodeRef = $("#bcodeRef").val();
			if(bcodeRef!="") 
				readBCODE(bcodeRef);
		}
		if(selectedReq==3){
			alert("Request for : "+"Create Lease");
			bcodeRef = $("#bcodeRef").val();
			noOfLease = $("#noOfLease").val();
			alert("more than 1 lease will not be accepted !");
			if(bcodeRef!="" && noOfLease!="" && noOfLease == 1) //constraint given that create only one lease
				createLease(bcodeRef,noOfLease);
		}
		if(selectedReq==4){
			alert("Request for : "+"Read Lease");
			bcodeRef = $("#bcodeRef").val();
			if(bcodeRef!="") 
				readLease(bcodeRef);
		}
		if(selectedReq==5){
			alert("Request for : "+"Read Gateway");
			readGateway();
		}
		if(selectedReq==6){
			alert("Request for : "+"Create bCODE SMS");
			bcodeRef = $("#bcodeRef").val();
			gwNumber = $("#gwNumber").val();
			txtMsg = $("#txtMsg").val();
			timestamp = $("#timestamp").val();
			priority = $("#priority").val();
			if(bcodeRef!="") //always make txtMsg to empty for getting exact bCODE value
				createBCODESMS(bcodeRef,gwNumber,txtMsg,timestamp,priority);
		}
		if(selectedReq==7){
			alert("Request for : "+"Read SMS");
			smsRef = $("#smsRef").val();
			if(smsRef!="")
				readSMS(smsRef);
		}
		if(selectedReq==8){
			alert("Request for : "+"Delete SMS");
			smsRef = $("#smsRef").val();
			if(smsRef!="")
				deleteSMS(smsRef);
		}
		if(selectedReq==9){
			alert("Request for : "+"Issue bCODE");
			type = $("#type").val();
			gwNumber = $("#gwNumber").val();
			msisdn = $("#msisdn").val();
			testFlag = $("#testFlag").val();
			txtMsg = $("#txtMsg").val();
			timestamp = $("#timestamp").val();
			priority = $("#priority").val();
			if(type!="" && testFlag!="" && msisdn!="" && msisdn.length==10) //always make txtMsg to empty for getting exact bCODE value
				issueBCODE(type,gwNumber,msisdn,testFlag,txtMsg,timestamp,priority);
		}
		if(selectedReq==10){
			alert("Request for : "+"Update bCODE");
			bcodeRef = $("#bcodeRef").val();
			txtMsg = $("#txtMsg").val();
			if(bcodeRef!="" && txtMsg!="") //txtMsg consists the cause of deleting the bCODE
				updateBCODE(bcodeRef,txtMsg);
		}
		if(selectedReq==11){
			alert("Request for : "+"Read All bCODEs");
			readAllBCODEs();
		}
		if(selectedReq==12){
			alert("Request for : "+"SMS Only");
			msisdn = $("#msisdn").val();
			gwNumber = $("#gwNumber").val();
			txtMsg = $("#txtMsg").val();
			timestamp = $("#timestamp").val();
			priority = $("#priority").val();
			if(timestamp !=""){
				if(msisdn!="" && msisdn.length==10 && timestamp!="")//msisdn and timestamp mandatory
					smsOnly(msisdn,gwNumber,txtMsg,timestamp,priority);
			}
			else{
				alert("Must provide the scheduled time !")
			}
			
		}
	});
	
	/****************************************************************************************************************/
	var issue_click = 0 ;
	function activateFields(){
		disableField(9);
		$("#typeOption").prop('disabled', false);
		$("#msisdn").prop('disabled', false);
		$("#testFlagOption").prop('disabled', false);
		$("#gwNumber").prop('disabled', false);
		$("#txtMsg").prop('disabled', false);
		$("#timestamp").prop('disabled', false);
		$("#priorityOption").prop('disabled', false);
		issue_click = 1 ;
	};
	
	$("#btn-bCODE-gen").on('click',function(e){
		if(issue_click==0){
			alert("Provide Required values and Click again...");
			activateFields();
		}
		else{
			alert("Request for : "+"Issue bCODE AND chain calls...");
			type = $("#type").val();
			gwNumber = $("#gwNumber").val();
			msisdn = $("#msisdn").val();
			testFlag = $("#testFlag").val();
			txtMsg = $("#txtMsg").val();
			timestamp = $("#timestamp").val();
			priority = $("#priority").val();
			if(type!="" && testFlag!="" && msisdn!="" && msisdn.length==10) //always make txtMsg to empty for getting exact bCODE value
				issueBCODE_1(type,gwNumber,msisdn,testFlag,txtMsg,timestamp,priority);
			issue_click = 0 ;
		}
		
	});
	function insertIntoBCODETbl(bcode_ref,sms_ref){
		$.ajax({
	   		url:"./saveBCODERef",
	   		type:'POST',
	   		data:"bcode_ref="+bcode_ref+"&sms_ref="+sms_ref,
	   		success:function(response){
	   			//alert(response);
	   			//$("#resData").val(response);
	   			
	   			},error: function(){	 
	   				alert('Error while request in insertIntoBCODETbl..');
			}
	        
	      });
	}//end of insertIntoBCODETbl
	function insertSmsIntoBCODETbl(bcode_ref,sms){
		$.ajax({
	   		url:"./saveBCODESms",
	   		type:'POST',
	   		data:"bcode_ref="+bcode_ref+"&sms="+sms,
	   		success:function(response){
	   			//alert(response);
	   			//$("#resData").val(response);
	   			
	   			},error: function(){	 
	   				alert('Error while request in insertSmsIntoBCODETbl..');
			}
	        
	      });
	}//end of insertSmsIntoBCODETbl
	function insertLeaseIntoBCODETbl(bcode_ref,startTS,endTS,hmac){
		$.ajax({
	   		url:"./saveBCODELease",
	   		type:'POST',
	   		data:"bcode_ref="+bcode_ref+"&startTS="+startTS+"&endTS="+endTS+"&hmac="+hmac,
	   		success:function(response){
	   			//alert(response);
	   			//$("#resData").val(response);
	   			
	   			},error: function(){	 
	   				alert('Error while request in insertLeaseIntoBCODETbl..');
			}
	        
	      });
	}//end of insertLeaseIntoBCODETbl
	function issueBCODE_1(type,gwNumber,msisdn,testFlag,txtMsg,timestamp,priority){
		alert("issueBCODE_1");
		/*$.ajax({
	   		url:"./issueBCODE",
	   		type:'POST',
	   		data:"type="+type+"&gwNumber="+gwNumber+"&msisdn="+msisdn+"&testFlag="+testFlag+"&txtMsg="+txtMsg+"&timestamp="+timestamp+"&priority="+priority,
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			//After successfull creation of a bcode get the bcode_ref and sms_ref
	   			var bcode_ref = jsonObj.response.bcode ._ref;
	   			var sms_ref = jsonObj.response.sms._ref;
	   			alert("bCODE REF:"+bcode_ref+" SMS REF:"+sms_ref);
	   			
	   				//Store in database : bcode_mapping_details
	   			alert("Saving bCODE Ref & SMS Ref to DB");
	   				insertIntoBCODETbl(bcode_ref,sms_ref);//																	1
	   			alert("Data Saved successfully in Database");
	   			
	   			//Read sms as bcode_sms for the sms_ref and get the bcode_sms( It will store the sms details in sms_details table also )
	   			readSMS_1(bcode_ref,sms_ref);
	   				
	   			
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request in issueBCODE..');
			}
	        
	      });*/
	}//end of issueBCODE
	function saveSMSToDB(ref,sms,destination,scheduledTS,sentTS,deliveredTS,customerRef,status,priority){
		$.ajax({
	   		url:"./bCODEDbAction",
	   		type:'POST',
	   		data:{	
	   			ref:ref,
	   			sms:sms,
	   			destination:destination,
	   			scheduledTS:scheduledTS,
	   			sentTS:sentTS,
	   			deliveredTS:deliveredTS,
	   			customerRef:customerRef,
	   			status:status,
	   			priority:priority,
	   			type:1
	   			 },
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			
	   			},error: function(){	 
	   				alert('Error while request..');
			}
	        
	      });
	}//end of saveSMSToDB
	function createLease_1(bcodeRef,noOfLease){
		$.ajax({
	   		url:"./createLease",
	   		type:'POST',
	   		data:"bcodeRef="+bcodeRef+"&noOfLease="+noOfLease,
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			
	   			//Get the lease details and store it into database
	   			var startTS = jsonObj.response.leases.lease._startTS;
	   			var endTS = jsonObj.response.leases.lease._endTS;
	   			var hmac = jsonObj.response.leases.lease._hmac;
	   			alert("saving lease details to DB...");
	   			//store lease details into bcode_mapping_details table
	   			insertLeaseIntoBCODETbl(bcodeRef,startTS,endTS,hmac);//																	4		
	   			alert("lease details populated successfully");
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request in createLease..');
			}
	        
	      });
	}//end of createLease
	function readSMS_1(bcode_ref,smsRef){
		
		$.ajax({
	   		url:"./readSMS",
	   		type:'GET',
	   		data:"smsRef="+smsRef,
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			
	   			//alert(jsonObj.response.status.toString());
	   			if(jsonObj.response.status.toString()==0){//STATUS 0
	   				
	   				
	   				/*var val = confirm("Do u want to save sms to DB ?");
	   				if(val==true){*/
	   					alert("saving sms to Sms Table...");
	   					var sms = jsonObj.response.sms.message.toString() ; 
		   				//alert(sms);
		   				var ref = jsonObj.response.sms._ref ;
		   				//alert(ref);
		   				var destination = jsonObj.response.sms._destination ;
		   				//alert(destination);
		   				var scheduledTS = jsonObj.response.sms._scheduledTS ;
		   				//alert(scheduledTS);
		   				var sentTS = jsonObj.response.sms._sentTS ;
		   				//alert(sentTS);
		   				var deliveredTS = jsonObj.response.sms._deliveredTS ;
		   				//alert(deliveredTS);
		   				var customerRef = jsonObj.response.sms._customerRef ;
		   				//alert(customerRef);
		   				var status = jsonObj.response.sms._status ;
		   				//alert(status);
		   				var priority = jsonObj.response.sms._priority ;
		   				//alert(priority);
	   					saveSMSToDB(ref,sms,destination,scheduledTS,sentTS,deliveredTS,customerRef,status,priority);//				2
	   					alert("SMS Table populated Successfully");
	   					alert("saving sms to bcode mapping table");
	   				//Store in database : bcode_sms in bcode_mapping_details against the bcode_ref above
	   		   			insertSmsIntoBCODETbl(bcode_ref,sms);//																		3
	   		   			alert("bcode_mapping_details populated successfully");
	   		   			createLease_1(bcode_ref,1);
	   				}
	   				
	   			/*}
	   			else
	   				alert(jsonObj.response.message.toString());*/
	   			},error: function(){	 
	   				alert('Error while request in readSMS..');
			}
	        
	      });
	}//end of readSMS
	
	$("#offerMsg").hide();
	$("#REDM_DATA_TBL").hide();
	$("#btn-scanresult").on('click',function(e){
		$("#offerMsg").hide();
		$("#REDM_DATA_TBL").show();
		$("#leaseValue").val("");
		$("#offerValue").val("");
		$("#leaseValue").focus();
		
	});
	//var a = document.getElementById("#leaseValue");
	//var b = $("#offerValue");
	$("#leaseValue").keyup (function() {
	    if (this.value.length === parseInt(this.attributes["maxlength"].value)) {
	    	$("#offerValue").focus();
	    }
	});
	$("#btn-redeem").on('click',function(e){
		var leaseVal = $("#leaseValue").val();
		var offerVal = $("#offerValue").val();
		alert("hmac:"+leaseVal+" offer: Rs."+offerVal+"/-");
		redeemOffer(leaseVal,offerVal);
		
			
	});
	function redeemOffer(leaseVal,offerVal){
		$.ajax({
	   		url:"./redeemOffer",
	   		type:'POST',
	   		data:{	
	   				leaseVal:leaseVal,
	   				offerVal:offerVal
	   			 },
	   		success:function(response){
	   			$("#REDM_DATA_TBL").hide();
	   			$("#offerMsg").show();
	   			$("#offerMsg").text(response);
	   			//$("#offerMsg").hide(10000);
	   			
	   			},error: function(){	 
	   				alert('Error while request in redeemOffer');
			}
	        
	      });
	}//end of redeemOffer
	/****************************************************************************************************************/
	function readCustomer(){
		
		$.ajax({
	   		url:"./readCustomer",
	   		type:'GET',
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			console.log(jsonObj.response.status.toString());
	   			console.log(jsonObj.response.customer._id);
	   			console.log(jsonObj.response.customer._name);
	   			console.log(jsonObj.response.customer.usage.bcode.count.toString());
	   			console.log(jsonObj.response.customer.usage.bcode.remaining.toString());
	   			console.log(jsonObj.response.customer.usage.sms.count.toString());
	   			console.log(jsonObj.response.customer.usage.sms.remaining.toString());
	   			
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request..');
			}
	        
	      });
	}//end of readCustomer
	function createBCODE(type,msisdn,testFlag){
		$.ajax({
	   		url:"./createBCODE",
	   		type:'POST',
	   		data:"type="+type+"&msisdn="+msisdn+"&testFlag="+testFlag,
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request..');
			}
	        
	      });
	}//end of createBCODE
	function readBCODE(bcodeRef){
		
		$.ajax({
	   		url:"./readBCODE",
	   		type:'GET',
	   		data:"bcodeRef="+bcodeRef,
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			
	   			
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request..');
			}
	        
	      });
	}//end of readBCODE
	function createLease(bcodeRef,noOfLease){
		$.ajax({
	   		url:"./createLease",
	   		type:'POST',
	   		data:"bcodeRef="+bcodeRef+"&noOfLease="+noOfLease,
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			
	   			//Get the lease details and store it into database
	   			var startTS = jsonObj.response.leases.lease._startTS;
	   			var endTS = jsonObj.response.leases.lease._endTS;
	   			var hmac = jsonObj.response.leases.lease._hmac;
	   			alert("saving lease details to DB...");
	   			//store lease details into bcode_mapping_details table
	   			insertLeaseIntoBCODETbl(bcodeRef,startTS,endTS,hmac);
	   			alert("lease details populated successfully");
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request in createLease..');
			}
	        
	      });
	}//end of createLease
	function readLease(bcodeRef){
		
		$.ajax({
	   		url:"./readLease",
	   		type:'GET',
	   		data:"bcodeRef="+bcodeRef,
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			console.log(jsonObj.response.status.toString());
	   			console.log(jsonObj.response.customer._id);
	   			console.log(jsonObj.response.customer._name);
	   			console.log(jsonObj.response.customer.usage.bcode.count.toString());
	   			console.log(jsonObj.response.customer.usage.bcode.remaining.toString());
	   			console.log(jsonObj.response.customer.usage.sms.count.toString());
	   			console.log(jsonObj.response.customer.usage.sms.remaining.toString());
	   			
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request..');
			}
	        
	      });
	}//end of readLease
	function readGateway(){
		
		$.ajax({
	   		url:"./readGateway",
	   		type:'GET',
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			/*var strJson = JSON.stringify(jsonObj);
	   			alert(strJson);*/
	   			
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request..');
			}
	        
	      });
	}//end of readGateway
	
	
	function createBCODESMS(bcodeRef,gwNumber,txtMsg,timestamp,priority){
		$.ajax({
	   		url:"./createBCODESMS",
	   		type:'POST',
	   		data:"bcodeRef="+bcodeRef+"&gwNumber="+gwNumber+"&txtMsg="+txtMsg+"&timestamp="+timestamp+"&priority="+priority,
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request..');
			}
	        
	      });
	}//end of createBCODESMS
	

	function readSMS(smsRef){
		
		$.ajax({
	   		url:"./readSMS",
	   		type:'GET',
	   		data:"smsRef="+smsRef,
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			
	   			//alert(jsonObj.response.status.toString());
	   			/*if(jsonObj.response.status.toString()==0){//STATUS 0
	   				
	   				
	   				var val = confirm("Do u want to save sms to DB ?");
	   				if(val==true){
	   					//alert("request to save...");
	   					var sms = jsonObj.response.sms.message.toString() ; 
		   				//alert(sms);
		   				var ref = jsonObj.response.sms._ref ;
		   				//alert(ref);
		   				var destination = jsonObj.response.sms._destination ;
		   				//alert(destination);
		   				var scheduledTS = jsonObj.response.sms._scheduledTS ;
		   				//alert(scheduledTS);
		   				var sentTS = jsonObj.response.sms._sentTS ;
		   				//alert(sentTS);
		   				var deliveredTS = jsonObj.response.sms._deliveredTS ;
		   				//alert(deliveredTS);
		   				var customerRef = jsonObj.response.sms._customerRef ;
		   				//alert(customerRef);
		   				var status = jsonObj.response.sms._status ;
		   				//alert(status);
		   				var priority = jsonObj.response.sms._priority ;
		   				//alert(priority);
	   					saveSMSToDB(ref,sms,destination,scheduledTS,sentTS,deliveredTS,customerRef,status,priority);
	   				}
	   				
	   			}
	   			else
	   				alert(jsonObj.response.message.toString());*/
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request..');
			}
	        
	      });
	}//end of readSMS
	function deleteSMS(smsRef){
		
		$.ajax({
	   		url:"./deleteSMS",
	   		type:'POST',
	   		data:"smsRef="+smsRef,
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			
	   			
	   			
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request..');
			}
	        
	      });
	}//end of deleteSMS
	
	function issueBCODE(type,gwNumber,msisdn,testFlag,txtMsg,timestamp,priority){
		$.ajax({
	   		url:"./issueBCODE",
	   		type:'POST',
	   		data:"type="+type+"&gwNumber="+gwNumber+"&msisdn="+msisdn+"&testFlag="+testFlag+"&txtMsg="+txtMsg+"&timestamp="+timestamp+"&priority="+priority,
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request..');
			}
	        
	      });
	}//end of issueBCODE
	function updateBCODE(bcodeRef,txtMsg){
		
		$.ajax({
	   		url:"./updateBCODE",
	   		type:'POST',
	   		data:"bcodeRef="+bcodeRef+"&txtMsg="+txtMsg,
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			
	   			
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request..');
			}
	        
	      });
	}//end of updateBCODE
	function readAllBCODEs(){
		
		$.ajax({
	   		url:"./readAllBCODEs",
	   		type:'GET',
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			
	   			
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request..');
			}
	        
	      });
	}//end of readAllBCODEs
	function smsOnly(msisdn,gwNumber,txtMsg,timestamp,priority){
		$.ajax({
	   		url:"./smsOnly",
	   		type:'POST',
	   		data:"msisdn="+msisdn+"&gwNumber="+gwNumber+"&txtMsg="+txtMsg+"&timestamp="+timestamp+"&priority="+priority,
	   		success:function(response){
	   			alert(response);
	   			$("#resData").val(response);
	   			var x2js = new X2JS();
	   			var xmlDoc=x2js.parseXmlString(response);
	   			var jsonObj = x2js.xml2json( xmlDoc );
	   			console.log(jsonObj);
	   			//alert(jsonObj);
	   			},error: function(){	 
	   				alert('Error while request..');
			}
	        
	      });
	}//end of issueBCODE
	
	
	
	
});


















/*var offer = prompt("Scan Results to Offer Amounting Rs.", "");
if(offer == null){
	alert("no offer available");
}
else{
	alert("Offer value : Rs."+offer+"/-")
	var flag = confirm("Do u wanna redeem ?");
	if(flag == true){
		goForRedemption();
		$("#offerMsg").text("Redeemed Rs."+offer+"/-"+" Check Database Value");
		$("#offerMsg").show();
		$("#offerMsg").hide(10000);
		
	}
	else{
		$("#offerMsg").text("No Redemption done!!!");
		$("#offerMsg").show();
		$("#offerMsg").hide(10000);
		
	}
}*/
//alert(person);