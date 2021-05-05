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
public class RatingDTO implements Serializable {

    private String carID, email, comment;
    private int rating;
    private Date dateOfCreate;

    public RatingDTO(String carID, String email, String comment, int rating) {
        this.carID = carID;
        this.email = email;
        this.comment = comment;
        this.rating = rating;
    }

    public RatingDTO(String email, String comment, Date dateOfCreate) {
        this.email = email;
        this.comment = comment;
        this.dateOfCreate = dateOfCreate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
