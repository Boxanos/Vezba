package vakcine.DAO;

import java.util.Collection;

import vakcine.model.Vakcina;

public interface VakcinaDAO {
	
	public Vakcina get(long id) throws Exception;
	public Collection<Vakcina> getAll() throws Exception;
}