package com.task.service.impl;

/*import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.project.platform.dao.IBaseMapper;
import com.project.platform.service.IBaseService;*/
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.dao.BaseMapper;
import com.task.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseMapper<T> mapper;

    @Override
    public PageList<T> findByObjectPageList(Map<String, Object> param, PageBounds pageBounds) {
        return mapper.findByObjectPageList(param, pageBounds);
    }

    @Override
    public void updateObject(T object) {
        mapper.updateObject(object);
    }

    @Override
    public void batchAddObject(List<T> listObject) {
        mapper.batchAddObject(listObject);
    }

    @Override
    public void batchDeleteObject(List<Object> listId) {
        mapper.batchDeleteObject(listId);
    }

    @Override
    public boolean objectIsExist(T object) {
        List<T> list = mapper.getObjectList(object);
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<T> getObjectList(T object) {
        return mapper.getObjectList(object);
    }

    @Override
    public void deleteObjectById(Object id) {
        mapper.deleteObjectById(id);
    }

    @Override
    public void addObject(T object) {
        mapper.addObject(object);
    }

    @Override
    public T getObjectById(Object id) {
        return mapper.getObjectById(id);
    }
}
