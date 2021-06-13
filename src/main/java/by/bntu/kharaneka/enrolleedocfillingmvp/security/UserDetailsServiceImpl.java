package by.bntu.kharaneka.enrolleedocfillingmvp.security;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.User;
import by.bntu.kharaneka.enrolleedocfillingmvp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        return SecurityUser.fromUser(user);
    }
}
