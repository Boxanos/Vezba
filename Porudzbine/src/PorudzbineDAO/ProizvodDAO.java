package PorudzbineDAO;

import java.util.Collection;

import Porudzbine.model.Proizvod;

public interface ProizvodDAO {

	public Proizvod get(long id) throws Exception;
	public Collection<Proizvod> getAll() throws Exception;
}
