$(function() {
		$("body").append('<div class="modal fade" id="ajaxErrorMessagePopup" role="dialog">' +
		    '<div class="modal-dialog modal-md">' +
		      '<div class="modal-content">' +
		        '<div class="modal-header" style="color: #fff;background-color:#23527c;">' +
		          '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
		          '<h4 class="modal-title" id="ajaxErrorMessageHeader">&nbsp;</h4>' +
		        '</div>' +
		        '<div class="modal-body" id="ajaxErrorMessageText">' +
		        '</div>' +
		        '<div class="modal-footer">' +
		          '<button type="button" class="btn btn-default" id="ajaxErrorMessageOk">OKAY</button>' +
		        '</div>' +
		      '</div>' +
		    '</div>' +
		  '</div>');
		
		$("#ajaxErrorMessageOk").click(function () {
			$("#ajaxErrorMessagePopup").modal("hide");
		});
		
		
		$.extend({ showAjaxErrorMessage: function (header, message, onOkClick ) {
				try {
					hideProgress();
				} catch (e) {
					// ignore
				}
				
				$("#ajaxErrorMessagePopup").on('hidden.bs.modal', {value : onOkClick},  function (e) {
					if (e.data.value && typeof(e.data.value) == "function") {
						e.data.value();
					}
					
				});
				
				// set the error message header
				$("#ajaxErrorMessageHeader").html(header);
				
				// set the error message
				$("#ajaxErrorMessageText").html(message);
				
				// open error message dialog
				$("#ajaxErrorMessagePopup").modal("show");
			}
		});
		$( document ).ajaxError(function(event, jqXHR, ajaxSettings, thrownError ) {
			if (jqXHR != undefined && jqXHR != null) {
				try {
					try {
						hideProgress();
					} catch (e) {
						// ignore
					}
					
					var ldapSessionCookieVal = $.cookie('SMSESSION');
					
					if ((jqXHR.status != undefined && jqXHR.status == 0 &&
							jqXHR.statusText != undefined && jqXHR.statusText == 'error' &&
							jqXHR.responseText != undefined && jqXHR.responseText == "") ||
							(jqXHR.status != undefined && jqXHR.status == 200 &&
									jqXHR.statusText != undefined && jqXHR.statusText == 'OK' &&
									jqXHR.getResponseHeader('Content-Type').indexOf('text/html')!=-1 &&
									jqXHR.responseText != undefined && 
									jqXHR.responseText.indexOf('You will be forwarded to continue the authorization process') != -1) ||
									(ldapSessionCookieVal != undefined && ldapSessionCookieVal != null && ldapSessionCookieVal=="LOGGEDOFF")) {
						
						$.showAjaxErrorMessage('Session Expired', 'Your session has expired. Please login again to continue.', function () {
							window.location.href="/";
						} );
						return;
						
					}
					
					var jsonResponse = JSON.parse(jqXHR.responseText);
					$.showAjaxErrorMessage('Error Occurred', jsonResponse.message);
				} catch (e) {
					 $.showAjaxErrorMessage('Error Occurred', 'An error occured while servicing your request'); 
				}
			} else {
				 $.showAjaxErrorMessage('Error Occurred', 'An error occured while servicing your request'); 
			}
		});
});