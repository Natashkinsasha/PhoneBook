package by.itechart.phonebook.Servic;

import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.Servic.ServiceException;

public interface ContactService {


    public void deleteContact(int id) throws ServiceException;


    public void createContact(ContactDTO contactDTO) throws ServiceException;

    public ContactDTO getContactById(int id) throws ServiceException;

    void updateContact(ContactDTO contactDTO) throws ServiceException;
}
