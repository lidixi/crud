package com.dyzc.survey.service.impl;

import com.dyzc.common.core.domain.AjaxResult;
import com.dyzc.common.core.lang.UUID;
import com.dyzc.survey.domain.*;
import com.dyzc.survey.mapper.KnowledgeBaseMapper;
import com.dyzc.survey.service.KnowledgeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {

    @Autowired
    private KnowledgeBaseMapper knowledgeBaseMapper;

    @Override
    public List<KnowledgeBaseModel> searchArticles(String keyword, String status) {
        return knowledgeBaseMapper.search(keyword, status);
    }

    @Override
    public AjaxResult addArticle(KnowledgeBaseModel knowledgeBaseModel) {
        try {
            knowledgeBaseModel.setId(UUID.randomUUID().toString());
            knowledgeBaseMapper.insertKnowledgeBase(knowledgeBaseModel);
            return AjaxResult.success("文章添加成功");
        } catch (Exception e){
        return AjaxResult.error("文章添加失败" + e.getMessage());
        }
    }

    @Override
    public AjaxResult updateArticle(KnowledgeBaseModel knowledgeBaseModel) {
        try {
            knowledgeBaseMapper.updateKnowledgeBase(knowledgeBaseModel);
            return AjaxResult.success("文章更新成功");
        } catch (Exception e){
            return AjaxResult.error("文章更新失败" + e.getMessage());
        }
    }

    @Override
    public AjaxResult deleteArticle(String id) {
        try {
            knowledgeBaseMapper.deleteKnowledgeBase(id);
            return AjaxResult.success("文章删除成功");
        } catch (Exception e) {
            return AjaxResult.error("文章删除失败: " + e.getMessage());
        }
    }

    @Override
    public AjaxResult publishArticle(String id) {
        try {
            knowledgeBaseMapper.updateStatusToPublished(id);
            return AjaxResult.success("草稿发布成功");
        } catch (Exception e) {
            return AjaxResult.error("草稿发布失败" + e.getMessage());
        }
    }

    @Override
    public AjaxResult viewArticle(String id) {
        try {
            List<KnowledgeBaseModel> knowledgeBaseModel = knowledgeBaseMapper.viewArticleById(id);
            return AjaxResult.success(knowledgeBaseModel);
        } catch (Exception e) {
            return AjaxResult.error("文章预览失败" + e.getMessage());
        }
    }
}