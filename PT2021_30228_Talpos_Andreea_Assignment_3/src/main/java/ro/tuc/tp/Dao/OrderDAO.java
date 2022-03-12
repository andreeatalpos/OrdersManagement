package ro.tuc.tp.Dao;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import ro.tuc.tp.Connection.ConnectionFactory;
import ro.tuc.tp.Model.Client;
import ro.tuc.tp.Model.Orders;
import ro.tuc.tp.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class OrderDAO which extends AbstractDAO
 * @author Talpos Andreea
 */
public class OrderDAO extends AbstractDAO<Orders> {
    private ClientDAO clientDAO=new ClientDAO();
    private ProductDAO productDAO = new ProductDAO();

    /**
     * The constructor
     * @param clientDAO
     * @param productDAO
     */
    public OrderDAO(ClientDAO clientDAO, ProductDAO productDAO){
        this.clientDAO = clientDAO;
        this.productDAO = productDAO;
    }
    /**
     * Creates an update query to set the address, price and name needed for order
     *
     * @return the query
     */
    public String updateOrdersSt(){
        return "UPDATE Orders SET adresa =?, pret=?, denumire=? WHERE id=?";
    }
    /**
     * Updates order in the database with: address, price, name of product
     *
     * @param order
     *            the order to be updated
     * @return 1 if successfull, -1 otherwise
     */
    public int updateOrders(Orders order){
        int updated=-1;
        int updated1=-1;
        Connection conn = null;
        Client client = clientDAO.findById(order.getClientID());
        Product product = productDAO.findById(order.getProductID());
        PreparedStatement statement=null;
        String updateStatement = updateOrdersSt();
        try{
            if(order.getCantitate()<= product.getStoc()) {
                conn = ConnectionFactory.getConnection();
                statement = conn.prepareStatement(updateStatement);
                statement.setString(1, client.getAdresa());
                statement.setInt(2, product.getPret() * order.getCantitate());
                statement.setInt(4, order.getId());
                statement.setString(3,product.getNume());
                updated = statement.executeUpdate();
                updated1 = productDAO.updateStoc(product, product.getStoc() - order.getCantitate());
            }
            else throw new IllegalArgumentException("Not enough stock");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updated;
    }
}
