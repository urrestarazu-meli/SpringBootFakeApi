package com.photogram.fake.api.modules.entities.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Comment {
    long postId;
    long id;
    String name;
    String email;
    String body;
}
