package by.itechart.phonebook.DTO;


import by.itechart.phonebook.Entity.EmailTemplateEntity;
import com.mysql.jdbc.StringUtils;

public class EmailTemplateDTO implements DTO{
    private int id;
    private String path;
    private String ruName;
    private String engName;

    public EmailTemplateDTO(EmailTemplateEntity emailTemplateEntity) {
        this.id=emailTemplateEntity.getId();
        this.path=emailTemplateEntity.getPath();
        this.ruName=emailTemplateEntity.getRuName();
        this.engName=emailTemplateEntity.getEngName();
    }

    public EmailTemplateDTO() {

    }

    public String getPath() {
        return path;
    }

    public String getRuName() {
        return ruName;
    }

    public String getEngName() {
        return engName;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPathString() {
        if (StringUtils.isNullOrEmpty(path)){
            return "";
        }
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRuNameString() {
        if (StringUtils.isNullOrEmpty(ruName)){
            return "";
        }
        return ruName;
    }

    public void setRuName(String ruName) {
        this.ruName = ruName;
    }

    public String getEngNameString() {
        if (StringUtils.isNullOrEmpty(engName)){
            return "";
        }
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }
}
