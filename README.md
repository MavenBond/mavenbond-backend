# mavenbond-backend

Microservices backend in Java SpringBoot

## Quickstart

Make sure you have [Maven](https://www.digitalocean.com/community/tutorials/install-maven-mac-os), [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed in your computer.

1. Clone this repo into your local machine.

2. Run the following order of command

```bash
./run.sh build # to build all JAR of services
```

```bash
./run.sh start # to start all services
```

3. After finish working, stop all the containers

```bash
./run.sh stop # to stop all services
```

For load testing, there is an `insert.sql` file for insert 1 million records to Customer (Influencer) DB, but need to perform via pgAdmin GUI
