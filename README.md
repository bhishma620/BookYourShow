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
##### Response

```
Status Code: 201 CREATED
Body: ADDED Successfully
```
OR
```
Status Code: 409 CONFLICT
Body: Already Exists
```


```http
  GET /v1/movies?pageNo={no}&pageSize={size}
```
##### Description
Get movies 

##### Example Response
```
Status Code : 200 OK
Body: 
[
    {
        "id": 302,
        "title": "Dilwale Dulhania Le Jayenge",
        "cast": "Shah Rukh Khan, Kajol",
        "releaseDate": "1998-10-20T00:00:00.000+00:00",
        "duration": "02:59:00",
        "language": "Hindi",
        "type": "Romance",
        "country": "India"
    },
    {
        "id": 352,
        "title": "Kuch Kuch Hota hai",
        "cast": "Shah Rukh Khan, Kajol",
        "releaseDate": "1998-10-20T00:00:00.000+00:00",
        "duration": "02:59:00",
        "language": "Hindi",
        "type": "Romance",
        "country": "India"
    },
    {
        "id": 402,
        "title": "War",
        "cast": "Hrithik Roshan,Tiger Shroff",
        "releaseDate": "2019-02-19T00:00:00.000+00:00",
        "duration": "02:15:00",
        "language": "Hindi",
        "type": "Action",
        "country": "India"
    }
]
```

```http
  GET /v1/movies/search?title={title}
```
##### Description
Return all movies containing the search title

##### Example Response
```
Status Code : 200 OK
Body: 
[
    {
        "id": 402,
        "title": "War",
        "cast": "Hrithik Roshan,Tiger Shroff",
        "releaseDate": "2019-02-19T00:00:00.000+00:00",
        "duration": "02:15:00",
        "language": "Hindi",
        "type": "Action",
        "country": "India"
    },
    {
        "id": 403,
        "title": "War2",
        "cast": "Hrithik Roshan,Tiger Shroff",
        "releaseDate": "2024-12-19T00:00:00.000+00:00",
        "duration": "02:32:00",
        "language": "Hindi",
        "type": "Action",
        "country": "India"
    }
]
```

```http
  GET /v1/movies/search/byCategory?category={name}&pageNo={no}&pageSize={}
```
##### Description
Returns all movies in a category

##### Example Response

```
Status Code : 200 OK
Body: 
[
    {
        "id": 254,
        "title": "Pathan",
        "cast": "SRK,Deepika",
        "releaseDate": "2023-10-21T00:00:00.000+00:00",
        "duration": "02:16:00",
        "language": "TELUGU13",
        "type": "Action",
        "country": "India"
    },
    {
        "id": 402,
        "title": "War",
        "cast": "Hrithik Roshan,Tiger Shroff",
        "releaseDate": "2019-02-19T00:00:00.000+00:00",
        "duration": "02:15:00",
        "language": "Hindi",
        "type": "Action",
        "country": "India"
    },
    {
        "id": 403,
        "title": "War2",
        "cast": "Hrithik Roshan,Tiger Shroff",
        "releaseDate": "2024-12-19T00:00:00.000+00:00",
        "duration": "02:32:00",
        "language": "Hindi",
        "type": "Action",
        "country": "India"
    }
]
```

```http
   PUT /v1/movies?/{id}
```
##### Description
Update a movie details

### Request Body
The request body should be in JSON format and contain the following fields:

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
##### Response

```
Status Code: 200 OK
Body: Updated
```
OR
```
Status Code: 404 Not Found
Body: No Movie Found with Id {id}
```

```http
    DELETE v1/movies/{id}
```
##### Desciption 
Delete a movie detials by using movie Id

##### Response

```
Status Code: 200 OK,
Body: Deleted Successfully
```
OR
```
Status Code: 404 Not Found,
Body: "No Movie Found with Id {id}"
```
#### 2. Cinema Hall Management
```http
 POST /v1/cinema-hall
```

##### Description
Adds a new cinema hall.

##### Request Body
The request body should be in JSON format and contain the following fields:

| Property   | Type     | Description                           |
|------------|----------|---------------------------------------|
| `name`     | `String` | Name of the cinema hall               |
| `city`     | `String` | City where the cinema hall is located |
| `location` | `String` | Detailed location of the cinema hall  |

##### Example Request
```json
{
  "name": "SVF",
  "city": "Kolkata",
  "location": "Kalyani, WB"
}
```
##### Response

```
Status Code: 200 OK
Body: ADDED Successfully
```
OR
```
Status Code: 409 CONFLICT
Body: Already Exists
```

```http
 GET /v1/cinema-hall//search/byCity?city={name}
```
##### Description
Gets all cinema hall within a City

##### Response

```json
[
    {
        "id": 152,
        "name": "SVF",
        "city": "Kolkata",
        "location": "Kalyani,WB"
    },
    {
        "id": 153,
        "name": "Multiplex",
        "city": "Kolkata",
        "location": "Kalyani,WB"
    }
]
```
```http
 GET /v1/cinema-hall/search/byName?name={name}
```
##### Description
Gets all cinema hall containing searched name

##### Response

```json
[
    {
        "id": 153,
        "name": "Multiplex",
        "city": "Kolkata",
        "location": "Kalyani,WB"
    },
    {
        "id": 154,
        "name": "Multiplex",
        "city": "Delhi",
        "location": "Road 234,New Delhi"
    }
]
```
```http
 PUT /v1/cinema-hall/{id}
```

##### Description
Update cinema hall details

##### Request Body
The request body should be in JSON format and contain the following fields:

| Property   | Type     | Description                           |
|------------|----------|---------------------------------------|
| `name`     | `String` | Name of the cinema hall               |
| `city`     | `String` | City where the cinema hall is located |
| `location` | `String` | Detailed location of the cinema hall  |

##### Example Request
```json
{
  "name": "Multiplex",
  "city": "Kolkata",
  "location": "Kalyani, WB"
}
```
##### Response

```
Status Code: 200 OK
Body: Updated
```
OR
```
Status Code: 409 CONFLICT
Body: Already Exists
```
OR
```
Status Code: 404 Not Found,
Body: "No Cinema Hall Found with Id {id}"
```

```http
    DELETE v1/cinema-hall/{id}
```
##### Desciption 
Delete a Cinema Hall details by id

##### Response

```
Status Code: 200 OK,
Body: Deleted Successfully
```
OR
```
Status Code: 404 Not Found,
Body: "No Cinema Hall Found with Id {id}"
```

#### 3. Theater Management

```http
 POST /v1/theater
```

##### Description
Adds a new Theater within cinema hall

##### Request Body
The request body should be in JSON format and contain the following fields:

| Property   | Type     | Description                           |
|------------|----------|---------------------------------------|
| `cinemaHallId` | `long` | Unique identifier for the cinema hall  |
| `name`     | `String` | Name of the cinema hall               |
| `capacity` | `int`    | Seating capacity of the cinema hall   |

##### Example Request
```json
{
    "cinemaHallId":3,
     "name":"A1",
     "capacity":120
}
```
##### Response

```
Status Code: 200 OK
Body: ADDED Successfully
```
OR
```
Status Code: 409 CONFLICT
Body: Already Exists
```

```http
    GET /v1/theater/search/byCinemaHall?cinema-hallId={id}
```
##### Description
Gets all theater details within a cinemaHall

##### Response
```
[
    {
        "id": 1,
        "name": "A1",
        "capacity": 120
    },
    {
        "id": 3,
        "name": "A2",
        "capacity": 80
    }
]
```

```http
    PUT /v1/theater/{theaterId}
```
##### Description
Update Theater Details by Theater Id

##### Request Body
The request body should be in JSON format and contain the following fields:

| Property   | Type     | Description                           |
|------------|----------|---------------------------------------|
| `cinemaHallId` | `long` | Unique identifier for the cinema hall  |
| `name`     | `String` | Name of the cinema hall               |
| `capacity` | `int`    | Seating capacity of the cinema hall   |

##### Example Request
```json
{
    "cinemaHallId":3,
     "name":"A1",
     "capacity":120
}
```

##### Response

```
Status Code: 200 OK
Body: Updated
```
OR

```
Status Code: 404 Not Found,
Body: "No Theater Found with Id {id}"
```

```http
    DELETE /v1/theater/{id}
```
##### Desciption 
Delete a Theater details by id

##### Response

```
Status Code: 200 OK,
Body: Deleted Successfully
```
OR
```
Status Code: 404 Not Found,
Body: "No Theater Found with Id {id}"
```
