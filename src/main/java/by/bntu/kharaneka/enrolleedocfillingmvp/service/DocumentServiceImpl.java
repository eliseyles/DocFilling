package by.bntu.kharaneka.enrolleedocfillingmvp.service;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Document;
import by.bntu.kharaneka.enrolleedocfillingmvp.entity.DocumentForm;
import by.bntu.kharaneka.enrolleedocfillingmvp.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document findById(Integer id) {
        return documentRepository.getOne(id);
    }

    @Override
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    @Override
    public Document saveDocument(DocumentForm documentForm, Date date) {
        return documentRepository.save(Document.builder()
                .id(documentForm.getId())
                .type(documentForm.getType())
                .number(documentForm.getNumber())
                .date(date)
                .governmentAgency(documentForm.getGovernmentAgency())
                .identificationNumber(documentForm.getIdentificationNumber())
                .build());
    }

    @Override
    public void deleteById(Integer id) {
        documentRepository.deleteById(id);
    }
}
