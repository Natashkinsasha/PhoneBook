package main.DTO;


import com.mysql.jdbc.StringUtils;
import main.Entity.ContactEntity;

import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ContactDTO implements DTO {
    private int id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private Date birthday;
    private Boolean male;
    private String nationality;
    private String relationshipStatus;
    private String webSite;
    private String email;
    private String company;
    private String country;
    private String city;
    private String street;
    private String index;
    private List<TelephoneDTO> telephonesDTO;
    private BufferedImage photo;

    public ContactDTO() {
        telephonesDTO = new ArrayList<>();
    }

    public ContactDTO(ContactEntity contactEntity) {
        this();
        this.id = contactEntity.getId();
        this.firstName = contactEntity.getFirstName();
        this.secondName = contactEntity.getSecondName();
        this.patronymic = contactEntity.getPatronymic();
        this.birthday = contactEntity.getBirthday();
        this.male = contactEntity.getMale();
        this.nationality = contactEntity.getNationality();
        this.relationshipStatus = contactEntity.getRelationshipStatus();
        this.webSite = contactEntity.getWebSite();
        this.email = contactEntity.getEmail();
        this.company = contactEntity.getCompany();
        this.country = contactEntity.getCountry();
        this.city = contactEntity.getCity();
        this.street = contactEntity.getStreet();
        this.index = contactEntity.getIndex();
    }


    public String getIdString() {
        return id + "";
    }

    public String getFirstNameString() {
        if (firstName == null) {
            return "";
        }
        return firstName;
    }

    public String getSecondNameString() {
        if (secondName == null) {
            return "";
        }
        return secondName;
    }

    public String getPatronymicString() {
        if (patronymic == null) {
            return "";
        }
        return patronymic;
    }


    public String getBirthdayString() {
        if (birthday == null) {
            return "";
        }
        return birthday.toString();
    }

    public String getRelationshipStatusString() {
        if (relationshipStatus == null) {
            return "";
        }
        return relationshipStatus.toString();
    }

    public String getNationalityString() {
        if (nationality == null) {
            return "";
        }
        return nationality.toString();
    }

    public String getWebSiteString() {
        if (webSite == null) {
            return "";
        }
        return webSite.toString();
    }

    public String getEmailString() {
        if (email == null) {
            return "";
        }
        return email.toString();
    }


    public String getCompanyString() {
        if (company == null) {
            return "";
        }
        return company.toString();
    }


    public String getCountryString() {
        if (country == null) {
            return "";
        }
        return country.toString();
    }


    public String getCityString() {
        if (city == null) {
            return "";
        }
        return city.toString();
    }


    public String getStreetString() {
        if (street == null) {
            return "";
        }
        return street.toString();
    }


    public String getIndexString() {
        if (street == null) {
            return "";
        }
        return street.toString();
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday +
                ", male=" + male +
                ", nationality='" + nationality + '\'' +
                ", relationshipStatus='" + relationshipStatus + '\'' +
                ", webSite='" + webSite + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", index='" + index + '\'' +
                ", telephonesDTO=" + telephonesDTO +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public ContactDTO setFirstName(String firstName) {
        if (StringUtils.isNullOrEmpty(firstName)) {
            this.firstName = null;
        } else {
            this.firstName = firstName;
        }
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public ContactDTO setSecondName(String secondName) {
        if (StringUtils.isNullOrEmpty(secondName)) {
            this.secondName = null;
        } else {
            this.secondName = secondName;
        }
        return this;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public ContactDTO setPatronymic(String patronymic) {
        if (StringUtils.isNullOrEmpty(patronymic)) {
            this.patronymic = null;
        } else {
            this.patronymic = patronymic;
        }
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public ContactDTO setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public ContactDTO setBirthday(String birthday) {
        if (StringUtils.isNullOrEmpty(birthday)) {
            this.birthday = null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-mm-dd");
            java.util.Date date = null;
            try {
                date = format.parse(birthday);
            } catch (ParseException e) {
                this.birthday = null;
            }
            this.birthday = new Date(date.getTime());
        }
        return this;
    }

    public Boolean getMale() {
        return male;
    }

    public String getMaleString() {
        if (male == null) {
            return "";
        } else if (male == true) {
            return "Male";
        } else if (male == false) {
            return "Female";
        } else {
            return "";
        }
    }

    public ContactDTO setMale(boolean male) {
        this.male = male;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public ContactDTO setNationality(String nationality) {
        if (StringUtils.isNullOrEmpty(nationality)) {
            this.nationality = null;
        } else {
            this.nationality = nationality;
        }
        return this;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public ContactDTO setRelationshipStatus(String relationshipStatus) {
        if (StringUtils.isNullOrEmpty(relationshipStatus)) {
            this.relationshipStatus = null;
        } else {
            this.relationshipStatus = relationshipStatus;
        }
        return this;
    }

    public String getWebSite() {
        return webSite;
    }

    public ContactDTO setWebSite(String webSite) {
        if (StringUtils.isNullOrEmpty(webSite)) {
            this.webSite = null;
        } else {
            this.webSite = webSite;
        }
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactDTO setEmail(String email) {
        if (StringUtils.isNullOrEmpty(email)) {
            this.email = null;
        } else {
            this.email = email;
        }
        return this;
    }

    public String getCompany() {
        return company;
    }

    public ContactDTO setCompany(String company) {
        if (StringUtils.isNullOrEmpty(company)) {
            this.company = null;
        } else {
            this.company = company;
        }
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ContactDTO setCountry(String country) {
        if (StringUtils.isNullOrEmpty(country)) {
            this.country = null;
        } else {
            this.country = country;
        }
        return this;
    }

    public String getCity() {
        return city;
    }

    public ContactDTO setCity(String city) {
        if (StringUtils.isNullOrEmpty(city)) {
            this.city = null;
        } else {
            this.city = city;
        }
        return this;
    }

    public String getStreet() {
        return street;
    }

    public ContactDTO setStreet(String street) {
        if (StringUtils.isNullOrEmpty(street)) {
            this.street = null;
        } else {
            this.street = street;
        }
        return this;
    }

    public String getIndex() {
        return index;
    }

    public ContactDTO setIndex(String index) {
        if (StringUtils.isNullOrEmpty(index)) {
            this.index = null;
        } else {
            this.index = index;
        }
        return this;
    }

    public List<TelephoneDTO> getTelephonesDTO() {
        return telephonesDTO;
    }

    public ContactDTO setTelephonesDTO(List<TelephoneDTO> telephonesDTO) {
        this.telephonesDTO = telephonesDTO;
        return this;
    }

    public ContactDTO setMale(String sex) {
        if (StringUtils.isNullOrEmpty(street)) {
            this.street = null;
        } else if (sex.toLowerCase().equals("male")) {
            this.male = true;
        } else if (sex.toLowerCase().equals("female")) {
            this.male = false;
        }
        return this;
    }

    public ContactDTO addTelephone(TelephoneDTO telephoneDTO) {
        this.telephonesDTO.add(telephoneDTO);
        return this;
    }
}
