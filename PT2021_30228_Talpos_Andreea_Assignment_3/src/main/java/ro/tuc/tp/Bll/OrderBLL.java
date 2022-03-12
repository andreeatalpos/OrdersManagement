package ro.tuc.tp.Bll;

import ro.tuc.tp.Bll.Validators.PositiveNumberValidator;
import ro.tuc.tp.Bll.Validators.Validator;
import ro.tuc.tp.Dao.ClientDAO;
import ro.tuc.tp.Dao.OrderDAO;

import ro.tuc.tp.Dao.ProductDAO;
import ro.tuc.tp.Model.Client;
import ro.tuc.tp.Model.Orders;
import ro.tuc.tp.Model.Product;
import ro.tuc.tp.Presentation.View.ClientInterface;
import ro.tuc.tp.Presentation.View.OrderInterface;
import ro.tuc.tp.Presentation.View.ProductInterface;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * The Class OrderBLL.
 * @author Talpos Andreea
 */
public class OrderBLL {
    private List<Validator<Orders>> validators;
    private OrderDAO orderDAO;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    private OrderInterface orderInterface;
    private ProductInterface productInterface;
    private ClientInterface clientInterface;
    private PrintWriter writer;


    public OrderBLL(OrderInterface orderInterface, ClientDAO clientDAO, ProductDAO productDAO){
        this.clientDAO  =clientDAO;
        this.productDAO =productDAO;
        orderDAO = new OrderDAO(clientDAO, productDAO);
        validators = new ArrayList<Validator<Orders>>();
        validators.add(new PositiveNumberValidator<Orders>());
        this.orderInterface=orderInterface;
    }
    public Orders findOrderById(int id){
        Orders order = orderDAO.findById(id);
        if(order == null){
            throw new NoSuchElementException("The order with id="+ id + " was not found!");
        }
        return order;
    }
    public int setAddressAndPrice(Orders order) {
        try {
            return orderDAO.updateOrders(order);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return 0;
        }
    }
    public void findAllOrders(){
        ArrayList<Orders> orders = orderDAO.findAll();
        ArrayList<Client> clients = clientDAO.findAll();
        ArrayList<Product> products = productDAO.findAll();
        if(orders==null){
            throw new NoSuchElementException("Zero orders in database!!");
        }
        JScrollPane myScrollPaneClients = new JScrollPane();
        myScrollPaneClients.setBounds(65,350,500,120);
        JTable clientTable;
        clientTable = new JTable();
        clientTable=clientDAO.createTable(clients);
        clientTable.setEnabled(true);
        clientTable.setVisible(true);
        clientTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        clientTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        clientTable.getColumnModel().getColumn(2).setPreferredWidth(250);
        clientTable.getColumnModel().getColumn(3).setPreferredWidth(250);
        clientTable.removeColumn(clientTable.getColumnModel().getColumn(4));
        clientTable.removeColumn(clientTable.getColumnModel().getColumn(4));
        clientTable.removeColumn(clientTable.getColumnModel().getColumn(4));
        myScrollPaneClients.setViewportView(clientTable);
        orderInterface.getContentPane().add(myScrollPaneClients);

        JScrollPane myScrollPaneProducts = new JScrollPane();
        myScrollPaneProducts.setBounds(65,200,500,120);
        JTable productTable;
        productTable  =productDAO.createTable(products);
        productTable.setEnabled(true);
        productTable.setVisible(true);
        productTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        productTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        productTable.getColumnModel().getColumn(2).setPreferredWidth(250);
        productTable.getColumnModel().getColumn(3).setPreferredWidth(250);
        productTable.removeColumn(productTable.getColumnModel().getColumn(4));
        productTable.removeColumn(productTable.getColumnModel().getColumn(4));
        productTable.removeColumn(productTable.getColumnModel().getColumn(4));
        myScrollPaneProducts.setViewportView(productTable);
        orderInterface.getContentPane().add(myScrollPaneProducts);
        JScrollPane myScrollPane = new JScrollPane();
        myScrollPane.setBounds(65,500,500,120);
        JTable orderTable = new JTable();
        orderTable=orderDAO.createTable(orders);
        orderTable.setEnabled(true);
        orderTable.setVisible(true);
        orderTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        orderTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        orderTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        orderTable.getColumnModel().getColumn(3).setPreferredWidth(30);
        orderTable.getColumnModel().getColumn(4).setPreferredWidth(30);
        orderTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        orderTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        myScrollPane.setViewportView(orderTable);
        orderInterface.getContentPane().add(myScrollPane);
    }
    public int insertOrder(Orders order){
        for(Validator<Orders> v : validators) {
            try {
                v.validate(order);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                return 0;
            }
        }
        return orderDAO.insert(order);
    }
    public int updateOrder(Orders order){
        for(Validator<Orders> v : validators) {
            try {
                v.validate(order);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                return 0;
            }
        }
        return orderDAO.update(order);
    }
    public int deleteOrder(Orders order){
            for (Validator<Orders> v : validators) {
                try {
                    v.validate(order);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                    return 0;
                }
            }
                return orderDAO.delete(order);
            }
    public void generateBill(Orders order){
        try {
            writer = new PrintWriter("Order #" + order.getId() + ".txt", "UTF-8");
            writer.println("*************ORDER #" + order.getId() + "*********************");
            writer.println();
            ClientBLL clientBLL = new ClientBLL(clientInterface);
            ProductBLL productBLL = new ProductBLL(productInterface);
            Client client = clientBLL.findClientById(order.getClientID());
            Product product = productBLL.findProductById(order.getProductID());

            writer.println("Client name: " + client.getNume());
            writer.println("Client Address: " + client.getAdresa());
            writer.println("Client E-mail: " + client.getEmail());
            writer.println();
            writer.println("Product name: " + product.getNume());
            writer.println("Product Unit Price: " + product.getPret());
            writer.println("Number bought: " + order.getCantitate() + " pcs");
            writer.println();

            writer.println("TOTAL PRICE: " + order.getCantitate() * product.getPret());

            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
