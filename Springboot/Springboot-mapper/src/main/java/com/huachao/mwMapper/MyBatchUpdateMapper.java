package com.huachao.mwMapper;

import org.apache.ibatis.annotations.UpdateProvider;
import java.util.List;

public interface MyBatchUpdateMapper<T> {
    @UpdateProvider(
            type = MyBatchUpdateProvide.class,
            method = "dynamicSQL"
    )
    void batchUpdate(List<T> list);
}
