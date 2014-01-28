package citExtract;

import java.util.ArrayList;
import org.w3c.dom.Document;
import citFormatter.*;
import citPrinter.CitationPrinter;
import java.util.List;

/**                                                                              
    main class for program that extract citation from NCBI                       
    and format it in txt and html                                                
*/                                                                               
public class CitationExtract {                                                   
    public static void main(String[] args) {                                     
        System.out.println("Citation extraction is started");                    
         
        IdsValidator idsVal = null;
        try {                                                                    
            idsVal = new IdsValidator(args);                        
        } catch (IllegalArgumentException e) {                                   
            System.out.println("Cannot process CMD line args: " + e);            
            System.out.println("Citation Extract failed!");                       
            System.exit(1);                                                      
        }                                                                        
           
        PubMedDownloader downloader = null;
        try {
            downloader = new PubMedDownloader(idsVal.getIds());           
        } catch (IllegalArgumentException e) {                                          
            System.out.println("FATAL ERROR:");
            System.out.println(e);
            System.out.println("Cannot Download PMIDs from NCBI!");                 
            System.exit(1);                                                             
        }                                                                        
                                                                                 
        Document doc = downloader.getXML();                                   
                                                                                 
        List<Citation> citations = CitationFactory.createCitations(doc);
        System.out.println("All citation are extracted"); 
        
        List<CitationFormatted> formattedCitations = formatCitations(citations);   
        
        CitationPrinter htmlPrinter = new CitationPrinter(formattedCitations);
        String html = htmlPrinter.print();

        String htmlFile = "citations.html";
        writeToFile(htmlFile, html);
    }

    private static List<CitationFormatted> formatCitations(List<Citation> citations) {
        ReferenceFormatter refFormatter = new ReferenceFormatter();
        AbstractFormatter absFormatter = new AbstractFormatter();
        CitationFormatterFactory formatter = 
                new CitationFormatterFactory(refFormatter, absFormatter);
        return formatter.format(citations);                                
    }                                                                            

    private static void writeToFile(String htmlFile, String html) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}                       