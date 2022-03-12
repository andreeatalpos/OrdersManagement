package ro.tuc.tp.Model;
/**
 * Class Client
 * @author Talpos Andreea
 */
public class Client {
    private int id;
    private String nume;
    private String email;
    private String adresa;

    /**
     * The constructor with params - instantiates a new client
     * @param clientID
     *          the id of the client
     * @param nume
     *          the name of the client
     * @param email
     *          client's email
     * @param adresa
     *          client's address
     */
    public Client(int clientID, String nume, String email, String adresa){
        this.id=clientID;
        this.nume=nume;
        this.email=email;
        this.adresa=adresa;
    }

    /**
     * The constructor without params
     */
    public Client(){
    }
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getNume() {
        return nume;
    }
    public String getAdresa(){
        return adresa;
    }
    public void setId(int clientID) {
        this.id = clientID;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
