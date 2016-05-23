
function setCookie(c_name,value,expiredays) 
{

var exdate=new Date()
exdate.setDate(exdate.getDate()+expiredays)

document.cookie=c_name+ "=" +escape(value)+
((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
}

function getCookie(c_name)
{
if (document.cookie.length>0)
  {
  c_start=document.cookie.indexOf(c_name + "=")
  if (c_start!=-1)
    { 
    c_start=c_start + c_name.length+1 
    c_end=document.cookie.indexOf(";",c_start)
    if (c_end==-1) c_end=document.cookie.length
    return unescape(document.cookie.substring(c_start,c_end))
    } 
  }
return ""
}

$(function() {

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
	 
      dishPost = getCookie('dishPost');
     if(dishPost!="")
    	 dishPost =  JSON.parse(dishPost);
     else dishPost = null;
      $.postJSON(url + "recommend", dishPost, function(data){
    
    	recommendProduct = data;
    	//alert("data" + data.length);

    	$("#Merchants").empty();
  
    	for(var i=0; i<recommendProduct.length; i++){ 
                    recp="<p>Components:";

              for(var x =0 ; x < recommendProduct[i]['components'].length; x++)
                    if(x == 0)
                 recp += recommendProduct[i]['components'][x]['componentName'];
             else recp += "," + recommendProduct[i]['components'][x]['componentName'];




                     recp +=  "</p><p>Merchant: " +  recommendProduct[i]['merchant']['merchantName'] + "</p>";
                        recp +="<p>Address: " +  recommendProduct[i]['merchant']['address'] + "</p>";
                    $( "<div class='order-top' ><li class='im-g'><a href=''><img src='" + url + "imgUpload/" + data[i]['product']['picture'] + "'class='img-responsive' alt=''></a></li><li class='data'><h4>" +  data[i]['product']['productName'] +"</h4>" + recp + "</li><div class='clearfix'></div></div>").appendTo($("#Merchants")); 
    //$("<p>地址：" +  recommendProduct[i]['shop'] + "</p>").appendTo($("#Items")); 

  
  }
  
 






  
      
    	
        
      });

	
});

