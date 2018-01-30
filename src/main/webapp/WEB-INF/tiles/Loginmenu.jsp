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
}
.panel-title a{
text-decoration:none;
font-weight:bold;
font-size:13px;
}
.badge{
 background-color:#ef3f3f !important;
}
/* ul li a:hover {
	background-color:#0092db !important;
	color:#fff !important;
	opacity:0.9;
} */
</style>
</head>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
		<!-- BEGIN SIDEBAR -->
		 <% String role = SecurityContextHolder.getContext().getAuthentication()
            					.getAuthorities().toString(); %>
		<div class="page-sidebar-wrapper">
			
			<div class="page-sidebar navbar-collapse collapse" style="margin-top:2px;z-index:1;">
				
				<ul id="ulMenu" class="page-sidebar-menu page-sidebar-menu-hover-submenu " data-keep-expanded="true" data-auto-scroll="true" data-slide-speed="200">
					<%if (role.equalsIgnoreCase("[ADMIN]")) {%>
                   <li style="border:none;padding-left: 10px;padding-right: 8px;padding-top:10px; border-radius: 20px !important; ">
						 
						<a  href="./maintainUserRole" id="maintainUserRole" style="height:35px;display:none;background-color: #1d2939;color: #ddd;" data-placement="right" data-toggle="tooltip" title="Maintain User Role">
						<i  style="color:#ddd;font-size:200%" class="fa fa-list"></i>
						</a>
						<a  href="./maintainUserRole" id="maintainUserRole"  style=" padding-right:0px;background-color: #1d2939;color: #ddd;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;">Maintain User Role</span></div>
						<div style="float:right"><i class="fa fa-list" style="font-size: 20px;"></i></div>
						</div>
						<!--<span class="selected"></span>-->
						</a>
					</li>
					<% } %>
                    <% if (false/* role.equalsIgnoreCase("[PLAYOWNER]") || role.equalsIgnoreCase("[ADMIN]") */) {%>
					<li style="border:none;padding-left: 10px;padding-right: 8px; border-radius: 20px !important; ">
						
						<a  href="./createPlay" id="createPlay" style="height:35px;display:none;background-color: #1d2939;color: #ddd;" data-placement="right" data-toggle="tooltip" title="CreatePlay">
						<i  style="color:#ddd;font-size:200%" class="fa fa-list"></i>
						</a>
						<a  href="./createPlay" id="createPlay"  style=" padding-right:0px;background-color: #1d2939;color: #ddd;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;">Create Play</span></div>
						<div style="float:right"><i class="fa fa-list" style="font-size: 20px;"></i></div>
						</div>
						<!--<span class="selected"></span>-->
						</a>
					</li>
					
                         
                      
						<% } %>
					
			    </ul>
				<!-- END SIDEBAR MENU -->
				<!-- CTA Admin module Start -->
				 <ul id="ulMenuCta" class="page-sidebar-menu page-sidebar-menu-hover-submenu " data-keep-expanded="true" data-auto-scroll="true" data-slide-speed="200" style="display:block;">
					<%if (role.equalsIgnoreCase("[ADMIN]")) {%>
                   <li style="border:none;padding-left: 10px;padding-right: 8px;padding-top:10px; border-radius: 20px !important; ">
						<a  href="./maintainCta" id="maintainCta" style="height:35px;display:none;background-color: #1d2939;color: #ddd;" data-placement="right" data-toggle="tooltip" title="Maintain Cta">
						<i  style="color:#ddd;font-size:200%" class="fa fa-list"></i>
						</a>
						<a  href="./maintainCta" id="maintainCta"  style=" padding-right:0px;background-color: #1d2939;color: #ddd;font-size: 14px;font-weight: bold;height: 35px; border-radius: 4px !important;">
						<div>
						<div style="float:left"><span class="title" style="font-family:Helvetica;font-weight:bold;font-size:14px;">Maintain CTA</span></div>
						<div style="float:right"><i class="fa fa-list" style="font-size: 20px;"></i></div>
						</div>
						</a>
					</li>
					<% } %>
			    </ul> 
				<!-- CTA Admin module End -->
			</div>
		</div>
		<!-- END SIDEBAR -->
		