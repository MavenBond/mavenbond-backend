#!/bin/bash

# Build process
build() {
  echo "Building JARs from all services 🔨";

  mvn -f ./UserService/pom.xml install -DskipTests

  docker build -t pcminh0505/user-service . #Rebuild

  mvn -f ./BusinessService/pom.xml install -DskipTests

  docker build -t pcminh0505/business-service . #Rebuild

  echo "=> Build DONE ✅";
}

# Start process
start() {
  echo "Starting all Containers 🚀";

  cd UserService

  docker-compose up -d

  cd ../BusinessService

  docker-compose up -d

  echo "=> Start DONE ✅";
}

# Stop process
stop() {
  echo "Stopping all Containers 🐳";

  docker-compose -f UserService/docker-compose.yml down 
  
  docker-compose -f BusinessService/docker-compose.yml down 

  echo "=> Stop DONE ✅";
}

execute() {
  local task=${1}
  case "${task}" in
    build)
      build
      ;;
    start)
      start
      ;;
    stop)
      stop
      ;;
    *)
      err "Invalid task: ${task}"
      usage
      exit 1
      ;;
  esac
}

err() {
    echo "$*" >&2
}

usage() {
    err "$(basename "$0"): [build|start|stop]"
}

main() {
  if [ $# -ne 1 ]
  then
    usage; 
    exit 1; 
  fi
  local task=${1}
  execute "${task}"
}

main "$@"