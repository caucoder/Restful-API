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

## [`lesson7 PathParam`](https://github.com/caucoder/Restful-API/tree/lesson7)

1. 将client端的get请求参数，提取绑定到函数参数上。@PathParam
    ```java
        @GET
        @Path("alien/{id}")
        @Produces(MediaType.APPLICATION_XML)
        public Alien getAlien(@PathParam("id") int id){
            
            return alienRepo.getAlienById(id);
        }
    ```


## [`lesson8 Working with JSON`](https://github.com/caucoder/Restful-API/tree/lesson8)

1. maven pom.xml中，支持json
    ```xml
        <!-- uncomment this to get JSON support -->
        <dependency>
            <groupId>org.glassfish.jersey.media</groupId>
            <artifactId>jersey-media-json-binding</artifactId>
        </dependency>
    ```
2. @Produces设置返回的类型MediaType.APPLICATION_JSON,注意可以设置多个类型，但是需要用大括号{},根据客户端的发送的头部信息 Accept： "application/json" 还是 "application/xml"，服务器决定返回什么类型
    ```json
    [
        {
            "id": 1,
            "name": "Q10Viking",
            "points": 95
        },
        {
            "id": 2,
            "name": "huangzhuangzhuang",
            "points": 100
        }
    ]
    ```
    ```xml
    <aliens>
    <alien>
        <id>1</id>
        <name>Q10Viking</name>
        <points>95</points>
    </alien>
    <alien>
        <id>2</id>
        <name>huangzhuangzhuang</name>
        <points>100</points>
    </alien>
    </aliens>
    ```

## [`lesson9 Mysql Repository`](https://github.com/caucoder/Restful-API/tree/lesson9)

1. 使用mysql Workbench创建数据库alien
    ```sql
        create database restdb;
        use restdb;
        create table alien(id int,name varchar(20),points int);
        select * from alien;
    ```
2. 在pom.xml中加入jdbc-mysql驱动
    ```xml
    <!-- jdbc-mysql-connecter -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.15</version>
    </dependency>
    ```
3. 使用java中的Statement,PreparedStatement,Connection,DriverManager,ResultSet,进行数据查询
4. 用postman形式测试观察



## [`lesson10 Consumes JSON and XML`](https://github.com/caucoder/Restful-API/tree/lesson10)


1. @Consumes指定服务器接收的数据类型，Alien类仍然是使用@XmlRootElement
```java
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Alien getAlien(@PathParam("id") int id){
        
        return alienRepo.getAlienById(id);
    }
```





## [`lesson11 Update Resource using PUT method`](https://github.com/caucoder/Restful-API/tree/lesson11)


1. @PUT注解用于http put方法，更新数据，数据与post一样存在与body当中
    ```java
        @PUT
        @Path("updateAlien")
        @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
        public void updateAlien(Alien alien){
            System.out.println(alien);
            //如果不存在，则添加
            if(alienRepo.getAlienById(alien.getId()).getId()==0){
                System.out.println("该对象不能更新，添加"+alien);
                alienRepo.createAlien(alien);
            }else{
                alienRepo.updateAlien(alien);
                System.out.println("更新成功。");
            }
        }
    ```



-----------



## [`lesson12 Delete Resource`](https://github.com/caucoder/Restful-API/tree/lesson12)

1. @DELETE
```java
    @DELETE
    @Path("alien/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Alien deleteAlien(@PathParam("id") int id){
        
        Alien a = alienRepo.getAlienById(id);
        //如果id=0，表示是空值
        if(a.getId() != 0)  
            alienRepo.deleteAlienById(id);
        return a;
    }
```


**use mysql workbancg error**
> You are using safe update mode and you tried to update a table without a WHERE that uses a KEY column To disable safe mode, toggle the option ....

[fix the problem](https://stackoverflow.com/questions/11448068/mysql-error-code-1175-during-update-in-mysql-workbench)
```
SET SQL_SAFE_UPDATES = 0;
```


-----------




## 小结


1. 通过使用JAR-RS实现的Restful api,进行实验。通过postman测试http的get,post,put,delete方法与后台数据库mysql的select,insert,update,delete结合使用，数据在server端与client端之间的通信。
2. 项目在vscode中成型，运行项目安装好下面的插件，使用**mvn clear package**命令生成war文件，然后部署到tomcat即可
3. postman测试用到的链接
```java
//获取全部数据
GET http://localhost:8080/demorest/webapi/aliens   

//根据id获取数据
GET http://localhost:8080/demorest/webapi/aliens/alien/5

//删除数据
DELETE http://localhost:8080/demorest/webapi/aliens/alien/4

//提交数据，数据在body
POST http://localhost:8080/demorest/webapi/aliens/addAlien

//更新数据,数据在body
PUT http://localhost:8080/demorest/webapi/aliens/updateAlien

```
4. 代码功能
```java
├── Alien.java     POJO对象
├── AlienRepository.java  与数据库链接
├── AlienResources.java   restful api数据交互
└── MyResource.java  项目初始化自带的测试用例
```
5. 关键信息
    1. java POJO对象:@XmlRootElement
    2. 返回：@Produces
    3. 接收：@Consumes
    4. 类型：MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML



## Import Notes

1. [Vscode 开发Java Web急救教程](https://www.cnblogs.com/fayin/p/10256619.html),生成.war文件
    ```
    mvn clean package
    ```
2. post请求直接绑定到对象上，get请求通过@PathParam注解绑定参数


## 插件

1. Java Code Generators
2. Maven for Java
3. Tomcat for Java