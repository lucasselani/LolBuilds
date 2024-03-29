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
	
	public int add(Build build) {
		try {
			String queryString = "insert into build (name, type, user_id, champion_id, datetime) values (?,?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			ptmt.setString(1, build.getName());
			ptmt.setString(2, build.getType());
			ptmt.setInt(3, build.getUserId());
			ptmt.setInt(4, build.getChampionId());
			ptmt.setTimestamp(5, build.getDatetime());
			int affectedRows = ptmt.executeUpdate();
			
			if(affectedRows != 0) {
				ResultSet generatedKeys = ptmt.getGeneratedKeys();
		        if (generatedKeys.next()) {
		            System.out.println("Build adicionado com sucesso!");
		            return generatedKeys.getInt(1);
		        }
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
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
	
	public String defineBuildName(String name) {
		try {
			String queryString = "SELECT COUNT(*) FROM build WHERE name LIKE ? OR name LIKE ? OR name LIKE ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, name);
			ptmt.setString(2, name + " (_)");
			ptmt.setString(3, name + " (__)");
			resultSet = ptmt.executeQuery();
			
			while (resultSet.next()) {
				int quantity = resultSet.getInt(1);
				if(quantity > 0) return name + " (" + quantity + ")";
		    }
			return name;
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
	
	public ArrayList<Build> getAllBuilds() {
		try {
			String queryString = "SELECT * FROM build";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			ArrayList<Build> builds = new ArrayList<Build>();
			while (resultSet.next()) {
				Build build = new Build();
				build.setId(resultSet.getInt("id"));
				build.setUserId(resultSet.getInt("user_id"));
				build.setName(resultSet.getString("name"));
				build.setChampionId(resultSet.getInt("champion_id"));
				build.setType(resultSet.getString("type"));
				build.setDatetime(resultSet.getTimestamp("datetime"));
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
				build.setChampionId(resultSet.getInt("champion_id"));
				build.setType(resultSet.getString("type"));
				build.setDatetime(resultSet.getTimestamp("datetime"));
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
	
	public ArrayList<Build> getBuildByUsername(String name) {
		try {
			String queryString = "SELECT * FROM build JOIN user ON build.user_id = user.id WHERE user.username=?";
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
				build.setChampionId(resultSet.getInt("champion_id"));
				build.setType(resultSet.getString("type"));
				build.setDatetime(resultSet.getTimestamp("datetime"));
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

	public void delete(String name) {

		try {
			String queryString = "DELETE FROM build WHERE name=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, name);
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

	public ArrayList<Build> getBuildsByUserId(int userId) {
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
				build.setChampionId(resultSet.getInt("champion_id"));
				build.setDatetime(resultSet.getTimestamp("datetime"));
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
	
	public ArrayList<Build> getBuildsByChampion(String championName) {
		try {
			String queryString = "SELECT * FROM build WHERE champion_id IN ";
			String inString = "(SELECT id FROM champion WHERE name=?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString+inString);
			ptmt.setString(1, championName);
			resultSet = ptmt.executeQuery();
			ArrayList<Build> builds = new ArrayList<Build>();
			while (resultSet.next()) {
				Build build = new Build();
				build.setId(resultSet.getInt("id"));
				build.setUserId(resultSet.getInt("user_id"));
				build.setName(resultSet.getString("name"));
				build.setType(resultSet.getString("type"));
				build.setChampionId(resultSet.getInt("champion_id"));
				build.setDatetime(resultSet.getTimestamp("datetime"));
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
	
	public ArrayList<Build> getBuildsByType(String championType) {
		try {
			String queryString = "SELECT * FROM build WHERE type=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, championType);
			resultSet = ptmt.executeQuery();
			ArrayList<Build> builds = new ArrayList<Build>();
			while (resultSet.next()) {
				Build build = new Build();
				build.setId(resultSet.getInt("id"));
				build.setUserId(resultSet.getInt("user_id"));
				build.setName(resultSet.getString("name"));
				build.setType(resultSet.getString("type"));
				build.setChampionId(resultSet.getInt("champion_id"));
				build.setDatetime(resultSet.getTimestamp("datetime"));
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
