package by.bntu.kharaneka.enrolleedocfillingmvp.service;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.*;
import by.bntu.kharaneka.enrolleedocfillingmvp.inserting.DocInserting;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class DataInsertingServiceImpl implements DataInsertingService{

    private final DocInserting docInserting;

    @Autowired
    public DataInsertingServiceImpl(DocInserting docInserting) {
        this.docInserting = docInserting;
    }

    @Override
    public File insertData(Contract contract) {
        try {
            return docInserting.insertData(contract);
        } catch (IOException | InvalidFormatException e) {
            return new File("");
        }
    }

    @Override
    public EmptyEntity checkData(Contract contract) {
        EmptyEntity emptyField = EmptyEntity.OK;
        if (contract.getPersonality() == null) {
            emptyField = EmptyEntity.PERSONALITY;
        } else if (contract.getDocument() == null) {
            emptyField = EmptyEntity.DOCUMENT;
        } else if (contract.getAddress() == null) {
            emptyField = EmptyEntity.ADDRESS;
        }
        if(!contract.isEighteen()) {
            if (contract.getParentPersonality() == null) {
                emptyField = EmptyEntity.PARENT_PERSONALITY;
            } else if (contract.getParentDocument() == null) {
                emptyField = EmptyEntity.PARENT_DOCUMENT;
            } else if (contract.getParentAddress() == null) {
                emptyField = EmptyEntity.PARENT_ADDRESS;
            }
        }
        return emptyField;
    }
}
