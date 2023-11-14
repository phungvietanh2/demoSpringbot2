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
@Table(name = "roomsimg" )
public class RoomsImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rooms_img_id")
    private  Long rooms_img_id;

    @Column(columnDefinition = "nvarchar(255)")
    public String rooms_img;

    @ManyToMany(mappedBy = "roomsimg",fetch = FetchType.EAGER)
    private Set<Rooms> rooms;

}
