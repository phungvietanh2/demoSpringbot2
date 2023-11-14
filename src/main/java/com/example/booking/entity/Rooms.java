package com.example.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms" )
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rooms_id")
    private  Long rooms_id;

    @Column(columnDefinition = "nvarchar(255)")
    public String rooms_address;

    @Column(columnDefinition = "nvarchar(255)")
    public String rooms_title;

    @Column(columnDefinition = "nvarchar(max)")
    public String rooms_description;

    @Column(columnDefinition = "float")
    public Float rooms_price;

    @Column(columnDefinition = "float")
    public Float rooms_rating;

    @Column(name = "rooms_state")
    @Enumerated(EnumType.STRING)
    public State state;

    @Column(columnDefinition = "nvarchar(255)")
    public String rooms_img;

    @Column(columnDefinition = "datetime")
    public LocalDateTime rooms_date_created;

    public int rooms_new;

    @Column(columnDefinition = "nvarchar(255)")
    public String rooms_acreage;

    @Column(columnDefinition = "nvarchar(255)")
    public String rooms_interior;

    public Rooms(String rooms_address, String rooms_title, String rooms_description, Float rooms_price, Float rooms_rating, State state, String rooms_img, LocalDateTime rooms_date_created, int rooms_new) {
        this.rooms_address = rooms_address;
        this.rooms_title = rooms_title;
        this.rooms_description = rooms_description;
        this.rooms_price = rooms_price;
        this.rooms_rating = rooms_rating;
        this.state = state;
        this.rooms_img = rooms_img;
        this.rooms_date_created = rooms_date_created;
        this.rooms_new = rooms_new;
    }



    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "roomlistimg" ,joinColumns = {@JoinColumn(referencedColumnName = "rooms_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "rooms_img_id")})
    @JsonIgnore
    private Set<RoomsImg> roomsimg;


    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "rooms",fetch = FetchType.EAGER)
    private Set<RoomsUser> roomsUsers;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

}
