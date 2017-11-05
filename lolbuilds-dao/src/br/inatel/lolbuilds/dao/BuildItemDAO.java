package br.inatel.lolbuilds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.inatel.lolbuilds.entity.BuildItem;
import br.inatel.lolbuilds.entity.BuildSpell;

public class BuildItemDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public BuildItemDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DAO.getInstance().getConnection();
		return conn;
	}	
	
	public void add(BuildItem buildItem) {
		try {
			String queryString = "insert into build_has_item (item_id,build_id) values (?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, buildItem.getItemId());
			ptmt.setInt(2, buildItem.getBuildId());
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
