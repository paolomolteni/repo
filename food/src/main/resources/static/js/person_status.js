var personStatusComponent = new Vue({
	el: '#personStatusDiv',
	mixins: [sharedFunction],
  	data: {
  		listPerson:[],
    	listPersonStatus:[],
    	personStatusSelected:{
    		id:null,
    		idPerson:null,
    		description:null,
    		date:null
    	},
    	idPerson:null
  	},
  	created: function(){
  		this.readPersonStatus();
  		this.readPersons();
  	},
  	methods: {
  		cancell: function(){
  			this.personStatusSelected = {};
  		},
  		doUpdate: function(personStatus){
  			this.personStatusSelected = {
  				id: personStatus.id,
  				idPerson: personStatus.idPerson,
  				description: personStatus.description,
  				date: personStatus.date
  			};
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
    	}
  	}
})