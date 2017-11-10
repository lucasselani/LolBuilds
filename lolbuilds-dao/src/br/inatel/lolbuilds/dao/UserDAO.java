package br.inatel.lolbuilds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.inatel.lolbuilds.entity.Build;
import br.inatel.lolbuilds.entity.User;

public class UserDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public UserDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DAO.getInstance().getConnection();
		return conn;
	}
	
	public int login(User user) {
		try {
			String queryString = "SELECT * FROM user WHERE username=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, user.getUsername());
			resultSet = ptmt.executeQuery();

			while (resultSet.next()) {
				String password = resultSet.getString("password");
				if(password.equals(user.getPassword())) {
					return resultSet.getInt("id");
				}				
		    }
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
		return -1;
	}
	
	public String getUsernameById(int id) {
		try {
			String queryString = "SELECT username FROM user WHERE id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, id);
			resultSet = ptmt.executeQuery();

			while (resultSet.next()) {
				return resultSet.getString("username");				
		    }
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
		return null;
	}
	
	public boolean findUsername(String username) {
		try {
			String queryString = "SELECT username FROM user WHERE username=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, username);
			resultSet = ptmt.executeQuery();

			while (resultSet.next()) {
				String usernameSql = resultSet.getString("username");
				if(username.equals(usernameSql)) {
					return true;
				}				
		    }
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
		return false;
	}

	public void register(User user) {
		try {
			String queryString = "insert into user (username, password) values (?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, user.getUsername());
			ptmt.setString(2, user.getPassword());
			ptmt.executeUpdate();
			System.out.println("Usuário adicionado com sucesso!");
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

	public void update(User user) {

		try {
			String sql = "update user set";
			connection = getConnection();
			Statement stm = connection.createStatement();
			if(user.getUsername() != null) {
				sql += " username='" + user.getUsername() + "'";
				if(user.getPassword() != null) {
					sql += ",password='" + user.getPassword() + "'";
				}
			} else if(user.getPassword()!=null) {
				sql += " password='" + user.getPassword() + "'";
			} 
			sql += " WHERE id=" + user.getId() + ";";
			System.out.println(sql);
			stm.execute(sql);
			System.out.println("Usuário atualizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			}

			catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	public void delete(int id) {

		try {
			String queryString = "DELETE FROM user WHERE id=?";
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

	public ArrayList<User> list() {
		try {
			String queryString = "SELECT * FROM user";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			ArrayList<User> users = new ArrayList<User>();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				users.add(user);
		    }
			return users;
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
