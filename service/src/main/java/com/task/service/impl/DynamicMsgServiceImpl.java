package com.task.service.impl;

import com.task.dao.DynamicMsgMapper;
import com.task.pojo.DynamicMsg;
import com.task.service.DynamicMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("dynamicMsgService")
@Transactional
public class DynamicMsgServiceImpl extends BaseServiceImpl<DynamicMsg> implements DynamicMsgService {
    @Autowired
    private DynamicMsgMapper mapper;
}
