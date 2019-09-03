export class Medicine {

	public date: string;

	public name: string;

	public description: string;

	public id: number;

	public dateExpiry: string;

	public dateExpiryWhenOpened: string;

	public cause: string;

	public personId: number;

	constructor(date: string, name: string, description: string, dateExpiry: string, dateExpiryWhenOpened: string, cause: string, personId: number) {
		this.date = date;
		this.name = name;
		this.description = description;
		this.dateExpiry = dateExpiry;
		this.dateExpiryWhenOpened = dateExpiryWhenOpened;
		this.cause = cause;
		this.personId = personId;
	}

}
