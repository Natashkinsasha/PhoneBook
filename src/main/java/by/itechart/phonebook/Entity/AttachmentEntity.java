package by.itechart.phonebook.Entity;

import by.itechart.phonebook.DTO.AttachmentDTO;

import java.sql.Date;


public class AttachmentEntity implements Entity {

    private int id;
    private String path;
    private String name;
    private Date creationDate;
    private String comment;
    private int contactId;


    public AttachmentEntity() {
    }

    public AttachmentEntity(AttachmentDTO attachmentDTO, int id) {
        this.id=attachmentDTO.getId();
        this.comment=attachmentDTO.getComment();
        this.creationDate=attachmentDTO.getCreationDate();
        this.name=attachmentDTO.getName();
        this.path=attachmentDTO.getPath();
        this.contactId=id;
    }



    public String getPath() {
        return path;
    }

    public AttachmentEntity setPath(String path) {
        this.path = path;
        return this;
    }

    public String getName() {
        return name;
    }

    public AttachmentEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public AttachmentEntity setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public AttachmentEntity setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Entity setId(int id) {
        this.id=id;
        return this;
    }
}
