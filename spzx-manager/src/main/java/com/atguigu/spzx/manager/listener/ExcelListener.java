package com.atguigu.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.model.entity.Category;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 不能交给Spring管理,即不能添加@Component注解,原因见官网,Spring默认单例对象,但是easy excel并发读取,无法区分是哪个文件
 * 这里演示在listener中直接写入数据库
 *
 * @param <T>
 */
@Slf4j
public class ExcelListener<T> implements ReadListener<T> {
    //缓存相关
    private static final int BATCH_COUNT = 100;
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    //数据库相关
    private CategoryMapper categoryMapper;

    public ExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * 从第二行开始读取,封装到 T
     *
     * @param t
     * @param analysisContext
     */
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        //存入集合
        cachedDataList.add(t);
        //存入数据库
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            //清理
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有操作完成后的方法
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //如果剩余条数不满足100,则会执行到这里
        saveData();
    }

    /**
     * 调用Mapper执行保存
     */
    private void saveData() {
        categoryMapper.batchInsert((List<Category>) cachedDataList);
    }
}
