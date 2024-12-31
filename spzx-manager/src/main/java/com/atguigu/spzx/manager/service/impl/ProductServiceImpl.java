package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.ProductDetailsMapper;
import com.atguigu.spzx.manager.mapper.ProductSkuMapper;
import com.atguigu.spzx.model.dto.ProductDto;
import com.atguigu.spzx.model.dto.SaveProductDto;
import com.atguigu.spzx.model.entity.ProductDetails;
import com.atguigu.spzx.model.entity.ProductSku;
import com.atguigu.spzx.model.vo.ProductVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.Product;
import com.atguigu.spzx.manager.service.ProductService;
import com.atguigu.spzx.manager.mapper.ProductMapper;
import jakarta.annotation.Resource;
import kotlin.jvm.internal.Lambda;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【product(商品)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductSkuMapper productSkuMapper;

    @Resource
    private ProductDetailsMapper productDetailsMapper;

    @Override
    public Page<ProductVo> getProductPage(Page<ProductVo> productPage, ProductDto productDto) {
        return productMapper.getProductPage(productPage, productDto);
    }

    @Transactional
    @Override
    public void saveProduct(SaveProductDto saveProductDto) {
        //保存商品到product表
        saveProductDto.setStatus(0); //设置初始值
        saveProductDto.setAuditStatus(0); //设置初始值
        Product product = new Product();
        BeanUtils.copyProperties(saveProductDto, product);
        super.save(product);

        //保存sku
        List<ProductSku> productSkuList = saveProductDto.getProductSkuList();
        for (int i = 0; i < productSkuList.size(); i++) {
            ProductSku sku = productSkuList.get(i);
            sku.setSkuCode(product.getId() + "_" + i);
            sku.setProductId(product.getId());
            sku.setSkuName(saveProductDto.getName() + sku.getSkuSpec());
            sku.setSaleNum(0);
            sku.setStatus(0);
            productSkuMapper.insert(sku);
        }


        //保存details
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId()); //保存后获取自增id
        productDetails.setImageUrls(saveProductDto.getDetailsImageUrls());
        productDetailsMapper.insert(productDetails);
    }

    @Transactional
    @Override
    public ProductVo getProductById(Integer id) {
        ProductVo productVo = productMapper.getProductById(id);

        LambdaQueryWrapper<ProductSku> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(ProductSku::getProductId, id);
        List<ProductSku> productSkus = productSkuMapper.selectList(wrapper1);

        productVo.setProductSkuList(productSkus);

        LambdaQueryWrapper<ProductDetails> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(ProductDetails::getProductId, productVo.getId());
        ProductDetails productDetails = productDetailsMapper.selectOne(wrapper2);

        productVo.setDetailsImageUrls(productDetails.getImageUrls());

        return productVo;
    }

    @Transactional
    @Override
    public void updateProductById(SaveProductDto saveProductDto) {
        Product product = new Product();
        BeanUtils.copyProperties(saveProductDto, product);
        productMapper.updateById(product);

        List<ProductSku> productSkuList = saveProductDto.getProductSkuList();
        productSkuList.forEach(productSku -> {
            productSkuMapper.updateById(productSku);
        });


        LambdaQueryWrapper<ProductDetails> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(ProductDetails::getProductId, saveProductDto.getId());
        ProductDetails productDetails = productDetailsMapper.selectOne(wrapper2);
        System.out.println(productDetails + "====================================>");
        productDetails.setImageUrls(saveProductDto.getDetailsImageUrls());
        productDetailsMapper.updateById(productDetails);
    }

    @Override
    public void deleteProductById(Integer id) {
        productMapper.deleteById(id);

        LambdaQueryWrapper<ProductSku> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductSku::getProductId, id);
        productSkuMapper.delete(wrapper);

        LambdaQueryWrapper<ProductDetails> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(ProductDetails::getProductId, id);
        productDetailsMapper.delete(wrapper1);
    }

    @Override
    public void updateProductAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);

        //1为通过
        if (auditStatus == 1) {

            product.setAuditStatus(auditStatus);
            product.setAuditMessage("审批通过");
        } else if (auditStatus == -1) {

            product.setAuditStatus(auditStatus);
            product.setAuditMessage("审批驳回");
        }
        //-1为驳回
        productMapper.updateById(product);
    }

    @Override
    public void updateProductStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);

        //1为上架
        if (status == 1) {

            product.setStatus(status);

        } else if (status == -1) {

            product.setStatus(status);

        }
        //-1为下架
        productMapper.updateById(product);
    }
}

