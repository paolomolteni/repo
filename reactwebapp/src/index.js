import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import FilterBox from './filter';

const divStyle = {
  border: '1px solid black',
  width: '100%',
  height: '200px'
};

const mockPeople = [
	{"id":1, "name":"Paolo", "lastName": "Molteni"}	
];

class PersonList extends React.Component {


	constructor(props) {
		super(props);
		this.state = {
			people:[]
		};

  	}

	componentDidMount(){

		var myInit = { method: 'GET' };
	
		const promise = fetch('http://localhost:8080/medicine/person/list', myInit).then(function(response) {
			return response.json();
		});

		promise.then((json) => {
			this.setState({ people: json });
		},
		(error) => {
			this.setState({ people: mockPeople });
			console.log("Errore generico: " + error);
		});
	}

	render(){
		return(
			<table style={divStyle}>
			<tbody>
			{this.state.people.map((person) => {
				return <tr key={person.id}>
							<td>{person.id}</td>
							<td>{person.name}</td>
							<td>{person.lastName}</td>
						</tr>
			})}
			</tbody>
			</table>
		);	
	}
	
}

ReactDOM.render(<div><FilterBox/><PersonList/></div>, document.getElementById('root'));