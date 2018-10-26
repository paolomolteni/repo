//############################### PROMISE EXAMPLE #############################################

function resolveAfter2Seconds(x) { 
  return new Promise( (resolve, reject) => {
    
	
	setTimeout(() => {
      resolve(x);
    }, 2000);
	
	setTimeout(() => {
      reject(x);
    }, 3000);
	
  });
}

async function f1() {
	try {
		var x = await resolveAfter2Seconds(10);
		console.log(`Resolved promise: ${x}`);
	}
	catch(e) {
		console.log(`Caught exception: ${e}`);
	}
}

f1();

console.log("processo principale");


//############################### FETCH + PROMISE #############################################

const getRandomUsers = n => {
	const fetchRandomUsers = fetch(`https://randomuser.me/api/?results=${n}`)
	fetchRandomUsers.then(data => {
		data.json().then(randomUsers => {
			console.log(JSON.stringify(randomUsers.results.length));
			randomUsers.results.forEach(user => {
				const {gender, email, name : { first }, name : { last }} = user;
				console.log(`${gender} - ${email} - ${first} ${last}`);
			});
		})
	});
}

getRandomUsers(10);