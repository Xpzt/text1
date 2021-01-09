package com.xpzt.day23.demo02;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/*
 * 练习:
 *   递归打印多级目录
 * 需求:
 *   只要.java结尾的文件
 *   C:\abc
 *   C:\abc\abc.txt
 *   C:\abc\abc.java
 *   C:\abc\a
 *   C:\abc\a\a.jpg
 *   C:\abc\a\a.java
 *   C:\abc\b
 *   C:\abc\b\b.txt
 *   C:\abc\b\b.java
 *
 * 我们可以使用过滤器来实现
 * 在File类中有两个和listFiles重载的方法,方法的参数传递过去的是过滤器
 * File[] listFiles(FileFilter filter)
 * java.io.FileFilter接口:用于抽象路径名(File类对象)的过滤器
 *      作用:用来过滤文件的方法
 *      抽象方法:用来过滤文件的方法
 *          boolean accept(File pathname) 此时指定抽象路径名是否应该包含在某个路径名列表中
 *          参数:
 *              File pathname:使用listFiles方法遍历目录,得到的每一个文件对象
 *
 * File[] listFiles(FilenameFilter filter)
 * java.io.FilenameFilter接口:实现此接口的类实例可用于过滤器文件名
 *      作用:用来过滤文件名称
 *      抽象方法:用来过滤文件的方法
 *          boolean accept(File dir,String name ) 测试指定文件是否应该包含在某一文件列表中
 *          参数:
 *              File dir:构造方法中传递的被遍历的目录
 *              String name:使用listFiles方法遍历目录,获取每一个文件/文件夹的名称
 * 注意:
 *      两个过滤器接口是没有实现类的,需要我们自己写实现类,重写过滤的方法accept,在方法中自己定义过滤的规则
 * */
public class Demo02Filter {
    public static void main(String[] args) {
        File file = new File("C:\\abc");
        getAllFile(file);
    }

    /*
     * 定义一个方法,参数传递File类型的目录
     * 方法中对目录进行遍历
     * */
    public static void getAllFile(File dir) {
        /*//打印被遍历的目录名称
        System.out.println(dir);*/
        //使用过滤器对象 使用匿名内部类
        /*File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                //过滤规则,pathname是文件夹或者是.java结尾的文件返回true
                return pathname.isDirectory() || pathname.toString().toLowerCase().endsWith(".java");
            }
        });*/

        //使用Lambda表达式优化匿名内部类(接口中只有一个抽象方法)
        //File[] files = dir.listFiles(pathname -> pathname.isDirectory() || pathname.toString().toLowerCase().endsWith(".java"));

       /* File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                //过滤规则,pathname是文件夹或者是.java结尾的文件返回true
                return new File(dir, name).isDirectory() || name.toLowerCase().endsWith(".java");
            }
        });*/

        //使用Lambda表达式优化匿名内部类(接口中只有一个抽象方法)
        File[] files = dir.listFiles((d, name) -> new File(d, name).isDirectory() || name.toLowerCase().endsWith(".java"));
        for (File f : files) {
            //对遍历得到的File对象f进行判断,判断是否文件夹
            if (f.isDirectory()) {
                //f是一个文件夹,则继续遍历这个文件夹
                //我们发现getAllFile方法就是传递文件夹,遍历文件夹的方法
                //所以我们直接调用getAllFile方法即可:递归(自己调用自己)
                getAllFile(f);
            } else {
                //f是一个文件直接打印
                System.out.println(f);
            }
        }
    }
}

