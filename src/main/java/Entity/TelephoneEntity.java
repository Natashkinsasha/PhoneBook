package main.java.Entity;


import main.java.DTO.TelephoneDTO;

public class TelephoneEntity {
    private int id;
    private String number;
    private String type;
    private String comments;
    private String countryCode;
    private String operatorCode;
    private int contactId;
    public  TelephoneEntity(){};
    public TelephoneEntity(TelephoneDTO telephoneDTO, int contactId) {
        setId(telephoneDTO.getId());
        setNumber(telephoneDTO.getNumber());
        setType(telephoneDTO.getType());
        setCountryCode(telephoneDTO.getCountryCode());
        setOperatorCode(telephoneDTO.getOperatorCode());
        setContactId(contactId);
        setComments(telephoneDTO.getComments());
    }

    @Override
    public String toString() {
        return "TelephoneEntity{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", comments='" + comments + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", operatorCode='" + operatorCode + '\'' +
                ", contactId=" + contactId +
                '}';
    }

    public String getCountryCode() {
        return countryCode;
    }

    public TelephoneEntity setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public TelephoneEntity setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
        return this;
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
