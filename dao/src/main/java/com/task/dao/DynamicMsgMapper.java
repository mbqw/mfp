package com.task.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.pojo.DynamicMsg;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DynamicMsgMapper extends BaseMapper<DynamicMsg> {
    PageList<DynamicMsg> findStarMsgPageList(Map<String, Object> param, PageBounds pageBounds);
}
