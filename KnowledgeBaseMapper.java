package com.dyzc.survey.mapper;

import com.dyzc.survey.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KnowledgeBaseMapper {

    List<KnowledgeBaseModel> search(@Param("keyword") String keyword, @Param("status") String status);

    void insertKnowledgeBase(KnowledgeBaseModel knowledgeBaseMode);

    void updateKnowledgeBase(KnowledgeBaseModel knowledgeBaseMode);

    void deleteKnowledgeBase(@Param("id") String id);

    void updateStatusToPublished(@Param("id") String id);

    List<KnowledgeBaseModel> viewArticleById(@Param("id") String id);
}
