package main.DTO;


import java.util.Date;
import java.util.List;

public class ContactDTO implements DTO{
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
    private List<TelephoneDTO> telephoneDTOs;

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
                ", telephoneDTOs=" + telephoneDTOs +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public ContactDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public ContactDTO setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public ContactDTO setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public ContactDTO setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public boolean isMale() {
        return male;
    }

    public ContactDTO setMale(boolean male) {
        this.male = male;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public ContactDTO setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public ContactDTO setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
        return this;
    }

    public String getWebSite() {
        return webSite;
    }

    public ContactDTO setWebSite(String webSite) {
        this.webSite = webSite;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public ContactDTO setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ContactDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ContactDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public ContactDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getIndex() {
        return index;
    }

    public ContactDTO setIndex(String index) {
        this.index = index;
        return this;
    }

    public List<TelephoneDTO> getTelephoneDTOs() {
        return telephoneDTOs;
    }

    public ContactDTO setTelephoneDTOs(List<TelephoneDTO> telephoneDTOs) {
        this.telephoneDTOs = telephoneDTOs;
        return this;
    }

}
