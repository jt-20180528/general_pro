<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<c:set var="ctx" value="${pageContext.request['contextPath']}" />  
  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
<title>HTTP 长连接测试 —— 监控服务器时间 </title>  
</head>  
  
<body>  
<div id="monitor-window">服务器现在是：<span id="time"></span></div>  
<form id="a-form" action="${ctx}/ServerTimeMonitor" method="post" target="handleFrame">  
    <input type="submit" name="submit" id="submit" value=" 获取并监控服务器时间 " />  
</form>  
<iframe name="handleFrame" id="handleFrame" style="display:none"></iframe>  
  
<script type="text/javascript" src="${ctx}/resource/common/js/jquery.min.js"></script>  
<script type="text/javascript">//<![CDATA[ 
function showServerTime(msg) { 
    $("#time").html(msg); 
} 
//]]></script>  
</body>  
</html>  