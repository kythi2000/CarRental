/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.cart;

import antdp.dtos.CarDTO;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author HP 840 G2
 */
public class CartObj implements Serializable {

    private String customerName;
    private HashMap<String, CarDTO> cart;
    private String rentalDate;
    private String returnDate;

    public CartObj() {
        this.customerName = "guest";
        this.cart = new HashMap<>();
    }

    public CartObj(String customerName, String rentalDate, String returnDate) {
        this.cart = new HashMap<>();
        this.customerName = customerName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, CarDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, CarDTO> cart) {
        this.cart = cart;
    }

    public void addToCart(CarDTO dto) throws Exception {
        if (this.cart.containsKey(dto.getCarID())) {
            int newQuantity = this.cart.get(dto.getCarID()).getQuantity() + 1;
            this.cart.get(dto.getCarID()).setQuantity(newQuantity);
        } else {
            this.cart.put(dto.getCarID(), dto);
        }
    }

    public void remove(String id) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public float getTotal() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date rentDay = sdf.parse(rentalDate);
        Date returnDay = sdf.parse(returnDate);
        long getDiff = returnDay.getTime() - rentDay.getTime();  // milisecond
        long days = getDiff / (24 * 60 * 60 * 1000);
        float result = 0;
        for (CarDTO dTO : this.cart.values()) {
            result += dTO.getQuantity() * dTO.getPrice();
        }
        return result * days;
    }

    public void updateCart(String id, int quantity) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.get(id).setQuantity(quantity);
        }
    }

}
