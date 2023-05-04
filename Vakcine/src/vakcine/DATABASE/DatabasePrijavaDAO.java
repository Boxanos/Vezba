package vakcine.DATABASE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import vakcine.DAO.PrijavaDAO;
import vakcine.model.Prijava;


public class DatabasePrijavaDAO implements PrijavaDAO {
	
	private final Connection conn;


	
	public DatabasePrijavaDAO(Connection conn) {
		this.conn = conn;
	
	}

	@Override
	public void add(Prijava prijava) throws Exception {
		
		String sql = "INSERT INTO prijave (id, jmbg, imeIPrezime, vakcina, datum) VALUES (?, ?, ?, ?, ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			int kolona = 0;
			stmt.setLong(++kolona, prijava.getId());
			stmt.setString(++kolona, prijava.getJmbg());
			stmt.setString(++kolona, prijava.getImeIPrezime());
			stmt.setLong(++kolona, prijava.getVakcina().getId());
			stmt.setTimestamp(++kolona, Timestamp.valueOf(prijava.getDatumPrijave()));
			stmt.executeUpdate();
		
		}	

	}

}
