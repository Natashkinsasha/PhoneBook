package by.itechart.phonebook.Controller;


import by.itechart.phonebook.DTO.AttachmentDTO;
import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.DTO.TelephoneDTO;
import by.itechart.phonebook.MVC.RequestMapping;
import by.itechart.phonebook.Servis.ContactService;
import by.itechart.phonebook.Servis.ContactServiceImpl;
import by.itechart.phonebook.Servis.ServiceException;
import by.itechart.phonebook.Validator.Validator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ContactController {
    @RequestMapping(uri = "/save_contact", method = RequestMapping.Method.POST)
    public void saveContact(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException {
        ContactDTO contactDTO = createContactFromRequest(req);
        List<TelephoneDTO> telephoneDTOList = createPhonesFromRequest(req);
        List<AttachmentDTO> attachmentsDTOList = createAttachmentsFromRequest(req);
        Validator validator = Validator.getValidator();
        if (validator.check(contactDTO).hasErroe()) {
            req.getSession().setAttribute("error", "Contact has't been saved!");
            resp.sendRedirect("/");
            return;
        }
        for (TelephoneDTO telephoneDTO : telephoneDTOList) {
            if (validator.check(telephoneDTO).hasErroe()) {
                req.getSession().setAttribute("error", "Contact has't been saved!");
                resp.sendRedirect("/");
                return;
            }
        }
        for (AttachmentDTO attachmentDTO : attachmentsDTOList) {
            if (validator.check(attachmentsDTOList).hasErroe()) {
                req.getSession().setAttribute("error", "Contact has't been saved!");
                resp.sendRedirect("/");
                return;
            }
        }
        contactDTO.setTelephonesDTO(telephoneDTOList);
        contactDTO.setAttachmentDTOs(attachmentsDTOList);
        ContactService contactServise = new ContactServiceImpl();
        if (contactDTO.getId() == 0) {
            contactServise.createContact(contactDTO);
        } else {
            contactServise.updateContact(contactDTO);
        }
        req.getSession().setAttribute("success", "Contact has been saved!");
        resp.sendRedirect("/");
    }

    @RequestMapping(uri = "/serchcontact", method = RequestMapping.Method.POST)
    public void serchContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        ContactDTO contactDTOFileItems = createContactFromRequest(req);
        ContactDTO contactDTO = new ContactDTO().setFirstName(contactDTOFileItems.getFirstName()).setSecondName(contactDTOFileItems.getSecondName()).setPatronymic(contactDTOFileItems.getPatronymic()).setBirthday(contactDTOFileItems.getBirthday()).setMale(contactDTOFileItems.getMale()).setNationality(contactDTOFileItems.getNationality()).setRelationshipStatus(contactDTOFileItems.getRelationshipStatus()).setWebSite(contactDTOFileItems.getWebSite()).setEmail(contactDTOFileItems.getEmail()).setCountry(contactDTOFileItems.getCountry()).setCity(contactDTOFileItems.getCity()).setStreet(contactDTOFileItems.getStreet()).setIndex(contactDTOFileItems.getIndex()).setCompany(contactDTOFileItems.getCompany());
        req.getSession().setAttribute("serchPattern", contactDTO);
        req.getSession().removeAttribute("page");
        resp.sendRedirect("/");
    }

    private ContactDTO createContactFromRequest(HttpServletRequest request) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setPhotoPath((String) request.getAttribute("up_photo"));
        contactDTO.setId(Integer.valueOf((String) request.getAttribute("contact_id")));
        contactDTO.setFirstName((String) request.getAttribute("first_name"));
        contactDTO.setSecondName((String) request.getAttribute("second_name"));
        contactDTO.setPatronymic((String) request.getAttribute("patronymic"));
        contactDTO.setBirthday((String) request.getAttribute("birthday"));
        contactDTO.setMale((String) request.getAttribute("sex"));
        contactDTO.setNationality((String) request.getAttribute("natinality"));
        contactDTO.setRelationshipStatus((String) request.getAttribute("relationship_status"));
        contactDTO.setWebSite((String) request.getAttribute("web_site"));
        contactDTO.setEmail((String) request.getAttribute("email"));
        contactDTO.setCountry((String) request.getAttribute("work_place"));
        contactDTO.setCity((String) request.getAttribute("country"));
        contactDTO.setStreet((String) request.getAttribute("city"));
        contactDTO.setIndex((String) request.getAttribute("street"));
        contactDTO.setIndex((String) request.getAttribute("imdex"));
        return contactDTO;
    }

    private List<TelephoneDTO> createPhonesFromRequest(HttpServletRequest request) {
        List<TelephoneDTO> phones = new ArrayList<>();
        String phonesStringIds = (String) request.getAttribute("phonesIds");
        if (phonesStringIds != null) {
            String[] phonesIds = phonesStringIds.split(",");
            for (int i = 1; i < phonesIds.length; i++) {
                String idPhone = phonesIds[i];
                TelephoneDTO telephoneDTO = new TelephoneDTO();
                String id = idPhone.replaceAll("telephone_", "");
                telephoneDTO.setId(Integer.valueOf(id));
                telephoneDTO.setNumber((String) request.getAttribute(idPhone.concat("_number")));
                telephoneDTO.setOperatorCode((String) request.getAttribute(idPhone.concat("_operator_code")));
                telephoneDTO.setCountryCode((String) request.getAttribute(idPhone.concat("_country_code")));
                telephoneDTO.setComments((String) request.getAttribute(idPhone.concat("_comments")));
                telephoneDTO.setComments((String) request.getAttribute(idPhone.concat("_type")));
                phones.add(telephoneDTO);
            }
        }
        return phones;
    }

    private List<AttachmentDTO> createAttachmentsFromRequest(HttpServletRequest request) {
        List<AttachmentDTO> attachmentDTOLink = new ArrayList<>();
        String attachmentStringIds = (String) request.getAttribute("attachmentsIds");
        if (attachmentStringIds != null) {
            String[] attachmentIds = attachmentStringIds.split(",");
            for (int i = 1; i < attachmentIds.length; i++) {
                String idAttachment = attachmentIds[i];
                AttachmentDTO attachmentDTO = new AttachmentDTO();
                String id = idAttachment.replaceAll("attachment_", "");
                attachmentDTO.setId(Integer.valueOf(id));
                String temp = idAttachment.concat("_name");
                attachmentDTO.setName((String) request.getAttribute(idAttachment.concat("_name")));
                attachmentDTO.setComment((String) request.getAttribute(idAttachment.concat("_comment")));
                //attachmentDTO.setCreationDate(request.getAttribute("attachment_".concat(idAttachment.concat("creation_date"))))
                attachmentDTO.setPath((String) request.getAttribute("up_file_" + id));
                attachmentDTOLink.add(attachmentDTO);
            }
        }

        return attachmentDTOLink;
    }
}


