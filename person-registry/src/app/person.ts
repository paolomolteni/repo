import { Car } from './car';

export class Person {
	
	id: number;
  
	name: string;
  
	lastName: string;
  
	age: number;
	
	cars: Car[];
  
	constructor(){}
  
	/*constructor(id : number, name: string, lastName: string, age: number) {
		this.id = id;
        this.name = name;
		this.lastName = lastName;
		this.age = age;
	}*/
	  
}