# Java: Exercises in Style
## Source files

These are the source files for the book project "Java: Exercises in Style",
currently under development for Manning.

Classes with "main" method:
 * `eis.UseCase` runs the standard use case on Reference (you may change the import line to run it against other versions)
 * `eis.ReflectiveUseCase` runs the standard use case on all compatible container versions
 * `eis.Memory3.UseCase` runs the standard use case on Memory3 (object-less API)
 * `eis.Memory4.UseCase` runs the standard use case on Memory4 (object-less API)

Other executable things:
 * Run jUnit on `eis.tests.UnitTests`. From the command line:
 
javac -cp .:../libs/junit-4.12.jar eis/tests/UnitTests.java
java  -cp .:../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar org.junit.runner.JUnitCore eis.tests.UnitTests

 * Run jacoco on `eis.tests.UnitTests`. From the command line:
   1. Collect info:
java -javaagent:../libs/jacocoagent.jar -cp .:../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar org.junit.runner.JUnitCore eis.tests.UnitTests
   2. Generate the report in the "coverage" folder:
java -jar ../libs/jacococli.jar report jacoco.exec --classfiles . --sourcefiles . --html ../coverage/
