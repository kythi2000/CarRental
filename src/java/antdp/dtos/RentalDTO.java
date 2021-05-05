/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author HP 840 G2
 */
public class RentalDTO implements Serializable {

    private String rentalID, email;
    private Date dateOfCreate;
    private float total;
    private boolean status;

    public RentalDTO(String rentalID, String email, Date dateOfCreate, float total, boolean status) {
        this.rentalID = rentalID;
        this.email = email;
        this.dateOfCreate = dateOfCreate;
        this.total = total;
        this.status = status;
    }

    public RentalDTO(String rentalID, String email, float total) {
        this.rentalID = rentalID;
        this.email = email;
        this.total = total;
    }

    public String getRentalID() {
        return rentalID;
    }

    public void setRentalID(String rentalID) {
        this.rentalID = rentalID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
