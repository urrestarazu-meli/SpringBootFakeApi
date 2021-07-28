package com.photogram.fake.api.modules.services.impl;

import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.services.FanService;
import com.photogram.fake.api.modules.usecase.AddFan;
import com.photogram.fake.api.modules.usecase.GetMyFans;
import com.photogram.fake.api.modules.usecase.GetsPostsFan;
import com.photogram.fake.api.modules.usecase.ValidateSession;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultFanService implements FanService {
    @Autowired
    private GetMyFans getMyFans;

    @Autowired
    private GetsPostsFan getsPostsFan;

    @Autowired
    private AddFan addFan;

    @Autowired
    ValidateSession validateSession;

    public User add(Model model) {
        return addFan.add(AddFan.Model.builder()
                .userId(model.getUserId())
                .build());
    }

    @Override
    public List<User> get(Model model) {
        validateSession(model);

        return getMyFans.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getPostsFan(Model model) {
        validateSession(model);

        return getsPostsFan.get(GetsPostsFan.Model.builder()
                .userId(model.getUserId())
                .build());
    }

    /*
    Validates a user session

     * @param model a dan service model
     */
    private void validateSession(Model model) {
        validateSession.validate(ValidateSession.Model.builder()
                .sessionToken(model.getToken())
                .build());
    }
}
