# RetailStoreProductSync

## Overview
RetailStoreProductSync is a Java-based application designed to synchronize product data from an external API into a local MySQL database.
This solution is ideal for retail businesses that require up-to-date product information across various platforms, ensuring consistency and accuracy in product management.

## Development
Write a Java program that retrieves product information from the 'https://fakestoreapi.com/products" API, and stores the data in a local MySQL database.
The program should be able to handle large volumes of data and should be designed to be easily maintainable and extensible.
The program should also implement error handling and logging to ensure that any errors or exceptions are properly handled and logged.

#### Steps to be followed:

- Start by creating a Java class that will act as the main program.
- Import the necessary Java libraries, such as the JDBC library to interact with the MySQL database and the HTTP library to retrieve data from the external API.
- Define the necessary variables, such as the database connection information, the API endpoint, and the table structure for the product information.
- Establish a connection to the MySQL database using the JDBC library.
- Retrieve the product information from the API using the HTTP library and parse the response into Java objects.
- Iterate through the product data and insert it into the MySQL database using SQL statements.
- Implement error handling and logging to ensure that any exceptions or errors are properly handled and logged.
- Test the program with a small amount of data and then gradually increase the volume of data to ensure that the program is scalable and can handle large volumes of data.
- Finally, refactor the code to improve maintainability and extensibility. For example, you can modularize the code into separate classes or methods for better organization and readability.

## Features
- **Data Fetching:** Automatically fetches product data from `https://fakestoreapi.com/products`.
- **Data Storage:** Stores fetched data in a local MySQL database.
- **Auto-Generated IDs:** Utilizes AUTO_INCREMENT for primary key management in the database.
- **Error Handling:** Robust error handling to manage and log connectivity or data integrity issues.
- **Security:** Ensures secure database connections and safe data handling practices.

## Prerequisites
- Java JDK 11 or newer
- MySQL Server 5.7 or newer
- Maven 3.6.3 or newer (for dependency management)

## Installation
1. **Clone the repository:**

        git clone https://github.com/jkjitendra/Java_Projects.git

2. **Navigate to the project directory:**
        
        cd RetailStoreProductSync

3. **Install dependencies:**

        mvn install


## Configuration
1. **Database Configuration:**
   - Ensure that MySQL is installed and running.
   - Create a database named `retailStore`.
   - Import the initial schema available at `src/main/resources/sql/schema.sql`.

2. **Application Configuration:**
   - Modify the `src/main/resources/application.properties` file to include the correct database URL, user, and password.

## Running the Application
To run the application, execute:

    java -jar target/RetailStoreProductSync.jar

## Usage
Once the application is running, it will automatically fetch product data from the API and insert it into the configured MySQL database. The process logs are available in the console for monitoring and troubleshooting.

## Contributing
Contributions are welcome. Please fork the repository and submit pull requests to the main branch. For major changes, please open an issue first to discuss what you would like to change.

## Contact
Your Name - Jitendra Kumar Tiwari <br/>
Project Link: https://github.com/jkjitendra/Java_Projects/RetailStoreProductSync

## Acknowledgements
- [Fake Store API](https://fakestoreapi.com)
- [MySQL](https://www.mysql.com)
- [Apache HttpClient](https://hc.apache.org/httpcomponents-client-5.1.x/index.html)




