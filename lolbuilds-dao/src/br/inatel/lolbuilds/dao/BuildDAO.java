package br.inatel.lolbuilds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.inatel.lolbuilds.entity.Build;
import br.inatel.lolbuilds.entity.Champion;

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
	
	public void add(Build build) {
		try {
			String queryString = "insert into build (name, type, used_id) values (?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, build.getName());
			ptmt.setString(1, build.getType());
			ptmt.setInt(1, build.getUserId());
			ptmt.executeUpdate();
			System.out.println("Build adicionado com sucesso!");
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
	
	public int findBuildIdByName(String name) {
		try {
			String queryString = "SELECT id FROM build WHERE name=?";
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
	
	public ArrayList<Build> searchBuildsByName(String name) {
		try {
			String queryString = "SELECT * FROM build WHERE name LIKE %?%";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, name);
			resultSet = ptmt.executeQuery();
			
			ArrayList<Build> builds = new ArrayList<>();
			while (resultSet.next()) {
				Build build = new Build();
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

	public ArrayList<Build> list(int userId) {
		try {
			String queryString = "SELECT * FROM build WHERE user_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, userId);
			resultSet = ptmt.executeQuery();
			ArrayList<Build> builds = new ArrayList<Build>();
			while (resultSet.next()) {
				Build build = new Build();
				build.setId(resultSet.getInt("id"));
				build.setUserId(resultSet.getInt("user_id"));
				build.setName(resultSet.getString("name"));
				build.setType(resultSet.getString("type"));
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
}
