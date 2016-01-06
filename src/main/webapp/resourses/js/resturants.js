
/***
*读取指定的Cookie值
*@param {string} cookieName Cookie名称
*/
var random = false;
var url = "http://localhost:8080/ProductTracing/";
currentPage={};

function ReadCookie(cookieName) {
    var theCookie = "" + document.cookie;
    var ind = theCookie.indexOf(cookieName);
    if(ind==-1 || cookieName=="") return "";
    var ind1 = theCookie.indexOf(';',ind);
    if(ind1==-1) ind1 = theCookie.length;
    /*读取Cookie值*/
    return unescape(theCookie.substring(ind+cookieName.length+1,ind1));
}

currentPage.SendPost  = function(postData)  {
     var curUrl;
     if( postData['tag'] == "轻工")
         curUrl = url  + "lightProduct";
    else curUrl  = url + "merchant";
    alert(curUrl);

       $.ajax({
           //提交数据的类型 POST GET
           type:"POST",
           //提交的网址
           url:curUrl,
           //提交的数据
           data:postData,
           //返回数据的格式
           datatype: 'json',//"xml", "html", "script", "json", "jsonp", "text".
     
           //成功返回之后调用的函数             
           success:function(data){
               $("#Merchants").empty();
               alert(data);

    
                
               if(('merchant' in data[0])){
            

                    for(var i=0; i<data.length; i++){ 
                        $("<li class='im-g'><a href=''><img src='" + data[i]['product']['picture'] + "'class='img-responsive' alt=''></a></li>").appendTo($("#Merchants"));




                    //  alert("共推荐产品:" + recommendProduct.length);
                        $( "<p class='mid text-center'>" +  data[i]['product']['productName']  + "</p>").appendTo($("#Merchants")); 
                        //alert( "<img src='" + url + recommendProduct[i]['picture'] +  "'" + " width:100% >");
                        $( "<img src='" + url + "imgUpload/" + data[i]['product']['picture'] +  "'" + " width:'100%'  align='middle'/>").css({
                            "width":"70%",  /* 宽度 */ 
                            "clear": "both", 
                            "display":" block", 
                            "margin":"auto" 
                           }).appendTo($("#Merchants")); 
                        $("<p></p>").appendTo($("#Merchants"));
                        
                        
                        
                        
                        
                        var components ="";
                        for(var x =0 ; x < data[i]['components'].length; x++)
                            if(x == 0)
                                components += data[i]['components'][x]['componentName'];
                            else components += "," + data[i]['components'][x]['componentName'];
                        $("<p>原料:" +  components + "</p>").appendTo($("#Merchants")); 
                        $("<p>商家:" +  data[i]['merchant']['merchantName'] + "</p>").appendTo($("#Merchants"));
                        $("<p>地址:" +  data[i]['merchant']['address'] + "</p>").appendTo($("#Merchants")); 
                 
                        //if(recommendProduct[i]['shop'] != 'local')
                        //$("<p>地址：" +  recommendProduct[i]['shop'] + "</p>").appendTo($("#Items")); 

                    
                    }
                    
                   
                   return;
                   
               }
                
               
             
            
          
                for(var i=0; i<data.length; i++){
                
                    $("<li class='im-g'><a href=''><img src='" + url + "imgUpload/" + data[i]['picture'] + "'class='img-responsive' alt=''></a></li>").appendTo($("#Merchants"));
   
                //  alert("共推荐产品:" + recommendProduct.length);
                 var recp = "<p>推荐菜： ";
            
                    for(var j = 0; j < data[i]['products'].length; j++)
                        if(j != 0)
                        recp += ",<a href=" + url + "product/" +  data[i]['products'][j]['productID'] + ">" +    data[i]['products'][j]['productName'] + "</a>" ;
                        else 
                            recp += "<a href=" + url + "product/" +  data[i]['products'][j]['productID'] + ">" +    data[i]['products'][j]['productName'] + "</a>" ;
                       recp += "</p>";
                       recp +=  "<p>评分: " +  data[i]['rating'] + "</p>";
                        recp +="<p>人均价格: " +  data[i]['price'] + "</p>";
                 //       recp += "<p>距离: " +  data[i]['positionX'].toFixed(2) + "km</p>";



                    $( "<div class='order-top' ><li class='data'><h4>" +  data[i]['merchantName'] +"</h4>" + recp + "</li><div class='clearfix'></div></div>").appendTo($("#Merchants")); 
                  //  $("<li class='bt-nn'><a class='morebtn hvr-rectangle-in' href='orders.html'>Explore</a></li>").appendTo($("#Merchants")); 

                   // $("<p>评分: " +  data[i]['rating'] + "</p>").appendTo($("#Merchants")); 
                  //  $("<p>人均价格: " +  data[i]['price'] + "</p>").appendTo($("#Merchants")); 
                 //   $("<p>地址: " +  data[i]['address'] + "</p>").appendTo($("#Merchants")); 
                   
                  //  $("<p>距离: " +  data[i]['positionX'].toFixed(2) + "km</p>").appendTo($("#Merchants")); 
                    //if(recommendProduct[i]['shop'] != 'local')
                    //$("<p>地址：" +  recommendProduct[i]['shop'] + "</p>").appendTo($("#Items")); 

                    

                     
                }
                
           }   
   
        });


}

$(function() {
		var postData = {};
		postData['priceLow'] = ReadCookie('priceLow');
		postData['priceHigh'] = ReadCookie('priceHigh');
		postData['tag'] = ReadCookie('tag');
		currentPage.SendPost(postData);
	
});

