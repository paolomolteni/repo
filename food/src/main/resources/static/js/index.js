var application = new Vue({
	el: '#application',
	data: {
		isPersonToShow: false,
		isFoodEatenToShow: false,
		isPersonStatusToShow: false,
		//#####################################
    	listPerson:[],
    	personSelected:{
    		name:"",
    		lastName:""
    	},
    	currentPagePerson: 1,
    	perPagePerson: 2,
    	//######################################
    	listPersonStatus:[],
    	personStatusSelected:{
    		id:null,
    		idPerson:null,
    		description:null,
    		date:null
    	},
    	idPerson:null,
    	currentPagePersonStatus: 1,
    	perPagePersonStatus: 2,
    	dateI: null,
    	timeI: null,
    	//######################################
    	listFoodEaten:[],
    	foodEatenSelected:{
    		id:null,
    		idPerson:null,
    		meal:null,
    		date:null
    	},
    	currentPageFoodEaten: 1,
    	perPageFoodEaten: 2
    	//######################################
  	},
  	computed: {
  		rowsPerson() {
  			return this.listPerson.length;
  		},
  		getPersonListToShow(){
  			// Calculate start and end elements
  			let pageStart = this.perPagePerson * (this.currentPagePerson-1);
  			let pageEnd = this.perPagePerson * (this.currentPagePerson-1) + (this.perPagePerson);
  			// Cut the list
  			return this.listPerson.slice(pageStart, pageEnd);
  		},
  		rowsPersonStatus() {
  			return this.listPersonStatus.length;
  		},
  		getPersonStatusListToShow(){
  			// Calculate start and end elements
  			let pageStart = this.perPagePersonStatus * (this.currentPagePersonStatus-1);
  			let pageEnd = this.perPagePersonStatus * (this.currentPagePersonStatus-1) + (this.perPagePersonStatus);
  			// Cut the list
  			return this.listPersonStatus.slice(pageStart, pageEnd);
  		},
  		rowsFoodEaten() {
  			return this.listFoodEaten.length;
  		},
  		getFoodEatenListToShow(){
  			// Calculate start and end elements
  			let pageStart = this.perPageFoodEaten * (this.currentPageFoodEaten-1);
  			let pageEnd = this.perPageFoodEaten * (this.currentPageFoodEaten-1) + (this.perPageFoodEaten);
  			// Cut the list
  			return this.listFoodEaten.slice(pageStart, pageEnd);
  		}
  	},
  	created: function(){
  		this.readPersons();
  		this.readPersonStatus();
  		this.readFoodEaten();
  	},
  	methods: {
  		showPerson: function(){
  			this.isPersonToShow = true;
  			this.isFoodEatenToShow = false;
  			this.isPersonStatusToShow = false;
  		},
  		showFoodEaten: function(){
  			this.isPersonToShow = false;
  			this.isFoodEatenToShow = true;
  			this.isPersonStatusToShow = false;
  		},
  		showStatus: function(){
  			this.isPersonToShow = false;
  			this.isFoodEatenToShow = false;
  			this.isPersonStatusToShow = true;
  		},
  		cancellPerson: function(){
  			this.personSelected = {};
  		},
  		doUpdatePerson: function(person){
  			this.personSelected = {
  				id: person.id,
  				name: person.name,
  				lastName: person.lastName
  			};
  		},
  		deletePerson : function(person){
      		
      		var confirmDelete = confirm("Eliminare la persona?");
      		
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
			
			var url = "/food/person/delete?id=" + person.id;
			
			const promise = fetch(url, myInit).then(function(response) {

				console.log("http status: " + response.status);
				console.log("http status: " + response.ok);

				return response.json();
			});

			promise.then((json) => {
				console.log("Stato eliminazione: " + json.success);
				this.personSelected = {};
				this.readPersons();
			},
			(error) => {
				console.log("Errore generico: " + error);
			});
  		},
    	createOrUpdatePerson: function () {
      		
      		if(!this.checkField(this.personSelected.name) || !this.checkField(this.personSelected.lastName)){
      			alert("Verificare i parametri di input");
      			return;
      		}
      		
      		var myInit = { 
      			method: 'POST',
      			headers: {
      				'Accept': 'application/json',
      				'Content-Type': 'application/json'
    			},
    			body: JSON.stringify({id: this.personSelected.id, name: this.personSelected.name, lastName: this.personSelected.lastName})
      		};
			
			var url = "/food/person/insert";
			
			const promise = fetch(url, myInit).then(function(response) {

				console.log("http status: " + response.status);
				console.log("http status: " + response.ok);

				return response.json();
			});

			promise.then((json) => {
				console.log("Stato salvataggio: " + json.success);
				this.personSelected = {};
				this.readPersons();
			},
			(error) => {
				console.log("Errore generico: " + error);
			});
      		
      		
    	},
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
    	//############################################################################################
    	//#########################  PERSON STATUS ###################################################
    	cancellPersonStatus: function(){
  			this.personStatusSelected = {};
  		},
  		doUpdatePersonStatus: function(personStatus){
  			this.personStatusSelected = {
  				id: personStatus.id,
  				idPerson: personStatus.idPerson,
  				description: personStatus.description,
  				date: personStatus.date
  			};
  			
  			let d = new Date(personStatus.date);
  			this.dateI = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+(d.getDate() < 10 ? '0' : '') + d.getDate();
  			this.timeI = d.getHours()+":"+d.getMinutes();
  		},
  		deletePersonStatus : function(personStatus){
      		
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
			
			var url = "/food/person/status/delete?id=" + personStatus.id;
			
			const promise = fetch(url, myInit).then(function(response) {

				console.log("http status: " + response.status);
				console.log("http status: " + response.ok);

				return response.json();
			});

			promise.then((json) => {
				console.log("Stato eliminazione: " + json.success);
				this.personStatusSelected = {};
				this.readPersonStatus();
			},
			(error) => {
				console.log("Errore generico: " + error);
			});
  		},
    	createOrUpdatePersonStatus: function () {
      		
      		if(!this.checkField(this.personStatusSelected.description) || !this.checkField(this.personStatusSelected.date)){
      			alert("Verificare i parametri di input");
      			return;
      		}
      		
      		this.personStatusSelected.date = this.dateI+" "+this.timeI;
      		
      		var myInit = { 
      			method: 'POST',
      			headers: {
      				'Accept': 'application/json',
      				'Content-Type': 'application/json'
    			},
    			body: JSON.stringify({id: this.personStatusSelected.id, idPerson: this.personStatusSelected.idPerson, description: this.personStatusSelected.description, date: this.personStatusSelected.date})
      		};
			
			var url = "/food/person/status/insert";
			
			const promise = fetch(url, myInit).then(function(response) {

				console.log("http status: " + response.status);
				console.log("http status: " + response.ok);

				return response.json();
			});

			promise.then((json) => {
				console.log("Stato salvataggio: " + json.success);
				this.personStatusSelected = {};
				this.readPersonStatus();
				this.dateI = null;
				this.timeI = null;
			},
			(error) => {
				console.log("Errore generico: " + error);
			});
      		
      		
    	},
    	readPersonStatus: function(){
    	
    		this.listPersonStatus = [];
    	
    		var myInit = { 
      			method: 'GET',
      			headers: {
      				'Accept': 'application/json',
      				'Content-Type': 'application/json'
    			}
      		};
      		
      		var url = "/food/person/status/get";
			
			const promise = fetch(url, myInit).then(function(response) {
				console.log("http status: " + response.status);
				console.log("http status: " + response.ok);
				return response.json();
			});	
		
			promise.then((json) => {
		
				json.data.forEach(personStatus => this.listPersonStatus.push(personStatus));
		
			},
			(error) => {
				console.log("Errore generico: " + error);
			});
    	},
    	//############################################################################################
    	//########################## FOOD EATEN ######################################################
    	cancellFoodEaten: function(){
  			this.foodEatenSelected = {};
  		},
  		doUpdateFoodEaten: function(foodEaten){
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
	
});