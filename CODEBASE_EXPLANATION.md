# Scigenics ERP - Codebase Explanation

## Table of Contents
1. [Project Overview](#project-overview)
2. [Technology Stack](#technology-stack)
3. [Architecture & Design Patterns](#architecture--design-patterns)
4. [Project Structure](#project-structure)
5. [Core Modules](#core-modules)
6. [Key Components](#key-components)
7. [Database Layer](#database-layer)
8. [Workflow Management](#workflow-management)
9. [Scheduled Jobs](#scheduled-jobs)
10. [Configuration Files](#configuration-files)
11. [Getting Started](#getting-started)

---

## Project Overview

**Scigenics ERP** is an Enterprise Resource Planning (ERP) system built as a Java web application. This system manages various business processes including:

- **Marketing & Sales**: Enquiries, proposals, work orders, and project tracking
- **Material Management**: Material indents (MI), purchase orders (PO), inventory management
- **Store Management**: Store requests, material issue/receipt, inventory tracking
- **Design & Engineering**: Design document management, drawing management
- **Quality Control (QC)**: Quality inspection, rejection reports
- **Project Management**: Project tracking, cost estimation, progress monitoring
- **User & Role Management**: User authentication, role-based access control
- **Task Management**: Task assignment, escalation, and tracking

The application follows a traditional enterprise architecture with clear separation of concerns across layers.

---

## Technology Stack

### Core Framework
- **Spring Framework 5.0.5**: Dependency injection, MVC, transaction management
- **Spring Boot 2.0.1**: Application configuration and embedded server support
- **Spring WebFlow 2.5.0**: Workflow and conversation management
- **Hibernate 5.2.16**: ORM for database operations
- **Spring Data JPA 2.0.1**: Data access abstraction

### View Layer
- **JSP (JavaServer Pages)**: Server-side templating
- **Apache Tiles 3.0.0**: Page composition framework
- **JSTL 1.2**: JSP Standard Tag Library
- **Ajax Tags**: AJAX functionality in JSP pages
- **DisplayTag 1.2**: Table display and pagination

### Reporting & Document Generation
- **iText 5.0.6**: PDF generation
- **Apache POI 3.7**: Excel document generation
- **Apache FOP 1.0**: XSL-FO processing for advanced PDF reports
- **JasperReports**: Report generation (workorder.jrxml)

### Utilities & Integration
- **Quartz Scheduler 1.7.3**: Job scheduling
- **Google API Client 1.23.0**: Gmail integration
- **Commons FileUpload 1.3.1**: File upload handling
- **Commons IO 2.4**: I/O utilities
- **Log4j 2.7**: Logging framework

### Database
- **Oracle Database**: Using OJDBC7 driver (version 12.0.0)

### Build Tool
- **Maven**: Dependency management and build automation
- **Java 8**: Target compilation version

---

## Architecture & Design Patterns

The application follows a **layered architecture** with clear separation of concerns:

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│  (JSP, Tiles, Controllers, WebFlow)     │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│         Service Layer                   │
│  (Business Logic, Validation)           │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│         DAO Layer                       │
│  (Data Access Objects, Repositories)    │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│         Database Layer                  │
│  (Oracle DB, JPA Entities)              │
└─────────────────────────────────────────┘
```

### Design Patterns Used

1. **MVC (Model-View-Controller)**: Spring MVC framework
2. **DAO Pattern**: Data access abstraction
3. **Service Layer Pattern**: Business logic encapsulation
4. **Dependency Injection**: Spring IoC container
5. **Template Pattern**: JdbcTemplate, HibernateTemplate
6. **Factory Pattern**: Spring bean factories
7. **Strategy Pattern**: Validators, custom editors
8. **Front Controller Pattern**: DispatcherServlet
9. **Session Facade**: Service interfaces
10. **Value Object (VO/DTO)**: Command beans for data transfer

---

## Project Structure

```
scigenicserp/
├── pom.xml                          # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/sci/bpm/
│   │   │       ├── chart/           # Charting models
│   │   │       ├── command/         # Command/DTO objects
│   │   │       │   ├── bulletin/
│   │   │       │   ├── design/
│   │   │       │   ├── login/
│   │   │       │   ├── marketing/
│   │   │       │   ├── mi/
│   │   │       │   ├── po/
│   │   │       │   ├── proj/
│   │   │       │   ├── qc/
│   │   │       │   ├── role/
│   │   │       │   ├── stores/
│   │   │       │   ├── task/
│   │   │       │   └── user/
│   │   │       ├── constants/       # Application constants
│   │   │       ├── controller/      # MVC Controllers
│   │   │       │   ├── base/        # Base controller classes
│   │   │       │   ├── bulletin/
│   │   │       │   ├── design/
│   │   │       │   ├── item/
│   │   │       │   ├── login/
│   │   │       │   ├── lookup/
│   │   │       │   ├── marketing/
│   │   │       │   ├── mi/
│   │   │       │   ├── po/
│   │   │       │   ├── proj/
│   │   │       │   ├── qc/
│   │   │       │   ├── role/
│   │   │       │   ├── stores/
│   │   │       │   ├── task/
│   │   │       │   └── user/
│   │   │       ├── dao/             # Data Access Objects
│   │   │       │   └── [modules]/
│   │   │       ├── db/
│   │   │       │   └── model/       # JPA Entity classes
│   │   │       ├── flow/            # WebFlow builders
│   │   │       ├── service/         # Business logic services
│   │   │       │   └── [modules]/
│   │   │       ├── validator/       # Input validators
│   │   │       └── view/
│   │   │           └── resolver/    # View resolvers
│   │   ├── resources/
│   │   │   ├── META-INF/
│   │   │   │   └── persistence.xml  # JPA configuration
│   │   │   ├── message.properties   # i18n messages
│   │   │   ├── log4j2.xml          # Logging config
│   │   │   ├── credentials.json     # Google API credentials
│   │   │   └── [query files]        # SQL/HQL queries
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── web.xml          # Servlet configuration
│   │       │   ├── applicationContext.xml   # Spring context
│   │       │   ├── springtest-servlet-config.xml
│   │       │   ├── springtest-webflow-config.xml
│   │       │   ├── flows/           # Spring WebFlow definitions
│   │       │   ├── jsp/             # JSP view files
│   │       │   └── defs/            # Tiles definitions
│   │       └── [static resources]
│   └── test/                        # Test classes
└── target/                          # Build output
```

---

## Core Modules

The application is organized into functional modules, each handling specific business domains:

### 1. **Login Module** (`login`)
- **Purpose**: User authentication and session management
- **Key Components**:
  - `LoginFormController`: Handles login form submission
  - `LoginService`: Authentication logic
  - `LoginValidator`: Input validation
- **Features**: Multi-role login support, session management

### 2. **Marketing Module** (`marketing`)
- **Purpose**: Sales and project proposal management
- **Key Components**:
  - `EnquiryController`: Customer enquiry management
  - `ProposalController`: Proposal creation and tracking
  - `WorkOrderController`: Work order management
  - `FermJobController`: Fermentation job tracking
  - `JDRMasterController`: Job delivery report management
- **Key Entities**: 
  - `SciProposalDetailsEntity`
  - `SciWorkorderMaster`
  - `SciFermJobDetails`

### 3. **Material Indent (MI) Module** (`mi`)
- **Purpose**: Material requisition and indent management
- **Key Components**:
  - `MaterialIndentController`: Material indent creation/approval
  - `MaterialStoresRequestController`: Store request management
  - `SpritJOBScheduleController`: Sprint job scheduling
- **Key Entities**:
  - `SciMatindMaster`
  - `SciStoreMiMaster`
  - `SciItemmiDetails`

### 4. **Purchase Order (PO) Module** (`po`)
- **Purpose**: Purchase order creation and vendor management
- **Key Components**:
  - `PurchaseOrderController`: PO lifecycle management
  - `POFopProcessorServlet`: PDF generation for POs
  - `POEmailProcessorServlet`: Email PO to vendors
- **Key Entities**:
  - `SciPurchaseMast`
  - `SciPurchaseItemdetails`
  - `SciVendorInvoiceMaster`

### 5. **Stores Module** (`stores`)
- **Purpose**: Inventory and warehouse management
- **Key Components**:
  - `StoresManagerController`: Store operations
  - `StoreService`: Inventory business logic
- **Key Entities**:
  - `SciStoreMaster`
  - `SciStoreissueMaster`
  - `SciStoresRequest`
  - `SciIssueMaster`
  - `SciIssueDetails`

### 6. **Design Module** (`design`)
- **Purpose**: Engineering design and drawing management
- **Key Components**:
  - `DesignDocumentController`: Design document management
  - `DocumentStreamServlet`: Document streaming/download
- **Key Entities**:
  - `SciDesignDocMaster`
  - `SciDrwingDetails`
  - `SciDrawingRef`

### 7. **Quality Control (QC) Module** (`qc`)
- **Purpose**: Quality inspection and rejection management
- **Key Components**:
  - `QualityController`: QC operations
  - `QualityDocOpener`: QC document viewer
- **Key Entities**:
  - `SciQcMaster`
  - `SciQcMiMaster`
  - `SciRejectedMaterials`
  - `SciDamagedMaterials`

### 8. **Project Tracking Module** (`proj`)
- **Purpose**: Project cost estimation and progress tracking
- **Key Components**:
  - `ProjTrackController`: Project tracking
- **Key Entities**:
  - `SciProjectCostMaster`

### 9. **Task Management Module** (`task`)
- **Purpose**: Task assignment, tracking, and escalation
- **Key Components**:
  - `TaskProcessController`: Task workflow management
- **Scheduled Jobs**:
  - `EscalationService`: Task escalation
  - `EmailWriterJob`: Email notifications

### 10. **Item/Product Module** (`item`, `product`)
- **Purpose**: Master item and product catalog management
- **Key Components**:
  - `ITemMasterController`: Item master management
- **Key Entities**:
  - `SciMasterItem`
  - `SciProductMaster`
  - `SciMatspecMaster`
  - `SciMatcatMaster`
  - `SciMattypeMaster`

### 11. **User & Role Module** (`user`, `role`)
- **Purpose**: User and role-based access control
- **Key Components**:
  - `UserMasterController`: User management
  - `RoleMasterController`: Role management
- **Key Entities**:
  - `ScigenicsUserMaster`
  - `ScigenicsRoleMaster`
  - `SciUserStateMasterEntity`

### 12. **Lookup Module** (`lookup`)
- **Purpose**: Master data and lookup values
- **Key Components**:
  - `LookupValueController`: Lookup management
- **Key Entities**:
  - `SciLookupMaster`
  - `SciStateCityMasterEntity`

### 13. **Bulletin Board Module** (`bulletin`)
- **Purpose**: Internal announcements and notifications
- **Key Components**:
  - `BulletinController`: Bulletin management
- **Key Entities**:
  - `SciBulletinBoard`

---

## Key Components

### Controllers
Controllers handle HTTP requests and coordinate between views and services. The base controller is `SciBaseController`, which provides common functionality.

**Example Controllers**:
```java
@Controller("loginController")
public class LoginFormController extends SciBaseController {
    @Autowired
    private LoginService loginService;
    
    public Event processLogin(RequestContext context) {
        // Handle login logic
    }
}
```

### Services
Services contain business logic and are transactional. They act as a facade to the DAO layer.

**Naming Convention**:
- Interface: `[Module]Service` (e.g., `LoginService`)
- Implementation: `[Module]ServiceImpl` (e.g., `LoginServiceImpl`)

### DAOs (Data Access Objects)
DAOs handle database operations using JPA/Hibernate. They extend or use Spring Data JPA repositories.

### Command Beans (DTOs)
Command beans are data transfer objects that carry data between layers. They are located in the `command` package.

### Validators
Input validation classes that validate form submissions before processing.

**Example**: `LoginValidator` validates login credentials

### View Resolvers
Custom view resolvers for specific rendering needs.

---

## Database Layer

### JPA Configuration
- **Persistence Unit**: `ScigenicsDBPU` (defined in `persistence.xml`)
- **Provider**: Hibernate JPA
- **Database**: Oracle Database (OJDBC7 driver)
- **Transaction Type**: RESOURCE_LOCAL

### Entity Naming Convention
All entities follow the pattern: `Sci[ModuleName]Master` or `Sci[ModuleName]Entity`

**Examples**:
- `ScigenicsUserMaster`: User entity
- `SciWorkorderMaster`: Work order entity
- `SciMatindMaster`: Material indent entity

### Key Database Tables (80+ entities)
The system has approximately **80 JPA entities** representing:
- Master data tables (users, roles, items, products)
- Transaction tables (work orders, material indents, purchase orders)
- Detail tables (work order details, indent details, issue details)
- Supporting tables (lookups, bulletins, tasks)

---

## Workflow Management

The application uses **Spring WebFlow** for managing complex multi-step workflows.

### WebFlow Configuration
- **Config File**: `springtest-webflow-config.xml`
- **Flow Definitions**: Located in `/WEB-INF/flows/`
- **Total Flows**: Approximately **80 workflow definitions**

### Example Workflows
1. **Login Flow**: Multi-step authentication with role selection
2. **Material Indent Flow** (`approvemi-flow.xml`): Material requisition approval
3. **Work Order Flow** (`viewworkorder-flow.xml`): Work order viewing/editing
4. **Design Approval Flow** (`approvedesign-flow.xml`): Design document approval
5. **QC Rejection Flow** (`qc-rejectionreport-flow.xml`): Quality rejection reporting
6. **Project Estimate Flow** (`addProjEstimate-flow.xml`): Project cost estimation

### WebFlow Benefits
- **Conversation Management**: Maintains state across multiple requests
- **Navigation Control**: Declarative flow definitions
- **Validation Integration**: Built-in validation support
- **Modular Design**: Each flow is self-contained

---

## Scheduled Jobs

The application uses **Quartz Scheduler** for background job execution.

### Configured Jobs

1. **Escalation Service** (`EscalationService`)
   - **Trigger**: Simple trigger, repeats every 5 minutes (300,000 ms)
   - **Purpose**: Escalate pending tasks based on SLA
   - **Start Delay**: 10 seconds

2. **Report Count Service** (`ReportCountService`)
   - **Trigger**: Cron trigger - daily at 12:00 PM
   - **Cron Expression**: `0 0 12 * * ?`
   - **Purpose**: Generate daily report counts

3. **Report Query Service** (`ReportQueryService`)
   - **Trigger**: Cron trigger - daily at 12:00 PM
   - **Cron Expression**: `0 0 12 * * ?`
   - **Purpose**: Execute daily report queries

4. **Stores Request Suppress Job** (`StoresRequestSuppressJob`)
   - **Trigger**: Cron trigger - daily at 12:00 PM
   - **Cron Expression**: `0 0 12 * * ?`
   - **Purpose**: Clean up or suppress old store requests

5. **Email Writer Job** (`EmailWriterJob`)
   - **Trigger**: Cron trigger - every 50 minutes
   - **Cron Expression**: `0 0/50 * * * ?`
   - **Purpose**: Process pending emails and send notifications
   - **Integration**: Uses Gmail API for sending emails

6. **Weekly Report Query Service** (`ReportQueryWeeklyService`)
   - **Trigger**: Cron trigger - every Sunday at 12:00 PM
   - **Cron Expression**: `0 0 12 ? * SUN *`
   - **Purpose**: Generate weekly reports

### Job Dependencies
- Jobs access services via dependency injection
- **DiskWriterJob**: Writes files to external storage server
  - Host: `192.168.1.115`
  - Port: `32771`
  - Location: `/home/scigenics/datafolder/`

---

## Configuration Files

### 1. **web.xml** (Servlet Configuration)
- Defines servlets and mappings
- Configures Spring DispatcherServlet
- Sets up filters (DisplayTag filter)
- Session timeout: 30 minutes

### 2. **applicationContext.xml** (Spring Application Context)
- Component scanning: `com.sci.bpm`
- JPA configuration
- Transaction management
- Quartz scheduler setup
- Scheduled job definitions
- Message source for i18n

### 3. **springtest-servlet-config.xml** (Spring MVC Configuration)
- Controller mappings
- View resolvers
- Interceptors
- Handler mappings

### 4. **springtest-webflow-config.xml** (WebFlow Configuration)
- Flow registry
- Flow executor
- Flow builders

### 5. **persistence.xml** (JPA Configuration)
- Persistence unit: `ScigenicsDBPU`
- Entity class registrations
- Database connection properties
- Hibernate settings

### 6. **jdbc.properties**
- Database connection parameters
- Located in `/WEB-INF/`

### 7. **log4j2.xml**
- Logging configuration
- Log levels and appenders

### 8. **message.properties**
- Internationalization messages
- Error messages
- Labels and UI text

---

## Getting Started

### Prerequisites
- **Java 8** or higher
- **Maven 3+**
- **Oracle Database** (configured and running)
- **Application Server**: Tomcat 8+ or any Java EE container

### Build the Project
```bash
mvn clean install
```

### Configure Database
1. Update database connection properties in:
   - `src/main/resources/META-INF/persistence.xml`
   - `src/main/webapp/WEB-INF/jdbc.properties`

2. Ensure Oracle database is accessible and credentials are correct

### Deploy
```bash
# Build WAR file
mvn clean package

# Deploy to Tomcat
cp target/scigenicserp.war $TOMCAT_HOME/webapps/
```

### Access the Application
```
http://localhost:8080/scigenicserp/
```

### Default Login Flow
1. Navigate to the application URL
2. Enter credentials
3. Select role (multi-role support)
4. System redirects to role-specific dashboard

---

## Key Features Summary

### Business Features
- ✅ Multi-role user management
- ✅ Material indent and approval workflow
- ✅ Purchase order generation and email integration
- ✅ Inventory/store management
- ✅ Quality control and inspection
- ✅ Design document management
- ✅ Project tracking and cost estimation
- ✅ Task management with SLA escalation
- ✅ Enquiry to work order conversion
- ✅ Report generation (PDF, Excel)

### Technical Features
- ✅ Spring Framework with dependency injection
- ✅ Hibernate ORM with JPA
- ✅ Spring WebFlow for complex workflows
- ✅ Apache Tiles for page composition
- ✅ Quartz scheduler for background jobs
- ✅ Gmail API integration for emails
- ✅ PDF generation with iText and FOP
- ✅ Excel generation with Apache POI
- ✅ AJAX support with Ajax Tags
- ✅ Responsive data tables with DisplayTag
- ✅ Session management with Spring
- ✅ Transaction management
- ✅ Centralized exception handling
- ✅ Internationalization support

---

## Development Guidelines

### Code Organization
- Each module follows the layered architecture pattern
- Controllers extend `SciBaseController`
- Services implement interfaces and are transactional
- Entities follow naming conventions
- Command beans are used for data transfer

### Adding a New Module
1. Create packages: `controller`, `service`, `dao`, `command`, `validator`
2. Define JPA entities in `db.model`
3. Create WebFlow definitions if needed
4. Add JSP views in `/WEB-INF/jsp/[module]/`
5. Update Tiles definitions

### Best Practices
- Use dependency injection (@Autowired)
- Mark services with @Service
- Mark repositories with @Repository
- Use @Transactional for database operations
- Validate inputs with custom validators
- Follow existing naming conventions
- Use command beans for form handling

---

## Additional Resources

### Maven Coordinates
```xml
<groupId>com.scigenics</groupId>
<artifactId>scigenicserp</artifactId>
<version>1.0</version>
<packaging>war</packaging>
```

### Package Structure
```
com.sci.bpm
├── chart
├── command
├── constants
├── controller
├── dao
├── db.model
├── flow
├── service
├── validator
└── view
```

### External Dependencies
- Total dependencies: ~40
- Build plugins: Maven standard plugins
- External services: Gmail API (for email)

---

## Support & Maintenance

### Monitoring
- Logs are configured via log4j2.xml
- Scheduled jobs run automatically
- Task escalation ensures SLA compliance

### File Storage
- Documents stored on external server: `192.168.1.115:32771`
- Local path: `/home/scigenics/datafolder/`

### Email Configuration
- Uses Gmail API with OAuth2
- Credentials: `credentials.json` in resources

---

## Conclusion

The **Scigenics ERP** is a comprehensive, well-structured enterprise application that manages the complete lifecycle of business processes from enquiry to delivery. It leverages modern Java frameworks and follows industry-standard design patterns for maintainability and scalability.

The modular architecture allows for easy extension and maintenance, while the use of Spring WebFlow provides powerful workflow management capabilities. The system integrates various technologies for reporting, scheduling, and communication, making it a complete ERP solution.

---

**Last Updated**: December 2025  
**Version**: 1.0  
**Framework**: Spring Framework 5.x / Spring Boot 2.x  
**Java Version**: 1.8
