package com.dyzc.web.controller.follow.survey;

import com.dyzc.common.core.domain.AjaxResult;
import com.dyzc.common.core.page.TableDataInfo;
import com.dyzc.common.exception.user.UserNotFundException;
import com.dyzc.framework.util.ShiroUtils;
import com.dyzc.common.core.controller.BaseController;
import com.dyzc.survey.domain.KnowledgeBaseModel;
import com.dyzc.survey.service.KnowledgeBaseService;
import com.dyzc.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping("/articles")
public class KnowledgeBaseController extends BaseController {

    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    //  分页搜索，根据状态（不填为全部，等于1为已发布，等于0为草稿）在标题、摘要、创建人中模糊搜索
    @PostMapping("/search")
    public TableDataInfo searchArticles(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                        @RequestParam(value = "status", required = false, defaultValue = "") String status,
                                        KnowledgeBaseModel knowledgeBaseModel) {
        if ("".equals(status)) {
            status = null;
        }
        startPage();
        List<KnowledgeBaseModel> list = knowledgeBaseService.searchArticles(keyword, status);
        return getDataTable(list);
    }

    //  添加文章
    @PostMapping("/add")
    public AjaxResult addArticle(@RequestBody KnowledgeBaseModel knowledgeBase) {
        SysUser user = ShiroUtils.getSysUser();
        if (user == null) {
            throw new UserNotFundException("登录失效,请重新登录");
        }
        return knowledgeBaseService.addArticle(knowledgeBase);
    }

    //  更新文章
    @PostMapping("/update")
    public AjaxResult updateArticle(@RequestBody KnowledgeBaseModel knowledgeBase) {
        SysUser user = ShiroUtils.getSysUser();
        if (user == null) {
            throw new UserNotFundException("登录失效,请重新登录");
        }
        return knowledgeBaseService.updateArticle(knowledgeBase);
    }

    //  删除文章
    @PostMapping("/delete")
    public AjaxResult deleteArticle(@RequestParam("id") String id) {
        SysUser user = ShiroUtils.getSysUser();
        if (user == null) {
            throw new UserNotFundException("登录失效,请重新登录");
        }
        return knowledgeBaseService.deleteArticle(id);
    }


    //  发布草稿
    @PostMapping("/publish")
    public AjaxResult publishArticle(@RequestParam("id") String id) {
        SysUser user = ShiroUtils.getSysUser();
        if (user == null) {
            throw new UserNotFundException("登录失效,请重新登录");
        }
        return knowledgeBaseService.publishArticle(id);
    }

    //  预览文章
    @PostMapping("/view")
    public AjaxResult viewArticle(@RequestParam("id") String id) {
        SysUser user = ShiroUtils.getSysUser();
        if (user == null) {
            throw new UserNotFundException("登录失效,请重新登录");
        }
        return knowledgeBaseService.viewArticle(id);
    }

    // 导出并下载Word
    @PostMapping("/export")
    public void exportArticleById(@RequestParam("id") String id, HttpServletResponse response) {
        XWPFDocument document = null;
        try {
            document = knowledgeBaseService.generateWordDocument(id);
            if (document == null) {
                throw new RuntimeException("未找到指定id的文章");
            }

            String fileName = id + ".docx";
            String encodedFileName = URLEncoder.encode(fileName, "UTF-8");

            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(encodedFileName.getBytes("UTF-8"), "ISO-8859-1"));

            try (OutputStream os = response.getOutputStream()) {
                document.write(os);
            }
        } catch (IOException e) {
            throw new RuntimeException("导出文章时发生IO错误: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("导出文章时发生错误: " + e.getMessage(), e);
        } finally {
            // 确保 XWPFDocument 也被关闭
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    logger.error("关闭文档时发生错误: {}", e.getMessage(), e);
                }
            }
        }
    }
}
