
function getRandomUser(path){

	let myHeaders = new Headers();
	myHeaders.append("Content-Type", "application/json");

	let opts = {
		method: 'GET',
		headers: myHeaders,
	};
	
	fetch(path, opts).then((response) => {
		
		return response.json();
		
	})
	.then((data) => {
		
		data.results.forEach((user) => {
			
			console.log("Nation: " + user.nat);
			
			getRandomUserSameNation(user.nat);
			
		});
		
	});
	
}

function getRandomUserSameNation(nation){
	
	let getSameNationPromise = fetch("https://randomuser.me/api/?results=5&nat=" + nation);
	
	getSameNationPromise.then((response) => {
		
		return response.json();
		
	})
	.then((data) => {
		
		data.results.forEach((user) => {
					
			console.log(`${user.name.title} ${user.name.first} ${user.name.last} ==> ${user.nat}`);
			
		});
		
	});
	
}

//#################################################################################################
//#################################################################################################

getRandomUser("https://randomuser.me/api/?results=1");