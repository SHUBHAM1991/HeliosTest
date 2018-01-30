/**
 * 
 */
var ctx = $("#svsURL").val();

$(document).ready(function(){
	
	
});
function showProgress() {
	var content= '<div id="progressBarId" style="position: absolute; width: 100%;background-color: darkslategray;top: 0; z-index: 1000;height: 2015px; opa;o;opacity: 0.5; 0 !important;"><div id="example_processing" class="col-lg-2 col-md-3 col-sm-4" style="top: 40%;text-align: center;font-size: 20px;/* height: 48px; */color: #fff;position: relative;border: none !important;border-color: gainsboro;/* width: 14.3%; */padding: 15px;/* margin-left: 12%; */background: initial;">'
		    +'<div>'
			+'<div class="col-lg-6" style=" padding-right: 1px; float: left;">'
			+'<img width="50" height="50" src="./resources/img/loading1.gif" >'
			+'</div><div class="col-lg-6" style="color: inherit;padding: 10px;padding-left: 10px;/* height: 40px; */float:left;/* text-align: center; */"> Loading...</div></div>'
			+' </div>'
			+' </div>';
	$("body").append(content);
}

function hideProgress(){
	$("#progressBarId").remove();
}

function geDataRefreshTime(processName){
	var lastUpdatedDate ="";
	
	if(processName!=undefined && processName!=null && processName!=''){
		$.ajax({
			url:ctx+"/getLastRefreshTime/"+processName,
			type:"post",
			timeout:10000,
			async :false,
			success:  function(data, textStatus, jqXHR){
				lastUpdatedDate=data;
			}
			
		});
		
	}
	return lastUpdatedDate;
}

function getCurrentTime() {
    var str = "";
    var offset = "";
	
	$.ajax({
		url:ctx+"/getCurrentRefreshTime",
		type:"post",
		timeout:10000,
		async :false,
		success:  function(data, textStatus, jqXHR){
			offset = data.replace(":", ".");
		}
	});
    var currentTime = new Date();
    var utc = currentTime.getTime() + (currentTime.getTimezoneOffset() * 60000);
    currentTime = new Date(utc + (3600000*(parseInt(offset))));
    var hours = currentTime.getHours();
    var minutes = currentTime.getMinutes();
    var monthNameArr=["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"];
    var month= monthNameArr[currentTime.getMonth()];
    var year = ''+currentTime.getFullYear();
    var date = currentTime.getDate();
    if(date < 10){
    	date = "0" + date;
    }
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    if (hours < 10) {
    	hours = "0" + hours;
    }
    str +=date+"-"+month+"-"+year.substring(2)+" " +hours + ":" + minutes;
    return str;
}
function truncateTitle (title) {
	var length =70;
	if(($(window).width() > 768)){
		length = 85;
	}
    if (title.length > length) {
       title = title.substring(0, length)+'...';
    }
    return title;
}
function truncateDataWithLen (data,len) {
    if (undefined!=data && '' !=data && data.length > len) {
       data = data.substring(0, len)+'...';
    }
    return data;
}
function openUrlSfdc(url,from){ 
	if(undefined !=from && '' !=from  && 'users'==from)
		logUserActivity(2014, 'Clicked Add New Contact button to SFDC');
	else if(undefined !=from && '' !=from  && 'createopp'==from)
		logUserActivity(2015, 'Clicked Create new oppurtunity hyperlink to SFDC');
	else if(undefined !=from && '' !=from  && 'logtask'==from)
		logUserActivity(2016, 'Clicked Log A Task hyperlink to SFDC');
	else if(undefined !=from && '' !=from  && 'compname'==from)
		logUserActivity(2020, 'Clicked Company Name hyperlink From Lead Customer to SFDC');

	 var win = window.open(url, '_blank');
	  win.focus();
}
function generateLogs(from){ 
	if(undefined !=from && '' !=from  && 'users'==from)
		logUserActivity(2014, 'Clicked Add New Contact button to SFDC');
	else if(undefined !=from && '' !=from  && 'createopp'==from)
		logUserActivity(2015, 'Clicked Create new oppurtunity hyperlink to SFDC from left menubar');
	else if(undefined !=from && '' !=from  && 'createoppSAMNew'==from)
		logUserActivityDotCom(7015, 'Clicked Create new oppurtunity hyperlink to SFDC from left menubar');
	else if(undefined !=from && '' !=from  && 'logtask'==from)
		logUserActivity(2016, 'Clicked Log A Task hyperlink to SFDC');
	else if(undefined !=from && '' !=from  && 'logtaskSAMNew'==from)
		logUserActivityDotCom(7016, 'Clicked Log A Task hyperlink to SFDC');
	else if(undefined !=from && '' !=from  && 'compnameMM'==from)
		logUserActivity(2035, 'Clicked Company Name hyperlink From Mid-Market Customer to SFDC');
	else if(undefined !=from && '' !=from  && 'compnameSAMNew'==from)
		logUserActivityDotCom(7035, 'Clicked Company Name hyperlink From SAM.COM Customer to SFDC');
	else if(undefined !=from && '' !=from  && 'compname'==from)
		logUserActivity(2020, 'Clicked Company Name hyperlink From Lead Customer to SFDC');
	else if(undefined !=from && '' !=from  && 'createoppCta'==from){
		logUserActivity(2036, 'Clicked create new oppurtunity hyperlink to SFDC from call to action');
	}else if(undefined !=from && '' !=from  && 'userSecHk'==from){
		logUserActivityDotCom(7038, 'User has clicked on sku link from Staples.com Activity');
	}else if(undefined !=from && '' !=from  && 'skuUpsell'==from){
		logUserActivityDotCom(7040, 'User has clicked on sku link from UP-SELL');
	}else if(undefined !=from && '' !=from  && 'skuCrosssell'==from){
		logUserActivityDotCom(7041, 'User has clicked on sku link from CROSS-SELL');
	}else if(undefined !=from && '' !=from  && 'skuReorder'==from){
		logUserActivityDotCom(7042, 'User has clicked on sku link from REORDER');
	}else if(undefined !=from && '' !=from  && 'homeIcon'==from){
		logUserActivityDotCom(7049, 'User has clicked home button from dashboard');
	}else if(undefined !=from && '' !=from  && 'SAMcustFromTodolist'==from){
		logUserActivityDotCom(7050, 'User has clicked customer number from To Do List');
	}else if(undefined !=from && '' !=from  && 'usersDetailYlink'==from){
		logUserActivityDotCom(7048, 'viewed staples.com activity from users tab');
	}else if(undefined !=from && '' !=from  && 'usersSku'==from){
		logUserActivityDotCom(7051, 'User visited sku link on staples.com activity from users tab');
	}
}
function chkNegAmount(amount) { 
	if(undefined !=amount && '' !=amount){
    if (amount.indexOf("-")!=-1) {
       amount='$'+amount.split("-")[1];
       if(amount !='' && amount.indexOf("$")!=-1){ 
    	   amount = amount.replace(/[&\/\\#$'":*?<>{}]/g, '')
       }
       amount='-$'+amount;
    }
	}
    return amount;
}
function chkNegAmountWithoutDollor(amount) { 
	if(undefined !=amount && '' !=amount){
     if (amount.indexOf("-")!=-1) {
       amount='-$'+amount.split("-")[1];
     }else
    	 amount='$'+amount; 
	}
    return amount;
}
function chkNegAmountOrder(amount) { 
	if(undefined !=amount && '' !=amount){
    if (amount.indexOf("-")!=-1) {
       amount='$'+amount.split("-")[1];
       if(amount !='' && amount.indexOf("$")!=-1){ 
    	   amount = amount.replace(/[&\/\\#$'":*?<>{}]/g, '')
       }
       amount='-$'+amount;
    }
    else
    	amount='$'+amount;
	}
    return amount;
}
function chkDollar(amount){
	if(amount !='' && amount.indexOf("$")!=-1){ 
 	   amount = amount.replace(/[&\/\\#$'":*?<>{}]/g, '')
    }
	    return amount;
}
function parseDate(str) {
    var mdy = str.split('/')
    return new Date(mdy[2], mdy[0]-1,mdy[1]);
}

function daydiff(first, second) {
    return (second-first)/(1000*60*60*24)
}
function openOutlook(email) { 
	if(undefined != email && '' !=email){
		logUserActivity(2034, 'Open Outlook window of Contact from User Grid');
    window.location.href ="mailto:"+email;
	}
}
function openOutlookLead(email,from) { 
	if(undefined !=from && from=='ToDoList')
		logUserActivity(2021, 'Open Outlook window of Lead Contact from To Do List');
	else if(undefined !=from && from=='Dashboard')
		logUserActivity(2022, 'Open Outlook window of Lead Contact from Customer Dashboard');
	if(undefined != email && '' !=email)
    window.location.href ="mailto:"+email;
}
function openOutlookSAMNew(email,from) { 
	if(undefined !=from && from=='ToDoList')
		logUserActivityDotCom(7021, 'Open Outlook window of SAM.COM Contact from To Do List');
	else if(undefined !=from && from=='Dashboard')
		logUserActivityDotCom(7022, 'Open Outlook window of SAM.COM Contact from Customer Dashboard');
	if(undefined != email && '' !=email)
    window.location.href ="mailto:"+email;
}
function submitForm(){
		document.getElementById('customerFormOnlineRetail').submit();
}
function replaceChar(val){ 
	if(undefined !=val && ""!=val)
		val=val.replace("-","\'");
return val;
}

function capitalize(string) {
	if(undefined !=string && ""!=string && string.length>=2)
    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
}
function hideMMFeatures(){
	$("#ulPlaySec").css("display","none");
	$("#li8").css("visibility","visible");
	$("#menuItemId1").css("display","none");
	$("#menuItemId4").css("display","none");
	$("#menuItemIcon1").css("display","none");
	$("#menuItemIcon4").css("display","none");
	$("#ulIamIdSec").css("display","none");
	$("#actionStat").css("display","none");
}
function generateContactPerson(name,email){
	var finalString='';
	if(''!=name)
		finalString +='<span id="custFirstName"> '+name+'</span>';
	if(''!=name && ''!=email)
		finalString +=' | <a href="javascript:openOutlookLead(\''+email+'\',\'Dashboard\');" style="color:#0092db;text-decoration:none;"><span style="color:#0092db;" id="custEmailId">'+email+' </span></a>';
	else if(''!=email)
		finalString +=' | <a href="javascript:openOutlookLead(\''+email+'\',\'Dashboard\');" style="color:#0092db;text-decoration:none;"><span style="color:#0092db;" id="custEmailId">'+email+' </span></a>';
	return finalString;
}
function generateContactPersonSAMNew(name,email){
	var finalString='';
	if(''!=name)
		finalString +='<span id="custFirstName"> '+name+'</span>';
	if(''!=name && ''!=email)
		finalString +=' | <a href="javascript:openOutlookSAMNew(\''+email+'\',\'Dashboard\');" style="color:#0092db;text-decoration:none;"><span style="color:#0092db;" id="custEmailId">'+email+' </span></a>';
	else if(''!=email)
		finalString +=' | <a href="javascript:openOutlookSAMNew(\''+email+'\',\'Dashboard\');" style="color:#0092db;text-decoration:none;"><span style="color:#0092db;" id="custEmailId">'+email+' </span></a>';
	return finalString;
}
function generateSegmentDetails(custType){ 
	var desc='';
	var title='';
	if(undefined !=custType && ''!=custType)
		custType= custType.trim();
	if(undefined !=custType && ''!=custType && custType=='PP5'){
		$('#example').html("PP5 - Premier Platinum Benefits");
		title='PP5 - Premier Platinum Benefits (5 %)';
	desc='<ul style="padding:10px;">'
		+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'FREE SHIPPING on orders over $14.99. Excludes Oversize items.'
		+'</li>'
		+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
		+'5% back in rewards on everything in store and online, except postage, phone/gift cards and savings passes'
		+'</li>'
		+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
		+'20% off instantly on Print & Marketing Services'
		+'</li>'
		+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
		+'$2 back in rewards for each ink or toner cartridge you recycle (up to 20 per month). Minimum purchase requirements must be met'
		+'</li>'
		+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
		+'Exclusive Premier Bonus events throughout the year'
		+'</li>'
		+'<li id="SupernovaHead" style="list-style: none;border-radius:5px !important;padding: 15px 0px 15px 0px;font-size: 11px;co;color: red;">* Benefits may vary and might not be latest</li>';
	}else if(undefined !=custType && ''!=custType && custType=='PG5'){
		$('#example').html("PG5 - Premier Gold");
		title='PG5 - Premier Gold (5 %)';
		desc='<ul style="padding:10px;">'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
				+'FREE SHIPPING on orders over $14.99. Excludes Oversize items.'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'5% back in rewards on everything in store and online, except postage, phone/gift cards and savings passes'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'10% off instantly on Print & Marketing Services'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'$2 back in rewards for each ink or toner cartridge you recycle (up to 20 per month). Minimum purchase requirements must be met.'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'Exclusive Premier Bonus events throughout the year'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style: none;border-radius:5px !important;padding: 15px 0px 15px 0px;font-size: 11px;co;color: red;">* Benefits may vary and might not be latest</li>';
	}else if(undefined !=custType && ''!=custType && custType=='PPL'){
		$('#example').html("PPL - Premier Platinum Rewards");
		title='PPL - Premier Platinum Rewards';
		desc='<ul style="padding:10px;">'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
				+'FREE SHIPPING on orders over $14.99. Excludes Oversize items.'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'15% back in rewards on all case and ream paper'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'20% off instantly on Print & Marketing Services'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'Unbeatable Ink & Toner prices'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'$2 back in rewards for each ink or toner cartridge you recycle (up to 20 per month). Minimum purchase requirements must be met.'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'Exclusive Premier Bonus events throughout the year'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style: none;border-radius:5px !important;padding: 15px 0px 15px 0px;font-size: 11px;co;color: red;">* Benefits may vary and might not be latest</li>';
	}else if(undefined !=custType && ''!=custType && custType=='P15'){
		$('#example').html("P15 - Premier Platinum Rewards");
		title='P15 - Premier Platinum Rewards ( 15 % )';
		desc='<ul style="padding:10px;">'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
				+'FREE SHIPPING on orders over $14.99. Excludes Oversize items.'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'15% off in rewards on all case and ream paper'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'20% off instantly on Print & Marketing Services'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'Unbeatable Ink & Toner prices'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'$2 back in rewards for each ink or toner cartridge you recycle (up to 20 per month). Minimum purchase requirements must be met.'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'Exclusive Premier Bonus events throughout the year'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style: none;border-radius:5px !important;padding: 15px 0px 15px 0px;font-size: 11px;co;color: red;">* Benefits may vary and might not be latest</li>';
	}else if(undefined !=custType && ''!=custType && custType=='G10'){
		$('#example').html("G10 - Premier Gold Rewards");
		title='G10 - Premier Gold Rewards (10 %)';
		desc='<ul style="padding:10px;">'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
				+'FREE SHIPPING on orders over $14.99. Excludes Oversize items.'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'10% off instantly on all case and ream paper'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'10% off instantly on Print & Marketing Services'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'Unbeatable Ink & Toner prices'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'$2 back in rewards for each ink or toner cartridge you recycle (up to 20 per month). Minimum purchase requirements must be met.'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'Exclusive Premier Bonus events throughout the year'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style: none;border-radius:5px !important;padding: 15px 0px 15px 0px;font-size: 11px;co;color: red;">* Benefits may vary and might not be latest</li>';
	}else if(undefined !=custType && ''!=custType && custType=='PMR'){
		$('#example').html("PMR - Benefits for PLCC Members");
		title='PMR Benefits for PLCC Members';
		desc='<ul style="padding:10px;">'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
				+'FREE SHIPPING on orders over $14.99. Excludes Oversize items.'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'5% back in rewards on everything in store and online, except postage, phone/gift cards and savings passes'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'20% off instantly on Print & Marketing Services'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'$2 back in rewards for each ink or toner cartridge you recycle (up to 20 per month). Minimum purchase requirements must be met.'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'$50 of free Print & Marketing Services'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'Free PC Diagnostic and Easy Total Support'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'Exclusive Premier Bonus events throughout the year'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style: none;border-radius:5px !important;padding: 15px 0px 15px 0px;font-size: 11px;co;color: red;">* Benefits may vary and might not be latest</li>';
	}else if(undefined !=custType && ''!=custType && custType=='PR7'){
		$('#example').html("PR7 - Business Rewards Plus");
		title='Business Rewards Plus Paid Membership (membership must be renewed with customer every 365 days)';
		desc='<ul style="padding:10px;">'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
				+'Special pricing on business essentials.'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'PLUS extra discounts on the eligible items customers buys most'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'5% back in Rewards with the Staples More Account'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'$50 of free Print & Marketing Services '
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'$50 of free Print & Marketing Services'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'Free shipping on orders over $14.99'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'$2 back in Rewards per recycled ink cartridge (up to 20/month)'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'$20 of free EasyTech support PLUS one free diagnostic service ($69 value)'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style: none;border-radius:5px !important;padding: 15px 0px 15px 0px;font-size: 11px;co;color: red;">* Benefits may vary and might not be latest</li>';
	}else if(undefined !=custType && ''!=custType && custType=='PR6'){
		$('#example').html("PR6 - Business Rewards Plus");
		title='Business Rewards Plus Free Trial Member (90 days)';
		desc='<ul style="padding:10px;">'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
				+'Special pricing on business essentials '
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'PLUS extra discounts on the eligible items customers buys most '
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'5% back in Rewards with the Staples More Account'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'$50 of free Print & Marketing Services '
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'Free shipping on orders over $14.99'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'$2 back in Rewards per recycled ink cartridge (up to 20/month)'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style:initial;border-radius:5px !important;padding: 5px 0px 5px 5px; font-size: 12px; color: #666;font-family: Helvetica;">' 
			+'$20 of free EasyTech support PLUS one free diagnostic service ($69 value)'
			+'</li>'
			+'<li id="SupernovaHead" style="list-style: none;border-radius:5px !important;padding: 15px 0px 15px 0px;font-size: 11px;co;color: red;">* Benefits may vary and might not be latest</li>'
			
	}else{
		$('#example').html("NA");
	}
	
	$("#tempData").html(desc);
		    $('#example').popover({
				 html : true,
				 title:title,
				 placement: 'bottom',
				content : $("#tempData").html()
			 });
			        
			    
}
function opencustDetailsMM(cNum){
	 $("#customerForm").attr("action","./home_template2")
		$("#reqCustNum").val(cNum);
	  $('#customerForm').submit();
		
}
function opencustDetailsLead(cNum){
	$("#customerForm").attr("action","./home_template3")
	$("#reqCustNum").val(cNum);
  $('#customerForm').submit();	
}
function opencustDetailsSAM(cNum){
	$("#customerForm").attr("action","./home_template4")
	$("#reqCustNum").val(cNum);
  $('#customerForm').submit();	
}
function isNumeric(n) {
	  return !isNaN(parseFloat(n)) && isFinite(n);
	}
function openUrlWithEmail(url){
	
	window.open(url, '_blank');	
}
function checkModifyChanges(selDesp,oldDesp,comment,oldComment,subject,oldSubject){
	if(undefined!=selDesp && ''!=selDesp){
		if(selDesp != oldDesp && subject!=''){ //when user changes only desposition
			return true;
		}else if((undefined !=comment && undefined !=oldComment) && comment != oldComment && selDesp!='Not Started' && subject!=''){ //when user changes only comment with old desposition
			return true;
		}else if((undefined !=subject && undefined !=subject) && subject != oldSubject && subject !='' && selDesp!='Not Started'){ //when user changes only subject
			return true;
		}else if((undefined !=subject && undefined !=oldSubject) && (undefined !=comment && undefined !=oldComment) && comment != oldComment  && subject==''){
			$("#noChangeBodyId").html("Please select or enter a Subject.");
			return false;
		}else if((undefined !=comment && undefined !=oldComment) && comment != oldComment && selDesp=='Not Started'){
			$("#noChangeBodyId").html("Please select a Disposition.");
			return false;
		}else if( $("#oldOrderContactVal").html() != $("#orderContactVal").html() && selDesp!='Not Started' && subject!=''){
			return true;
		}else if( $("#oldOrderContactVal").html() != $("#orderContactVal").html() && selDesp=='Not Started'){
			$("#noChangeBodyId").html("Please select a Disposition.");
			return false;
		}else if( $("#oldOrderContactVal").html() != $("#orderContactVal").html() && subject==''){
			$("#noChangeBodyId").html("Please select or enter a Subject..");
			return false;
		}else if( subject==''){
			$("#noChangeBodyId").html("Please select or enter a Subject.");
			return false;
		}else if( selDesp=='Not Started'){
			$("#noChangeBodyId").html("Please select a Disposition.");
			return false;
		}
		$("#noChangeBodyId").html("No changes have been made");
		return false;
	}
	return false;
}
function getNum(val) {
	   if (undefined !=val && (isNaN(val) || ''==val)) {
	     return 0;
	   }
	   return val;
}
function compareDate(D1,D2){
	if(undefined!=D1 && '' !=D1){
		var minutes = 1000*60;
		var hours = minutes*60;
		var days = hours*24;
		var foo_date1 = Date.parse(D1.toUpperCase(), "dd/mm/yyyy");
		var foo_date2 = Date.parse(D2, "dd/mm/yyyy");
		var diff_date = Math.round((foo_date2 - foo_date1)/days);
		return getNum(diff_date);
	}else
		return 0;
}

function generateTalkingPoints(data){
	var lableArr = new Array();
	var headerCon='<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 bhoechie-tab-menu" style="padding-left: 15px;"><div class="list-group">';
	var bodyCon='<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 bhoechie-tab">';
	$.each(data.hdrLables, function(i, item) {
		//alert(item.headerId +" i="+i);
		if (i == 0) {
			headerCon += '<a id="callSeg'
					//+ (item.headerId)
				      +(i+1)
					+ '" href="#" class="list-group-item text-center active" style="width:100%;padding-bottom:0px;border-top:none !important;">'
					+ (item.headerName) + '</a>';
		} else {
			headerCon += '<a id="callSeg'
					//+ (item.headerId)
				      +(i+1)
					+ '" href="#" class="list-group-item text-center " style="width:100%;padding-bottom:0px;border-top:none !important;">'
					+ (item.headerName) + '</a>';
		}
		if (i == 0) {
			bodyCon += '<div class="bhoechie-tab-content active"> <div class="" style="">'
					+ '<div class="well profile" style="width: 100%;padding-left:15px;padding-right:0px;padding-top:0px;background-color:#fff;border:none;border-top-color:initial;padding-bottom:0px;margin-bottom:0px;">'
					+ '<div class="col-sm-12" style="padding:0px;">'
					+ '<div id="editIdHdr'
					//+ item.headerId
					+(i+1)
					+ 'StaticText" class="nopadding fa fa-edit fa-lg " style="cursor:pointer;position:static;padding: 0px 15px;transform: scale(1.7);text-align: right;line-height: 2em; float: right; color: #ffc107;"></div>'
					+ ' <div class="col-xs-12 col-sm-12 green " id="Hdr'
					//+ item.headerId
					+(i+1)
					+ 'StaticText" style="max-height: 315px;overflow-y: auto;"> </div></div></div></div></div>';
		} else {
			bodyCon += '<div class="bhoechie-tab-content"> <div class="" style="">'
					+ '<div class="well profile" style="width: 100%;padding-left:15px;padding-right:0px;padding-top:0px;background-color:#fff;border:none;border-top-color:initial;padding-bottom:0px;margin-bottom:0px;">'
					+ '<div class="col-sm-12" style="padding:0px;">'
					+ '<div id="editIdHdr'
					//+ item.headerId
					+(i+1)
					+ 'StaticText" class="nopadding fa fa-edit fa-lg " style="cursor:pointer;position:static;padding: 0px 15px;transform: scale(1.7);text-align: right;line-height: 2em; float: right; color: #ffc107;"></div>'
					+ ' <div class="col-xs-12 col-sm-12 green " id="Hdr'
					//+ item.headerId
					+(i+1)
					+ 'StaticText" style="max-height: 315px;overflow-y: auto;"> </div></div></div></div></div>';
		}
		
	});
	headerCon+='</div></div>';
	bodyCon+='</div>';
	var popupHeaderHtml=headerCon;//getCtaHeaderHtml();
	var popupContentHtml=bodyCon;//getCtaContentHtml();
	$(".bhoechie-tab-container").html();
	$(".bhoechie-tab-container").html(popupHeaderHtml+popupContentHtml);
	
	$("div.bhoechie-tab-menu>div.list-group>a").css("display", "none");
	$("div.bhoechie-tab-menu>div.list-group>a").removeClass("active");
	$("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
	if (undefined != data.hdrLables && '' != data.hdrLables) {
		$.each(data.hdrLables, function(i, item) {
			lableArr.push(item.headerId);
			//var lableInnerTextId = 'Hdr' + item.headerId + 'StaticText';
			var lableInnerTextId = 'Hdr' +(i+1)+ 'StaticText';
			var lableInnerText=unescape(data[lableInnerTextId]);
			$("#" + lableInnerTextId).html("");
			var res= $("#" + lableInnerTextId).html(lableInnerText).text();
			$("#" + lableInnerTextId).html(res);
		});
		var uniqueItems = lableArr.filter(function(itm, i, a) {
			return i == lableArr.indexOf(itm);
		});
		$.each(uniqueItems, function(i, item) {
			$("div.bhoechie-tab-menu>div.list-group>a").eq((i)).css(
					"display", "block");
			if (i == 0) {
				$("div.bhoechie-tab-menu>div.list-group>a").click(
						function(e) {
							e.preventDefault();
							$(this).siblings('a.active').removeClass("active");
							$(this).addClass("active");
							var index = $(this).index();
							$("div.bhoechie-tab>div.bhoechie-tab-content")
									.removeClass("active");
							$("div.bhoechie-tab>div.bhoechie-tab-content")
									.eq(index).addClass("active");
						});
				$("div.bhoechie-tab-menu>div.list-group>a").eq((i))
						.addClass("active").attr("style","width:100%;padding-bottom:0px;border-top:none !important;");
				$("div.bhoechie-tab>div.bhoechie-tab-content").eq((i))
						.addClass("active");
			}
		});
		if(undefined !=data.headerIdsShown && ''!=data.headerIdsShown){
			//alert(data.headerIdsShown);
			bindEventsOnEdit(data.headerIdsShown);
		}
		else
		    bindEventsOnEdit('');
	}
	
}

function getCtaHeaderHtml(){
	return '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 bhoechie-tab-menu" style="padding-left: 15px;">               <div class="list-group">                 <a id="callSeg1" href="#" class="list-group-item text-center active" style="width:100%;padding-bottom:0px;border-top:none !important;">                   RATIONALE FOR SELECTION                 </a>                 <a id="callSeg2" href="#" class="list-group-item text-center " style="width:100%;padding-bottom:0px;">                   EXPLORE &amp; EXPAND: CONVERSATION STARTERS                 </a>                 <a id="callSeg3" href="#" class="list-group-item text-center " style="width:100%;padding-bottom:0px;">                   EXPLORE FOR VALUE: OBJECTION HANDLING                 </a>                 <a id="callSeg4" href="#" class="list-group-item text-center " style="width:100%;padding-bottom:0px;">     			BUSINESS PROBING QUESTIONS: DRILL DOWN                 </a>                 <a id="callSeg5" href="#" class="list-group-item text-center " style="width:100%;padding-bottom:0px;">     		   PROPOSE AND CLOSE												                 </a>                 <a id="callSeg6" href="#" class="list-group-item text-center " style="width:100%;padding-bottom:0px;">         	   MARKETING RESOURCES												                 </a>                 <a id="callSeg7" href="#" class="list-group-item text-center " style="width:100%;padding-bottom:0px;">                MISCELLANEOUS </a> </div></div>';
}
function getCtaContentHtml(){
	return '<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 bhoechie-tab"> <!-- flight section --> <!-- RATIONALE DATA START --> <div class="bhoechie-tab-content active"> <div class="" style="">  <div class="well profile" style="width: 100%;padding-left:15px;padding-right:0px;padding-top:0px;background-color:#fff;border:none;border-top-color:initial;padding-bottom:0px;margin-bottom:0px;"> <div class="col-sm-12" style="padding:0px;"> <div id="editIdRat" class="nopadding fa fa-edit fa-lg " style="cursor:pointer;position:static;padding: 0px 15px;transform: scale(1.7);text-align: right;line-height: 2em; float: right; color: #ffc107;"></div> <div class="col-xs-12 col-sm-12 green " id="Hdr1StaticText" style="max-height: 315px;overflow-y: auto;"> </div> </div> <div class="col-xs-12 divider" style="padding: 15px; margin-top: 15px;display:none;"> <div class="notice notice-sm" style="font-size: 13px; letter-spacing: .6px; background-color: #fff; color: currentColor;opacity: 1; border-color: darkseagreen;"> <strong style="font-size: 13px; text-decoration: underline;">Note</strong><b>:</b> dynamic content staples </div> </div>            </div> </div> </div> <!-- RATIONALE DATA END --> <!-- EXPLORE & EXPAND START--> <div class="bhoechie-tab-content">   <div class="" style=""> <div class="well profile" style="width: 100%;padding-left:15px;padding-right:0px;padding-top:0px;background-color:#fff;border:none;border-top-color:initial;padding-bottom:0px;"> <div class="col-sm-12" style="padding:0px;"> <div id="editIdExp" class="nopadding fa fa-edit fa-lg " style="cursor:pointer;position:static;padding: 0px 15px;transform: scale(1.7);text-align: right;line-height: 2em; float: right; color: #ffc107;"> </div> <div class="col-xs-12 col-sm-12 green" id="Hdr2StaticText" style="max-height: 315px;overflow-y: auto;"> </div> </div> </div> </div> </div> <!-- EXPLORE & EXPAND END --> <!-- EXPLORE FOR VALUE:OH START --> <div class="bhoechie-tab-content"> <div class="" style="">  <div class="well profile" style="width: 100%;padding-left:15px;padding-right:0px;padding-top:0px;background-color:#fff;border:none;border-top-color:initial;padding-bottom:0px;">  <div class="col-sm-12" style="padding:0px;">  <div id="editIdExpObj" class="nopadding fa fa-edit fa-lg " style="cursor:pointer;position:static;padding: 0px 15px;transform: scale(1.7);text-align: right;line-height: 2em; float: right; color: #ffc107;">  </div> <div class="col-xs-12 col-sm-12 green" id="Hdr3StaticText" style="max-height: 315px;overflow-y: auto;"> </div></div></div></div> </div> <!-- EXPLORE FOR VALUE:OH END --> <!-- DRILL DOWN START --> <div class="bhoechie-tab-content"> <div class="" style=""> <div class="well profile" style="width: 100%;padding-left:15px;padding-right:0px;padding-top:0px;background-color:#fff;border:none;border-top-color:initial;padding-bottom:0px;"> <div class="col-sm-12" style="padding:0px;"> <div id="editIdDrill" class="nopadding fa fa-edit fa-lg " style="cursor:pointer;position:static;padding: 0px 15px;transform: scale(1.7);text-align: right;line-height: 2em; float: right; color: #ffc107;"> </div> <div class="col-xs-12 col-sm-12 green" id="Hdr4StaticText" style="max-height: 315px;overflow-y: auto;"> </div></div></div></div> </div> <!-- DRILL DOWN END --> <!-- PROPOSE AND CLOSE START --> <div class="bhoechie-tab-content"> <div class="" style=""> <div class="well profile" style="width: 100%;padding-left:15px;padding-right:0px;padding-top:0px;background-color:#fff;border:none;border-top-color:initial;padding-bottom:0px;"> <div class="col-sm-12" style="padding:0px;"> <div id="editIdProp" class="nopadding fa fa-edit fa-lg " style="cursor:pointer;position:static;padding: 0px 15px;transform: scale(1.7);text-align: right;line-height: 2em; float: right; color: #ffc107;"> </div> <div class="col-xs-12 col-sm-12 green" id="Hdr5StaticText" style="max-height: 315px;overflow-y: auto;"> </div></div></div></div> </div> <!-- PROPOSE AND CLOSE END -->  <!-- MARKETING RESOURCE START --> <div class="bhoechie-tab-content"> <div class="" style=""> <div class="well profile" style="width: 100%;padding-left:15px;padding-right:0px;padding-top:0px;background-color:#fff;border:none;border-top-color:initial;padding-bottom:0px;"> <div class="col-sm-12" style="padding:0px;"> <div id="editIdMark" class="nopadding fa fa-edit fa-lg " style="cursor:pointer;position:static;padding: 0px 15px;transform: scale(1.7);text-align: right;line-height: 2em; float: right; color: #ffc107;"> </div> <div class="col-xs-12 col-sm-12 green" id="Hdr6StaticText" style="max-height: 315px;overflow-y: auto;"></div> </div></div></div> </div> <!-- MARKETING RESOURCE END -->  <!-- MISCELLENEOUS START --> <div class="bhoechie-tab-content"> <div class="" style=""> <div class="well profile" style="width: 100%;padding-left:15px;padding-right:0px;padding-top:0px;background-color:#fff;border:none;border-top-color:initial;padding-bottom:0px;"> <div class="col-sm-12" style="padding:0px;"> <div id="editIdMisc" class="nopadding fa fa-edit fa-lg " style="cursor:pointer;position:static;padding: 0px 15px;transform: scale(1.7);text-align: right;line-height: 2em; float: right; color: #ffc107;"> </div> <div class="col-xs-12 col-sm-12 green" id="Hdr7StaticText" style="max-height: 315px;overflow-y: auto;"> </div> </div></div> </div> </div> <!-- MISCELLENEOUS END --> </div>';
}

function showEditPopUp(editId) {
	$('#'+editId).modal({
		"backdrop"  : "static",
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
	$("[id^='param']").prop("disabled",false);
	$("[id^='param']").keyup(function(e){
		var target = e.target,
	      position = target.selectionStart; // Capture initial position
		 $(this).attr("value",$(this).val());
			  target.value = target.value.replace(/\s/g, '');  // This triggers the cursor to move.
			  target.selectionEnd = position;
	 });
}

function bindEventsOnEdit(headerIds){
	var confirmationTitle='Confirmation Dialog';
	var confirmationText='Are you sure want to update the detail ?';
	$("#editIdHdr1StaticText").click(function(){
			var rationaleHtmlData=$("#Hdr1StaticText").html();
			$(".Editor-editor").html("");
			$("#infoIdRat").find(".Editor-editor").html(rationaleHtmlData);
			showEditPopUp('info-edit-Rat');
		 });
	$("#applyOnEditRat").click(function(){
		var ratDataAfterEdit=$("#txtEditorRat").next().find(".Editor-editor").html();
		$("#Hdr1StaticText").html("");
		$("#Hdr1StaticText").html(ratDataAfterEdit);
		showConfirmationPopUp(confirmationTitle,confirmationText,'info-edit-Rat','Hdr1StaticText',headerIds,'update');
		
		
	 });
	 $("#editIdHdr2StaticText").click(function(){
			var expHtmlData=$("#Hdr2StaticText").html();
			$(".Editor-editor").html("");
			$(".Editor-editor").html(expHtmlData);
			showEditPopUp('info-edit-Exp');
		 });
	 $("#applyOnEditExp").click(function(){
			var expDataAfterEdit=$("#txtEditorExp").next().find(".Editor-editor").html();
			$("#Hdr2StaticText").html("");
			$("#Hdr2StaticText").html(expDataAfterEdit);
			showConfirmationPopUp(confirmationTitle,confirmationText,'info-edit-Exp','Hdr2StaticText',headerIds,'update');
		 });
	 $("#editIdHdr3StaticText").click(function(){
			var expObjHtmlData=$("#Hdr3StaticText").html();
			$(".Editor-editor").html("");
			$(".Editor-editor").html(expObjHtmlData);
			showEditPopUp('info-edit-ExpObj');
		 });
	 $("#applyOnEditExpObj").click(function(){
			var expObjDataAfterEdit=$("#txtEditorExpObj").next().find(".Editor-editor").html();
			$("#Hdr3StaticText").html("");
			$("#Hdr3StaticText").html(expObjDataAfterEdit);
			showConfirmationPopUp(confirmationTitle,confirmationText,'info-edit-ExpObj','Hdr3StaticText',headerIds,'update');
		 });
	 $("#editIdHdr4StaticText").click(function(){
			var drillHtmlData=$("#Hdr4StaticText").html();
			$(".Editor-editor").html("");
			$(".Editor-editor").html(drillHtmlData);
			showEditPopUp('info-edit-Drill');
		 });
	 $("#applyOnEditDrill").click(function(){
			var drillDataAfterEdit=$("#txtEditorDrill").next().find(".Editor-editor").html();
			$("#Hdr4StaticText").html("");
			$("#Hdr4StaticText").html(drillDataAfterEdit);
			showConfirmationPopUp(confirmationTitle,confirmationText,'info-edit-Drill','Hdr4StaticText',headerIds,'update');
		 });
	 $("#editIdHdr5StaticText").click(function(){
			var propHtmlData=$("#Hdr5StaticText").html();
			$(".Editor-editor").html("");
			$(".Editor-editor").html(propHtmlData);
			showEditPopUp('info-edit-Prop');
		 });
	 $("#applyOnEditProp").click(function(){
			var propDataAfterEdit=$("#txtEditorProp").next().find(".Editor-editor").html();
			$("#Hdr5StaticText").html("");
			$("#Hdr5StaticText").html(propDataAfterEdit);
			showConfirmationPopUp(confirmationTitle,confirmationText,'info-edit-Prop','Hdr5StaticText',headerIds,'update');
		 });
	 $("#editIdHdr6StaticText").click(function(){
			var markHtmlData=$("#Hdr6StaticText").html();
			$(".Editor-editor").html("");
			$(".Editor-editor").html(markHtmlData);
			showEditPopUp('info-edit-Mark');
		 });
	 $("#applyOnEditMark").click(function(){
			var markDataAfterEdit=$("#txtEditorMark").next().find(".Editor-editor").html();
			$("#Hdr6StaticText").html("");
			$("#Hdr6StaticText").html(markDataAfterEdit);
			showConfirmationPopUp(confirmationTitle,confirmationText,'info-edit-Mark','Hdr6StaticText',headerIds,'update');
		 });
	 $("#editIdHdr7StaticText").click(function(){
			var miscHtmlData=$("#Hdr7StaticText").html();
			$(".Editor-editor").html("");
			$(".Editor-editor").html(miscHtmlData);
			showEditPopUp('info-edit-Misc');
		 });
	 $("#applyOnEditMisc").click(function(){
			var miscDataAfterEdit=$("#txtEditorMisc").next().find(".Editor-editor").html();
			$("#Hdr7StaticText").html("");
			$("#Hdr7StaticText").html(miscDataAfterEdit);
			showConfirmationPopUp(confirmationTitle,confirmationText,'info-edit-Misc','Hdr7StaticText',headerIds,'update');
		 });
	 $('#info-edit-Rat , #info-edit-Exp , #info-edit-ExpObj , #info-edit-Drill, #info-edit-Prop, #info-edit-Mark, #exp-info-Misc').on('hidden.bs.modal', function () {
		    $("[id^='param']").prop("disabled",true);
		});
}

function showConfirmationPopUp(dialogTitle,dialogtext,dialogToClose,headerToUpdate,headerIds,operation) {
	
	$('#commonConfDialog').modal({
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
	
	$("#dialogTitle").html();
	$("#dialogTitle").html(dialogTitle);
	$("#dialogText").html();
	$("#dialogText").html(dialogtext);
	$("#dialogYesId").unbind('click');
	$("#dialogYesId").click(function(){
		$('#commonConfDialog').modal('hide');
		if(undefined!=operation && 'update'==operation)
		updateHeaderContent(dialogToClose,headerToUpdate,headerIds);
		
	});
	$("#dialogNoId").unbind('click');
	$("#dialogNoId").click(function(){
		if(undefined!=operation && 'confirm'==operation)
			$('#'+dialogToClose).modal('hide');	
		$('#commonConfDialog').modal('hide');
	});
}

function showConfirmationPopUpOnClose(dialogTitle,dialogtext,dialogToClose,headerToUpdate,headerIds,operation) {
	
	$('#commonConfDialog').modal({
		/*"backdrop"  : "static",*/
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
	
	$("#dialogTitle").html();
	$("#dialogTitle").html(dialogTitle);
	$("#dialogText").html();
	$("#dialogText").html(dialogtext);
	$("#dialogYesId").unbind('click');
	$("#dialogYesId").click(function(){
		$('#commonConfDialog').modal('hide');
		if(undefined!=operation && 'save'==operation)
		updateHeaderContent(dialogToClose,headerToUpdate,headerIds);
		
	});
	$("#dialogNoId").unbind('click');
	$("#dialogNoId").click(function(){
		if(undefined!=operation && 'confirm'==operation)
			$('#'+dialogToClose).modal('hide');	
		$('#commonConfDialog').modal('hide');
	});
}
function getTextWithSpecialHtmlEntities(headerContent){
	if(undefined !=headerContent && ''!=headerContent && headerContent.indexOf("&nbsp;")!=-1){
		headerContent=headerContent.replace(/&nbsp;/g, ' ');
	}
	
	if(undefined !=headerContent && ''!=headerContent && headerContent.indexOf("'")!=-1){
		headerContent=headerContent.replace(/\'/g, "&#39;");
	} 
	if(undefined !=headerContent && ''!=headerContent && headerContent.indexOf("&quot;")!=-1){
		headerContent=headerContent.replace(/&quot;/g, "&#39;");
	} 
	if(undefined !=headerContent && ''!=headerContent && headerContent.indexOf("&amp;")!=-1){
		headerContent=headerContent.replace(/&amp;/g, "&#38;");
	}
	return headerContent;
}
function zeroPad(num, places) {
	  var zero = places - num.toString().length + 1;
	  return Array(+(zero > 0 && zero)).join("0") + num;
	}
function setAttributeAligned(){
	formatParamValues();
	var widthArrayKey=new Array();
	var widthArrayVal=new Array();
	var maxWidthKey=200;
	var maxWidthVal=200;
	$("input[id^='paramKey']:not(.innerClass)").each(function () {
		if (undefined != this.id && '' != this.id
				&& undefined != this.value && '' != this.value
				&& undefined != this.value.length){
			var width=((this.value.length + 1) * 6.95);
			widthArrayKey.push(width);
			
		}else{
			widthArrayKey.push('200');
		}
    });
	$("input[id^='paramVal']:not(.innerClass)").each(function () {
		if (undefined != this.id && '' != this.id
				&& undefined != this.value && '' != this.value
				&& undefined != this.value.length){
			var width=((this.value.length + 1) * 7.5);
			widthArrayVal.push(width);
			
		}else{
			widthArrayVal.push('100');
		}
    });
	if(undefined!=widthArrayKey && widthArrayKey.length>0){
	widthArrayKey=widthArrayKey.sort(function(a, b){return b-a});
	maxWidthKey=(undefined!=widthArrayKey[0]) ? (widthArrayKey[0]):'200';
	}
	if(undefined!=widthArrayVal && widthArrayVal.length>0){
		widthArrayVal=widthArrayVal.sort(function(a, b){return b-a});
		maxWidthVal=(undefined!=widthArrayVal[0]) ? (widthArrayVal[0]):'200';
	}

	$("input[id^='paramKey']:not(.innerClass)").each(function () {
			$("#" + (this.id)).attr("style","width:"+ maxWidthKey	+ 'px !important; '+
					" vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;-webkit-opacity:1;-webkit-text-fill-color:#4d4d4d !important;font-family:Helvetica;font-weight:bold; ");
    });
	$("input[id^='paramVal']:not(.innerClass)").each(function () {
		$("#" + (this.id)).attr("style","width:"+ maxWidthVal+ 'px !important; '+
				" vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;-webkit-opacity:1;-webkit-text-fill-color:#4d4d4d !important;font-family:Helvetica;font-weight:bold;");
   });
	
	//formatParamValues();
}
function setInnerDynamicAttrWidth(){

	$("input[id^='paramKey'][class='innerClass']").each(function () {
		
	var keyWidth=((this.value.length + 1) * 8) + 4;
		$("#" + (this.id)).attr("style","width:"+ keyWidth	+ 'px !important; '+
				" vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:#4d4d4d;font-family:Helvetica;font-weight:bold;");
});
$("input[id^='paramVal'][class='innerClass']").each(function () {
	var valWidth=((this.value.length + 1) * 7.5);
	$("#" + (this.id)).attr("style","width:"+ valWidth+ 'px !important; '+
			" vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:#4d4d4d;font-family:Helvetica;font-weight:bold;");
});

}
function limitText(limitField, limitCount, limitNum) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	} else {
		limitCount.value = limitNum - limitField.value.length;
       $("#charLimit").html(limitNum - limitField.value.length);        
	}
}


function setCharLimit(obj){
	if(undefined!=obj){
		var len=$(obj).val().length;
		if(len==0)
			$("#charLimit").html('1000');
		else
			$("#charLimit").html(1000-len);
	}
}
function capitalizeAllWords(string){
	if(undefined !=string && ""!=string && string.length>=2)
	    return string.toLowerCase().replace( /\b./g, function(a){ return a.toUpperCase(); } );
};

function formatAmount (n) {
	if(undefined !=n && n != ''){
  		var commaFormatedVal= n.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
  	    return commaFormatedVal;
  	} else {
  		return n;
  	}	
}

function showOkModal(dialogTitle,dialogtext) {
	
	$('#commonConfDialogOk').modal({
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
	
	$("#okDialogTitle").html();
	$("#okDialogTitle").html(dialogTitle);
	$("#okDialogText").html();
	$("#okDialogText").html(dialogtext);

}

function getCurrentDateTime() {
    var month = ['JAN','FEB','MAR','APR','MAY','JUN','JUL','AUG','SEP','OCT','NOV','DEC'];
    var d = new Date();
    var day=zeroPad(d.getDate(),'2');
    var mon = month[d.getMonth()];
    var year = d.getFullYear();
    var hours = (d.getHours()<10?'0':'') + d.getHours();
    var mins = (d.getMinutes()<10?'0':'') + d.getMinutes()
    //pull the last two digits of the year
    year = year.toString().substr(2,2);
    var currDate=day+'-'+mon+'-'+year+' '+hours+':'+mins;
    return currDate;
}

function findAndReplace(val){ 
	if(undefined!=val && ''!=val && val.indexOf('�')!=-1){ 
	  	val=val.replace(/�/g , "");
	}
	return val;
	
}
function replaceWithNA(val){
	if(undefined!=val && ''!=val && 'null'!=val & null!=val)
		return val;
	else
		return 'NA';
}
function configCookie(from){
	 $.each( localStorage, function( key,value ){
	     // define which element to delete
		     if(undefined!=key && ''!=key && key.indexOf('dataTables-CustInfoSamNew')!=-1 && undefined != value && ''!=value && (value.indexOf('start')!=-1) && undefined != (value.split(',')[1]) && undefined!=from &&  from=='dashboard'){
		    	 var k=(value.split(',')[1]).split(':')[0];
	             if(undefined!=k && ''!=k){
		    	   k=k.replace(/"/gi,"");
		    	   if('start'==k){
		    	   quickSearchStart=(value.split(',')[1]).split(':')[1];
		    	   return false; 
		    	   }
	             }
		     }else if(undefined!=key && ''!=key && key.indexOf('dataTables-QuickSearch')!=-1 && undefined != value && ''!=value && (value.indexOf('start')!=-1) && undefined != (value.split(',')[1]) && undefined !=from &&  from=='other'){
		    	 var k=(value.split(',')[1]).split(':')[0];
	             if(undefined!=k && ''!=k){
		    	   k=k.replace(/"/gi,"");
		    	   if('start'==k){
		    	   quickSearchStart=(value.split(',')[1]).split(':')[1];
		    	
		    	   return false; 
		    	   }
	             }
		     }
	 });
}
function commonScroll(id){
	if($(window).width()<=768){
		$('html, body').animate({
	        scrollTop: $("#"+id).offset().top -400
	    }, 200);
	}else if(($(window).width() > 768)){
		$('html, body').animate({
	        scrollTop: $("#"+id).offset().top -100
	    }, 200);
	}
}
function formatDecimal(num,dollarReq){
	
	if(undefined!=num && ''!=num && null!=num){
		if(dollarReq==true)
		num='$'+parseFloat(Math.round(num * 100) / 100).toFixed(2)	;
		else if(dollarReq==false)
		num=parseFloat(Math.round(num * 100) / 100).toFixed(2)	;
	}
	return num;
	
}
function changeDateToMMDDYY(dateVal){
	if (undefined != dateVal && '' != dateVal) {
		var mdy = dateVal.split('-')
		return (mdy[1] + '/' + mdy[2] + '/' + mdy[0]);
	} else
		return dateVal;
}
function customCapitalizeCta(string){
	if(undefined !=string && ""!=string && string.length>2)
	    return string.toLowerCase().replace( /\b./g, function(a){ return a.toUpperCase(); } );
	else
		return string;
};

function setAttributeAlignedOthers(){
	$("input[id^='paramKey']").each(function () {
   		if (undefined != this.id && '' != this.id
				&& undefined != this.value && '' != this.value
				&& undefined != this.value.length && this.value.length <= 19 )
			$("#" + (this.id)).attr("style","width:"+ ((this.value.length) * 1.1)	+ '% !important; '+
					"  vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;");	
   		else if (undefined != this.id && '' != this.id
				&& undefined != this.value && '' != this.value
				&& undefined != this.value.length && this.value.length > 19 && this.value.length <= 20)
			$("#" + (this.id)).attr("style","width:"+ ((this.value.length) * 1.1)	+ '% !important; '+
					"  vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;");
		else if (undefined != this.id && '' != this.id
				&& undefined != this.value && '' != this.value
				&& undefined != this.value.length && this.value.length > 20)
			$("#" + (this.id)).attr("style","width:"+ ((this.value.length) *1.2)	+ '% !important; '+
					"  vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;");
		
		else if (undefined != this.id && '' != this.id && undefined != this.value && '' != this.value)
			$("#" + (this.id)).attr("style"," width:auto !important; vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;");
		else
			$("#" + (this.id)).attr("style",'display:none');
   		if(undefined != this.value && '' != this.value && ('SKU_NAME'==this.value)){
			$("#" + (this.id)).attr("style",'display:none');
		}
   });
   	$("input[id^='paramVal']").each(function () {
   		 if (undefined != this.id && '' != this.id
				&& undefined != this.value && '' != this.value && 'NA' != this.value
				&& undefined != this.value.length )
   					$("#" + (this.id)).attr("style","width:"+((this.value.length) *2)+"% !important;"+
					"  vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;position:absolute;");
   		 else if (undefined != this.id && '' != this.id
				&& undefined != this.value && '' != this.value && 'NA' == this.value
				&& undefined != this.value.length )
   			$("#" + (this.id)).attr("style","width:"+((this.value.length) *3)+"% !important;"+
			"  vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;");
					
   });
}
function remove(array, element) {
	if(undefined!=array && ''!=array){
     const index = array.indexOf(element);
     array.splice(index, 1);
	}
    return array;
}

function configshipToLocations(location){
	 
	 var shiptoLocData ='<div class="modal-header" style="padding: 10px 7px;"><h6 class="modal-title modal-tit-pop" style=" text-transform: none;font-size: 16px;">Select Status</h6></div>'
		 +'<ul class="" style="margin:0px">';
	 
	 shiptoLocData +='<li class="modal-li-pop" style="width: 200px;"><div class="checkbox"> <label style="line-height: 1.6;font-size: 1.2em;">  <input type="checkbox" value="" id="slf_ACT"> <span class="cr" style=""><i class="cr-icon fa fa-check"></i></span><span>Active</span></label> </div></li>'
	 +'<li class="modal-li-pop" style="width: 200px;"><div class="checkbox"> <label style="line-height: 1.6;font-size: 1.2em;">  <input type="checkbox" value="" id="slf_INA"> <span class="cr" style=""><i class="cr-icon fa fa-check"></i></span><span>Inactive</span></label> </div></li>'
	 +'<li class="modal-li-pop" style="width: 200px;"><div class="checkbox"> <label style="line-height: 1.6;font-size: 1.2em;">  <input type="checkbox" value="" id="slf_GLO"> <span class="cr" style=""><i class="cr-icon fa fa-check"></i></span><span>Globally Inactive</span></label> </div></li>'
	 +'<li class="modal-li-pop" style="width: 200px;"><div class="checkbox"> <label style="line-height: 1.6;font-size: 1.2em;">  <input type="checkbox" value="" id="slf_ALL"> <span class="cr" style=""><i class="cr-icon fa fa-check"></i></span><span>All</span></label> </div></li>'
		 +'</ul>'
			+'<div class="modal-footer col-md-12 modal-foot-pop" style="text-align: right;background-color:#004c74;padding: 5px;"><button type="button" id="okTimezone" class="btn btn-sm modal-cls-pop" style="width: 35%;border-radius: 0px;border: 1px solid #fff;color:#004c74;outline:0;padding: 4px;font-size:12px;background-color:#fff;font-weight:bold;">OK</button>'
			+'<button type="button" id="cancelTimezone" class="btn btn-sm modal-cls-pop" style="width: 35%;border-radius: 1px;border: 1px solid #fff;color:#004c74;outline:0;padding: 4px;font-size:12px;background-color:#fff;font-weight:bold;">CANCEL</button></div>';
	$('div#shipToLocFilterContent').html(shiptoLocData);
	var totalTzCount=$('[id^=slf_]').not('[id=slf_ALL]').length;
	$('#shipToLocFilter').popover({
		html : true,
		placement : 'bottom',
		content : $('div#shipToLocFilterContent').html()
	}).on('shown.bs.popover', function () {
		if(undefined!=tempslfIds && tempslfIds.length<=0){
			$('input[id^=slf_]').prop('checked',true);
			$('input[id^=slf_]').next('span.cr').css('background-color','#004c74');$('input[id^=slf_]').next('span.cr').find('i').css('color','#fff');
		}
		else if(''!=tempslfIds && tempslfIds.length >0 ){
			$.each(tempslfIds,function(i,item){
				$('input[id='+item+']').prop('checked',true);
				$('input[id='+item+']').next('span.cr').css('background-color','#004c74');$('input[id='+item+']').next('span.cr').find('i').css('color','#fff');
			});
		}
		$('input[id^=slf_]').click(function(){ 
			var chkboxId=$(this).attr('id')
			if(undefined !=chkboxId && ''!=chkboxId && chkboxId!='slf_ALL' && $(this).prop('checked')==false)
			{ 	$('input[id^=slf_ALL]').prop('checked',false);$('input[id^=slf_ALL]').next('span.cr').css('background-color','#d6d6d6');$(this).next('span.cr').css('background-color','#d6d6d6');}
			if(undefined !=chkboxId && ''!=chkboxId && chkboxId=='slf_ALL' && $(this).prop('checked')==true)
			{ 	$('input[id^=slf_]').prop('checked',true); $('input[id^=slf_]').next('span.cr').css('background-color','#012c43');$('input[id^=slf_]').next('span.cr').find('i').css('color','#fff');}
			else if(undefined !=chkboxId && ''!=chkboxId && chkboxId=='slf_ALL' && $(this).prop('checked')==false)
			{ $('input[id^=slf_]').prop('checked',false);  }
			var totalCheckedCount=$('#shipToLocFilter').next('.popover').find('input[id^=slf_][type="checkbox"]:checked').length;
			if(undefined!=totalCheckedCount && ''!=totalCheckedCount && undefined!=totalTzCount && ''!=totalTzCount && (totalTzCount == totalCheckedCount)){
			 $('input[id^=slf_]').prop('checked',true);	$('input[id^=slf_]').next('span.cr').css('background-color','#012c43');$('input[id^=slf_]').next('span.cr').find('i').css('color','#fff');
			}
			if(undefined !=chkboxId && ''!=chkboxId && chkboxId!='slf_ALL' && $(this).prop('checked')==true){
				$(this).next('span.cr').css('background-color','#012c43');$(this).next('span.cr').find('i').css('color','#fff');
			}
			if(undefined!=totalCheckedCount && '0'== totalCheckedCount && chkboxId!='slf_ALL'){
			 alert('please select at least one shipTo location !!');
			 $(this).prop('checked',true);
			 $(this).next('span.cr').css('background-color','#012c43');
			 $(this).next('span.cr').find('i').css('color','#fff');
			 return;
			}else if(undefined!=totalCheckedCount && '0'== totalCheckedCount && chkboxId=='slf_ALL' && $(this).prop('checked')==false){
				alert('please select at least one shipTo location !!');
				$('input[id^=slf_]').prop('checked',true);
				 return;
			}
		});
		var $popup = $(this);
		$(this).next('.popover').find('button#okTimezone').unbind('click');
		$(this).next('.popover').find('button#okTimezone').click(function (e) {
			var slfList='',slNames='';
			var tempslf=new Array();
			tempslfIds=[];
			var selectedTimeZone=$('#shipToLocFilter').next('.popover').find('input[id^=slf_][type="checkbox"]:checked');
			$.each(selectedTimeZone,function(i,item){
				tempslf[i]=(item.id).replace('slf_','');
				tempslfIds[i]=(item.id);
				slNames=slNames+''+$('#'+item.id).next().next().html()+',';
			});
			if(undefined !=tempslf && ''!=tempslf && (tempslf.indexOf("All")!=-1))
				slfList='ALL';
			else if(undefined !=tempslf && ''!=tempslf && (tempslf.indexOf("All")==-1))
				slfList=tempslf.join()
	        $popup.popover('hide');
			if(undefined!=slNames && (''==slNames || slNames.indexOf('All')!=-1))
			slNames='ALL';
			else
			slNames=(undefined!=slNames && ''!=slNames)?slNames.substring(0,slNames.lastIndexOf(",")):'';
			$('#shipToSelLocations').html(slNames)
	        $("#shipToLocations").html(slfList);
			openShipToDetails();
			var custNum=$("#reqCustNum").val();
			if(undefined!=custNum && ''!=custNum)
			logChangeSatusActivity(7011, 'User has Clicked OK on Filter By Ship To Status for '+slNames,custNum);	
			//alert(slNames+"--"+slfList);
			
	    });
		$(this).next('.popover').find('button#cancelTimezone').click(function (e) {
	        $popup.popover('hide');
	    });
		$('#shipToLocFilter').parent().parent().children('div.tooltip').hide();
	}).on("mouseleave", function () {
      
	});
}

function convertSymbols(symbol) {
	if (undefined != symbol && '' != symbol && '-' == symbol)
		symbol = 'NA';
	return symbol;
}