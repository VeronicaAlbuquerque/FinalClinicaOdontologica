package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.service.impl.login;

import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.entities.login.AppRoles;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.entities.login.AppUser;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.repository.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("password2");

        userRepository.save(new AppUser("Veronica", "veronica", "vero@digitalhouse.com", hashedPassword, AppRoles.ADMIN));
        userRepository.save(new AppUser("Andres", "andy", "andy@digitalhouse.com", hashedPassword2, AppRoles.USER));


    }
}
