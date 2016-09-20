package main.Entity;


import main.DTO.TelephoneDTO;

public class TelephoneEntity {
    private int id;
    private String number;
    private String type;
    private String comments;
    private int contactId;
    public  TelephoneEntity(){};
    public TelephoneEntity(TelephoneDTO telephoneDTO, int contactId) {
        setId(telephoneDTO.getId());
        setNumber(telephoneDTO.getNumber());
        setType(telephoneDTO.getType());
        setContactId(contactId);
    }

    @Override
    public String toString() {
        return "TelephoneEntity{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", comments='" + comments + '\'' +
                ", contactId=" + contactId +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public TelephoneEntity setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getType() {
        return type;
    }

    public TelephoneEntity  setType(String type) {
        this.type = type;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public TelephoneEntity setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public int getId() {
        return id;
    }

    public TelephoneEntity setId(int id) {
        this.id = id;
        return this;
    }

    public int getContactId() {
        return contactId;
    }

    public TelephoneEntity setContactId(int contactId) {
        this.contactId = contactId;
        return this;
    }
}
