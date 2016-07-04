package by.sc.trainorder.xml.sax;

/**
 * Created by User on 22.06.2016.
 */
public enum OrderEnum {

    ORDERS("orders"),
    ORDER("order"),
    WAGONS("wagon"),
    WAGON("wagon"),
    NAME("name"),
    CLIENT("client"),
    NUMBER("number"),
    TYPE("type"),
    COUNT("count");

    private String value;
    private OrderEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

}
