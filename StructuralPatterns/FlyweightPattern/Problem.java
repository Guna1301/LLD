package StructuralPatterns.FlyweightPattern;
import java.util.*;

class Tree{
    // attributes that keep on changing
    private int x;
    private int y;

    // attributes that remain constant
    private String name;
    private String color;
    private String texture;

    public Tree(int x, int y, String name, String color, String texture) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void display() {
        System.out.println("Tree: " + name + ", Color: " + color + ", Texture: " + texture + " at (" + x + ", " + y + ")");
    }   

}

class Forest{
    private List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        Tree tree = new Tree(x, y, name, color, texture);
        trees.add(tree);
    }

    public void displayTrees() {
        for(Tree tree : trees) {
            tree.display();
        }
    }
}

public class Problem {
    public static void main(String[] args) {
        Forest forest = new Forest();

        // plant 100000 trees
        for(int i = 0; i < 100000; i++) {
            forest.plantTree((int)(Math.random() * 1000), (int)(Math.random() * 1000), "Oak", "Green", "Rough");
        }
        forest.displayTrees();
    }
}

/*
issues with this code:
the above code works absolutely fine but there are few problems with it:
1. redudant memory usage: same tree data(name, color, texture) is duplicated for every tree instance
2. ineffecient: slower rendering as creating large number of objects takes time and memory allocation/deallocation adds overhead

here we creating a new tree object for each of the 100,000 trees, even when most of them had
identical properties like name, color, texture. This leads to unnecessary memory consumption and can slow down performance.

To solve these issues, we can implement the Flyweight Pattern. This pattern allows us to share common data among multiple objects, 
reducing memory usage and improving performance.
*/