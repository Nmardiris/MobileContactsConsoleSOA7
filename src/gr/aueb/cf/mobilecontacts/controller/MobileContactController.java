package gr.aueb.cf.mobilecontacts.controller;

import gr.aueb.cf.mobilecontacts.core.Serializer;
import gr.aueb.cf.mobilecontacts.dao.IMobileContactDao;
import gr.aueb.cf.mobilecontacts.dao.MobileContactDAOImpl;
import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactReadOnlyDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;
import gr.aueb.cf.mobilecontacts.exceptions.ContactNotFoundException;
import gr.aueb.cf.mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.mapper.Mapper;
import gr.aueb.cf.mobilecontacts.model.MobileContact;
import gr.aueb.cf.mobilecontacts.service.IMobileContactService;
import gr.aueb.cf.mobilecontacts.service.MobileContactServiceImpl;
import gr.aueb.cf.mobilecontacts.validation.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class MobileContactController {

    private final IMobileContactDao dao = new MobileContactDAOImpl();
    private final IMobileContactService service = new MobileContactServiceImpl(dao);

    public String insertContact(MobileContactInsertDTO insertDTO) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            // Validate input data
            String errorVector = ValidationUtil.validateDTO(insertDTO);
            if (!errorVector.isEmpty()) {
                return "Error." + "Validation Erron \n" + errorVector;
            }

            // if Validation  is ok, insert contact
            mobileContact = service.insertMobileContact(insertDTO);
            readOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);
            return "OK\n" + Serializer.serializerDTO(readOnlyDTO);
        } catch (PhoneNumberAlreadyExistsException e){
            return "Error\n" + e.getMessage() + "\n";
        }
    }

    public String updatetContact(MobileContactUpdateDTO updateDTO) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            // Validate input data
            String errorVector = ValidationUtil.validateDTO(updateDTO);
            if (!errorVector.isEmpty()) {
                return "Error.\n" + "Validation Erron \n" + errorVector;
            }

            // if Validation  is ok, insert contact
            mobileContact = service.updateMobileContact(updateDTO);
            readOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);
            return "OK\n" + Serializer.serializerDTO(readOnlyDTO);
        } catch (PhoneNumberAlreadyExistsException e){
            return "Error\n Λάθος κατα την ενημέρωση \n" + e.getMessage() + "\n";
        } catch (ContactNotFoundException e){
            return "Error.\n" + e.getMessage()+ "\n";
        }
    }

    public String deleteContactById(Long id) {
        try {
            service.deleteContactById(id);
            return "Ok\n Η επαφή διαγράφηκε";
        } catch (ContactNotFoundException e) {
            return "Error. \n Λάθος κατά την διαγραφή , η επαφή δεν βρέθηκε";
        }
    }

    public String getContactById(Long id) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            mobileContact = service.getContactById(id);
            readOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);
            return "Ok\n" + Serializer.serializerDTO(readOnlyDTO);
        } catch (ContactNotFoundException e) {
            return "Error. \n H επαφή δεν βρέθηκε \n";
        }
    }

    public List<String> getAllContacts(){
        List<MobileContact> contacts;
        List<String> serializedList = new ArrayList<>();
        MobileContactReadOnlyDTO readOnlyDTO;
        String serialized;

        contacts = service.getALLContacts();

        for (MobileContact contact : contacts) {
            readOnlyDTO = Mapper.mapMobileContactToDTO(contact);
            serialized = Serializer.serializerDTO(readOnlyDTO);
            serializedList.add(serialized);
        }

        return serializedList;
    }

    public String getContactByPhoneNumber(String phoneNumber) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            mobileContact = service.getContactByPhoneNumber(phoneNumber);
            readOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);
            return "Ok\n" + Serializer.serializerDTO(readOnlyDTO);
        } catch (ContactNotFoundException e) {
            return "Error. \n H επαφή δεν βρέθηκε \n";
        }
    }

    public String deleteContactByPhoneNumber(String phoneNumber) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;

        try {
            mobileContact = service.getContactByPhoneNumber(phoneNumber);
            readOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);
            service.deleteContactByPhoneNumber(phoneNumber);

            return "Ok\n Η επαφή διαγράφηκε" + Serializer.serializerDTO(readOnlyDTO);
        } catch (ContactNotFoundException e) {
            return "Error. \n Λάθος κατά την διαγραφή. H επαφή δεν βρέθηκε";
        }
    }
}
