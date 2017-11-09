package br.inatel.lolbuilds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.inatel.lolbuilds.entity.Champion;
import br.inatel.lolbuilds.entity.Item;

public class ChampionDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public ChampionDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DAO.getInstance().getConnection();
		return conn;
	}	
	
	public void add(Champion champion) {		
		try {
			int index = findChampionIdByName(champion.getName());
			if(index == -1) {
				String queryString = "insert into champion (name,image) values (?,?)";
				connection = getConnection();
				ptmt = connection.prepareStatement(queryString);
				ptmt.setString(1, champion.getName());
				ptmt.setString(2, champion.getImage());
				ptmt.executeUpdate();
				System.out.println("Champion adicionado com sucesso!");
			} 
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
	
	public int findChampionIdByName(String name) {
		try {
			String queryString = "SELECT id FROM champion WHERE name=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, name);
			resultSet = ptmt.executeQuery();
			
			while (resultSet.next()) {
				return resultSet.getInt("id");
		    }
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
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
	
	public Champion findChampionById(int id) {
		try {
			String queryString = "SELECT * FROM champion WHERE id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, id);
			resultSet = ptmt.executeQuery();			

			while (resultSet.next()) {
				Champion champion = new Champion();
				champion.setName(resultSet.getString("name"));
				champion.setImage(resultSet.getString("image"));
				champion.setId(resultSet.getInt("id"));
				return champion;
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

	public void update(Champion champion) {
		try {
			String sql = "update champion set";
			connection = getConnection();
			Statement stm = connection.createStatement();
			
			sql += " name='" + champion.getName() + "',";
			sql += " image='" + champion.getImage() + "'";	
			sql += " WHERE id=" + champion.getId() + ";";

			stm.execute(sql);
			System.out.println("Champion atualizada com sucesso!");
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
			String queryString = "DELETE FROM champion WHERE id=?";
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

	public Champion list(int buildId) {
		try {
			String queryString = "SELECT * FROM champion WHERE id IN ";
			String inString = "(SELECT champion_id FROM build WHERE id=?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString+inString);
			ptmt.setInt(1, buildId);
			resultSet = ptmt.executeQuery();
			Champion champion = new Champion();
			while (resultSet.next()) {				
				champion.setName(resultSet.getString("name"));
				champion.setImage(resultSet.getString("image"));
				champion.setId(resultSet.getInt("id"));				
		    }
			return champion;
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
