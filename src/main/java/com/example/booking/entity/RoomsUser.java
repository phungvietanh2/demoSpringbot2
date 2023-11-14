package com.example.booking.entity;

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
@Table(name = "rooms_user")
public class RoomsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(columnDefinition = "Float")
    public Float housedeposit;

    @Column(columnDefinition = "Float")
    public Float service;


    @Column(columnDefinition = "datetime")
    public LocalDateTime rooms_date_created;

    @Column(columnDefinition = "datetime")
    public LocalDateTime rooms_date_update;

    @Column(columnDefinition = "nvarchar(255)")
    public String rooms_users_code;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    public State state;

    @ManyToOne
    @JoinColumn(name = "rooms_id")
    private Rooms rooms;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;


    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "roomsuser")
    private Set<HistoryUser> historyUsers;


}
