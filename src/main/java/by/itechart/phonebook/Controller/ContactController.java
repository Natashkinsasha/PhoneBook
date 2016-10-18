package by.itechart.phonebook.Controller;


import by.itechart.phonebook.MVC.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactController {
    @RequestMapping(uri = "/save_contact", method = RequestMapping.Method.POST)
    public void saveContact(HttpServletRequest req, HttpServletResponse resp) {

    }
}
