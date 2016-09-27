package main.DTO;


import main.Entity.TelephoneEntity;

public class TelephoneDTO implements DTO {
    private int id;
    private String countryCode;
    private String operatorCode;
    private String number;
    private String type;
    private String comments;

    public TelephoneDTO() {
    }

    public TelephoneDTO(TelephoneEntity telephoneEntity) {
        this.id = telephoneEntity.getId();
        this.number = telephoneEntity.getNumber();
        this.type = telephoneEntity.getType();
        this.countryCode=telephoneEntity.getCountryCode();
        this.operatorCode=telephoneEntity.getOperatorCode();
    }

    public String getIdString(){
        return id+"";
    }

    public String getCountryCodeString(){
        if (countryCode==null){
            return "";
        }
        return countryCode.toString();
    }


    public String getOperatorCodeString(){
        if (operatorCode==null){
            return "";
        }
        return operatorCode.toString();
    }


    public String getNumberString(){
        if (number==null){
            return "";
        }
        return number.toString();
    }

    public String getTypeString(){
        if (number==null){
            return "";
        }
        return type.toString();
    }

    public String getCommentsString(){
        if (comments==null){
            return "";
        }
        return comments.toString();
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

    public String getCountryCode() {
        return countryCode;
    }

    public TelephoneDTO setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public TelephoneDTO setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
        return this;
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
