package xyz.iotcode.iadmin.common.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 树工厂类，用于生成树
 * @author xieshuang
 * @date 2019-08-08 10:43
 */
public class TreeFactory<T extends Tree>{

    /**
     * 优化方案
     * 1.按照pid进行分组
     * @param treeNodes
     * @return
     */

    public Collection<T> createTree(Collection<T> treeNodes){
        Map<Number, List<T>> collect = treeNodes.stream().collect(Collectors.groupingBy(T::getPid));
        Collection<T> treeNodeList = new ArrayList<>();
        List<T> list;
        List<T> list1 = collect.get(0);
        List<T> list2 = collect.get(0L);
        if (list1==null){
            list = list2;
        }else {
            list = list1;
        }
        if (list!=null){
            for (T t : list) {
                treeNodeList.add(addChildNode(t, collect));
            }
        }
        return treeNodeList;
    }

    private T addChildNode(T treeNode, Map<Number, List<T>> collect){
        List<T> list = collect.get(treeNode.getId());
        if (list!=null){
            List<T> treeNodeList = new ArrayList<>();
            for (T t : list) {
                treeNodeList.add(addChildNode(t, collect));
            }
            treeNode.setChilds(treeNodeList);
        }
        return treeNode;
    }

}
