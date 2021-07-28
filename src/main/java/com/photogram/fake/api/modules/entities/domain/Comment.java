package com.photogram.fake.api.modules.entities.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class Comment {
    long postId;
    long id;
    String name;
    String email;
    String body;
    String token;
}
