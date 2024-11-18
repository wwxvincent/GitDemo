import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 11/5/24
 * @Description:
 */
public class setTRy {

    public static void main(String[] args) {
        String selectParam = "name, id, depId";
        String selectFromTable = "depId, name, id, age";

// 将 selectFromTable 按逗号拆分成 List
        List<String> selectFromTableList = Arrays.asList(selectFromTable.split(","));

// 将 selectParam 按逗号拆分成 Set，去除空格
        Set<String> selectParamSet = Arrays.stream(selectParam.split(","))
                .map(String::trim)
                .collect(Collectors.toSet());

// 保留 selectParam 中的元素，且这些元素也出现在 selectFromTableList 中
        List<String> resultList = Arrays.stream(selectParam.split(",")) // 基于 selectParam 的顺序
                .map(String::trim) // 去除空格
                .filter(selectFromTableList::contains) // 仅保留在 selectFromTableList 中的元素
                .collect(Collectors.toList());

        // 输出结果
        System.out.println("结果列表：" + resultList);


    }
}
