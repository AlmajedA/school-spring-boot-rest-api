# School Spring Boot REST API

Root URL: `http://localhost:8080`

## Available APIs

### Student

- **Get all students**
  - Method: `GET`
  - Endpoint: `/v1/students`
  - Description: Returns all students.

- **Create a new student**
  - Method: `POST`
  - Endpoint: `/v1/students`
  - Description: Creates a new student.

- **Get student by ID**
  - Method: `GET`
  - Endpoint: `/v1/students/{id}`
  - Description: Returns the student with the selected ID.
 
- **Update student**
  - Method: `PUT`
  - Endpoint: `/v1/students/{id}`
  - Description: Update a new student by receiving a body request with `name` and `major`.

- **Assign student to a classroom**
  - Method: `PUT`
  - Endpoint: `/{studentId}/assign-classroom/{classroomId}`
  - Description: Assigns a student to a classroom.

### Classroom

- **Get all classrooms**
  - Method: `GET`
  - Endpoint: `/v1/classrooms`
  - Description: Returns all classrooms.

- **Create a new classroom**
  - Method: `POST`
  - Endpoint: `/v1/classrooms`
  - Description: Creates a new classroom.

- **Get classroom by ID**
  - Method: `GET`
  - Endpoint: `/v1/classrooms/{id}`
  - Description: Returns the classroom with the selected ID.
 
- **Update classroom**
  - Method: `PUT`
  - Endpoint: `/v1/classrooms/{id}`
  - Description: Update a new student by receiving a body request with `room` and `building`.

## Testing

### Creating a New Classroom

- **Endpoint:** `/v1/classrooms`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "room": "132",
    "building": "22"
  }
  ```

### Creating a New Student

- **Endpoint:** `/v1/students`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "name": "Ammar",
    "major": "SWE"
  }
  ```

### Assigning Student to a Classroom

- **Endpoint:** `/v1/students/{studentId}/assign-classroom/{classroomId}`
- **Method:** `POST`
- **Example:** `/v1/students/1/assign-classroom/1`

To see the result, use the following endpoints:

* Get all classrooms: GET `/v1/classrooms`
* Get all students: GET `/v1/students`
  
