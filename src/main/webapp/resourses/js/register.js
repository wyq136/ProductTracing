 
function validate_email(field,alerttxt)
{
with (field)
{
apos=value.indexOf("@")
dotpos=value.lastIndexOf(".")
if (apos<1||dotpos-apos<2) 
  {alert(alerttxt);return false}
else {return true}
}
}
function validate_required(field,alerttxt)
{
with (field)
{
if (value==null||value=="")
  {alert(alerttxt);return false}
else {return true}
}
}

   $(function() {

    $("#regist").bind("click", function() { 
    	 if(!validate_required(username, "username must be filled out!")|| !validate_required(password, "password must be filled out!"))
   		   return;
    	 if(!validate_email(email,"Not a valid e-mail address!"))
    		 return;
                $.ajax({
                   type: "POST",
                   url: url+ "rigister",
                   data: $("#registform").serialize(), 
                   success: function(msg){
       
                     if(msg=='success'){
                    	 alert('success');
                    	window.location.href='/ProductTracing/';
                       // $.mobile.changePage("content/first.html","slidedown", true, true);
                     }else{
                         alert("fail");
                       
                     }
                      
                   }
                }); 
              
            });
    });