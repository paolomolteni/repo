
var x = 1;

function f1(){
	
	var testPromise = new Promise((resolve, reject) => {
		
		if(x > 0){
			resolve("OK");
		}
		else{
			reject("ERROR");
		}
		
	});

	testPromise.then((result) => {
		
		console.log("F1 result: " + result);
		
		f2();
		
		console.log("F1 end fulfilled")
		
	});


	testPromise.catch((exception) => {
		
		console.log("F1 exception: " + exception);
		
	});
		
}

async function f2(){
	
	try{
		let resultF2 = await new Promise((resolve, reject) => {
			
			console.log("waiting...");
			
			setTimeout(() => {
				resolve("OK")
			}, 5000);
			
			setTimeout(() => {
				reject("ERROR")
			}, 6000);
			
		});
		
		console.log("F2 result: " + resultF2);
		
	}
	catch(exception){
		
		console.log("F2 exception: " + exception);
		
	}

}

//#################################################################################################
//#################################################################################################

f1();