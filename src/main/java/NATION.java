import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class NATION {

    @QuerySqlField (index = true)
    private Integer N_NATIONKEY;        //PK
    @QuerySqlField
    private String N_NAME;
    @QuerySqlField
    private Integer N_REGIONKEY;        //FK
    @QuerySqlField
    private String N_COMMENT;


    public NATION(Integer n_NATIONKEY, String n_NAME, Integer n_REGIONKEY, String n_COMMENT) {
        N_NATIONKEY = n_NATIONKEY;
        N_NAME = n_NAME;
        N_REGIONKEY = n_REGIONKEY;
        N_COMMENT = n_COMMENT;
    }


    public Integer getN_NATIONKEY() {
        return N_NATIONKEY;
    }

    public void setN_NATIONKEY(Integer n_NATIONKEY) {
        N_NATIONKEY = n_NATIONKEY;
    }

    public String getN_NAME() {
        return N_NAME;
    }

    public void setN_NAME(String n_NAME) {
        N_NAME = n_NAME;
    }

    public Integer getN_REGIONKEY() {
        return N_REGIONKEY;
    }

    public void setN_REGIONKEY(Integer n_REGIONKEY) {
        N_REGIONKEY = n_REGIONKEY;
    }

    public String getN_COMMENT() {
        return N_COMMENT;
    }

    public void setN_COMMENT(String n_COMMENT) {
        N_COMMENT = n_COMMENT;
    }
}
