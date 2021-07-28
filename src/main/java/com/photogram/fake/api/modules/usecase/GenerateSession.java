package com.photogram.fake.api.modules.usecase;

import com.photogram.fake.api.modules.stereotypes.Usecase;

@Usecase
public interface GenerateSession {
    String generate(long userId);
}
