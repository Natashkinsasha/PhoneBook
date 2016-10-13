package by.itechart.phonebook.Servis;


import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.Repository.ContactRepository;
import by.itechart.phonebook.Repository.ContactRepositoryImpl;
import by.itechart.phonebook.Repository.RepositoryException;

public class ContactServiceImpl implements ContactService {
    @Override
    public void deleteContact(Integer... id) throws ServiceException {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        try {
            contactRepository.delete(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createContact(ContactDTO contactDTO) throws ServiceException {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        try {
            contactRepository.create(contactDTO);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ContactDTO getContactById(int id) throws ServiceException {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        try {
            return contactRepository.get(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateContact(ContactDTO contactDTO) throws ServiceException {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        try {
            contactRepository.update(contactDTO);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

}
