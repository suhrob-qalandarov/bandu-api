package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.response.UserRes;
import org.exp.banduapp.models.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserRes getUserDataFromToken(User user);
}
