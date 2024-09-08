package edu.zafu.user_center_backend.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * SQL 工具
 *
 * @author ColaBlack
 */
public class SqlUtils {

    /**
     * 防止排序字段 SQL 注入
     *
     * @param sortField 排序字段
     * @return true 合法，false 非法
     */
    public static boolean validSortField(String sortField) {
        if (StringUtils.isBlank(sortField)) {
            return false;
        }
        return !StringUtils.containsAny(sortField, "=", "(", ")", " ");
    }
}
