package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.response.BookingRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminBookingService {

    List<BookingRes> getBookingsResList();

    BookingRes getBookingRes(Long bookingId);
}
