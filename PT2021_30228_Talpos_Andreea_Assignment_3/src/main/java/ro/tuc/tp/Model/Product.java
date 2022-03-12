package ro.tuc.tp.Model;

/**
 * Class Product
 * @author Talpos Andreea
 */
public class Product {
    private int id;
    private String nume;
    private int pret;
    private int stoc;

    /**
     * The constructor with params - instantiates a new product
     * @param productID
     *          the id
     * @param nume
     *          product's name
     * @param pret
     *          the price
     * @param stoc
     *          initial stock
     */
    public Product(int productID, String nume, int pret, int stoc){
        this.id=productID;
        this.nume=nume;
        this.pret=pret;
        this.stoc=stoc;
    }

    /**
     * The constructor without parameters
     */
    public Product(){
    }
    public int getId() {
        return id;
    }
    public int getPret() {
        return pret;
    }
    public int getStoc() {
        return stoc;
    }
    public String getNume() {
        return nume;
    }
    public void setId(int productID) {
        this.id = productID;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public void setPret(int pret) {
        this.pret = pret;
    }
    public void setStoc(int stoc) {
        this.stoc = stoc;
    }
}
