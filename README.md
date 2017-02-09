# Stress Manager

## Project Documentation

[Project Charter](https://docs.google.com/document/d/1MgSNgmPj97zqQZlCLURruOX9UYQXyxo_xUl81jwi4k4/edit?usp=sharing)

[Product Backlog](https://docs.google.com/document/d/1OBjZGVrhTmL1PJG95LB13ZsZtPMM6A06VYqGwWNWzJI/edit?usp=sharing)

[Test Plan](https://docs.google.com/document/d/1Wng8cIEPiLTz2BrBfEY_Hg_nauLI5P6y5QURing2764/edit?usp=sharing)

See the docs folder for PDF copies of these documents.

## Project Setup

Download and install:

* [node](https://nodejs.org/en/) >= 6.9.5
* npm >= 4.1.2
  * `sudo npm i -g npm@latest` after installing node
* [maven](http://maven.apache.org/install.html)

### Frontend

* `npm run dev` will watch all files in *javascript/* for changes, and recompile
*bundle.js*.
* `npm run build` to produce a minified version of the bundle.

### Backend

* `mvn install` will install all of the backend dependencies.
* `mvn package` will package up the app and put it in *target/*

You can run the backend with `java -jar target/backend-0.0.1-SNAPSHOT.jar`
Then navigate to *localhost:8080* in your web browser.
