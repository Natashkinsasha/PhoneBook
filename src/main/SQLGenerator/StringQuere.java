package main.SQLGenerator;


public class StringQuere {
    StringBuffer stringQuere;
    int orderByCounter=0;

    protected StringQuere() {
        stringQuere = new StringBuffer();
    }

    public StringQuere select() {
        stringQuere.append("SELECT *");
        return this;
    }

    public StringQuere from(String from) {
        stringQuere.append(" FROM").append(from);
        return this;
    }

    public StringQuere orderBy(String orderBy, Boolean desc) {
        if (orderByCounter!=0){
            stringQuere.append(",");
        }
        stringQuere.append(" ORDER BY").append(orderBy);
        if (desc) {
            stringQuere.append(" DESC");
        }
        orderByCounter++;
        return this;
    }

    public StringQuere limit(int number) {
        stringQuere.append(" LIMIT ").append(number);
        return this;
    }

    public StringQuere limit(int offset, int number) {
        stringQuere.append(" LIMIT ").append(offset).append(", ").append(number);
        return this;
    }

    public StringQuere where() {
        stringQuere.append(" WHERE");
        return this;
    }

    public StringQuere like(String field, String pattern) {
        stringQuere.append(" ").append(field).append(" LIKE ").append("'").append(pattern).append("'");
        return this;
    }



    @Override
    public String toString() {
        return stringQuere.toString();
    }
}
