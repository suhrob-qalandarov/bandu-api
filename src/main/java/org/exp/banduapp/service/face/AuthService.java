package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.request.RegisterReq;
import org.exp.banduapp.models.dto.response.LoginRes;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    String saveDataAndSendVerifyCode(RegisterReq registerReq);

    LoginRes verifyAndSendUserData(String phoneNumber, String otpCode);
}
