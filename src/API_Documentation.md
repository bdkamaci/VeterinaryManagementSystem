# API Documentation

This document provides an overview of the API endpoints and their functions of VET_API.

## Animal Endpoints

| Endpoint                              | HTTP Method | Description                                                           |
|---------------------------------------|-------------|-----------------------------------------------------------------------|
| /animals/save                         | POST        | Add new animal.                                                       |
| /animals/update                       | PUT         | Update the animal.                                                    |
| /animals/findById/{id}                | GET         | Retrieve the animal with the specified ID.                            |
| /animals/findByName/{name}            | GET         | Retrieve the animal with the specified name.                          |
| /animals/findByCustomerId/{id}        | GET         | Retrieve the animal with the specified customer id.                   |          
| /animals/delete/{id}                  | DELETE      | Delete the animal with the specified ID.                              |              
| /animals/findAll                      | GET         | Retrieve all recorded animals in the database.                        |

## Customer Endpoints

| Endpoint                              | HTTP Method | Description                                          |
|---------------------------------------|-------------|------------------------------------------------------|
| /customers/save                       | POST        | Add new customer.                                    |
| /customers/findById/{id}              | GET         | Retrieve the customer with the specified ID.         |
| /customers/update                     | PUT         | Update the customer.                                 |
| /customers/delete/{id}                | DELETE      | Delete the customer with the specified ID.           |
| /customers/findByName/{name}          | GET         | Retrieve the customer with the specified name.       |
| /customers/findAll                    | GET         | Retrieve all customers.                              |
| /customers/byAnimalList/{id}          | GET         | Retrieve all animals of customers with specified ID. |

## Vaccine Endpoints

| Endpoint                              | HTTP Method | Description                                                            |
|---------------------------------------|-------------|------------------------------------------------------------------------|
| /vaccine/save                         | POST        | Add new vaccine.                                                       |
| /vaccine/update                       | PUT         | Update the vaccine.                                                    |
| /vaccine/findById/{id}                | GET         | Retrieve the vaccine with the specified ID.                            |
| /vaccine/delete/{id}                  | DELETE      | Delete the vaccine with the specified ID.                              |
| /vaccine/findAll                      | GET         | Retrieve all vaccines.                                                 |
| /vaccine/findByAnimal/{id}            | GET         | Retrieve all vaccines related with the specified animalId.             |
| /vaccine/expiring                     | GET         | Retrieve all vaccines that protection end date is between given dates. |

## Appointment Endpoints

| Endpoint                              | HTTP Method | Description                                                           |
|---------------------------------------|-------------|-----------------------------------------------------------------------|
| /appointment/save                     | POST        | Add new appointment.                                                  |
| /appointment/findAll                  | GET         | Retrieve all appointments.                                            |
| /appointment/delete                   | DELETE      | Delete the appointment with the specified ID.                         |
| /appointment/update                   | PUT         | Update the appointment.                                               |
| /appointment/findById/{id}            | GET         | Retrieve the appointment with the specified ID.                       |
| /appointment/dateAnimal               | GET         | Retrieve all appointments with specified animal ID and given dates.   |
| /appointment/dateDoctor               | GET         | Retrieve all appointments with specified doctor ID and given dates.   |

## Available Date Endpoints

| Endpoint                              | HTTP Method | Description                                                           |
|---------------------------------------|-------------|-----------------------------------------------------------------------|
| /available-date/save                  | POST        | Add new available date.                                               |
| /available-date/findById/{id}         | GET         | Retrieve the available date with the specified ID.                    |
| /available-date/delete/{id}           | DELETE      | Delete the available date with the specified ID.                      |
| /available-date/update                | PUT         | Update the available date.                                            |
| /available-date/findAll               | GET         | Retrieve all available dates.                                         |

## Doctor Endpoints

| Endpoint                              | HTTP Method | Description                                                           |
|---------------------------------------|-------------|-----------------------------------------------------------------------|
| /doctor/save                          | POST        | Add new doctor.                                                       |
| /doctor/findById/{id}                 | GET         | Retrieve the doctor with the specified ID.                            |
| /doctor/update                        | PUT         | Update the doctor.                                                    |
| /doctor/delete                        | DELETE      | Delete the doctor with the specified ID.                              |
| /doctors/findAll                      | GET         | Retrieve all doctors.                                                 |
