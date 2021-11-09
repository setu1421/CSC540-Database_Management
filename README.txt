Team 10 members:

1. Setu Kumar Basak (sbasak4)
2. Md Jakaria (mjakari)
3. Anurata Prabha Hridi (aphridi)
4. Md Mirajul Islam (mislam22)

Steps to compile and execute the code: (Assuming the zip file is being ported in the remote server)

1. Unzip the "Project 1.zip" file using below command
   
   unzip "Project 1.zip"

2. Add Oracle to your environment  
   
   add oracle12

3. Set the CLASSPATH variable to include the JDBC drivers.  

   export CLASSPATH=.:/afs/eos.ncsu.edu/software/oracle12/oracle/product/12.2/client/jdbc/lib/ojdbc8.jar
   
4. Make sure to have jdk version "11.0.13". No need to do "add jdk" command in the remote as it will downgrade to lower version. Check the version using below command.

   java -version

5. Go the directory "Project 1/SQL"   

   cd "Project 1/SQL"
   
6. Type sqlplus and connect by providing the username and password (Make sure to be present in the SQL folder)
   
    sqlplus

7. Run the delete_object sql to delete any existing schema and objects

    @delete_object.sql
    /
    
8. Run the DDL sql to create all the tables, functions, stored procedures etc.
    
    @DDL.sql
    
9. Run the DML sql insert all the given records

    @DML.sql

10. Exit sqlplus by running below command   
     
    exit 
    
11  Go the "Marketplace Loyalty Program/src" directory.
    
    cd "../Marketplace Loyalty Program/src"    
    
12. Compile the code
    
    javac Home.java

13. Run the code

    java Home 

14. To run the jar, go to directory "../classes/artifacts/Marketplace_Loyalty_Program_jar"   

    cd "../classes/artifacts/Marketplace_Loyalty_Program_jar"   
    java -jar "Marketplace Loyalty Program.jar"  