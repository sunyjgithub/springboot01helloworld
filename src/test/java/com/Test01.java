package com;

import com.atguigu.entity.Course;
import com.atguigu.entity.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*
* java对象中private类型变量可以通过不提供set方法保证不被修改，但对象的List成员在提供get方法后，就可以随意add、remove改变其结构，
* 这不是希望的结果。网上看了下，发现Collections的静态方法unmodifiableList可以达到目的。
* 方法原型为：public static <T> List<T> unmodifiableList(List<? extends T> list);
* 用法也很简单，传入一个List实例la，返回这个list的只读视图lb，类型依然是List。
* 之后对lb进行add、remove等改变其内容的操作将导致编译不通过。
* 报错： java.lang.UnsupportedOperationException
*
*
* */
public class Test01 {

    public static void main(String[] args) {
        Course c1 = new Course();
        c1.setCourseName("语文");

        Course c2 = new Course();
        c1.setCourseName("数学");

        List<Course> lists=new ArrayList<>();
        lists.add(c1);
        lists.add(c2);


        Person p=new Person();
        p.setName("sunyj");
        p.setCourses(lists);


        p.getCourses().add(new Course("英语"));

        System.out.println(p.getCourses().size());


        List<Course> courses = Collections.unmodifiableList(p.getCourses());

        courses.add(new Course("物理"));

    }

}
