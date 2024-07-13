# BookYourShow

## Description

BookYourShow is a web application designed to provide users with a seamless experience for booking tickets to movies, events, and shows. The application aims to replicate the core functionalities of popular ticket booking platforms like BookMyShow, offering features such as movie listings, event schedules, seat selection.

## Features

### 1. Movie and Event Listings:

* Detailed information about movies and events, including descriptions, timings, and locations.
* Search and filtering options based on genres, dates, and locations.

### 2. Booking System:

* Multiple booking options for different shows and events.
* Seat selection and real-time availability updates.

### 3. Concurrency Handling:

* Efficient management of concurrent booking requests to prevent overbooking and ensure data consistency.

### 4. Admin Functionality
* Comprehensive dashboard for managing movies, events, theaters, and user bookings.


## User API Reference

### Admin Functionality

#### 1. Movie Management

```http
  POST /v1/movie
```
| Property       | Type       | Description                          |
| :--------------| :--------- | :------------------------------------ |
| `title`        | `String`   | Title of the movie                    |
| `cast`         | `String`   | Cast of the movie                     |
| `releaseDate`  | `String`   | Release date of the movie (YYYY-MM-DD)|
| `duration`     | `String`   | Duration of the movie (hh:mm:ss)      |
| `language`     | `String`   | Language of the movie                 |
| `type`         | `String`   | Type/genre of the movie               |
| `country`      | `String`   | Country of origin of the movie        |

##### Example request: 
```
{
  "title": "Dilwale Dulhania Le Jayenge",
  "cast": "Shah Rukh Khan, Kajol",
  "releaseDate": "1995-10-20",
  "duration": "02:59:00",
  "language": "Hindi",
  "type": "Romance",
  "country": "India"
}

```
