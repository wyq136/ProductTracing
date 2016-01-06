 
currentPage={};

neighbor = false;
var isPrice = false;
var isCategory = false;
var price;
var category;

/***
http://www.cnblogs.com/mikelin/archive/2010/02/03/1662479.html
* @param {string} cookieName Cookie名称
* @param {string} cookieValue Cookie值
* @param {number} nDays Cookie过期天数
*/
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

 currentPage.change_price = function(str){

  price = str;
 	if(str == 'p0_100'){
 		str = "0-100"
     
        setCookie('priceLow', '0',7);
        setCookie('priceHigh', '100', 7);
        alert(getCookie('priceLow'));
    
 }
 	else if(str == "p100_200"){
 		str = "100-200";
         setCookie("priceLow", 100, 7);
        setCookie("priceHigh", 200, 7);
    }
 	else if(str = "pg_200"){
 		str = "more than 200";
       setCookie("priceLow", 200, 7);
        setCookie("priceHigh", 99999, 7);
    }

 	$("#selected_price").text("Price: " + str)
 };

  currentPage.change_category = function(str){
 	category = str;
 	if(str == "泰国菜")
 		str = "Thailand";
 	else if(str == "韩国菜")
 		str = "Korea";
 	else if(str =="中东料理" )
 		str = "Middle East";
 	else if(str == "俄罗斯菜" )
 		str = "Russia";
 	else if(str == "法国菜")
 		str = "France";
 	else if(str == "川菜")
 		str = "Sichuan";
 	else if(str == "湘菜")
 		str = "Hunan";
 	else if(str == "粤菜")
 		str = "Guangzhou";
 	else if(str == "东北菜")
 		str = "Dongbei";
 	else if(str == "江浙菜")
 		str = "Jiangzhe";
 	else if(str == "轻工")
 		str = "light industry";
 	setCookie('tag',category, 7);
 	$("#selected_catgory").text("Catgory: " + str)
 };
$(function() {
         setCookie('priceLow', '', 7);
         setCookie('priceHigh','',7);
         setCookie('tag','',7);
        $("#neighbor").bind("click", function() {
        	if(neighbor == false){
        	 	$("#neighbor").css({
            	"background-color":"#8ad833",
            	});
            	neighbor = true;
        	 }
        	else{
        		$("#neighbor").css({
            	"background-color":"#d96b66",
            	});
            	neighbor = false;

        	}
          });

          $("#price").bind("click", function() {

			$("#select_form").empty();

          	$("<div class='section_room'><select id='country' onChange='currentPage.change_price(this.value)'' class='frm-field required'><option value='p0_100'>0-100</option><option value='p100_200'>100-200</option><option value='pg_200'>more than 200</option></select></div>").appendTo($("#select_form")); 

        	if(isPrice == false){
        	 	$("#price").css({
            	"background-color":"#8ad833",
            	});
            	isPrice = true;
        	 }
        	else{
        		$("#price").css({
            	"background-color":"#d96b66",
            	});
            	isPrice = false;

        	}
          });

            $("#catgory").bind("click", function() {
            	$("#select_form").empty();

          	$("<div class='section_room'><select id='country' onChange='currentPage.change_category(this.value)'' class='frm-field required'><option value='泰国菜'>Thailand</option><option value='韩国菜'>Korea</option><option value='中东料理'>Middle East</option><option value='俄罗斯菜'>Russia</option><option value='法国菜'>France</option><option value='川菜'>Sichuan</option><option value='湘菜'>Hunan</option><option value='粤菜'>Guangzhou</option><option value='东北菜'>Dongbei</option><option value='江浙菜'>Jiangzhe</option><option value='轻工'>light industry</option></select></div>").appendTo($("#select_form")); 

        	if(isCategory == false){
        	 	$("#catgory").css({
            	"background-color":"#8ad833",
            	});
            	isCategory = true;
        	 }
        	else{
        		$("#catgory").css({
            	"background-color":"#d96b66",
            	});
            	isCategory = false;

        	}
          });
 });