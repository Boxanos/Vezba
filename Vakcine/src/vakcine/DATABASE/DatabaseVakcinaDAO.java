package vakcine.DATABASE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import vakcine.DAO.VakcinaDAO;
import vakcine.model.Vakcina;

public class DatabaseVakcinaDAO implements VakcinaDAO {

	private final Connection conn;
	
	public DatabaseVakcinaDAO(Connection conn) {
		this.conn = conn;
	}



	
	public Vakcina get(long id) throws Exception {
		Vakcina vakcine = null;
		
		String sql = "SELECT naziv,tip,temperturaCuvanja FROM vakcine WHERE id=?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, id);
			try(ResultSet rset = stmt.executeQuery()){
				if(rset.next()) {
					int kolona = 0;
					String naziv = rset.getString(++kolona);
					String tip = rset.getString(++kolona);
					int temperturaCuvanja = rset.getInt(++kolona);
					
					vakcine = new Vakcina(id, naziv, tip, temperturaCuvanja);
				}
				
			}
		}
		return vakcine;
	}
	
	
	@Override
	public Collection<Vakcina> getAll() throws Exception {
	
		Collection<Vakcina> vakcina = new ArrayList<>();
		
		String sql = "SELECT id, naziv, tip, temperturaCuvanja FROM vakcine";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					String naziv = rset.getString(++kolona);
					String tip = rset.getString(++kolona);
					int temperturaCuvanja = rset.getInt(++kolona);
				
					Vakcina vakcine = new Vakcina(id, naziv, tip, temperturaCuvanja);
					vakcina.add(vakcine);
					
				}
			}
		}
		return vakcina;
	}



}
