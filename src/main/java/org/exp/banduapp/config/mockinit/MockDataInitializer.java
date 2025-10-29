package org.exp.banduapp.config.mockinit;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.entities.Place;
import org.exp.banduapp.models.entities.Role;
import org.exp.banduapp.models.entities.User;
import org.exp.banduapp.models.enums.PlaceStatus;
import org.exp.banduapp.repository.PlaceRepository;
import org.exp.banduapp.repository.RoleRepository;
import org.exp.banduapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.exp.banduapp.models.enums.RoleName.*;

@Component
@RequiredArgsConstructor
public class MockDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final PlaceRepository placeRepository;

    @Override
    public void run(String... args) throws Exception {

        Set<Role> roles = new HashSet<>(List.of(
                new Role(ROLE_SUPER_ADMIN),
                new Role(ROLE_ADMIN),
                new Role(ROLE_USER)
        ));

        if (roleRepository.count() == 0) {
            roleRepository.saveAll(roles);
        }

        if (userRepository.count() == 0) {
            userRepository.save(User.builder()
                            .firstName("Eshmat")
                            .lastName("Toshmatov")
                            .phoneNumber("+998901234567")
                            .password(passwordEncoder.encode("123456"))
                            .visibility(true)
                            .roles(roles)
                    .build());
        }

        if (placeRepository.count() == 0) {
            placeMockDataSaver();
        }
    }

    private void placeMockDataSaver() {
        placeRepository.saveAll(List.of(
                // 1. Cozy Cafe - ACTIVE
                Place.builder()
                        .name("Cozy Cafe")
                        .description("Shinam kafe, oilaviy dam olish uchun ideal. Wi-Fi, shirinliklar va qahva.")
                        .address("Toshkent, Chilonzor tumani, 45-uy")
                        .capacity(25)
                        .pricePerHour(new BigDecimal("150000"))
                        .status(PlaceStatus.ACTIVE)
                        .visibility(true)
                        .build(),

                // 2. Sky Lounge - ACTIVE
                Place.builder()
                        .name("Sky Lounge")
                        .description("Yuqori qavatdagi lounge, shahar manzarasi bilan. Kechki ovqat va kokteyllar.")
                        .address("Toshkent, Yunusobod, Amir Temur ko‘chasi, 100-uy")
                        .capacity(50)
                        .pricePerHour(new BigDecimal("300000"))
                        .status(PlaceStatus.ACTIVE)
                        .visibility(true)
                        .build(),

                // 3. Green Garden - ACTIVE
                Place.builder()
                        .name("Green Garden")
                        .description("Tabiat qo‘ynida, ochiq havo restorani. Barbekyu va oilaviy tadbirlar.")
                        .address("Toshkent, Sergeli, Park ko‘chasi, 12")
                        .capacity(35)
                        .pricePerHour(new BigDecimal("180000"))
                        .status(PlaceStatus.ACTIVE)
                        .visibility(true)
                        .build(),

                // 4. Old Town Hall - MAINTENANCE
                Place.builder()
                        .name("Old Town Hall")
                        .description("Tarixiy bino, ta'mirlash jarayonida. Tez orada qayta ochiladi.")
                        .address("Toshkent, Shayxontohur, Eski Shahar, 8")
                        .capacity(40)
                        .pricePerHour(new BigDecimal("250000"))
                        .status(PlaceStatus.MAINTENANCE)
                        .visibility(true)
                        .build(),

                // 5. Tech Hub - MAINTENANCE (admin uchun ko‘rinadi, foydalanuvchi uchun emas)
                Place.builder()
                        .name("Tech Hub")
                        .description("IT kompaniyalar uchun kovorking. Hozir ta'mirlashda.")
                        .address("Toshkent, Mirzo Ulug‘bek, IT Park, 5-qavat")
                        .capacity(60)
                        .pricePerHour(new BigDecimal("350000"))
                        .status(PlaceStatus.MAINTENANCE)
                        .visibility(false)  // Foydalanuvchi ko‘rmaydi
                        .build()
        ));
    }
}
