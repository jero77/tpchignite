import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.sql.Timestamp;

public class LINEITEM {

    // This field must be invisible to SQL-Engine so no @QuerySqlField
    private LINEITEM_KEY L_KEY;     //Compound PK
    @QuerySqlField (orderedGroups = {@QuerySqlField.Group(name = "lineitem_foreign_key_idx", order = 0)})
    private Integer L_PARTKEY;      //Compound FK with SUPPKEY
    @QuerySqlField (orderedGroups = {@QuerySqlField.Group(name = "lineitem_foreign_key_idx", order = 1)})
    private Integer L_SUPPKEY;      //FK

    @QuerySqlField
    private Double L_QUANTITY;
    @QuerySqlField
    private Double L_EXTENDEDPRICE;
    @QuerySqlField
    private Double L_DISCOUNT;
    @QuerySqlField
    private Double L_TAX;
    @QuerySqlField
    private String L_RETURNFLAG;
    @QuerySqlField
    private String L_LINESTATUS;
    @QuerySqlField
    private Timestamp L_SHIPDATE;
    @QuerySqlField
    private Timestamp L_COMMITDATE;
    @QuerySqlField
    private Timestamp L_RECEIPTDATE;
    @QuerySqlField
    private String L_SHIPINSTRUCT;
    @QuerySqlField
    private String L_SHIPMODE;
    @QuerySqlField
    private String L_COMMENT;


    public LINEITEM(LINEITEM_KEY l_KEY, Integer l_PARTKEY, Integer l_SUPPKEY, Double l_QUANTITY,
                    Double l_EXTENDEDPRICE, Double l_DISCOUNT, Double l_TAX,
                    String l_RETURNFLAG, String l_LINESTATUS, Timestamp l_SHIPDATE, Timestamp l_COMMITDATE,
                    Timestamp l_RECEIPTDATE, String l_SHIPINSTRUCT, String l_SHIPMODE, String l_COMMENT) {
        L_KEY = l_KEY;
        L_PARTKEY = l_PARTKEY;
        L_SUPPKEY = l_SUPPKEY;
        L_QUANTITY = l_QUANTITY;
        L_EXTENDEDPRICE = l_EXTENDEDPRICE;
        L_DISCOUNT = l_DISCOUNT;
        L_TAX = l_TAX;
        L_RETURNFLAG = l_RETURNFLAG;
        L_LINESTATUS = l_LINESTATUS;
        L_SHIPDATE = l_SHIPDATE;
        L_COMMITDATE = l_COMMITDATE;
        L_RECEIPTDATE = l_RECEIPTDATE;
        L_SHIPINSTRUCT = l_SHIPINSTRUCT;
        L_SHIPMODE = l_SHIPMODE;
        L_COMMENT = l_COMMENT;
    }

    public LINEITEM_KEY getL_KEY() {
        return L_KEY;
    }

    public void setL_KEY(LINEITEM_KEY l_KEY) {
        L_KEY = l_KEY;
    }

    public Integer getL_PARTKEY() {
        return L_PARTKEY;
    }

    public void setL_PARTKEY(Integer l_PARTKEY) {
        L_PARTKEY = l_PARTKEY;
    }

    public Integer getL_SUPPKEY() {
        return L_SUPPKEY;
    }

    public void setL_SUPPKEY(Integer l_SUPPKEY) {
        L_SUPPKEY = l_SUPPKEY;
    }

    public Double getL_QUANTITY() {
        return L_QUANTITY;
    }

    public void setL_QUANTITY(Double l_QUANTITY) {
        L_QUANTITY = l_QUANTITY;
    }

    public Double getL_EXTENDEDPRICE() {
        return L_EXTENDEDPRICE;
    }

    public void setL_EXTENDEDPRICE(Double l_EXTENDEDPRICE) {
        L_EXTENDEDPRICE = l_EXTENDEDPRICE;
    }

    public Double getL_DISCOUNT() {
        return L_DISCOUNT;
    }

    public void setL_DISCOUNT(Double l_DISCOUNT) {
        L_DISCOUNT = l_DISCOUNT;
    }

    public Double getL_TAX() {
        return L_TAX;
    }

    public void setL_TAX(Double l_TAX) {
        L_TAX = l_TAX;
    }

    public String getL_RETURNFLAG() {
        return L_RETURNFLAG;
    }

    public void setL_RETURNFLAG(String l_RETURNFLAG) {
        L_RETURNFLAG = l_RETURNFLAG;
    }

    public String getL_LINESTATUS() {
        return L_LINESTATUS;
    }

    public void setL_LINESTATUS(String l_LINESTATUS) {
        L_LINESTATUS = l_LINESTATUS;
    }

    public Timestamp getL_SHIPDATE() {
        return L_SHIPDATE;
    }

    public void setL_SHIPDATE(Timestamp l_SHIPDATE) {
        L_SHIPDATE = l_SHIPDATE;
    }

    public Timestamp getL_COMMITDATE() {
        return L_COMMITDATE;
    }

    public void setL_COMMITDATE(Timestamp l_COMMITDATE) {
        L_COMMITDATE = l_COMMITDATE;
    }

    public Timestamp getL_RECEIPTDATE() {
        return L_RECEIPTDATE;
    }

    public void setL_RECEIPTDATE(Timestamp l_RECEIPTDATE) {
        L_RECEIPTDATE = l_RECEIPTDATE;
    }

    public String getL_SHIPINSTRUCT() {
        return L_SHIPINSTRUCT;
    }

    public void setL_SHIPINSTRUCT(String l_SHIPINSTRUCT) {
        L_SHIPINSTRUCT = l_SHIPINSTRUCT;
    }

    public String getL_SHIPMODE() {
        return L_SHIPMODE;
    }

    public void setL_SHIPMODE(String l_SHIPMODE) {
        L_SHIPMODE = l_SHIPMODE;
    }

    public String getL_COMMENT() {
        return L_COMMENT;
    }

    public void setL_COMMENT(String l_COMMENT) {
        L_COMMENT = l_COMMENT;
    }
}
