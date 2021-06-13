package by.bntu.kharaneka.enrolleedocfillingmvp.service;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Contract;
import by.bntu.kharaneka.enrolleedocfillingmvp.entity.User;
import by.bntu.kharaneka.enrolleedocfillingmvp.entity.UserRegistrationForm;
import by.bntu.kharaneka.enrolleedocfillingmvp.exception.UserServiceException;
import by.bntu.kharaneka.enrolleedocfillingmvp.exception.ValidationException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User findById(Integer id);

    public List<User> findAll();

    public User saveUser(UserRegistrationForm userRegistrationForm, Contract contract)
            throws UserServiceException, ValidationException;

    public void deleteById(Integer id);

    Optional<User> findByLogin(String login);
}
