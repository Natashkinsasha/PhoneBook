package by.itechart.phonebook.DTO;


import by.itechart.phonebook.Entity.AttachmentEntity;
import com.mysql.jdbc.StringUtils;

import java.sql.Date;

public class AttachmentDTO implements DTO {
    private int id;
    private String path;
    private String name;
    private Date creationDate;
    private String comment;

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

    public void setName(String name) {
        if (StringUtils.isNullOrEmpty(name)){
            this.name=null;
        }
        this.name = name;
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
}
