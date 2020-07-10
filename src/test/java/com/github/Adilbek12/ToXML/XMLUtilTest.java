package com.github.Adilbek12.ToXML;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class XMLUtilTest {
    private XMLTestEntity xmlTestEntity;

    @Before
    public void setUp() {
        XMLTestInnerEntity innerEntity = new XMLTestInnerEntity();
        innerEntity.fieldThree = 10.532f;
        innerEntity.fieldFour = true;

        xmlTestEntity = new XMLTestEntity();
        xmlTestEntity.fieldOne = "fieldOneValue";
        xmlTestEntity.fieldTwo = new BigDecimal("2");
        xmlTestEntity.innerEntity = innerEntity;
    }

    @After
    public void tearDown() {
        xmlTestEntity = null;
    }

    @Test
    public void testToXml() {
        String xml = XMLUtil.toXML(xmlTestEntity);

        String expectedXML =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<testEntity fieldOne=\"fieldOneValue\">\n" +
                        "    <fieldTwo_>2</fieldTwo_>\n" +
                        "    <innerEntity fieldFour=\"true\">\n" +
                        "        <fieldThree>10.532</fieldThree>\n" +
                        "    </innerEntity>\n" +
                        "</testEntity>\n";

        assertNotNull(xml);
        assertFalse(xml.isEmpty());
        assertEquals(expectedXML, xml);
    }

    @SuppressWarnings("unused")
    @XmlRootElement(name = "testEntity")
    public static class XMLTestEntity {
        @XmlAttribute(name = "fieldOne")
        private String fieldOne;
        @XmlElement(name = "fieldTwo_")
        private BigDecimal fieldTwo;
        @XmlElement(name = "innerEntity")
        private XMLTestInnerEntity innerEntity;
    }

    @SuppressWarnings("unused")
    public static class XMLTestInnerEntity {
        @XmlElement(name = "fieldThree")
        private float fieldThree;
        @XmlAttribute(name = "fieldFour")
        private boolean fieldFour;
    }
}
