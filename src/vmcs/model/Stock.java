/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.model;

import java.util.Observable;

/**
 *
 * @author Dannel
 */
public abstract class Stock extends Observable {

    private String name;
    private double value;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        setChanged();
        notifyObservers();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setChanged();
        notifyObservers();
    }

    public void increaseCoin(){
        setQuantity(this.quantity+1);
    }
    public void decreaseCoin(){
        setQuantity(this.quantity-1);
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName()+ "{" + "name=" + name + ", value=" + value + ", quantity=" + quantity + '}';
    }

//	@Override
//	public boolean equals(Object obj) {
//		Stock stock = (Stock) obj;
//		if(this.name.equalsIgnoreCase(stock.getName())
//				&& this.value == stock.getValue()
//				&& this.quantity == stock.getQuantity())
//			return true;
//		else
//			return false;
//	}

    public boolean equals(Object obj) {
           Stock stock = (Stock) obj;
           if(this.name.equalsIgnoreCase(stock.getName()))
               return true;
           else
               return false;

    }

}
