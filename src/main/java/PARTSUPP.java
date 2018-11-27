import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.math.BigDecimal;

public class PARTSUPP {

    @AffinityKeyMapped      //Collocated to Lineitem
    @QuerySqlField (index = true)
    private PARTSUPP_KEY PS_KEY;        // compound PK (with 2 FK's)
    @QuerySqlField
    private Integer PS_AVAILQTY;
    @QuerySqlField
    private BigDecimal PS_SUPPLYCOST;
    @QuerySqlField
    private String PS_COMMENT;

    public PARTSUPP(PARTSUPP_KEY PS_KEY, Integer PS_AVAILQTY, BigDecimal PS_SUPPLYCOST,
                    String PS_COMMENT) {
        this.PS_KEY = PS_KEY;
        this.PS_AVAILQTY = PS_AVAILQTY;
        this.PS_SUPPLYCOST = PS_SUPPLYCOST;
        this.PS_COMMENT = PS_COMMENT;
    }

    public PARTSUPP_KEY getPS_KEY() {
        return PS_KEY;
    }

    public void setPS_KEY(PARTSUPP_KEY PS_KEY) {
        this.PS_KEY = PS_KEY;
    }

    public Integer getPS_AVAILQTY() {
        return PS_AVAILQTY;
    }

    public void setPS_AVAILQTY(Integer PS_AVAILQTY) {
        this.PS_AVAILQTY = PS_AVAILQTY;
    }

    public BigDecimal getPS_SUPPLYCOST() {
        return PS_SUPPLYCOST;
    }

    public void setPS_SUPPLYCOST(BigDecimal PS_SUPPLYCOST) {
        this.PS_SUPPLYCOST = PS_SUPPLYCOST;
    }

    public String getPS_COMMENT() {
        return PS_COMMENT;
    }

    public void setPS_COMMENT(String PS_COMMENT) {
        this.PS_COMMENT = PS_COMMENT;
    }
}
