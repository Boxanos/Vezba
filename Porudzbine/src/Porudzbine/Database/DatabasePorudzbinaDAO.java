package Porudzbine.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import Porudzbine.model.Porudzbina;
import Porudzbine.model.Proizvod;
import PorudzbineDAO.PorudzbinaDAO;
import PorudzbineDAO.ProizvodDAO;


public class DatabasePorudzbinaDAO implements PorudzbinaDAO {
	
	private final Connection conn;
	private final ProizvodDAO proizvodDAO;
	
	
	public DatabasePorudzbinaDAO(Connection conn, ProizvodDAO proizvodDAO) {
	
		this.conn = conn;
		this.proizvodDAO = proizvodDAO;
	}

	@Override
	public Collection<Porudzbina> getAll() throws Exception {
		
		Collection<Porudzbina> porudzbine = new ArrayList<>();
		
		String sql = "SELECT id, datumIVreme, ulica, broj, proizvodId FROM porudzbine";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long id = rset.getLong(++kolona);
					LocalDateTime datumIVreme = rset.getTimestamp(++kolona).toLocalDateTime();
					String ulica = rset.getString(++kolona);
					int broj = rset.getInt(++kolona);
					long proizvodId = rset.getLong(++kolona);
					Proizvod proizvod = proizvodDAO.get(proizvodId);
					Porudzbina porudzbina = new Porudzbina(id, datumIVreme, ulica, broj, proizvod);
					porudzbina.setProizvod(proizvod);
					porudzbine.add(porudzbina);
					
				}
			}
			return porudzbine;
		}
		
	
	}

	@Override
	public void add(Porudzbina porudzbina) throws Exception {
		
		String sql = "INSERT INTO porudzbine (datumIVreme, ulica, broj, proizvodId) VALUES (?, ?, ?, ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setTimestamp(++param, Timestamp.valueOf(porudzbina.getDatumIVreme()));
			stmt.setString(++param, porudzbina.getUlica());
			stmt.setInt(++param, porudzbina.getBroj());
			stmt.setLong(++param, porudzbina.getProizvod().getId());
			stmt.executeUpdate();
		}

	}

}
