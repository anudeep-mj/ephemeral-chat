Instructions on how to compile your service

- Go to demo folder
- Run the command: "mvn clean install -DskipTests=true"   
(If you compile with tests (i.e mvn clean install), it will run the 1 min integration test i have for my verification)

Instructions on how to run your service locally

- In the demo folder type the command   
"java -jar target/demo-0.0.1-SNAPSHOT.jar"
- In the resource folder  have a postman json named, Demo MFP.postman_collection which you can import to your postman (its a tool i use for api testing and development) and check out the apis once the project is running

The decisions you made

- Decided on the technology: Spring boot
- Have separation of concerns. Basically meaning divide program to specific folders for further needs.
- REST APIs only created for POST and GET as per the requested.
- Using Lightweight H2 database which is only runtime available.
- Using JPA for ease in database transactions.
- Columns for the message entity created as per requested.
- Added required dependencies in the pom.xml

The limitations of your implementation

- The request was for having timeout in integer. This means milliseconds timeout is not available
- No delete and update chats added since not requested
- Cannot post message without username of text
- Not able to see list of expired and unexpired messages at the same time.

What you would do if you had more time

- Better custom exception handling
- Separate table for user management
- Build further apis which could do more crud operations and also a better list api to return all expired and unexpired messages
- Oauth work to validate and verify user using token/context
- Standalone database integration like mysql, sql
- Streamlined retrieval for messages. Snapchat could have millions of records. This would needs smarter data handling like sharding etc
- Dockerizing and containerizing the application to have multiple instances on cloud running with infrastructure support such as load balancer, proxy handling etc
- Support for not only text but multimedia messages
- More tests
- Integration with mobile/desktop app for a good UI


How you would scale it in the future

- Dockerizing and deploying it in containers over cloud
- Using technologies like proxy servers, load balancers, zookeper to manage incoming requests
- Take care of concurrency by making requests thread safe
- If we have millions of transactions, we should start looking into queuing mechanisms for posting, schedulers for hard deleting messages which can be removed.
- Async implementation should be used. Transactional operations can take more time when having millions of transactions
- CDNs can be used for different demographics
- Caching could help increase speed of retrievals
- Monitoring of service on production using splunk alerts, datadog can keep you notified of issues
- Good testing framework covering all bases such as integration, regression, load, performance

