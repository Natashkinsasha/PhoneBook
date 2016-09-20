package main.Servic;

import main.DTO.ContactDTO;
import main.Repository.ContactRepository;
import main.Repository.ContactRepositoryImpl;
import main.Repository.RepositoryException;

import java.util.List;
import java.util.Map;

public class MainTableServiceImpl implements MainTableService{
    public static final int PAGESAIZE = 20;

    @Override
    public int getCountTablePage() throws ServiceException {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        try {
            return (int) Math.ceil(contactRepository.size()/PAGESAIZE);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public List<ContactDTO> getSerchSortLimitContacts(ContactDTO serchContactDTO, int page, Map<String, Boolean> sortFields) throws ServiceException {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        try {
            contactRepository.getSerchSortLimit(serchContactDTO,(page-1)*PAGESAIZE,page*PAGESAIZE, sortFields);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return null;
    }
}
