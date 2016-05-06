
$.ajaxPrefilter(function(options, originalOptions, jqXHR){
    if (options.type.toLowerCase() === "post") {
        // initialize `data` to empty string if it does not exist
        options.data = options.data || "";

        // add leading ampersand if `data` is non-empty
        options.data += options.data?"&":"";

        // add _token entry
        options.data += "_csrf=" + token;
    }
});

$(function() {

	$("#passwordform").submit( function()
	{
		showYesNo("Are you sure?", 
				"Are you sure you want to change your password?",
				function(){
					var oldPass = $("#oldPassword").val(),
						newPass = $("#newPassword").val(),
						confirmPass = $("#confirmPassword").val();
					if( oldPass.length == 0 
						|| newPass.length == 0	
						|| confirmPass.length == 0	
					)
					{
						showError("Error", "Some fields are empty.");
						return;
					}
					
					if( newPass !== confirmPass	)
					{
						showError("Error", "New password pair not identical.");
						return;
					}
					
					$.post( postUrl,
					{ 
						empId: empId, 
						newPass: newPass, 
						oldPass : oldPass
					},		
					function(data, textStatus, jqXHR)
				    {
				         if( data == 'passwordfail' )
			        	 {
				        	showError("Error", "Current password is incorrect.");
							return;
			        	 }
				         
				         if( data == 'success' )
			        	 {
				        	showInfo("Success", "Password changed successfuly.", function(){window.location.replace(successUrl);});
				        	return;
			        	 }
				    });
					
					$(this).dialog('close');
				}
		);
		
		return false;
	});
	
});

function showInfo(title, content, closefunc) {
	$("#user_dialog").dialog({
		dialogClass : "no-close",
		title : title,
		close: closefunc,
		buttons : {
			"OK" : function() {
				$(this).dialog('close');
			}
		}
	});
	$("#user_dialog p").text(content);
}

function showError(title, content) {
	$("#user_dialog").dialog({
		dialogClass : "no-close",
		title : title,
		buttons : {
			"OK" : function() {
				$(this).dialog('close');
			}
		}
	});
	$("#user_dialog p").text(content);
}

function showYesNo(title, content, yesfunc) {
	$("#user_dialog").dialog({
		title : title,
		modal: true,
		buttons : {
			"YES" : yesfunc,
			"NO" : function() {
				$(this).dialog('close');
			}
		}
	});
	$("#user_dialog p").text(content);
}