package br.inatel.lolbuilds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.inatel.lolbuilds.entity.Spell;

public class SpellDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public SpellDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DAO.getInstance().getConnection();
		return conn;
	}	
	
	public void add(Spell spell) {
		try {
			String queryString = "insert into spell (name,image) values (?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, spell.getName());
			ptmt.setString(2, spell.getImage());
			ptmt.executeUpdate();
			System.out.println("Spell adicionado com sucesso!");
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
	
	public Spell findSpellByName(String name) {
		try {
			String queryString = "SELECT * FROM spell WHERE name=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, name);
			resultSet = ptmt.executeQuery();

			while (resultSet.next()) {
				Spell spell = new Spell();
				spell.setImage(resultSet.getString("image"));
				spell.setName(resultSet.getString("name"));
				spell.setId(resultSet.getInt("id"));
				return spell;
		    }
			return null;
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

	public void update(Spell spell) {
		try {
			String sql = "update spell set";
			connection = getConnection();
			Statement stm = connection.createStatement();
			
			if(spell.getName() != null) {
				sql += " name='" + spell.getName() + "'";
			}							
			sql += " WHERE id=" + spell.getId() + ";";

			stm.execute(sql);
			System.out.println("Spell atualizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null) {
					ptmt.close();
				}					
				if (connection != null) {
					connection.close();
				}					
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(int id) {

		try {
			String queryString = "DELETE FROM spell WHERE id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();
			System.out.println("Data deleted Successfully");
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

	public void list() {
		try {
			String queryString = "SELECT * FROM spell";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			System.out.println(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
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
