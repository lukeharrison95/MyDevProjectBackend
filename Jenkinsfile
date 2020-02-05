
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
