package com.dyzc.survey.service;

import com.dyzc.common.core.domain.AjaxResult;
import com.dyzc.survey.domain.KnowledgeBaseModel;

import java.util.List;

public interface KnowledgeBaseService {

    /**
     * 搜索文章
     *
     * @param keyword 搜索关键字
     * @param status  文章状态（可选），如果为 null 则不根据状态筛选
     * @return 符合条件的文章列表
     */
    List<KnowledgeBaseModel> searchArticles(String keyword, String status);

    /**
     * 添加文章
     *
     * @param knowledgeBase 需要添加的文章对象
     * @return 操作结果，包含成功或失败的信息
     */
    AjaxResult addArticle(KnowledgeBaseModel knowledgeBase);

    /**
     * 更新文章
     *
     * @param knowledgeBase 需要更新的文章对象
     * @return 操作结果，包含成功或失败的信息
     */
    AjaxResult updateArticle(KnowledgeBaseModel knowledgeBase);

    /**
     * 删除文章
     *
     * @param id 需要删除的文章的 ID
     * @return 操作结果，包含成功或失败的信息
     */
    AjaxResult deleteArticle(String id);

    /**
     * 发布文章
     *
     * @param id 需要发布的文章的 ID
     * @return 操作结果，包含成功或失败的信息
     */
    AjaxResult publishArticle(String id);

    /**
     * 预览文章
     *
     * @param id 需要预览的文章的 ID
     * @return 操作结果，包含成功或失败的信息
     */
    AjaxResult viewArticle(String id);
}
