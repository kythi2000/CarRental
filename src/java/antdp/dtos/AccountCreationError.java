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
public class AccountCreationError implements Serializable {

    private String confirmErr;
    private String usernameIsExistErr;

    public AccountCreationError() {
    }

    public AccountCreationError(String confirmErr, String usernameIsExistErr) {
        this.confirmErr = confirmErr;
        this.usernameIsExistErr = usernameIsExistErr;
    }

    public String getConfirmErr() {
        return confirmErr;
    }

    public void setConfirmErr(String confirmErr) {
        this.confirmErr = confirmErr;
    }

    public String getUsernameIsExistErr() {
        return usernameIsExistErr;
    }

    public void setUsernameIsExistErr(String usernameIsExistErr) {
        this.usernameIsExistErr = usernameIsExistErr;
    }

}
