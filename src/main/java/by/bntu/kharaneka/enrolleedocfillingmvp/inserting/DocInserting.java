package by.bntu.kharaneka.enrolleedocfillingmvp.inserting;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Contract;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DocInserting {

    public File insertData(Contract contract) throws IOException, InvalidFormatException {
        XWPFDocument doc = null;
//        if (contract.isEighteen()) {
//            doc = new XWPFDocument(OPCPackage.open("src/main/resources/static/template_eighteen.docx"));
//        } else {
//        }
        doc = new XWPFDocument(OPCPackage.open("src/main/resources/static/template.docx"));

        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains("name")) {
                        text = text.replace("name", contract.getPersonality().getName());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("patronymic")) {
                        text = text.replace("patronymic", contract.getPersonality().getPatronymic());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("surname")) {
                        text = text.replace("surname", contract.getPersonality().getSurname());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("email")) {
                        text = text.replace("email", contract.getPersonality().getEmail());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("phone")) {
                        text = text.replace("phone", contract.getPersonality().getPhone());
                        r.setText(text, 0);
                    }
//                    address
                    if (text != null && text.contains("region")) {
                        text = text.replace("region", contract.getAddress().getRegion());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("city")) {
                        text = text.replace("city", contract.getAddress().getCity());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("street")) {
                        text = text.replace("street", contract.getAddress().getStreet());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("building")) {
                        text = text.replace("building", contract.getAddress().getBuilding());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("flat")) {
                        text = text.replace("flat", contract.getAddress().getFlat());
                        r.setText(text, 0);
                    }
//                    id
                    if (text != null && text.contains("type")) {
                        text = text.replace("type", contract.getDocument().getType());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("number")) {
                        text = text.replace("number", contract.getDocument().getNumber());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("date")) {
                        text = text.replace("date", contract.getDocument().getDate().toString());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("government")) {
                        text = text.replace("government", contract.getDocument().getGovernmentAgency());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("identification")) {
                        text = text.replace("identification", contract.getDocument().getIdentificationNumber());
                        r.setText(text, 0);
                    }
                }
            }
        }
        doc.write(new FileOutputStream("src/main/resources/static/out/output_" + contract.getId() + ".docx"));
        return new File("src/main/resources/static/out/output_" + contract.getId() + ".docx");
        //return new File("src/main/resources/static/template.docx");
    }
}
