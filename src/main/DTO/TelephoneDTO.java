package main.DTO;


import main.Entity.TelephoneEntity;

public class TelephoneDTO implements DTO {
    private int id;
    private String number;
    private String type;
    private String comments;

    public TelephoneDTO(TelephoneEntity telephoneEntity) {
        this.id = telephoneEntity.getId();
        this.number = telephoneEntity.getNumber();
        this.type = telephoneEntity.getType();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TelephoneDTO{" +
                "number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public TelephoneDTO setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getType() {
        return type;
    }

    public TelephoneDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public TelephoneDTO setComments(String comments) {
        this.comments = comments;
        return this;
    }
}
