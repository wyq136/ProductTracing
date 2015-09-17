<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Tracing</title>
<!-- <link href="../css/bootstrap.min.css" rel="stylesheet"> -->
<script>
	console.log(window.location);
</script>
</head>
<body>
  <div class="row">
	<img src="/ProductTracing/imgUpload/${requestScope.product.picture}" width="100%"  style="max-width:500px"/>
	<h3>${requestScope.product.product_name}</h3>
	<p>${requestScope.product.description}</p>
	<!--  <p>${requestScope.components}</p>-->
	<p> 组成成分：
	<c:forEach var="component" items="${requestScope.components}">
	    <c:out value="${component.component_name}" />
	    <c:forEach var="attribute" items="${component.attributes}">
	    	<p><c:out value="${attribute.attribute_name}" />:&nbsp;&nbsp;<c:out value="${attribute.attribute_value}" /></p>
	    </c:forEach>
	</c:forEach>
	</p>
  </div>
</body>
</html>