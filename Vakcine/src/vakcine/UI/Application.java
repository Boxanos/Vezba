package vakcine.UI;



import java.sql.Connection;
import java.sql.DriverManager;

import com.ftninformatika.jwd.modul1.util.Meni;
import com.ftninformatika.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija;

import vakcine.DAO.PrijavaDAO;
import vakcine.DAO.VakcinaDAO;
import vakcine.DATABASE.DatabasePrijavaDAO;
import vakcine.DATABASE.DatabaseVakcinaDAO;


	public class Application {

		private static void initDatabase() throws Exception {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/vakcinisanje?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade", 
					"root", 
					"root");
			PrijavaDAO prijavaDAO = new DatabasePrijavaDAO(conn);
			VakcinaDAO vakcinaDAO = new DatabaseVakcinaDAO(conn);

			PrijavaUI.setPrijavaDAO(prijavaDAO);
			PrijavaUI.setVakcinaDAO(vakcinaDAO);
			VakcinaUI.setVakcinaDAO(vakcinaDAO);
			IzvestajUI.setVakcinaDAO(vakcinaDAO);
		}

		static {
			try {
				initDatabase();
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Greška pri povezivanju sa izvorom podataka!");
				
				System.exit(1);
			}
		}

		public static void main(String[] args) throws Exception {
			Meni.pokreni("Vakcine", new StavkaMenija[] {
				new IzlaznaStavkaMenija("Izlaz"),
				new FunkcionalnaStavkaMenija("Prikaz svih vakcina") {

					@Override
					public void izvrsi() { VakcinaUI.prikazSvih(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Dodavanje prijave") {

					@Override
					public void izvrsi() { PrijavaUI.dodavanjePrijave(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Prikaz svih prijava sa vakcinama") {

					@Override
					public void izvrsi() { PrijavaUI.prikazSvihSaVakcionm(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Izvestavanje") {

					@Override
					public void izvrsi() { IzvestajUI.izvestaj(); }
					
				}
			});
		}

	}



