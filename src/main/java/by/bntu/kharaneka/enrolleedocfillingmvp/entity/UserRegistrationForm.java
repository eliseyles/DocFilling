package by.bntu.kharaneka.enrolleedocfillingmvp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationForm {
    private String login;
    private String password;
    private String secondPassword;
}
