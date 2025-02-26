package gr.aueb.cf.mobilecontacts.validation;

import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;

public class ValidationUtil {

    /**
     * No instances of this class should be available
     */
    private ValidationUtil(){

    }

    public static String validateDTO(MobileContactInsertDTO insertDTO) {
        String errorResponce ="";

        if (insertDTO.getPhoneNumber().length() <= 5)
            errorResponce += "Ο αριθμός τηλεφώνου πρεπει να έχει περισσότερα απο 5 σύμβολα \n";
        if (insertDTO.getFirstname().length() < 2)
            errorResponce += "Tο όνομα πρέπει να περιέχει 2 ή περισσότερους χαρακτήρες.\n";
        if (insertDTO.getLastname().length() < 2)
            errorResponce += "Tο επώνυμο πρέπει να περιέχει 2 ή περισσότερους χαρακτήρες.\n";

        return errorResponce;
    }

    public static String validateDTO(MobileContactUpdateDTO updateDTO) {
        String errorResponce ="";

        if (updateDTO.getPhoneNumber().length() <= 5)
            errorResponce += "Ο αριθμός τηλεφώνου πρεπει να έχει περισσότερα απο 5 σύμβολα \n";
        if (updateDTO.getFirstname().length() < 2)
            errorResponce += "Tο όνομα πρέπει να περιέχει 2 ή περισσότερους χαρακτήρες.\n";
        if (updateDTO.getLastname().length() < 2)
            errorResponce += "Tο επώνυμο πρέπει να περιέχει 2 ή περισσότερους χαρακτήρες.\n";

        return errorResponce;
    }
}
