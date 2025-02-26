package gr.aueb.cf.mobilecontacts.dao;



import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.List;

public interface IMobileContactDao {
    MobileContact insert(MobileContact mobileContact);
    MobileContact update(Long id, MobileContact mobileContact);
    void deleteById(Long id);                                           // είναι υπηρεσία
    MobileContact getById(Long id);
    List<MobileContact> getAll();            // λίστα ώστε να φέρνουμε όλα τα contacts είναι κλάση η
                                             // list kai υλοποιείται απο την Arraylist
                                             // στην πραγματικότητα είναι ένα dynamic Array
                                             // ta < > σημαίνουν πως τα στοιχεία αυτής της κλάσης είναι contact
                                             // δεν μπορούμε να έχουμε κάτι περισσότερο απο κλάσεις (δεν παίρνει πρωταρχικούς τύπους δεδομένων)


    void deleteByPhoneNumber(String phoneNumber);

    // απο κάτω όλα είναι quires - φίλτρα για παράδειγμα
    MobileContact getByPhoneNumber(String phoneNumber);
    boolean userIdExists(Long id);
    boolean phoneNumberExists(String PhoneNumber);

}
