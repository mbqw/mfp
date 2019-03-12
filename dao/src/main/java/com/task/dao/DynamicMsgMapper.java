package com.task.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.pojo.DynamicMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DynamicMsgMapper extends BaseMapper<DynamicMsg> {
    PageList<DynamicMsg> findStarMsgPageList(Map<String, Object> param, PageBounds pageBounds);

    @Override
    DynamicMsg getObjectById(@Param("id") Object id);

    PageList<Map> findCommentsPageList(Map<String, Object> param, PageBounds pageBounds);
}
