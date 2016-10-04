package test;


import main.DTO.ContactDTO;
import main.Validator.Validator;
import org.junit.Assert;
import org.junit.Test;

public class ValidatorJUnit extends Assert{
    @Test
    public void checkTest() throws IllegalAccessException {
        Validator validator = new Validator();
        validator.check(new ContactDTO().setFirstName("Alex").setCity("Slonim"));
    }


}
