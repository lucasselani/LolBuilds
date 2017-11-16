package br.inatel.lolbuilds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.inatel.lolbuilds.entity.BuildItem;
import br.inatel.lolbuilds.entity.Item;

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
			System.out.println("BuildItem adicionado com sucesso!");
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
	
	public ArrayList<Item> list(int buildId) {
		try {
			String queryString = "SELECT item.name, item.image, item.id FROM item "
					+ "JOIN build_has_item ON item.id = build_has_item.item_id WHERE build_id=?";
			//String inString = "(SELECT item_id FROM build_has_item WHERE build_id=?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, buildId);
			resultSet = ptmt.executeQuery();
			ArrayList<Item> items = new ArrayList<Item>();;
			while (resultSet.next()) {		
				Item item = new Item();
				item.setName(resultSet.getString("name"));
				item.setImage(resultSet.getString("image"));
				item.setId(resultSet.getInt("id"));
				items.add(item);
		    }			
			return items;
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
