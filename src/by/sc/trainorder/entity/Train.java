package by.sc.trainorder.entity;

/**
 * Created by User on 01.06.2016.
 */

import by.sc.trainorder.exception.DataCheckException;
import org.apache.log4j.Logger;

public class Train {

    private static final Logger LOG = Logger.getLogger(Train.class);

    private int trainNumber;
    private String trainName;
    private int trainCount;
    private String client;
    private static int trainNumberCount = 10000;

    private static final int MAX_TRAIN_NAME_SIZE = 20;
    private static final int MIN_TRAIN_NAME_SIZE = 4;

    public void setTrainName(String name) {
        if (name.length() >= MIN_TRAIN_NAME_SIZE && name.length() <= MAX_TRAIN_NAME_SIZE)
            trainName = name;
        else
            trainName = "Order # " + trainNumber;
    }

    public void setTrainCount(int count) {
        trainCount = count;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getTrainNumber() { return trainNumber; }
    public String getTrainName() { return trainName; }
    public String getClient() { return client; }
    public int getTrainCount() { return trainCount; }

    public Train (String name, String client, int count) throws DataCheckException{
        trainNumber = trainNumberCount++;
        this.client = client;
        if (count <= 0) throw new DataCheckException("Invalid train number!");
        trainCount = count;
        setTrainName(name);
    }

    public Train () {}

    @Override
    public String toString() {
        return  " [ " + trainNumber  + " : " + client + " : " + trainName + " : " + trainCount + " ]";
    }

}

