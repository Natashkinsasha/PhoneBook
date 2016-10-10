package by.itechart.phonebook.Servic;



import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.Repository.ContactRepository;
import by.itechart.phonebook.Repository.ContactRepositoryImpl;
import by.itechart.phonebook.Repository.RepositoryException;

import java.util.List;
import java.util.Map;

public class MainTableServiceImpl implements MainTableService{
    public static final int PAGESAIZE = 10;

    @Override
    public int getCountTablePage(ContactDTO contactDTO) throws ServiceException {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        try {

            return (int) Math.ceil((double) contactRepository.sizeWithSerch(contactDTO) /PAGESAIZE);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public List<ContactDTO> getSerchSortLimitContacts(ContactDTO serchContactDTO, Integer page, Map<String, Boolean> sortFields) throws ServiceException {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        if (page==null){
            page=new Integer(1);
        }
        try {
            return contactRepository.getSerchSortLimit(serchContactDTO,(page-1)*PAGESAIZE, PAGESAIZE, sortFields);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }


}
