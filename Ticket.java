package IITU_shop.data;

import java.io.Serializable;

public class Ticket extends Good implements Serializable {
    private String date;
    private int pleace;
    private String organization;

    public Ticket() {
    }

    public Ticket(Long id, String name, int price, int count, String date, int pleace, String organization) {
        super(id, name, price, count);
        this.date = date;
        this.pleace = pleace;
        this.organization = organization;
    }

    public Ticket(Long id, String name, int price, int count, int sold, String date, int pleace, String organization) {
        super(id, name, price, count, sold);
        this.date = date;
        this.pleace = pleace;
        this.organization = organization;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPleace() {
        return pleace;
    }

    public void setPleace(int pleace) {
        this.pleace = pleace;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
    public String showDetails(){
        return getDate()+" "+getPleace();
    }
}
