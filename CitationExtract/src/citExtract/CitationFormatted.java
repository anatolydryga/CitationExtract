package citExtract;

import java.util.List;
import substitution.*;

/**
 * parsed and formatted Citation consists of reference, article abstract,
 * substitutions and status.
 *
 */
public class CitationFormatted {

    private final int PMID;
    private final String ref;
    private final List<Substitution> substitutions;

    public CitationFormatted(int PMID, String ref, List<Substitution> substitutions) {
        this.PMID = PMID;
        this.ref = ref;
        this.substitutions = substitutions;
    }

    public int getPmid() {
        return PMID;
    }

    public String getRef() {
        return ref;
    }

    public List<Substitution> getSubstitutions() {
        return substitutions;
    }
}