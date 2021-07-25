package com.photogram.fake.api.modules.entities.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class Post {
    long userId;
    long id;
    String title;
    String body;
}
