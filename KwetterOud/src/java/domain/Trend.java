package domain;

/**
 *
 * @author Jurgen
 */
public class Trend
{
    // Data
    private String word;
    private Long count;

    // Constructor
    public Trend(String _word)
    {
        word = _word;
        count = 1L;
    }

    //Eigenschappen
    public Long getCount() {
        return count;
    }

    public String getWord() {
        return word;
    }

    //Methoden
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (word != null ? word.hashCode()+ count.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Trend)) {
            return false;
        }
        Trend other = (Trend) object;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        return "twitter.domain.Trend[word=" + word + "]";
    }

}
