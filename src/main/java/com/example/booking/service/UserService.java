package com.example.booking.service;

import com.example.booking.entity.Role;
import com.example.booking.entity.RoomsUser;
import com.example.booking.entity.User;
import com.example.booking.repository.RoomUsersRepository;
import com.example.booking.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private RoomUsersRepository usersRepository;


    public Long getUserIdByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        return userOptional.map(User::getUser_id).orElse(null);
    }


    public Set<String> getUserRolesByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            Set<String> roles = new HashSet<>();
            for (Role role : user.get().getRoles()) {
                roles.add(role.getRole_name());
            }
            return roles;
        } else {
            // Trả về một tập hợp trống hoặc giá trị khác để xử lý trường hợp người dùng không tồn tại
            return Collections.emptySet();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

        // Trích xuất danh sách quyền từ đối tượng User
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
        }
        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getUser_password(),
                authorities

        );

    }

    public RoomsUser addRoomUser(RoomsUser roomsUser) {
        return usersRepository.save(roomsUser);
    }

//
//    public Optional<HistoryUser> findById(Long id){
//        return historyUsersRepository.findById(id);
//    }


}