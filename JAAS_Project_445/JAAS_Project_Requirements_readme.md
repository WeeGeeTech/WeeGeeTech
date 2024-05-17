# JAAS Project Files

This folder contains a collection of Java files for a very basic username and password storage program.

## Purpose

The purpose of this project was to gain experience writing secure login programs using the JAAS library to hash stored passwords. While normal practice is not to store passwords to a .txt file, this exercise was meant to gain an understanding of how the hashing process works when developing software login processes.

Its functionality is very basic. It allows a user to create a username and password. The password is hashed, and both the username and hashed password are stored to a local .txt file. Once the user account is created, the program uses hashing to verify properly entered passwords. Users are allowed access if they enter the correct password. 

Preliminary work was started on a user session management module, but it has not been completed. 

The jaas.config file must be included in the main directory for the program to function.

## Setup

**Clone the Repository**: Clone this repository to your local machine. Be sure you have Java and its JAAS library installed.


## Running

To run the program, simply run it from Main using your IDE of choice. 


## License

N/A.
