package cn.csix.project.system.mapper;

import cn.csix.project.system.domain.SysDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 */
public interface SysDictMapper extends BaseMapper<SysDict> {

	String queryDictTextByKey(@Param("code") String code, @Param("key") String key);

	String queryTableDictTextByKey(@Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("key") String key);

}
