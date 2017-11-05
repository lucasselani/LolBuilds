package br.inatel.lolbuilds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.inatel.lolbuilds.entity.BuildSpell;
import br.inatel.lolbuilds.entity.Spell;

public class BuildSpellDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public BuildSpellDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DAO.getInstance().getConnection();
		return conn;
	}	
	
	public void add(BuildSpell buildSpell) {
		try {
			String queryString = "insert into build_has_spell (spell_id,build_id) values (?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, buildSpell.getSpellId());
			ptmt.setInt(2, buildSpell.getBuildId());
			ptmt.executeUpdate();
			System.out.println("BuildSpell adicionado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
