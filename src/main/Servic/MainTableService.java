package main.Servic;


import main.DTO.ContactDTO;

import java.util.List;
import java.util.Map;

public interface MainTableService {
    int getCountTablePage(ContactDTO contactDTO) throws ServiceException;
    List<ContactDTO> getSerchSortLimitContacts(ContactDTO serchContactDTO, Integer page, Map<String, Boolean> sortFields) throws ServiceException;

}
