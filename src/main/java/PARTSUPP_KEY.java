import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class PARTSUPP_KEY {

    //  Both are indexed seperately and once together as index on FK
    @QuerySqlField (index = true, orderedGroups = {@QuerySqlField.Group(name = "partsupp_foreign_key_idx", order = 0)})
    private Integer PS_PARTKEY;     //PK & FK   (compound PK)
    @QuerySqlField (index = true, orderedGroups = {@QuerySqlField.Group(name = "partsupp_foreign_key_idx", order = 1)})
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
