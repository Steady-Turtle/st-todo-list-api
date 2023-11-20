package com.example.anna.api._config.security;

import com.example.anna.entity.user.domain.User;
import com.example.anna.entity.user.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomUserDetailsService implements UserDetailsService {
    @NonNull
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userId) {
        User user = userRepository.getByUserId(userId);

        return new UserPrincipal(
                user.getUserId(), user.getPassword(), user.getRole(), true, user.checkActiveUser(), user);
    }
}
