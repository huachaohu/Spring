package com.huachao.mwMapper;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;
@RegisterMapper
public interface MwMapper<T> extends
        BaseMapper<T> ,
        ExampleMapper<T>,
        MyBatchUpdateMapper<T> {
}