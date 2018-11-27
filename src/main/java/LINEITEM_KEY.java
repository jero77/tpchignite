import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class LINEITEM_KEY {


    @QuerySqlField
    private Integer L_ORDERKEY;
    @QuerySqlField
    private Integer L_LINENUMBER;


    public LINEITEM_KEY(Integer l_ORDERKEY, Integer l_LINENUMBER) {
        L_ORDERKEY = l_ORDERKEY;
        L_LINENUMBER = l_LINENUMBER;
    }

    public Integer getL_ORDERKEY() {
        return L_ORDERKEY;
    }

    public void setL_ORDERKEY(Integer l_ORDERKEY) {
        L_ORDERKEY = l_ORDERKEY;
    }

    public Integer getL_LINENUMBER() {
        return L_LINENUMBER;
    }

    public void setL_LINENUMBER(Integer l_LINENUMBER) {
        L_LINENUMBER = l_LINENUMBER;
    }
}
