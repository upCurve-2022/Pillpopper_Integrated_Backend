package com.Pill.Popper.dao.entity;

import com.Pill.Popper.dao.validation.ValidPassword;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(	name = "user_info",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstname;
    
    @Column(name = "last_name", nullable = false)
    private String lastname;
    
    @Column(name = "gender", nullable = false)
    private String gender;
    
    @Column(name = "date_of_birth", nullable = false)
    private String dob;
    
    @Column(name = "age", nullable = false)
    private String age;
    
    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @ValidPassword
    @NonNull
    @NotBlank(message = "New password is mandatory")
    @Min(5)
    private String password;

    @Column(name = "mobile_no", nullable = false)
    private String mobile_no;
    
    @Column(name = "address", nullable = false)
    private String address;
    

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<RoleEntity> roles= new HashSet<>();
    
    public UserEntity() {
    	
    }

    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
   
}
