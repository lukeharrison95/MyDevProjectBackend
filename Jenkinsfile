
pipeline {
  agent any
  stages {
    
   stage('----mvn clean----'){
    steps{
      sh "mvn clean"
    }
   }
    
    stage('----mvn test----'){
    steps{
      sh "mvn test"
    }
    }
    
    stage('----mvn package----'){
    steps{
      sh "mvn package -DskipTests"
    }
   }
    stage('----build dockerfile----'){
      steps{
	      sh " touch Dockerfile"
        sh """echo '# build from the Maven image
# which has a maven environment configured already
FROM maven:latest
# copy our application in
COPY . /MyDevProjectBackend
# change the working directory to where we are building
# the application
WORKDIR /MyDevProjectBackend
# use maven to build the application
RUN mvn clean package -DskipTests
# create a new build stage from the Java image
# which has java installed already
FROM openjdk:8-jdk-alpine
# change the working directory to where the application
# is going to be installed
WORKDIR /MyDevProjectBackend
# copy the JAR file that was created in the previous
# build stage to the application folder in this build stage
COPY --from=0 /MyDevProjectBackend/target/*.jar app.jar
# create an entrypoint to run the application
ENTRYPOINT ["/usr/bin/java", "-jar","-Dspring.profiles.active=dev", "app.jar"]' > Dockerfile"""
      }
    }
    
    stage('----Build Image For Application----'){
    steps{
      sh "docker build -t lukeharrison95/dartsdevprojectbackend:latest ."
    }
   }
   stage('----Push to dockerhub----'){
	   steps{
		withDockerRegistry([ credentialsId: "dockerhub", url: "" ]) {
		sh "docker push lukeharrison95/dartsdevprojectbackend:latest"
		}
	   }
   }
	  stage('----nexus----'){
		  steps{
			  sh "mvn deploy"
		  }
	  }
	
 }
}
