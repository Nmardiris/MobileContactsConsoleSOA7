package gr.aueb.cf.mobilecontacts.dao;

import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.ArrayList;
import java.util.List;

public class MobileContactDAOImpl implements IMobileContactDao{
    private static final List<MobileContact> contacts = new ArrayList<>();    // ως data source - παίρνουμε/δημιουργούμε μια λίστα container ενός πίνακα, δε χρειάζεται να βάλουμε δεξιά τι ArrayList<MobileContact>, στην πραγματικότητα είναι ένας constructor
    private  static Long id = 1L;


    @Override
    public MobileContact insert(MobileContact mobileContact) {
        mobileContact.setId(id++);
        contacts.add(mobileContact);
        return mobileContact;
    }

    @Override
    public MobileContact update(Long id, MobileContact mobileContact) {   // εισάγουμε μια private μέθοδο(βοηθητική η οποία επιστρέφει το index ενος mobile contact id)  πριν κάνουμε το update !!
        contacts.set(getInbexById(id), mobileContact);
        return mobileContact;
    }

    @Override
    public void deleteById(Long id) {
//        contacts.remove(getInbexById(id));
            contacts.removeIf(contact -> contact.getId().equals(id));
    }

    @Override
    public MobileContact getById(Long id) {
        int positionToReturn = getInbexById(id);
        return (positionToReturn != -1) ? contacts.get(positionToReturn) : null;
        // if ...then ....return...else...
    }

    @Override
    public List<MobileContact> getAll() {
        return new ArrayList<>(contacts); // επειδη το contacts ειναι immutable το επιστρέφουμε ως new arraylist για να μην ειναι mutable
    }

    @Override
    public void deleteByPhoneNumber(String phoneNumber) {
        contacts.removeIf(contact -> contact.getPhoneNumber().equals(phoneNumber));
    }

    @Override
    public MobileContact getByPhoneNumber(String phoneNumber) {
        int positionToReturn = getInbexByPhoneNumber(phoneNumber);
        return (positionToReturn != -1) ? contacts.get(positionToReturn) : null;
    }

    @Override
    public boolean userIdExists(Long id) {
        int position = getInbexById(id);
        return position != -1;
    }

    @Override
    public boolean phoneNumberExists(String phoneNumber) {
        int position = getInbexByPhoneNumber(phoneNumber);
        return position != -1;
    }

    private int getInbexById(Long id) {
        int positionToReturn = -1;

        for (int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).getId().equals(id)) {
                positionToReturn = i;
                break;
            }
        }
        return positionToReturn;
    }

    private int getInbexByPhoneNumber(String phoneNumber) {
        int positionToReturn = -1;

        for (int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).getPhoneNumber().equals(phoneNumber)) {
                positionToReturn = i;
                break;
            }
        }
        return positionToReturn;
    }
}
