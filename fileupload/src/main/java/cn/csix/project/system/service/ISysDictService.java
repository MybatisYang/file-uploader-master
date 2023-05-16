package cn.csix.project.system.service;

import cn.csix.project.system.domain.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 */
public interface ISysDictService extends IService<SysDict> {

    public String queryDictTextByKey(String code, String key);

    String queryTableDictTextByKey(String table, String text, String code, String key);
}
