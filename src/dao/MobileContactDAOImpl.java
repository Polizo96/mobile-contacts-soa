package dao;

import model.MobileContact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MobileContactDAOImpl implements IMobileContactDAO {
    private static final List<MobileContact> contacts = new ArrayList<>();

    /**
     *
     * @param mobileContact
     * @return
     */
    @Override
    public MobileContact insert(MobileContact mobileContact) {
        if (mobileContact == null) return null;
        contacts.add(mobileContact);
        return mobileContact;
    }

    /**
     *
     * @param id
     * @param mobileContact
     * @return
     */
    @Override
    public MobileContact update(long id, MobileContact mobileContact) {
        if (id != mobileContact.getId()) return null;
        int positionToUpdate = getIndexById(id);

        return contacts.set(positionToUpdate, mobileContact);
    }

    /**
     *
     * @param id
     */
    @Override
    public void delete(long id) {
        contacts.removeIf((contact) -> contact.getId() == id);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public MobileContact get(long id) {
        int pos = getIndexById(id);
        if (pos == -1) return null;

        return contacts.get(pos);
    }

    /**
     *
     * @return
     */
    @Override
    public List<MobileContact> getAll() {
//      return new ArrayList<>(contacts);
        return Collections.unmodifiableList(contacts);
    }

    /**
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public MobileContact get(String phoneNumber) {
        int pos = getIndexByPhoneNumber(phoneNumber);
        if (pos == -1) return null;

        return contacts.get(pos);
    }

    /**
     *
     * @param phoneNumber
     */
    @Override
    public void delete(String phoneNumber) {
        contacts.removeIf(contact -> contact.getPhoneNumber().equals(phoneNumber));
    }

    /**
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public boolean phoneNumberExists(String phoneNumber) {
        return getIndexByPhoneNumber(phoneNumber) != -1;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean userIdExists(long id) {
        return getIndexById(id) != -1;
    }

    /**
     *
     * @param id
     * @return
     */
    private int getIndexById(long id) {
        int pos = -1;

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == id) {
                pos = i;
                break;
            }
        }

        return pos;
    }

    /**
     *
     * @param phoneNumber
     * @return
     */
    private int getIndexByPhoneNumber(String phoneNumber) {
        int pos = -1;

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phoneNumber)) {
                pos = i;
                break;
            }
        }

        return pos;
    }
}
