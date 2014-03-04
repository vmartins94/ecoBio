package org.primefaces.examples.view;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modele.metier.Saison;
import modele.metier.Type;
import org.primefaces.event.SlideEndEvent;

public class SliderBean implements Serializable{

    private int number1;

    private int number2;

    private int number3;

    private int number4;

    private int number5;

    private int number6 = 30;

    private int number7 = 80;
    private Saison maSaison;
    private Type type;
    private boolean buttonClick = false;

    public Saison getMaSaison() {
        return maSaison;
    }

    public void setMaSaison(Saison maSaison) {
        this.maSaison = maSaison;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isButtonClick() {
        return buttonClick;
    }

    public void setButtonClick(boolean buttonClick) {
        this.buttonClick = buttonClick;
    }
    

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getNumber3() {
        return number3;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public int getNumber4() {
        return number4;
    }

    public void setNumber4(int number4) {
        this.number4 = number4;
    }

    public int getNumber5() {
        return number5;
    }

    public void setNumber5(int number5) {
        this.number5 = number5;
    }

    public int getNumber6() {
        return number6;
    }

    public void setNumber6(int number6) {
        this.number6 = number6;
    }

    public int getNumber7() {
        return number7;
    }

    public void setNumber7(int number7) {
        this.number7 = number7;
    }

    public void onSlideEnd(SlideEndEvent event) {
        FacesMessage msg = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void clickButton(){
        buttonClick = true;
    }
}
