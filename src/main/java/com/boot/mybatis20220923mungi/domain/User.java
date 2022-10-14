package com.boot.mybatis20220923mungi.domain;

import com.boot.mybatis20220923mungi.dto.SignupRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int user_code;

    private String user_id;
    private String user_password;
    private String user_name;
    private String user_email;


    /*
    반대로 untity에서 respDto로 간다.
     */
    public SignupRespDto toDto(){
        return SignupRespDto.builder()
                .userCode(user_code)
                .userId(user_id)
                .userPassword(user_password)
                .userName(user_name)
                .userEmail(user_email)
                .build();
    }
}
