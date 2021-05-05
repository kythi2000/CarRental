/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.history;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author HP 840 G2
 */
public class HistoryRental implements Serializable {

    private HashMap<String, List<RentalCarDTO>> historyRental;

    public HistoryRental() {
        this.historyRental = new HashMap<>();
    }

    public HashMap<String, List<RentalCarDTO>> getHistoryRental() {
        return historyRental;
    }

    public void setHistoryRental(HashMap<String, List<RentalCarDTO>> historyRental) {
        this.historyRental = historyRental;
    }

    public void add(String orderId, List<RentalCarDTO> list) {
        if (this.historyRental == null) {
            this.historyRental = new HashMap<>();
        }
        this.historyRental.put(orderId, list);
    }

    public void remove(String id) {
        if (this.historyRental.containsKey(id)) {
            this.historyRental.remove(id);
        }
    }
}
