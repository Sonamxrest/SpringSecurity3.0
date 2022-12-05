package com.xrest.nchl.serviceImpl;

import com.xrest.nchl.repository.BaseRepository;
import com.xrest.nchl.service.BaseService;

import java.util.List;

public class BaseServiceImpl<C,I> implements BaseService<C, I> {

    private final BaseRepository<C,I> baseRepository;

    public BaseServiceImpl(BaseRepository<C,I> baseRepository) {
        this.baseRepository = baseRepository;
    }


    @Override
    public C findOne(I id) {
        return baseRepository.findById(id).get();
    }

    @Override
    public List<C> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public C save(C c) {
        return baseRepository.save(c);
    }

    @Override
    public void deleteById(I id) {
    baseRepository.deleteById(id);
    }
}
