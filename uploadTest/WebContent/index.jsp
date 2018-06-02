<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%
	String path = request.getContextPath();
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>妞见妞爱 QQ:609865047</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" href="css/jquery-ui-1.8.16.custom.css" type="text/css"></link>
    <link rel="stylesheet" href="css/main.css" type="text/css"></link>
	<script type="text/javascript" src="js/jquery-1.6.2.min.js" ></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.16.custom.min.js" ></script>
	<script type="text/javascript" src="js/script.js" ></script>
	<script type="text/javascript">
	 	
	 	
		function uploadFile(){
			var file = document.getElementById("file").value;
			if(file == ""){
				alert("请选项上传文件！");
				return false;
			}
			document.getElementById("editForm").submit();
			document.getElementById("updateButton").disabled = "disabled";
			ajaxBackState();
		}
		
		var bool = true;
		
		var readedBytes = 0;
		var totalBytes = 0;
		function ajaxBackState(){
			
			$.post("<%=path%>/servlet/UploadFileProgressBar",{uploadStatus:'uploadStatus'},function(result){
				var obj = eval("("+result+")");
				var txt = document.getElementById("txt");
				alert(obj);
				readedBytes = obj["readedBytes"];
				totalBytes = obj["totalBytes"];
				
				if(obj["error"] == "0"){
					txt.innerHTML = obj["statusMsg"]+"："+ readedBytes +"/"+totalBytes;
				}else if(obj["error"] == "1"){
					txt.innerHTML = obj["statusMsg"];
					bool = false;
				}else{
				    txt.innerHTML = obj["statusMsg"]+"："+ readedBytes +"/"+totalBytes;
					bool = false;
				}
			});
			document.getElementById("bytes").innerHTML += readedBytes + "<br>";
			progressbar(readedBytes,totalBytes)
			if(bool){
					setTimeout("ajaxBackState()",1000); 					
			}
		}
		
		function progressbar(readedBytes,totalBytes){
				 
			 $('#progress').children('.pbar').progressbar();
			 iPerc = (readedBytes > 0) ? (readedBytes / totalBytes) * 100 : 0; // percentages
			 
			 $('#progress').children('.percent').html('<b>'+iPerc.toFixed(1)+'%</b>');
			 $('#progress').children('.pbar').children('.ui-progressbar-value').css('width', iPerc+'%');
			 if (iPerc >= 100) {
                  $('#progress').children('.percent').html('<b>100%</b>');
                  $('#progress').children('.elapsed').html('Finished');
             }
			 
		}
		
		/***
		*
		*
		var readedBytes = 0;
		var totalBytes = 100000;
		function test(){
			
			readedBytes = readedBytes + 1000;
			
			progressbar(readedBytes,totalBytes);
			if(readedBytes >= totalBytes){
				clearInterval(intervalProcess);
			}
		}
		
		var intervalProcess = setInterval("test()",1000);
		**/ 
	</script>

	</head>
  
  <body>
  	<iframe name="uploadIfr" style="display:none;"></iframe>
    <form id="editForm" action="<%=path%>/servlet/UploadFileProgressBar" method="post" enctype="multipart/form-data" target="uploadIfr" > 
	    <input type="file" id="file" name="file" style="width: 300px;" >&nbsp;
	    <input type="button" style="height:20px;"  id="updateButton" value=" 上 传 " onclick="uploadFile()" ><br> 
	    <span id='txt' ></span><br><br>
	   <div id="progress" style="width: 400px" >
            <div class="percent"></div>
            <div class="pbar"></div>
            <div class="elapsed"></div>
        </div>
        <span id=bytes ></span><br>
    </form>
  </body>
</html>