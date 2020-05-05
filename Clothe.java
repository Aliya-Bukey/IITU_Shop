package IITU_shop.data;

import java.io.Serializable;

public class Clothe extends Good implements Serializable {
    private String c_size;
    private String c_color;
    private String clothe_type;

    public Clothe() {
    }

    public Clothe(Long id, String model, int price, int count, String c_size, String c_color, String clothe_type) {
        super(id, model, price, count);
        this.c_size = c_size;
        this.c_color = c_color;
        this.clothe_type = clothe_type;
    }

    public String getC_size() {
        return c_size;
    }

    public void setC_size(String c_size) {
        this.c_size = c_size;
    }

    public String getC_color() {
        return c_color;
    }

    public void setC_color(String c_color) {
        this.c_color = c_color;
    }

    public String getClothe_type() {
        return clothe_type;
    }

    public void setClothe_type(String clothe_type) {
        this.clothe_type = clothe_type;
    }
    public String showDetails(){
        return getC_color();
    }
}
