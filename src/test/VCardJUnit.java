package test;



import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.parameter.AddressType;
import ezvcard.property.Address;
import ezvcard.property.Nickname;
import org.junit.Assert;
import org.junit.Test;

public class VCardJUnit extends Assert{
    @Test
    public void vCard(){
        VCard vcard = new VCard();
        Nickname nickname = new Nickname();
        nickname.getValues().add("Ricky");
        nickname.getValues().add("Bobby");
        nickname.getValues().add("Ricky Bobby");
        vcard.setNickname(nickname);
        Address adr = new Address();
        adr.setStreetAddress("123 Main St.");
        adr.setLocality("Austin");
        adr.setRegion("TX");
        adr.setPostalCode("12345");
        adr.setCountry("USA");
        adr.getTypes().add(AddressType.WORK);
        adr.setLabel("123 Main St.\nAustin, TX 12345\nUSA");
        vcard.addAddress(adr);
        String str = Ezvcard.write(vcard).version(VCardVersion.V4_0).go();
        System.out.println(str);
    }
}
