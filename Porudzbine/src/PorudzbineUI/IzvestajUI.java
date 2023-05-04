package PorudzbineUI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ftninformatika.jwd.modul1.util.Konzola;

import Porudzbine.model.Izvestaj;
import Porudzbine.model.Porudzbina;
import Porudzbine.model.Proizvod;
import PorudzbineDAO.PorudzbinaDAO;
import PorudzbineDAO.ProizvodDAO;

public class IzvestajUI {

	private static ProizvodDAO proizvodDAO;
	private static PorudzbinaDAO porudzbinaDAO;

	public static void setProizvodDAO(ProizvodDAO proizvodDAO) {
		IzvestajUI.proizvodDAO = proizvodDAO;
	}

	public static void setPorudzbinaDAO(PorudzbinaDAO porudzbinaDAO) {
		IzvestajUI.porudzbinaDAO = porudzbinaDAO;
	}

	public static void izvestavanje() {
		
		LocalDateTime pocetniDatum = Konzola.ocitajDateTime("Unesite pocetni datum pretragae: ");
		LocalDateTime krajnjiDatum = Konzola.ocitajDateTime("Unesite krajnji datum pretragae: ");

		try {
			List<Izvestaj> izvestaji = new ArrayList<>();
			Collection<Proizvod> poroizvodi = proizvodDAO.getAll();
			Collection<Porudzbina> porudzbine = porudzbinaDAO.getAll();
			
			for (Proizvod proizvod : poroizvodi) {
				long sifra = proizvod.getSifra();
				String naziv = proizvod.getNaziv();
				double prihod = 0;
				LocalDateTime datum = LocalDateTime.MIN;
				for (Porudzbina porudzbina : porudzbine) {
					if (porudzbina.isDatumUOpsegu(pocetniDatum, krajnjiDatum)) {
						
						if (porudzbina.getProizvod().getSifra() == proizvod.getSifra()) {
							if(proizvod.isBesplatnaDostava()) {
								prihod += proizvod.getCena();
							} else {
								prihod += proizvod.getCena() + 1000;
							}
							
							if (porudzbina.getDatumIVreme().isAfter(datum)) {
								datum = porudzbina.getDatumIVreme();
							}
						}
					}
				}
				
				Izvestaj stavka = new Izvestaj(sifra, naziv, prihod, datum);
				izvestaji.add(stavka);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske! ");
		}
	}
	
	
}
