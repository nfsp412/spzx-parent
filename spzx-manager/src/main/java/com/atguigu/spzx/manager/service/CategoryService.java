package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.Category;
import com.atguigu.spzx.model.vo.CategoryVo;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【category(商品分类)】的数据库操作Service
 * @createDate 2024-12-24 22:35:04
 */
public interface CategoryService extends IService<Category> {

    List<CategoryVo> findByParentId(Long id);

    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);

    void exportTemplate(HttpServletResponse response);
}
