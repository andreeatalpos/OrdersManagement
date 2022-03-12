package ro.tuc.tp.Bll;

import ro.tuc.tp.Bll.Validators.NameValidator;
import ro.tuc.tp.Bll.Validators.PositiveNumberValidator;
import ro.tuc.tp.Bll.Validators.Validator;
import ro.tuc.tp.Dao.ProductDAO;
import ro.tuc.tp.Model.Product;
import ro.tuc.tp.Presentation.View.ProductInterface;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * The Class ProductBLL.
 * @author Talpos Andreea
 */
public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;
    private ProductInterface productInterface;
    public ProductBLL(ProductInterface productInterface){
        productDAO = new ProductDAO();
        validators = new ArrayList<Validator<Product>>();
        validators.add(new NameValidator<Product>());
        validators.add(new PositiveNumberValidator<Product>());

        this.productInterface=productInterface;
    }
    public Product findProductById(int id){
        Product product = productDAO.findById(id);
        if(product==null){
            throw new NoSuchElementException("The product with id="+id+" was not found!");
        }
        return product;
    }
    public void findAllProducts(){
        ArrayList<Product> products = productDAO.findAll();
        if(products==null){
            throw new NoSuchElementException("Zero products in database!");
        }
        JScrollPane myScrollPane = new JScrollPane();
        myScrollPane.setBounds(65,200,500,120);

        JTable productTable = new JTable();
        productTable=productDAO.createTable(products);
        productTable.setEnabled(true);
        productTable.setVisible(true);
        productTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        productTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        productTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        productTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        productTable.removeColumn(productTable.getColumnModel().getColumn(4));
        productTable.removeColumn(productTable.getColumnModel().getColumn(4));
        productTable.removeColumn(productTable.getColumnModel().getColumn(4));
        myScrollPane.setViewportView(productTable);
        productInterface.getContentPane().add(myScrollPane);
    }
    public int insertProduct(Product product){
        for(Validator<Product> v: validators){
            try{
            v.validate(product);
        }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                return 0;
            }
        }
        return productDAO.insert(product);
    }
    public int updateProduct(Product product){
        for(Validator<Product> v: validators){
            try{
            v.validate(product);
        }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                return 0;
            }
        }
        return productDAO.update(product);
    }
    public int deleteProduct(Product product){
        for(Validator<Product> v: validators){
            try{
            v.validate(product);
        }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                return 0;
            }
        }
        return productDAO.delete(product);
    }

}
