SpringBoot+ElementUI—Customer Info System
Front End and Back End Separated Development

Credit: https://github.com/ming201005/study-demo-01

> ### Dev Environment：
>
> Front End：Element UI + VUE
>
> Back End：SpringBoot, MybatisPlus, Lombok
>
> Back End Tool：IDEA
>
> API Test Tool：PostMan
>
> Front End Dev Tool：VSCode
<br>
 ### How to run the project：
> 1) Download Zip
> 2) Unzip to designated directory
> 3) Use IDE to import POM file
> 4) Create DB (customer_db) and Data Table (customer_table) -> see Chapter1.
> 5) Run Spring Boot Application (CustomerApp) in main/java/com/customer
> 6) Open Index.html in FrontEnd/views/test

# Chapter 1: Create DB Table

mysql，

customer_db

customer_table <br>
run sql script below:
```
CREATE TABLE customer_db.customer_table (
  customer_id int UNSIGNED NOT NULL AUTO_INCREMENT,
  customer_name varchar(255) NOT NULL,
  gender varchar(255) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL,
  addr varchar(255) DEFAULT NULL,
  PRIMARY KEY (customer_id)
)
ENGINE = INNODB,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;
```

# Chapter 2: Create a Back End Project

Tool: IDEA Create a Spring Boot Project

## 1) Create a Spring Boot Project

## 2) Import all jar package

```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.9.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

 <dependencies>
       <!-- spring boot Starter-->
    	  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <!-- spring boot web Starter-->
         <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- spring boot test Starter-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Use lombok to simplify java code. If it's not installed, install the plugin in IntelliJ IDE-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- mybatis-plus Plugin -->
        <dependency>
          <groupId>com.baomidou</groupId>
          <artifactId>mybatis-plus-boot-starter</artifactId>
          <version>3.1.0</version>
				</dependency>

				<!-- mysql jdbc Driver -->
		 		<dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
  </dependencies>

<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```



If you are using maven to create a project, you need to create application starter manually.
```java
@SpringBootApplication
public static void main(String[] args) {
        SpringApplication.run (xx.class, args);
    }
```
If you are using maven to create the project, there is no test class by default.<br>
If you need to create test class, you need to add annotations before the test class.
```java
@RunWith(SpringRunner.class)
@SpringBootTest
```

## 3）Create data source connection config

create application.yml config file
```yaml
server:
  port: 8088
spring:
    datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/XXXX
        username: root
        password: 8888
mybatis-plus:
  #Data output setting
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```



 

# Chapter 3: Implement API interfaces 

 ### Entity
 ### Service
 ### ServiceImpl
 ### DAO
 ### Controller


# Chapter4: Config Interface Mapping and Shown Pages Plugin

1) Add @MapperScan annotation in main application to scan all mappers in one or more mapper packages.在主程序添加注解 @MapperScan，

2) Add Config Class in the poroject. Use @Bean annotation to register divided pages plugin.
```java
//Spring boot
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // Set what happens if the requested page is greater than the max value. True: back to index page; False(default): keep requesting. 
        // paginationInterceptor.setOverflow(false);
        // Set maximum number of data entries in one page (500/page by default, -1 means it's not limited)
        // paginationInterceptor.setLimit(500);
        return paginationInterceptor;
    }
}
```



 



# Chapter 5: Start Back End Project, Test API Interfaces

Use PostMan to test GET, POST methods.


 

# Chapter 6：Create a Front End Project and new Index Page

Element UI+VUE create project manually (not using vue-cli).<br>
Then use VS Code to edit.

## 1) Basic

## 2) Simulate rendering data in Table/List

# Chapter 7: Rendering Back End data to Front End

basic vue framework:

```html
<script>
    var Main = {
        data() {
            return {
               
            }
        },      

        //Initialize
        created() {
            
        },

        
        methods: {
            
        }
    }
    var Ctor = Vue.extend(Main)
    new Ctor().$mount('#app')
</script>
```



```javascript
// Use Ajax to request data from Backend 
getData() {
  let that = this;
  let url = "http://localhost:8080/customer";
  that.$http.get(url, null, { emulateJSON: true })
    .then(res => {
    let rs = JSON.parse(res.body);
    if (rs.code == 0) {
      that.tableData = rs.data.records;
    }
  });

}
```

```javascript
Parse Data From Back End

this.$http.get(url, data,{emulateJSON:true})
   .then( res => {
       let rs = JSON.parse(res.body);
});
```

**Encapsulate ajax: **

```javascript

class Ajax {
    
    vueObj = null;

    baseUrl = "http://localhost:8080/";

    /**
     * Constructor
     * @param {*} vueObj 
     */
    constructor(vueObj) {

        this.vueObj = vueObj;
    }

    /**
     * GET Method
     * @param {*} api 
     * @param {*} callback 
     * @param {*} params 
     */
    get(api,callback,params){
        let url = this.baseUrl + api ;
        let data = JSON.stringify(params);
        this.vueObj.$http.get(url, data,{emulateJSON:true})
        .then( res => {
            callback(JSON.parse(res.body));
        });
    }

    /**
     * POST Method
     * @param {*} api 
     * @param {*} callback 
     * @param {*} params 
     */
    post(api,callback,params){
        let url = this.baseUrl + api ;
        let data = JSON.stringify(params);
        this.vueObj.$http.post(url, data,{emulateJSON:true})
        .then( res => {
            callback(JSON.parse(res.body));
        });
    }

    /**
     * PUT Method
     * @param {*} api 
     * @param {*} callback 
     * @param {*} params 
     */
    put(api,callback,params){
        let url = this.baseUrl + api ;
        let data = JSON.stringify(params);
        this.vueObj.$http.put(url, data,{emulateJSON:true})
        .then( res => {
            callback(JSON.parse(res.body));
        });
    }

    /**
     * DELETE Method
     * @param {*} api 
     * @param {*} callback 
     * @param {*} params 
     */
    delete(api,callback,params){
        let url = this.baseUrl + api ;
        let data = JSON.stringify(params);
        this.vueObj.$http.delete(url, data ,{emulateJSON:true})
        .then( res => {
            callback(JSON.parse(res.body));
        });
    }
}
```



# Chapter 8: Config Shown Pages

```javascript
//Shown Pages Setting
currentChange(current){
  // console.log(current);
  this.page.current = current;
  this.getData();
}
```

```html
<el-pagination
            v-if="page.total>page.size"
            background
            layout="prev, pager, next"
            @current-change="currentChange"
            :page-size="page.size"
            :current-page="page.current"
            :total="page.total">
            </el-pagination>
```



# Chapter 9: Delete Data


```javascript
// Delete Data
            del() {
                if (this.idList.length > 0) {
                    let that = this;
                    let idList = that.idList.toString();
                    let url = "customerTable?idList=" + idList;
                    //Call Ajax DELETE method
                    that.ajax.delete(url, function (rs) {
                        if (rs.code == 0) {
                            //Regain data
                            that.getData();
                        }
                    });
                }
            }
```



# Chapter 10: Create, Edit Data

```javascript
onSubmit() {
                let that = this;
                let url = "customerTable";
                let data = that.form;
                //Use Ajax POST method
                that.ajax.post(url, function (rs) {
                    if (rs.code == 0) {
                        //Regain data
                        that.getData();
                    }
                },data);
            }
```

