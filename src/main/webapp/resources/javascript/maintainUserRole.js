var ctxNew = $("#svsURL").val();
var ctx =  $("#svsURL").val();
var prevRole = "";
var prevUserId ="";
var clickBttn = "";
var isExist = null;
/**
* MalKa002
*/
$(document).ready(function(){
	$("#openid").click(function(){
		// alert("ok") ;
		removeRole();
		$("#checkExistingDialog-confirm").modal('hide');  
		});
	
	// log user activity; View Maintain User Role Page
	logUserActivity(5001, 'Maintain User Role Page');
});
function validateUser()
{
          

var userId = $("#userId").val();
  
if (userId == "") {
	
            alert("Please enter User ID");
            return;

}
var role = $("#roleId").val();

if (role=="default") {
            alert("Please select role");
            return;
}

//REST api ajax call to validate the user


var formData = {};
var paramLogin =1;
            try{
                        //showLayer();
            $.ajax({
            			dataType: "json",
                        url :  ctxNew + "/helios/maintainUserRole/validate/" +userId +"/"+paramLogin,
                        type : "GET",
                        cache : false,
                        timeout : 95000,
                        data : JSON.stringify(formData),
                        headers : {},
                        contentType : "application/json",
                        
                        success : function(data, textStatus, jqXHR) {
                                    
                                    if(data !='' && data != ' ' && data != undefined && data != null){
                                    	
                                                var lanId = data[0].LAN_ID;
                                                var firstName = data[0].FIRST_NAME;
                                                var lastName = data[0].LAST_NAME;
                                                if((lanId!="" && lastName!="" && firstName!="")&&(lanId!=undefined && lastName!=undefined && firstName!=undefined)){
                                                            isUserExist(lanId,firstName,lastName,role);
                                                            
                                                }
                                                else
                                                            {
                                                            var msg="Role Assignation for "+userId+" failed because "
                                                            if((lanId =="") || (lanId == undefined)){
                                                                        msg= msg+"LAN Id ";
                                                            }
                                                            if((firstName =="") || (firstName == undefined)){
                                                                        msg = msg +"First Name ";
                                                            }
                                                            if ((lastName=="")||(lastName == undefined)){
                                                                        msg=msg+"Last Name ";
                                                            }
                                                            msg = msg +"infromation is unavailable. "
                                                            alert (msg);
                                                            // hideLayer();
                                                            return;
                                    }
                                    }                       
                                    else{
                                                alert("Please enter a valid User Id");
                                    }
                            ;
                                    //findUser();
                                    // hideLayer();
                        
                        },
                        
                        error : function(jqXHR, textStatus, errorThrown) {
                                    //alert("error"+errorThrown)
                                    // hideLayer();
                        
                        }
                        });

            }catch(e){
                        //alert("err"+e)
            
            

}           
}

            
function assignRole(lanId,firstName,lastName,userId,role){
	// log user activity; Assign Role
	logUserActivity(5010, 'Assign Role');
            var urlNew = ctxNew + "/api/helios/maintainUserRole/assign/" + userId+ "/" + lanId +"/"+firstName+"/"+role+"/"+lastName;
            $.ajax({
            dataType: "json",
            url : urlNew,
            type : "GET",
            cache : false,
            timeout : 15000,
            data : {},
            headers : {},
            success : function(data, textStatus, jqXHR) {
            	// log user activity; Role assigned successfully.
            	logUserActivity(5011, 'Role Assigned Successfully');

            	alert ("Role assigned successfully");
                        findUser();
                        
            },
            error : function(jqXHR, textStatus, errorThrown) {
                        //alert("error"+errorThrown)
            
            }
            });
}           



function findUser(){
	// log user activity; Open User Details
	logUserActivity(5002, 'Open User Details');
	
            //alert($("#userId").val())
            var userId = $("#userId").val();
            var formData = {};
            if (userId!=null && userId !=""){
                        try{
                                    ////showLayer();
                        $.ajax({
                        			dataType: "json",
                                    url :  ctxNew + "/api/helios/maintainUserRole/findUser/" + userId,
                                    type : "GET",
                                    cache : false,
                                    timeout : 95000,
                                    data : JSON.stringify(formData),
                                    headers : {},
                                    contentType : "application/json",
                                    
                                    success : function(data, textStatus, jqXHR) {
                                                /*if(data.insertFlag !=null){
                                                            $('#roleId option[value=' + data.insertFlag + ']').attr('selected','selected');
                                                }
                                                else{
                                                            $('#roleId option[value=default]').attr('selected','selected');
                                                }*/
                                                
                                                
                                                if(data.insertFlag){
		                                                	// log user activity; User Details Successfully Opened
		                                                	logUserActivity(5003, 'User Details Opened Successfully');
                                                	
                                                            $('#roleId option[value=' + data.insertFlag + ']').attr('selected','selected');
                                                            $("#assignRole").hide();
                                                            $("#updateRole").show();
                                                            $("#removeRole").show();
                                                            prevRole = data.insertFlag;
                                                            prevUserId = userId;
                                                            clickBttn = "update";
                                                            getAssignedRoleMappings(data.insertFlag);
                                                }
                                                else{
                                                            $('#roleId option[value=' +"default"+ ']').attr('selected','selected');
                                                            $("#updateRole").hide();
                                                            $("#assignRole").show();
                                                            $("#removeRole").hide();
                                                            $('#assignUserRoles').hide();
                                                            alert("User Id entered does not exist. Please assign a role.");
                                                            return;

                                                }
                                                
                                                //hideLayer();
                                    },
                                    
                                    error : function(jqXHR, textStatus, errorThrown) {
                                                //alert("error"+errorThrown)
                                    			$('#assignUserRoles').hide();
                                                // hideLayer();
                                    }
                                    });

                        }catch(e){
                                    //alert("err"+e);
                        }
                        $('#assignUserRoles').show();
            }           
                        else{
                                    $('#roleId option[value=' +"default"+ ']').attr('selected','selected');
                                    $("#updateRole").hide();
                                    $("#assignRole").show();
                                    $("#removeRole").hide();
                                    $('#assignUserRoles').hide();
                                    alert("Please enter a User Id.");
                                    return;
                        }
            }
            
            function updateUserRole()
            {
                        
                        try{
                                    //showLayer();
                                    var userId = $("#userId").val();
                                    if (userId == "") {
                                                alert("Please enter a User ID");
                                                return;

                                    }
                                    var role = $("#roleId").val();

                                    if (role=="default") {
                                                alert("Please select a role");
                                                return;
                                    
            }
                                    if(role == prevRole){
                                                alert("No changes have been made");
                                                // hideLayer();
                                                return;
                                    }

                                 // log user activity; Update Role
                                	logUserActivity(5006, 'Update Role');
            var urlNew = ctxNew + "/api/helios/maintainUserRole/update/" + userId+ "/" + role;
            $.ajax({
            dataType: "json",
            url : urlNew,
            type : "GET",
            cache : false,
            timeout : 15000,
            data : {},
            headers : {},
            success : function(data, textStatus, jqXHR) {
            	// log user activity; Role updated successfully.
            	logUserActivity(5007, 'Role updated Successfully');
            alert ("Role updated successfully");
            findUser();
            // hideLayer();
            }
            });
                        }catch(e){
                                    //alert("err"+e);
                        }           
                        
            }
            
            
            function removeUserRole(){
            	

                
                
                            //showLayer();
                            
                            var userId = $("#userId").val();
                            /*if (userId == "") {
                                        alert("Please enter User ID");
                                        return;

                            }*/
                            var role = $("#roleId").val();

                            /*if (role=="default") {
                                        alert("Please select role");
                                        return;
                            
                                }*/
                           /* if(role == prevRole){
                                        alert("No Changes have been made");
                                        hideLayer();
                                        return;
                            }*/
                            
                            checkConfirmDialog("Do you want to remove the assigned role "+prevRole+" of the user "+prevUserId+" ?");
                            // hideLayer();
  
            }      
                            
   function  removeRole (){
	// log user activity; Remove Role
   	logUserActivity(5008, 'Remove Role');
   	
    var urlNew = ctxNew + "/api/helios/maintainUserRole/removeUserRole/" + prevUserId;
    try{
    $.ajax({
    dataType: "json",
    url : urlNew,
    type : "GET",
    cache : false,
    timeout : 15000,
    data : {},
    headers : {},
    success : function(data, textStatus, jqXHR) {
    	// log user activity; Role removed successfully.
       	logUserActivity(5009, 'Role Removed Successfully');
    alert ("Role removed successfully");
    $('#roleId option[value=' +"default"+ ']').attr('selected','selected');
    $("#updateRole").hide();
    $("#assignRole").show();
    $("#removeRole").hide();
    $('#assignUserRoles').hide();
    // hideLayer();
    }
    });
                }catch(e){
                            //alert("err"+e);
                }           
                
    
            	
            }
            
            function showRoleDesc(){
                        var title = "";
                        if(($("#roleId").find("option:selected").attr("title"))!= undefined){
                                    title = $("#roleId").find("option:selected").attr("title");
                                    $("#roleId").attr('title', title);
                        }
                        }
            
            

function getRoles(){
            findUser();
}


     
     function allowNumbersOnly(myfield, e, dec) {
                        var key;
                        var keychar;
                        if (window.event)
                                    key = window.event.keyCode;
                        else if (e)
                                    key = e.which;
                        else
                                    return true;
                        keychar = String.fromCharCode(key);

                        // control keys
                        if ((key == null) || (key == 0) || (key == 8) || (key == 9) || (key == 13)
                                                || (key == 27) || (key == 67) || (key == 86) || (key == 88))
                                    return true;

                        // numbers
                        else if ((("0123456789").indexOf(keychar) > -1))
                                    return true;

                        // decimal point jump
                        else if (dec && (keychar == ".")) {
                                    myfield.form.elements[dec].focus();
                                    return false;
                        } else
                                    return false;
     }
                        
     function checkExistingConfirmDialog(textToDisplay) {
    	 alert("inside checkexistingconfirmdialog"+textToDisplay);
         $("#checkExistingDialog-confirm").html(textToDisplay);
         alert("inside checkexistingconfirmdialog"+textToDisplay);
          // Define the Dialog and its properties.
          $("#checkExistingDialog-confirm").dialog({
                resizable : false,
                modal : true,
                title : "Maintain User Role",
                height : 180,
                width : 400,
                buttons : {
                      "Ok" : function() {
                            $(this).dialog('close');
                            //showComLogDialog1();
                            
                      }
         
                }
          });
    }
function checkConfirmDialog(textToDisplay){// $("#checkExistingDialog-confirm").html(textToDisplay);
     // Define the Dialog and its properties.
	$("#modalBodyId").html("");
	$("#modalBodyId").html("<p>"+textToDisplay+"</p>");
	$('#checkExistingDialog-confirm').modal({
						 "backdrop"  : "static",
						"keyboard"  : true,
						"show"      : true           
	});
	//alert(11)
     /*$("#checkExistingDialog-confirm").dialog({
           resizable : false,
           modal : true,
           title : "Maintain User Role",
           height : 180,
           width : 400,
           buttons : {
                 "Yes" : function() {
                       $(this).dialog('close');
                       removeRole();
                       
                 },
     			"No" : function(){
     				$(this).dialog('close');
     			}
    
           }
     });*/}
     
     $(document).keyup(function(event) {
                        if (event.keyCode == 27) {
                                    clearUserForm();
                        }
                        if(event.keyCode == 13){
                                    if(clickBttn != "update"){
                                                validateUser();
                                    }
                                    else{
                                                updateUserRole();
                                    }
                        }

            });
    
     
     function clearUserForm(){
             $("#userId").val("") ;
                                    $('#roleId option[value=' +"default"+ ']').attr('selected','selected');
                                    $("#updateRole").hide();
                                    $("#assignRole").show();
                                    $("#removeRole").hide();
                                    $('#assignUserRoles').hide();
     }
            function isUserExist(lanId,firstName,lastName,role){
                        
                        var userId = $("#userId").val();
                        var formData = {};
                        if (userId!=null && userId !=""){
                                    try{
                                                ////showLayer();
                                    $.ajax({
                                    			dataType: "json",
                                                url :  ctxNew + "/api/helios/maintainUserRole/findUser/" + userId,
                                                type : "GET",
                                                cache : false,
                                                timeout : 95000,
                                                data : JSON.stringify(formData),
                                                headers : {},
                                                contentType : "application/json",
                                                
                                                success : function(data, textStatus, jqXHR) {                             
                                                            if(data.insertFlag){
                                                                        
                                                                        alert("User Id "+userId+" already has an assigned Role. Please click Open to view details");
                                                                                    }
                                                                                    else{
                                                                                                
                                                                                                assignRole(lanId,firstName,lastName,userId,role);
                                                                                                
                                                                                               
                                                                                    }
                                                            
                                                            //hideLayer();
                                                },
                                                
                                                error : function(jqXHR, textStatus, errorThrown) {
                                                            //alert("error"+errorThrown)
                                                			$('#assignUserRoles').hide();
                                                            // hideLayer();
                                                }
                                                });

                                    }catch(e){
                                                //alert("err"+e);
                                    }
                        }           
                                    else{
                                                $('#roleId option[value=' +"default"+ ']').attr('selected','selected');
                                                $("#updateRole").hide();
                                                $("#assignRole").show();
                                                $("#removeRole").hide();
                                                $('#assignUserRoles').hide();
                                                alert("Please Enter a User Id.");
                                                return;
                                    }
                        }
                                    
            
    $(function() {
        $('#assignUserRoles').hide();

        $('#roleId').change(function() {
        	if($('#roleId').val() != 'default') {
        		getAssignedRoleMappings($('#roleId').val());
                $('#assignUserRoles').show(); 
            } else {
                $('#assignUserRoles').hide(); 
            } 
        });
    });
    
    function assignMappingRole() {
    	
    	// log user activity; Save Role Screen Mapping
    	logUserActivity(5004, 'Save Role Screen Mapping');
    	
    	var role = $("#roleId").val();
    	var roleMappings = [];
    	$('#assignUserRoles :checked').each(function() {
    		if(''!=$(this).val() && $(this).val()=='recommAction'){
    			roleMappings.push("recommActionSam");	
    		}
    		if(''!=$(this).val() && $(this).val()=='home_cust_profiles'){
    			roleMappings.push("cp_online_retail");	
    		}
    		if(''!=$(this).val() && $(this).val()=='home_template2'){
    			roleMappings.push("home_template3");	
    		}
    		roleMappings.push($(this).val());
    		
    	});
    	if (roleMappings.length == 0){
    		alert ("Please select atleast one screen to be mapped with the Role");
    		return;
    	}
    	////showLayer();
        var urlNew = ctxNew + "/api/helios/maintainUserRole/assignMapping/" + role + "/" + roleMappings;
        $.ajax({
        	dataType: "json",
	        url : urlNew,
	        type : "GET",
	        cache : false,
	        timeout : 15000,
	        success : function(data, textStatus, jqXHR) {
	        	// log user activity; Role Screen Mapping Saved Successfully
	        	logUserActivity(5005, 'Role Screen Mapping Saved Successfully');
	        	alert ("Role to screen mapping done successfully");
	        	// hideLayer();
	        },
	        error : function(jqXHR, textStatus, errorThrown) {
	        	//alert("error" + errorThrown);
	        	// hideLayer();
	        }
        });
    }
    
    function getAssignedRoleMappings(role) {
    	//console.log("getAssignedRoleMappings: "+role);
    	////showLayer();
        var urlNew = ctxNew + "/api/helios/maintainUserRole/getAssignedRoleMappings/" + role;
        $.ajax({
        	dataType: "json",
	        url : urlNew,
	        type : "GET",
	        cache : false,
	        timeout : 15000,
	        contentType : "application/json",
	        success : function(data, textStatus, jqXHR) {
	        	//alert(data);
	        	if (data != undefined && data != null && data != "") {
	        		$('#assignUserRoles').find('input[type=checkbox]:checked').removeAttr('checked');
		        	$.each(data, function(key, item) {
		        		//alert(item)
		        		if ($('#'+item).length) {
		        			$('#'+item).prop("checked", true);
		        		} else {
		        			$('#'+item).prop("checked", false);
		        		}
		        	});
	        	} else {
	        		$('#assignUserRoles').find('input[type=checkbox]:checked').removeAttr('checked');
	        	}
	        	// hideLayer();
	        },
	        error : function(jqXHR, textStatus, errorThrown) {
	        	//alert("error" + errorThrown);
	        	$('#assignUserRoles').hide(); 
	        	// hideLayer();
	        }
        });
    } 
    /*$(window).unload(function() {
    	  
        clearUserForm();
      
        
    });*/
