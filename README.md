# sep-project
SEP Project repository for Software Engineering Methods course

How to setup this:

Install VScode: https://code.visualstudio.com/download

Install Java JDK (I installed latest one here: https://download.oracle.com/java/23/latest/jdk-23_windows-x64_bin.exe)

Install Maven: https://maven.apache.org/download.cgi (I downloaded it as a binary zip file, extract it wherever you want)

Install Extension Pack for Java in VScode extensions menu

Make sure to have environmental variables properly setup (otherwise you will get errors such as "mvn : The term 'mvn' is not recognized as the name of a cmdlet, function, script file, or operable program. Check the spelling of the name, or if a path was included, verify that the path is correct and try again."):
    
    It should look something like this: 

    System variables
    JAVA_HOME C:\Program Files\Java\jdk-23
    PATH C:\Windows\System32;C:\Program Files\Java\jdk-23\bin;C:\Program Files\ApacheMaven\apache-maven-3.9.9\bin;

Once repo is cloned, position in "sep-app" folder, and run: mvn clean install (this should insall all dependencies if maven is properly setup)

Once that is done, running "mvn jetty:run" should host website on http://localhost:8080/