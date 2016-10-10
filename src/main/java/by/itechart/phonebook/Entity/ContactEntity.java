package by.itechart.phonebook.Entity;
import by.itechart.phonebook.DTO.ContactDTO;

import java.sql.Date;

public class ContactEntity implements Entity{
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
    private String photoPath;

    public ContactEntity(ContactDTO contactDTO) {
        this.id = contactDTO.getId();
        this.firstName = contactDTO.getFirstName();
        this.secondName = contactDTO.getSecondName();
        this.patronymic = contactDTO.getPatronymic();
        this.birthday = contactDTO.getBirthday();
        this.male = contactDTO.getMale();
        this.nationality = contactDTO.getNationality();
        this.relationshipStatus = contactDTO.getRelationshipStatus();
        this.webSite = contactDTO.getWebSite();
        this.email = contactDTO.getEmail();
        this.company = contactDTO.getCompany();
        this.country = contactDTO.getCountry();
        this.city = contactDTO.getCity();
        this.street = contactDTO.getStreet();
        this.index = contactDTO.getIndex();
        this.photoPath=contactDTO.getPhotoPath();
    }

    public Boolean getMale() {
        return male;
    }

    public ContactEntity() {

    }

    public String getPhotoPath() {
        return photoPath;
    }

    public ContactEntity setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
        return this;
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



    public ContactEntity setMale(Boolean male) {
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
    public ContactEntity setId(int id) {
        this.id=id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactEntity)) return false;

        ContactEntity that = (ContactEntity) o;

        if (male != that.male) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (nationality != null ? !nationality.equals(that.nationality) : that.nationality != null) return false;
        if (relationshipStatus != null ? !relationshipStatus.equals(that.relationshipStatus) : that.relationshipStatus != null)
            return false;
        if (webSite != null ? !webSite.equals(that.webSite) : that.webSite != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        return index != null ? index.equals(that.index) : that.index == null;

    }

}
