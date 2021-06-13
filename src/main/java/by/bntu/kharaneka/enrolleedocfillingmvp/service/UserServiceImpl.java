package by.bntu.kharaneka.enrolleedocfillingmvp.service;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.*;
import by.bntu.kharaneka.enrolleedocfillingmvp.exception.UserServiceException;
import by.bntu.kharaneka.enrolleedocfillingmvp.exception.ValidationException;
import by.bntu.kharaneka.enrolleedocfillingmvp.repository.UserRepository;
import by.bntu.kharaneka.enrolleedocfillingmvp.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User saveUser(UserRegistrationForm userRegistrationForm, Contract contract)
            throws UserServiceException, ValidationException {
        User user = null;
        if (userRepository.findByLogin(userRegistrationForm.getLogin()).isEmpty()) {

                UserValidation.checkPassword(userRegistrationForm);
                user = User
                        .builder()
                        .login(userRegistrationForm.getLogin())
                        .password(passwordEncoder.encode(userRegistrationForm.getPassword()))
                        .role(Role.USER)
                        .status(Status.ACTIVE)
                        .contract(contract)
                        .build();
        } else {
            throw new UserServiceException("User already exist");
        }
        return userRepository.save(user);
    }
}
