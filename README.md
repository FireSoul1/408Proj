# Stress Manager

## Project Documentation

[Project Charter](https://docs.google.com/document/d/1MgSNgmPj97zqQZlCLURruOX9UYQXyxo_xUl81jwi4k4/edit?usp=sharing)

[Product Backlog](https://docs.google.com/document/d/1OBjZGVrhTmL1PJG95LB13ZsZtPMM6A06VYqGwWNWzJI/edit?usp=sharing)

[Test Plan](https://docs.google.com/document/d/1Wng8cIEPiLTz2BrBfEY_Hg_nauLI5P6y5QURing2764/edit?usp=sharing)

[Sprint 1 Design Inspection, Code Inspection, Unit Testing](https://docs.google.com/document/d/1286QKHC_SKXjvaOQllOBXlMAwIpHeSg2QAVMARqN_hA/edit?usp=sharing)

[Incremental and Regression Testing](https://docs.google.com/document/d/1vN7wMspZt-SuaZ3SMEV28CeFf54eZ4NnjWjsXfhb9bg/edit?usp=sharing)

[Sprint 2 Design Inspection, Code Inspection, Unit Testing](https://docs.google.com/document/d/18--iv3bl29AJA4VfDhIz6OvPok1PliRPg5Q1kbM6FsI/edit?usp=sharing)

See the docs folder for PDF copies of these documents.

## Project Setup

Download and install:

* [node](https://nodejs.org/en/) >= 6.9.5
* npm >= 4.1.2
  * `sudo npm i -g npm@latest` after installing node
* [maven](http://maven.apache.org/install.html)

### Frontend

* `npm install` will install all of the frontend dependencies.
* `npm test` will run the frontend tests
* `npm run dev` will watch all files in *client/* for changes, and recompile
*bundle.js* and all other assets.
* `npm run build` to produce a minified version of the bundle an all other assets.
* `npm run cleanup` will clean up the snapshots of the frontend tests

### Backend

* `mvn install` will install all of the backend dependencies.
* `mvn package` will package up the app and put it in *target/*. This will also
  run the backend tests.

You can run the backend with `java -jar target/backend-0.0.1-SNAPSHOT.jar`
Then navigate to *localhost:8080* in your web browser.

### Tests

* `npm test` will run all frontend test suites

The output will show in your terminal.  If the snapshot tests do not pass, delete the old snapshots that are files that end in .snap and then re-run the tests.

### Recommended Workflow

First, install all necessary dependencies with `npm install` and `mvn install`.

Then, have one terminal session running `npm run dev`, which will automatically rebuild
the frontend whenever you make changes. When you make changes to the backend
(or frontend, for that matter), kill the server if you have one running and
run `mvn -Pdev package && java -jar target/backend-0.0.1-SNAPSHOT.jar` <--- **without** npm
or
run `mvn package && java -jar target/backend-0.0.1-SNAPSHOT.jar` <--- **with** npm
