
## Requirements

For building and running the application you need:

- JDK 11
- Maven 3.x.x
- this application is using in memory H2 database 

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.musalasoft.application.MusalasoftDroneApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
Aslo, you can build the project using following command and run the jar file mannually

```shell
mvn clean install
```

```shell
java -jar target/musalasoft-drone-0.0.1-SNAPSHOT.jar
```

## Running Unit Tests

```
mvn test
```

## API Specification

### drone register

```
POST /api/v1/drone/register
Accept: application/json
Content-Type: application/json

{
    "serialNumber": "ysQmd5S7kjo5aoJ5rXFh",
    "model": "Lightweight",
    "weightLimit": 456.0,
    "batteryCapacity": 90.0,
    "state": "IDLE"
}

RESPONSE: HTTP 201 (Created)
```

### get all drones

```
GET /api/v1/drone/all
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200 (OK)
[
    {
        "id": 1,
        "serialNumber": "4cgtevV3suvUq3TeMtsv",
        "model": "Lightweight",
        "weightLimit": 400.0,
        "batteryCapacity": 90.0,
        "state": "LOADING",
        "medication": [
            {
                "id": 1,
                "name": "Voltaren_Gel",
                "weight": 100.0,
                "code": "MEDIC001",
                "imageLocation": "http://locations",
                "drone": 1
            }
        ]
    },
    {
        "id": 3,
        "serialNumber": "mzcgqPwDF7YiX96EGUu9",
        "model": "Lightweight",
        "weightLimit": 400.0,
        "batteryCapacity": 30.0,
        "state": "IDLE",
        "medication": []
    }
]
```

### get drone by serial number 

```
GET /api/v1/drone/{serial-number}
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200 (OK)
{
    "id": 3,
    "serialNumber": "mzcgqPwDF7YiX96EGUu9",
    "model": "Lightweight",
    "weightLimit": 400.0,
    "batteryCapacity": 30.0,
    "state": "IDLE",
    "medication": []
}
```

### get drone's battary capacity

```
GET /api/v1/drone/battary-capacity/{serial-number}
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200 (OK)
{
    "serialNumber": "Qik8R5N6FUFhi6AFN4Sq",
    "battaryCapacity": "90.0%"
}
```

### find available drones for loading

```
GET /api/v1/drone/avaiable-for-load
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200 (OK)
[
    {
        "id": 3,
        "serialNumber": "mzcgqPwDF7YiX96EGUu9",
        "model": "Lightweight",
        "weightLimit": 400.0,
        "batteryCapacity": 30.0,
        "state": "IDLE",
        "medication": []
    }
]
```

### get Medication for a given Drone

```
GET /api/v1/drone/{serial-number}/medicationItems
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200 (OK)
{
    "droneSerialNumber": "4cgtevV3suvUq3TeMtsv",
    "medications": [
        {
            "name": "Voltaren_Gel",
            "weight": 100.0,
            "code": "MEDIC001",
            "image": "http://locations"
        }
    ]
}
```

### Battary Event logs

```
GET /api/v1/audit/battary
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200 (OK)
[
    {
        "id": 1,
        "serialNumber": "4cgtevV3suvUq3TeMtsv",
        "updatedTime": "Mon Sep 26 12:18:09 IST 2022",
        "battaryLevel": 90.0
    },
    {
        "id": 2,
        "serialNumber": "Qik8R5N6FUFhi6AFN4Sq",
        "updatedTime": "Mon Sep 26 12:18:09 IST 2022",
        "battaryLevel": 90.0
    },
    {
        "id": 3,
        "serialNumber": "mzcgqPwDF7YiX96EGUu9",
        "updatedTime": "Mon Sep 26 12:18:09 IST 2022",
        "battaryLevel": 30.0
    }
]
```

### Load medication for a drone

```
curl --location --request POST '/api/v1/drone/load-medication' \
--form 'serialNumber="mzcgqPwDF7YiX96EGUu9"' \
--form 'name="Golimumab"' \
--form 'weight="300.0"' \
--form 'code="MADIC009"' \
--form 'image=@"<image>"'

RESPONSE: HTTP 201 (Created)
```
