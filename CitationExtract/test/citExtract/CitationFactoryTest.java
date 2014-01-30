package citExtract;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.w3c.dom.*;
import java.net.*;
import java.util.List;
import javax.xml.parsers.*;

public class CitationFactoryTest {

    Document doc1;
    Document doc2;
    Document doc3;

    public CitationFactoryTest() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc1 = db.parse("./test/citExtract/16545112.xml"); // structured abstract, no subsititutions
            doc2 = db.parse("./test/citExtract/24015326.xml"); // abstract with \mu
            doc3 = db.parse("./test/citExtract/21887247_124.xml"); // 2 articles: one without anything; second with h, greek and approx
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void structuredAbstractParsing() {
        ArrayList<Citation> citations = CitationFactory.createCitations(doc1);
        assertNotNull(citations);
        assertEquals(citations.size(), 1);
        Citation citation = citations.get(0);
        assertEquals(citation.getPmid(), 16545112);
        assertNotNull(citation.getAbstract());
    }

    @Test
    public void abstractWith1SubstitutionParsing() {
        ArrayList<Citation> citations = CitationFactory.createCitations(doc2);
        assertNotNull(citations);
        assertEquals(citations.size(), 1);
        Citation citation = citations.get(0);
        assertEquals(citation.getPmid(), 24015326);
        assertNotNull(citation.getAbstract());
    }

    @Test
    public void twoArticles() {
        ArrayList<Citation> citations = CitationFactory.createCitations(doc3);
        assertNotNull(citations);
        assertEquals(citations.size(), 2);

        Citation citation1 = citations.get(0);
        assertTrue(citation1.getPmid() == 21887247 || citation1.getPmid() == 124);
        assertNotNull(citation1.getAbstract());

        Citation citation2 = citations.get(1);
        assertTrue(citation2.getPmid() == 21887247 || citation2.getPmid() == 124);
        assertNotNull(citation2.getAbstract());

        assertTrue(citation1.getPmid() != citation2.getPmid());
    }

    @Test
    public void abs1() {
        List<Citation> citations = CitationFactory.createCitations(doc1);
        Abstract abst = citations.get(0).getAbstract();
        assertEquals(abst.getSectionsText().size(), 3);
        assertEquals(abst.getSectionsLabel().size(), 3);

        // we add \n for each section of structured abstract
        assertEquals(abst.getSectionsText().get(0).length(), 740);
        assertEquals(abst.getSectionsLabel().get(0), "BACKGROUND");

        assertEquals(abst.getSectionsText().get(1).length(), 1193);
        assertEquals(abst.getSectionsLabel().get(1), "DESCRIPTION");

        assertEquals(abst.getSectionsLabel().get(2), "CONCLUSION");
    }

    @Test
    public void abs2() {
        List<Citation> citations = CitationFactory.createCitations(doc2);
        Abstract abst = citations.get(0).getAbstract();
        assertEquals(abst.getSectionsText().size(), 1);
        assertEquals(abst.getSectionsLabel().size(), 1);
        assertEquals(abst.getSectionsText().get(0).length(), 1178);
        assertEquals(abst.getSectionsLabel().get(0), "");
    }

    @Test
    public void abs3twoArticles() {
        List<Citation> citations = CitationFactory.createCitations(doc3);
        Abstract abst1 = citations.get(0).getAbstract();
        Abstract abst2 = citations.get(1).getAbstract();

        assertEquals(abst1.getSectionsText().size(), 1);
        assertEquals(abst1.getSectionsText().size(), 1);

        assertEquals(abst2.getSectionsLabel().size(), 3);
        assertEquals(abst2.getSectionsLabel().size(), 3);
    }

    @Test
    public void ref1() {
        List<Citation> citations = CitationFactory.createCitations(doc1);
        Reference ref = citations.get(0).getReference();
        assertNotNull(ref);
        assertEquals(ref.getYear(), 2006);
        assertEquals(ref.getVolume(), "7");
        assertEquals(ref.getIssue(), "");
        assertEquals(ref.getJournal(), "BMC Bioinformatics");
        assertEquals(ref.getPages(), "152");
        assertEquals(ref.getNumberOfAuthors(), 5);
        ArrayList< String> lastNames = ref.getAuthorLastNames();
        ArrayList< String> initials = ref.getAuthorInitials();
        assertEquals(lastNames.get(0), "Snyder");
        assertEquals(initials.get(0), "KA");
        assertEquals(lastNames.get(4), "Hogue");
        assertEquals(initials.get(4), "CW");
    }

    @Test
    public void ref2() {
        List<Citation> citations = CitationFactory.createCitations(doc2);
        Reference ref = citations.get(0).getReference();
        assertNotNull(ref);
        assertEquals(ref.getYear(), 2013);
        assertEquals(ref.getVolume(), "4");
        assertEquals(ref.getIssue(), "8");
        assertEquals(ref.getJournal(), "ACS Med Chem Lett");
        assertEquals(ref.getPages(), "747-751");
        assertEquals(ref.getNumberOfAuthors(), 11);
        ArrayList< String> lastNames = ref.getAuthorLastNames();
        ArrayList< String> initials = ref.getAuthorInitials();
        assertEquals(lastNames.get(1), "Detorio");
        assertEquals(initials.get(1), "M");
        assertEquals(lastNames.get(4), "Montero");
        assertEquals(initials.get(4), "CM");

    }

    @Test
    public void ref3twoArticles() {
        List<Citation> citations = CitationFactory.createCitations(doc3);
        Reference ref1 = citations.get(0).getReference();
        Reference ref2 = citations.get(1).getReference();
        assertNotNull(ref1);
        assertNotNull(ref2);
        Reference ref124;
        Reference ref2188;
        if (citations.get(0).getPmid() == 124) {
            ref124 = ref1;
            ref2188 = ref2;
        } else {
            ref124 = ref2;
            ref2188 = ref1;
        }

        assertEquals(ref124.getYear(), 1975);
        assertEquals(ref124.getVolume(), "4");
        assertEquals(ref124.getIssue(), "5990");
        assertEquals(ref124.getJournal(), "Br Med J");
        assertEquals(ref124.getPages(), "200-2");
        assertEquals(ref124.getNumberOfAuthors(), 6);

        assertEquals(ref2188.getYear(), 2011);
        assertEquals(ref2188.getVolume(), "6");
        assertEquals(ref2188.getIssue(), "8");
        assertEquals(ref2188.getJournal(), "PLoS ONE");
        assertEquals(ref2188.getPages(), "e23388");
        assertEquals(ref2188.getNumberOfAuthors(), 5);
    }
}