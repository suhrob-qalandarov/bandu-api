package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.response.UserRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminUserService {

    List<UserRes> getUserResList();

    UserRes getUserRes(Long userId);
}
