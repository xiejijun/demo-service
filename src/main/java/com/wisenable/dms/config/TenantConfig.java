package com.wisenable.dms.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.wisenable.dms.utils.Tools;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TenantConfig {

    private static final Set<String> filter = new HashSet<>();

    static {
        filter.add("com.wisenable.dms.datasource.mappers.UserMapperEx.getUserByWeixinOpenId");
        filter.add("com.wisenable.dms.datasource.mappers.UserMapperEx.updateUserWithWeixinOpenId");
        filter.add("com.wisenable.dms.datasource.mappers.UserMapperEx.getUserListByUserNameOrLoginName");
        filter.add("com.wisenable.dms.datasource.mappers.UserMapperEx.disableUserByLimit");
        filter.add("com.wisenable.dms.datasource.mappers.RoleMapperEx.getRoleWithoutTenant");
        filter.add("com.wisenable.dms.datasource.mappers.LogMapperEx.insertLogWithUserId");
        filter.add("com.wisenable.dms.datasource.mappers.UserBusinessMapperEx.getBasicDataByKeyIdAndType");
        filter.add("com.wisenable.dms.datasource.mappers.UserBusinessMapperEx.getBasicDataByKeyIdsAndType");
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(HttpServletRequest request) {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId() {
                String token = request.getHeader("X-Access-Token");
                Long tenantId = Tools.getTenantIdByToken(token);
                if (tenantId!=0L) {
                    return new LongValue(tenantId);
                } else {
                    //超管
                    return null;
                }
            }

            @Override
            public String getTenantIdColumn() {
                return "tenant_id";
            }

            @Override
            public boolean doTableFilter(String tableName) {
                //获取开启状态
                boolean res = true;
                String token = request.getHeader("X-Access-Token");
                Long tenantId = Tools.getTenantIdByToken(token);
                if (tenantId!=0L) {
                    // 这里可以判断是否过滤表
                    res = "dms_material_property".equals(tableName) || "dms_sequence".equals(tableName)
                            || "dms_function".equals(tableName) || "dms_platform_config".equals(tableName)
                            || "dms_tenant".equals(tableName);
                }
                return res;
            }
        });

        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        paginationInterceptor.setSqlParserFilter(metaObject -> {
            MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
            // 过滤自定义查询此时无租户信息约束出现
            return filter.contains(ms.getId());
        });
        return paginationInterceptor;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.wisenable.dms.datasource.mappers*");
        return scannerConfigurer;
    }

    /**
     * 性能分析拦截器，不建议生产使用
     */
//    @Bean
//    public PerformanceInterceptor performanceInterceptor(){
//        return new PerformanceInterceptor();
//    }


}
