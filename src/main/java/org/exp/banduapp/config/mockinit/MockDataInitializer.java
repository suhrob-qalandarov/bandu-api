package org.exp.banduapp.config.mockinit;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.entities.*;
import org.exp.banduapp.models.enums.PlaceStatus;
import org.exp.banduapp.repository.PlaceRepository;
import org.exp.banduapp.repository.RoadMapRepository;
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
    private final RoadMapRepository roadMapRepository;

    @Override
    public void run(String... args) throws Exception {

        Set<Role> roles = new HashSet<>(List.of(
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

        if (roadMapRepository.count() == 0) {
            roadMapMockDataSaver();
        }
    }

    private void roadMapMockDataSaver() {
        RoadMap mvp = RoadMap.builder()
                .title("MVP (0–2 oy)")
                .description("Minimal ishlaydigan mahsulot")
                .result("Ishlaydigan minimal versiya (MVP)")
                .visibility(true)
                .build();

        mvp.setTasks(List.of(
                RoadMapTask.builder().task("Mobil ilova va admin panelni ishlab chiqish").completed(true).roadMap(mvp).build(),
                RoadMapTask.builder().task("Geo-lokatsiya va navbat logikasi").completed(false).roadMap(mvp).build(),
                RoadMapTask.builder().task("To‘lov tizimi sinov integratsiyasi (Click / Payme)").completed(false).roadMap(mvp).build(),
                RoadMapTask.builder().task("Telegram mini app test versiyasi").completed(false).roadMap(mvp).build(),
                RoadMapTask.builder().task("UI/UX dizayn va brend tayyorlash").completed(true).roadMap(mvp).build()
        ));

        RoadMap testFeedback = RoadMap.builder()
                .title("Test va Feedback (3–5 oy)")
                .description("Pilot sinov va foydalanuvchi tajribasini tahlil qilish bosqichi")
                .result("Barqaror MVP va dastlabki foydalanuvchi bazasi (1 000+)")
                .visibility(true)
                .build();

        testFeedback.setTasks(List.of(
                RoadMapTask.builder().task("10–15 joyda pilot test (barber, klinika, kafe)").roadMap(testFeedback).build(),
                RoadMapTask.builder().task("Foydalanuvchi tajribasi tahlili va optimizatsiya").roadMap(testFeedback).build(),
                RoadMapTask.builder().task("Barqarorlik, tezlik va xavfsizlikni yaxshilash").roadMap(testFeedback).build()
        ));

        RoadMap launch = RoadMap.builder()
                .title("Bozorga chiqish (6–9 oy)")
                .description("Mahsulotni real bozorda ishga tushirish bosqichi")
                .result("5 000+ foydalanuvchi, 100+ joy tizimda")
                .build();

        launch.setTasks(List.of(
                RoadMapTask.builder().task("Promo web-sayt ishga tushirish").roadMap(launch).build(),
                RoadMapTask.builder().task("Brending va reklama (Instagram, Telegram, TikTok)").roadMap(launch).build(),
                RoadMapTask.builder().task("1–2 shaharda soft launch").roadMap(launch).build()
        ));

        RoadMap monetization = RoadMap.builder()
                .title("Monetizatsiya (10–15 oy)")
                .description("Daromad olish mexanizmlarini joriy etish bosqichi")
                .result("Oylik 3 000$+ daromad, 300+ joy faol")
                .build();

        monetization.setTasks(List.of(
                RoadMapTask.builder().task("Joylar uchun obuna tizimi (10–200$/oy)").roadMap(monetization).build(),
                RoadMapTask.builder().task("1% komissiya modeli").roadMap(monetization).build(),
                RoadMapTask.builder().task("Statistik panel va reklama joylari").roadMap(monetization).build()
        ));

        RoadMap invest = RoadMap.builder()
                .title("Investitsiya va Hamkorlik (16–20 oy)")
                .description("Loyihani kengaytirish uchun moliyaviy va strategik sheriklar topish bosqichi")
                .result("50 000$+ investitsiya, 2 strategik hamkor")
                .build();

        invest.setTasks(List.of(
                RoadMapTask.builder().task("Pitch deck va moliyaviy prognozlar tayyorlash").roadMap(invest).build(),
                RoadMapTask.builder().task("IT Park va investorlar bilan uchrashuvlar").roadMap(invest).build(),
                RoadMapTask.builder().task("Yirik tarmoqlar bilan hamkorlik").roadMap(invest).build()
        ));

        RoadMap expand = RoadMap.builder()
                .title("Kengayish (21–24 oy)")
                .description("AI asosida tavsiya tizimi va xalqaro miqyosda kengayish bosqichi")
                .result("100 000+ foydalanuvchi, 500+ joy faol, oylik 10 000$+ daromad")
                .build();

        expand.setTasks(List.of(
                RoadMapTask.builder().task("Tavsiya tizimi (AI asosida)").roadMap(expand).build(),
                RoadMapTask.builder().task("Qo‘shni davlatlarga chiqish (Qozog‘iston, Qirg‘iziston)").roadMap(expand).build(),
                RoadMapTask.builder().task("Bandu Business panel (analitika uchun)").roadMap(expand).build()
        ));

        roadMapRepository.saveAll(List.of(mvp, testFeedback, launch, monetization, invest, expand));
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
