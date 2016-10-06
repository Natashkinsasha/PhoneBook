package main.java.SQLGenerator;


public class StringQuere {
    StringBuffer stringQuere;
    int orderByCounter = 0;
    int likeCounter = 0;

    protected StringQuere() {
        stringQuere = new StringBuffer();
    }

    public StringQuere select(String str) {
        stringQuere.append("SELECT ").append(str);
        return this;
    }

    public StringQuere from(String from) {
        stringQuere.append(" FROM ").append(from);
        return this;
    }

    public StringQuere orderBy(String orderBy, Boolean desc) {
        if (orderByCounter != 0) {
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
        if (pattern != null) {
            if (likeCounter != 0) {
                stringQuere.append("and");
            }
            stringQuere.append(" ").append(field).append(" LIKE ").append("'%").append(pattern != null ? pattern : "").append("%'");
            likeCounter++;
        }
        return this;
    }

    public StringQuere like(String field, boolean pattern) {
        if (likeCounter != 0) {
            stringQuere.append(",");
        }
        stringQuere.append(" ").append(field).append(" LIKE ").append(pattern);
        likeCounter++;

        return this;
    }


    @Override
    public String toString() {
        return stringQuere.toString();
    }
}
