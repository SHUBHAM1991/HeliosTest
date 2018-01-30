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
     background-color:#1d2939; 
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
     background-color:#1d2939; 
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
	background-color:#f5f5f5;
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
						 
						<a id="menuItemId0" href="#" style=" padding-right:0px;background-color: #fff;border: 1px solid #444;color:#444;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;">Dashboard</span></div>
						<div style="float:right"><i class="fa fa-tachometer" style="font-size: 20px;"></i></div>
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
						<!-- <div
						style="margin-left: -5px; width: 35px; background-color: #fff;">
						<img width="100%" height="35"
							src="./resources/img/store.png" style="">
					     </div> -->
			            </a>
						<a  href="javascript:openStoreLocator();" id="menuItemId6"  style=" padding-right:0px;background-color:#1d2939;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;">Stores Near By</span></div>
						<div style="float:right;"> <i class="fa fa-map-marker" style="font-size: 20px;"> </i>
							
							<!-- <img width="28" height="28"
								src="./resources/img/store.png" style=""> -->
						    
						</div>
						</div>
						<!-- <span class="selected"></span>-->
						</a>
					</li>
					 <li id="slideBar" style="display:none;border:none;padding-right: 6px; border-radius: 20px !important; ">
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
					</li> 
					<!--
					<li id="slideBar" style="border: none; padding-right: 6px; border-radius: 20px !important; padding-left: 10px;">
						 <a href="javascript:void();" id="menuItemIcon10" style="height: 35px; display: none; background-color: rgb(29, 41, 57);" data-placement="right" data-toggle="tooltip" data-container="body" data-original-title="" title="">
						<i style="color:#fff;font-size:220%" class="fa fa-chevron-circle-left"></i>
						</a> 
						<a href="javascript:void();" id="menuItemId10" style="padding-right: 0px; font-size: 14px; font-weight: bold; height: 35px; border-radius: 4px !important; display: block; color: rgb(255, 255, 255); border: none; background-color: rgb(29, 41, 57);">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;"></span></div>
						<div style="padding-top: 15px;z-index: 1000 !important;float:right;"><i class="icon-arrow-up menu-toggler sidebar-toggler" style="font-size: 23px;transform: rotate(-90deg);"></i></div>
						</div>
						<span class="selected"></span>
						</a>
					</li>
					
					  -->
					<!-- <li id="slideBar" style="border:none;padding-right: 6px;border-radius: 20px !important;">
						 <a href="javascript:void();" id="menuItemIcon10" style="display:none;height:35px;display:none;background-color: #1d2939;" data-placement="right" data-toggle="tooltip" data-container="body">
						<i style="color:#fff;font-size:220%" class="fa fa-chevron-circle-left"></i>
						</a> 
						<a href="javascript:void();" id="menuItemId10" style=" padding-right:0px;background-color:#1d2939;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;"></span></div>
						<div style="padding-top: 15px;z-index: 1000 !important;float:right;"><i class="icon-arrow-up menu-toggler sidebar-toggler" style=" font-size: 30px;transform: rotate(-90deg);"></i></div>
						</div>
						<span class="selected"></span>
						</a>
					</li> -->
			    </ul>
				<!-- END SIDEBAR MENU -->
				<!-- Notification Start -->
				
			
			
			<!-- IAM ID Changes START -->
			
			<!-- IAM ID Changes END -->
		</div>
		</div>
		<!-- END SIDEBAR -->
		</div>