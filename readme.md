# To-Do List Application

This is a full-stack application for managing tasks. Users can create, read, update, delete tasks, assign priorities, mark tasks as completed, and filter tasks by state and priority.

## Installation

### Backend
1. Clone the repository: `git clone <repository_url>`
2. Navigate to the backend directory: `cd backend`
3. Install dependencies: `mvn install`
4. Run the application: `mvn spring-boot:run`

## Usage

1. Start the backend server (default at `http://localhost:4000`).


## API Endpoints

### Tasks
- `GET /api/tasks`: Retrieve all tasks
- `POST /api/tasks`: Create a new task
- `PUT /api/tasks/{id}`: Update an existing task
- `DELETE /api/tasks/{id}`: Delete a task
- `GET /api/tasks/filter?isComplete={true}&priority={HIGH}` 


### Backend
- Java 17
- Spring Boot
- JPA/Hibernate
- MySQL
