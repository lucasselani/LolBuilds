package br.inatel.lolbuilds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.inatel.lolbuilds.entity.Build;

public class BuildDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public BuildDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DAO.getInstance().getConnection();
		return conn;
	}	
	
	public ArrayList<Build> findBuildByName(String name) {
		try {
			String queryString = "SELECT * FROM build WHERE name LIKE %?%";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, name);
			resultSet = ptmt.executeQuery();
			
			ArrayList<Build> builds = new ArrayList<>();
			while (resultSet.next()) {
				Build build = new Build();
				build.setChampionId(resultSet.getInt("champion_id"));
				build.setId(resultSet.getInt("id"));
				build.setUserId(resultSet.getInt("user_id"));
				build.setName(resultSet.getString("name"));
				builds.add(build);
		    }
			return builds;
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

	public void update(Build build) {
		try {
			String sql = "update build set";
			connection = getConnection();
			Statement stm = connection.createStatement();
			
			sql += " name='" + build.getName() + "'";			
			sql += " WHERE id=" + build.getId() + ";";

			stm.execute(sql);
			System.out.println("Build atualizada com sucesso!");
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
			String queryString = "DELETE FROM build WHERE id=?";
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
			String queryString = "SELECT * FROM build";
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
