# Club-Management-System# Project Title
CS2312 Java Assignment

## Description
This project is a Java application designed for managing equipment and members in a club. It allows users to borrow equipment, check member statuses, and manage reservations. The application is built using object-oriented programming (OOP) principles, promoting code reusability and maintainability. 

The system employs interfaces to define contracts for various commands, ensuring that different command implementations can be easily integrated and managed. Error handling is a key aspect of the application, with custom exceptions implemented to handle scenarios such as member not found, equipment already borrowed, and invalid input. This robust error handling mechanism enhances user experience by providing clear feedback and preventing system crashes.

## Features
- Borrow and return equipment
- List available equipment and members
- Check member status
- Manage reservations
- Command-line interface for user interaction

## Installation
1. Ensure you have Java Development Kit (JDK) installed on your machine.
2. Clone the repository:
   ```
   git clone <repository-url>
   ```
3. Navigate to the project directory:
   ```
   cd CS2312-JAVA-ASSIGNMENT
   ```

## Usage
To run the application, compile the Java files and execute the `Main` class:
```
javac *.java
java Main
```
Follow the on-screen instructions to interact with the application.

## File Structure
```
.
├── BorrowStatus.java
├── Club.java
├── CmdArrive.java
├── CmdBorrow.java
├── CmdCreate.java
├── CmdListEquipment.java
├── CmdListEquipmentStatus.java
├── CmdListMembers.java
├── CmdListMemberStatus.java
├── CmdRegister.java
├── CmdRequest.java
├── CmdStartNewDay.java
├── Command.java
├── Day.java
├── Equipment.java
├── EquipmentSet.java
├── ExEmployeeNotFound.java
├── ExEquipmentCodeAlreadyInUse.java
├── ExEquipmentRecordNotFound.java
├── ExIncorrectNumberOfDays.java
├── ExInsufficientArguments.java
├── ExInvalidDate.java
├── ExMemberAlreadyBorrowing.java
├── ExMemberIdAlreadyInUse.java
├── ExMemberNotFound.java
├── ExMemberNotUsingThisEquipment.java
├── ExMissingEquipmentRecord.java
├── ExNoAvailableSet.java
├── ExOverlapBorrow.java
├── ExOverlapRequest.java
├── ExUnknownCommand.java
├── Main.java
├── Member.java
├── RecordedCommand.java
├── RequestStatus.java
├── Reservation.java
├── Status.java
├── SystemDate.java
└── TextFiles/
    ├── 1c.txt
    ├── 1d.txt
    ├── 1d1.txt
    ├── 1d2.txt
    ├── 1d3.txt
    ├── 1e.txt
    ├── 1e1.txt
    ├── 2a.txt
    ├── 2b.txt
    ├── 2c.txt
    ├── 2x.txt
    ├── 3a.txt
    ├── 3b.txt
    ├── 3c.txt
    ├── 3c4.txt
    ├── buffer.txt
    └── testsampleoutput.txt
```

## Commands
- **CmdBorrow**: Allows a member to borrow equipment for a specified number of days.
- **CmdCreate**: Creates a new equipment record in the system.
- **CmdListEquipment**: Lists all available equipment in the club.
- **CmdListMembers**: Lists all registered members in the club.
- **CmdRegister**: Registers a new member in the club.
- **CmdRequest**: Handles requests for equipment, specifying the request date and duration.
- **CmdStartNewDay**: Sets the current date in the system.
- **CmdArrive**: Marks equipment as arrived in the system.
- **CmdListEquipmentStatus**: Lists the status of all equipment in the club.
- **CmdListMemberStatus**: Lists the status of all members in the club.

## Errors
- **ExInsufficientArguments**: Thrown when there are not enough arguments provided for a command.
- **ExMemberNotFound**: Thrown when a specified member cannot be found in the system.
- **ExEquipmentRecordNotFound**: Thrown when a specified equipment record cannot be found.
- **ExMemberAlreadyBorrowing**: Thrown when a member attempts to borrow equipment they are already borrowing.
- **ExOverlapBorrow**: Thrown when a borrowing period overlaps with an existing borrowing period.
- **ExNoAvailableSet**: Thrown when there are no available sets of equipment for borrowing.
- **ExInvalidDate**: Thrown when an invalid date is provided.
- **ExIncorrectNumberOfDays**: Thrown when the number of days for borrowing is invalid.
- **ExEquipmentCodeAlreadyInUse**: Thrown when trying to create an equipment record with a code that is already in use.
- **ExMissingEquipmentRecord**: Thrown when an equipment record is missing.
- **ExUnknownCommand**: Thrown when an unrecognized command is encountered.
- **ExMemberIdAlreadyInUse**: Thrown when trying to register a member with an ID that is already in use.
- **ExMemberNotUsingThisEquipment**: Thrown when a member is not using the specified equipment.
- **ExOverlapRequest**: Thrown when a request period overlaps with an existing borrowing/request period.

