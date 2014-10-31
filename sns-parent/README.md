项目简介
-----
Maven父pom项目，将pom公共部分提取出来汇总在父pom中，降低各项目编写pom的复杂度。

包含的内容
-----
* 指定各插件（plugin）的版本，基本参数（如编码）
* 指定常用依赖的具体版本，希望通过父pom能控制所有二方和三方包的依赖，降低项目pom依赖冲突的可能性。如果需要，项目可以自己特殊指定。
* 指定公司私有Maven仓库的地址。
* 指定公司私有仓库的发布地址，发布需要有帐号密码。

使用方式
-----
* 各项目在pom.xml中增加以下内容：

```java
<project ...>
        ...
        
        <!-- 指定父pom信息 -->
        <parent>
          <groupId>cc.pp</groupId>
	        <artifactId>parent</artifactId>
	        <version>0.0.1-SNAPSHOT</version>
        </parent>

        ...

        <!-- 指定pp私有Maven仓库地址，以便下载父pom -->
        <repositories>
	        <repository>
		        <id>ppcc-public</id>
		        <name>Nexus Release Repository</name>
		        <url>http://ops.pp.cc:3305/nexus/content/groups/public/</url>
	        </repository>
        </repositories>

        ...
</project>
```

* 如果pom中依赖或者插件的版本处提示“Overriding managed ...”，说明父pom中已经指定，此处不需要再指定。如果有特殊需要可以覆盖。

> 注意：父pom中依赖的版本或者插件的版本，可能会在不通知的情况下变更。项目需要自己进行回归测试。