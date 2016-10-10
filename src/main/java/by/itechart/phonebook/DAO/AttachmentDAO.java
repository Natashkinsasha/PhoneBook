package by.itechart.phonebook.DAO;


import by.itechart.phonebook.DTO.AttachmentDTO;
import by.itechart.phonebook.Entity.AttachmentEntity;
import by.itechart.phonebook.Entity.TelephoneEntity;

import java.util.List;

public interface AttachmentDAO extends DAO<AttachmentEntity, Integer>{
    List<AttachmentEntity> getByContactId(int id) throws DAOException;
}
