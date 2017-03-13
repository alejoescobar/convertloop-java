# ConvertLoop API Java Client

A Java client of the ConvertLoop REST API. You can sign up for a ConvertLoop account at http://convertloop.co.

## Installation

### Gradle

Add the dependency to your `build.gradle` file:
```groovy
dependencies {
    compile 'co.convertloop:0.1.0'
}
```

### Maven

Add the dependency to your `pom.xml`file:
```xml
<dependency>
  <groupId>co.convertloop</groupId>
  <artifactId>convertloop-java</artifactId>
  <version>0.1.0</version>
</dependency> 
```

Or [download the JAR](https://search.maven.org/remotecontent?filepath=co/convertloop/convertloop-java/0.1.0/convertloop-java-0.1.0.jar), [its dependencies](https://search.maven.org/remotecontent?filepath=com/google/code/gson/gson/2.8.0/gson-2.8.0.jar) and include it in your project.

## Getting Started

First, you need to create an instance of ConvertLoop class passing your appId and apiKey:

```java
Convertloop convertloop = new Convertloop("appId", "apiKey", "v1")
```

You are now ready to start calling the API methods:


### Creating or updating a person

You need to pass at least one of the following: `pid`, `user_id` or `email` to identify a user. Use `pid` when you are updating a guest of your site (you can obtain this value from the cookie `dp_pid`). Use `user_id` to match the `id` of the user in your application.

```java
import java.util.HashMap;

Convertloop convertloop = new Convertloop("7aa23283", "wUDY7GN7m9P111Xj37sWhnuE", "v1");
HashMap<String, Object> person = new HashMap<String, Object>();
person.put("email", "alejo.escobar@convertloop.co");
person.put("first_name", "Alejandro");
person.put("last_name", "Escobar");
person.put("plan", "free");
convertloop.createOrUpdatePerson(person);
```

Any key different to `pid`, `user_id`, `email`, `first_seen_at`, `last_seen_at`, `add_tags`, and `remove_tags` will be treated as a **custom attribute** of the person.

You can add or remove tags using the `add_tags` and `remove_tags` keys:
import java.util.HashMap;

```java
import java.util.HashMap;

HashMap<String, Object> person = new HashMap<String, Object>();
person.put("email", "alejo.escobar@convertloop.co");
person.put("plan", "free");
person.put("add_tags", new String[] {"Learn Something"});
person.put("remove_tags", new String[] {"Lead"});
convertloop.createOrUpdate(person);
```

### Tracking an event

You can track an event for any person:

```java
import java.util.HashMap;

HashMap<String, Object> person = new HashMap<String, Object>();
person.put("email", "alejo.escobar@convertloop.co");
HashMap<String, Object> metadata = new HashMap<String, Object>();
metadata.put("credits", 1000);
HashMap<String, Object> event = new HashMap<String, Object>();
event.put("name", "Billed");
event.put("person", person);
event.put("metadata", metadata);
event.put("occurred_at", new Date());
convertloop.sendEventLog(event);
```

If you don't specify the `ocurred_at` key, the current time will be used. You can use the `person` key to add **custom attributes** and **tags** to that person.


