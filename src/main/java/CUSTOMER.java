import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class CUSTOMER {

    @AffinityKeyMapped      //Collocated to Orders
    @QuerySqlField (index = true)
    private Integer C_CUSTKEY;      //PK
    @QuerySqlField
    private String C_NAME;
    @QuerySqlField
    private String C_ADDRESS;
    @QuerySqlField (index = true)
    private Integer C_NATIONKEY;    //FK
    @QuerySqlField
    private String C_PHONE;
    @QuerySqlField
    private Double C_ACCTBAL;
    @QuerySqlField
    private String C_MKTSEGMENT;
    @QuerySqlField
    private String C_COMMENT;

    public CUSTOMER(Integer c_CUSTKEY, String c_NAME, String c_ADDRESS, Integer c_NATIONKEY, String c_PHONE,
                    Double c_ACCTBAL, String c_MKTSEGMENT, String c_COMMENT) {
        C_CUSTKEY = c_CUSTKEY;
        C_NAME = c_NAME;
        C_ADDRESS = c_ADDRESS;
        C_NATIONKEY = c_NATIONKEY;
        C_PHONE = c_PHONE;
        C_ACCTBAL = c_ACCTBAL;
        C_MKTSEGMENT = c_MKTSEGMENT;
        C_COMMENT = c_COMMENT;
    }


    public Integer getC_CUSTKEY() {
        return C_CUSTKEY;
    }

    public void setC_CUSTKEY(Integer c_CUSTKEY) {
        C_CUSTKEY = c_CUSTKEY;
    }

    public String getC_NAME() {
        return C_NAME;
    }

    public void setC_NAME(String c_NAME) {
        C_NAME = c_NAME;
    }

    public String getC_ADDRESS() {
        return C_ADDRESS;
    }

    public void setC_ADDRESS(String c_ADDRESS) {
        C_ADDRESS = c_ADDRESS;
    }

    public Integer getC_NATIONKEY() {
        return C_NATIONKEY;
    }

    public void setC_NATIONKEY(Integer c_NATIONKEY) {
        C_NATIONKEY = c_NATIONKEY;
    }

    public String getC_PHONE() {
        return C_PHONE;
    }

    public void setC_PHONE(String c_PHONE) {
        C_PHONE = c_PHONE;
    }

    public Double getC_ACCTBAL() {
        return C_ACCTBAL;
    }

    public void setC_ACCTBAL(Double c_ACCTBAL) {
        C_ACCTBAL = c_ACCTBAL;
    }

    public String getC_MKTSEGMENT() {
        return C_MKTSEGMENT;
    }

    public void setC_MKTSEGMENT(String c_MKTSEGMENT) {
        C_MKTSEGMENT = c_MKTSEGMENT;
    }

    public String getC_COMMENT() {
        return C_COMMENT;
    }

    public void setC_COMMENT(String c_COMMENT) {
        C_COMMENT = c_COMMENT;
    }
}
