
function testApi(path){

	let myHeaders = new Headers();
	myHeaders.append("Janus-Authorization", "a00c36aa-04bb-4a49-b8d5-3c91557244a5");
	myHeaders.append("Content-Type", "application/json");

	let opts = {
		method: 'GET',
		headers: myHeaders,
	};
	
	fetch(path, opts).then((response) => {
		
		return response.json();
		
	})
	.then((data) => {
		
		data.page.items.forEach((item) => {
			
			console.log(item);
			
		});
		
	});
	
}

//#################################################################################################
//#################################################################################################

testApi("http://localhost:8080/janus/api/customer?type=PERSON&page=1&pageSize=10");