// Copyright 2010 htmldrive.net Inc.
/**
* @projectHomepage http://www.htmldrive.net/welcome/amazon-scroller
* @projectDescription Amazon style image and title scroller
* @author htmldrive.net
* More script and css style : htmldrive.net
* @version 1.0
* @license http://www.apache.org/licenses/LICENSE-2.0
*/ 
var totalBaBLength=0;
var totalBnBLength=0
var totalReorderRecommLength=0
var totalPlayRecomLength=0
/*var maxAbandoneddisplaylength=5;
var maxRecommdisplaylength=5;
var maxDotcomisplaylength=5;*/
var maxBaBdisplaylength=5;
var maxBaBlength=5;
var maxBnBdisplaylength=5;
var maxReorderdisplaylength=5;
var maxPlayRecomlength=5;

var totalAbandonedLength=0;
var totalRecommLength=0
var totalDotCommLength=0
var maxAbandoneddisplaylength=5;
var abanImgCount=5;
var maxRecommdisplaylength=5;
var maxDotcomisplaylength=5;
var reorderMaxImagCount=5;
var orientation='';
var collapsed='false';
(function(a){
    a.fn.amazon_scroller=function(p){
        var p=p||{};

        var g=p&&p.scroller_time_interval?p.scroller_time_interval:"0";
        var h=p&&p.scroller_title_show?p.scroller_title_show:"disable";
        var i=p&&p.scroller_window_background_color?p.scroller_window_background_color:"white";
        var j=p&&p.scroller_window_padding?p.scroller_window_padding:"5";
        var k=p&&p.scroller_border_size?p.scroller_border_size:"1";
        var l=p&&p.scroller_border_color?p.scroller_border_color:"black";
        var m=p&&p.scroller_images_width?p.scroller_images_width:"70";
        var m1=p&&p.scroller_images_width?p.scroller_images_width:"140";
        var n=p&&p.scroller_images_height?p.scroller_images_height:"50";
        var o=p&&p.scroller_title_size?p.scroller_title_size:"12";
        var q=p&&p.scroller_title_color?p.scroller_title_color:"blue";
        var r=p&&p.scroller_show_count?p.scroller_show_count:"3";
        var d=p&&p.directory?p.directory:"img";
        j += "px";
        k += "px";
        m += "px";
        n += "px";
        o += "px";
        var dom=a(this);
        var s;
        var t=0;
        var u;
        var v;
        var w=dom.find("ul:first").children("li").length;
        //alert(w)
        var x=Math.ceil(w/r);
        if(dom.find("ul").length==0||dom.find("li").length==0){
            dom.append("Require content");
            return null
        }
        dom.find("ul[id^='ulid']:first").children("li").children("div").children("a").children("img").css("width",m).css("height",n);
        if(h=='enable'){
            dom.find("ul[id^='ulid']:first").children("li").children("div").children("a").each(function(){
               // $(this).append('<div class="amazon_scroller_title">'+$(this).attr("title")+'</div>')
            })
			dom.find("ul[id^='ulid']:first").children("li").children("div").css("height",n+o+"px");
        }else{
			dom.find("ul[id^='ulid']:first").children("li").children("div").css("height",n+"px");
		}
        /*dom.find("ul:first").children("li").children("div").children("a").children("img").css("width",m).css("height",n);
        if(h=='enable'){
            dom.find("ul:first").children("li").children("div").children("a").each(function(){
               // $(this).append('<div class="amazon_scroller_title">'+$(this).attr("title")+'</div>')
            })
			dom.find("ul:first").children("li").children("div").css("height",n+o+"px");
        }else{
			dom.find("ul:first").children("li").children("div").css("height",n+"px");
		}*/
		dom.find(".amazon_scroller_title").height(parseInt(o)+"px");
        s_s_ul(dom,j,k,l,i);
        s_s_nav(dom.find(".amazon_scroller_nav"),d);
        m=parseInt(m);
        //dom.find("ul[id^='SUulid']:first").children("li").css("width","135px");
        //dom.find("ul[id^='SUulid']:first").children("li").children("div").css("width","135px");
        dom.find("ul[id^='SUulid']:first").children("li").css("width",m+"px");
        //alert($(window).width())
        var babWidth=dom.find("ul[id^='ulidBAB']:first").children("li").css("width");
        if(undefined != babWidth && '290px'==babWidth){
     	   babHtml='<li style="border: 1px solid green; width: 290px; height: 350px; display: block;">';
     	   dom.find("#ulidBAB").append(babHtml+""+dom.find("#ulidBAB li:first").html()+"</li>");
            dom.find("#ulidBAB").children(":first").remove();
        }
        if($(window).width()<=768){
        	dom.find("ul[id^='ulidREORDER']:first").children("li").css("width","142px");
        	dom.find(".amazon_scroller_reorder").css("paddning-left","0px");
        		maxReorderdisplaylength=4;
        		reorderMaxImagCount=4;
        		dom.find("ul[id^='SUulid']:first").children("li").attr("style","width:110px !important;border: 1px solid #ccc;height: 362px;display: block;border-radius: 3px !important;");
            	$("#abanId").attr("style","width:90.1% !important");
            	abanImgCount=4;
            	maxAbandoneddisplaylength=4;
            	dom.find("ul[id^='ulidBAB']:first").children("li").css("width","355px");
                dom.find("ul[id^='ulidBAB']:first").children("li").children("div").css("width","110px");
                maxBaBdisplaylength=4;
                maxBaBlength=4;
                if(totalReorderRecommLength<=reorderMaxImagCount){
        			var cls=$("ul#reorder li a[title=Next]").attr("class");
                	if(cls=='btn btn-sm default next'){
                		$("ul#reorder li a[title=Next]").attr("class","btn btn-sm default next disabled");
                	}
        		}
                if((totalBaBLength) <= 3){
    	            var cls=$("ul#boughtAbought li a[title=Next]").attr("class");
    	        	if(cls=='btn btn-sm default next'){
    	        		$("ul#boughtAbought li a[title=Next]").attr("class","btn btn-sm default next disabled");
    	        	}
                }
        }else if(($(window).width()<=1024) && ($(window).width() > 768)){
            	dom.find("ul[id^='ulidREORDER']:first").children("li").css("width","156px");
            	//$("#ReorderMaskId").attr("style","width:90.1% !important");
            	dom.find(".amazon_scroller_reorder").css("paddning-left","0px");
            		maxReorderdisplaylength=4;
            		reorderMaxImagCount=4;
            		dom.find("ul[id^='SUulid']:first").children("li").attr("style","border: 1px solid #ccc;height: 362px;width: 135px;display: block;border-radius: 3px !important;");
                	$("#abanId").attr("style","width:87% !important");
                	abanImgCount=5;
                	maxAbandoneddisplaylength=5;	
                dom.find("ul[id^='ulidBAB']:first").children("li").css("width","405px");
                dom.find("ul[id^='ulidBAB']:first").children("li").children("div").css("width","115px");
                maxBaBdisplaylength=4;
                maxBaBlength=4;
                if(totalReorderRecommLength<=reorderMaxImagCount){
        			var cls=$("ul#reorder li a[title=Next]").attr("class");
                	if(cls=='btn btn-sm default next'){
                		$("ul#reorder li a[title=Next]").attr("class","btn btn-sm default next disabled");
                	}
        		}
                if((totalBaBLength) <= 3){
    	            var cls=$("ul#boughtAbought li a[title=Next]").attr("class");
    	        	if(cls=='btn btn-sm default next'){
    	        		$("ul#boughtAbought li a[title=Next]").attr("class","btn btn-sm default next disabled");
    	        	}
                }
        }
        if(($(window).width()<=1024) && ($(window).width() > 768)){
            $("#currDeviceWidth").val($(window).width())        	
        }else if($(window).width()>1200){
            $("#currDeviceWidth").val("");
            if(totalReorderRecommLength<=reorderMaxImagCount){ 
    			var cls=$("ul#reorder li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next'){
            		$("ul#reorder li a[title=Next]").attr("class","btn btn-sm default next disabled");
            	}
    		}
            if((totalBaBLength) <= 6){
	            var cls=$("ul#boughtAbought li a[title=Next]").attr("class");
	        	if(cls=='btn btn-sm default next'){
	        		$("ul#boughtAbought li a[title=Next]").attr("class","btn btn-sm default next disabled");
	        	}
            }
        }
        $(window).resize(function(){
        	var isChrome = /Chrome/.test(navigator.userAgent) && /Google Inc/.test(navigator.vendor);
        	var isSafari = /Safari/.test(navigator.userAgent) && /Apple Computer/.test(navigator.vendor);
        	//alert(collapsed)
        	//alert(window.navigator.userAgent.toLowerCase())
        	if (isChrome && (collapsed=='false') && $(window).width()>1023){ 
        		$("#reorderPane").removeClass("reorderPaneWinCollapse");
        		$("#reorderPane").removeClass("reorderPaneIpadCollapse");
        		$("#reorderPane").removeClass("reorderPaneIpadExp");
        		$("#reorderPane").addClass("reorderPaneWinExp");
        		$("#babPane").removeClass("babPaneWinCollapse");
        		$("#babPane").removeClass("babPaneIpadCollapse");
        		$("#babPane").removeClass("babPaneIpadExp");
        		$("#babPane").addClass("babPaneWinExp");
        	}else if (isChrome && (collapsed=='true') && $(window).width()>1023){ 
        		$("#reorderPane").removeClass("reorderPaneWinExp");
        		$("#reorderPane").removeClass("reorderPaneIpadCollapse");
        		$("#reorderPane").removeClass("reorderPaneIpadExp");
        		$("#reorderPane").addClass("reorderPaneWinCollapse");
        		$("#babPane").removeClass("babPaneWinExp");
        		$("#babPane").removeClass("babPaneIpadCollapse");
        		$("#babPane").removeClass("babPaneIpadExp");
        		$("#babPane").addClass("babPaneWinCollapse");
        	}else if (isSafari){
        		//alert("You are using Safari!");
        	} 
        	//alert($(window).width()+"---"+$("#currDeviceWidth").val())
        	if(($(window).width()<=1024)){
        	    //location.reload();
        		openRecommendations();
    		}else if(($(window).width()>1200 && $("#currDeviceWidth").val() !='' && $("#currDeviceWidth").val() <=1024)){
    			//location.reload();
    			openRecommendations();
    		}
        });
        window
        .matchMedia('(orientation: portrait)')
        .addListener(function (m) {
            if (m.matches) { 
            	//location.reload();
            	openRecommendations();
            } else { 
            	//location.reload();
            	openRecommendations();
            }
        });
        n=parseInt(n);
        dom.find("ul[id^='ulid']:first").children("li").children("div").css("margin-left","1%");
        dom.find("ul[id^='ulid']:first").children("li").children("div").css("margin-right","1%");
        dom.find("ul[id^='ulid']:first").children("li").children("div").children("a").css("color",q);
        dom.find("ul[id^='ulid']:first").children("li").children("div").children("a").css("font-size",o);
        dom.find("ul[id^='SUulid']:first").children("li").children("a").css("color",q);
        dom.find("ul[id^='SUulid']:first").children("li").children("a").css("font-size",o);
        /*dom.find("ul:first").children("li").children("div").css("margin-left","1%");
        dom.find("ul:first").children("li").children("div").css("margin-right","1%");
        dom.find("ul:first").children("li").children("div").children("a").css("color",q);
        dom.find("ul:first").children("li").children("div").children("a").css("font-size",o);*/
        begin();
        s=setTimeout(play,g);
        
            $("a[title=Prev]").attr("class","btn btn-sm default prev disabled");
            dom.find("#aban_nav.amazon_scroller_nav").children("li").click(function(){
            	//alert("aban")
                if($(this).parent().children().index($(this))==0 && maxAbandoneddisplaylength > abanImgCount){
                	
                	var cls=$("ul#aban_nav li a[title=Next]").attr("class");
                	if(cls=='btn btn-sm default next disabled'){
                	$("ul#aban_nav li a[title=Next]").attr("class","btn btn-sm default next");
                	}
                    previous('aban')
                    maxAbandoneddisplaylength--;
                    if(maxAbandoneddisplaylength==abanImgCount){
                    	$("ul#aban_nav li a[title=Prev]").attr("class","btn btn-sm default prev disabled");
                    }
                }else if($(this).parent().children().index($(this))==1 && totalAbandonedLength > maxAbandoneddisplaylength){
                	var cls=$("ul#aban_nav li a[title=Next]").attr("class");
                	if(cls=='btn btn-sm default next disabled'){
                	$("ul#aban_nav li a[title=Next]").attr("class","btn btn-sm default next");
                	}
                	$("ul#aban_nav li a[title=Prev]").attr("class","btn btn-sm default prev");
                	//alert(cls)
                	//alert($(this).parent().children().index($(this)))
                	//]btn btn-sm default next disabled
                	//alert("total="+totalAbandonedLength+" max="+maxAbandoneddisplaylength);
                	//if(totalAbandonedLength>maxAbandoneddisplaylength)
                    next('aban')
                    maxAbandoneddisplaylength++;
                }else if($(this).parent().children().index($(this))==1){
                	var cls=$("ul#aban_nav li a[title=Next]").attr("class");
                	if(cls=='btn btn-sm default next'){
                		$("ul#aban_nav li a[title=Next]").attr("class","btn btn-sm default next disabled");
                	}
                }
            });
            dom.find("#dotcom_nav.amazon_scroller_nav").children("li").click(function(){
            	//alert("dotcom")
                if($(this).parent().children().index($(this))==0 && maxDotcomisplaylength > 5){
                	
                	var cls=$("ul#dotcom_nav li a[title=Next]").attr("class");
                	if(cls=='btn btn-sm default next disabled'){
                	$("ul#dotcom_nav li a[title=Next]").attr("class","btn btn-sm default next");
                	}
                    previous('dotcom')
                    maxDotcomisplaylength--;
                    if(maxDotcomisplaylength==5){
                    	$("ul#dotcom_nav li a[title=Prev]").attr("class","btn btn-sm default prev disabled");
                    }
                }else if($(this).parent().children().index($(this))==1 && totalDotCommLength > maxDotcomisplaylength){
                	
                	var cls=$("ul#dotcom_nav li a[title=Next]").attr("class");
                	if(cls=='btn btn-sm default next disabled'){
                	$("ul#dotcom_nav li a[title=Next]").attr("class","btn btn-sm default next");
                	}
                	$("ul#dotcom_nav li a[title=Prev]").attr("class","btn btn-sm default prev");
                	//alert(cls)
                	//alert($(this).parent().children().index($(this)))
                	//]btn btn-sm default next disabled
                	//alert("total="+totalAbandonedLength+" max="+maxAbandoneddisplaylength);
                	//if(totalAbandonedLength>maxAbandoneddisplaylength)
                    next('dotcom')
                    maxDotcomisplaylength++;
                }else if($(this).parent().children().index($(this))==1){
                	var cls=$("ul#dotcom_nav li a[title=Next]").attr("class");
                	if(cls=='btn btn-sm default next'){
                		$("ul#dotcom_nav li a[title=Next]").attr("class","btn btn-sm default next disabled");
                	}
                }
            });
            dom.find("#recomm_nav.amazon_scroller_nav").children("li").click(function(){
            	//alert("recom")
                if($(this).parent().children().index($(this))==0 && maxRecommdisplaylength > reorderMaxImagCount){
                	
                	var cls=$("ul#recomm_nav li a[title=Next]").attr("class");
                	if(cls=='btn btn-sm default next disabled'){
                	$("ul#recomm_nav li a[title=Next]").attr("class","btn btn-sm default next");
                	}
                    previous('recomm')
                    maxRecommdisplaylength--;
                    if(maxRecommdisplaylength==reorderMaxImagCount){
                    	$("ul#recomm_nav li a[title=Prev]").attr("class","btn btn-sm default prev disabled");
                    }
                }else if($(this).parent().children().index($(this))==1 && totalRecommLength > maxRecommdisplaylength){
                	
                	var cls=$("ul#recomm_nav li a[title=Next]").attr("class");
                	if(cls=='btn btn-sm default next disabled'){
                	$("ul#recomm_nav li a[title=Next]").attr("class","btn btn-sm default next");
                	}
                	$("ul#recomm_nav li a[title=Prev]").attr("class","btn btn-sm default prev");
                	//alert(cls)
                	//alert($(this).parent().children().index($(this)))
                	//]btn btn-sm default next disabled
                	//alert("total="+totalAbandonedLength+" max="+maxAbandoneddisplaylength);
                	//if(totalAbandonedLength>maxAbandoneddisplaylength)
                    next('recomm')
                    maxRecommdisplaylength++;
                }else if($(this).parent().children().index($(this))==1){
                	var cls=$("a[title=Next]").attr("class");
                	if(cls=='btn btn-sm default next'){
                		$("ul#recomm_nav li a[title=Next]").attr("class","btn btn-sm default next disabled");
                	}
                }
            });
        dom.find("#boughtAbought.amazon_scroller_nav").children("li").click(function(){
        	//alert("aban"+(totalBaBLength) +"--"+maxBaBdisplaylength)
            if($(this).parent().children().index($(this))==0 && maxBaBdisplaylength>maxBaBlength){
            	
            	var cls=$("ul#boughtAbought li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next disabled'){
            	$("ul#boughtAbought li a[title=Next]").attr("class","btn btn-sm default next");
            	} 
                previous('BabMaskId')
                maxBaBdisplaylength= maxBaBdisplaylength-3;
                if(maxBaBdisplaylength==maxBaBlength){
                	$("ul#boughtAbought li a[title=Prev]").attr("class","btn btn-sm default prev disabled");
                }
                
            }else if($(this).parent().children().index($(this))==1 && (totalBaBLength) > maxBaBdisplaylength){
            	var cls=$("ul#boughtAbought li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next disabled'){
            	$("ul#boughtAbought li a[title=Next]").attr("class","btn btn-sm default next");
            	}
            	$("ul#boughtAbought li a[title=Prev]").attr("class","btn btn-sm default prev");
            	//alert(cls)
            	//alert($(this).parent().children().index($(this)))
            	//]btn btn-sm default next disabled
            	//alert("total="+totalAbandonedLength+" max="+maxAbandoneddisplaylength);
            	//if(totalAbandonedLength>maxAbandoneddisplaylength)
                next('BabMaskId')
                maxBaBdisplaylength=maxBaBdisplaylength+3
                if((totalBaBLength) <= maxBaBdisplaylength){ 
                	var cls=$("ul#boughtAbought li a[title=Next]").attr("class");
                	if(cls=='btn btn-sm default next'){
                		$("ul#boughtAbought li a[title=Next]").attr("class","btn btn-sm default next disabled");
                	}
                }
            }else if((totalBaBLength) <= maxBaBdisplaylength){ 
            	/*var cls=$("ul#boughtAbought li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next'){
            		$("ul#boughtAbought li a[title=Next]").attr("class","btn btn-sm default next disabled");
            	}*/
            	var cls=$("ul#boughtAbought li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next'){
            		$("ul#boughtAbought li a[title=Next]").attr("class","btn btn-sm default next disabled");
            	}
            }
        });
        dom.find("#reorder.amazon_scroller_nav").children("li").click(function(){
        	
            if($(this).parent().children().index($(this))==0 && maxReorderdisplaylength>reorderMaxImagCount){
            	
            	var cls=$("ul#reorder li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next disabled'){
            	$("ul#reorder li a[title=Next]").attr("class","btn btn-sm default next");
            	}
                previous('ReorderMaskId')
                maxReorderdisplaylength--;
                if(maxReorderdisplaylength==reorderMaxImagCount){
                	$("ul#reorder li a[title=Prev]").attr("class","btn btn-sm default prev disabled");
                }
            }else if($(this).parent().children().index($(this))==1 && totalReorderRecommLength>maxReorderdisplaylength){
            	
            	var cls=$("ul#reorder li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next disabled'){
            	$("ul#reorder li a[title=Next]").attr("class","btn btn-sm default next");
            	}
            	$("ul#reorder li a[title=Prev]").attr("class","btn btn-sm default prev");
            	//alert(cls)
            	//alert($(this).parent().children().index($(this)))
            	//]btn btn-sm default next disabled
            	//alert("total="+totalReorderRecommLength+" max="+maxReorderdisplaylength);
            	//if(totalAbandonedLength>maxAbandoneddisplaylength)
                next('ReorderMaskId')
                maxReorderdisplaylength=maxReorderdisplaylength+1;
                if((totalReorderRecommLength) <= maxReorderdisplaylength){ 
                	var cls=$("ul#reorder li a[title=Next]").attr("class");
                	if(cls=='btn btn-sm default next'){
                		$("ul#reorder li a[title=Next]").attr("class","btn btn-sm default next disabled");
                	}
                }
            }else if((totalReorderRecommLength) <= maxReorderdisplaylength){
            	var cls=$("ul#reorder li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next'){
            		$("ul#reorder li a[title=Next]").attr("class","btn btn-sm default next disabled");
            	}
            }
        });
        dom.find("#BnBRecom.amazon_scroller_nav").children("li").click(function(){
        	//alert("recom"+"-"+totalRecommLength+"-"+maxRecommdisplaylength)
            if($(this).parent().children().index($(this))==0 && maxBnBdisplaylength>5){
            	
            	var cls=$("ul#BnBRecom li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next disabled'){
            	$("ul#BnBRecom li a[title=Next]").attr("class","btn btn-sm default next");
            	}
                previous('BnBMaskId')
                maxBnBdisplaylength--;
                if(maxBnBdisplaylength==5){
                	$("ul#BnBRecom li a[title=Prev]").attr("class","btn btn-sm default prev disabled");
                }
            }else if($(this).parent().children().index($(this))==1 && totalBnBLength > maxBnBdisplaylength){
            	var cls=$("ul#BnBRecom li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next disabled'){
            	$("ul#BnBRecom li a[title=Next]").attr("class","btn btn-sm default next");
            	}
            	$("ul#BnBRecom li a[title=Prev]").attr("class","btn btn-sm default prev");
            	//alert(cls)
            	//alert($(this).parent().children().index($(this)))
            	//]btn btn-sm default next disabled
            	//alert("total="+totalAbandonedLength+" max="+maxAbandoneddisplaylength);
            	//if(totalAbandonedLength>maxAbandoneddisplaylength)
                next('BnBMaskId')
                maxBnBdisplaylength++;
            }else if($(this).parent().children().index($(this))==1){
            	var cls=$("ul#BnBRecom li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next'){
            		$("ul#BnBRecom li a[title=Next]").attr("class","btn btn-sm default next disabled");
            	}
            }
        });
dom.find("#PlayRecom.amazon_scroller_nav").children("li").click(function(){
        	
            if($(this).parent().children().index($(this))==0 && maxPlayRecomlength>5){
            	
            	var cls=$("ul#PlayRecom li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next disabled'){
            	$("ul#PlayRecom li a[title=Next]").attr("class","btn btn-sm default next");
            	}
                previous('playRecomMaskId')
                maxPlayRecomlength--;
                if(maxPlayRecomlength==5){
                	$("ul#PlayRecom li a[title=Prev]").attr("class","btn btn-sm default prev disabled");
                }
            }else if($(this).parent().children().index($(this))==1 && totalPlayRecomLength>maxPlayRecomlength){
            	
            	var cls=$("ul#PlayRecom li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next disabled'){
            	$("ul#PlayRecom li a[title=Next]").attr("class","btn btn-sm default next");
            	}
            	$("ul#PlayRecom li a[title=Prev]").attr("class","btn btn-sm default prev");
            	//alert(cls)
            	//alert($(this).parent().children().index($(this)))
            	//]btn btn-sm default next disabled
            	//alert("total="+totalAbandonedLength+" max="+maxAbandoneddisplaylength);
            	//if(totalAbandonedLength>maxAbandoneddisplaylength)
                next('playRecomMaskId')
                maxPlayRecomlength++;
            }else if($(this).parent().children().index($(this))==1){
            	var cls=$("ul#PlayRecom li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next'){
            		$("ul#PlayRecom li a[title=Next]").attr("class","btn btn-sm default next disabled");
            	}
            }
        });
        dom.hover(
            function(){
                clearTimeout(s);
            },
            function(){
                s=setTimeout(play,g);
            }
        );
        function begin(){
            var a=dom.find("ul:first").children("li").outerWidth(true)*w;
            dom.find("ul:first").children("li").hide();
            dom.find("ul:first").children("li").slice(0,r).show();
            u=dom.find("ul:first").outerWidth();
            v=dom.find("ul:first").outerHeight();
            dom.find("ul:first").width(a);
            dom.width(u+60);
            dom.height(v);
            dom.children(".amazon_scroller_mask").width(u);
            dom.children(".amazon_scroller_mask").height(v);
            dom.find("ul:first").children("li").show();
            dom.css("position","relative");
            dom.find("ul:first").css("position","absolute");
            dom.children(".amazon_scroller_mask").width(u);
            dom.children(".amazon_scroller_mask").height(v);
            dom.find(".amazon_scroller_nav").css('top',(v-50)/2+parseInt(j)+"px");
            dom.find(".amazon_scroller_nav").width(u+60)
			//dom.find("ul:first").clone().appendTo(dom.children(".amazon_scroller_mask"));
			dom.children(".amazon_scroller_mask").find("ul:last").css("left",0);
        }
        function previous(id){
			clearTimeout(s); 
			if(t > 0 && undefined !=id && id=='BabMaskId' && ($(window).width()<=1024) && ($(window).width()>768)){ 
				t--;
				dom.children(".amazon_scroller_mask").find("ul").animate({
	                left: '+='+(405+10)
	            },0);
			}else if(t > 0 && undefined !=id && id=='BabMaskId' && ($(window).width()>1024)){ 
				t--;
				dom.children(".amazon_scroller_mask").find("ul").animate({
	                left: '+='+(440+10)
	            },0);
			}else if(t > 0 && undefined !=id && id=='BabMaskId' && ($(window).width()<=768)){ 
				t--;
				dom.children(".amazon_scroller_mask").find("ul").animate({
	                left: '+='+(355+10)
	            },0);
			} else if(t > 0 && undefined !=id && id=='playRecomMaskId' ||id=='ReorderMaskId' || id=='BnBMaskId'){
				t--;
				if($(window).width()<=768 && (id=='ReorderMaskId')){
				dom.children(".amazon_scroller_mask").find("ul").animate({
	                left: '+='+(142+10)
	            },0);
				} else if(($(window).width()<=1024) && ($(window).width() > 768) &&  (id=='ReorderMaskId')){
					dom.children(".amazon_scroller_mask").find("ul").animate({
		                left: '+='+(156+10)
		            },0);
				} else{
					dom.children(".amazon_scroller_mask").find("ul").animate({
		                left: '+='+(164+10)
		            },0);
				}
			}else if(t > 0 && undefined !=id && id=='recomm' ||id=='aban' || id=='dotcom'){
				t--;
				/*dom.children(".amazon_scroller_mask").find("ul").animate({
	                left: '+='+(m+10)
	            },0);*/
				if($(window).width()<=768 && ((id=='aban')|| (id=='dotcom'))){ 
    				dom.children(".amazon_scroller_mask").find("ul").animate({
    	                left: '+='+(110+10)
    	            },0);
    				}else if(($(window).width()<=1024) && ($(window).width() > 768) &&  ((id=='aban')|| (id=='dotcom'))){
    					dom.children(".amazon_scroller_mask").find("ul").animate({
    		                left: '+='+(135+10)
    		            },0);
    				}else{ 
    					dom.children(".amazon_scroller_mask").find("ul").animate({
    		                left: '+='+(m+10)
    		            },0);
    				}
			}
        }
        function next(id){
        	//alert(1)
            play(id);
        }
        function play(id){
            clearTimeout(s);
			t++;
			var a = dom.find("ul:first").children("li").outerWidth(true)*w;
			//alert(a)
            if(t >= w+1){
				t = 0;
				dom.children(".amazon_scroller_mask").find("ul:first").css("left","0px");
				dom.children(".amazon_scroller_mask").find("ul:last").css("left",a);
				s=setTimeout(play,0);
            }else if(undefined!=id && id=='BabMaskId' && ($(window).width()>1024)){ 
				dom.children(".amazon_scroller_mask").find("ul").animate({
	                left: '-='+(440+10)
	            },0);
				s=setTimeout(play,g);
			}else if(undefined !=id && id=='BabMaskId' && ($(window).width()<=1024) && ($(window).width() > 768)){
				dom.children(".amazon_scroller_mask").find("ul").animate({
	                left: '-='+(405+10)
	            },0);
			}else if(undefined !=id && id=='BabMaskId' && ($(window).width()<=768)){
				dom.children(".amazon_scroller_mask").find("ul").animate({
	                left: '-='+(355+10)
	            },0);
			}else if(undefined!=id && id=='playRecomMaskId' || id=='ReorderMaskId' || id=='BnBMaskId'){
				//alert('play')
				if($(window).width()<=768 && (id=='ReorderMaskId')){
				dom.children(".amazon_scroller_mask").find("ul").animate({
	                left: '-='+(142+10)
	            },0);
				}else if(($(window).width()<=1024) && ($(window).width() > 768) &&  (id=='ReorderMaskId')){
					dom.children(".amazon_scroller_mask").find("ul").animate({
		                left: '-='+(156+10)
		            },0);
				}else{
					dom.children(".amazon_scroller_mask").find("ul").animate({
		                left: '-='+(164+10)
		            },0);
				}
				s=setTimeout(play,g);
			}else if(undefined!=id && id=='recomm' || id=='aban' || id=='dotcom'){
				/*dom.children(".amazon_scroller_mask").find("ul").animate({
	                left: '-='+(m+10)
	            },0);*/
				if($(window).width()<=768 && ((id=='aban')|| (id=='dotcom'))){ 
    				dom.children(".amazon_scroller_mask").find("ul").animate({
    	                left: '-='+(110+10)
    	            },0);
    				}else if(($(window).width()<=1024) && ($(window).width() > 768) && ((id=='aban')|| (id=='dotcom'))){
    					dom.children(".amazon_scroller_mask").find("ul").animate({
    		                left: '-='+(135+10)
    		            },0);
    				}else{ 
    					dom.children(".amazon_scroller_mask").find("ul").animate({
    		                left: '-='+(m+10)
    		            },0);
    					s=setTimeout(play,g);
    				}
			}
        }
        function s_s_ul(a,b,c,d,e){
            b=parseInt(b);
            c=parseInt(c);
            var f="border: "+d+" solid "+" "+c+"px; padding:"+b+"px; background-color:"+e;
            a.attr("style",f)
        }
        function s_s_nav(a,d){
            var b=a.children("li:first");
            var c=a.children("li:last");
            a.children("li").css("width","25px");
            a.children("li").css("height","50px");
            a.children("li").css('background-image','url("'+d+'/arrow.gif")');
            c.css('background-position','right top');
            a.children("li").css('background-repeat','no-repeat');
            c.css('right','0px');
            b.css('left','0px');
        }
    }
})(jQuery);
