# Spring Boot Project - School Rewards System

## Project Overview

The School Rewards System is a web application developed using Spring Boot and microservices architecture. It allows school students to use their scores to receive rewards offered by their school. The system includes an admin panel to manage schools, teachers, and users, as well as services for managing students, avatars, elements, and rewards.

## Project Structure

The project follows a modular structure with separate microservices for each component. Here are the main modules:

1. **User Service**:

   - Manages user-related operations such as adding, removing, updating, and viewing users.
   - Includes authentication and role-based access control.

2. **School Service**:

   - Handles school-related operations like adding, removing, updating, and viewing schools.
   - Stores school details such as name, address, and contact information.

3. **Teacher Service**:

   - Manages teacher-related operations, including adding, removing, updating, and viewing teachers.
   - Stores teacher details like name, contact information, assigned school, and teaching class.

4. **Student Service**:

   - Handles student-related operations, such as adding, removing, updating, and viewing students.
   - Stores student details like name, student number, school, class, score, avatar, and rewards.
   - The initial score for each student is set to 1000.

5. **Avatar Service**:

   - Manages avatar-related operations, including adding, removing, updating, and viewing avatars.
   - Stores avatar components such as head, hair, eyes, etc., along with their colors.

6. **Element Service**:

   - Handles element-related operations, such as adding, removing, updating, and viewing elements.
   - Stores various avatar elements (head, hair, eyes, etc.) and their corresponding prices.

7. **Reward Service**:
   - Manages reward-related operations, including adding, removing, updating, and viewing rewards.
   - Stores different types of rewards like elements, in-school rewards, and gifts, along with their names, quantities, types, and prices.

## Functionality Overview

### Admin Functionality:

- Admin users can log in using fixed username and password.
- Admins can administer schools using the school service.
- Admins can administer users using the user service.
- Email notifications are sent to teachers when they are added by the admin.

### Teacher Functionality:

- Teacher users can log in using credentials provided by the admin.
- Teachers can administer their students using the student service.
- Email notifications are sent to students when they are added by the teacher.
- Teachers can administer rewards.

### Student Functionality:

- Students can log in using credentials provided by the teacher.
- Students can administer their avatars using the avatar service.
- Students can buy avatar elements using their scores as the cost.
- Score validation is performed before making element purchases.
- Students can buy rewards, limited to one reward of each type.
