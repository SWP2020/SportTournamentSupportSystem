package doan2020.SportTournamentSupportSystem.testConverter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dto.UserDTO;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.IRoleService;
import doan2020.SportTournamentSupportSystem.validator.Validator;

@RunWith(SpringRunner.class)
public class TestUserConverter {
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private Validator validator;
	
	@Mock
	private IRoleService roleService;
	
	@InjectMocks
	private UserConverter userConverter;
	
	
	@Test
	public void testToEntity() {
		
		UserDTO userDto = new UserDTO((long)1, "username1", "password1", 
				"Nguyen", "Huy", "Manhattan, NY", "123456", true, "2019-01-12", "huy@gmail.com", 
				"avatar1.jpg", "background1.jpg", (long) 2, "active", "abc.com/xyzmn");
		UserEntity userEntity = userConverter.toEntity(userDto);
		System.out.println("_______"+userEntity.getDob());
		Assert.assertEquals("username1", userEntity.getUsername());
	}
}
