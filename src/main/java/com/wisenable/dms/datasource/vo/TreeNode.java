package com.wisenable.dms.datasource.vo;

import lombok.Data;

import java.util.List;

@Data
public class TreeNode {
    /**
     * id主键
     * */
     private Long id;
     private Long key;
     private Long value;
     /**
      * title显示的文本
      * */
    private String title;
    /**
     *state节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
     * */
    private String state="open";
    /**
     *iconCls 节点图标id
     * */
    private String iconCls;
    /**
     * checked 是否被选中
     * */
    private boolean checked;
    /**
     *attributes 自定义属性
     * */
    private String attributes;
    /**
     * children 子节点
     * */
    private List<TreeNode> children;
}
