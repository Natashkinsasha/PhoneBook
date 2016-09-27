package main.Servic;


import main.DTO.ContactDTO;

public interface ContactService {


    public void deleteContact(int id) throws ServiceException;


    public void createContact(ContactDTO contactDTO) throws ServiceException;

    public ContactDTO getContactById(int id) throws ServiceException;

    void updateContact(ContactDTO contactDTO) throws ServiceException;
}
