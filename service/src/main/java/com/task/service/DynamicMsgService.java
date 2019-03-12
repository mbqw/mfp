package com.task.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.task.pojo.DynamicMsg;

import java.util.Map;

public interface DynamicMsgService extends BaseService<DynamicMsg> {
    /**
     * 查询已收藏的信息
     * @param param
     * @param pageBounds
     * @return
     */
    PageList<DynamicMsg> findStarMsgPageList(Map<String, Object> param, PageBounds pageBounds);

    /**
     * 查询评论分页信息
     * @param param
     * @param pageBounds
     * @return
     */
    PageList<Map> findCommentsPageList(Map<String, Object> param, PageBounds pageBounds);


}
