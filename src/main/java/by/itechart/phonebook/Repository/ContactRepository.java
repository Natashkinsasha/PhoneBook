package by.itechart.phonebook.Repository;




import by.itechart.phonebook.DTO.ContactDTO;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface ContactRepository extends Repository<ContactDTO, Integer>{
    List<ContactDTO> getSerchSortLimit(ContactDTO contactDTO, int offset, int number, Map<String, Boolean> sortFields) throws RepositoryException;
    int sizeWithSerch(ContactDTO contactDTO) throws RepositoryException;
    List<ContactDTO> getBirthdays() throws RepositoryException;
}
