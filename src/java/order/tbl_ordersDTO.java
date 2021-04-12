/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class tbl_ordersDTO implements Serializable{
    public String email;
    public String carid;
    public String name;
    public String carname;
    public float price;
    public int quantity;

    public tbl_ordersDTO() {
    }

    public tbl_ordersDTO(String email, String carid, String name, String carname, float price, int quantity) {
        this.email = email;
        this.carid = carid;
        this.name = name;
        this.carname = carname;
        this.price = price;
        this.quantity = quantity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
