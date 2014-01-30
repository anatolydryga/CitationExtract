package citFormatter;

import citExtract.Abstract;
import citExtract.Citation;
import citExtract.CitationFactory;
import citExtract.FileReader;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import substitution.Substitution;
import substitution.SubstitutionList;

public class AbstractFormatterTest {

    Abstract abs1;
    Abstract abs2;
    Abstract abs3;
    Abstract abs4;
    AbstractFormatter absFormatter = new AbstractFormatter();

    public AbstractFormatterTest() {
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
        List<Citation> citations = CitationFactory.createCitations(doc1);
        abs1 = citations.get(0).getAbstract();
        List<Citation> citations2 = CitationFactory.createCitations(doc2);
        abs2 = citations2.get(0).getAbstract();
        List<Citation> citations3 = CitationFactory.createCitations(doc3);
        abs3 = citations3.get(0).getAbstract(); // PMID 124
        abs4 = citations3.get(1).getAbstract(); // PMID 21887247
    }

    @Test 
    public void testAbstracts() {
        testAbstract("./test/citExtract/16545112_abstract.dat", abs1);
        testAbstract("./test/citExtract/24015326_abstract.dat", abs2);
        testAbstract("./test/citExtract/124_abstract.dat"     , abs3);
        testAbstract("./test/citExtract/21887247_abstract.dat", abs4);
    }

    private void testAbstract(String abstractPath, Abstract abstractForFormat) {
        try {
            String expResult = FileReader.readFile(abstractPath);
            List<Substitution> subs = absFormatter.format(abstractForFormat);
            String result = SubstitutionList.getSubstitutionText(subs);
            assertEquals(expResult, result);
        } catch (IOException e) {
            fail("cannot read abstract file for the test: " + e);
        }
    }
}