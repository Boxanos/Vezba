package vakcine.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Prijava {
	
	private long id;
	
	private String jmbg;
	private String imeIPrezime;
	private LocalDateTime datumPrijave;
	 Vakcina vakcina;

	public Prijava(long id, String jmbg, String imeIPrezime,Vakcina vakcine,LocalDateTime datumPrijave ) {
		
		this.id = id;
		this.jmbg = jmbg;
		this.imeIPrezime = imeIPrezime;
		this.datumPrijave = datumPrijave;
		this.vakcina = vakcine;
		
	}

	public Prijava( String jmbg, String imeIPrezime,LocalDateTime datumPrijave,Vakcina vakcine ) {
		this.jmbg = jmbg;
		this.imeIPrezime = imeIPrezime;
		this.datumPrijave = datumPrijave;
		this.vakcina = vakcine;
			}

	@Override
	public String toString() {
		return "Prijave [id=" + id + ", jmbg=" + jmbg + ", imeIPrezime=" + imeIPrezime + ", datumPrijave="
				+ datumPrijave + ", vakcine=" + vakcina.getNaziv() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prijava other = (Prijava) obj;
		return id == other.id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getImeIPrezime() {
		return imeIPrezime;
	}

	public void setImeIPrezime(String imeIPrezime) {
		this.imeIPrezime = imeIPrezime;
	}

	public LocalDateTime getDatumPrijave() {
		return datumPrijave;
	}

	public void setDatumPrijave(LocalDateTime datumPrijave) {
		this.datumPrijave = datumPrijave;
	}

	public Vakcina getVakcina() {
		return vakcina;
	}

	public void setVakcina(Vakcina vakcina) {
		this.vakcina = vakcina;
		vakcina.prijave.add(this);
	}
	
	public boolean isDatumUOpsegu(LocalDateTime pocetni, LocalDateTime krajnji) {
		return datumPrijave.compareTo(pocetni) >= 0 && datumPrijave.compareTo(krajnji) <= 0;

	}

	
}
