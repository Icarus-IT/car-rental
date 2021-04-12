/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class tblCarsDTO implements Serializable{
    private String carid;
    private String carname;
    private String color;
    private String year;
    private String category;
    private int price;
    private int quantiy;
    private String image;

    public tblCarsDTO() {
    }

    public tblCarsDTO(String carid, String carname, String color, String year, String category, int price, int quantiy, String image) {
        this.carid = carid;
        this.carname = carname;
        this.color = color;
        this.year = year;
        this.category = category;
        this.price = price;
        this.quantiy = quantiy;
        this.image = image;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantiy() {
        return quantiy;
    }

    public void setQuantiy(int quantiy) {
        this.quantiy = quantiy;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
   
    
}
