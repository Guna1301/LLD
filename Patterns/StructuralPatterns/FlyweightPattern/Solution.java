package StructuralPatterns.FlyweightPattern;
import java.util.*;

class TreeType{
    // attributes that remain constant
    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void display(int x, int y) {
        System.out.println("Tree: " + name + ", Color: " + color + ", Texture: " + texture + " at (" + x + ", " + y + ")");
    }
}


class Tree{
    // attributes that keep on changing
    private int x;
    private int y;

    // reference to shared TreeType
    private TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void display() {
        type.display(x, y);
    }
}

class TreeFactory{
    private static final Map<String,TreeType> treeTypeMap = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "-" + color + "-" + texture;
        if(!treeTypeMap.containsKey(key)) {
            treeTypeMap.put(key, new TreeType(name, color, texture));
        }
        return treeTypeMap.get(key);
    }
}

class Forest{
    private List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeType type = TreeFactory.getTreeType(name, color, texture);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }

    public void displayTrees() {
        for(Tree tree : trees) {
            tree.display();
        }
    }
}


public class Solution {
    public static void main(String[] args) {
        Forest forest = new Forest();

        for(int i=0;i<100000;i++){
            forest.plantTree(i, i, "oak", "green", "rough");
        }

        forest.displayTrees();
    }
}

/*
how flyweight pattern solves previous issues:
- treetype class: this acts as the flyweight object. it stores the data common to all trees of a given type(name, color, texture). insted of
duplication this data, we create only one instance per unique combination of these attributes.

- Tree class: this now only stores
   - intrinsic state: the reference to the shared TreeType object
    - extrinsic state: the unique attributes for each tree instance (x, y coordinates)
- treeFactory class: this is the central factory taht ensures treetype instance are reused:

- memory efficieny: even with 100,000 trees, if there are only a few unique types of trees, the memory used is significantly reduced.
- improved performance: rendering is faster as fewer objects are created, reducing memory allocation overhead
most of their data identical. by sharing the common data in TreeType instances, we save a lot of memory and improve performance.
*/