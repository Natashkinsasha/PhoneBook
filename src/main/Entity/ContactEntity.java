package main.Entity;

import main.DTO.ContactDTO;

import java.util.Date;

public class ContactEntity implements Entity{
    private int id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private Date birthday;
    private boolean male;
    private String nationality;
    private String relationshipStatus;
    private String webSite;
    private String email;
    private String company;
    private String country;
    private String city;
    private String street;
    private String index;

    public ContactEntity(ContactDTO contactDTO) {
        this.id = contactDTO.getId();
        this.firstName = contactDTO.getFirstName();
        this.secondName = contactDTO.getSecondName();
        this.patronymic = contactDTO.getPatronymic();
        this.birthday = contactDTO.getBirthday();
        this.male = contactDTO.isMale();
        this.nationality = contactDTO.getNationality();
        this.relationshipStatus = contactDTO.getRelationshipStatus();
        this.webSite = contactDTO.getWebSite();
        this.email = contactDTO.getEmail();
        this.company = contactDTO.getCompany();
        this.country = contactDTO.getCountry();
        this.city = contactDTO.getCity();
        this.street = contactDTO.getStreet();
        this.index = contactDTO.getIndex();
    }

    public String getFirstName() {
        return firstName;
    }

    public ContactEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public ContactEntity setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public ContactEntity setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public ContactEntity setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public boolean isMale() {
        return male;
    }

    public ContactEntity setMale(boolean male) {
        this.male = male;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public ContactEntity setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public ContactEntity setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
        return this;
    }

    public String getWebSite() {
        return webSite;
    }

    public ContactEntity setWebSite(String webSite) {
        this.webSite = webSite;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public ContactEntity setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ContactEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ContactEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public ContactEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getIndex() {
        return index;
    }

    public ContactEntity setIndex(String index) {
        this.index = index;
        return this;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Entity setId(int id) {
        this.id=id;
        return this;
    }
}
