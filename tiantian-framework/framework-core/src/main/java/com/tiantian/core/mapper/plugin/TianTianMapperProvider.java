package com.tiantian.core.mapper.plugin;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.scripting.xmltags.IfSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;
import org.apache.ibatis.scripting.xmltags.WhereSqlNode;

import com.github.abel533.mapper.MapperProvider;
import com.github.abel533.mapperhelper.EntityHelper;
import com.github.abel533.mapperhelper.MapperHelper;
import com.tiantian.core.mapper.threadlocal.DynamicColumnThreadLocal;
/**
 * 改写默认的MapperProvider，添加根据id批量删除方法，支持动态字段的查询
 * 
 * @author good-zhiwei 刘志伟
 * @data 2016年10月4日 下午1:59:18
 */
public class TianTianMapperProvider extends MapperProvider {

    public TianTianMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
        // TODO Auto-generated constructor stub
    }

    /**
     * 根据本地线程获取需要查询的字段
     * @param entityClass
     * @return
     */
    private String getColumn(Class<?> entityClass){
        String tableColumn;
        if(DynamicColumnThreadLocal.get()!=null){
            tableColumn=DynamicColumnThreadLocal.get();
        }
        else{
            tableColumn= EntityHelper.getSelectColumns(entityClass);
        }
        return tableColumn;
    }
    /**
     * 查询
     *
     * @param ms
     * @return
     */
    @Override
    public SqlNode select(MappedStatement ms) {
        Class<?> entityClass = getSelectReturnType(ms);
        //修改返回值类型为实体类型
        setResultType(ms, entityClass);
        List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
        //静态的sql部分:select column ... from table
        sqlNodes.add(new StaticTextSqlNode("SELECT "
                + getColumn(entityClass)
                + " FROM "
                + tableName(entityClass)));
        //将if添加到<where>
        sqlNodes.add(new WhereSqlNode(ms.getConfiguration(), getAllIfColumnNode(entityClass)));
        StringBuilder orderBy = new StringBuilder();
        for (EntityHelper.EntityColumn column : EntityHelper.getColumns(entityClass)) {
            if (column.getOrderBy() != null) {
                orderBy.append(column.getColumn()).append(" ").append(column.getOrderBy()).append(",");
            }
        }
        if (orderBy.length() > 0) {
            orderBy.insert(0, "order by");
            sqlNodes.add(new StaticTextSqlNode(orderBy.substring(0, orderBy.length() - 1)));
        }
        return new MixedSqlNode(sqlNodes);
    }

    /**
     * 根据主键进行查询
     *
     * @param ms
     */
    @Override
    public void selectByPrimaryKey(MappedStatement ms) {
        final Class<?> entityClass = getSelectReturnType(ms);
        //获取主键字段映射
        List<ParameterMapping> parameterMappings = getPrimaryKeyParameterMappings(ms);
        //开始拼sql
        String sql = new SQL() {{
            //select全部列
            SELECT(getColumn(entityClass));
            //from表
            FROM(tableName(entityClass));
            //where条件，主键字段=#{property}
            WHERE(EntityHelper.getPrimaryKeyWhere(entityClass));
        }}.toString();
        //使用静态SqlSource
        StaticSqlSource sqlSource = new StaticSqlSource(ms.getConfiguration(), sql, parameterMappings);
        //替换原有的SqlSource
        setSqlSource(ms, sqlSource);
        //将返回值修改为实体类型
        setResultType(ms, entityClass);
    }

    /**
     * 根据Example查询
     *
     * @param ms
     * @return
     */
    @Override
    public SqlNode selectByExample(MappedStatement ms) {
        Class<?> entityClass = getSelectReturnType(ms);
        //将返回值修改为实体类型
        setResultType(ms, entityClass);
        List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
        //静态的sql部分:select column ... from table
        sqlNodes.add(new StaticTextSqlNode("SELECT"));
        IfSqlNode distinctSqlNode = new IfSqlNode(new StaticTextSqlNode("DISTINCT"), "distinct");
        sqlNodes.add(distinctSqlNode);
        sqlNodes.add(new StaticTextSqlNode(EntityHelper.getSelectColumns(entityClass) + " FROM " + tableName(entityClass)));
        IfSqlNode ifNullSqlNode = new IfSqlNode(exampleWhereClause(ms.getConfiguration()), "_parameter != null");
        sqlNodes.add(ifNullSqlNode);
        IfSqlNode orderByClauseSqlNode = new IfSqlNode(new TextSqlNode("order by ${orderByClause}"), "orderByClause != null");
        sqlNodes.add(orderByClauseSqlNode);
        return new MixedSqlNode(sqlNodes);
    }
    /**
     * 通过ids批量删除
     * 
     * @param ms
     * @return
     */
    public SqlNode deleteByIDS(MappedStatement ms) {
        Class<?> entityClass = getSelectReturnType(ms);
        Set<EntityHelper.EntityColumn> entityColumns = EntityHelper.getPKColumns(entityClass);
        EntityHelper.EntityColumn entityColumn = null;
        for (EntityHelper.EntityColumn entity : entityColumns) {
            entityColumn = entity;
            break;
        }
        EntityHelper.EntityColumn column = entityColumn;
        List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
        // 开始拼sql
        BEGIN();
        // delete from table
        DELETE_FROM(tableName(entityClass));
        // 得到sql
        String sql = SQL();
        // 静态SQL部分
        sqlNodes.add(new StaticTextSqlNode(sql + " WHERE " + column.getColumn() + " IN "));
        // 构造foreach sql
        SqlNode foreach = new ForEachSqlNode(ms.getConfiguration(), new StaticTextSqlNode("#{"
                + column.getProperty() + "}"), "ids", "index", column.getProperty(), "(", ")", ",");
        sqlNodes.add(foreach);
        return new MixedSqlNode(sqlNodes);
    }
}
