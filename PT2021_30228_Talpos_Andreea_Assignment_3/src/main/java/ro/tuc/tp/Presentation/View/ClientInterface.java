package ro.tuc.tp.Presentation.View;

import ro.tuc.tp.Bll.ClientBLL;
import ro.tuc.tp.Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClientInterface extends JFrame {
    private JButton back;
    private JButton listAll;
    private JButton insert;
    private JButton update;
    private JButton delete;

    private JLabel input;
    private JLabel idLabel;
    private JTextField id;
    private JLabel emailLabel;
    private JTextField email;
    private JLabel nameLabel;
    private JTextField name;
    private JLabel addressLabel;
    private JTextField address;

    public ClientInterface(){
        final ClientBLL clientBLL = new ClientBLL(this);
        this.setTitle("Clients");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 600, 450);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(144,183,181));

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

        nameLabel = new JLabel("Name: ");
        nameLabel.setFont(bigFont);
        nameLabel.setFont(bigFont);
        nameLabel.setBounds(20,80,100,25);
        getContentPane().add(nameLabel);

        name = new JTextField();
        name.setFont(bigFont);
        name.setBounds(90,80,250,25);
        name.setEditable(true);
        getContentPane().add(name);

        emailLabel = new JLabel("Email: ");
        emailLabel.setFont(bigFont);
        emailLabel.setFont(bigFont);
        emailLabel.setBounds(20,110,100,25);
        getContentPane().add(emailLabel);

        email = new JTextField();
        email.setFont(bigFont);
        email.setBounds(90,110,250,25);
        email.setEditable(true);
        getContentPane().add(email);

        addressLabel = new JLabel("Address: ");
        addressLabel.setFont(bigFont);
        addressLabel.setFont(bigFont);
        addressLabel.setBounds(20,140,100,25);
        getContentPane().add(addressLabel);

        address = new JTextField();
        address.setFont(bigFont);
        address.setBounds(90,140,250,25);
        address.setEditable(true);
        getContentPane().add(address);

        back = new JButton("Back");
        back.setBounds(20,350,80,35);
        back.setFont(bigFont);
        back.setBackground(Color.white);
        getContentPane().add(back);

        listAll = new JButton("List all clients");
        listAll.setBounds(400,20,150,30);
        listAll.setFont(bigFont);
        listAll.setBackground(Color.white);
        getContentPane().add(listAll);
        listAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientBLL.findAllClients();
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
                clientBLL.insertClient(new Client(getId(),getName(),getEmail(),getAddress()));
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
                clientBLL.updateClient(new Client(getId(),getName(),getEmail(),getAddress()));
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
                clientBLL.deleteClient(new Client(getId(),getName(),getEmail(),getAddress()));
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
        return name.getText();
    }
    public String getEmail(){
        return email.getText();
    }
    public String getAddress(){
        return address.getText();
    }
}
