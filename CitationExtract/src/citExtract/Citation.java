package citExtract;
/**
    Citation parsed  from PubMed XML file.

    @sa CitationFormatted.java for parsed citation with substituition rules applied
    and reference formated

*/
public class Citation {

    private final int            pmid;
    private final Reference      ref;
    private final Abstract       abs;

    public Citation(int pmid, Reference ref, Abstract abs) {
       this.pmid   = pmid;
       this.ref    = ref;
       this.abs    = abs;
    }

    public Reference getReference() {
        return ref;
    }

    public Abstract getAbstract() {
        return abs;
    }

    public int getPmid() {
        return pmid;
    }
}


