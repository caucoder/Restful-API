## Restful-API
学习restful web Services


## [`lesson1 创建环境`](https://github.com/caucoder/Restful-API/tree/lesson1)

> jersey-quickstart-webapp

[vscode 创建maven project](https://blog.usejournal.com/visual-studio-code-for-java-the-ultimate-guide-2019-8de7d2b59902)



## [`lesson2 Running our First Rest Jersey Application`](https://github.com/caucoder/Restful-API/tree/lesson2)

> vscode安装插件 **Maven for Java**和**Tomcat for Java**

1. 执行mvn clean package将项目打包后，在 target 目录中会生成 .war 文件
    ```
    (base) hzz@hzz:~/Desktop/github/caucoder/Restful-API/project/demorest$ mvn clean package
    ```
2. 具体如何运行及配置看插件**Tomcat for Java**的useage
    ![](https://raw.githubusercontent.com/adashen/vscode-tomcat/master/resources/Tomcat.gif)
3. 注意缓存对浏览器的影响，可能程序已经对了，但是由于浏览器的缓存作用，导致延迟发现。



## [`lesson3 How to create a Resource Class`](https://github.com/caucoder/Restful-API/tree/lesson3)

```
├── Alien.java     
├── AlienResources.java   
└── MyResource.java
```
1. lienResources.java 作为访问的接口,@Path,@GET,@Produces
    ```java
    @Path("aliens")
    public class AlienResources {

        @GET
        @Produces(MediaType.APPLICATION_XML)
        public Alien getAlien(){
            Alien alien  = new Alien();
            alien.setName("Q10Viking");
            alien.setPoints(95);
            return alien;
        }
        
    }
    ```
2. 对象Alien.java，需要转化为xml文件，@XmlRootElement
    ```java
        @XmlRootElement
        public class Alien {

            private String name;
            private int points;
            //...
        }
    ```
3. 访问 http://localhost:8080/demorest/webapi/aliens
    ```xml
        <alien>
            <name>Q10Viking</name>
            <points>95</points>
        </alien>
    ```




## [`lesson4 List as Resource`](https://github.com/caucoder/Restful-API/tree/lesson4)

1. List返回的xml形式
    ```java
        @GET
        @Produces(MediaType.APPLICATION_XML)
        public List<Alien> getAlien(){
            Alien alien1  = new Alien();
            alien1.setName("Q10Viking");
            alien1.setPoints(95);


            Alien alien2  = new Alien();
            alien2.setName("huangzhuangzhuang");
            alien2.setPoints(100);

            List<Alien> aliens = Arrays.asList(alien1,alien2);

            return aliens;
        }

        /*
        <aliens>
            <alien>
                <name>Q10Viking</name>
                <points>95</points>
            </alien>
            <alien>
                <name>huangzhuangzhuang</name>
                <points>100</points>
            </alien>
        </aliens>
        */
    ```




## [`lesson5 Mock repository`](https://github.com/caucoder/Restful-API/tree/lesson5)

1. 创建AlienRepository.java
2. java8使用lambda遇到问题，[Lambda expressions are allowed only at source level 1.8 or above](https://github.com/redhat-developer/vscode-java/issues/328),看pom.xml
3. [Java 8 Streams filter examples](https://www.mkyong.com/java8/java-8-streams-filter-examples/)


## [`lesson6 Send a Post Request`](https://github.com/caucoder/Restful-API/tree/lesson6)


1. 使用postman软件提交post请求到 http://localhost:8080/demorest/webapi/aliens/addAlien 其中 Headers信息是 Content-Type=application/xml,body是
    ```xml
        <alien>
            <id>3</id>
            <name>caucoder</name>
            <points>95</points>
        </alien>
    ```
2. AlienResources.java的属性声明为static,final,在post后，能够get到数据
    ```java
        //避免每次请求都重新创建AlienRepository
        static final AlienRepository alienRepo = new AlienRepository();
    ```





## Import Notes

1. [Vscode 开发Java Web急救教程](https://www.cnblogs.com/fayin/p/10256619.html),生成.war文件
    ```
    mvn clean package
    ```


## 插件

1. Java Code Generators
2. Maven for Java
3. Tomcat for Java