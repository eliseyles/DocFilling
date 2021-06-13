package by.bntu.kharaneka.enrolleedocfillingmvp.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "identify_document")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "number")
    private String number;

    @Column(name = "date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @Column(name = "government_agency")
    private String governmentAgency;

    @Column(name = "identification_number")
    private String identificationNumber;

}
