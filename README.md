# Clustered Data Warehouse

Clustered Data Warehouse is a project developed as part of a Scrum team for Bloomberg to analyze FX (Foreign Exchange) deals. The primary goal of this project is to accept deal details from various sources and persist them into a database, making the data available for further analysis.

## Project Description

### Request Logic

The project handles incoming FX deal requests with the following key fields:

- Deal Unique Id
- From Currency ISO Code (Ordering Currency)
- To Currency ISO Code
- Deal Timestamp
- Deal Amount in Ordering Currency

The request data is subject to validation, which includes checking for missing fields and ensuring the correct data types. While the project may not cover all possible validation cases, it demonstrates how to implement such validations as shown below.

![image](https://github.com/AkramJaghoub/clustered-data-warehouse/assets/105783739/e4fb5e31-695a-4104-8d88-3be5caeb789e)


### Key Features

- The system ensures that the same deal is not imported more than once.
- No rollback is allowed, meaning that once a deal is imported, it is saved in the database.
- The project provides a workable deployment environment using Docker Compose.
- The source code is organized as a Maven or Gradle project.
- Proper error and exception handling mechanisms are implemented.
- Comprehensive logging is in place to capture relevant information.
- Unit testing is performed, and code coverage is maintained to ensure the correctness of the application.
- The project is hosted on GitHub for collaboration and version control.

## Getting Started

To get started with the Clustered Data Warehouse project, follow these steps:

1. **Clone the Project**: Clone the project repository from GitHub.

    ```shell
    git clone https://github.com/AkramJaghoub/clustered-data-warehouse.git
    ```



2. **Build the Project**: Build the project using Maven or Gradle, depending on your preference.

   Using Maven:

    ```shell
    mvn clean install package
    ```

   Using Gradle:

    ```shell
    ./gradlew clean build
    ```


3. **Run the Application with Docker Compose**: Use Docker Compose to start the application and the associated database container.

    ```shell
    docker-compose up
    ```

   Docker Compose will start the necessary containers and link them together to create a fully operational environment.


4. **Access the Endpoints**:

- **Save a Deal**:

  Endpoint: `POST /api/save-deal`

  Use this endpoint to save a new FX deal. Send a POST request with a valid JSON body, for example:

    ```json
    {
      "dealId": 2,
      "fromCurrency": "USD",
      "toCurrency": "JOD",
      "dealTimestamp": "2023-10-04T12:34:56",
      "dealAmount": 50.25
    }
    ```

  **Sample Output**:

    ```json
    {
        "message": "Deal with ID 2 has been saved successfully",
        "statusCode": "CREATED"
    }
    ```

- **Get All Deals**:

  Endpoint: `GET /api/all-deals`

  Use this endpoint to retrieve all FX deals. Send a GET request to this endpoint.

  **Sample Output**:

    ```json
    {
        "message": "Deals retrieved successfully",
        "statusCode": "OK",
        "data": [
            {
                "dealId": 1,
                "fromCurrency": "USD",
                "toCurrency": "JOD",
                "dealTimestamp": "2023-10-04T12:34:56",
                "dealAmount": 50.25
            },
            {
                "dealId": 2,
                "fromCurrency": "USD",
                "toCurrency": "JOD",
                "dealTimestamp": "2023-10-04T12:34:56",
                "dealAmount": 50.25
            }
        ]
    }
    ```

- **Get a Deal by ID**:

  Endpoint: `GET /api/fetch-deal/{deal_id}`

  Use this endpoint to retrieve a specific FX deal by its unique ID. Replace `{deal_id}` with the actual deal ID you want to fetch.

  **Sample Output**:

    ```json
    {
        "message": "Deal with ID 2 has been retrieved successfully",
        "statusCode": "OK",
        "data": {
            "dealId": 2,
            "fromCurrency": "USD",
            "toCurrency": "JOD",
            "dealTimestamp": "2023-10-04T12:34:56",
            "dealAmount": 50.25
        }
    }
    ```


5. **Testing in Postman**: To test the endpoints in Postman, ensure that you have Postman installed correctly. If you encounter issues with the Postman path, open Postman manually and import the provided [Postman Collection](https://www.postman.com/maintenance-specialist-51211780/workspace/data-warehouse-deals/request/29497652-a5626522-b54b-4b86-b9d5-7192db0c3271). You can run requests, pass JSON data, and examine responses using Postman's user-friendly interface.

   To make testing easier, you can use the following steps:

   - Click the "Run in Postman" button below to import the provided Postman Collection.

     [![Run in Postman](https://run.pstmn.io/button.svg)](https://www.postman.com/maintenance-specialist-51211780/workspace/data-warehouse-deals/request/29497652-a5626522-b54b-4b86-b9d5-7192db0c3271)

   - Postman will open automatically, and you will see the imported collection on the left sidebar.

   - You can now run requests, pass JSON data, and examine responses using Postman's user-friendly interface.

   Feel free to explore and interact with the application's endpoints to manage FX deals efficiently.

## Future Works

While this project focuses on using MongoDB for simplicity, there are considerations for using transactional databases like PostgreSQL or MySQL in the future:

- **Transactional Consistency**: In the financial domain, ensuring transactional consistency is critical. PostgreSQL and MySQL provide support for ACID transactions, making them better suited for handling financial data where data integrity is paramount.

- **Rollback Capability**:
  In MongoDB, the "fire-and-forget" approach means that once data is acknowledged as written, it's considered saved in the database without guaranteeing immediate disk storage or supporting individual rollbacks. This fits the project's need to preserve imported data even if subsequent operations fail. However, MongoDB lacks traditional transactional rollbacks, making it essential to consider alternatives like PostgreSQL or MySQL with ACID transactions for strict consistency requirements.
- **Data Integrity**: Transactional databases offer robust mechanisms for ensuring data integrity through constraints, foreign keys, and data validation rules.

In future versions of this project, transitioning to a transactional database may be considered to better align with the requirements and business logic of handling financial transactions.
