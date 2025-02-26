package gr.aueb.cf.mobilecontacts.core;

import gr.aueb.cf.mobilecontacts.dto.MobileContactReadOnlyDTO;

public class Serializer {

    /**
     * No instances of this class should be available
     */
    private Serializer(){

    }

    public static String serializerDTO(MobileContactReadOnlyDTO readOnlyDTO) {
        return "ID: " + readOnlyDTO.getId() + ", Όνομα " + readOnlyDTO.getFirstname()
                + ", Επώνυμο¨" + readOnlyDTO.getLastname() + ", Τηλ. Αριθμός" + readOnlyDTO.getPhoneNumber();
    }
}
