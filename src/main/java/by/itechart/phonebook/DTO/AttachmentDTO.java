package by.itechart.phonebook.DTO;


import by.itechart.phonebook.Entity.AttachmentEntity;
import by.itechart.phonebook.Validator.NotNull;
import by.itechart.phonebook.Validator.MaxSize;
import com.mysql.jdbc.StringUtils;

import java.sql.Date;

public class AttachmentDTO implements DTO {
    private int id;
    @NotNull
    @MaxSize(254)
    private String path;
    @NotNull
    private String name;
    private Date creationDate;
    private String comment;

    public AttachmentDTO() {
    }

    public AttachmentDTO(AttachmentEntity entity) {
        this.id=entity.getId();
        this.name=entity.getName();
        this.comment=entity.getComment();
        this.creationDate=entity.getCreationDate();
        this.path=entity.getPath();
    }

    public String getName() {
        return name;
    }

    public String getNameString() {
        if (StringUtils.isNullOrEmpty(name)) {
            return "";
        } else {
            return name;
        }
    }

    public AttachmentDTO setName(String name) {
        if (StringUtils.isNullOrEmpty(name)){
            this.name=null;
        }
        this.name = name;
        return this;
    }




    public String getPath() {
        return path;
    }

    public String getPathString() {
        if (StringUtils.isNullOrEmpty(path)) {
            return "";
        } else {
            return path;
        }
    }

    public AttachmentDTO setPath(String path) {
        if (StringUtils.isNullOrEmpty(path)) {
            this.path = null;
        } else {
            this.path = path;
        }
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getCreationDateString() {
        if (creationDate == null) {
            return "";
        } else {
            return creationDate.toString();

        }
    }

    public AttachmentDTO setCreationDate(Date creationDate) {
        if (creationDate == null) {
            this.creationDate = null;
        } else {
            this.creationDate = creationDate;
        }
        return this;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentString() {
        if (StringUtils.isNullOrEmpty(comment)) {
            return "";
        } else {
            return comment;
        }
    }

    public AttachmentDTO setComment(String comment) {
        if (StringUtils.isNullOrEmpty(comment)) {
            this.comment = null;
        } else {
            this.comment = comment;
        }
        return this;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getIdString() {
        return new Integer(id).toString();
    }

    public AttachmentDTO setId(int id) {
        this.id=id;
        return this;
    }



}
