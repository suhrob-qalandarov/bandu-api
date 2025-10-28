package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.request.RegisterReq;
import org.exp.banduapp.models.dto.response.LoginRes;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    String registerClientAndSendVerifyCode(RegisterReq registerReq);

    LoginRes verifyClient(String phoneNumber, String otpCode);

    LoginRes loginClient(String phoneNumber, String password);
}
