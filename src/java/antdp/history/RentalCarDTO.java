/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.history;

import antdp.dtos.CarDTO;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author HP 840 G2
 */
public class RentalCarDTO implements Serializable {

    private CarDTO carInRental;
    private float total, price;
    private int quantity;
    private String rentalDate;
    private String returnDate;
    private Date dateOfCreate;
    private String rentalID;

    public RentalCarDTO(CarDTO carInOrder, float total, float price, int quantity, String rentalDate, String returnDate, Date dateOfCreate, String rentalID) {
        this.carInRental = carInOrder;
        this.total = total;
        this.price = price;
        this.quantity = quantity;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.dateOfCreate = dateOfCreate;
        this.rentalID = rentalID;
    }

    public String getRentalID() {
        return rentalID;
    }

    public void setRentalID(String rentalID) {
        this.rentalID = rentalID;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
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

    public CarDTO getCarInRental() {
        return carInRental;
    }

    public void setCarInRental(CarDTO carInRental) {
        this.carInRental = carInRental;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
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
