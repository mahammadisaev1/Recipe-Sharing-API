//package com.masteringbackend.recipeSharingAPI.entities;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Entity
//@Table(name = "reviews")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Review {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String review;
//
//    @ManyToOne
//    @JoinColumn(name = "recipe_id")
//    private Recipe recipe;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//}
