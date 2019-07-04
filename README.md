# Seriously Good Software
## Source files

These are the source files for the book ["Seriously Good Software"](https://www.manning.com/books/seriously-good-software), published by Manning.

This repository is self-contained (dependencies included).
You can compile it from the command line or import it in your IDE (Eclipse project file is included).

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
