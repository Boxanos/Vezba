package PorudzbineUI;

import java.util.Collection;

import Porudzbine.model.Proizvod;
import PorudzbineDAO.ProizvodDAO;

public class ProizvodUI {
	
	private static ProizvodDAO proizvodDAO;

	public static void setProizvodDAO(ProizvodDAO proizvodDAO) {
		ProizvodUI.proizvodDAO = proizvodDAO;
	}

	public static void prikazSvih() {
		
		try {
			Collection<Proizvod> proizvodi = proizvodDAO.getAll();
			System.out.println();
			for(Proizvod proizvod: proizvodi) {
				System.out.println(proizvod);
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			System.out.println("Doslo je do greske!");
		}
	}

	
	public static Proizvod pretragaProizovda(long id) {
		
		Proizvod proizvod = null;
		
		try {
			proizvod = proizvodDAO.get(id);
			
			if (proizvod == null) {
				System.out.println("Proizvod sa zdatim id nije pronadjen! ");
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return proizvod;
	}
	

}
