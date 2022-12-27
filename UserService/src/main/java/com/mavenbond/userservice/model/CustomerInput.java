package com.mavenbond.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomerInput {
    String id;
    String email;
    String password;
}
