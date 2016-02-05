## Capsule localcache

**localcache** is a [Capsule](http://capsule.io) caplet which relocates the capsule cache directory to be co-located with the capsule jar file.

```
.
├── myapp.jar
└── .capsule
    └── apps
        └── myapp
```

This caplet allows you to remove a Capsule and all it's extracted resources by deleting the directory containing your Capsule jar file.  

### Limitations

By design, Capsule's internals are locked-down.  This caplet uses reflection to manipulate the static value that specifies the cache directory.

This caplet is built against Capsule 1.0.1 but it is a fragile workaround for a [missing feature](https://github.com/puniverse/capsule/issues/59) in core Capsule.

### Maven Usage

Add the dependency to your capsule application.
```xml
<dependency>
    <groupId>com.gitblit.capsule</groupId>
    <artifactId>localcache</artifactId>
    <version>1.0.0</version>
</dependency>
```

Configure the [Capsule Maven plugin](https://github.com/chrischristo/capsule-maven-plugin) to load this caplet on startup.

```xml
<plugin>
    <groupId>com.github.chrischristo</groupId>
    <artifactId>capsule-maven-plugin</artifactId>
    <version>1.0.2</version>
    <executions>
        <execution>
            <goals>
                <goal>build</goal>
            </goals>
            <configuration>
                <appClass>com.namespace.yourapp.Launcher</appClass>
                <caplets>com.gitblit.capsule:localcache:1.0.0</caplets>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### License

Distributed under the Apache Software License 2.0.