var foodEatenComponent = new Vue({
	el: '#foodEatenDiv',
	mixins: [sharedFunction],
  	data: {
  		listPerson:[],
    	listFoodEaten:[],
    	foodEatenSelected:{
    		id:null,
    		idPerson:null,
    		meal:null,
    		date:null
    	},
    	idPerson:null
  	},
  	created: function(){
  		this.readFoodEaten();
  		this.readPersons();
  	},
  	methods: {
  		cancell: function(){
  			this.foodEatenSelected = {};
  		},
  		doUpdate: function(foodEaten){
  			this.foodEatenSelected = {
  				id: foodEaten.id,
  				idPerson: foodEaten.idPerson,
  				meal: foodEaten.meal,
  				date: foodEaten.date
  			};
  		},
  		deleteFoodEaten : function(foodEaten){
      		
      		var confirmDelete = confirm("Eliminare il pasto?");
      		
      		if(confirmDelete == false){
      			return;
      		}
      		
      		var myInit = { 
      			method: 'GET',
      			headers: {
      				'Accept': 'application/json',
      				'Content-Type': 'application/json'
    			}
      		};
			
			var url = "/food/food/delete?id=" + foodEaten.id;
			
			const promise = fetch(url, myInit).then(function(response) {

				console.log("http status: " + response.status);
				console.log("http status: " + response.ok);

				return response.json();
			});

			promise.then((json) => {
				console.log("Stato eliminazione: " + json.success);
				this.foodEatenSelected = {};
				this.readFoodEaten();
			},
			(error) => {
				console.log("Errore generico: " + error);
			});
  		},
    	createOrUpdateFoodEaten: function () {
      		
      		if(!this.checkField(this.foodEatenSelected.meal) || !this.checkField(this.foodEatenSelected.date)){
      			alert("Verificare i parametri di input");
      			return;
      		}
      		
      		var myInit = { 
      			method: 'POST',
      			headers: {
      				'Accept': 'application/json',
      				'Content-Type': 'application/json'
    			},
    			body: JSON.stringify({id: this.foodEatenSelected.id, idPerson: this.foodEatenSelected.idPerson, meal: this.foodEatenSelected.meal, date: this.foodEatenSelected.date})
      		};
			
			var url = "/food/food/insert";
			
			const promise = fetch(url, myInit).then(function(response) {

				console.log("http status: " + response.status);
				console.log("http status: " + response.ok);

				return response.json();
			});

			promise.then((json) => {
				console.log("Stato salvataggio: " + json.success);
				this.foodEatenSelected = {};
				this.readFoodEaten();
			},
			(error) => {
				console.log("Errore generico: " + error);
			});
      		
      		
    	},
    	readFoodEaten: function(){
    	
    		this.listFoodEaten = [];
    	
    		var myInit = { 
      			method: 'GET',
      			headers: {
      				'Accept': 'application/json',
      				'Content-Type': 'application/json'
    			}
      		};
      		
      		var url = "/food/food/get";
			
			const promise = fetch(url, myInit).then(function(response) {
				console.log("http status: " + response.status);
				console.log("http status: " + response.ok);
				return response.json();
			});	
		
			promise.then((json) => {
		
				json.data.forEach(foodEaten => this.listFoodEaten.push(foodEaten));
		
			},
			(error) => {
				console.log("Errore generico: " + error);
			});
    	}
  	}
})