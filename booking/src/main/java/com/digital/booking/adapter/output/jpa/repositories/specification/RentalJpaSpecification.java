package com.digital.booking.adapter.output.jpa.repositories.specification;

import com.digital.booking.adapter.input.controller.models.input.RentalRequestFilter;
import com.digital.booking.adapter.output.jpa.entities.RentalJpaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class RentalJpaSpecification {

    public Specification<RentalJpaEntity> readByFilters(RentalRequestFilter rentalRequestFilter) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            LocalDate bookDateIn = LocalDate.now();
            LocalDate bookDateOut = LocalDate.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


            //CRITERIA TO FILTER RENTALS BY CITY
            if (rentalRequestFilter.getCity() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("city"), rentalRequestFilter.getCity())
                );
            }

            //CRITERIA TO FILTER RENTALS BY CATEGORY
            if (rentalRequestFilter.getCategory() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("category"), rentalRequestFilter.getCategory())
                );
            }


            //CRITERIA TO FILTER RENTALS BY DATE
            if (rentalRequestFilter.getCheckIn() != null || rentalRequestFilter.getCheckOut() != null) {

                //IF CHECK-IN WAS PROVIDED, READ THE DATE. ELSE, ASSUME CHECK-IN IS ONE DAY BEFORE CHECK-OUT
                if (rentalRequestFilter.getCheckIn() != null) {
                    bookDateIn = LocalDate.parse(rentalRequestFilter.getCheckIn(), formatter);
                } else {
                    bookDateIn = LocalDate.parse(rentalRequestFilter.getCheckOut(), formatter);
                    bookDateIn.minusDays(1);
                }

                //IF CHECK-OUT WAS PROVIDED, READ THE DATE. ELSE, ASSUME CHECK-OUT IS ONE DAY AFTER CHECK-IN
                if (rentalRequestFilter.getCheckOut() != null) {
                    bookDateOut = LocalDate.parse(rentalRequestFilter.getCheckOut(), formatter);
                } else {
                    bookDateOut = LocalDate.parse(rentalRequestFilter.getCheckIn(), formatter);
                    bookDateOut.plusDays(1);
                }

                //PREDICATE TO RETURN ALL THOSE RENTALS WITHOUT ANY BOOKING, THEN ARE CONSIDERED AVAILABLE
                Predicate noBooking = criteriaBuilder.isNull(root.join("bookingSet", JoinType.LEFT).<LocalDate>get("checkIn"));

                //PREDICATE TO RETURN ALL THOSE RENTALS WITH BOOKING BUT STATE = FALSE or NULL, THEN ARE CONSIDERED AVAILABLE
                Predicate bookingStatusFalse = criteriaBuilder.equal(root.join("bookingSet", JoinType.LEFT).<Boolean>get("status"), Boolean.FALSE);
                Predicate bookingStatusNull = criteriaBuilder.isNull(root.join("bookingSet", JoinType.LEFT).<Boolean>get("status"));

                Predicate bookingStatus = criteriaBuilder.or(bookingStatusFalse, bookingStatusNull);

                //PREDICATE: REGISTER_CHECKIN >= bookDateIn AND bookDateOut >= REGISTER_CHECKOUT
                Predicate regCheckInGreaterEqualBookDateIn = criteriaBuilder.greaterThanOrEqualTo(root.join("bookingSet", JoinType.LEFT).<LocalDate>get("checkIn"), bookDateIn);
                Predicate regCheckOutLessEqualBookDateOut = criteriaBuilder.lessThanOrEqualTo(root.join("bookingSet", JoinType.LEFT).<LocalDate>get("checkOut"), bookDateOut);

                Predicate datesCriteriaBetween = criteriaBuilder.and(regCheckInGreaterEqualBookDateIn, regCheckOutLessEqualBookDateOut);

                //PREDICATE: REGISTER_CHECKIN < bookDateIn
                Predicate regCheckInLessBookDateIn = criteriaBuilder.lessThan(root.join("bookingSet", JoinType.LEFT).<LocalDate>get("checkIn"), bookDateIn);

                //PREDICATE: REGISTER_CHECKOUT > bookDateOut
                Predicate regCheckOutGreaterBookDateOut = criteriaBuilder.greaterThan(root.join("bookingSet", JoinType.LEFT).<LocalDate>get("checkOut"), bookDateOut);


                //AND PREDICATE TO CONSIDER AVAILABLE ANY RENTAL BETWEEN CHECK-IN/CHECK-OUT BUT STATE = FALSE or NULL
                Predicate datesBetweenStateFalseNull = criteriaBuilder.and(datesCriteriaBetween, bookingStatus);

                //PREDICATE IS ADDED TO CONSIDER RENTALS WITH NO BOOKING AND THOSE WHO HAS BOOKING BUT STATE = FALSE, THOSE SHOULD BE THE ONES CONSIDERED AS AVAILABLE.
                predicates.add(
                        criteriaBuilder.or(datesBetweenStateFalseNull, regCheckInLessBookDateIn, regCheckOutGreaterBookDateOut, noBooking)
                );
            }


            query.distinct(true);

            String orderByField = "name";

            if (rentalRequestFilter.getCurrentUser() == null){
                query.orderBy(
                        rentalRequestFilter.isASC() ? criteriaBuilder.asc(criteriaBuilder.function("RAND", null))
                                : criteriaBuilder.desc(criteriaBuilder.function("RAND", null))
                );
            } else {
                query.orderBy(
                        rentalRequestFilter.isASC() ? criteriaBuilder.asc(root.get(orderByField))
                                : criteriaBuilder.desc(root.get(orderByField))
                );
            }



            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });
    }
}
