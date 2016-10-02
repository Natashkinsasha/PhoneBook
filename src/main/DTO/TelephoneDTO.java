package main.DTO;


import com.mysql.jdbc.StringUtils;
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
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", operatorCode='" + operatorCode + '\'' +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

    public String getCountryCode() {
        return countryCode;
    }

    public TelephoneDTO setCountryCode(String countryCode) {

        if (StringUtils.isNullOrEmpty(countryCode)){
            this.countryCode = null;
        } else {
            this.countryCode = countryCode;
        }
        return this;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public TelephoneDTO setOperatorCode(String operatorCode) {
        if (StringUtils.isNullOrEmpty(operatorCode)) {
            this.operatorCode = null;
        } else {
            this.operatorCode = operatorCode;
        }
        return this;
    }

    public String getNumber() {
        return number;
    }

    public TelephoneDTO setNumber(String number) {
        if (StringUtils.isNullOrEmpty(number)) {
            this.number = null;
        } else {
            this.number = number;
        }
        return this;

    }

    public String getType() {
        return type;
    }

    public TelephoneDTO setType(String type) {
        if (StringUtils.isNullOrEmpty(type)) {
            this.type = null;
        } else {
            this.type= type;
        }
        return this;
    }

    public String getComments() {
        return comments;
    }

    public TelephoneDTO setComments(String comments) {
        if (StringUtils.isNullOrEmpty(comments)) {
            this.comments = null;
        } else {
            this.comments = comments;
        }
        return this;
    }
}
