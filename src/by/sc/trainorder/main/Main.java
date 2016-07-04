package by.sc.trainorder.main;

import by.sc.trainorder.entity.Check;
import by.sc.trainorder.entity.Order;
import by.sc.trainorder.entity.Train;
import by.sc.trainorder.entity.WagonTrain;
import by.sc.trainorder.exception.DataCheckException;
import by.sc.trainorder.parser.FileParser;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws DataCheckException, IOException {

        Train trainPass = null;
        {
            try {
                trainPass = new Train("Passenger", "Minsk Central Station", 4);
            } catch (DataCheckException dataCheckException) {
                dataCheckException.printStackTrace();
            }
        }
        Train trainReg = null;
        {
            try {
                trainReg = new Train("Regional2", "Gomel Station", 2);
            } catch (DataCheckException dataCheckException) {
                dataCheckException.printStackTrace();
            }
        }
        System.out.println(trainPass);
        System.out.println(trainReg);

        ArrayList<String> wagon = new ArrayList<String>();
        wagon.add("l");
        wagon.add("5");
        wagon.add("o");
        wagon.add("3");
        wagon.add("vr");
        wagon.add("4");
        wagon.add("b");
        wagon.add("2");

        WagonTrain wagonTrain = new WagonTrain(trainReg, wagon);

        Order order = new Order(wagonTrain);
        System.out.println(order);

        FileParser fileParser = null;
        try {
            fileParser = new FileParser("Data/input.txt");
        } catch (DataCheckException e) {
            System.out.println(e.getMessage());
        }

        if (fileParser != null) {
            Check check = new Check(fileParser.getOrders());
            check.printCheck();
        }
    }
}
