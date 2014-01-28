package citFormatter;

import citExtract.Citation;
import citExtract.CitationFactory;
import citExtract.CitationFormatted;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;

/**
 * @author drygaay
 */
public class CitationFormatterFactoryTest {

    List<Citation> citations =  new ArrayList<>();
    
    public CitationFormatterFactoryTest() {
        Document doc1 = null; // structured abstract, no subsititutions
        Document doc2 = null; // abstract with \mu
        Document doc3 = null; // 2 abstracts: nonthing in 1st, h, greek and approx in 2nd 
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc1 = db.parse("./test/citExtract/16545112.xml");
            doc2 = db.parse("./test/citExtract/24015326.xml");
            doc3 = db.parse("./test/citExtract/21887247_124.xml");
        } catch (Exception e) {
            assertTrue(false);
        }
        List<Citation> citations1 = CitationFactory.createCitations(doc1);
        List<Citation> citations2 = CitationFactory.createCitations(doc2);
        List<Citation> citations3 = CitationFactory.createCitations(doc3);

        citations.addAll(citations1);
        citations.addAll(citations2);
        citations.addAll(citations3);
    }

    @Test
    public void testFormat() {
            ReferenceFormatter refFormatter = new ReferenceFormatter();
        AbstractFormatter absFormatter = new AbstractFormatter(); 
        CitationFormatterFactory instance = 
                new CitationFormatterFactory(refFormatter, absFormatter);
        List<CitationFormatted>  result = instance.format(citations);
        assertTrue(result.size() == 4);
    }
}