package com.len.base;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

/**
 * @author zhuxiaomeng
 * @date 2018/10/12
 * @email 154040976@qq.com
 * 重写 SpecialProvider 使得批量方法支持主键自增和自定义自增  (仅测试了mysql)
 */
public class MySpecialProvider extends MapperTemplate {
    public MySpecialProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }


    public String insertList(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        //开始拼sql
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, tableName(entityClass)));
        boolean keyIsAuto = keyIsAuto(entityClass);
        sql.append(SqlHelper.insertColumns(entityClass, keyIsAuto, false, false));
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);

        for (EntityColumn column : columnList) {
            if (column.isId() && column.isInsertable()) {
                if (!keyIsAuto) {
                    sql.append(column.getColumnHolder("record")).append(",");
                }
            } else if (column.isInsertable()) {
                sql.append(column.getColumnHolder("record")).append(",");
            }
        }
        sql.append("</trim>");
        sql.append("</foreach>");
        return sql.toString();
    }

    /**
     * 调用insertList insertUseGeneratedKeys
     * 判断是否为 自增主键 当@GeneratedValue(generator = "JDBC") 为主键自增 删除此注解为自定义
     *
     * @param entityClass 当前实体
     * @return 是否为自增主键 true 是 false 否
     */
    private boolean keyIsAuto(Class<?> entityClass) {
        EntityTable entityTable = EntityHelper.getEntityTable(entityClass);
        entityTable.getEntityClassColumns();
        Set<EntityColumn> entityClassPKColumns = entityTable.getEntityClassPKColumns();
        for (EntityColumn entityColumn : entityClassPKColumns) {
            String generator = entityColumn.getGenerator();
            if ("JDBC".equals(generator)) {
                return true;
            }
        }
        return false;
    }

    public String insertUseGeneratedKeys(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        //开始拼sql
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, tableName(entityClass)));
        boolean keyIsAuto = keyIsAuto(entityClass);
        sql.append(SqlHelper.insertColumns(entityClass, keyIsAuto, false, false));
        sql.append(SqlHelper.insertValuesColumns(entityClass, keyIsAuto, false, false));
        return sql.toString();
    }
}
