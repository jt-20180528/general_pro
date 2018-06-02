<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        (function longPolling() {
            $.ajax({
                url: "${pageContext.request.contextPath}/AutoMonitor",
                data: {"time": new Date().getTime()},
                dataType: "text",
                timeout: 5000,
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $("#state").append("[state: " + textStatus + ", error: " + errorThrown + " ]<br/>");
                    if (textStatus == "timeout") { // 请求超时
                        longPolling(); // 递归调用
                    } else if(textStatus == "error"){ // 网络异常
                    	alert(' 网络异常');
                        //longPolling();
                    }
                },
                success: function (data, textStatus) {
                    $("#state").append("[state: " + textStatus + ", data: { " + data + "} ]<br/>");
                    
                    if (textStatus == "success") { // 请求成功
                        longPolling();
                    }
                }
            });
        })();
    });
</script>
</head>
<body>
<span id="state"></span>
</body>
</html>