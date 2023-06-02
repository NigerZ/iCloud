package com.ohh.icloud.vo;

import lombok.Data;

@Data
public class RespUserVo {
    private Long id;
    private String username;
    private String nickname;
    private String phone;
    private String token;
}
