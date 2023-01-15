#!/bin/bash

# Build process
build() {
  echo "Building JARs from all services 🔨";

  cd UserService

  mvn install -DskipTests

  docker build -t pcminh0505/user-service . #Rebuild

  cd ../
  
  cd BusinessService
  
  mvn install -DskipTests

  docker build -t pcminh0505/business-service . #Rebuild

  cd ../
  
  cd ServiceDiscovery

  mvn install -DskipTests

  docker build -t pcminh0505/discovery-service . #Rebuild

  echo "=> Build DONE ✅";
}

# Build process
publish() {
  echo "Publish all image to DockerHub 🔨";

  cd UserService

  mvn install -DskipTests

  docker buildx build --platform linux/amd64,linux/arm64 --push -t pcminh0505/user-service .

  cd ../
  
  cd BusinessService
  
  mvn install -DskipTests

  docker buildx build --platform linux/amd64,linux/arm64 --push -t pcminh0505/business-service .

  cd ../
  
  cd ServiceDiscovery

  mvn install -DskipTests

  docker buildx build --platform linux/amd64,linux/arm64 --push -t pcminh0505/discovery-service .

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
    publish)
      publish
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
    err "$(basename "$0"): [build|start|stop|publish]"
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