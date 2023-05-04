package vakcine.UI;

import java.time.LocalDateTime;
import java.util.Collection;

import com.ftninformatika.jwd.modul1.util.Konzola;

import vakcine.DAO.PrijavaDAO;
import vakcine.DAO.VakcinaDAO;
import vakcine.model.Prijava;
import vakcine.model.Vakcina;




public class PrijavaUI {
	
	private static PrijavaDAO prijavaDAO;
	private static VakcinaDAO vakcinaDAO;
	
	
	public static void setPrijavaDAO(PrijavaDAO prijavaDAO) {
		PrijavaUI.prijavaDAO = prijavaDAO;
	}

	public static void setVakcinaDAO(VakcinaDAO vakcinaDAO) {
		PrijavaUI.vakcinaDAO = vakcinaDAO;
	}

	public static void dodavanjePrijave() {
		String jmbg = "";
		
		while (jmbg.equals("")) {
			jmbg = Konzola.ocitajString("Unesite jmbg ");
		}
		boolean uspeh = pretragaJmbga(jmbg);
		if (uspeh == false) {
			return;
		}
		String ime = "";
		while (ime.equals("")) {
			ime = Konzola.ocitajString("Unesite ime pacijenta: ");
		}
		VakcinaUI.prikazSvih();
		long idVakcine = Konzola.ocitajLong("Unesite id zeljene Vakcine: ");
		Vakcina vakcina = VakcinaUI.pretragaVakcine(idVakcine);
		if (vakcina == null) {
			return;
		}
		LocalDateTime datum = LocalDateTime.now();
		Prijava prijava = new Prijava(jmbg, ime, datum, vakcina);
		vakcina.addPrijavu(prijava);
		try {
			prijavaDAO.add(prijava);
			System.out.println("Uspesno ste dodali novu prijavu!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static boolean pretragaJmbga(String jmbg) {
		boolean uspesno = false;
		try {
			Collection<Vakcina> vakcine = vakcinaDAO.getAll();
			for (Vakcina vakcina : vakcine) {
				for (Prijava prijava : vakcina.getPrijave()) {
					if (prijava.getJmbg().equals(jmbg)) {
						System.out.println("Prijava vec postoji za JMBG:" + jmbg);
						uspesno = false;
					} else {
						uspesno = true;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uspesno;
	}

	

	public static void prikazSvihSaVakcionm() {
	
		try {
			Collection<Prijava> prijava = prijavaDAO.getAll();

			System.out.println();
			for (Prijava itPriajve: prijava) {
				System.out.println(itPriajve);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	
	}

}
