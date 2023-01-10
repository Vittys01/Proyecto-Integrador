package com.digital.booking.core.port.output;

import com.digital.booking.core.domain.Booking;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Optional;

public interface CommonDaoRepository<T> {

    default T save(T t){throw new UnsupportedOperationException();}

    default Page<T> getPaginated(Integer page, Integer size){throw new UnsupportedOperationException();}

    default Optional<T> getEntityById(Long id){throw new UnsupportedOperationException();}

    default T patch(T t){throw new UnsupportedOperationException();}

    default void deleteById(Long id){throw new UnsupportedOperationException();}

    default T getByParams(Map<String, String> params){throw new UnsupportedOperationException();}
}
