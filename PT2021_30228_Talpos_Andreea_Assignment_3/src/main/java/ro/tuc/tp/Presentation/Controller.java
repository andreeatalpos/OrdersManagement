package ro.tuc.tp.Presentation;

import ro.tuc.tp.Connection.ConnectionFactory;
import ro.tuc.tp.Dao.ClientDAO;
import ro.tuc.tp.Dao.OrderDAO;
import ro.tuc.tp.Dao.ProductDAO;
import ro.tuc.tp.Presentation.View.ClientInterface;
import ro.tuc.tp.Presentation.View.MainInterface;
import ro.tuc.tp.Presentation.View.OrderInterface;
import ro.tuc.tp.Presentation.View.ProductInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Controller {
    private MainInterface mainInterface;
    private ClientInterface clientInterface;
    private ProductInterface productInterface;
    private OrderInterface orderInterface;
    private ClientDAO client = new ClientDAO();
    private ProductDAO product=new ProductDAO();
    public void start(){
        Connection conn = ConnectionFactory.getConnection();
        if(conn!=null){
            System.out.println("Connected!");
        }
        mainInterface = new MainInterface();
        clientInterface = new ClientInterface();
        productInterface = new ProductInterface();
        orderInterface = new OrderInterface(client,product);
        mainInterface.setVisible(true);
        initializeButtons();
    }
    public void initializeButtons(){
        mainInterface.addClientsInterfaceButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientInterface.setVisible(true);
                mainInterface.setVisible(false);

            }
        });
        mainInterface.addProductsInterfaceButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productInterface.setVisible(true);
                mainInterface.setVisible(false);
            }
        });
        mainInterface.addOrdersInterfaceButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderInterface.setVisible(true);
                mainInterface.setVisible(false);
            }
        });
        clientInterface.addBackActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientInterface.setVisible(false);
                mainInterface.setVisible(true);

            }
        });
        orderInterface.addBackActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderInterface.setVisible(false);
                mainInterface.setVisible(true);
            }
        });
        productInterface.addBackActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productInterface.setVisible(false);
                mainInterface.setVisible(true);
            }
        });
    }
}
