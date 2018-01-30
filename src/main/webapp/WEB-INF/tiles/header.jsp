
<!-- BEGIN HEADER INNER -->
	<div class="page-header-inner container">
		<!-- BEGIN LOGO -->
		<div class="page-logo" style="background-color:#fff;border-right:1px solid #ccc;">
			<!-- <a href="index.html" style="text-decoration:none;"> -->
                              <span id="stplId" style="display:none;text-align:center;font-weight:bold;color:#fff;font-size:24px;text-decoration:none;">STAPLES</span>
		<span id="stplLogoId" style="float:left;width:75%;font-weight:bold;color:#fff;font-size:24px;text-decoration:none;">
                              <img src="./resources/img/helios-logo.png" style="filter:contrast(500%);-webkit-filter: contrast(500%);height: 40px;margin-top: 11px;margin-left: 10px;transform: scale(1);">
		</span>
			<!-- </a> -->
			<!-- <div id="tglId" class="menu-toggler sidebar-toggler" style="float:right;width:12%">
				DOC: Remove the above "hide" to enable the sidebar toggler button on header
			</div> -->
		<div id="tglId" class="menu-toggler sidebar-toggler"
			style="float: right; width: 19%; padding-top: 0px;text-align:center;">
			<i class="fa fa-reorder fa-lg"
				style="font-size: 2em !important; padding-left: 8px; color: #cc0000;">
			</i>
		</div>
	</div>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN PAGE ACTIONS -->
		<!-- DOC: Remove "hide" class to enable the page header actions -->
		<div class="page-actions hide">
			<div class="btn-group">
				<button type="button" class="btn btn-circle red-pink dropdown-toggle" data-toggle="dropdown">
				<i class="icon-bar-chart"></i>&nbsp;<span class="hidden-sm hidden-xs">New&nbsp;</span>&nbsp;<i class="fa fa-angle-down"></i>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li>
						<a href="javascript:;">
						<i class="icon-user"></i> New User </a>
					</li>
					<li>
						<a href="javascript:;">
						<i class="icon-present"></i> New Event <span class="badge badge-success">4</span>
						</a>
					</li>
					<li>
						<a href="javascript:;">
						<i class="icon-basket"></i> New order </a>
					</li>
					<li class="divider">
					</li>
					<li>
						<a href="javascript:;">
						<i class="icon-flag"></i> Pending Orders <span class="badge badge-danger">4</span>
						</a>
					</li>
					<li>
						<a href="javascript:;">
						<i class="icon-users"></i> Pending Users <span class="badge badge-warning">12</span>
						</a>
					</li>
				</ul>
			</div>
			<div class="btn-group">
				<button type="button" class="btn btn-circle green-haze dropdown-toggle" data-toggle="dropdown">
				<i class="icon-bell"></i>&nbsp;<span class="hidden-sm hidden-xs">Post&nbsp;</span>&nbsp;<i class="fa fa-angle-down"></i>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li>
						<a href="javascript:;">
						<i class="icon-docs"></i> New Post </a>
					</li>
					<li>
						<a href="javascript:;">
						<i class="icon-tag"></i> New Comment </a>
					</li>
					<li>
						<a href="javascript:;">
						<i class="icon-share"></i> Share </a>
					</li>
					<li class="divider">
					</li>
					<li>
						<a href="javascript:;">
						<i class="icon-flag"></i> Comments <span class="badge badge-success">4</span>
						</a>
					</li>
					<li>
						<a href="javascript:;">
						<i class="icon-users"></i> Feedbacks <span class="badge badge-danger">2</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<!-- END PAGE ACTIONS -->
		<!-- BEGIN PAGE TOP -->
		<div class="page-top">
			<!-- BEGIN HEADER SEARCH BOX -->
			<!-- DOC: Apply "search-form-expanded" right after the "search-form" class to have half expanded search box -->
			<%-- <div id="frmId" class="search-form search-form-expanded">
				<div class="input-group" style="border:none;">
					<input type="text" class="form-control" placeholder="Enter Company Name OR Customer Number" id="query" name="query" onkeypress="javascript:return submitOnEnter(event,$('#query').val(),'<%=request.getParameter("LoggedInUserID") %>');" style="width: 300px; border-radius: 5px !important;height: 24px;border: 1px solid #ccc;color: #888;margin-top: 20px;">
					<span class="input-group-btn">
					<a href="javascript:searchCustomer($('#query').val(),'<%=request.getParameter("LoggedInUserID") %>');" class="btn submit" style="text-align:left;padding-left:4px;"><i class="icon-magnifier" style=" text-decoration: none; cursor: default !important;font-weight:700;"></i></a>
					</span>
				</div>
			</div> --%>
			<div id="frmId" class="search-form search-form-expanded" style="margin-left: 10px;width: 230px;">
			<div class="input-group" style="border: none;">
				<div style="float: left;">
					<label style="margin-top: 22px;">  Search<span style="padding-left:1px;">:</span> </label>
				</div>
				<div style="float: right;">
					<input type="text" class="form-control" placeholder="Enter Customer Number/Name" id="query" name="query" onkeypress="javascript:return submitOnEnter(event,$('#query').val(),'<%=request.getParameter("LoggedInUserID") %>');" style="margin-left: 0px;padding-left: 5px;font-size: 12px;width: 190px;border-radius: 5px !important;height: 24px;border: 1px solid #004c74;color: green !important;margin-top: 20px;">
				</div>
				<span class="input-group-btn"> <a href="javascript:searchCustomer($('#query').val(),'<%=request.getParameter("LoggedInUserID") %>');" class="btn submit" style="width:28px;text-align: left; padding-left: 4px;"><i class="icon-magnifier" style="text-decoration: none; cursor: default !important; font-weight: 700;"></i></a>
				</span>
			</div>
		   </div>
			<!-- END HEADER SEARCH BOX -->
			<!-- BEGIN TOP NAVIGATION MENU -->
			<div class="top-menu">
				<ul class="nav navbar-nav pull-right">
					<!-- BEGIN NOTIFICATION DROPDOWN -->
					<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
					<li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar" style="display:none;">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
						<i class="icon-bell"></i>
						<span class="badge badge-default">
						7 </span>
						</a>
						<ul class="dropdown-menu" style="display:none;">
							<li class="external">
								<h3><span class="bold">12 pending</span> notifications</h3>
								<a href="javascript:void()">view all</a>
							</li>
							<li>
								<ul class="dropdown-menu-list scroller" style="height: 250px;" data-handle-color="#637283">
									<li>
										<a href="javascript:;">
										<span class="time">just now</span>
										<span class="details">
										<span class="label label-sm label-icon label-success">
										<i class="fa fa-plus"></i>
										</span>
										New user registered. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">3 mins</span>
										<span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span>
										Server #12 overloaded. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">10 mins</span>
										<span class="details">
										<span class="label label-sm label-icon label-warning">
										<i class="fa fa-bell-o"></i>
										</span>
										Server #2 not responding. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">14 hrs</span>
										<span class="details">
										<span class="label label-sm label-icon label-info">
										<i class="fa fa-bullhorn"></i>
										</span>
										Application error. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">2 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span>
										Database overloaded 68%. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">3 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span>
										A user IP blocked. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">4 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-warning">
										<i class="fa fa-bell-o"></i>
										</span>
										Storage Server #4 not responding dfdfdfd. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">5 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-info">
										<i class="fa fa-bullhorn"></i>
										</span>
										System Error. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">9 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span>
										Storage server failed. </span>
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<!-- END NOTIFICATION DROPDOWN -->
					<!-- BEGIN INBOX DROPDOWN -->
					<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
					<li class="dropdown dropdown-extended dropdown-inbox" id="header_inbox_bar" style="display:none;">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
						<i class="icon-envelope-open"></i>
						<span class="badge badge-default">
						4 </span>
						</a>
						<ul class="dropdown-menu" style="display:none;">
							<li class="external">
								<h3>You have <span class="bold">7 New</span> Messages</h3>
								<a href="javascript:void()">view all</a>
							</li>
							<li>
								<ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
									<li>
										<a href="javascript:void()">
										<span class="photo">
										<img src="../../assets/admin/layout3/img/avatar2.jpg" class="img-circle" alt="">
										</span>
										<span class="subject">
										<span class="from">
										Lisa Wong </span>
										<span class="time">Just Now </span>
										</span>
										<span class="message">
										Vivamus sed auctor nibh congue nibh. auctor nibh auctor nibh... </span>
										</a>
									</li>
									<li>
										<a href="javascript:void()">
										<span class="photo">
										<img src="../../assets/admin/layout3/img/avatar3.jpg" class="img-circle" alt="">
										</span>
										<span class="subject">
										<span class="from">
										Richard Doe </span>
										<span class="time">16 mins </span>
										</span>
										<span class="message">
										Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span>
										</a>
									</li>
									<li>
										<a href="javascript:void()">
										<span class="photo">
										<img src="../../assets/admin/layout3/img/avatar1.jpg" class="img-circle" alt="">
										</span>
										<span class="subject">
										<span class="from">
										Bob Nilson </span>
										<span class="time">2 hrs </span>
										</span>
										<span class="message">
										Vivamus sed nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span>
										</a>
									</li>
									<li>
										<a href="javascript:void()">
										<span class="photo">
										<img src="../../assets/admin/layout3/img/avatar2.jpg" class="img-circle" alt="">
										</span>
										<span class="subject">
										<span class="from">
										Lisa Wong </span>
										<span class="time">40 mins </span>
										</span>
										<span class="message">
										Vivamus sed auctor 40% nibh congue nibh... </span>
										</a>
									</li>
									<li>
										<a href="javascript:void()">
										<span class="photo">
										<img src="../../assets/admin/layout3/img/avatar3.jpg" class="img-circle" alt="">
										</span>
										<span class="subject">
										<span class="from">
										Richard Doe </span>
										<span class="time">46 mins </span>
										</span>
										<span class="message">
										Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span>
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<!-- END INBOX DROPDOWN -->
					<!-- BEGIN TODO DROPDOWN -->
					<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
					<li class="dropdown dropdown-extended dropdown-tasks" id="header_task_bar" style="display:none;">
						<a href="javascript:void();" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
						<i class="icon-calendar"></i>
						<span class="badge badge-default">
						3 </span>
						</a>
						<ul class="dropdown-menu extended tasks" style="display:none;">
							<li class="external">
								<h3>You have <span class="bold">12 pending</span> tasks</h3>
								<a href="page_todo.html">view all</a>
							</li>
							<li>
								<ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">New release v1.2 </span>
										<span class="percent">30%</span>
										</span>
										<span class="progress">
										<span style="width: 40%;" class="progress-bar progress-bar-success" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">40% Complete</span></span>
										</span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">Application deployment</span>
										<span class="percent">65%</span>
										</span>
										<span class="progress">
										<span style="width: 65%;" class="progress-bar progress-bar-danger" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">65% Complete</span></span>
										</span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">Mobile app release</span>
										<span class="percent">98%</span>
										</span>
										<span class="progress">
										<span style="width: 98%;" class="progress-bar progress-bar-success" aria-valuenow="98" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">98% Complete</span></span>
										</span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">Database migration</span>
										<span class="percent">10%</span>
										</span>
										<span class="progress">
										<span style="width: 10%;" class="progress-bar progress-bar-warning" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">10% Complete</span></span>
										</span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">Web server upgrade</span>
										<span class="percent">58%</span>
										</span>
										<span class="progress">
										<span style="width: 58%;" class="progress-bar progress-bar-info" aria-valuenow="58" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">58% Complete</span></span>
										</span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">Mobile development</span>
										<span class="percent">85%</span>
										</span>
										<span class="progress">
										<span style="width: 85%;" class="progress-bar progress-bar-success" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">85% Complete</span></span>
										</span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">New UI release</span>
										<span class="percent">38%</span>
										</span>
										<span class="progress progress-striped">
										<span style="width: 38%;" class="progress-bar progress-bar-important" aria-valuenow="18" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">38% Complete</span></span>
										</span>
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<!-- END TODO DROPDOWN -->
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
					<!-- <li class="dropdown dropdown-user">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
						<img alt="" class="img-circle" src="../../assets/admin/layout2/img/avatar3_small.jpg"/>
						<span class="username username-hide-on-mobile">
						Nick </span>
						<i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu dropdown-menu-default">
							<li>
								<a href="extra_profile.html">
								<i class="icon-user"></i> My Profile </a>
							</li>
							<li>
								<a href="page_calendar.html">
								<i class="icon-calendar"></i> My Calendar </a>
							</li>
							<li>
								<a href="inbox.html">
								<i class="icon-envelope-open"></i> My Inbox <span class="badge badge-danger">
								3 </span>
								</a>
							</li>
							<li>
								<a href="page_todo.html">
								<i class="icon-rocket"></i> My Tasks <span class="badge badge-success">
								7 </span>
								</a>
							</li>
							<li class="divider">
							</li>
							<li>
								<a href="extra_lock.html">
								<i class="icon-lock"></i> Lock Screen </a>
							</li>
							<li>
								<a href="login.html">
								<i class="icon-key"></i> Log Out </a>
							</li>
						</ul>
					</li>-->
					<!-- END USER LOGIN DROPDOWN -->
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
					<li class="dropdown dropdown-extended" style="display:none">
	                    <span class="sr-only">Toggle Quick Sidebar</span>
	                    <i class="icon-logout"></i>
	                </li>
					<!-- END USER LOGIN DROPDOWN -->
				</ul>
			</div>
			<!-- END TOP NAVIGATION MENU -->
			
			<!-- Nav Start -->
			<div style="text-align:right;color:#1d2939; vertical-align: middle; float: right; padding-top: 20px; padding-right: 15px;">
				
				<button   type="button" style="display: inline;margin-left:5px;margin-right:1px;border-radius:10px !important ;background-color:#004c74;letter-spacing:.5px;margin-right:7px;" class="btn btn-primary btn-xs" id="printDashboardBtn" >Print</button>
				
				<a href="#" data-toggle="tooltip" title="Help / FAQ / Training" data-placement="bottom" id="trainingHelp" style="padding:0px">	
				<img src="./resources/img/download.jpg" width="32" height="24" onclick="javascript:showTrainingPopUp()" style="vertical-align: text-top;"></img>
					<!-- <img src="./resources/img/training.gif" width="16" height="16" onclick="javascript:showTrainingPopUp()" style="vertical-align: middle"></img>--> 
					<!-- <i class="fa fa-question-circle" aria-hidden="true" onclick="javascript:showTrainingPopUp()" style="color: #004c74;font-size: 170%;vertical-align: middle;"></i> -->
					</a>
				
				
<%-- <form id="form1" action="./home_cust_profiles" method="post">
    <input name="reqCustNum" id="reqCustNum" type="hidden" value="${requestScope['accId']}"/>
    <input name="filterBy" id="filterBy" type="hidden" value="${requestScope['filterBy']}"/>
    <input name="subPlayParams" id="subPlayParams" type="hidden" value="${requestScope['subPlayParams']}"/>
     <input name="LoggedInUserID" id="LoggedInUserID" type="hidden" value="<%=request.getParameter("LoggedInUserID")%>"/>
    
</form> --%>
			<a style="color: #004c74" data-toggle="tooltip"
				title="Back to To Do List / Home" data-placement="bottom" onclick="javascript:submitForm();"
				href="javascript:generateLogs(\'homeIcon');"><span
				class="letterSpace"
				style="padding-right: 5px !important; padding-left: 5px !Important; padding-top: 2px; padding-bottom: 2px;"><i
					class="fa fa-home" style="font-size: 195%; border-radius: 10px;vertical-align:middle"></i></span></a> 
					
					<!-- <a id="faqIcon" href="javascript:void();" data-toggle="tooltip" title="Frequently Asked Questions" data-placement="bottom"
				id="faqId" style="padding: 0px;"
				data-original-title="Frequently Asked Questions"> <img
				src="./resources/img/info_icon_green2.png"
				width="35" height="26" onclick="javascript:showFaqPopUp()"
				style="margin-top: 0px; transform: scale(, 0.5);"></a> --> 
				
				<span class="quick-sidebar-toggler">
	                    <span class="sr-only">Toggle Quick Sidebar</span>
	                    <i class="icon-logout" style="cursor: pointer; font-size: 19px; color: #ef3f3f;vertical-align:middle;"></i>
	                </span>
	               <!-- <div style="font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:8px;">You logged in as <span id="loggedInUserNameSpan"></span></div> -->
	               <div id="loggedInUserNameSpan" style="text-align: end;/* padding-right: 1% !important; */font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:4px;"></div>
		</div>
				
				<!-- Nav End -->
		</div>
		<!-- END PAGE TOP -->
	</div>
	<!-- END HEADER INNER -->
