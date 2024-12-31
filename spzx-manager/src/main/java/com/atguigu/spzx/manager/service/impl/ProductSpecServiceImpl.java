package com.atguigu.spzx.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.ProductSpec;
import com.atguigu.spzx.manager.service.ProductSpecService;
import com.atguigu.spzx.manager.mapper.ProductSpecMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【product_spec(商品规格)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class ProductSpecServiceImpl extends ServiceImpl<ProductSpecMapper, ProductSpec>
        implements ProductSpecService {

    @Override
    public Page<ProductSpec> selectAllProductSpec(Page<ProductSpec> page) {
        return super.page(page);
    }

    @Override
    public List<ProductSpec> findAllProductSpec() {
        return super.list();
    }

    @Override
    public List<ProductSpec> findProductSkuByBrandId(Integer id) {
        List<ProductSpec> list = super.list();
        ArrayList<ProductSpec> result = new ArrayList<>();
        result.add(list.get(0));
        result.add(list.get(1));
        return result;
    }
}

