
let vet = [1,2,3];

vet.forEach( v => {
	
	console.log(v);
	
});


let test = (a, b) => {
	
	let sum = a + b;
	
	console.log(`Somma: ${sum}`);
	
};

test(5,7);

// Promise

const promise = new Promise((resolve, reject) => {
  setTimeout(() => {
    resolve("data back from the server");
  }, 3000);

  setTimeout(() => {
    reject("no data back from the server, there was an error");
  }, 5000);
});

promise.then(response => {
  console.log(response);
}).catch(error => {
  console.log(error);
})

class Person {
  constructor(name, lastName) {
    this.name = name;
    this.lastName = lastName;
  }
  
  getName() {
    return this.name;
  }
  
}

var p = new Person("Jonh","Doe");
console.log(p.getName());