package by.bntu.kharaneka.enrolleedocfillingmvp.validation;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.UserRegistrationForm;
import by.bntu.kharaneka.enrolleedocfillingmvp.exception.ValidationException;


public class UserValidation {

    public static void checkPassword(UserRegistrationForm userRegistrationForm) throws ValidationException {
        if (!userRegistrationForm.getPassword().equals(userRegistrationForm.getSecondPassword())){
            throw new ValidationException();
        }
    }
}
