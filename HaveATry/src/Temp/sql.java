package Temp;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 11/15/24
 * @Description:
 */
public class sql {



    public static void main (String[] args) {

        String sql = "SELECT * FROM users WHERE id = #{id} AND name = #{name} AND age = #{age}";
        Map.Entry<String, Map<Integer, String>> result = parseSqlPlaceholders(sql);
        System.out.println("Parsed SQL: " + result.getKey());
        System.out.println("Parameter Map: " + result.getValue());
    }

    /**
     * 解析 SQL 语句中的占位符 `#{paramName}`，替换为 `?` 并返回参数顺序映射。
     * @param sql 待解析的 SQL 语句
     * @return Map.Entry，其中 key 为解析后的 SQL 字符串，value 为参数映射 Map
     */
    public static Map.Entry<String, Map<Integer, String>> parseSqlPlaceholders(String sql) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, String> paramMap = new HashMap<>();
        int sort = 1;

        for (int i = 0; i < sql.length(); i++) {
            char currentChar = sql.charAt(i);
            // 检查是否是占位符的开始 `#{`
            if (currentChar == '#' && i + 1 < sql.length() && sql.charAt(i + 1) == '{') {
                int start = i + 2;
                int end = start;
                // 查找 `}` 的位置
                while (end < sql.length() && sql.charAt(end) != '}') {
                    end++;
                }
                if (end < sql.length()) {
                    // 提取参数名
                    String paramName = sql.substring(start, end);
                    // 替换为 `?`
                    sb.append('?');
                    // 更新参数顺序映射 map，key 为顺序号，value 为参数名
                    paramMap.put(sort++, paramName);
                    // 更新 i 跳过 `#{paramName}`
                    i = end;
                } else {
                    // 如果没有找到 `}`，则保留原字符
                    sb.append(currentChar);
                }
            } else {
                // 非占位符字符，直接添加到结果 SQL
                sb.append(currentChar);
            }
        }

        // 使用 Map.Entry 返回结果
        return new AbstractMap.SimpleEntry<>(sb.toString(), paramMap);
    }

}
