# Java: Exercises in Style
## Source files

These are the source files for the book project "Java: Exercises in Style",
currently under development for Manning.

Most source files are different versions of the same `Container` class.
So, they are all called `Container` but sit in different packages.
For example, `eis.reference.Container` is the reference version.

Classes with a "main" method:

 * `eis.UseCase` runs the standard use case on Reference (you may change the import line to run it against other versions)
 * `eis.ReflectiveUseCase` runs the standard use case on all compatible container versions (you must compile those versions first)
 * `eis.Memory3.UseCase` runs the standard use case on Memory3 (object-less API)
 * `eis.Memory4.UseCase` runs the standard use case on Memory4 (object-less API)

Other fun things to try:

 * Run _jUnit_ on `eis.tests.UnitTests`. From the command line (in /src):
 
     `javac -cp .:../libs/junit-4.12.jar eis/tests/UnitTests.java`  
     `java  -cp .:../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar org.junit.runner.JUnitCore eis.tests.UnitTests`

 * Run code coverage tool _jacoco_ on `eis.tests.UnitTests` and obtain an HTML report. From the command line (in /src):
 
     1. Collect info:

        `java -javaagent:../libs/jacocoagent.jar -cp .:../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar org.junit.runner.JUnitCore eis.tests.UnitTests`  

     2. Generate the report in the "coverage" folder:

        `java -jar ../libs/jacococli.jar report jacoco.exec --classfiles . --sourcefiles . --html ../coverage/`
