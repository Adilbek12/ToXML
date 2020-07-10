import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class XMLUtil {

    private final static Logger LOGGER = Logger.getLogger(XMLUtil.class.getName());

    public static String toXML(Object data) {
        String xml = "";
        try {
            LOGGER.info("Generating xml for: " + data.getClass());
            JAXBContext jaxbContext = JAXBContext.newInstance(data.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(data, sw);
            xml = sw.toString();
        } catch (JAXBException e) {
            LOGGER.error("JAXBException while generation xml for: " + data.getClass(), e);
        }
        return xml;
    }

}
