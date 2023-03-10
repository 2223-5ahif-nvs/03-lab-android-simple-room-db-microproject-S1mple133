# Microprojekt Airplane Flights

![CD workflow](https://github.com/2223-5ahif-nvs/03-lab-android-simple-room-db-microproject-S1mple133/actions/workflows/leocloud.yaml/badge.svg)

<p align="center">
<img src="img/airplane.jpg"
     alt="Markdown Monster icon"
     style="width: 500px;" />
</p>

## Features

* Ability to view available flights
* Flights can be added to the favorite list
* Flights can be removed from the favorite list

### Backend

* Native compiled using graalvm
* Deployed on [leocloud](https://student.cloud.htl-leonding.ac.at/d.pavelescu/airport-backend)
* Github action to build, test and deploy latest changes (pushed on main branch) to leocloud
* End2End tests using restassured


### Frontend (Jetpack Compose)

* Retrofit used for REST
* RoomDB used to persist favorite flights

## Screenshots

<p align="center" class="display:flex;justify-content:space-around;">
     <img src="img/flight-list.png"
     alt="Markdown Monster icon"
     style="width: 300px;" />
     <img src="img/added-flight.png"
     alt="Markdown Monster icon"
     style="width: 300px;"/>
     <img src="img/favorite-flights.png"
     alt="Markdown Monster icon"
     style="width: 300px;" />
</p>
