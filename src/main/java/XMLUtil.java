import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class XMLUtil {

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
            LOGGER.throwing(XMLUtil.class.getName(), "JAXBException while generation xml for: " + data.getClass(), e);
        }
        return xml;
    }

}
