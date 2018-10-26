
function testApi(path){

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
		
		data.results.forEach((item) => {
			
			console.log(item);
			
		});
		
	});
	
}

//#################################################################################################
//#################################################################################################

testApi("https://randomuser.me/api/?results=10");