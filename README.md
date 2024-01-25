内容简介  · · · · · ·
《你真的会写代码吗》的核心思想是通过对各方面的代码质量进行比较，使读者了解经验丰富的开发者拥有的思维模式。为了展示软件开发最佳实践，作者对一个水容器示例进行多次重构，讨论了18种实现，分别从7个方面改进代码质量：时间效率、空间效率、监控与可靠性、测试与可靠性、可读性、线程安全、可复用性。在此过程中，作者还探讨了与计算机科学、Java编程以及软件工程相关的专业话题，这些知识都有助于读者写出更好的代码。

作者简介  · · · · · ·
【作者简介】

马尔科·法埃拉（Marco Faella）

意大利那不勒斯费德里克二世大学副教授，面向本科生和研究生讲授高级编程、软件工程、面向对象设计、编译器与程序分析、游戏设计等课程，同时为信息技术从业者开发和讲授Java编程课。另外，他也是爱思唯尔、施普林格等旗下期刊的审稿人。

【译者简介】

雷威

信公科技首席架构师，曾在阿里巴巴中间件团队任职。沉浸软件行业十余年，热衷于软件架构、研发效能、分布式、云原生等领域，相信技术能改变世界。

李强

信公科技CTO，浙江中金黄金集团前副总裁兼CTO，曾就职于美国道富银行。技术涉猎广泛，在产品设计开发、架构设计、技术团队管理等方面有丰富经验。另译有《监控的艺术》《扩展jQuery》等。

丛书信息  · · · · · ·
　　图灵程序设计丛书·程序员修炼系列(共72册)， 这套丛书还有 《发布！》《程序员的38堂成长课》《学习敏捷》《敏捷软件开发》《敏捷武士》 等 。

# Seriously Good Software
## Source files

These are the source files for the book ["Seriously Good Software"](https://www.manning.com/books/seriously-good-software), published by Manning.

This repository is self-contained (dependencies included).
You can compile it from the command line or import it in your IDE (Eclipse project file is included).

You need at least **Java 9** to compile this project.

Most source files are different versions of the same `Container` class.
So, they are all called `Container` but sit in different packages.
For example, `eis.chapter2.reference.Container` is the reference (baseline) version.

Some classes with a "main" method:

 * `eis.ReflectiveUseCase` runs the standard use case on all compatible container versions (you must compile those versions first)
 * `eis.chapter2.reference.UseCase` runs the standard use case on `eis.chapter2.reference.Container`
 * all other `UseCase` classes in various packages

Other fun things to try:

 * Run _jUnit_ on `eis.chapter6.UnitTests`. From the command line (in /src):
 
     `javac -cp .:../libs/junit-4.12.jar eis/chapter6/UnitTests.java`  
     `java  -cp .:../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar org.junit.runner.JUnitCore eis.chapter6.UnitTests`

 * Run code coverage tool _jacoco_ on `eis.chapter6.UnitTests` and obtain an HTML report. From the command line (in /src):
 
     1. Collect info:

        `java -javaagent:../libs/jacocoagent.jar -cp .:../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar org.junit.runner.JUnitCore eis.chapter6.UnitTests`  

     2. Generate the report in the "coverage" folder:

        `java -jar ../libs/jacococli.jar report jacoco.exec --classfiles . --sourcefiles . --html ../coverage/`
