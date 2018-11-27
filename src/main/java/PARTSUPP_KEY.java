import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class PARTSUPP_KEY {

    @QuerySqlField
    private Integer PS_PARTKEY;     //PK & FK   (compound PK)
    @QuerySqlField
    private Integer PS_SUPPKEY;     //PK & FK


    public PARTSUPP_KEY(Integer PS_PARTKEY, Integer PS_SUPPKEY) {
        this.PS_PARTKEY = PS_PARTKEY;
        this.PS_SUPPKEY = PS_SUPPKEY;
    }

    public Integer getPS_PARTKEY() {
        return PS_PARTKEY;
    }

    public void setPS_PARTKEY(Integer PS_PARTKEY) {
        this.PS_PARTKEY = PS_PARTKEY;
    }

    public Integer getPS_SUPPKEY() {
        return PS_SUPPKEY;
    }

    public void setPS_SUPPKEY(Integer PS_SUPPKEY) {
        this.PS_SUPPKEY = PS_SUPPKEY;
    }
}
