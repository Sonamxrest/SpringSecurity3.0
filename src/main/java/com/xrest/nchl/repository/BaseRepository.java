package com.xrest.nchl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface BaseRepository<C,I> extends JpaRepository<C,I> , JpaSpecificationExecutor<C> {
}
