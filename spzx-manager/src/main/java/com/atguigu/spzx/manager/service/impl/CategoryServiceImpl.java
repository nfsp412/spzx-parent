package com.atguigu.spzx.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.manager.listener.ExcelListener;
import com.atguigu.spzx.manager.mapper.CategoryVoMapper;
import com.atguigu.spzx.model.vo.CategoryExcelVo;
import com.atguigu.spzx.model.vo.CategoryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.Category;
import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.manager.mapper.CategoryMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【category(商品分类)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {
    @Resource
    private CategoryVoMapper categoryVoMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVo> findByParentId(Long id) {

        //parent id查询所有
        List<CategoryVo> list = categoryVoMapper.selectByParentId(id);

        if (!CollectionUtil.isEmpty(list)) {
            list.forEach(item -> {
                //查询子节点count,根据item的id查询子节点parent id匹配
                Long count = categoryVoMapper.selectChildrenCountByParentId(item.getId());

                if (count == 0) {
                    //不存在children
                    item.setHasChildren(false);
                } else {
                    item.setHasChildren(true);
                }
            });
        }
        return list;

    }

    @Override
    public void exportData(HttpServletResponse response) {
        //设置响应头等等
        // 设置响应结果类型
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        // 这里URLEncoder.encode可以防止中文乱码 当然和easy excel没有关系
        String fileName = null;
        fileName = URLEncoder.encode("分类数据后端", StandardCharsets.UTF_8);
        //设置响应头
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        //response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");


        //查询数据库数据,返回list
        List<Category> list = super.list();


        //easy excel写出
        try {
            EasyExcel.write(response.getOutputStream(), Category.class)
                    .sheet("分类数据")
                    .doWrite(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exportTemplate(HttpServletResponse response) {
        //设置响应头等等
        // 设置响应结果类型
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        // 这里URLEncoder.encode可以防止中文乱码 当然和easy excel没有关系
        String fileName = null;
        fileName = URLEncoder.encode("exportTemplate", StandardCharsets.UTF_8);
        //设置响应头
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        //response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");


        //查询数据库数据,返回list
        List<Category> list = new ArrayList<>();

        //easy excel写出
        try {
            EasyExcel.write(response.getOutputStream(), Category.class)
                    .sheet("分类数据")
                    .doWrite(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void importData(MultipartFile file) {
        //easy excel读取
        ExcelListener<Category> excelListener = new ExcelListener<>(categoryMapper);
        try {
            EasyExcel.read(file.getInputStream(), Category.class, excelListener)
                    .sheet()
                    .doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //写入数据库操作在listener中进行
    }
}
