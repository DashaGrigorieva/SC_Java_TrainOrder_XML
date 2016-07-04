package by.sc.trainorder.xml.sax;

import by.sc.trainorder.entity.Order;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;;

/**
 * Created by User on 22.06.2016.
 */
public class SaxBuilder {

    private static final Logger LOG = Logger.getLogger(SaxBuilder.class);

    private ArrayList<Order> orders;
    private OrderHandler handler;
    private XMLReader reader;

    public SaxBuilder() throws SAXException {
        handler = new OrderHandler();
        reader = XMLReaderFactory.createXMLReader();
        reader.setContentHandler(handler);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void buildSetOrders(String fileName) {
        try{
            reader.parse(fileName);
        } catch(SAXException e) {
            LOG.error("ошибка SAX парсера: " + e);
        } catch(IOException e) {
            LOG.error("ошибка I/О потока: " + e);
        }
        orders = handler.getOrders();
    }
}