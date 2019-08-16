package xyz.iotcode.iadmin.common.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 树工厂类，用于生成树
 * @author xieshuang
 * @date 2019-08-08 10:43
 */
public class TreeFactory<T extends Tree>{

    public Collection<T> createTree(Collection<T> treeNodes){
        Collection<T> treeNodeList = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (treeNode.getPid()==null || treeNode.getPid().equals(0) || treeNode.getPid().equals(0L)){
                treeNodeList.add(addChildNode(treeNode, treeNodes));
            }
        }
        return treeNodeList;
    }

    private T addChildNode(T treeNode, Collection<T> treeNodes){
        List<T> treeNodeList = new ArrayList<>();
        for (T node : treeNodes) {
            if (node.getPid()!=null && node.getPid().equals(treeNode.getId())){
                treeNodeList.add(addChildNode(node, treeNodes));
            }
        }
        treeNode.setChilds(treeNodeList);
        return treeNode;
    }

}
