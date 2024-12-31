package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.vo.CategoryVo;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface CategoryVoMapper {

    @Select("select * from db_spzx.category where is_deleted = 0 and parent_id = #{id} order by id ")
    List<CategoryVo> selectByParentId(Long id);

    @Select("select count(*) from db_spzx.category where is_deleted = 0 and parent_id = #{id}")
    Long selectChildrenCountByParentId(Long id);
}
