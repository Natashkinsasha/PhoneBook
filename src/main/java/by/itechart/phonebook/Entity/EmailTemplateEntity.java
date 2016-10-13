package by.itechart.phonebook.Entity;


import by.itechart.phonebook.DTO.EmailTemplateDTO;

public class EmailTemplateEntity implements Entity{
    private int id;
    private String path;
    private String ruName;
    private String engName;

    public EmailTemplateEntity(EmailTemplateDTO emailTemplateDTO) {
        this.id=emailTemplateDTO.getId();
        this.path=emailTemplateDTO.getPath();
        this.ruName=emailTemplateDTO.getRuName();
        this.engName=emailTemplateDTO.getEngName();
    }

    public EmailTemplateEntity() {

    }

    @Override
    public int getId() {
        return id;
    }

    public EmailTemplateEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getPath() {
        return path;
    }

    public EmailTemplateEntity setPath(String path) {
        this.path = path;
        return this;
    }

    public String getRuName() {
        return ruName;
    }

    public EmailTemplateEntity setRuName(String ruName) {
        this.ruName = ruName;
        return this;
    }

    public String getEngName() {
        return engName;
    }

    public EmailTemplateEntity setEngName(String engName) {
        this.engName = engName;
        return this;
    }
}
