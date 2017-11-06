package br.inatel.lolbuilds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.inatel.lolbuilds.entity.BuildSpell;
import br.inatel.lolbuilds.entity.Item;
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
	
	public ArrayList<Spell> list(int buildId) {
		try {
			String queryString = "SELECT * FROM spell WHERE id IN ";
			String inString = "(SELECT spell_id FROM build_has_spell WHERE build_id=?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString+inString);
			ptmt.setInt(1, buildId);
			resultSet = ptmt.executeQuery();
			ArrayList<Spell> spells = new ArrayList<Spell>();			
			while (resultSet.next()) {		
				Spell spell = new Spell();
				spell.setId(resultSet.getInt("id"));
				spell.setImage(resultSet.getString("image"));
				spell.setName(resultSet.getString("name"));
				spells.add(spell);
		    }
			return spells;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
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
