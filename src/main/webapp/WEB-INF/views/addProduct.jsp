<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Narrow Jumbotron Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="jumbotron-narrow.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">
    <div class="row">
      <div class="header clearfix">
        <nav>
          <ul class="nav nav-pills pull-right">
            <li role="presentation" class="active"><a href="#">Home</a></li>
            <li role="presentation"><a href="#">About</a></li>
            <li role="presentation"><a href="#">Contact</a></li>
          </ul>
        </nav>
        <h3 class="text-muted">添加产品</h3>
      </div>

      <div class="jumbotron">
      	<div class="container">
        	<form class="form-horizontal" action="addProduct" method="post" enctype="multipart/form-data">
				  <div class="form-group">
				    <label for="inputEmail" class="col-sm-2 control-label">名称</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="product_name" name="product_name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="selectCatalog" class="col-sm-2 control-label">类别</label>
				    <div class="col-sm-8">
				      <select class="form-control" id="catalog" name="catalog">
				      	<option value="1">甜品</option>
				      	<option value="2">水果</option>
				      	<option value="3">饮料</option>
				      </select>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="inputDescription" class="col-sm-2 control-label">描述</label>
				    <div class="col-sm-8">
				      <textarea class="form-control" id="description" name="description" rows="3"></textarea>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="inputEmail" class="col-sm-2 control-label">店铺链接</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="shop" name="shop">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <input type="file" id="picture" name="fileUpload" title="Search for a file to add">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-lg btn-success">Sign in</button>
				    </div>
				  </div>
			</form>
		</div>
      </div>

      <footer class="footer">
        <p>&copy; Company 2014</p>
      </footer>
	</div>
    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
