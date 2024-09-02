package com.wisenable.dms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wisenable.dms.constants.BusinessConstants;
import com.wisenable.dms.datasource.entities.Organization;
import com.wisenable.dms.datasource.vo.TreeNode;
import com.wisenable.dms.service.organization.OrganizationService;
import com.wisenable.dms.service.user.UserService;
import com.wisenable.dms.utils.BaseResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping(value = "/organization")
@Api(tags = {"机构管理"})
public class OrganizationController {
    private final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Resource
    private OrganizationService organizationService;

    @Resource
    private UserService userService;

    /**
     * 根据id来查询机构信息
     */
    @GetMapping(value = "/findById")
    @ApiOperation(value = "根据id来查询机构信息")
    public BaseResponseInfo findById(@RequestParam("id") Long id, HttpServletRequest request) {
        BaseResponseInfo res = new BaseResponseInfo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<Organization> dataList = organizationService.findById(id);
            JSONObject outer = new JSONObject();
            if (null != dataList) {
                for (Organization org : dataList) {
                    outer.put("id", org.getId());
                    outer.put("orgAbr", org.getOrgAbr());
                    outer.put("parentId", org.getParentId());
                    List<Organization> dataParentList = organizationService.findByParentId(org.getParentId());
                    if(dataParentList!=null&&dataParentList.size()>0){
                        //父级机构名称显示简称
                        outer.put("orgParentName", dataParentList.get(0).getOrgAbr());
                    }
                    outer.put("orgNo", org.getOrgNo());
                    outer.put("sort", org.getSort());
                    outer.put("remark", org.getRemark());
                }
            }
            res.code = 200;
            res.data = outer;
        } catch(Exception e){
            logger.error(e.getMessage(), e);
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    /**
     * 获取机构树数据
     */
    @GetMapping(value = "/getOrganizationTree")
    @ApiOperation(value = "获取机构树数据")
    public JSONArray getOrganizationTree(@RequestParam("id") Long id) throws Exception{
       JSONArray arr=new JSONArray();
       List<TreeNode> organizationTree= organizationService.getOrganizationTree(id);
       if(organizationTree!=null&&organizationTree.size()>0){
           for(TreeNode node:organizationTree){
               String str=JSON.toJSONString(node);
               JSONObject obj=JSON.parseObject(str);
               arr.add(obj);
           }
       }
        return arr;
    }

    /**
     * 根据用户获取全部机构树
     */
    @GetMapping(value = "/getAllOrganizationTreeByUser")
    @ApiOperation(value = "根据用户获取全部机构树")
    public JSONArray getAllOrganizationTreeByUser(HttpServletRequest request) throws Exception{
        JSONArray arr = new JSONArray();
        Long userId = userService.getUserId(request);
        String roleType = userService.getRoleTypeByUserId(userId).getType();
        if(BusinessConstants.ROLE_TYPE_PUBLIC.equals(roleType)) {
            List<TreeNode> organizationTree = organizationService.getOrganizationTree(null);
            if(organizationTree!=null && organizationTree.size()>0){
                for(TreeNode node: organizationTree){
                    String str = JSON.toJSONString(node);
                    JSONObject obj = JSON.parseObject(str);
                    arr.add(obj);
                }
            }
        }
        return arr;
    }
}
