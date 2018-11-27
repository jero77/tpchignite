import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.math.BigDecimal;

public class SUPPLIER {

    @AffinityKeyMapped      // Supplier collocated to Partsupp
    @QuerySqlField (index = true)
    private Integer S_SUPPKEY;          //PK
    @QuerySqlField
    private String S_NAME;
    @QuerySqlField
    private String S_ADDRESS;
    @QuerySqlField
    private Integer S_NATIONKEY;        //FK
    @QuerySqlField
    private String S_PHONE;
    @QuerySqlField
    private BigDecimal S_ACCTBAL;
    @QuerySqlField
    private String S_COMMENT;

    public SUPPLIER(Integer s_SUPPKEY, String s_NAME, String s_ADDRESS, Integer s_NATIONKEY,
                    String s_PHONE, BigDecimal s_ACCTBAL, String s_COMMENT) {
        S_SUPPKEY = s_SUPPKEY;
        S_NAME = s_NAME;
        S_ADDRESS = s_ADDRESS;
        S_NATIONKEY = s_NATIONKEY;
        S_PHONE = s_PHONE;
        S_ACCTBAL = s_ACCTBAL;
        S_COMMENT = s_COMMENT;
    }

    public Integer getS_SUPPKEY() {
        return S_SUPPKEY;
    }

    public void setS_SUPPKEY(Integer s_SUPPKEY) {
        S_SUPPKEY = s_SUPPKEY;
    }

    public String getS_NAME() {
        return S_NAME;
    }

    public void setS_NAME(String s_NAME) {
        S_NAME = s_NAME;
    }

    public String getS_ADDRESS() {
        return S_ADDRESS;
    }

    public void setS_ADDRESS(String s_ADDRESS) {
        S_ADDRESS = s_ADDRESS;
    }

    public Integer getS_NATIONKEY() {
        return S_NATIONKEY;
    }

    public void setS_NATIONKEY(Integer s_NATIONKEY) {
        S_NATIONKEY = s_NATIONKEY;
    }

    public String getS_PHONE() {
        return S_PHONE;
    }

    public void setS_PHONE(String s_PHONE) {
        S_PHONE = s_PHONE;
    }

    public BigDecimal getS_ACCTBAL() {
        return S_ACCTBAL;
    }

    public void setS_ACCTBAL(BigDecimal s_ACCTBAL) {
        S_ACCTBAL = s_ACCTBAL;
    }

    public String getS_COMMENT() {
        return S_COMMENT;
    }

    public void setS_COMMENT(String s_COMMENT) {
        S_COMMENT = s_COMMENT;
    }
}
