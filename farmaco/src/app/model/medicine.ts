export class Medicine {

	public date: string;

	public name: string;

	public description: string;

	public id: number;

	public dateExpiry: string;

	public dateExpiryWhenOpened: string;

	constructor(date: string, name: string, description: string, dateExpiry: string, dateExpiryWhenOpened: string) {
		this.date = date;
		this.name = name;
		this.description = description;
		this.dateExpiry = dateExpiry;
		this.dateExpiryWhenOpened = dateExpiryWhenOpened;
	}

}
