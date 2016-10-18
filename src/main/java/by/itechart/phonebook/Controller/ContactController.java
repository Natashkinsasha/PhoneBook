package by.itechart.phonebook.Controller;


import by.itechart.phonebook.DTO.AttachmentDTO;
import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.DTO.TelephoneDTO;
import by.itechart.phonebook.MVC.RequestMapping;
import by.itechart.phonebook.Servis.ContactService;
import by.itechart.phonebook.Servis.ContactServiceImpl;
import by.itechart.phonebook.Servis.ServiceException;

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
        List<AttachmentDTO> attachmentsFromRequest = createAttachmentsFromRequest(req);
        contactDTO.setTelephonesDTO(telephoneDTOList);
        contactDTO.setAttachmentDTOs(attachmentsFromRequest);
        ContactService contactServise = new ContactServiceImpl();
        contactServise.updateContact(contactDTO);
        resp.sendRedirect("/");

    }

    private ContactDTO createContactFromRequest(HttpServletRequest request) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName((String) request.getAttribute("first_name"));
        contactDTO.setSecondName((String) request.getAttribute("second_name"));
        contactDTO.setPatronymic((String) request.getAttribute("patronymic"));
        contactDTO.setBirthday((String) request.getAttribute("birthday"));
        contactDTO.setMale((Boolean) request.getAttribute("sex"));
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

        String[] phonesIds = ((String) request.getAttribute("phonesIds")).split(",");
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
        return phones;
    }

    private List<AttachmentDTO> createAttachmentsFromRequest(HttpServletRequest request) {
        List<AttachmentDTO> attachmentDTOLink = new ArrayList<>();
        if ((String) request.getAttribute("attachmentsIds") == null) {
            return attachmentDTOLink;
        } else {
            String[] attachmentIds = ((String) request.getAttribute("attachmentsIds")).split(",");
            for (int i = 1; i < attachmentIds.length; i++) {
                String idAttachment = attachmentIds[i];
                AttachmentDTO attachmentDTO = new AttachmentDTO();
                attachmentDTO.setId(Integer.valueOf(idAttachment));
                attachmentDTO.setName((String) request.getAttribute("attachment_".concat(idAttachment).concat("_name")));
                attachmentDTO.setComment((String) request.getAttribute("attachment_".concat(idAttachment.concat("_comments"))));
                //attachmentDTO.setCreationDate(request.getAttribute("attachment_".concat(idAttachment.concat("creation_date"))))
                attachmentDTO.setPath((String) request.getAttribute("up_file_" + idAttachment));
            }

            return attachmentDTOLink;
        }
    }

}
