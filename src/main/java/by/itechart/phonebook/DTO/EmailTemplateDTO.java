package by.itechart.phonebook.DTO;


public class EmailTemplateDTO {
    int id;
    String name;
    String templatePath;

    public int getId() {
        return id;
    }

    public EmailTemplateDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EmailTemplateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public EmailTemplateDTO setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
        return this;
    }
}
