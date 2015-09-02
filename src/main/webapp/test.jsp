<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Tracing</title>
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var components = [];
		$('#component').click(function(){
			console.log("test component");
			$.get("component", {}, function(data){
				console.log(data);
				//components = data;
				components = [];
				for(var i=2; i<data.length; i++){
					//console.log(data[i].username + " " + data[i].password);
					components.push(data[i]);
				}
			})
		});
		
		$.postJSON = function(url, data, callback) {
		    return jQuery.ajax({
		    headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
		    'type': 'POST',
		    'url': url,
		    'data': JSON.stringify(data),
		    'dataType': 'json',
		    'success': callback
		    });
		};
		
		$('#recommend').click(function(){
			console.log("test recommend");
			console.log(components);
			var postdata = {
					"like": components,
					"dislike":[]
				};
			console.log(postdata);
			$.postJSON("recommend", postdata, function(data){
				console.log(data);
				for(var i=0; i<data.length; i++){
					//console.log(data[i].username + " " + data[i].password);
				}
			})
		});
	});
	
</script>
</head>
<body>

<button id="component">component</button>
<br><br>

<button id="recommend">recommend</button>
<br><br>

</body>
</html>