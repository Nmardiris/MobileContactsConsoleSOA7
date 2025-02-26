package gr.aueb.cf.mobilecontacts.model;

import java.util.Objects;

public class MobileContact extends AbstractEntity{
    private String firstname;
    private String lastname;
    private String phoneNumber;

    public MobileContact(){

    }

    public MobileContact(Long id, String firstname, String lastname, String phoneNumber) {
        setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // η το string είναι μία μέθοδος που κληρονομήτε απο την object κλάση δεν κάνει κατι αλλά βοηθάει στο logging
    // στην τουστρινγ ακόμη και αν τα firstname κλπ δεν ήταν στρινγκ απο την στιγμή που υπάρχει έστω και να " " γίνονται ολα μεταροπή σε string
    @Override
    public String toString() {
        return "Firstname: " + firstname + ", Lastname: " + lastname + ", phoneNumber" + phoneNumber;
    }

    @Override
    public boolean equals(Object other) { // το other είναι αυτό με το οποίο συγκρίνουμε το this!
        if (this == other) return true;
                                                                        //if (!(other instanceof MobileContact)) return false;
                                                                        // MobileContact that = (MobileContact) other;

                                                                        // In Java 17 το παρακάτω κάνει δύο πράγματα
                                                                        // Έλεγχο αν είναι instanceof και αν ναι μετά κάνει
                                                                        // typecast (όπως κάναμε πριν τη Java 17 με τον παραπάνω κώδικα)

        if (!(other instanceof MobileContact that)) return false; // to that είναι ψευδώνυμο του mobile contact γίνεται typeCast sto that
        return Objects.equals(getFirstname(), that.getFirstname()) // γίνεται έλεγχος με αυτές τις εντολές
                && Objects.equals(getLastname(), that.getLastname())
                && Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

    @Override
    public int hashCode() { // είναι μια συνάρτηση που δημιουργεί χάσεις αν είναι equals έχουν ίδιο χασ αλλιώς διαφορετικό
        return Objects.hash(getFirstname(), getLastname(), getPhoneNumber());  // στις σημειώσεις έχει πιο πολύπλοκο τρόπο για δημιουργία hash
    }
}
