package citExtract;

import java.util.ArrayList;
import org.w3c.dom.Document;
import citFormatter.*;
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
        
        List<CitationFormatted> formCitations = formatCitations(citations);   
        
        //                                                                                 
        //        CitFormatter  txtFormat = CitTxtFormatter(citationsFormatted);           
        //        CitFormatter htmlFormat = CitHtmlFormatter(citationsFormatted);
    }

    private static List<CitationFormatted> formatCitations(List<Citation> citations) {
        ReferenceFormatter refFormatter = new ReferenceFormatter();
        AbstractFormatter absFormatter = new AbstractFormatter();
        CitationFormatterFactory formatter = 
                new CitationFormatterFactory(refFormatter, absFormatter);
        return formatter.format(citations);                                
    }                                                                            
}                       