package com.zhloong.blog.common.util;

import java.util.Iterator;
import java.util.List;

/**
 * @author zhloong
 * @date 2021/11/17 17:58
 */
public abstract class IteratorUtil<T> implements Iterator<T> {
    private int page = 1;
    private int pageSize = 1000;

    private boolean hasMore = true;
    private List<T> list;

    @Override
    public boolean hasNext() {
        // 当前的数据已经加载完毕，尝试加载下一批
        if (!hasMore) {
            return false;
        }

        list = load(page, pageSize);
        if (list == null || list.isEmpty()) {
            // 没有加载到数据，结束
            return false;
        }

        page++;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T next() {
        return (T) list;
    }

    /**
     * 循环获取数据
     *
     * @param page
     * @param pageSize
     * @return
     */
    public abstract List<T> load(int page, int pageSize);

    /**
     * 默认pageSize为3000 可以通过该方法自定义pageSize
     *
     * @param pageSize
     * @return
     */
    public IteratorUtil<T> initPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
