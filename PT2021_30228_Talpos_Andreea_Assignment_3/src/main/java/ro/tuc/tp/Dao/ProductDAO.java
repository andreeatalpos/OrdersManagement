package ro.tuc.tp.Dao;

import ro.tuc.tp.Connection.ConnectionFactory;
import ro.tuc.tp.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class ProductDAO which extends AbstractDAO
 * @author Talpos Andreea
 */
public class ProductDAO extends AbstractDAO<Product>{
    public ProductDAO(){
        super();
    }
    /**
     * Creates an update query to set the new stock of a product
     *
     * @return the query
     */
    public String updateStockStatement(){
        return "UPDATE Product SET stoc=? WHERE id=?";
    }
    /**
     * Updates product in the database with a new stock after
     *
     * @param product
     *                  the product to be updated
     * @param stoc
     *                  the new stock
     * @return 1 if successfull, -1 otherwise
     */
    public int updateStoc(Product product, int stoc){
        int updated=-1;
        Connection conn=null;
        PreparedStatement statement=null;
        String updateStatement = updateStockStatement();
        try{
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(updateStatement);
            statement.setInt(1,stoc);
            statement.setInt(2,product.getId());
            updated=statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updated;
    }
}
