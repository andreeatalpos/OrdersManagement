package ro.tuc.tp.Model;

/**
 * Class Orders
 * @author Talpos Andreea
 */
public class Orders {
    private int id;
    private int clientID;
    private int productID;
    private int cantitate;
    private int pret=0;
    private String adresa=null;
    private String denumire;

    /**
     * The constructor with params  - instantiates a new order
     * @param id
     *          the order id
     * @param clientID
     *          the id of the client
     * @param productID
     *          the id of the product
     * @param cantitate
     *          the quantity of the product
     * @param pret
     *          the price of the product
     * @param adresa
     *          the address of the client
     * @param denumire
     *          the name of the product
     */
    public Orders(int id, int clientID, int productID,int cantitate, int pret, String adresa, String denumire){
        this.id=id;
        this.clientID=clientID;
        this.productID=productID;
        this.cantitate=cantitate;
        this.pret=pret;
        this.adresa = adresa;
        this.denumire=denumire;
    }

    /**
     * the constructor without parameters
     */
    public Orders(){

    }
    public int getProductID() {
        return productID;
    }
    public int getCantitate() {
        return cantitate;
    }

    public int getClientID() {
        return clientID;
    }
    public int getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getPret() {
        return pret;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setId(int id) {
        this.id = id;
    }
}
