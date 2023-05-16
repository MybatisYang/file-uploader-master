package cn.csix.framework.web.controller;

import cn.csix.common.constant.HttpStatus;
import cn.csix.common.utils.DateUtils;
import cn.csix.common.utils.StringUtils;
import cn.csix.common.utils.sql.SqlUtil;
import cn.csix.framework.aspectj.lang.annotation.TranslateAnnotation;
import cn.csix.framework.aspectj.lang.enums.TranseTypeEnum;
import cn.csix.framework.web.domain.AjaxResult;
import cn.csix.framework.web.page.PageDomain;
import cn.csix.framework.web.page.TableDataInfo;
import cn.csix.framework.web.page.TableSupport;
import cn.csix.project.system.domain.SysDictData;
import cn.csix.project.system.service.ISysDictDataService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * web层通用数据处理
 * 
 *
 */
public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    private ISysDictDataService sysDictDataCommonService;
//    @Autowired
//    private ISysDeptService sysDeptCommonService;

    /**
     * 描述：将翻译后的值 赋给对应字段
     * 备注：
     * 日期： 10:07 2020/4/26
     * 作者：
     *
     * @param obj   原始实体
     * @param filed 待翻译字段
     * @param value 翻译后的值
     * @return java.lang.Object
     **/
    private static Object taransFilds(Object obj, String filed, String value) {
        try {
            Class<?> c = obj.getClass();
            Field field = c.getDeclaredField(filed);
            //获取字段
            field.setAccessible(true);
            field.set(obj, value);
            //为字段赋值
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }
    /**
     * 响应请求分页数据
     */
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应请求分页数据(字典注解使用)
     */
    protected TableDataInfo getDataTable(List<?> list, Class tClass)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setTotal(new PageInfo(list).getTotal());
        list= translateBeanList(list, tClass);
        rspData.setRows(list);
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 描述：  单实体翻译工具类
     * 备注：
     * 日期： 10:08 2020/4/26
     *
     * @param obj
     * @return java.lang.Object
     **/
    private Object valid(Object obj, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            TranslateAnnotation translateAnnotation = field.getAnnotation(TranslateAnnotation.class);//获取属性上的@Test注解
            if (translateAnnotation != null) {
                field.setAccessible(true);//设置属性可访问
                String reslut = null;
                //原始值
                try {
                    String objStr =  field.get(obj) + "";
                    TranseTypeEnum transeTypeEnum = translateAnnotation.distType();
                    switch (transeTypeEnum) {
                        case DIST:
                            reslut = getDistMapByType(translateAnnotation.distCode(), objStr);
                            break;

//                        case DEPT:
//                            reslut = getDeptByType(objStr);
//                            break;

                        case DATE:

                            reslut = DateUtils.parseDateToStr(translateAnnotation.distCode(), (Date) field.get(obj));

                            break;
                        default:
                            reslut = "";
                            break;
                    }
                } catch (Exception e) {
                    reslut = "";
                    e.printStackTrace();
                }
                obj = taransFilds(obj, translateAnnotation.filed(), reslut);

            }
        }
        return obj;
    }


    /**
     * 描述：获取 数据字典
     * 备注：
     * 日期： 11:43 2020/1/8
     * 作者： zrd
     *
     * @param type
     * @return java.util.Map<java.lang.String, java.lang.String>
     **/
    public String getDistMapByType(String type,String keyValue) {
        String result=null;

        List<SysDictData>  sysDictDataList=   sysDictDataCommonService.selectDictDataByType(type);
        if (sysDictDataList != null && sysDictDataList.size() > 0) {
            for (SysDictData sysDictData : sysDictDataList) {
                if(sysDictData.getDictValue().equals(keyValue)){
                    result=sysDictData.getDictLabel();
                    break;
                }
            }

        }


        return result;
    }

//    /**
//     * 描述：获取 部门翻译
//     * 备注：
//     * 日期： 11:43 2020/1/8
//     * 作者： zrd
//     *
//     * @return java.util.Map<java.lang.String, java.lang.String>
//     **/
//    public String getDeptByType(String keyValue) {
//        String result=null;
//
//        List<SysDept>  list=   sysDeptCommonService.selectAllDeptList();
//        if (CollUtil.isNotEmpty(list)) {
//            for (SysDept sysDept : list) {
//                if(sysDept.getDeptId().toString().equals(keyValue)){
//                    result=sysDept.getDeptName();
//                    break;
//                }
//            }
//
//        }
//
//
//        return result;
//    }

    //非分页集合返回值转换
    public <T> List translateBeanList(List<T> list, Class tClass) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        }
        List<T> rsList = new ArrayList();
        for (T t : list
        ) {
            rsList.add((T) valid(t, tClass));
        }
        return rsList;
    }

    //非分页返回值转换
    public <T> T translateBean(T t, Class tClass) {
        return (T) valid(t, tClass);
    }

    protected Page getPageInstance()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            return new Page<>(pageNum, pageSize);
        }
        return null;
    }
}
