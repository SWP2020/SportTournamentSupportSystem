package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.entity.UserTestEntity;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private UserConverter userConverter;
	

	@Override
	public Response findAll(Pageable pageable) {
		Response results = new Response();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<UserDtOut> listUsers = new ArrayList<>();
		try {
		List<UserTestEntity> entities = userRepository.findAll(pageable).getContent();
		for (UserTestEntity item: entities) {
			UserDtOut userDtOut = userConverter.toDTO(item);
			listUsers.add(userDtOut);
			result.put("listUsers", listUsers);
		    error.put("messageCode", "001");
		    error.put("message", "get List Users successfully");
		}
		}catch (Exception e) {
		    error.put("messageCode", "002");
		    error.put("message", "ko get List Users");
		}
		
	    
	    results.setResult(result);
	    results.setError(error);
		
		
		return results;
	}

	
	
}
