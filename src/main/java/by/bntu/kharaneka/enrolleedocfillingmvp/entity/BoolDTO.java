package by.bntu.kharaneka.enrolleedocfillingmvp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoolDTO {

    @JsonProperty("checked")
    private Boolean isEighteen;
}
