var analyticappServices = angular.module('analyticappServices', [])

analyticappServices.service('anchorSmoothScroll', function(){
    
    this.scrollTo = function(eID) {
        
        var startY = currentYPosition();
        var stopY = elmYPosition(eID);
        var distance = stopY > startY ? stopY - startY : startY - stopY;
        if (distance < 100) {
            scrollTo(0, stopY); return;
        }
        var speed = Math.round(distance / 100);
        if (speed >= 20) speed = 20;
        var step = Math.round(distance / 25);
        var leapY = stopY > startY ? startY + step : startY - step;
        var timer = 0;
        if (stopY > startY) {
            for ( var i=startY; i<stopY; i+=step ) {
                setTimeout("window.scrollTo(0, "+leapY+")", timer * speed);
                leapY += step; if (leapY > stopY) leapY = stopY; timer++;
            } return;
        }
        for ( var i=startY; i>stopY; i-=step ) {
            setTimeout("window.scrollTo(0, "+leapY+")", timer * speed);
            leapY -= step; if (leapY < stopY) leapY = stopY; timer++;
        }
        
        function currentYPosition() {
            // Firefox, Chrome, Opera, Safari
            if (self.pageYOffset) return self.pageYOffset;
            // Internet Explorer 6 - standards mode
            if (document.documentElement && document.documentElement.scrollTop)
                return document.documentElement.scrollTop;
            // Internet Explorer 6, 7 and 8
            if (document.body.scrollTop) return document.body.scrollTop;
            return 0;
        }
        
        function elmYPosition(eID) {
            var elm = document.getElementById(eID);
            var y = elm.offsetTop;
            var node = elm;
            while (node.offsetParent && node.offsetParent != document.body) {
                node = node.offsetParent;
                y += node.offsetTop;
            } return y;
        }

    };
    
});


analyticappServices.service('successAndErrorMessageHandlingService', function(anchorSmoothScroll){
    
	this.resetMessageObject=function(messageRefObj){
		messageRefObj.message="";
		messageRefObj.showSuccessMessage=false;
		messageRefObj.showErrorMessage=false;
	};
	
    this.handleSuccessAndErrorMessage = function(returnData, messageRefObj, customSuccessMessage, customErrorMessage) {
    	if(!returnData.isError){
    		messageRefObj.message=customSuccessMessage;
			messageRefObj.showSuccessMessage=true;
			messageRefObj.showErrorMessage=false;
			anchorSmoothScroll.scrollTo(messageRefObj.successMessageDivId);
		 }else{
			messageRefObj.message=customErrorMessage;
			messageRefObj.showSuccessMessage=false;
			messageRefObj.showErrorMessage=true;
			anchorSmoothScroll.scrollTo(messageRefObj.errorMessageDivId);
		}
    	if(returnData.messages && returnData.messages.length>0){
			 messageRefObj.message=returnData.messages[0].text;
		}
    };
    
});

//the name fixing directive
analyticappServices.directive('fixRepeatedModelName', function () {
    return {
        link: function (scope, element, attrs, ngModelCtrl) {
            if (!attrs.name) return;
            ngModelCtrl.$name = attrs.name;
        },
        priority: '-100',
        require: 'ngModel'
    };
});