package PorudzbineUI;

import java.time.LocalDateTime;
import java.util.Collection;

import com.ftninformatika.jwd.modul1.util.Konzola;

import Porudzbine.model.Porudzbina;
import Porudzbine.model.Proizvod;
import PorudzbineDAO.PorudzbinaDAO;

public class PorudzbinaUI {
	
	private static PorudzbinaDAO porudzbinaDAO;

	public static void setPorudzbinaDAO(PorudzbinaDAO porudzbinaDAO) {
		PorudzbinaUI.porudzbinaDAO = porudzbinaDAO;
	}

	public static void prikazSvihPorudzbina() {

		try {
			Collection<Porudzbina> porudzbine = porudzbinaDAO.getAll();
			String Headher = String.format("%-10s %-20s %-20s %-15s %-20s", "ID", "Datum i Vreme", "Ulica", "Broj", "Proizvod");
			System.out.println(Headher);
			System.out.println("========== ==================== ==================== =============== ====================");
			for (Porudzbina porudzbina : porudzbine) {
				String foother = String.format("%-10s %-20s %-20s %-15s %-20s", porudzbina.getId(), porudzbina.getDatumIVreme(), porudzbina.getUlica(), porudzbina.getBroj(), porudzbina.getProizvod().getNaziv());
				System.out.println(foother);
				System.out.println("---------- -------------------- -------------------- --------------- --------------------");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void dodavanje() {
		
		ProizvodUI.prikazSvih();
		
		long id = Konzola.ocitajLong("Unesite id proizvoda");
		
		Proizvod proizvod = ProizvodUI.pretragaProizovda(id);
		if (proizvod == null) {
			return;
		}
		
		String ulica = "";
		while (ulica.equals("")) {
			ulica = Konzola.ocitajString("Unesite ulicu: ");
		}
		
		int broj = Konzola.ocitajInt("Unesite broj: ");
		LocalDateTime datum = LocalDateTime.now();
		
		Porudzbina porudzbina = new Porudzbina( datum, ulica, broj, proizvod);
		
		try {
			porudzbinaDAO.add(porudzbina);
			System.out.println("Uspesno ste dodali novu porudzbinu!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		}
	
	
	
	

}
