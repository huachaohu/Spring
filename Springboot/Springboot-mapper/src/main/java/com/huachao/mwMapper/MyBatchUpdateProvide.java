package com.huachao.mwMapper;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

public class MyBatchUpdateProvide extends MapperTemplate {

    public MyBatchUpdateProvide(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String batchUpdate(MappedStatement statement){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("<foreach collection=\"list\" item=\"record\" separator=\";\" >");

        //3.获取实体类对应的Class对象
        Class<?> entityClass = super.getEntityClass(statement);
        //4.获取实体类在数据库中对应的表名
        String tableName = super.tableName(entityClass);

        //5.生成update子句
        String updateClause = SqlHelper.updateTable(entityClass, tableName);
        sqlBuilder.append(updateClause);

        //6.获取所有字段信息
        Set<EntityColumn> entityColumns = EntityHelper.getColumns(entityClass);

        String idColumn = null;
        String idColumnHolder = null;
        sqlBuilder.append("<set>");
        //遍历字段
        for (EntityColumn entityColumn : entityColumns){
            boolean isId = entityColumn.isId();
            //7.判断当前字段是否为主键
            if(isId){
                //8.缓存主键的字段名和字段值
                idColumn = entityColumn.getColumn();
                //※返回格式如:#{record.age,jdbcType=NUMERIC,typeHandler=MyTypeHandler}
                idColumnHolder = entityColumn.getColumnHolder("record");
            }else {
                //9.使用非主键字段拼接SET子句
                String column = entityColumn.getColumn();
                String columnHolder = entityColumn.getColumnHolder("record");
                sqlBuilder.append(column).append("=").append(columnHolder).append(",");
            }
        }
        sqlBuilder.append("</set>");

        //10.使用前面缓存的主键名、主键值拼接where子句
        sqlBuilder.append("where ").append(idColumn).append("=").append(idColumnHolder);
        //forEach结束标签
        sqlBuilder.append("</foreach>");
        return sqlBuilder.toString();
    }
}
