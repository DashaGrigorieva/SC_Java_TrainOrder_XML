package by.sc.trainorder.parser;

import by.sc.trainorder.entity.Order;
import by.sc.trainorder.exception.DataCheckException;
import by.sc.trainorder.input.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 03.06.2016.
 */
public class FileParser {

    private ArrayList<Order> orders = new ArrayList<Order>();

    public void setOrders(ArrayList<Order> orders) {this.orders = orders;}
    public ArrayList<Order> getOrders() {return orders;}

    public FileParser (String fileName) throws IOException, DataCheckException {

        ArrayList<String> parsedFile = new ReadFromFile(fileName).getParsedFile();
        String[] splitLine;

        for (String line : parsedFile) {
            splitLine = line.split(" ");
            String trainName = null;
            String client = null;
            int trainCount = 0;
            ArrayList<String> wagon = null;
            try {
                if (splitLine.length < 5) throw new DataCheckException("Invalid parameters number for train order!");
                trainName = splitLine[0];
                client = splitLine[1];
                trainCount = Integer.parseInt(splitLine[2]);
                wagon = new ArrayList<String>();
                for (int i = 3; i < splitLine.length; i++)
                    wagon.add(splitLine[i]);
                if (wagon.size()%2 != 0) throw new DataCheckException("Invalid parameters for wagons!");
                Order order = new Order(trainName, client, trainCount, wagon);
                orders.add(order);
            } catch (NumberFormatException | DataCheckException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
