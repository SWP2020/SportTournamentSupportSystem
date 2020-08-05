package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.RoleEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.repository.RoleRepository;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private RoleRepository roleRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// Kiểm tra xem user có tồn tại trong database không?
////		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        UserEntity user = userRepository.findByUsername(username);
//        RoleEntity roleEntity = roleRepository.findOneById(user.getRole().getId());
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority(roleEntity.getName()));
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//        return new User(username, /*passwordEncoder.encode*/(user.getPassword()), enabled, accountNonExpired, credentialsNonExpired,
//            accountNonLocked, authorities);
//	}
//
////	// JWTAuthenticationFilter sẽ sử dụng hàm này
////    @Transactional
////    public UserDetails loadUserById(Long id) {
////        UserEntity user = userRepository.findById(id).orElseThrow(
////                () -> new UsernameNotFoundException("User not found with id : " + id)
////        );
////
////        return new CustomUserDetails(user);
////    }

}
