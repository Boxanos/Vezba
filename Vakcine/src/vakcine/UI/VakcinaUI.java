package vakcine.UI;

import java.util.Collection;
import vakcine.DAO.VakcinaDAO;
import vakcine.model.Vakcina;


public class VakcinaUI {
	
	private static VakcinaDAO vakcinaDAO;
	
	public static void setVakcinaDAO(VakcinaDAO vakcinaDAO) {
		VakcinaUI.vakcinaDAO = vakcinaDAO;
	}

	

	public static void prikazSvih() {
		
		try {
			Collection<Vakcina> vakcina = vakcinaDAO.getAll();

			System.out.println();
			for(Vakcina itVakcine: vakcina)
			System.out.println(itVakcine);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	
		
	}



	public static Vakcina pretragaVakcine(long id) {
		Vakcina vakcina = null;
		try {
			vakcina = vakcinaDAO.get(id);
			if (vakcina == null) {
				System.out.println("Vakcina ne postoji sa IDem: " + id);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vakcina;

	}






	
	
	
}
