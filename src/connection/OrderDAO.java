package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.orders.Order;
import entities.products.Product;
import entities.users.User;
import exceptions.MyException;

public class OrderDAO {
	
	private Connection con = DBConnection.getInstance().getConnection();
	
	public void addOrder(Order order){
		
		try {
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int orderId = 0;
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO orders VALUES(null, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			ps.setInt(1, order.getUser().getID());
			ps.setDate(2, date);
			
			ps.executeUpdate();
			
			ResultSet result = ps.getGeneratedKeys();
			result.next();
			orderId = result.getInt(1);
			int a = 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Product product : order.getProducts()) {
			try {
				PreparedStatement ps1 = con.prepareStatement("INSERT INTO orders_has_products VALUES( ?, ?)");
				ps1.setInt(1, orderId);
				ps1.setInt(2, product.getId());
				
				ps1.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showAllOrdersByUser(User user){
		
		List<Integer> userOrderIds = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT o.id FROM orders as o JOIN users as u ON (u.id = o.users_id) WHERE u.id = ?");
			ps.setInt(1, user.getID());
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				userOrderIds.add(result.getInt(1));			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Integer id : userOrderIds) {
			try {
				PreparedStatement ps = con.prepareStatement("SELECT u.username, o.data, p.name, p.size, o.id "
						+ "From deliveries.users as u JOIN deliveries.orders as o ON (u.id = o.users_id) "
						+ "JOIN deliveries.orders_has_products as op "
						+ "ON (o.id = op.orders_id) "
						+ "JOIN deliveries.products as p "
						+ "ON (op.products_id = p.id) "
						+ "WHERE o.id = ?");
				
				ps.setInt(1, id);
				
				ResultSet result = ps.executeQuery();
				boolean isPrint = false;
				while (result.next()) {
					if(!isPrint){
					System.out.println("~~~~~~~~~ Date of order: " + result.getString(2) + " ~~~~~~~~~~~");
				
					isPrint = true;
					}
					
					System.out.println("pizza: " + result.getString(3) + ", size: " + result.getString(4));
				}
				
				System.out.println("===============================================");
				System.out.println();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<String> getAllUsersMadeOrders() throws MyException{
		List<String> usernamesMadeOrder = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT u.username "
														+ "From deliveries.users as u "
														+ "JOIN deliveries.orders as o "
														+ "ON (u.id = o.users_id) "
														+ "JOIN deliveries.orders_has_products as op "
														+ "ON (o.id = op.orders_id) "
														+ "JOIN deliveries.products as p "
														+ "ON (op.products_id = p.id)");
			
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				usernamesMadeOrder.add(result.getString(1));			
			}
			
			return usernamesMadeOrder;
			
		} catch (SQLException e) {
			throw new MyException("Can't get user made orders");
		}
		
	}
}
