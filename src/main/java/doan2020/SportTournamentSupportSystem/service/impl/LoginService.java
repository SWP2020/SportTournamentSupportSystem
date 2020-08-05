package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dtIn.LoginDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ILoginService;

@Service
public class LoginService implements ILoginService {

	@Override
	public Response checkLogin(LoginDtIn loginDtIn) {
		// TODO Auto-generated method stub
		return null;
	}
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//
//	@Autowired
//	private JwtService jwtService;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private UserConverter userConverter;
//
//	@Override
//	public Response checkLogin(LoginDtIn user) {
//		Response results = new Response();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		try {
//			System.out.println(user.getUsername());
//			UserEntity findUser = userRepository.findByUsername(user.getUsername());
//			System.out.println(findUser);
//			boolean checkPW = passwordEncoder.matches(user.getPassword(), findUser.getPassword());
//			System.out.println("check pass OK");
//			if (StringUtils.equals(user.getUsername(), findUser.getUsername()) && checkPW) {
//				if (findUser.getStatus().equals("active")) {
//
//					String token = jwtService.generateTokenLogin(user.getUsername());
//					UserDtOut userDtOut = userConverter.toDTO(findUser);
//					
//					System.out.println("convert OK");
//
//					result.put("User", userDtOut);
//					result.put("Authentication", token);
//					error.put("messageCode", 0);
//					error.put("message", "login Successfull");
//
//				} else {
//					error.put("messageCode", 1);
//					error.put("message", "User is not active");
//				}
//			} else {
//				error.put("messageCode", 1);
//				error.put("message", "UserName and PassWrong is Wrong");
//			}
//		} catch (Exception e) {
//			error.put("messageCode", 1);
//			error.put("message", "Has Exception");
//		}
//
//		results.setResult(result);
//		results.setError(error);
//
//		return results;
//	}

}
