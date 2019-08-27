package xyz.iotcode.iadmin.common.tree;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TreeFactoryTest {

    public static void main(String[] args) {
        List<MyTree> trees = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            MyTree myTree = new MyTree();
            myTree.setName("顶级"+i);
            myTree.setId(i);
            myTree.setPid(0);
            trees.add(myTree);
            for (int j = 0; j <100 ; j++) {
                MyTree myTree1 = new MyTree();
                myTree1.setName("子级"+j);
                myTree1.setId(j+(i*100));
                myTree1.setPid(i);
                trees.add(myTree1);
                for (int k = 0; k <100 ; k++) {
                    MyTree myTree2 = new MyTree();
                    myTree2.setName("子级"+j);
                    myTree2.setId(k+(j*100)+(i*10000));
                    myTree2.setPid(j+(i*100));
                    trees.add(myTree2);
                }
            }
        }
        System.out.println(trees.size());
        Collection<MyTree> tree = new TreeFactory<MyTree>().createTree(trees);
        //System.out.println(JSONObject.toJSONString(tree));
    }
}








