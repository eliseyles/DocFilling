package by.bntu.kharaneka.enrolleedocfillingmvp.service;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Document;
import by.bntu.kharaneka.enrolleedocfillingmvp.entity.DocumentForm;

import java.util.Date;
import java.util.List;

public interface DocumentService {
    public Document findById(Integer id);

    public List<Document> findAll();

    public Document saveDocument(DocumentForm documentForm, Date date);

    public void deleteById(Integer id);
}
