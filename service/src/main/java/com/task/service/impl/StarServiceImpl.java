package com.task.service.impl;

import com.task.dao.StarMapper;
import com.task.pojo.Star;
import com.task.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("starService")
@Transactional
public class StarServiceImpl extends BaseServiceImpl<Star> implements StarService {
    @Autowired
    private StarMapper mapper;
}
