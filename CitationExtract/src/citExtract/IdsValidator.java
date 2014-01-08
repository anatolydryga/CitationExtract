package citExtract;

/**                                                                              
    input IDs validator.                                                         
                                                                                 
    check that array of Strings can represent ids (e.g. PMID)                    
    @throw IllegalArgumentException if:                                          
        1) have no ids                                                           
        2) have more than MAX_NUM_IDs ids                                        
        3) cannot convert id string to int                                       
        4) id is equal to zero or negative number                                
*/                                                                               
                                                                                 
import java.util.Arrays;                                                         
                                                                                 
public class IdsValidator {                                                      
    private static final int MAX_NUM_IDs = 50;                                   
    private int[] ids;                                                           
                                                                                 
    IdsValidator(String[] idsStr) {                                              
        ids  = new int[idsStr.length];                                           
        if (idsStr.length > MAX_NUM_IDs)                                         
            throw new IllegalArgumentException("too many ids. MAX # ids <= " + MAX_NUM_IDs);
        if (idsStr.length == 0) throw new IllegalArgumentException("need at least one id");
        try {                                                                    
            for (int i = 0; i < idsStr.length; i++) {                            
                ids[i] = Integer.parseInt(idsStr[i]);                            
                if (ids[i] <= 0) {                                               
                   throw new IllegalArgumentException("all ids should be positive");
                }                                                                
            }                                                                    
        } catch (NumberFormatException e) {                                      
           throw new IllegalArgumentException("cannot parse ids: " + Arrays.toString(idsStr));
        }                                                                        
    }                                                                            
                                                                                 
    int[] getIds() {                                                             
        return ids;                                                              
    }                                                                            
}                                   