package nl.tudelft.oopp.demo.security;

import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository rep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = rep.getByUserName(username);
        CustomUserDetails userDetails = null;
        if (user == null) {
            throw new UsernameNotFoundException("There is no user with the username " + username);
        } else {
            userDetails = new CustomUserDetails();
            userDetails.setUser(user);
        }
        return userDetails;
    }
}
