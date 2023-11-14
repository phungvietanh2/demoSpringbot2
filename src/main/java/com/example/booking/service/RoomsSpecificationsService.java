package com.example.booking.service;

import com.example.booking.entity.Rooms;
import com.example.booking.repository.RoomsRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class RoomsSpecificationsService {

    @Autowired
    private RoomsRepository roomsRepository;

//    public static Specification<Rooms> findName(final String name) {
//        return new Specification<Rooms>() {
//            @Override
//            public Predicate toPredicate(Root<Rooms> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                return cb.equal(root.get("rooms_title"),  name );
//            }
//        };
//    }

    public static Specification<Rooms> findName(final String name) {
        return new Specification<Rooms>() {
            @Override
            public Predicate toPredicate(Root<Rooms> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                return cb.like(root.get("rooms_title"), "%" + name + "%");
            }
        };
    }
    public static Specification<Rooms> findPrice(final Float minPrice, final Float maxPrice) {
        return new Specification<Rooms>() {
            @Override
            public Predicate toPredicate(Root<Rooms> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.orderBy(cb.desc(root.get("rooms_id")));
                return cb.between(root.get("rooms_price"), minPrice, maxPrice);
            }
        };
    }

    public Page<Rooms> searchRooms(String name, Float minPrice , Float maxPrice, int page , int size) {
        Specification<Rooms> spec = Specification.where(null);

        Pageable pageable = PageRequest.of(page, size);

        if (name != null) {
            spec = spec.and(RoomsSpecificationsService.findName(name));
        }

        if (minPrice != null && maxPrice != null) {
            spec = spec.and(RoomsSpecificationsService.findPrice(minPrice, maxPrice));
        }


        return roomsRepository.findAll(spec,pageable);
    }


}
