window.it_paolomolteni_vaadintest_MyJsComponent = function() {
    
	// Create the component
    var map = initMap(this.getElement().id);
    
    // Handle changes from the server-side
    this.onStateChange = function() {
    	console.log(this.getState().latitude);
    	console.log(this.getState().longitude);
    	
    	if(this.getState().latitude && this.getState().longitude){
    		addMarker(map, this.getState().latitude, this.getState().longitude);
    	}
    };
    
//    // Pass user interaction to the server-side
//    var self = this;
//    mycomponent.click = function() {
//        self.onClick(mycomponent.getValue());
//    };
};