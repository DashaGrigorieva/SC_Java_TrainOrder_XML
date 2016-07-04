package by.sc.trainorder.entity;

import by.sc.trainorder.exception.DataCheckException;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by User on 02.06.2016.
 */
public class Order {

    private WagonTrain wagonTrain;
    private String wagonCheck = "";

    private static final String LOCOMOTIVE = "l";
    private static final String TOTAL_CAR = "o";
    private static final String RESTAURANT_CAR = "vr";
    private static final String COMPARTMENT_CAR = "k";
    private static final String ECONOM_CAR = "p";
    private static final String EXTRA_CLASS_CAR = "sv";
    private static final String BAGGAGE_CAR = "b";

    private static final int LOCOMOTIVE_PRICE = 10000;
    private static final int TOTAL_CAR_PRICE = 2000;
    private static final int RESTAURANT_CAR_PRICE = 5000;
    private static final int COMPARTMENT_CAR_PRICE = 7000;
    private static final int ECONOM_CAR_PRICE = 4000;
    private static final int EXTRA_CLASS_CAR_PRICE = 7000;
    private static final int BAGGAGE_CAR_PRICE = 1000;
    private static final int WORK_PRICE = 800;

    public Order(WagonTrain wagonTrain) {
        this.wagonTrain = wagonTrain;
        calcTrainPrice();
    }

    public Order(String name, String client, int count, ArrayList<String> cars) throws DataCheckException {
        try {
            Train train = new Train(name, client, count);
            this.wagonTrain = new WagonTrain(train, cars);
            calcTrainPrice();
        } catch (DataCheckException e) {
            e.getMessage();
        }
    }

    public int calcTrainPrice() {
        int result = 0;
        for (String key : wagonTrain.getWagon().keySet()) {
            int value = wagonTrain.getWagon().get(key);
            switch (key) {
                case LOCOMOTIVE:
                    result += value*LOCOMOTIVE_PRICE;
                    wagonCheck += "   Lokomotive car : " + value + " : " + value*LOCOMOTIVE_PRICE + "\n";
                    break;
                case TOTAL_CAR:
                    result += value*TOTAL_CAR_PRICE;
                    wagonCheck += "   Total car : " + value + " : " + value*TOTAL_CAR_PRICE + "\n";
                    break;
                case COMPARTMENT_CAR:
                    result += value*COMPARTMENT_CAR_PRICE;
                    wagonCheck += "   Compartment car : " + value + " : " + value*COMPARTMENT_CAR_PRICE + "\n";
                    break;
                case ECONOM_CAR:
                    result += value*ECONOM_CAR_PRICE;
                    wagonCheck += "   Econom-class car : " + value + " : " + value*EXTRA_CLASS_CAR_PRICE + "\n";
                    break;
                case EXTRA_CLASS_CAR:
                    result += value*EXTRA_CLASS_CAR_PRICE;
                    wagonCheck += "   Estra-class car : " + value + " : " + value*EXTRA_CLASS_CAR_PRICE + "\n";
                    break;
                case BAGGAGE_CAR:
                    result += value*BAGGAGE_CAR_PRICE;
                    wagonCheck += "   Baggage car : " + value + " : " + value*BAGGAGE_CAR_PRICE + "\n";
                    break;
                case RESTAURANT_CAR:
                    result += RESTAURANT_CAR_PRICE;
                    wagonCheck += "   Restaurant car : " + value + " : " + value*RESTAURANT_CAR_PRICE + "\n";
                    break;
                default:
                    break;
            }
        }
        return result;
    }
    public int calcResultPrice() {
        return calcTrainPrice()*wagonTrain.getTrain().getTrainCount() + WORK_PRICE;
    }

    public void print(ArrayList<Order> orderList) {
        orderList.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return  "\n******************************************\n" +
                " Order # " + wagonTrain.getTrain().getTrainNumber() + "\n" +
                " Client : " + wagonTrain.getTrain().getClient() + "\n" +
                " Train name : " + wagonTrain.getTrain().getTrainName() + "\n" +
                "------------------------------------------\n" +
                " Wagons : \n" + wagonCheck +
                "------------------------------------------\n" +
                " Train cost : " + calcTrainPrice() + "\n" +
                " Number of trains : " + wagonTrain.getTrain().getTrainCount() + "\n" +
                " Order formalization : " + WORK_PRICE + "\n" +
                "------------------------------------------\n" +
                " Total cost : " + calcResultPrice() +
                "\n******************************************\n";
    }
}
