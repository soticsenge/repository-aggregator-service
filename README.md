# repository-aggregator-service
Aggregates repos from other 2 service


**Run in container:**


*./mvnw compile package*

*docker build --tag=aggregator .*

*docker run aggregator -p 8083:8000*

**Run tests:**

*./mvnw test*
