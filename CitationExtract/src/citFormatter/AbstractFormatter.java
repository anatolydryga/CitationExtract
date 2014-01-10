package citFormatter;

import java.util.ArrayList;
import java.util.List;
import substitution.Substitution;
import citExtract.Abstract;
import substitution.GreekRule;
import substitution.MicroRule;
import substitution.SubstitutionRule;
import substitution.TextRule;
import substitution.TextSubstitution;

/**
 *
 * @author drygaay
 */
public class AbstractFormatter {

    static final ArrayList<SubstitutionRule> rules = new ArrayList<>();

    static {
        final SubstitutionRule hRule     = new TextRule("h", "hr");
        rules.add(hRule);
        final SubstitutionRule mlRule    = new TextRule("Ml", "mL");
        rules.add(mlRule);
        final SubstitutionRule mkgRule   = new TextRule("Microg", "ug");
        rules.add(mkgRule);
        final SubstitutionRule mklRule   = new TextRule("Microl", "uL");
        rules.add(mklRule);
        final SubstitutionRule microRule   = new MicroRule();
        rules.add(microRule);
        final SubstitutionRule greekRule = new GreekRule();
        rules.add(greekRule);           
    }

    public List<Substitution> format(Abstract abs) {
        List<Substitution> subs = new ArrayList<>();
        List<String> sectionText  = abs.getSectionsText();
        List<String> sectionLabel = abs.getSectionsLabel();
        for (int i = 0;  i < sectionText.size();  i++) {
            // we dont need labels at present
            if ( ! sectionLabel.get(i).equals("")) {
                String spaceOpt = (i == 0) ? "" : " "; 
                subs.add(new Substitution(sectionLabel.get(i), spaceOpt));
            }
            TextSubstitution formatter = 
                    new TextSubstitution(sectionText.get(i), rules);
            for (Substitution s : formatter) {
                subs.add(s);
            }
        }
        return subs;
    }
}
