package com.example.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private  Long user_id;

    @Column(columnDefinition = "nvarchar(255)")
    public String user_full_name;

    @Column(columnDefinition = "nvarchar(255)")
    public String username;

    @Column(columnDefinition = "nvarchar(255)")
    public String user_address;

    @Column(columnDefinition = "nvarchar(255)")
    public String email;

    @Column(columnDefinition = "nvarchar(255)")
    public String user_password;

    @Column(columnDefinition = "Float")
    public Float user_money;


    @Column(columnDefinition = "nvarchar(255)")
    public String users_code;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",joinColumns = {@JoinColumn(referencedColumnName = "user_id") },
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "role_id")}
    )
    private Set<Role> roles;

    public User(Long user_id, String user_full_name, String username, String user_address, String email, String user_password, Float user_money, String users_code) {
        this.user_id = user_id;
        this.user_full_name = user_full_name;
        this.username = username;
        this.user_address = user_address;
        this.email = email;
        this.user_password = user_password;
        this.user_money = user_money;
        this.users_code = users_code;
    }

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "users",fetch = FetchType.EAGER)
    private Set<RoomsUser> roomsUsers;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "users" ,fetch = FetchType.EAGER)
    private Set<Rooms> rooms;
}
