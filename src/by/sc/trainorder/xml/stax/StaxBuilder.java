package by.sc.trainorder.xml.stax;

import by.sc.trainorder.entity.Order;
import by.sc.trainorder.entity.Train;
import by.sc.trainorder.exception.DataCheckException;
import by.sc.trainorder.xml.sax.OrderEnum;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 22.06.2016.
 */
public class StaxBuilder {

    private static final Logger LOG = Logger.getLogger(StaxBuilder.class);

    private HashSet<Order> orders = new HashSet<Order>();
    private XMLInputFactory inputFactory;

    public StaxBuilder() throws XMLStreamException {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Order> getOrderts() {
        return orders;
    }

    public void buildSetOrders(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try{
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while(reader.hasNext()) {
                int type = reader.next();
                if(type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if(OrderEnum.valueOf(name.toUpperCase()) == OrderEnum.ORDER) {
                        Order order = buildOrder(reader);
                        orders.add(order);
                    }
                }
            }
        } catch(XMLStreamException ex) {
            LOG.error("StAX parsing error! " + ex.getMessage());
        } catch(FileNotFoundException ex) {
            LOG.error("File " + fileName + " not found! " + ex);
        } catch (DataCheckException e) {
            LOG.error(e.getMessage());
        } finally{
            try{
                if(inputStream != null) {
                    inputStream.close();
                }
            } catch(IOException e) {
                LOG.error("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Order buildOrder(XMLStreamReader reader) throws XMLStreamException, DataCheckException {
//        Student st = newStudent();
        Train train = new Train();
        ArrayList<String> wagons = new ArrayList<String>();
        String name;
        while(reader.hasNext()) {
            int type = reader.next();
            switch(type) {
                case XMLStreamConstants.START_ELEMENT:
                name = reader.getLocalName();
                switch(OrderEnum.valueOf(name.toUpperCase())) {
                    case NAME:
                        train.setTrainName(getXMLText(reader));
                        break;
                    case NUMBER:
                        name = getXMLText(reader);
                        train.setTrainCount(Integer.parseInt(name));
                        break;
                    case CLIENT:
                        train.setClient(getXMLText(reader));
                        break;
                    /*case WAGON:
                        wagons.add(getXMLWagon(reader));
                        break;*/
                    case TYPE:
                        wagons.add(getXMLText(reader));
                        break;
                    case COUNT:
                        wagons.add(getXMLText(reader));
                        break;
                }
                break;
                case XMLStreamConstants.END_ELEMENT:
                name = reader.getLocalName();
                if(OrderEnum.valueOf(name.toUpperCase()) == OrderEnum.ORDER) {
                    Order order = new Order(train.getTrainName(), train.getClient(), train.getTrainCount(), wagons);
                    return order;
                }
                break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Student");
    }

 /*   private String getXMLWagon(XMLStreamReader reader)
    throwsXMLStreamException {
        Student.Address address = newStudent.Address();
        inttype;
        String name;
        while(reader.hasNext()) {
            type = reader.next();
            switch(type) {
                caseXMLStreamConstants.START_ELEMENT:
                name = reader.getLocalName();
                switch(StudentEnum.valueOf(name.toUpperCase())) {
                    case COUNTRY:      address.setCountry(getXMLText(reader));
                        break;
                    case CITY:      address.setCity(getXMLText(reader));
                        break;
                    case STREET:      address.setStreet(getXMLText(reader));
                        break;
                }
                break;
                caseXMLStreamConstants.END_ELEMENT:
                name = reader.getLocalName();
                if(StudentEnum.valueOf(name.toUpperCase()) ==
                        StudentEnum.ADDRESS) {
                    returnaddress;
                }
                break;
            }
        }
        throw newXMLStreamException("Unknown element in tag Address");
    }*/

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if(reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
