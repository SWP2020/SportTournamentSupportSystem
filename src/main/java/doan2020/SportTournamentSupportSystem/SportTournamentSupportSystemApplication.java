package doan2020.SportTournamentSupportSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SportTournamentSupportSystemApplication /*implements CommandLineRunner*/{

	public static void main(String[] args) {
		SpringApplication.run(SportTournamentSupportSystemApplication.class, args);
	}
	
//	@Autowired
//    UserRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Khi chương trình chạy
//        // Insert vào csdl một user.
//        UserEntity user = new UserEntity();
//        user.setUserName("a");
//        user.setPassword(passwordEncoder.encode("loda"));
//        Long id = (long) 1;
////        userRepository.save(user);
////        user = userRepository.findById();
////        System.out.println(user.getUserID().toString());
//    }

}
