package by.bntu.kharaneka.enrolleedocfillingmvp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "doc_info")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_info_id", referencedColumnName = "id")
    private Personality personality;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_personal_info_id", referencedColumnName = "id")
    private Personality parentPersonality;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identify_document_id", referencedColumnName = "id")
    private Document document;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_identify_document_id", referencedColumnName = "id")
    private Document parentDocument;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_address_id", referencedColumnName = "id")
    private Address parentAddress;

    @Column(name = "is_eighteen")
    private boolean isEighteen;

}
