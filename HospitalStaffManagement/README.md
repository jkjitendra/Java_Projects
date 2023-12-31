# Hospital Management System 🏥

## Overview
The Hospital Management System is a Java-based web application designed to manage various hospital operations. This system simplifies the workflow for hospital staff and provides an intuitive interface for managing patient admissions, billing, and staff authentication.

## Features

### 🩺 Patient Management
- Admitting and discharging patients.
- Retrieving patient details and managing their records.

### 💊 Billing System
- Handling billing details of patients.
- Viewing pending bills and total amounts.

### 👩‍⚕️ Staff Authentication
- Secure login and registration for hospital staff.

### 📊 Reporting
- Comprehensive reports on patient and hospital data.

## API Endpoints <img src="https://cdn-icons-png.flaticon.com/512/2164/2164832.png" width="54px" height="40px" />

Below are some of the key API endpoints:

- `POST /api/auth/signup` - For staff signup.
- `POST /api/auth/login` - For staff login.
- `POST /api/hospital/admit` - To admit a new patient.
- `GET /api/hospital/patients` - To get all admitted patients.
- More endpoints are detailed in the Postman collection provided.

## Postman Collection  <img src="https://www.vhv.rs/dpng/d/571-5718602_transparent-ubuntu-logo-png-logo-postman-icon-png.png" width="20px" height="20px" />

The Postman collection for the API, named `HospitalManagement APIs.postman_collection.json`, is included in the project. It provides an easy way to test and explore the functionalities of the API.

To use the collection:
1. Open Postman.
2. Go to 'Import' and select the file `HospitalManagement APIs.postman_collection.json` from the project directory.
3. Explore the various API endpoints.

This collection contains pre-configured requests for all the API endpoints in the system, making it straightforward to test each feature.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

- Java 11  <img src="https://static-00.iconduck.com/assets.00/java-icon-2048x2048-yxty4s2p.png" width="20px" height="20px"/>
- Maven    <img src="https://onurdesk.com/wp-content/uploads/2021/05/kisspng-apache-maven-apache-ant-gradle-apache-http-server-apache-maven-5b194e571b97f1.295739891528385111113.jpg" width="20px" height="20px"/>

### Installation

1. Clone the repo
   ```
   git clone https://github.com/jkjitendra/Java_Projects
   ```
2. Change to the project directory
   ```
   cd HospitalManagementSystem
   ```
3. Install Maven dependencies
   ```
    mvn install
   ```
4. Run the application
   ```
   mvn spring-boot:run
   ```

## Contributing
Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are <b>greatly appreciated.</b>

1. Fork the Project
2. Create your Feature Branch (git checkout -b feature/AmazingFeature)
3. Commit your Changes (git commit -m 'Add some AmazingFeature')
4. Push to the Branch (git push origin feature/AmazingFeature)
5. Open a Pull Request

## Contact
Jitendra Tiwari - jitendrakumartiwari849@gmail.com
