package com.photogram.fake.api.modules.entities.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class Comment {
    long postId;
    long id;
    String name;
    String email;
    String body;
}
