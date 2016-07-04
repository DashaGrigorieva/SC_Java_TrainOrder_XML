package by.sc.trainorder.entity;

import by.sc.trainorder.exception.DataCheckException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 02.06.2016.
 */
public class WagonTrain {

    private Train train;
    private HashMap<String, Integer> wagon;
    private int wagonCount;

    private static final String LOCOMOTIVE = "l";
    private static final String TOTAL_CAR = "o";
    private static final String RESTAURANT_CAR = "vr";
    private static final String COMPARTMENT_CAR = "k";
    private static final String ECONOM_CAR = "p";
    private static final String EXTRA_CLASS_CAR = "sv";
    private static final String BAGGAGE_CAR = "b";

    private static final int MAX_WAGON_COUNT = 30;

    public Train getTrain() {
        return train;
    }

    public int getWagonCount() {
        return wagonCount;
    }

    public HashMap<String, Integer> getWagon() {
        return wagon;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void addWagon(ArrayList<String> cars) throws DataCheckException {
        if ((cars.size() % 2) != 0) throw new DataCheckException("Invalid wagon count data!");
        wagon.put(LOCOMOTIVE, 1);
        for (int i = 0; i < cars.size(); i = i + 2) {
            try {
                int carCount = Integer.parseInt(cars.get(i + 1));
                if ((wagonCount + carCount <= MAX_WAGON_COUNT)) {
                    if (wagon.containsKey(cars.get(i)) && (cars.get(i) != RESTAURANT_CAR)) {
                        wagon.put(cars.get(i), wagon.get(cars.get(i)) + carCount);
                    } else
                        switch (cars.get(i).toLowerCase()) {
                            case LOCOMOTIVE:
                                wagon.put(LOCOMOTIVE, carCount);
                                break;
                            case TOTAL_CAR:
                                wagon.put(TOTAL_CAR, carCount);
                                break;
                            case COMPARTMENT_CAR:
                                wagon.put(COMPARTMENT_CAR, carCount);
                                break;
                            case ECONOM_CAR:
                                wagon.put(ECONOM_CAR, carCount);
                                break;
                            case EXTRA_CLASS_CAR:
                                wagon.put(EXTRA_CLASS_CAR, carCount);
                                break;
                            case BAGGAGE_CAR:
                                wagon.put(BAGGAGE_CAR, carCount);
                                break;
                            case RESTAURANT_CAR:
                                wagon.put(RESTAURANT_CAR, 1);
                                break;
                            default:
                                break;
                        }
                    findWagonCount();
                } else
                    System.out.println("Too big order. Max count of wagons = " + MAX_WAGON_COUNT);
            } catch (NumberFormatException e) {
                System.out.println("Invalid data format!");
            }
        }
        findWagonCount();
    }

    public void addWagon(String carType, int carCount) {
        wagon.put(LOCOMOTIVE, 1);
        if ((wagonCount + carCount <= MAX_WAGON_COUNT)) {
            if (wagon.containsKey(carType) && (carType != RESTAURANT_CAR)) {
                wagon.put(carType, wagon.get(carType) + carCount);
            } else
                switch (carType.toLowerCase()) {
                    case LOCOMOTIVE:
                        wagon.put(LOCOMOTIVE, carCount);
                        break;
                    case TOTAL_CAR:
                        wagon.put(TOTAL_CAR, carCount);
                        break;
                    case COMPARTMENT_CAR:
                        wagon.put(COMPARTMENT_CAR, carCount);
                        break;
                    case ECONOM_CAR:
                        wagon.put(ECONOM_CAR, carCount);
                        break;
                    case EXTRA_CLASS_CAR:
                        wagon.put(EXTRA_CLASS_CAR, carCount);
                        break;
                    case BAGGAGE_CAR:
                        wagon.put(BAGGAGE_CAR, carCount);
                        break;
                    case RESTAURANT_CAR:
                        wagon.put(RESTAURANT_CAR, 1);
                        break;
                    default:
                        break;
                }
            findWagonCount();
        } else
            System.out.println("Too big order. Max count of wagons = " + MAX_WAGON_COUNT);
        findWagonCount();
    }

    public WagonTrain(Train train, ArrayList<String> cars) throws DataCheckException {
        try {
            wagon = new HashMap<String, Integer>();
            addWagon(cars);
            this.train = train;
        } catch (DataCheckException e) {
            System.out.println(e.getMessage());
        }
    }

    public WagonTrain(Train train, String carType, int carCount) throws DataCheckException {
        wagon = new HashMap<String, Integer>();
        this.train = train;
        addWagon(carType, carCount);
    }

    private void findWagonCount() {
        for (int value : wagon.values()) {
            wagonCount += value;
        }
    }
}


