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

}
