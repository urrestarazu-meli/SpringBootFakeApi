package com.photogram.fake.api.modules.services.impl;

import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.services.FanService;
import com.photogram.fake.api.modules.usecase.AddFan;
import com.photogram.fake.api.modules.usecase.GetMyFans;
import com.photogram.fake.api.modules.usecase.GetsPostsFan;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultFanService implements FanService {
    @Autowired
    GetMyFans getMyFans;

    @Autowired
    private GetsPostsFan getsPostsFan;

    @Autowired
    AddFan addFan;

    public User add(long userId) {
        return addFan.add(AddFan.Model.builder()
                .userId(userId)
                .build());
    }

    @Override
    public List<User> get() {
        return getMyFans.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getPostsFan(long userId) {
        return getsPostsFan.get(GetsPostsFan.Model.builder()
                .userId(userId)
                .build());
    }
}
