$(document).ready(function () {
	var copy = document;
	var ele = parent.document;
	
	// manish
    $("canvas", copy).each(function(i) {
    	var sourceCanvasList = $("canvas", ele);
    	var sourceCanvas = sourceCanvasList.eq(i);
    	
    	var newCanvasContext = this.getContext('2d');
    	//size the new canvas to mirror the old canvas
    	this.width = sourceCanvas[0].width;
    	this.height = sourceCanvas[0].height;

    	//copy the old canvas onto the new canvas with drawImage();
    	newCanvasContext.drawImage(sourceCanvas[0], 0, 0, sourceCanvas[0].width, sourceCanvas[0].height);
    });
    
    $("svg", copy).each(function (i) {
    	if ($(this).attr("fit-to-page") == "true") {
    		var pagePixelWidth = 600;
    		var eleWidth = $("svg", ele).get(0).getBBox().width;
    		var scaleX = pagePixelWidth / eleWidth;          		
    		
    		// scale 
    		$(this).css("transform", "scale(" + scaleX + ",1)");
    		$(this).css("-ms-transform", "scale(" + scaleX + ",1)"); /* IE 9 */
    		$(this).css("-webkit-transform", "scale(" + scaleX + ",1)" ); /* Safari and Chrome */
    		$(this).css("-o-transform", "scale(" + scaleX + ",1)" ); /* Opera */
    		$(this).css("-moz-transform", "scale(" + scaleX + ",1)"); /* Firefox */
    		
    		// transform origin 
    		$(this).css("transform-origin", "0 0 0");
    		$(this).css("-ms-transform-origin", "0 0 0"); /* IE 9 */
    		$(this).css("-webkit-transform-origin", "0 0 0" ); /* Safari and Chrome */
    		$(this).css("-o-transform-origin", "0 0 0" ); /* Opera */
    		$(this).css("-moz-transform-origin", "0 0 0"); /* Firefox */
    	}
    });
});