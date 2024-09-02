package com.wisenable.dms.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface ICommonQuery {
    /**
     * 根据id查询明细。
     *
     * @param id 资源id
     * @return 资源
     */
    Object selectOne(Long id) throws Exception;

    /**
     * 自定义查询
     *
     * @param parameterMap 查询参数
     * @return 查询结果
     */
    List<?> select(Map<String, String> parameterMap) throws Exception;

    /**
     * 查询数量
     *
     * @param parameterMap 查询参数
     * @return 查询结果
     */
    Long counts(Map<String, String> parameterMap) throws Exception;

    /**
     * 新增数据
     *
     * @param obj
     * @return
     */
    int insert(JSONObject obj, HttpServletRequest request) throws Exception;

    /**
     * 更新数据
     *
     * @param obj
     * @return
     */
    int update(JSONObject obj, HttpServletRequest request) throws Exception;

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    int delete(Long id, HttpServletRequest request) throws Exception;

    /**
     * 批量删除数据
     *
     * @param ids
     * @return
     */
    int deleteBatch(String ids, HttpServletRequest request) throws Exception;

    /**
     * 查询名称是否存在
     *
     * @param id
     * @return
     */
    int checkIsNameExist(Long id, String name) throws Exception;
}