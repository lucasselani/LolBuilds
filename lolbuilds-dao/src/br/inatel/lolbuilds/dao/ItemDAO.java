package br.inatel.lolbuilds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.inatel.lolbuilds.entity.Champion;
import br.inatel.lolbuilds.entity.Item;

public class ItemDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public ItemDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DAO.getInstance().getConnection();
		return conn;
	}	
	
	public void add(Item item) {
		try {
			String queryString = "insert into champion (name,description,image,gold,stats) values (?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, item.getName());
			ptmt.setString(2, item.getDescription());
			ptmt.setString(3, item.getImage());
			ptmt.setInt(4, item.getGold());
			ptmt.setString(5, item.getStats());
			ptmt.executeUpdate();
			System.out.println("Item adicionado com sucesso!");
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
	
	public Item findItemByName(String name) {
		try {
			String queryString = "SELECT * FROM item WHERE name=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, name);
			resultSet = ptmt.executeQuery();

			while (resultSet.next()) {
				Item item = new Item();
				item.setDescription(resultSet.getString("description"));
				item.setImage(resultSet.getString("image"));
				item.setName(resultSet.getString("name"));
				item.setStats(resultSet.getString("stats"));
				item.setGold(resultSet.getInt("gold"));
				item.setId(resultSet.getInt("id"));
				return item;
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

	public void update(Item item) {
		try {
			String sql = "update build set";
			connection = getConnection();
			Statement stm = connection.createStatement();
			
			if(item.getName() != null) {
				sql += " name='" + item.getName() + "'";
			}							
			sql += " WHERE id=" + item.getId() + ";";

			stm.execute(sql);
			System.out.println("Item atualizado com sucesso!");
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
			String queryString = "DELETE FROM item WHERE id=?";
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
			String queryString = "SELECT * FROM item";
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
