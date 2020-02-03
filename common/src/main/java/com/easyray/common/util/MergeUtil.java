package com.easyray.common.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
public class MergeUtil<T> {

    public static class MergeResult<T> {
        private List<T> needAdd;
        private List<T> needDelete;

        public MergeResult() {
        }

        public MergeResult(List<T> needAdd, List<T> needDelete) {
            this.needAdd = needAdd;
            this.needDelete = needDelete;
        }

        @Override
        public String toString() {
            return "MergeResult{" +
                    "needAdd=" + needAdd +
                    ", needDelete=" + needDelete +
                    '}';
        }

        public List<T> getNeedAdd() {
            return needAdd;
        }

        public MergeResult<T> setNeedAdd(List<T> needAdd) {
            this.needAdd = needAdd;
            return this;
        }

        public List<T> getNeedDelete() {
            return needDelete;
        }

        public MergeResult<T> setNeedDelete(List<T> needDelete) {
            this.needDelete = needDelete;
            return this;
        }
    }

    public MergeResult<T> merge(List<T> oldList, List<T> newList) {
        Set<T> needAdd = new HashSet<>();
        Set<T> needDelete = new HashSet<>();

        needAdd.addAll(oldList);
        needAdd.addAll(newList);
        needAdd.removeAll(oldList);

        needDelete.addAll(oldList);
        needDelete.addAll(newList);
        needDelete.removeAll(newList);

        return new MergeResult<>(new ArrayList<>(needAdd), new ArrayList<>(needDelete));
    }
}
