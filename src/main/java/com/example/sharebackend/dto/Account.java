package com.example.sharebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Account {
    String id;
    String nickname;
    String pw;
    boolean active;


}
