package com.photogram.fake.api.modules.entities.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class User {
    Long id;
    String name;
    String username;
    String email;
}
