package com.photogram.fake.api.modules.entities.responses;

import com.photogram.fake.api.modules.entities.domain.User;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class GetFollowersResponse {
    List<User> following;
}
