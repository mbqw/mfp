package com.task.service;

/*import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;*/

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import java.util.List;
import java.util.Map;

/**
 * @description:服务层的基础类，所有service都继承它
 */
public interface BaseService<T> {
    /**
     * 根据主键得到对象
     *
     * @param id
     * @return
     */
    T getObjectById(Object id);

    /**
     * 添加对象
     *
     * @param object
     */
    void addObject(T object);

    /**
     * 根据一个或多个属性得到对象列表
     *
     * @param object
     * @return
     */
    List<T> getObjectList(T object);

    /**
     * 根据主键id删除对象
     *
     * @param id
     */
    void deleteObjectById(Object id);

    /**
     * 根据一个或多个属性得到分页对象
     *
     * @param param
     * @param pageBounds
     * @return
     */
    PageList<T> findByObjectPageList(Map<String, Object> param, PageBounds pageBounds);

    /**
     * 对象是否存在
     *
     * @param object
     * @return
     */
    boolean objectIsExist(T object);

    /**
     * 更新对象
     *
     * @param object
     */
    void updateObject(T object);

    /**
     * 批量插入对象
     *
     * @param listObject
     */
    void batchAddObject(List<T> listObject);

    /**
     * 批量删除对象,多个主键ID
     *
     * @param listId
     */
    void batchDeleteObject(List<Object> listId);
}


