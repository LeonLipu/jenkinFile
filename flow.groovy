
def pipeline(Map<String,String> dataSet){

  def jobs=load("jobRepo.groovy");

  ws("/Users/brahmanandakar/Desktop/jenkin/gitrepo1") {

  stage('checkout'){


    checkout([$class: 'GitSCM',
    branches: [[name: dataSet.get('seleniumBranch')]],
    extensions: [[$class: 'CleanCheckout']],
    userRemoteConfigs: [[credentialsId: dataSet.get('git-id'), url: dataSet.get('seleniumRepoUrl')]]]);

     }

  stage('BuildGeneration'){

           sh 'mvn install -DskipTests'
           
          }

  stage('Selenium_Automation'){

      sh 'mvn test'

        }
   }

}




return this;
