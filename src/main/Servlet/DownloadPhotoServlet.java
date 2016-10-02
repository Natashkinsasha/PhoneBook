package main.Servlet;


import main.DTO.ContactDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/download_photo")
public class DownloadPhotoServlet extends HttpServlet{
    final static String UPLOAD_DIRECTORY = "META-INF"+File.separator+"photo";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!ServletFileUpload.isMultipartContent(req)){
            PrintWriter writer = resp.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String uploadPath = getServletContext().getRealPath("")+ File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
            if (formItems!=null&&formItems.size()>0){
                for(FileItem item: formItems){
                    if(!item.isFormField()){
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath+File.separator+fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        contactDTO.setPhotoPath(filePath);
                        req.setAttribute("message", "Upload has been done successfully");
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Error");
        }
        resp.sendRedirect("/createcontact");

    }
}
