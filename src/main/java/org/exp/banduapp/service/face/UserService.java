package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.request.RegisterReq;
import org.exp.banduapp.models.dto.response.UserRes;
import org.exp.banduapp.models.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    UserRes convertToUserResponse(User user);

    String saveNewUser(RegisterReq registerReq);

    Optional<User> checkOtpAndGetUser(String phoneNumber, String code);

    boolean toggleUserVisibility(Long id);

    User checkPasswordAndGetUser(String phoneNumber, String password);
}
