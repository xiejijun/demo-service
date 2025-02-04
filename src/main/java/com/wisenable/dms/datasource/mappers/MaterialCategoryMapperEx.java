package com.wisenable.dms.datasource.mappers;

import com.wisenable.dms.datasource.entities.MaterialCategory;
import com.wisenable.dms.datasource.vo.TreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface MaterialCategoryMapperEx {
    List<MaterialCategory> selectByConditionMaterialCategory(
            @Param("name") String name,
            @Param("parentId") Integer parentId,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    Long countsByMaterialCategory(
            @Param("name") String name,
            @Param("parentId") Integer parentId);

    List<TreeNode> getNodeTree(@Param("currentId")Long currentId);
    List<TreeNode> getNextNodeTree(Map<String, Object> parameterMap);

    int addMaterialCategory(MaterialCategory mc);

    int batchDeleteMaterialCategoryByIds(@Param("updateTime") Date updateTime, @Param("updater") Long updater, @Param("ids") String ids[]);

    int editMaterialCategory(MaterialCategory mc);

    List<MaterialCategory> getMaterialCategoryBySerialNo(@Param("serialNo") String serialNo, @Param("id") Long id);

    List<MaterialCategory> getMaterialCategoryListByCategoryIds(@Param("parentIds") String[] categoryIds);

    List<MaterialCategory> getListByParentId(@Param("parentId") Long parentId);
}
