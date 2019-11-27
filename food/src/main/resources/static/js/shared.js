var sharedFunction = Vue.mixin({
	data: function () {
	    return {
	    }
	},
	methods: {
		readPersons: function(){
	    	
    		this.listPerson = [];
    	
    		var myInit = { 
      			method: 'GET',
      			headers: {
      				'Accept': 'application/json',
      				'Content-Type': 'application/json'
    			}
      		};
      		
      		var url = "/food/person/get";
			
			const promise = fetch(url, myInit).then(function(response) {
				console.log("http status: " + response.status);
				console.log("http status: " + response.ok);
				return response.json();
			});	
		
			promise.then((json) => {
		
				json.data.forEach(person => this.listPerson.push(person));
		
			},
			(error) => {
				console.log("Errore generico: " + error);
			});
    	},
    	checkField: function(field){
    		if(field && field != ""){
    			return true;
    		}
    		return false;
    	},
    	showPerson: function(){
    		this.showDivPerson = true;
    	},
    	showMeal: function(){
    		this.showDivPerson = false;
    	},
    	showStatus: function(){
    		
    	}
    	
	}		
});