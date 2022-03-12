package ro.tuc.tp.Presentation.View;


import ro.tuc.tp.Bll.OrderBLL;

import ro.tuc.tp.Dao.ClientDAO;
import ro.tuc.tp.Dao.ProductDAO;
import ro.tuc.tp.Model.Orders;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OrderInterface extends JFrame {
    private JButton back;
    private JButton listAll;
    private JButton insert;
    private JButton update;
    private JButton delete;
    private JButton generateBill;

    private JLabel input;
    private JLabel orderId;
    private JTextField ordId;
    private JLabel clientId;
    private JTextField clId;
    private JLabel productId;
    private JTextField prId;
    private JLabel cantitate;
    private JTextField cant;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;


    public OrderInterface(ClientDAO clientDAO, ProductDAO productDAO){
        this.clientDAO=clientDAO;
        this.productDAO=productDAO;
        final OrderBLL orderBLL = new OrderBLL(this,clientDAO,productDAO);
        this.setTitle("Orders");
        this.getContentPane().setBackground(Color.PINK);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 600, 750);
        this.getContentPane().setLayout(null);

        Font hugeFont= new Font("Serif",Font.PLAIN,32);
        Font bigFont= new Font("Serif",Font.PLAIN,18);

        input = new JLabel("Data: ");
        input.setFont(bigFont);
        input.setFont(bigFont);
        input.setBounds(20,20,100,25);
        getContentPane().add(input);


        orderId = new JLabel("Order ID:");
        orderId.setFont(bigFont);
        orderId.setFont(bigFont);
        orderId.setBounds(20,50,70,25);
        getContentPane().add(orderId);

        ordId = new JTextField();
        ordId.setFont(bigFont);
        ordId.setBounds(110,50,60,25);
        ordId.setEditable(true);
        getContentPane().add(ordId);

        clientId = new JLabel("Client ID: ");
        clientId.setFont(bigFont);
        clientId.setFont(bigFont);
        clientId.setBounds(20,80,100,25);
        getContentPane().add(clientId);

        clId = new JTextField();
        clId.setFont(bigFont);
        clId.setBounds(110,80,250,25);
        clId.setEditable(true);
        getContentPane().add(clId);

        productId = new JLabel("Product ID: ");
        productId.setFont(bigFont);
        productId.setFont(bigFont);
        productId.setBounds(20,110,100,25);
        getContentPane().add(productId);

        prId = new JTextField();
        prId.setFont(bigFont);
        prId.setBounds(110,110,250,25);
        prId.setEditable(true);
        getContentPane().add(prId);

        cantitate = new JLabel("Cantitate: ");
        cantitate.setFont(bigFont);
        cantitate.setFont(bigFont);
        cantitate.setBounds(20,140,100,25);
        getContentPane().add(cantitate);

        cant = new JTextField();
        cant.setFont(bigFont);
        cant.setBounds(110,140,250,25);
        cant.setEditable(true);
        getContentPane().add(cant);

        back = new JButton("Back");
        back.setBounds(20,650,80,35);
        back.setFont(bigFont);
        back.setBackground(Color.white);
        getContentPane().add(back);

        listAll = new JButton("List all");
        listAll.setBounds(400,20,150,30);
        listAll.setFont(bigFont);
        listAll.setBackground(Color.white);
        getContentPane().add(listAll);
        listAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderBLL.findAllOrders();
            }
        });

        insert = new JButton("Insert");
        insert.setBounds(400,60,150,30);
        insert.setFont(bigFont);
        insert.setBackground(Color.white);
        getContentPane().add(insert);
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderBLL.insertOrder(new Orders(getOrderId(),getClientId(),getProductId(),getCantitate(),0,"",""));
                orderBLL.setAddressAndPrice(new Orders(getOrderId(),getClientId(),getProductId(),getCantitate(),0,"",""));
            }
        });

        update = new JButton("Update");
        update.setFont(bigFont);
        update.setBackground(Color.white);
        update.setBounds(400,100,150,30);
        getContentPane().add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderBLL.updateOrder(new Orders(getOrderId(),getClientId(),getProductId(),getCantitate(),0,"",""));
                orderBLL.setAddressAndPrice(new Orders(getOrderId(),getClientId(),getProductId(),getCantitate(),0,"",""));
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(400,140,150,30);
        delete.setFont(bigFont);
        delete.setBackground(Color.white);
        getContentPane().add(delete);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderBLL.deleteOrder(new Orders(getOrderId(),getClientId(),getProductId(),getCantitate(),0,"",""));
            }
        });
        generateBill = new JButton("Bill");
        generateBill.setBounds(400,650,150,30);
        generateBill.setFont(bigFont);
        generateBill.setBackground(Color.white);
        getContentPane().add(generateBill);
        generateBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderBLL.generateBill(new Orders(getOrderId(),getClientId(),getProductId(),getCantitate(),0,"",""));
            }
        });
    }
    public void addBackActionListener(ActionListener actionListener){
        back.addActionListener(actionListener);
    }
    public int getOrderId(){
        return Integer.parseInt(ordId.getText());
    }
    public int getClientId(){return Integer.parseInt(clId.getText());}
    public int getProductId(){  return Integer.parseInt(prId.getText());}
    public int getCantitate(){ return Integer.parseInt(cant.getText());}
}



