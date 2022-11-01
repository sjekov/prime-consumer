# Read Me First
###What is prime consumer project used for
Consumer connects to the generator project and consumes random positive numbers. 
Creates a csv file with all numbers. Where the firs value is the number and second is boolean value if it is prime one.
Numbers are shown in the log. 
##How to run the prime-consumer
### Maven package
run command

    mvn clean package

### Docker 

in the project folder run command

    docker build --tag=prime-consumer:latest .

after success run command 

    docker run -p8082:8082 prime-consumer:latest


#Missing implementation
- There are not so many test. 
- In docker mounting drive is not configured, so user can see the CSV files. You can see the files if you run the apps on your IDE. 
- Missing maven profiles.
- Logging is not configured
