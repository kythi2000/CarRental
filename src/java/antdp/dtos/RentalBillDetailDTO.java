/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.dtos;

import java.io.Serializable;

/**
 *
 * @author HP 840 G2
 */
public class RentalBillDetailDTO implements Serializable {

    private String rentalDetailID, rentalID, carID;
    private int quantity;
    private float price;
    private String rentalDate, returnDate;

    public RentalBillDetailDTO(String rentalDetailID, String rentalID, String carID, int quantity, float price, String rentalDate, String returnDate) {
        this.rentalDetailID = rentalDetailID;
        this.rentalID = rentalID;
        this.carID = carID;
        this.quantity = quantity;
        this.price = price;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public RentalBillDetailDTO(String carID, int quantity, float price) {
        this.carID = carID;
        this.quantity = quantity;
        this.price = price;
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

    public String getRentalDetailID() {
        return rentalDetailID;
    }

    public void setRentalDetailID(String rentalDetailID) {
        this.rentalDetailID = rentalDetailID;
    }

    public String getRentalID() {
        return rentalID;
    }

    public void setRentalID(String rentalID) {
        this.rentalID = rentalID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
