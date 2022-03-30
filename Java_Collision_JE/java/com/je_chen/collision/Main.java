package com.je_chen.collision;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList objects = new ArrayList() ; //想要檢查碰撞的Object

        QuadTree quad = new QuadTree(0, new Rectangle(0,0,600,600)); // 4 叉樹
        quad.Clear();
        ArrayList allObjects = new ArrayList();

        //每frame都要重新插入
        for (int i = 0; i < allObjects.size(); i++) {
            quad.Insert((Rectangle) allObjects.get(i));
        }

        ArrayList returnObjects = new ArrayList();
        for (int i = 0; i < allObjects.size(); i++) {
            returnObjects.clear();
            quad.Retrieve(returnObjects, (Rectangle) objects.get(i));
            for (int x = 0; x < returnObjects.size(); x++) {
                // Run collision detection algorithm between objects
            }
        }

    }
}
