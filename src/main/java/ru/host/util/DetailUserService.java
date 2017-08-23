package ru.host.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.host.repository.DetailUserRepository;

@Service("detailUserService")
public class DetailUserService implements UserDetailsService {

    @Autowired
    private DetailUserRepository detailUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return detailUserRepository.findOne(username);
    }
}