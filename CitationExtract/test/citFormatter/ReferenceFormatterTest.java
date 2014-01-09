package citFormatter;

import citExtract.Citation;
import citExtract.CitationFactory;
import citExtract.Reference;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;

/**
 *
 * @author drygaay
 */
public class ReferenceFormatterTest {

    Reference ref1;
    Reference ref2;
    ReferenceFormatter refFormatter;

    public ReferenceFormatterTest() {
        Document doc1 = null; // structured abstract, no subsititutions
        Document doc2 = null; // abstract with \mu
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc1 = db.parse("./test/citExtract/16545112.xml");
            doc2 = db.parse("./test/citExtract/24015326.xml");
        } catch (Exception e) {
            assertTrue(false);
        }
        ArrayList<Citation> citations = CitationFactory.createCitations(doc1);
        ref1 = citations.get(0).getReference();
        ArrayList<Citation> citations2 = CitationFactory.createCitations(doc2);
        ref2 = citations2.get(0).getReference();

        refFormatter = new ReferenceFormatter();
    }

    @Test
    public void testRef1Format() {
        String result = refFormatter.format(ref1);
        // author list. journal, volume, issue, year; pages
        String expResult =
                "Snyder KA, Feldman HJ, Dumontier M, Salama JJ,"
                + " Hogue CW. BMC Bioinformatics, 7, 2006; 152";
        assertEquals(expResult, result);
    }

    @Test
    public void testRef2Format() {
        String result = refFormatter.format(ref2);
        // author list. journal, volume, issue, year; pages
        String expResult =
                "Bondada L, Detorio M, Bassit L, Tao S, Montero CM, "
                + "Singletary TM, Zhang H, Zhou L, Cho JH, Coats SJ, "
                + "Schinazi RF. ACS Med Chem Lett, 4, 8, 2013; 747-751";
        assertEquals(expResult, result);
    }
}