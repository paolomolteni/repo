
var person = new Vue({
	el: '#app',
	mixins: [sharedFunction],
  	data: {
    	listPerson:[],
    	personSelected:{
    		name:"",
    		lastName:""
    	},
    	showDivPerson: false
  	},
  	created: function(){
  		
  		this.readPersons();
      	
  	},
  	methods: {
  		cancell: function(){
  			this.personSelected = {};
  		},
  		doUpdate: function(person){
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
      		
      		
    	}

  	}
})