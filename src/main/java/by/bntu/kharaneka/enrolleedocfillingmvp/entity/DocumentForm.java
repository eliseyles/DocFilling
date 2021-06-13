package by.bntu.kharaneka.enrolleedocfillingmvp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DocumentForm {
    private Integer id;
    private String type;
    private String number;
    private String governmentAgency;
    private String identificationNumber;
}
