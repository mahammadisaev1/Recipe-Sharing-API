//package com.masteringbackend.recipeSharingAPI.init;
//
//import com.masteringbackend.recipeSharingAPI.entities.Role;
//import com.masteringbackend.recipeSharingAPI.repos.RoleRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class RoleInitializer implements CommandLineRunner {
//
//    private final RoleRepository roleRepo;
//
//    @Override
//    public void run(String... args) throws Exception {
//        if (roleRepo.findByName("ROLE_ADMIN") == null) {
//            Role adminRole = new Role();
//            adminRole.setName("ROLE_ADMIN");
//            roleRepo.save(adminRole);
//            System.out.println("ROLE_ADMIN created.");
//        }
//
//        if (roleRepo.findByName("ROLE_USER") == null) {
//            Role userRole = new Role();
//            userRole.setName("ROLE_USER");
//            roleRepo.save(userRole);
//            System.out.println("ROLE_USER created.");
//        }
//    }
//}
