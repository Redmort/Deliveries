package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.products.Product;
import enums.PizzaType;
import enums.SizeType;
import exceptions.ExistedProductExceprion;
import exceptions.MyException;

public class ProductDAO {
	
	private Connection con = DBConnection.getInstance().getConnection();
	
	public void addProduct(Product product){
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO products VALUES(null,?, ?, ?, ?)");
			ps.setString(1, product.getName());
			ps.setString(2, product.getSize());
			ps.setInt(3, product.getPrice());
			ps.setString(4, product.getDescription());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeProduct(String pizzaType, String pizzaSize) throws ExistedProductExceprion, MyException{
		if(!this.exists(pizzaType, pizzaSize)){
			throw new ExistedProductExceprion("This product doesn't exists");
		}
		
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE name = ? AND size = ?");
			ps.setString(1, pizzaType);
			ps.setString(2, pizzaSize);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Product getProduct(String pizzaType, String pizzaSize) throws MyException, ExistedProductExceprion{
		
		if(!this.exists(pizzaType, pizzaSize)){
			throw new ExistedProductExceprion("This product doesn't exists");
		}
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM products as p WHERE (p.name = ? AND p.size = ?)");
			ps.setString(1, pizzaType);
			ps.setString(2, pizzaSize);
			
			ResultSet result = ps.executeQuery();
			result.next();
			
			int id = result.getInt(1);
			String name = result.getString(2);
			String size = result.getString(3);
			int price = result.getInt(4);
			String description = result.getString(5);
			
			return new Product(id, name, size, price, description);
			
		} catch (SQLException e) {
			throw new MyException("Can't get this product");
		}
	}
	
	public boolean exists(String name, String size) throws MyException{
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM products as p WHERE (p.name = ? AND p.size = ?)");
			ps.setString(1, name);
			ps.setString(2, size);
			
			ResultSet result = ps.executeQuery();
			
			if(result.next()){
				return true;
			}
			
			return false;
			
		} catch (SQLException e) {
			throw new MyException("Can't get this product");
		}
	}
	
	public List<Product> getAllProducts() throws MyException{
		
		List<Product> products = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				String size = result.getString(3);
				int price = result.getInt(4);
				String description = result.getString(5);
				
				products.add(new Product(id, name, size, price, description));
				
			}
			
			return products;
		} catch (SQLException e) {
			throw new MyException("Can't get all products");
		}
	}

}
