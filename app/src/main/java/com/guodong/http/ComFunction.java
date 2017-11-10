package com.guodong.http;


import com.guodong.app.AppConfig;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Description:
 * Created by LSQ108 on 2017/10/28.
 */

public class ComFunction<T> implements Function<BaseEntity<T>, T> {
    @Override
    public T apply(@NonNull BaseEntity<T> tBaseEntity) throws Exception {
        int code = tBaseEntity.getCode();
        if (code != AppConfig.SUCCESS_CODE) {
            throw new ApiException(tBaseEntity.getMessage());
        }
        return tBaseEntity.getData();
    }
}
