package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.request.BookingReq;
import org.exp.banduapp.models.dto.response.BookingRes;
import org.exp.banduapp.models.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    List<BookingRes> getUserBookingsRes(User user);

    BookingRes newBooking(BookingReq bookingReq, User user);
}
