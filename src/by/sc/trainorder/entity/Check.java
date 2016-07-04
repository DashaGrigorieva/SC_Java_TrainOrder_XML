package by.sc.trainorder.entity;

import java.util.ArrayList;

/**
 * Created by User on 03.06.2016.
 */
public class Check {

    private ArrayList<Order> orderList;

    public void setOrderList(ArrayList<Order> orderList) {this.orderList = orderList;}
    public ArrayList<Order> getOrderList() {return this.orderList;}

    public Check(ArrayList<Order> orderList) {this.orderList = orderList;}

    public void printCheck () {
            orderList.forEach(System.out::println);
    }

}
