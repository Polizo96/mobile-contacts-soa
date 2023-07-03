import dao.IMobileContactDAO;
import dao.MobileContactDAOImpl;
import dto.MobileContactDTO;
import dto.UserDetailsDTO;
import model.MobileContact;
import service.IMobileContactService;
import service.MobileContactServiceImpl;
import service.exceptions.MobileContactNotFoundException;
import service.exceptions.PhoneNumberAlreadyExistsException;
import service.exceptions.UserIdAlreadyExistsException;

import java.util.List;

public class MobileContactsApp {

    // Wiring
    private final static IMobileContactDAO dao = new MobileContactDAOImpl();
    private final static IMobileContactService service = new MobileContactServiceImpl(dao);

    public static void main(String[] args) {
        try {

            // DTO
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO(1L,"Alice","W.");
            MobileContactDTO mobileContactDTO = new MobileContactDTO(1L,userDetailsDTO, "123456789");

            UserDetailsDTO updatedUserDetailsDTO = new UserDetailsDTO(1L,"Alice","Wonderland");
            MobileContactDTO updatedMobileContactDTO = new MobileContactDTO(1L,updatedUserDetailsDTO,"123456789");

            // Service
            service.insertMobileContact(mobileContactDTO);
            service.updateMobileContact(1L,updatedMobileContactDTO);

            // Return back
            List<MobileContact> contacts = service.getAllMobileContacts();
            for (MobileContact contact : contacts){
                System.out.println(contact);
            }

        } catch (PhoneNumberAlreadyExistsException | UserIdAlreadyExistsException | MobileContactNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
