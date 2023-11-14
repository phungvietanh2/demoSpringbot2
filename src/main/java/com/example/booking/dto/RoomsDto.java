    package com.example.booking.dto;

    import com.example.booking.entity.Rooms;
    import com.example.booking.entity.State;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.time.LocalDateTime;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class RoomsDto {
        private  Long rooms_id;
        public String rooms_address;
        public String rooms_title;
        public String rooms_description;
        public Float rooms_price;
        public Float rooms_rating;
        public String rooms_img;
        public int rooms_new;
        public String  rooms_users_code;
        public State satus;
        public LocalDateTime userrom_date_create;
        public Float  service;
        public Float housedeposit;
        public Long roomuserid;

        public RoomsDto(Rooms rooms) {
            this.rooms_id = rooms.getRooms_id();
            this.rooms_address = rooms.getRooms_address();
            this.rooms_title = rooms.getRooms_title();
            this.rooms_description = rooms.getRooms_description();
            this.rooms_price = rooms.getRooms_price();
            this.rooms_rating = rooms.getRooms_rating();
            this.rooms_img = rooms.getRooms_img();
            this.rooms_new = rooms.getRooms_new();

            if (rooms.getRoomsUsers() != null && !rooms.getRoomsUsers().isEmpty()) {
                this.housedeposit = rooms.getRoomsUsers().iterator().next().getHousedeposit();
                this.satus = rooms.getRoomsUsers().iterator().next().state;
                this.service = rooms.getRoomsUsers().iterator().next().getService();
                this.userrom_date_create = rooms.getRoomsUsers().iterator().next().getRooms_date_created();
                this.roomuserid = rooms.getRoomsUsers().iterator().next().getId();

            }
        }
    }
