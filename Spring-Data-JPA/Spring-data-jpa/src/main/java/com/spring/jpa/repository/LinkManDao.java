package com.spring.jpa.repository;

import com.spring.jpa.model.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/28
 */
public interface LinkManDao extends JpaRepository<LinkMan,Long>, JpaSpecificationExecutor<LinkMan> {
}
