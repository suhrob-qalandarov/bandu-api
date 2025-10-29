``` markdown
# Bandu App - Backend API

RESTful API for place booking management system built with Spring Boot.

## 🚀 Tech Stack

- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA** - Database operations
- **Spring Security** - Authentication & Authorization
- **JWT** - Token-based authentication
- **Lombok** - Boilerplate code reduction
- **Swagger/OpenAPI** - API documentation
- **PostgreSQL** - Database (configure in application.properties)
- **Maven** - Dependency management

## 📋 Features

### User Features
- ✅ User registration and authentication (JWT)
- ✅ Browse available places
- ✅ Create bookings with time slot validation
- ✅ View personal booking history
- ✅ Booking conflict detection

### Admin Features
- ✅ Manage places (CRUD operations)
- ✅ Manage users
- ✅ View and manage all bookings
- ✅ Place visibility control

### System Features
- ✅ Role-based access control (USER/ADMIN)
- ✅ Audit logging (created/updated timestamps)
- ✅ Request logging filter
- ✅ JWT token validation
- ✅ Booking expiration handling
- ✅ Price calculation based on hourly rates

```
``` markdown
## 🏗️ Project Structure
src/main/java/org/exp/banduapp/ 
├── config/ # Configuration classes 
│ 
├── advice/ # Global exception handlers 
│ ├── audit/ # JPA auditing config 
│ ├── mockinit/ # Initial data loading 
│ ├── security/ # Security & JWT configuration 
│ └── swagger/ # API documentation config 
├── controller/ # REST endpoints 
│ ├── admin/ # Admin-only endpoints 
│ ├── AuthController # Authentication endpoints 
│ ├── BookingController # User booking operations 
│ └── PlaceController # Place browsing 
├── models/ 
│ ├── base/ # Base entities 
│ ├── dto/ # Request/Response DTOs 
│ ├── entities/ # JPA entities 
│ └── enums/ # Enumerations 
├── repository/ # Spring Data repositories 
├── service/ 
│ ├── face/ # Service interfaces 
│ └── impl/ # Service implementations 
└── util/ # Utility classes``` 
``` 

## 🔧 Setup & Installation

### Prerequisites
- Java 21 or higher
- Maven 3.8+
- PostgreSQL database
- (Optional) Docker

### 1. Clone the repository
```bash
git clone https://github.com/suhrob-qalandarov/bandu-api
cd bandu-app
```
```

2. Configure database
Edit src/main/resources/application.properties:
# App
spring.application.name=bandu-app

# Datasource
spring.datasource.url=jdbc:postgresql://localhost:5432/bandu_app_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driverClassName=org.postgresql.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# JWT Configuration
jwt.secret.key=your-very-secure-secret-key-must-be-at-least-32-bytes-long!!!
jwt.token.lifetime.days=7

# Server Configuration
server.port=8080
```

3. Run the application
Using Maven:
./mvnw spring-boot:run
```
Or build and run:
./mvnw clean package
java -jar target/bandu-app-0.0.1-SNAPSHOT.jar
```

4. Access the application:

``` markdown
  API Base URL: http://localhost:8080/api
  Swagger UI: http://localhost:8080/swagger-ui.html
  API Docs: http://localhost:8080/v3/api-docs
```
  
📚 API Endpoints:

Authentication``` 
POST   /api/v1/auth/register     - Register new user
POST   /api/v1/auth/login        - Login and get JWT token
GET    /api/v1/auth/me           - Get current user info
```

Places (Public)``` 
GET    /api/v1/places            - Get all active places
GET    /api/v1/places/{id}       - Get place by ID
```

Bookings (Authenticated)``` 
GET    /api/v1/bookings          - Get user's bookings
POST   /api/v1/bookings          - Create new booking
```

Admin - Places``` 
GET    /api/v1/admin/places      - Get all places
POST   /api/v1/admin/places      - Create new place
PUT    /api/v1/admin/places/{id} - Update place
DELETE /api/v1/admin/places/{id} - Delete place
```

Admin - Users``` 
GET    /api/v1/admin/users       - Get all users
PUT    /api/v1/admin/users/{id}  - Update user
DELETE /api/v1/admin/users/{id}  - Delete user
```

Admin - Bookings``` 
GET    /api/v1/admin/bookings    - Get all bookings
PUT    /api/v1/admin/bookings/{id}/status - Update booking status
```

🔐 Authentication
The API uses JWT (JSON Web Token) for authentication.
Getting a token:``` bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "phoneNumber": "+998901234567",
    "password": "password123"
  }'
```

Using the token:``` bash
curl -X GET http://localhost:8080/api/bookings \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

📊 Database Schema
Main Entities
User - System users (customers & admins)
Role - User roles (USER, ADMIN, SUPER_ADMIN)
Place - Bookable venues/places
Booking - Reservation records
Key Relationships
User → Role (Many-to-Many)
Booking → User (Many-to-One)
Booking → Place (Many-to-One)
🧪 Testing``` bash
# Run all tests
./mvnw test

# Run with coverage
./mvnw test jacoco:report
```

🐳 Docker Support``` dockerfile
# Dockerfile example
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build and run:``` bash
./mvnw clean package
docker build -t bandu-app .
docker run -p 8080:8080 bandu-app
```

🔧 Configuration Options
Property:                               Description:                  Default:
 server.port                             Server port                   8080
 jwt.secret                              JWT signing key               your-secure-secret-key
 jwt.expiration                          Token validity (day)          7
spring.jpa.hibernate.ddl-auto            Schema generation             update


📝 Business Rules
1 Bookings:
 - Minimum booking duration: 1 hour
 - Bookings expire 15 minutes before start time
 - No overlapping bookings for same place
 - Price calculated by: hours × place.pricePerHour
 
2 Places:
 - Only ACTIVE places can be booked
 - Visibility controls public listing
 
3 Users:
 - Phone number must be unique
 - Default role: USER
 - Admin role required for admin endpoints
 
🛠️ Development
Code Style
 - Follow Java naming conventions
 - Use Lombok annotations for boilerplate code
 - Service layer pattern (interface + implementation)
 - DTO pattern for API requests/responses
Adding New Features
 - Create entity in models/entities
 - Create repository in repository
 - Create service interface in service/face
 - Implement service in service/impl
 - Create controller in controller
 - Add DTOs in models/dto
 
🤝 Contributing
1.Fork the repository
2.Create feature branch (git checkout -b feature/amazing-feature)
3.Commit changes (git commit -m 'Add amazing feature')
4.Push to branch (git push origin feature/amazing-feature)
5.Open Pull Request

📄 License Apache.license
👤 Author: Suhrob-Qalandarov
📞 Support: For issues and questions, please open an issue on GitHub.
👥 LinkedIn: https://www.linkedin.com/in/suhrob-qalandarov``` 
```
