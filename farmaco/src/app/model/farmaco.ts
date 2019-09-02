export class Farmaco {

	public data: string;

	public nomeFarmaco: string;

	public descrizione: string;

	public id: number;

	constructor(data: string, nomeFarmaco: string, descrizione: string) {
		this.data = data;
		this.nomeFarmaco = nomeFarmaco;
		this.descrizione = descrizione;
	}

}
