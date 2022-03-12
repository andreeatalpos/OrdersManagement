package ro.tuc.tp.Bll;

import ro.tuc.tp.Bll.Validators.AddressValidator;
import ro.tuc.tp.Bll.Validators.EmailValidator;
import ro.tuc.tp.Bll.Validators.NameValidator;
import ro.tuc.tp.Bll.Validators.Validator;
import ro.tuc.tp.Dao.ClientDAO;
import ro.tuc.tp.Model.Client;
import ro.tuc.tp.Presentation.View.ClientInterface;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * The Class ClientBLL.
 * @author Talpos Andreea
 */

public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientInterface clientInterface;
    private ClientDAO clientDAO;

    public ClientBLL(ClientInterface clientInterface){
        clientDAO=new ClientDAO();
        validators = new ArrayList<Validator<Client>>();
        validators.add(new NameValidator<Client>());
        validators.add(new EmailValidator());
        validators.add(new AddressValidator());
        this.clientInterface = clientInterface;
    }
    public Client findClientById(int id){
        Client client = clientDAO.findById(id);
        if(client==null){
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return client;
    }
    public void findAllClients(){
        ArrayList<Client> clients = clientDAO.findAll();
        if(clients==null) {
                throw new NoSuchElementException("Zero clients in database!");
            }
        JScrollPane myScrollPane = new JScrollPane();
        myScrollPane.setBounds(65,200,500,120);

        JTable clientTable = new JTable();
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
        myScrollPane.setViewportView(clientTable);
        clientInterface.getContentPane().add(myScrollPane);
    }
    public int insertClient(Client client){
        try {
            for (Validator<Client> v : validators) {
                v.validate(client);
            }
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error: " +e.getMessage());
                return 0;
            }
        return clientDAO.insert(client);
    }
    public int updateClient(Client client){
        for(Validator<Client> v: validators){
            try {
                v.validate(client);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error: " +e.getMessage());
                return 0;
            }
        }
        return clientDAO.update(client);
    }
    public int deleteClient(Client client){
        return clientDAO.delete(client);
    }
}
