/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package citExtract;

import org.w3c.dom.*;
import java.net.*;
import javax.xml.parsers.*;
/**
    download XML for citations.

    creates URL for eUtils based on array of integers that represents PMIDs
    and download XML from NCBI
*/
public class PubMedDownloader {
    private static final String baseURL =
        "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&retmode=xml&id=";

    private String url;
    private int[] pmids;
    private Document doc = null;

    /** for a given list of PMID returns PubMed XML from NCBI 
     * @wa ids assumed to be valid
     * @sa IdsValidator for validation
    */
    PubMedDownloader(int[] pmids) {
        this.pmids = pmids;
        constructUrl();
        postUrl();
        validateResponse();
    }
    
    /** only URL for all IDs is produced.
    */
    public String getUrl() {
        return url;
    }
    
    /// get response from NCBI PubMed with article(s) descriptions
    Document getXML() {
        return doc;
    }

    private void constructUrl() {
        String idsStr = "";
        for (int i = 0; i < pmids.length; i++) {
            idsStr += Integer.toString(pmids[i]) + ",";
        }
        url = baseURL + idsStr;
    }

    private void postUrl() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new URL(getUrl()).openStream());
            Thread.sleep(500);
        } catch (Exception e) {
            throw new IllegalArgumentException("cannot create DocumentBuilder.");
        }
    }

    private void validateResponse() {
            /// check for errors
            NodeList nList = doc.getElementsByTagName("PubmedArticle");
            if (nList.getLength() == 0) {
                throw new IllegalArgumentException(
                        "cannot parse PubMedArticle tag in the response");
            }
            if (nList.getLength() != pmids.length) {
                throw new IllegalArgumentException(
                        "Did not get all PMIDs in the response.");
            }
    }
 
}