package Porudzbine.model;

import java.time.LocalDateTime;

public class Izvestaj {

	private long sifraProizvoda;
	
	private String nazivProizvoda;
	private double prihod;
	private LocalDateTime datumPoslednjeProdaje;
	
	public Izvestaj(long sifraProizvoda, String nazivProizvoda, double prihod, LocalDateTime datumPoslednjeProdaje) {
		super();
		this.sifraProizvoda = sifraProizvoda;
		this.nazivProizvoda = nazivProizvoda;
		this.prihod = prihod;
		this.datumPoslednjeProdaje = datumPoslednjeProdaje;
	}

	@Override
	public String toString() {
		return "Izvestaj [sifraProizvoda=" + sifraProizvoda + ", nazivProizvoda=" + nazivProizvoda + ", prihod="
				+ prihod + ", datumPoslednjeProdaje=" + datumPoslednjeProdaje + "]";
	}
	
	public int comparePrihod(Izvestaj stavka1, Izvestaj stavka2) {
		
		return -Double.compare(stavka1.prihod, stavka2.prihod);
	}
	
}
