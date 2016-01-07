



   $(function() {

    $("#submit").bind("click", function() {
                $.ajax({
                   type: "POST",
                   url: url+ "login", 
                   data: $("#loginform").serialize(),  
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