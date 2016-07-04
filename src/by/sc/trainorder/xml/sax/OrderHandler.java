package by.sc.trainorder.xml.sax;

import by.sc.trainorder.entity.Order;
import by.sc.trainorder.entity.Train;
import by.sc.trainorder.exception.DataCheckException;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Created by User on 22.06.2016.
 */
public class OrderHandler extends DefaultHandler {

    private static final Logger LOG = Logger.getLogger(OrderHandler.class);

    private ArrayList<Order> orders;
    private Order curOrder = null;
    private Train curTrain = null;
    private OrderEnum currentEnum = null;
    private EnumSet<OrderEnum> orderEnum;
    private ArrayList<String> wagons;

    public OrderHandler() {
        orders = new ArrayList<Order>();
        orderEnum = EnumSet.range(OrderEnum.NAME, OrderEnum.COUNT);
    }
    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if("orders".equals(localName)) {
            curTrain = new Train();
        }
        else {
            OrderEnum temp = OrderEnum.valueOf(localName.toUpperCase());
            if(orderEnum.contains(temp)) {
                currentEnum = temp;
            }
            if(temp.equals(OrderEnum.WAGONS)) {
                wagons = new ArrayList<String>();
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if("order".equals(localName)) {
            try {
                curOrder = new Order(curTrain.getTrainName(), curTrain.getClient(), curTrain.getTrainCount(), wagons);
            } catch (DataCheckException e) {
                LOG.error(e.getMessage());
            }
            orders.add(curOrder);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if(currentEnum != null) {
            switch(currentEnum) {
                case NAME:
                    curTrain.setTrainName(s);
                    break;
                case CLIENT:
                    curTrain.setClient(s);
                    break;
                case NUMBER:
                    curTrain.setTrainCount(Integer.parseInt(s));
                    break;
                case TYPE:
                    wagons.add(s);
                    break;
                case COUNT:
                    wagons.add(s);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }

}
