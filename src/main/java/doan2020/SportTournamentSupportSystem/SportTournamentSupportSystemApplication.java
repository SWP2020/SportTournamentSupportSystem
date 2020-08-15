package doan2020.SportTournamentSupportSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import doan2020.SportTournamentSupportSystem.config.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class SportTournamentSupportSystemApplication /*implements CommandLineRunner*/{

	public static void main(String[] args) {
		SpringApplication.run(SportTournamentSupportSystemApplication.class, args);
	}

}
