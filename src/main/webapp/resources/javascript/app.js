// Copyright 2010 htmldrive.net Inc.
/**
* @projectHomepage http://www.htmldrive.net/welcome/amazon-scroller
* @projectDescription Amazon style image and title scroller
* @author htmldrive.net
* More script and css style : htmldrive.net
* @version 1.0
* @license http://www.apache.org/licenses/LICENSE-2.0
*/ 
var totalAbandonedLength=0;
var totalRecommLength=0
var totalDotCommLength=0
var maxAbandoneddisplaylength=5;
var abanImgCount=5;
var maxRecommdisplaylength=5;
var maxDotcomisplaylength=5;
var dotcomImgCount=5;
(function(a){
    a.fn.amazon_scroller=function(p){
    	//alert(111)
    	
        var p=p||{};

        var g=p&&p.scroller_time_interval?p.scroller_time_interval:"0";
        var h=p&&p.scroller_title_show?p.scroller_title_show:"disable";
        var i=p&&p.scroller_window_background_color?p.scroller_window_background_color:"white";
        var j=p&&p.scroller_window_padding?p.scroller_window_padding:"5";
        var k=p&&p.scroller_border_size?p.scroller_border_size:"1";
        var l=p&&p.scroller_border_color?p.scroller_border_color:"black";
        var m=p&&p.scroller_images_width?p.scroller_images_width:"70";
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
        dom.find("ul:first").children("li").children("a").children("img").css("width",m).css("height",n);
        if(h=='enable'){
            dom.find("ul:first").children("li").children("a").each(function(){
               // $(this).append('<div class="amazon_scroller_title">'+$(this).attr("title")+'</div>')
            })
			dom.find("ul:first").children("li").css("height",n+o+"px");
        }else{
			dom.find("ul:first").children("li").css("height",n+"px");
		}
		dom.find(".amazon_scroller_title").height(parseInt(o)+"px");
        s_s_ul(dom,j,k,l,i);
        s_s_nav(dom.find(".amazon_scroller_nav"),d);
        m=parseInt(m);
        dom.find("ul:first").children("li").css("width",m+"px");
        n=parseInt(n);
        if($(window).width()<=768){
        	dom.find("ul[id^='SUulid']:first").children("li").attr("style","width:110px !important;border: 1px solid #ccc;height: 362px;display: block;border-radius: 3px !important;");
        	//$("#dotcomId").attr("style","margin-left:3% !important");
        	$("#abanId").attr("style","width:90.1% !important;margin-left:20px !important;");
        	abanImgCount=4;
        	maxAbandoneddisplaylength=4;
        	//$("#dotcomId").attr("style","width:90% !important");
        	dotcomImgCount=4;
        	maxDotcomisplaylength=4;
        }else if(($(window).width()<=1024) && ($(window).width() > 768)){
        	dom.find("ul[id^='SUulid']:first").children("li").attr("style","border: 1px solid #ccc;height: 362px;width: 135px;display: block;border-radius: 3px !important;");
        	$("#dotcomId").attr("style","margin-left:50px !important;");
        	$("#abanId").attr("style","width:87% !important;margin-left:50px !important;");
        	abanImgCount=5;
        	maxAbandoneddisplaylength=5;
        	//$("#dotcomId").attr("style","width:88% !important;margin-left:40px !important;");
        	dotcomImgCount=5;
        	maxDotcomisplaylength=5;
        }
        
        window
        .matchMedia('(orientation: portrait)')
        .addListener(function (m) {
            if (m.matches) { 
            	location.reload();
            	/*dom.find("ul[id^='SUulid']:first").children("li").attr("style","width:110px !important");
            	$("#abanId").attr("style","width:83% !important");
            	abanImgCount=4;
            	maxAbandoneddisplaylength=4;*/
            } else { 
            	location.reload();
            	/*dom.find("ul[id^='SUulid']:first").children("li").attr("style","width:135px !important");
            	$("#abanId").attr("style","width:88% !important");
            	abanImgCount=5;
            	maxAbandoneddisplaylength=5;*/
            }
        });
        
        dom.find("ul:first").children("li").children("a").css("color",q);
        dom.find("ul:first").children("li").children("a").css("font-size",o);
        begin();
        s=setTimeout(play,g);
        dom.find("recomm_nav.amazon_scroller_nav").children("li").hover(
            function(){
                if($(this).parent().children().index($(this))==0){
                    $(this).css("background-position","left -50px");
                }else if($(this).parent().children().index($(this))==1){
                    $(this).css("background-position","right -50px");
                }
            },
            function(){
                if($(this).parent().children().index($(this))==0){
                    $(this).css("background-position","left top");
                }else if($(this).parent().children().index($(this))==1){
                    $(this).css("background-position","right top");
                }
            }
            );
        $("a[title=Prev]").attr("class","btn btn-sm default prev disabled");
        dom.find("#aban_nav.amazon_scroller_nav").children("li").click(function(){
        	//alert("aban")
            if($(this).parent().children().index($(this))==0 && maxAbandoneddisplaylength > abanImgCount){
            	
            	var cls=$("ul#aban_nav li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next disabled'){
            	$("ul#aban_nav li a[title=Next]").attr("class","btn btn-sm default next");
            	}
                previous()
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
                next()
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
            if($(this).parent().children().index($(this))==0 && maxDotcomisplaylength > dotcomImgCount){
            	
            	var cls=$("ul#dotcom_nav li a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next disabled'){
            	$("ul#dotcom_nav li a[title=Next]").attr("class","btn btn-sm default next");
            	}
                previous()
                maxDotcomisplaylength--;
                if(maxDotcomisplaylength==dotcomImgCount){
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
                next()
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
            if($(this).parent().children().index($(this))==0 && maxRecommdisplaylength > 5){
            	
            	var cls=$("a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next disabled'){
            	$("a[title=Next]").attr("class","btn btn-sm default next");
            	}
                previous()
                maxRecommdisplaylength--;
                if(maxRecommdisplaylength==5){
                	$("a[title=Prev]").attr("class","btn btn-sm default prev disabled");
                }
            }else if($(this).parent().children().index($(this))==1 && totalRecommLength > maxRecommdisplaylength){
            	
            	var cls=$("a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next disabled'){
            	$("a[title=Next]").attr("class","btn btn-sm default next");
            	}
            	$("a[title=Prev]").attr("class","btn btn-sm default prev");
            	//alert(cls)
            	//alert($(this).parent().children().index($(this)))
            	//]btn btn-sm default next disabled
            	//alert("total="+totalAbandonedLength+" max="+maxAbandoneddisplaylength);
            	//if(totalAbandonedLength>maxAbandoneddisplaylength)
                next()
                maxRecommdisplaylength++;
            }else if($(this).parent().children().index($(this))==1){
            	var cls=$("a[title=Next]").attr("class");
            	if(cls=='btn btn-sm default next'){
            		$("a[title=Next]").attr("class","btn btn-sm default next disabled");
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
        function previous(){
			clearTimeout(s);
			if(t > 0){
				t--;
				if($(window).width()<=768 ){
    				dom.children(".amazon_scroller_mask").find("ul").animate({
    	                left: '+='+(110+10)
    	            },0);
    				}else if(($(window).width()<=1024) && ($(window).width() > 768) ){
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
        function next(){
        	//alert(1)
            play();
        }
        function play(){
            clearTimeout(s);
			t++;
			var a = dom.find("ul:first").children("li").outerWidth(true)*w;
            if(t >= w+1){
				t = 0;
				dom.children(".amazon_scroller_mask").find("ul:first").css("left","0px");
				dom.children(".amazon_scroller_mask").find("ul:last").css("left",a);
				s=setTimeout(play,0);
            }else{
            	if($(window).width()<=768 ){
    				dom.children(".amazon_scroller_mask").find("ul").animate({
    	                left: '-='+(110+10)
    	            },0);
    				}else if(($(window).width()<=1024) && ($(window).width() > 768) ){
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
