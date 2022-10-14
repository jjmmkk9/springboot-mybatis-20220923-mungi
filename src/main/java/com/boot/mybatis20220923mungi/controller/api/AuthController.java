package com.boot.mybatis20220923mungi.controller.api;


import com.boot.mybatis20220923mungi.domain.User;
import com.boot.mybatis20220923mungi.dto.CMRespDto;
import com.boot.mybatis20220923mungi.dto.SignupReqDto;
import com.boot.mybatis20220923mungi.dto.SignupRespDto;
import com.boot.mybatis20220923mungi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    // /api/auth/signup
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(SignupReqDto signupReqDto){
        log.info("{}", signupReqDto);

        User user = signupReqDto.toEntity();
        /*

         */
        log.info("마이바티스 가기 전 Entity: {}", user);
        int result = userRepository.save(user);
        log.info("마이바티스 다녀온 후 Entity: {}", user);

        SignupRespDto signupRespDto = user.toDto();

        if(result == 0){
            return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "회원가입 실패", signupRespDto));
        }
        return ResponseEntity.ok( new CMRespDto<>(1, "회원가입완료",signupRespDto));

    }
}
