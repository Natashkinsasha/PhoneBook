package main.java.Repository;


import main.java.DTO.ContactDTO;

import java.util.List;
import java.util.Map;

public interface ContactRepository extends Repository<ContactDTO, Integer>{
    List<ContactDTO> getSerchSortLimit(ContactDTO contactDTO, int offset, int number, Map<String, Boolean> sortFields) throws RepositoryException;
    int sizeWithSerch(ContactDTO contactDTO) throws RepositoryException;
}
