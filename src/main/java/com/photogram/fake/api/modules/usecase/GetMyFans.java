package com.photogram.fake.api.modules.usecase;

import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import java.util.List;

@Usecase
public interface GetMyFans {
    List<User> get();
}
