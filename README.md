spark-template-jade
=======================

Spark templating system using jade rendering.

Add jade4j dependency to maven dependencies

```
 <dependency>
    <groupId>de.neuland-bfi</groupId>
    <artifactId>jade4j</artifactId>
    <version>0.4.0</version>
  </dependency>
```        

Example usage of JadeEngine

Create a file named hello.jade and place it into the resources/templates directory
Copy the following into the file...

```html
!!! 5
html
    head
    body
        p Hi there #{name}
```        
In your Java code,
Create a simple route

Use the a Hashmap to pass in the model variables we defined in hello.jade.
In our case we have a variable named "name" -> ```#{name}```

Set the "name" key to a value.

and pass in the JadeEngine.

```java
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class JadeTemplateExample {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("name", "Godzilla");

        // hello.jade file is in resources/templates directory
        get("/hello", (rq, rs) -> new ModelAndView(map, "hello"), new JadeEngine());
    }
}
```

Now if you render ```/hello``` you should get the following.

Hi there Godzilla
