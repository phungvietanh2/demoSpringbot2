package com.example.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "History_User" )
public class HistoryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(columnDefinition = "Float")
    public Float totalprice;

    @Column(columnDefinition = "Float")
    public Float service;

    @Column(columnDefinition = "Float")
    public Float House;

    @Column(columnDefinition = "Float")
    public Float Electricity;

    @Column(columnDefinition = "Float")
    public Float water;

    @Column(columnDefinition = "datetime")
    public LocalDateTime rooms_date_created;

    @Column(name = "rooms_state")
    @Enumerated(EnumType.STRING)
    public State state;

    @ManyToOne
    @JoinColumn(name = "roomsUser_id")
    private RoomsUser roomsuser;

}
