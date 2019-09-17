import React from 'react';

class FilterBox extends React.Component {
	
	constructor(props) {
		super(props);
		this.state = {
			nameFilter:""
		};

		this.handleChange = this.handleChange.bind(this);
    	this.clickEvent = this.clickEvent.bind(this);
  	}

	clickEvent(event){
		alert(this.state.nameFilter);
	}

 	handleChange(event) {
    	this.setState({nameFilter: event.target.value});
  	}

	render() {
		return(
			<div>			
				<input type="text" name="nameFilter" value={this.state.nameFilter} onChange={this.handleChange} />
				<button onClick={this.clickEvent}>PREMI</button>
			</div>);
	}
}

export default FilterBox;
