# LOAN CALCULATOR
Loan Calculator  is a Java-based backend project that handles loan calculations based on user input from the [Loan Calculator  frontend](https://github.com/MURATKAYMAZ56/loan_calculator-frontend) application. It exposes RESTful APIs for receiving loan details, performing calculations, and providing **Equated Monthly Instalment (EMI)** information.

**(EMI**) is getting calculated based on  below formula

$`
                𝐸𝑀𝐼 = \frac{(𝑃 𝑥 𝑅 𝑥 (1 + 𝑅)^N )}{((1 + 𝑅)^N − 1)}
`$

- “P” is the loan amount
- “N” is tenure in months
- “R” is the monthly interest rate.
## Project Structure
The project follows the Maven standard directory structure. Key components include:

- `src/main/java`: Contains the Java source code.
- `src/main/resources`: Contains application properties and configurations.
- `src/test`: Contains test classes.

## Getting Started
To run the project locally, follow these steps:
1. Ensure that Java 17 is installed on your machine. You can download it from [here](https://www.oracle.com/java/technologies/downloads/#java17).
2. Clone this repository:
```
    git clone https://github.com/MURATKAYMAZ56/loan_calculator-backend.git
```
3. Navigate to the project directory:
```
    cd loan_calculator-backend
```
4. Build the project:
```
    ./mvnw clean install
```
5. Run the application:
```
    ./mvnw spring-boot:run
```
6. The backend application will be accesiable at `http://localhost:8080`
## Integration with Loan Calculator Frontend application
Loan Calculate backend  is designed to work seamlessly with the [Loan Calculate frontend](https://github.com/MURATKAYMAZ56/loan_calculator-frontend) React application. The integration is achieved through RESTful APIs. The frontend sends loan details to backend, and backend responds with calculated EMI information.

You can either run frontend project by following [its](https://github.com/MURATKAYMAZ56/loan_calculator-frontend) configuration steps or use postman as below to calculate emi.

## Making API Calls via Postman
You can use Postman to make API calls to the backend. Follow these steps:
1. Open Postman and create a new request.

2. Set the request type to POST.

3. Enter the API endpoint: http://localhost:8080/api/loans

4. Set the request headers:
   `Content-Type: application/json`
5. In the request body, provide a JSON payload with loan details. For example:
```
{
  "amount": 1000,
  "instalment": 11,
  "interestRate": 2.4,
  "email": "test@example.com"
}


```
6. Click the "Send" button to make the API call. Then you should see api response as below.
```
{
    "emi": 231.11
}
```

7. Review the response in H2 console (See below for H2 console) to see the calculated EMI information.
## Accessing H2 Console
The Loan Calculator backend uses an H2 in-memory database, and you can access the H2 Console to view and query the data.
1. Open your browser and go to `http://localhost:8080/h2-console`.
2. In the login page, set the following values:
```
    JDBC URL: jdbc:h2:mem:testdb
    User Name: sa
    Password: pwd
```
3.Click the "Connect" button.
4. You will be redirected to the H2 Console. Here, you can view and execute SQL queries against the in-memory database.
5. To see data Click on `LOAN_ENTITY` table and run below script
```
    SELECT * FROM LOAN_ENTITY 
```
## Running Tests
To run tests, execute the following command:
```
    ./mvnw test
```
