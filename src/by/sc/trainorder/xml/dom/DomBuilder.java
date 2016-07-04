package by.sc.trainorder.xml.dom;

import by.sc.trainorder.entity.Order;
import by.sc.trainorder.entity.Train;
import by.sc.trainorder.exception.DataCheckException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 22.06.2016.
 */
public class DomBuilder {

    private static final Logger LOG = Logger.getLogger(DomBuilder.class);

    private Set<Order> orders;
    private DocumentBuilder docBuilder;

    public DomBuilder() {
        this.orders = new HashSet<Order>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            docBuilder = factory.newDocumentBuilder();
        } catch(ParserConfigurationException e) {
            LOG.error("Ошибка конфигурации парсера: " + e);
        }
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void buildSetStudents(String fileName) {
        Document doc= null;
        try{
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList studentsList = root.getElementsByTagName("order");
            for(int i = 0; i < studentsList.getLength(); i++) {
                Element orderElement = (Element) studentsList.item(i);
                Order order = buildOrder(orderElement);
                orders.add(order);
            }
        } catch(IOException e) {
            LOG.error("File error or I/O error: " + e);
        } catch(SAXException e) {
            LOG.error("Parsing failure: " + e);
        } catch (DataCheckException e) {
            LOG.error(e.getMessage());
        }
    }

    private Order buildOrder(Element orderElement) throws DataCheckException {
        Train train = new Train();
        train.setTrainName(getElementTextContent(orderElement, "name"));
        train.setClient(getElementTextContent(orderElement, "client"));
        Integer number = Integer.parseInt(getElementTextContent(orderElement, "number"));
        train.setTrainCount(number);
        ArrayList<String> wagon = new ArrayList<String>();

        Element wagonsElement = (Element) orderElement.getElementsByTagName("wagons").item(0);
        NodeList wagonList = wagonsElement.getElementsByTagName("wagon");
        for (int i = 0; i < wagonList.getLength(); i++) {
            wagon.add(getElementTextContent((Element) wagonList.item(i), "type"));
            wagon.add(getElementTextContent((Element)wagonList.item(i), "count"));
        }

        Order order = new Order(train.getTrainName(), train.getClient(), train.getTrainCount(), wagon);
        return order;
        }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

}
