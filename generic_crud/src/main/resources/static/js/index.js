Vue.component('star-rating', VueStarRating.default);

var application = new Vue({
	el: '#application',
	data: {
		isFilmToShow: false,
		//#####################################
    	listFilm:[],
    	filmSelected:{
    		titple:"",
    	},
    	currentPageFilm: 1,
    	perPageFilm: 10,
    	//######################################
  	},
  	computed: {
  		rowsFilm() {
  			return this.listFilm.length;
  		},
  		getFilmListToShow(){
  			// Calculate start and end elements
  			let pageStart = this.perPageFilm * (this.currentPageFilm-1);
  			let pageEnd = this.perPageFilm * (this.currentPageFilm-1) + (this.perPageFilm);
  			// Cut the list
  			return this.listFilm.slice(pageStart, pageEnd);
  		}
  	},
  	created: function(){
  		this.readFilms();
  	},
  	methods: {
  		showFilm: function(){
  			this.cancellFilm();
  			this.isFilmToShow = true;
  		},
  		cancellFilm: function(){
  			this.filmSelected = {};
  		},
  		doUpdateFilm: function(film){
  			this.filmSelected = {
  				id: film.id,
  				title: film.title,
  			};
  		},
  		deleteFilm : function(film){
      		
      		var confirmDelete = confirm("Eliminare il film?");
      		
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
			
			var url = "/film/film/delete?id=" + film.id;
			
			const promise = fetch(url, myInit).then(function(response) {

				console.log("http status: " + response.status);
				console.log("http status: " + response.ok);

				return response.json();
			});

			promise.then((json) => {
				console.log("Stato eliminazione: " + json.success);
				this.filmSelected = {};
				this.readFilms();
			},
			(error) => {
				console.log("Errore generico: " + error);
			});
  		},
    	createOrUpdateFilm: function () {
      		
      		if(!this.checkField(this.filmSelected.title)){
      			alert("Verificare i parametri di input");
      			return;
      		}
      		
      		var myInit = { 
      			method: 'POST',
      			headers: {
      				'Accept': 'application/json',
      				'Content-Type': 'application/json'
    			},
    			body: JSON.stringify({id: this.filmSelected.id, title: this.filmSelected.title})
      		};
			
			var url = "/film/film/insert";
			
			const promise = fetch(url, myInit).then(function(response) {

				console.log("http status: " + response.status);
				console.log("http status: " + response.ok);

				return response.json();
			});

			promise.then((json) => {
				console.log("Stato salvataggio: " + json.success);
				this.filmSelected = {};
				this.readFilms();
			},
			(error) => {
				console.log("Errore generico: " + error);
			});
      		
      		
    	},
    	readFilms: function(){
	    	
    		this.listFilm = [];
    	
    		var myInit = { 
      			method: 'GET',
      			headers: {
      				'Accept': 'application/json',
      				'Content-Type': 'application/json'
    			}
      		};
      		
      		var url = "/film/film/get/list";
			
			const promise = fetch(url, myInit).then(function(response) {
				console.log("http status: " + response.status);
				console.log("http status: " + response.ok);
				return response.json();
			});	
		
			promise.then((json) => {
				json.data.forEach(film => this.listFilm.push(film));
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
    	}
  	}
	
});
