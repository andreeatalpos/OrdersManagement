package ro.tuc.tp.Presentation.View;

import ro.tuc.tp.Bll.ProductBLL;
import ro.tuc.tp.Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProductInterface extends JFrame {
    private JButton back;
    private JButton listAll;
    private JButton insert;
    private JButton update;
    private JButton delete;

    private JLabel input;
    private JLabel idLabel;
    private JTextField id;
    private JLabel nume;
    private JTextField name;
    private JLabel price;
    private JTextField pret;
    private JLabel stoc;
    private JTextField stock;




    public ProductInterface(){
        final ProductBLL productBLL = new ProductBLL(this);

        this.setTitle("Products");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 600, 450);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(243,241,120));

        Font hugeFont= new Font("Serif",Font.PLAIN,32);
        Font bigFont= new Font("Serif",Font.PLAIN,18);

        input = new JLabel("Data: ");
        input.setFont(bigFont);
        input.setFont(bigFont);
        input.setBounds(20,20,100,25);
        getContentPane().add(input);


        idLabel = new JLabel("ID: ");
        idLabel.setFont(bigFont);
        idLabel.setFont(bigFont);
        idLabel.setBounds(20,50,60,25);
        getContentPane().add(idLabel);

        id = new JTextField();
        id.setFont(bigFont);
        id.setBounds(90,50,60,25);
        id.setEditable(true);
        getContentPane().add(id);

        nume = new JLabel("Denumire: ");
        nume.setFont(bigFont);
        nume.setFont(bigFont);
        nume.setBounds(20,80,100,25);
        getContentPane().add(nume);

        name = new JTextField();
        name.setFont(bigFont);
        name.setBounds(90,80,250,25);
        name.setEditable(true);
        getContentPane().add(name);

        price = new JLabel("Price: ");
        price.setFont(bigFont);
        price.setFont(bigFont);
        price.setBounds(20,110,100,25);
        getContentPane().add(price);

        pret = new JTextField();
        pret.setFont(bigFont);
        pret.setBounds(90,110,250,25);
        pret.setEditable(true);
        getContentPane().add(pret);

        stoc = new JLabel("Stoc: ");
        stoc.setFont(bigFont);
        stoc.setFont(bigFont);
        stoc.setBounds(20,140,100,25);
        getContentPane().add(stoc);

        stock = new JTextField();
        stock.setFont(bigFont);
        stock.setBounds(90,140,250,25);
        stock.setEditable(true);
        getContentPane().add(stock);

        back = new JButton("Back");
        back.setBounds(20,350,80,35);
        back.setFont(bigFont);
        back.setBackground(Color.white);
        getContentPane().add(back);

        listAll = new JButton("List all products");
        listAll.setBounds(400,20,150,30);
        listAll.setFont(bigFont);
        listAll.setBackground(Color.white);
        getContentPane().add(listAll);
        listAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productBLL.findAllProducts();
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
                productBLL.insertProduct(new Product(getId(),getName(),getPrice(),getStock()));
            }
        });

        update = new JButton("Update");
        update.setFont(bigFont);
        update.setBounds(400,100,150,30);
        update.setBackground(Color.white);
        getContentPane().add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productBLL.updateProduct(new Product(getId(),getName(),getPrice(),getStock()));
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(400,140,150,30);
        delete.setBackground(Color.white);
        delete.setFont(bigFont);
        getContentPane().add(delete);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productBLL.deleteProduct(new Product(getId(),getName(),getPrice(),getStock()));
            }
        });


    }
    public void addBackActionListener(ActionListener actionListener){
        back.addActionListener(actionListener);
    }
    public int getId(){
        return Integer.parseInt(id.getText());
    }
    public String getName(){
        System.out.println(name.getText());
        return name.getText();
    }
    public int getPrice(){
        return Integer.parseInt(pret.getText());
    }
    public int getStock(){
        return Integer.parseInt(stock.getText());
    }
}

