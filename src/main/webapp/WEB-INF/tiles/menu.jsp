<head>
<style type="text/css">
.list-group-item{
  height:56px !important;
}
</style>
<style type="text/css">
#accordion .glyphicon {
    margin-right: 10px;
}
.panel-collapse > .list-group .list-group-item:first-child {
    border-top-right-radius: 0;
    border-top-left-radius: 0;
}
.panel-collapse > .list-group .list-group-item {
    border-width: 1px 0;
}
.panel-collapse > .list-group {
    margin-bottom: 0;
}
.panel-collapse .list-group-item {
    border-radius: 0;
}
.panel-collapse .list-group .list-group {
    margin: 0;
    margin-top: 10px;
}
.panel-collapse .list-group-item li.list-group-item {
    margin: 0 -15px;
    border-top: 1px solid #ddd;
    border-bottom: 0;
    padding-left: 30px;
}
.panel-collapse .list-group-item li.list-group-item:last-child {
    padding-bottom: 0;
}
.panel-collapse div.list-group div.list-group {
    margin: 0;
}
.panel-collapse div.list-group .list-group a.list-group-item {
    border-top: 1px solid #ddd;
    border-bottom: 0;
    padding-left: 30px;
}
.list-group-item{
 text-align:left !important;
 padding: 24px 12px !important;
 padding-top:4px !important;
 border:none !important;
 background-color: #1d2939 !Important;
 color:#ddd;
 
}
.panel-title a{
text-decoration:none;
font-weight:bold;
font-size:13px;
}
.badge{
 background-color:#ef3f3f !important;
}
ul#growUlId li:last{
border:none !important;
}
@media (min-width: 768px){
	#notifyPanelId{
	margin-left:1%;
	margin-right:1%;
	}
	div#heliosSideBar{
	/* position:inherit !important; */
	}
	div#menuContainer{
	  position:inherit !important;
	  width:100%;
	}
	li#slideBar{
	display:none;
	}
	#scrollDivId{
     width: 100% !important;
     background-color:transparent;
     /* background-color:#1d2939; */ 
    }
}
@media (min-width: 992px){
	#notifyPanelId{
	margin-left:2.5%;
	margin-right:2.5%;
	}
	div#heliosSideBar{
	/* position:fixed !important; */
	}
	li#slideBar{
	display:block;
	padding-left:105% !important;
	}
	#scrollDivId{
     width: 100% !important;
     /* background-color:#1d2939; */ 
      background-color:transparent;
    }
    div#menuContainer{
	  position:fixed !important;
	  width:18%;
	}
}
@media (min-width: 1200px){
	#notifyPanelId{
	margin-left:2.5%;
	margin-right:2.5%;
	}
	div#heliosSideBar{
	/* position:fixed !important; */
	}
	li#slideBar{
	display:block;
	padding-left:105% !important;
	}
	#scrollDivId{
	width:100%;
	background-color:transparent;
	/* background-color:#f5f5f5; */
     /* width: 140.5% !important; */
    }
    div#menuContainer{
	  position:fixed !important;
	  width:18%;
	}
}
.cnoExpand , .logExpand{
height:40px;
padding:10px 15px;
color: #fff;
background-color:#1d2c3c !important;
opacity:0.9;
}
.cnoCollapse , .logCollapse{
height:40px;
padding:10px 15px;
color: #fff;
background-color:#1d2c3c !important;
}


.msg_container_base{
  background: #e5e5e5;
  margin: 0;
  padding: 0 0px 0px;
  /* max-height:600px; */
  overflow-x:hidden;
}
.msg_container_base::-webkit-scrollbar-track
{
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
    background-color: #F5F5F5;
}

.msg_container_base::-webkit-scrollbar
{
    width: 12px;
    background-color: #F5F5F5;
}

.msg_container_base::-webkit-scrollbar-thumb
{
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
    background-color: #555;
}

</style>
</head>
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar-wrapper" id="menuContainer" style="">
			<div id="scrollDivId" class="msg_container_base" style="">
			<div id="heliosSideBar" class="page-sidebar navbar-collapse collapse msg_container_base" style="background-color:#1d2939;width:100%;margin:0px;">
				
				<ul id="ulMenu" class="page-sidebar-menu page-sidebar-menu-hover-submenu " data-keep-expanded="true" data-auto-scroll="true" data-slide-speed="200">
                   <li style="border:none;padding-left: 10px;padding-right: 8px;padding-top:10px; border-radius: 20px !important; ">
						<a  href="#" id="menuItemIcon0"  style="height:35px;display:none;background-color: #1d2939;color:#444;" data-placement="right" data-toggle="tooltip" data-container="body" title="Dashboard">
						<i  style="color:#fff;font-size:200%" class="fa fa-tachometer"></i>
						</a>
						 
						<a id="menuItemId0" href="javascript:openMainDashboard();" style=" padding-right:0px;background-color: #fff;border: 1px solid #444;color:#444;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;">Dashboard</span></div>
						<div style="float:right"><i class="fa fa-tachometer" style="font-size: 20px;"></i></div>
						</div>
						<!--<span class="selected"></span>-->
						</a>
					</li>
					<li style="border:none;padding-left: 10px;padding-right: 8px; border-radius: 20px !important; ">
						
						<a  href="javascript:openSuperUserDetails();" id="menuItemIcon1"  style="height:35px;display:none;background-color: #1d2939;;" data-placement="right" data-toggle="tooltip" data-container="body" title="Users">
						<i  style="color:#fff;font-size:200%" class="fa fa-user"></i>
						</a>
						<a  href="javascript:openSuperUserDetails();" id="menuItemId1"  style=" padding-right:0px;background-color: #1d2939;;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;">Users</span></div>
						<div style="float:right"><i class="fa fa-user" style="font-size: 20px;"></i></div>
						</div>
						<!--<span class="selected"></span>-->
						</a>
					</li>
					<li style="border:none;padding-left: 10px;padding-right: 8px; border-radius: 20px !important; ">
						<a  href="javascript:openOrderDetails('N');" id="menuItemIcon2" style="height:35px;display:none;background-color: #1d2939;" data-placement="right" data-toggle="tooltip" data-container="body" title="Orders">
						<i  style="color:#fff;font-size:200%" class="fa fa-file-text-o"></i>
						</a>
						<a  href="javascript:openOrderDetails('N');" id="menuItemId2"  style=" padding-right:0px;background-color: #1d2939;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;">Orders</span></div>
						<div style="float:right"><i class="fa fa-file-text-o" style="font-size: 19px;"></i></div>
						</div>
						<!--<span class="selected"></span>-->
						</a>
					</li>
					<!-- <li style="display:none;border:none;padding-left: 10px;padding-right: 8px; border-radius: 20px !important; ">
						
						<a  href="#" id="menuItemIcon3" style="height:35px;display:none;background-color: #1d2939;">
						<i  style="color:#fff;font-size:200%" class="fa fa-file-text-"></i>
						</a>
						<a  href="#" id="menuItemId3"  style=" padding-right:0px;background-color: #1d2939;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;">Customer Profile</span></div>
						<div style="float:right"><i class="fa fa-file-text-o" style="font-size: 20px;"></i></div>
						</div>
						<span class="selected"></span>
						</a>
					</li> -->
					<li style="border:none;padding-left: 10px;padding-right: 8px; border-radius: 20px !important; ">
						
						<a  href="javascript:openShipToDetails();" id="menuItemIcon4"  style="height:35px;display:none;background-color: #1d2939;color: #ddd;" data-placement="right" data-toggle="tooltip" data-container="body" title="Ship To">
						<i  style="color:#fff;font-size:200%;transform:scale(-1,1);" class="fa fa-truck"></i>
						</a>
						<a  href="javascript:openShipToDetails();" id="menuItemId4"  style=" padding-right:0px;background-color: #1d2939;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;">Ship To</span></div>
						<div style="float:right"><i class="fa fa-truck" style="font-size: 19px;transform:scale(-1,1);"></i></div>
						</div>
						<!--<span class="selected"></span>-->
						</a>
					</li>
					 <li style="border:none;padding-left: 10px;padding-right: 6px; border-radius: 20px !important; ">
						
						<a  href="javascript:openRecommendations();" id="menuItemIcon5"  style="height:35px;display:none;background-color: #1d2939;" data-placement="right" data-toggle="tooltip" data-container="body" title="Recommendations">
						<i  style="color:#fff;font-size:220%" class="fa fa-thumbs-o-up"></i>
						</a>
						<a  href="javascript:openRecommendations();" id="menuItemId5"  style=" padding-right:0px;background-color:#1d2939;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;">Recommendations</span></div>
						<div style="float:right"><i class="fa fa-thumbs-o-up" style="font-size: 20px;"></i></div>
						</div>
						<span class="selected"></span>
						</a>
					</li>
					<li style="border:none;padding-left: 10px;padding-right: 8px; border-radius: 20px !important; ">
						<a  href="javascript:openStoreLocator();" id="menuItemIcon6"  style="height:35px;display:none;background-color: #1d2939;" data-placement="right" data-toggle="tooltip" data-container="body" title="Stores Near By">
						 <i  style="color:#fff;font-size:220%" class="fa fa-map-marker"></i> 
						
			            </a>
						<a  href="javascript:openStoreLocator();" id="menuItemId6"  style=" padding-right:0px;background-color:#1d2939;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;">Stores Near By</span></div>
						<div style="float:right;"> <i class="fa fa-map-marker" style="font-size: 20px;"> </i>
						    
						</div>
						</div>
						<!-- <span class="selected"></span>-->
						</a>
					</li>
					 <!-- <li id="slideBar" style="display:none;border:none;padding-right: 6px; border-radius: 20px !important; ">
						 <a href="javascript:void();" id="menuItemIcn10"  style="display:none;height:35px;display:none;background-color: #1d2939;" data-placement="right" data-toggle="tooltip" data-container="body">
						<i  style="color: rgb(29, 41, 57);font-size: 35px;font-style: normal;font-weight: 600;" class="fa fa-chevron-circle-right"></i>
						</a> 
						<a  href="javascript:void();" id="menuItemd10"  style=" padding-right:0px;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;background-color:transparent;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;"></span></div>
						<div style="float:right;padding-top:10px;"><i class="fa fa-chevron-circle-left menu-toggler sidebar-toggler" style="font-size: 30px;"></i></div>
						</div>
						<span class="selected"></span>
						</a>
					</li>  -->
					
			    </ul>
				<!-- END SIDEBAR MENU -->
				<!-- Notification Start -->
				<ul id="ulPlaySec" class="page-sidebar-menu page-sidebar-menu-hover-submenu " data-keep-expanded="true" data-auto-scroll="true" data-slide-speed="200" style="padding-top:25px;">
				
								<div class="caption" style="display:none;color: #fff;font-weight:700;font-size:16px;padding-bottom:7px;padding-top:5px;" id="notiId">
									<span style="font-family: Helvetica;padding-left: 12px;color: #fff;font-weight: bold;font-size: 14px;">CALL TO ACTION</span>
									<!-- <a href="./resources/docs/Helios QRG - 2016 Call To Action Guide.pdf" target="_blank" style="text-decoration: none;"><div style="float:right;"> <i class="fa fa-question" style="padding-right: 15px;font-size: 17px;color:#fff;"> </i></div></a> -->
									<a
										href="./resources/docs/Helios 2017 Call To Action Guide.pdf"
										target="_blank"
										style="float: right; text-decoration: none; padding-right: 10px;"><i
										class="fa fa-question-circle" aria-hidden="true"
										style="padding-left: 5px; color: #fff; font-size: 122%; vertical-align: middle;"></i>
									</a>
						      </div>
						      
							<div class="caption" style="display: block;color: rgb(255, 255, 255);font-weight: 700;font-size: 16px;padding-bottom: 7px;padding-top:5px;padding-left: 15px;" id="actionStat">
									<span style="font-family: Helvetica;/* padding-left: 15px; */color: #fff;font-weight: bold;font-size: 12px;border-bottom: 1px;solid;border-style: dashed;">NO CALL ACTION FOUND</span>
								</div>
						
							<div class="portlet-body" id="notifyPanelId" style="padding: 0px;background-color: #1d2939;">
								 <div class="panel-group" id="allCtaSegments">
                <div class="panel panel-default" style="border:none;" id="growthPlayId">
                    <div class="panel-heading" style="height:40px;text-align:left;padding:10px 15px;color: #fff; background-color:#1d2c3c;opacity:.9;" >
                    <a id="growthId" style="text-decoration:none;" data-toggle="collapse" data-parent="#accordion" href="#growthIdBody" class="collapsed" aria-expanded="true">
                        <h4 class="panel-title">
                              <!-- <span id="growthSpanId" class="glyphicon glyphicon-plus" style="color:#ddd;font-family:Helvetica;font-size: 120%;font-weight: bold !important; margin-right: 4px;"></span> --> 
                              <span style="color: #fff;font-family:Helvetica;font-size:14px;letter-spacing: .5px;" id="gId">Growth</span>
                              <i style="color:#fff;font-size: 90%;font-weight: bold !important; margin-right: -8px;float:right;" id="gIconId" class="fa fa-chevron-down"></i>
                        </h4>
                        </a>
                    </div>
                    <div id="growthIdBody" style="background-color: #1d2939;padding-top: 0px;" class="panel-collapse collapse" aria-expanded="true">
                        <ul class="list-group" id="growUlId" style="margin-right: 28px; margin-left: 10px; padding-top: 0px;">
                           
                        </ul>
                    </div>
                </div>
				 <div class="panel panel-default" style="border:none;" id="retPlayId">
                    <div class="panel-heading" style="height:40px;text-align:left;padding:10px 15px;color: #fff; background-color:#1d2c3c;opacity:.9;" >
                    <a id="retentionId" style="text-decoration:none;" data-toggle="collapse" data-parent="#accordion" href="#retentionIdBody" class="collapsed" aria-expanded="false">
                        <h4 class="panel-title">
                              <span style="color: #fff;font-family:Helvetica;font-size:14px;letter-spacing: .5px;" id="rId">Retention</span>
                              <i style="color:#fff;font-size: 90%;font-weight: bold !important; margin-right: -8px;float:right;" id="rIconId" class="fa fa-chevron-down"></i>
                            
                        </h4>
                        </a>
                    </div>
                    <div id="retentionIdBody" style="padding-top:0px;background-color: #1d2939;" class="panel-collapse collapse" aria-expanded="false">
                        <ul class="list-group" id="retUlId" style="margin-right: 28px; margin-left: 10px; padding-top: 0px;">
                         
                        </ul>
                    </div>
                </div>
				<div class="panel panel-default" style="border:none;" id="expPlayId">
                    <div class="panel-heading" style="height:40px;text-align:left;padding:10px 15px;color: #fff; background-color:#1d2c3c;opacity:.9;" >
                    <a id="expansionId" style="text-decoration:none;" data-toggle="collapse" data-parent="#accordion" href="#expansionIdBody" class="collapsed" aria-expanded="false">
                        <h4 class="panel-title">
                              <span style="color: #fff;font-family:Helvetica;font-size:14px;letter-spacing: .5px;" id="eId">Expansion</span>
                              <i style="color:#fff;font-size: 90%;font-weight: bold !important; margin-right: -8px;float:right;" id="eIconId" class="fa fa-chevron-down"></i>
                            
                        </h4>
                        </a>
                    </div>
                    <div id="expansionIdBody" style="padding-top:0px;background-color: #1d2939;" class="panel-collapse collapse" aria-expanded="false">
                        <ul class="list-group" id="expUlId" style="margin-right: 28px; margin-left: 10px; padding-top: 0px;">
                        
                        </ul>
                    </div>
                </div>
            </div>
			</div>
						
			</ul>
			
			
			<!-- IAM ID Changes START -->
				<ul id="ulIamIdSec" class="page-sidebar-menu page-sidebar-menu-hover-submenu " data-keep-expanded="true" data-auto-scroll="true" data-slide-speed="200" style="padding-top:25px;display:none;">
				<div class="caption" style="display:none;color: #fff;font-weight:700;font-size:16px;padding-bottom:7px;padding-top:5px;" id="sfdcId">
									<span style="font-family: Helvetica;padding-left: 12px;color: #fff;font-weight: bold;font-size: 14px;">On SFDC</span>
									
		      </div>
							<div class="portlet-body" id="notifyPanelId" style="padding: 0px;background-color: #1d2939;">
								 <div class="panel-group" id="">
					<div class="panel panel-default" style="border:none;">
                    <div id="cnoHead" class="panel-heading cnoExpand" style="" >
                    <a  href="#" target="_blank" id="menuItemIcon7"  style="height:35px;display:none;background-color: #1d2939;color: #fff;" data-placement="right" data-toggle="tooltip" data-container="body" title="Create new opportunity">
						 <i  style="color:#fff;font-size:200%" class="fa fa-hand-o-up"></i> 
						
			            </a>
                    <a style="" id="menuItemId7">
                        <h4 class="panel-title" id="cnoId">
                             
                        </h4>
                        </a>
                    </div>
                    
                </div>
                <div class="panel panel-default" style="border:none;" >
                    <div id="logHead" class="panel-heading logExpand"  >
                    <a  href="#" target="_blank" id="menuItemIcon8"  style="height:35px;display:none;background-color: #1d2939;color: #fff;" data-placement="right" data-toggle="tooltip" data-container="body" title="Log A Task">
						 <i  style="color:#fff;font-size:200%" class="fa fa-file"></i> 
						
			            </a>
                    <a style="" id="menuItemId8">
                        <h4 class="panel-title" id="logId">
                             
                        </h4>
                        </a>
                    </div>
                    
                </div>
				 
            </div>
			</div>
			</ul>
			<!-- IAM ID Changes END -->
			
			
		<ul id="ulIdOther" class="page-sidebar-menu page-sidebar-menu-hover-submenu " data-keep-expanded="true" data-auto-scroll="true" data-slide-speed="200" style="padding-top:25px;display:none;">
			<div class="caption" style="color: #fff;font-weight:700;font-size:16px;padding-bottom:7px;padding-top:5px;" id="otherId">
				<span style="font-family: Helvetica;padding-left: 12px;color: #fff;font-weight: bold;font-size: 14px;">Other</span>
	        </div>
	        
	        <div class="portlet-body" id="notifyPanelId" style="padding: 0px;background-color: #1d2939;">
			 <div class="panel-group">
				<div class="panel panel-default" style="border:none;">
                   <!-- <div id="wirDivId" class="panel-heading cnoExpand" style="display:block;" > -->
                    <div id="wirDivId" class="panel-heading" style="text-align:left;padding:10px 15px;color: #fff; background-color:#1d2c3c;opacity:.9;" >
  			         <a id="wirAnchor" href="javascript:openPerfDashboard();" >
					   <span id="menuItemIdWIR" style="height:35px;background-color: #1d2939;color: #fff;font-family:Helvetica;font-size:14px;">Workplace Insights Review</span>
		             </a> 
		            </div>
               		
            	</div>
             </div>
            </div>
		  </ul> 
		  
			
		</div>
		</div>
		</div>