import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.math.BigDecimal;
import java.sql.Date;

public class ORDERS {

    @AffinityKeyMapped      // Collocated to Lineitem
    @QuerySqlField (index = true)
    private Integer O_ORDERKEY;         //PK
    @QuerySqlField
    private Integer O_CUSTKEY;          //FK
    @QuerySqlField
    private String O_ORDERSTATUS;
    @QuerySqlField
    private BigDecimal O_TOTALPRICE;
    @QuerySqlField
    private Date O_ORDERDATE;
    @QuerySqlField
    private String O_ORDERPRIORITY;
    @QuerySqlField
    private String O_CLERK;
    @QuerySqlField
    private Integer O_SHIPPRIORITY;
    @QuerySqlField
    private String O_COMMENT;


    public ORDERS(Integer o_ORDERKEY, Integer o_CUSTKEY, String o_ORDERSTATUS, BigDecimal o_TOTALPRICE, Date o_ORDERDATE,
                  String o_ORDERPRIORITY, String o_CLERK, Integer o_SHIPPRIORITY, String o_COMMENT) {
        O_ORDERKEY = o_ORDERKEY;
        O_CUSTKEY = o_CUSTKEY;
        O_ORDERSTATUS = o_ORDERSTATUS;
        O_TOTALPRICE = o_TOTALPRICE;
        O_ORDERDATE = o_ORDERDATE;
        O_ORDERPRIORITY = o_ORDERPRIORITY;
        O_CLERK = o_CLERK;
        O_SHIPPRIORITY = o_SHIPPRIORITY;
        O_COMMENT = o_COMMENT;
    }


    public Integer getO_ORDERKEY() {
        return O_ORDERKEY;
    }

    public void setO_ORDERKEY(Integer o_ORDERKEY) {
        O_ORDERKEY = o_ORDERKEY;
    }

    public Integer getO_CUSTKEY() {
        return O_CUSTKEY;
    }

    public void setO_CUSTKEY(Integer o_CUSTKEY) {
        O_CUSTKEY = o_CUSTKEY;
    }

    public String getO_ORDERSTATUS() {
        return O_ORDERSTATUS;
    }

    public void setO_ORDERSTATUS(String o_ORDERSTATUS) {
        O_ORDERSTATUS = o_ORDERSTATUS;
    }

    public BigDecimal getO_TOTALPRICE() {
        return O_TOTALPRICE;
    }

    public void setO_TOTALPRICE(BigDecimal o_TOTALPRICE) {
        O_TOTALPRICE = o_TOTALPRICE;
    }

    public Date getO_ORDERDATE() {
        return O_ORDERDATE;
    }

    public void setO_ORDERDATE(Date o_ORDERDATE) {
        O_ORDERDATE = o_ORDERDATE;
    }

    public String getO_ORDERPRIORITY() {
        return O_ORDERPRIORITY;
    }

    public void setO_ORDERPRIORITY(String o_ORDERPRIORITY) {
        O_ORDERPRIORITY = o_ORDERPRIORITY;
    }

    public String getO_CLERK() {
        return O_CLERK;
    }

    public void setO_CLERK(String o_CLERK) {
        O_CLERK = o_CLERK;
    }

    public Integer getO_SHIPPRIORITY() {
        return O_SHIPPRIORITY;
    }

    public void setO_SHIPPRIORITY(Integer o_SHIPPRIORITY) {
        O_SHIPPRIORITY = o_SHIPPRIORITY;
    }

    public String getO_COMMENT() {
        return O_COMMENT;
    }

    public void setO_COMMENT(String o_COMMENT) {
        O_COMMENT = o_COMMENT;
    }
}
