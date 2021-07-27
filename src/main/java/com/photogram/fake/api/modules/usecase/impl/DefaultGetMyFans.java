package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.repositories.FanRepository;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import com.photogram.fake.api.modules.usecase.GetMyFans;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Usecase
@AllArgsConstructor
public class DefaultGetMyFans implements GetMyFans {
    @Autowired
    private FanRepository fanRepository;

    @Override
    public List<User> get() {
        return fanRepository.get();
    }
}
