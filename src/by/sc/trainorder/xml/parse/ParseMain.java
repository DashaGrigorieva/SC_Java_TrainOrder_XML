package by.sc.trainorder.xml.parse;

import by.sc.trainorder.entity.Order;
import by.sc.trainorder.xml.dom.DomBuilder;
import by.sc.trainorder.xml.sax.SaxBuilder;
import by.sc.trainorder.xml.stax.StaxBuilder;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;

/**
 * Created by User on 22.06.2016.
 */
public class ParseMain {

    public static void main(String[] args) throws XMLStreamException, SAXException {

        System.out.println("\n\n\n ***  SAX Parser  ***\n");
        SaxBuilder saxBuilder = new SaxBuilder();
        saxBuilder.buildSetOrders("data/train.xml");
        System.out.println(saxBuilder.getOrders());

        System.out.println("\n\n\n ***  DOM Parser  ***\n");
        DomBuilder domBuilder = new DomBuilder();
        domBuilder.buildSetStudents("data/train.xml");
        System.out.println(domBuilder.getOrders());

        System.out.println("\n\n\n ***  StAX Parser  ***\n");
        StaxBuilder staxBuilder = new StaxBuilder();
        staxBuilder.buildSetOrders("data/train.xml");
        System.out.println(staxBuilder.getOrderts());

    }

}
