package com.example.cakeshopapp.Models;

import com.example.cakeshopapp.Models.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "customer_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    @OneToMany (mappedBy ="user", fetch = FetchType.EAGER) //so ova kazuvame deka maprimae obratno do user svojtvoto vo shoppingcard
    private List<Cart> shoppingCarts;

    @Enumerated(value = EnumType.STRING)
    private Roles role;

    public User(String firstName, String lastName, String email, String password, String confirmPassword, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
        this.role = Roles.USER;
    }

    public User() {

    }
}
