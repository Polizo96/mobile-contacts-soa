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

public class Main {
    public static void main(String[] args) {
        // Create an instance of the DAO and Service
        IMobileContactDAO contactDAO = new MobileContactDAOImpl();
        IMobileContactService contactService = new MobileContactServiceImpl(contactDAO);

        // Create a new user details
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setId(1);
        userDetailsDTO.setFirstname("John");
        userDetailsDTO.setLastname("Doe");

        // Create a new mobile contact
        MobileContactDTO contactDTO = new MobileContactDTO();
        contactDTO.setId(1);
        contactDTO.setUserDetails(userDetailsDTO);
        contactDTO.setPhoneNumber("1234567890");

        try {
            // Insert the mobile contact
            MobileContact createdContact = contactService.insertMobileContact(contactDTO);
            System.out.println("Mobile contact created: " + createdContact);

            // Update the mobile contact
            MobileContact updatedContact = contactService.updateMobileContact(1, contactDTO);
            System.out.println("Mobile contact updated: " + updatedContact);
        } catch (MobileContactNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (PhoneNumberAlreadyExistsException | UserIdAlreadyExistsException e) {
            System.out.println("Error creating/updating mobile contact: " + e.getMessage());
        }
    }
}