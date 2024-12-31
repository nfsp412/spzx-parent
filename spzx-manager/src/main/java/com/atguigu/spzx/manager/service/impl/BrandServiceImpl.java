package com.atguigu.spzx.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.Brand;
import com.atguigu.spzx.manager.service.BrandService;
import com.atguigu.spzx.manager.mapper.BrandMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【brand(分类品牌)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand>
        implements BrandService {

    @Override
    public Page<Brand> findALlPage(Page<Brand> page) {
        return super.page(page);
    }

    @Override
    public void saveBrand(Brand brand) {
        super.save(brand);
    }

    @Override
    public void updateBrand(Brand brand) {
        super.updateById(brand);
    }

    @Override
    public void deleteBrand(Integer id) {
        super.removeById(id);
    }

    @Override
    public List<Brand> findALlBrand() {
        return super.list();
    }
}
