
analyticapp.directive('hideXpanel', function () {
    return {
    	restrict: 'A',
        link: function (scope, element, attrs) {
        	element.bind('click', function () {
        		console.log("hideXpanel");
        		if($(".page-sidebar .x-navigation").hasClass("x-navigation-minimized")){
                    $(".page-container").removeClass("page-navigation-toggled");
                    x_navigation_minimize("open");
                }else{            
                    $(".page-container").addClass("page-navigation-toggled");
                    x_navigation_minimize("close");            
                }
                onresize();
            });
        	
        	function x_navigation_minimize(action){
        	    
        	    if(action == 'open'){
        	        $(".page-container").removeClass("page-container-wide");
        	        $(".page-sidebar .x-navigation").removeClass("x-navigation-minimized");
        	        $(".x-navigation-minimize").find(".fa").removeClass("fa-indent").addClass("fa-dedent");
        	        $(".page-sidebar.scroll").mCustomScrollbar("update");
        	    }
        	    
        	    if(action == 'close'){
        	        $(".page-container").addClass("page-container-wide");
        	        $(".page-sidebar .x-navigation").addClass("x-navigation-minimized");
        	        $(".x-navigation-minimize").find(".fa").removeClass("fa-dedent").addClass("fa-indent");
        	        $(".page-sidebar.scroll").mCustomScrollbar("disable",true);
        	    }
        	    
        	    $(".x-navigation li.active").removeClass("active");
        	    
        	}
        }
    };
});