<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Tracing</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery-1.11.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
	<script>
		console.log(window.location);
	</script>
	<style type="text/css">
		a:link,a:visited{
 		text-decoration:none;  /*超链接无下划线*/
	}
	a:hover{
 		text-decoration:none;  /*鼠标放上去有下划线*/
	}
</style>
<script>
	$(document).ready(function (){
	
  	$('#star_1').click(function () {
    	    $('#star_1').attr('src', '../imgs/st.gif');
    	    $('#star_2').attr('src', '../imgs/nst.gif');
    	  	$('#star_3').attr('src', '../imgs/nst.gif');
    	    $('#star_4').attr('src', '../imgs/nst.gif');
      	$('#star_5').attr('src', '../imgs/nst.gif');
 		star = 1;
      });
      $('#star_2').click(function () {
    	  	$('#star_1').attr('src', '../imgs/st.gif');
    	  	$('#star_2').attr('src', '../imgs/st.gif');
    	  	$('#star_3').attr('src', '../imgs/nst.gif');
    	    $('#star_4').attr('src', '../imgs/nst.gif');
      	$('#star_5').attr('src', '../imgs/nst.gif');
    	  	star = 2;
      });
      $('#star_3').click(function () {
    	    $('#star_1').attr('src', '../imgs/st.gif');
    	    $('#star_2').attr('src', '../imgs/st.gif');
    	    $('#star_3').attr('src', '../imgs/st.gif');
    	    $('#star_4').attr('src', '../imgs/nst.gif');
      	$('#star_5').attr('src', '../imgs/nst.gif');
    	    star = 3;
      });
      $('#star_4').click(function () {
    	    $('#star_1').attr('src', '../imgs/st.gif');
  	    $('#star_2').attr('src', '../imgs/st.gif');
  	    $('#star_3').attr('src', '../imgs/st.gif');
  	    $('#star_4').attr('src', '../imgs/st.gif');
  	    $('#star_5').attr('src', '../imgs/nst.gif');
  	    star = 4;
      });
      $('#star_5').click(function () {
    	    $('#star_1').attr('src', '../imgs/st.gif');
    	    $('#star_2').attr('src', '../imgs/st.gif');
    	    $('#star_3').attr('src', '../imgs/st.gif');
    	    $('#star_4').attr('src', '../imgs/st.gif');
    	    $('#star_5').attr('src', '../imgs/st.gif');
    	    star = 5;
      });
      
	});
	
	function aclick(e){
  	  $('#'+ e)[0].click();
    }
  </script>
</head>
<body>
  <div class="container">
  <p></p>
	<!-- <img src="/ProductTracing/imgUpload/${requestScope.product.picture}" width="100%"  style="max-width:500px"/> -->
	<h3>${requestScope.product.productName}</h3>
	<p>${requestScope.product.description}</p>
	<!--  <p>${requestScope.components}</p>-->
	
	<div class="panel-heading">
			<h4 class="panel-title">
				<p data-toggle="modal" data-target="#myModal">评分&nbsp&nbsp
					<a><img src="../imgs/nst.gif" id="star_1"></img></a>
					<a><img src="../imgs/nst.gif" id="star_2"></img></a>
					<a><img src="../imgs/nst.gif" id="star_3"></img></a>
					<a><img src="../imgs/nst.gif" id="star_4"></img></a>
					<a><img src="../imgs/nst.gif" id="star_5"></img></a>
				</p>
			</h4>
		</div>
	
	
	<h4> 组成成分：</h4>
	
	<div class="panel panel-default">
		<c:forEach var="component" items="${requestScope.components}">
		<div class="panel-heading" onclick="aclick('a${component.componentID}')">
			<h4 class="panel-title">
				<a id="a${component.componentID}" data-toggle="collapse" data-parent="#accordion" href="/demo/bootstrap3-plugin-collapse-method.htm#collapse${component.componentID}">
					<c:out value="${component.componentName}" />
				</a>
			</h4>
		</div>
		<div id="collapse${component.componentID}" class="panel-collapse collapse in">
			<div class="panel-body">
				<table class="table">
	    			<c:forEach var="attribute" items="${component.attributes}">
	    	  			<tr><td width="100px"><c:out value="${attribute.attributeName}" />:</td><td><c:out value="${attribute.attributeValue}" /></td></tr>
	    			</c:forEach>
	    		</table>
			</div>
		</div>
		
		</c:forEach>
	</div>
	
  </div>
  
  
  
</body>
</html>