package by.itechart.phonebook.DTO;

import com.mysql.jdbc.StringUtils;

public class EmailDTO implements DTO {
    private int id;
    private String from;
    private String whom;
    private String subject;
    private String text;

    public String getFrom() {
        return from;
    }

    public String getFromString() {
        if (from==null){
            return "";
        }
        return from;
    }

    public EmailDTO setFrom(String from) {
        if (StringUtils.isNullOrEmpty(from)) {
            this.from = null;
        } else {
            this.from = from;
        }
        return this;
    }

    public String getWhom() {
        return whom;
    }

    public String getWhomString() {
        if (whom==null){
            return "";
        }
        return whom;
    }

    public EmailDTO setWhom(String whom) {
        if (StringUtils.isNullOrEmpty(whom)) {
            this.whom = null;
        } else {
            this.whom = whom;
        }
        return this;
    }

    public String getSubjectString() {
        if (subject==null){
            return "";
        }
        return subject;
    }

    public String getSubject() {
        return subject;
    }

    public EmailDTO setSubject(String subject) {
        if (StringUtils.isNullOrEmpty(subject)) {
            this.subject= null;
        } else {
            this.subject = subject;
        }
        return this;
    }

    public String getText() {
        return text;
    }

    public String getTextString() {
        if (text==null){
            return "";
        }
        return text;
    }

    public EmailDTO setText(String text) {
        if (StringUtils.isNullOrEmpty(text )) {
            this.text = null;
        } else {
            this.text = text;
        }
        return this;
    }

    @Override
    public int getId() {
        return 0;
    }
}
