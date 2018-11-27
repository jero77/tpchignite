import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class REGION {

    @QuerySqlField (index = true)
    private Integer R_REGIONKEY;        //PK
    @QuerySqlField
    private String R_NAME;
    @QuerySqlField
    private String R_COMMENT;


    public REGION(Integer r_REGIONKEY, String r_NAME, String r_COMMENT) {
        R_REGIONKEY = r_REGIONKEY;
        R_NAME = r_NAME;
        R_COMMENT = r_COMMENT;
    }


    public Integer getR_REGIONKEY() {
        return R_REGIONKEY;
    }

    public void setR_REGIONKEY(Integer r_REGIONKEY) {
        R_REGIONKEY = r_REGIONKEY;
    }

    public String getR_NAME() {
        return R_NAME;
    }

    public void setR_NAME(String r_NAME) {
        R_NAME = r_NAME;
    }

    public String getR_COMMENT() {
        return R_COMMENT;
    }

    public void setR_COMMENT(String r_COMMENT) {
        R_COMMENT = r_COMMENT;
    }
}
