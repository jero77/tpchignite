import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;


public class PART {

    @AffinityKeyMapped      // Part collocated to Partsupp
    @QuerySqlField (index = true)
    private Integer P_PARTKEY;        // PK
    @QuerySqlField
    private String P_NAME;
    @QuerySqlField
    private String P_MFGR;
    @QuerySqlField
    private String P_BRAND;
    @QuerySqlField
    private String P_TYPE;
    @QuerySqlField
    private Integer P_SIZE;
    @QuerySqlField
    private String P_CONTAINER;
    @QuerySqlField
    private Double P_RETAILPRICE;
    @QuerySqlField
    private String P_COMMENT;


    public PART(Integer p_PARTKEY, String p_NAME, String p_MFGR, String p_BRAND, String p_TYPE, Integer p_SIZE,
                String p_CONTAINER, Double p_RETAILPRICE, String p_COMMENT) {
        P_PARTKEY = p_PARTKEY;
        P_NAME = p_NAME;
        P_MFGR = p_MFGR;
        P_BRAND = p_BRAND;
        P_TYPE = p_TYPE;
        P_SIZE = p_SIZE;
        P_CONTAINER = p_CONTAINER;
        P_RETAILPRICE = p_RETAILPRICE;
        P_COMMENT = p_COMMENT;
    }

    public Integer getP_PARTKEY() {
        return P_PARTKEY;
    }

    public void setP_PARTKEY(Integer p_PARTKEY) {
        P_PARTKEY = p_PARTKEY;
    }

    public String getP_NAME() {
        return P_NAME;
    }

    public void setP_NAME(String p_NAME) {
        P_NAME = p_NAME;
    }

    public String getP_MFGR() {
        return P_MFGR;
    }

    public void setP_MFGR(String p_MFGR) {
        P_MFGR = p_MFGR;
    }

    public String getP_BRAND() {
        return P_BRAND;
    }

    public void setP_BRAND(String p_BRAND) {
        P_BRAND = p_BRAND;
    }

    public String getP_TYPE() {
        return P_TYPE;
    }

    public void setP_TYPE(String p_TYPE) {
        P_TYPE = p_TYPE;
    }

    public Integer getP_SIZE() {
        return P_SIZE;
    }

    public void setP_SIZE(Integer p_SIZE) {
        P_SIZE = p_SIZE;
    }

    public String getP_CONTAINER() {
        return P_CONTAINER;
    }

    public void setP_CONTAINER(String p_CONTAINER) {
        P_CONTAINER = p_CONTAINER;
    }

    public Double getP_RETAILPRICE() {
        return P_RETAILPRICE;
    }

    public void setP_RETAILPRICE(Double p_RETAILPRICE) {
        P_RETAILPRICE = p_RETAILPRICE;
    }

    public String getP_COMMENT() {
        return P_COMMENT;
    }

    public void setP_COMMENT(String p_COMMENT) {
        P_COMMENT = p_COMMENT;
    }
}
