### iadmin，基于springboot（2.x）+mybatisplus（3.x）的快速开发框架

#### 模块介绍
- iadmin-common：常用基础依赖，引入常用的工具包
- iadmin-core：mybatisplus核心依赖，引代码生成器

#### maven地址

```
 <dependency>
    <groupId>com.github.xs0529</groupId>
    <artifactId>iadmin-core</artifactId>
    <version>1.0.1</version>
 </dependency>
```


#### 核心功能
##### 1.树结构生成工具

tree父类：


```
@Data
public class Tree{
    private Number id;
    private Number pid;
    private Collection childs;
}
```


生成树的工厂类

```
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
```


实际场景中用到的树对象


```
@Data
public class MyTree extends Tree {
    private String name;
}
```

测试


```
public class test{

 public static void main(String[] args) {
        System.out.println("1111111111111111111111");
        List<MyTree> trees = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            MyTree myTree = new MyTree();
            myTree.setName("顶级"+i);
            myTree.setId(i);
            myTree.setPid(0);
            trees.add(myTree);
            for (int j = 11; j <100 ; j++) {
                MyTree myTree1 = new MyTree();
                myTree1.setName("子级"+j);
                myTree1.setId(j);
                myTree1.setPid(i);
                trees.add(myTree1);
            }
        }
        System.out.println(trees);
        System.out.println("222222222222222222222");
        Collection<MyTree> tree = new TreeFactory<MyTree>().createTree(trees);
        System.out.println(JSONObject.toJSONString(tree));
        System.out.println("3333333333333333333333333");
    }
}
```


说明：
> 1.我们要生成的树的对象继承Tree对象即可。
> 
> 2.需要排序的话在调用生成树方法之前对传入的集合进行排序即可。
> 
> 3.生成树的数据量建议在1万条左右，否则会很慢，超级慢。经测试，1万数据量生成在1S左右，10万已经半天跑不出来了。大数据量请做懒加载，逐级获取数据。

##### 2.mybatisplus wrapper 生成工具

说明：mybatisplus封装了良好的单表查询方法，但是在使用过程中还是要构造查询条件类，而且查询条件多的话会比较繁琐，所以单独写了一个wrapper生成工具，根据传入dto字段上的注解生成查询条件

工具类与注解位于 xyz.iotcode.iadmin.core.wrapper 包下，有兴趣的可以查看代码，实现方式比较简单。

使用方式：

dto 类字段上增加@QueryCondition注解，默认构造==条件，目前还支持like，in，大于，小于这几个常用条件注解
```
   @ApiModelProperty(value = "菜单id")
    @QueryCondition
    private Long menuId;
```

service层使用方式
```
@Override
    public IPage<Menu> ipage(MenuQuery query) {
        return this.page(query.createPage(), new WrapperFactory<Menu>().create(query));
    }
```

> 此wrapper工具比较简单，但是基本满足日常使用，后续增加更多条件。


##### 3.封装 mybatisplus 的代码生成器

基于mybatisplus的代码生成器，改写自己的生成模板，目前支持生成后端crud代码，生成的代码包含：==service层缓存注解（可配置）==，==entity 参数校验注解（JSR303，可配置）==

使用方式：复制 MybatisPlusGenerator 到项目里，保证与Application启动器同级目录，更改生成方法里的配置（数据库，生成选项等），即可生成代码

代码生成演示视频：http://image.xieshuang.xyz/%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90%E6%BC%94%E7%A4%BA%E8%A7%86%E9%A2%91.mp4


