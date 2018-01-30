<link href="./resources/stylesheet/stylesV2.css?helios_version=${initParam.helios_ver}" rel="stylesheet"
	type="text/css">
<link href="./resources/stylesheet/stylesV3.css?helios_version=${initParam.helios_ver}" rel="stylesheet"
	type="text/css">

<div class="modal fade" id="cache_modal" role="dialog">
	<div class="modal-dialog modal-lg"
		style="margin-top:5px;height:auto;width: 645px;">
		<div class="modal-content" id="cache-content" style="border-radius:0px !important;">
			<div class="modal-header modal-head modal-head-pop" style="padding:10px 15px;">
				<!-- <button type="button" class="modal-cross" data-dismiss="modal">&times;</button> -->
				<h4 class="modal-title modal-tit-pop" >
					Having trouble accessing Helios ?<span id="uName"></span>
				</h4>
			</div>
			<div style="padding: 1px;border-left: solid 2px #012c43;border-right: solid 2px #012c43;"><hr style="margin: 0;"></div>
			<div class="modal-body col-md-12 modal-bod modal-bod-pop" style="padding-bottom: 0px;">
				<button type="button"
					style="display: none; float: right; margin-right: 1px; border-radius: 10px !important;"
					class="btn btn-primary btn-xs" id="printUserInfoBtn">Print</button>

				<div class="col-md-12">
					<!-- BEGIN WIDGET TAB -->
					<div class="widget-bg-color-white widget-tab rounded-3">
						<div class="">
							<div class="">
								<strong class="modal-note modal-note-pop">How to access Helios</strong>
								<ol>
									<li class="modal-supp-text modal-supp-text-pop" id="li1" style="padding:5px;list-style-type:decimal !important;">
										<b>Download Chrome:</b> Helios works best in Chrome; if you do
										not already have Chrome, you should download and install
										chrome:
										<ul class="">
											<li class="modal-supp-text modal-supp-text-pop" id="li1" style="padding:5px;list-style-type:disc !important;">desktop: <a href="https://www.google.com/chrome/browser/desktop/" target="_blank" class="modal-link-cls">https://www.google.com/chrome/browser/desktop/</a></li>
											<li class="modal-supp-text modal-supp-text-pop" id="li2" style="padding:5px;list-style-type:disc !important;">iPad: go to "app store"; search 'google chrome' and download</li>
										</ul>
									</li>
									<li class="modal-supp-text modal-supp-text-pop" id="li2" style="padding:5px;list-style-type:decimal !important;">
										<b>Remote/VPN access for iPad users only:</b> to access Helios remotely on your iPad, download VPN app (see attachments)
									</li>
									<li class="modal-supp-text modal-supp-text-pop" id="li3" style="padding:5px;list-style-type:decimal !important;">
										<b>Clear cache on Chrome browser</b> (note: this clears your browsing history/cookies and <b>will only need to be done when you have problems accessing Helios</b>)
										<ul class="">
											<li class="modal-supp-text modal-supp-text-pop" id="li1" style="padding:5px;list-style-type:disc !important;">windows desktop: press control+shift+delete; under "Obliterate the following items from" dropdown, select "the beginning of time", then select "clear browsing data" at bottom</li>
											<li class="modal-supp-text modal-supp-text-pop" id="li2" style="padding:5px;list-style-type:disc !important;">mac desktop: press command+shift+delete; under "Obliterate the following items from" dropdown, select "the beginning of time", then select "clear browsing data" at bottom</li>
											<li class="modal-supp-text modal-supp-text-pop" id="li2" style="padding:5px;list-style-type:disc !important;">iPad: In the Chrome app, tap on the menu button (looks like three horizontal lines) and then select Settings. Tap on Privacy and you will see an option to Clear Cookies, Clear Cache, Clear Browsing History, or Clear All. Select <b>Clear All</b>.</li>
										</ul>
									</li>
								</ol>
							</div>
						</div>
					</div>
					<!-- END WIDGET TAB -->
				</div>

			</div>
			<div class="modal-footer col-md-12 modal-foot modal-foot-pop" style="padding: 4px;">
				<button type="button" class="btn btn-sm modal-cls modal-cls-pop"
					 data-dismiss="modal" style="color:#fff">CLOSE</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
  </script>