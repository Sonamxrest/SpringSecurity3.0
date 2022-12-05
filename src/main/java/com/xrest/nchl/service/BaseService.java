package com.xrest.nchl.service;

import com.xrest.nchl.repository.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface BaseService<C,I> {
     C findOne(I id);

     List<C> findAll();

     C save(C c);

     @Transactional
     @Modifying
     void deleteById(I id);
}
