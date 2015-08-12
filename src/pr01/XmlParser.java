package pr01;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class XmlParser {
    public List parse() {
        try {
            File file = new File("deposit.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(file);
            doc.getDocumentElement().normalize();

            List<Deposit> deposits = new ArrayList<Deposit>();
            NodeList depositList = doc.getDocumentElement().getChildNodes();


            for (int i = 0; i < depositList.getLength(); i++) {

                if (depositList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element depositElement = (Element) depositList.item(i);

                    String name = depositElement.getElementsByTagName("depositType").item(0).getTextContent();
                    String  customerNumber = (depositElement.getElementsByTagName("customerNumber").item(0).getTextContent());
                    BigDecimal depositBalance = new BigDecimal(depositElement.getElementsByTagName("depositBalance").item(0).getTextContent());
                    int durationInDays = Integer.parseInt(depositElement.getElementsByTagName("durationInDays").item(0).getTextContent());
                    try {
                        Deposit deposit = new Deposit(customerNumber, findDepositType(name), depositBalance, durationInDays);
                        deposit.calculateInterest();
                        deposits.add(deposit);
                    }
                    catch (DepositBalanceException e) {
                        System.out.println(e.getMessage());
                    } catch (DepositDurationException e) {
                        System.out.println(e.getMessage());
                    } catch (DepositTypeException e) {
                        System.out.println(e.getMessage());
                    }
                }}
                return deposits;


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public DepositType findDepositType(String name) throws DepositTypeException {
        try {
            Object instance = Class.forName("pr01." + name).newInstance();
            return (DepositType) instance;
        } catch (ClassNotFoundException e) {
            throw new DepositTypeException("Deposit Type incorrect");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}