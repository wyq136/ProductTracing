
 
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
	
    $("#submit").bind("click", function() {
    	   
    	if(!validate_required(username, "username must be filled out!")|| !validate_required(password, "password must be filled out!"))
    		   return;
                $.ajax({
                   type: "POST",
                   url: url+ "login", 
                   data: $("#loginform").serialize(),  
                   success: function(msg){
                	   alert(msg);
             
                      
                   }
                }); 
              
            });
    });