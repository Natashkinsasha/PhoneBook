package by.itechart.phonebook.MVC;

import exceptions.ExecutingCommandsException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

public class FormatterRequest {

    private Logger logger = Logger.getLogger(FormatterRequest.class.getName());

    private static final int MEMORY_THRESHOLD;
    private static final int MAX_FILE_SIZE;
    private static final int MAX_REQUEST_SIZE;

    static {
        ResourceBundle resource = ResourceBundle.getBundle("resources");
        MEMORY_THRESHOLD = Integer.parseInt(resource
                .getString("memory.threshold"));
        MAX_FILE_SIZE = Integer.parseInt(resource.getString("max.file.size"));
        MAX_REQUEST_SIZE = Integer.parseInt(resource
                .getString("max.request.size"));
    }

    private File tmpDirectory;
    private ServletFileUpload upload;
    private HttpServletRequest request;

    public FormatterRequest(HttpServletRequest request) {
        this.request = request;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        tmpDirectory = FileUtil.createTmpFolder();
        factory.setRepository(tmpDirectory);
        upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF8");
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
    }

    public void format() throws FileUploadException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            convertSimpleRequest(request);
        } else {
            convertMultipartRequest(request);
        }
        FileUtil.deleteFolder(tmpDirectory);
    }

    private void convertSimpleRequest(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            request.setAttribute(parameterName,
                    request.getParameter(parameterName));
        }
    }

    private void convertMultipartRequest(HttpServletRequest request)
            throws FileUploadException {

        List<FileItem> items = upload.parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                createStringAttribute(request, item);
            }
        }
        for (FileItem item : items) {
            if (!item.isFormField() && StringUtils.isNotEmpty(item.getName())) {
                createFileFromAttribute(request, item);
            }
        }

    }

    private void createFileFromAttribute(HttpServletRequest request, FileItem item) {
        try {
            String idContact = (String) request.getAttribute("idContact");
            ContactFilesService contactFileService = new ContactFilesService(idContact);
            File file = contactFileService.createUniqueFile(idContact, item.getName());
            item.write(file);
            request.setAttribute(item.getFieldName(), ContactFilesService.getPrefix(file) + item.getName());
        } catch (Exception e) {
            request.setAttribute(item.getFieldName(), null);
            logger.error(item.toString(), e);
            throw new ExecutingCommandsException("Ошибка сохранения временного файла на диск.");
        }
    }

    private void createStringAttribute(HttpServletRequest request, FileItem item) {
        try {
            request.setAttribute(item.getFieldName(), item.getString("utf-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error(item.toString(), e);
            throw new ExecutingCommandsException("Ошибка конвертирования параметра в строковой атрибут.");
        }
    }

    public void writeToLogAndConsoleAllAttribute() {
        Enumeration<String> names = request.getAttributeNames();
        String requestParameters = "Request attribute from client: \n";
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            requestParameters += name + ": " + request.getAttribute(name) + "\n";
        }
        logger.info(requestParameters);
    }

}
