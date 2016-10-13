package by.itechart.phonebook.Servis;

import by.itechart.phonebook.DTO.ContactDTO;

public interface ContactService {


    public void deleteContact(Integer... id) throws ServiceException;

    public void createContact(ContactDTO contactDTO) throws ServiceException;

    public ContactDTO getContactById(int id) throws ServiceException;

    void updateContact(ContactDTO contactDTO) throws ServiceException;
}
