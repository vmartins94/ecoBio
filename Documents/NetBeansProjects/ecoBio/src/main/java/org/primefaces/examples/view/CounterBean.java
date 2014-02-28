package org.primefaces.examples.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
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

    public void increment() {

        count++;

    }

    public void decrement() {
        if (count > 0) {
            count--;
        }
    }

    public void resetBean() {
        count = 0;
    }

}
