export class Farmaco {

	public data: string;

	public nomeFarmaco: string;

	public descrizione: string;

	public id: number;

	public dataScadenza: string;

	public dataScadenzaAperto: string;

	constructor(data: string, nomeFarmaco: string, descrizione: string, dataScadenza: string, dataScadenzaAperto: string) {
		this.data = data;
		this.nomeFarmaco = nomeFarmaco;
		this.descrizione = descrizione;
		this.dataScadenza = dataScadenza;
		this.dataScadenzaAperto = dataScadenzaAperto;
	}

}
