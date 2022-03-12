package ro.tuc.tp.Presentation.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainInterface extends JFrame {
    private JLabel title;

    private JButton goToClientInterface;
    private JButton goToProductInterface;
    private JButton goToOrderInterface;
    public MainInterface(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500,150,800,600);
        this.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(217,148,95));

        Font hugeFont= new Font("Serif",Font.BOLD,40);
        Font bigFont= new Font("Serif",Font.PLAIN,22);


        title = new JLabel("Order Management Application");
        title.setFont(hugeFont);
        title.setBounds(130,50,700,50);
        getContentPane().add(title);

        goToClientInterface = new JButton();
        goToClientInterface.setLayout(new BorderLayout());
        goToClientInterface.setBounds(300,150,200,60);
        JLabel c1 = new JLabel("Access clients");
        c1.setFont(bigFont);
        JLabel c2 = new JLabel("interface");
        c2.setFont(bigFont);
        goToClientInterface.add(BorderLayout.NORTH,c1);
        goToClientInterface.add(BorderLayout.SOUTH,c2);
        goToClientInterface.setBackground(Color.white);
        getContentPane().add(goToClientInterface);

        goToProductInterface = new JButton();
        goToProductInterface.setLayout(new BorderLayout());
        goToProductInterface.setBounds(300,250,200,60);
        JLabel p1 = new JLabel("Access products");
        p1.setFont(bigFont);
        JLabel p2 = new JLabel("interface");
        p2.setFont(bigFont);
        goToProductInterface.add(BorderLayout.NORTH ,p1);
        goToProductInterface.add(BorderLayout.SOUTH,p2);
        goToProductInterface.setBackground(Color.white);
        getContentPane().add(goToProductInterface);

        goToOrderInterface = new JButton();
        goToOrderInterface.setLayout(new BorderLayout());
        goToOrderInterface.setBounds(300,350,200,60);
        JLabel o1 = new JLabel("Access orders");
        o1.setFont(bigFont);
        JLabel o2 = new JLabel("interface");
        o2.setFont(bigFont);
        goToOrderInterface.add(BorderLayout.NORTH ,o1);
        goToOrderInterface.add(BorderLayout.SOUTH,o2);
        goToOrderInterface.setBackground(Color.white);
        getContentPane().add(goToOrderInterface);

    }
    public void addClientsInterfaceButtonActionListener(ActionListener actionListener){
        goToClientInterface.addActionListener(actionListener);
    }
    public void addProductsInterfaceButtonActionListener(ActionListener actionListener){
        goToProductInterface.addActionListener(actionListener);
    }
    public void addOrdersInterfaceButtonActionListener(ActionListener actionListener){
        goToOrderInterface.addActionListener(actionListener);
    }
}
