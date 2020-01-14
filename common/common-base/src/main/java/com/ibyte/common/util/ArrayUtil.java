package com.ibyte.common.util;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Description: <数组计算>
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @Date: 2019-10-10
 */
public class ArrayUtil {

    /**
     * 判断数组是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 将列表的值分批次执行
     */
    public static <T> void batchConsumer(List<T> list, int size,
                                         Consumer<List<T>> consumer) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        int count = list.size();
        if (count <= size) {
            consumer.accept(list);
            return;
        }
        for (int i = 0; i < count; i += size) {
            int toIndex = Math.min(i + size, count);
            consumer.accept(list.subList(i, toIndex));
        }
    }

    /**
     * 将集合中元素输出成字符串，并用{@code separator}连接
     * @param collection
     * @param separator
     * @return
     */
    public static String join(Collection<?> collection,String separator) {
        if(isEmpty(collection)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for(Object o : collection) {
            sb.append(o.toString()).append(separator);
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
