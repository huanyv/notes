# Java工具类

## 菜单列表转树形菜单

```java
public static List<MenuNode> toTree(List<MenuNode> treeList, Integer root) {
    List<MenuNode> children = new ArrayList<MenuNode>();
    //1、遍历list,给root找孩子
    for(MenuNode menu : treeList) {
        //2、判断父节点是否为root
        if(root.equals(menu.getPid())) {
            children.add(menu);
        }
    }
    //3、遍历孩子节点list
    for(MenuNode menu : children) {
        List<MenuNode> menuNodes = toTree(treeList, menu.getId());
        menu.setChild(menuNodes);
    }
    return children;
}
```


```java
public static List<MenuNode> toTree(List<MenuNode> treeList, Long root) {
    List<MenuNode> children = treeList.stream()
            .filter(menuNode -> root.equals(menuNode.getPid())) // 找孩子
            .map(menuNode -> {
                menuNode.setChild(toTree(treeList, menuNode.getId()));
                return menuNode;
            }) // 给孩子找孩子
            .collect(Collectors.toList());

    return children;
}
```