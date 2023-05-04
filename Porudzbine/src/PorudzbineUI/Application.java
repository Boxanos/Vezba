package PorudzbineUI;

import java.sql.Connection;
import java.sql.DriverManager;

import com.ftninformatika.jwd.modul1.util.Meni;
import com.ftninformatika.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija;

import Porudzbine.Database.DatabasePorudzbinaDAO;
import Porudzbine.Database.DatabaseProizvodDAO;
import PorudzbineDAO.PorudzbinaDAO;
import PorudzbineDAO.ProizvodDAO;

public class Application {
	


		private static void initDatabase() throws Exception {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/porudzbine?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade", 
					"root", 
					"root");

			ProizvodDAO proizvodDAO = new DatabaseProizvodDAO(conn);
			PorudzbinaDAO proudzbinaDAO = new DatabasePorudzbinaDAO(conn, proizvodDAO);

			ProizvodUI.setProizvodDAO(proizvodDAO);
			PorudzbinaUI.setPorudzbinaDAO(proudzbinaDAO);
			IzvestajUI.setProizvodDAO(proizvodDAO);
			IzvestajUI.setPorudzbinaDAO(proudzbinaDAO);

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
			Meni.pokreni("Porudzbine", new StavkaMenija[] {
				new IzlaznaStavkaMenija("Izlaz"),
				new FunkcionalnaStavkaMenija("Prikaz svih proizvoda") {

					@Override
					public void izvrsi() { ProizvodUI.prikazSvih(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Prikaz svih porudžbina ") {

					@Override
					public void izvrsi() { PorudzbinaUI.prikazSvihPorudzbina(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Dodavanje porudzbine") {

					@Override
					public void izvrsi() { PorudzbinaUI.dodavanje(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Izvestavanje") {

					@Override
					public void izvrsi() { IzvestajUI.izvestavanje(); }
					
				}
			});
		}

	}


