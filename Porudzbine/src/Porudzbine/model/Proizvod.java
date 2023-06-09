package Porudzbine.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Proizvod {
	
	private long id;
	
	private long sifra;
	private String naziv;
	private double cena;
	private boolean besplatnaDostava;
	
	private final Set<Porudzbina> porudzbine = new HashSet<>();

	public Proizvod(long id, long sifra, String naziv, double cena, boolean besplatnaDostava) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.cena = cena;
		this.besplatnaDostava = besplatnaDostava;
	}

	@Override
	public int hashCode() {
		return Objects.hash(besplatnaDostava, cena, id, naziv, porudzbine, sifra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proizvod other = (Proizvod) obj;
		return besplatnaDostava == other.besplatnaDostava
				&& Double.doubleToLongBits(cena) == Double.doubleToLongBits(other.cena) && id == other.id
				&& Objects.equals(naziv, other.naziv) && Objects.equals(porudzbine, other.porudzbine)
				&& Objects.equals(sifra, other.sifra);
	}

	@Override
	public String toString() {
		return "Proizvod [id=" + id + ", sifra=" + sifra + ", naziv=" + naziv + ", cena=" + cena + ", besplatnaDostava="
				+ besplatnaDostava +  "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSifra() {
		return sifra;
	}

	public void setSifra(long sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public boolean isBesplatnaDostava() {
		return besplatnaDostava;
	}

	public void setBesplatnaDostava(boolean besplatnaDostava) {
		this.besplatnaDostava = besplatnaDostava;
	}

	public Set<Porudzbina> getPorudzbine() {
		return porudzbine;
	}
	
	
	public void addPorudzbina(Porudzbina porudzbina) {
		this.porudzbine.add(porudzbina);
		porudzbina.setProizvod(this);
	}
	
	public void addAllPorudzbine (Collection<Porudzbina> porudzbine) {
		this.porudzbine.addAll(porudzbine);
		for(Porudzbina porudzbina: porudzbine) {
			porudzbina.setProizvod(this);
		}
	}
	
	public void removePorudzbina(Porudzbina porudzbina) {
		porudzbina.setProizvod(null);
		porudzbine.remove(porudzbina);
	}
	
	public void removeAllPorudzbine() {
		for(Porudzbina porudzbina: porudzbine) {
			porudzbina.setProizvod(null);
		}
		this.porudzbine.clear();
	}

}