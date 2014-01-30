package citFormatter;

import citExtract.Citation;
import citExtract.CitationFactory;
import citExtract.Reference;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
    Reference ref3;
    Reference ref4;
    Reference ref5;
    ReferenceFormatter refFormatter;

    public ReferenceFormatterTest() {
        Document doc1 = null; // structured abstract, no subsititutions
        Document doc2 = null; // abstract with \mu
        Document doc3 = null; // 2 author ref
        Document doc4 = null; // 1 author ref
        Document doc5 = null; // only collective name for authors no real names
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc1 = db.parse("./test/citExtract/16545112.xml");
            doc2 = db.parse("./test/citExtract/24015326.xml");
            doc3 = db.parse("./test/citExtract/20836533.xml");
            doc4 = db.parse("./test/citExtract/6582500.xml");
            doc5 = db.parse("./test/citExtract/24259429.xml");
        } catch (Exception e) {
            assertTrue(false);
        }
        ArrayList<Citation> citations = CitationFactory.createCitations(doc1);
        ref1 = citations.get(0).getReference();
        ArrayList<Citation> citations2 = CitationFactory.createCitations(doc2);
        ref2 = citations2.get(0).getReference();
        ArrayList<Citation> citations3 = CitationFactory.createCitations(doc3);
        ref3 = citations3.get(0).getReference();
        ArrayList<Citation> citations4 = CitationFactory.createCitations(doc4);
        ref4 = citations4.get(0).getReference();
        ArrayList<Citation> citations5 = CitationFactory.createCitations(doc5);
        ref5 = citations5.get(0).getReference();

        refFormatter = new ReferenceFormatter();
    }

    @Test
    public void testRef1Format() {
        String result = refFormatter.format(ref1);
        // author list; journal volume(issue): pages (year)
        String expResult =
                "Snyder KA et al; BMC Bioinformatics 7: 152 (2006)";
        assertEquals(expResult, result);
    }

    @Test
    public void testRef2Format() {
        String result = refFormatter.format(ref2);
        String expResult =
                "Bondada L et al; ACS Med Chem Lett 4(8): 747-751 (2013)";
        assertEquals(expResult, result);
    }

    @Test
    public void testRef3Format() {
        String result = refFormatter.format(ref3);
        String expResult =
                "Dryga A, Warshel A; J Phys Chem B 114(39): 12720-8 (2010)";
        assertEquals(expResult, result);
    }

    @Test
    public void testRef4Format() {
        String result = refFormatter.format(ref4);
        String expResult =
                "Warshel A; Proc. Natl. Acad. Sci. U.S.A. 81(2): 444-8 (1984)";
        assertEquals(expResult, result);
    }

    @Test
    public void testRef5Format() {
        String result = refFormatter.format(ref5);
        String expResult =
                "NCBI Resource Coordinators; Nucleic Acids Res. 42(1): D7-D17 (2014)";
        assertEquals(expResult, result);
    }
}