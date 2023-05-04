package PorudzbineDAO;

import java.util.Collection;

import Porudzbine.model.Porudzbina;

public interface PorudzbinaDAO {

	public Collection<Porudzbina> getAll() throws Exception;
	public void add (Porudzbina Porudzbina) throws Exception;
}
