package org.primefaces.examples.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import modele.metier.Produit;

public class CounterBean implements Serializable {

    private int count;

    public CounterBean() {
    }

    public CounterBean(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment(HashMap hm, Integer monProduit) {

        Integer monInt = (Integer) hm.get(monProduit);

        monInt++;

        hm.put(monProduit, monInt);

    }

    public void decrement(Map hm, int key) {
        if (count > 0) {
            count--;

        }

    }
}
