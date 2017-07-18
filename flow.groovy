
def pipeline(Map<String,String> dataSet){

  def jobs=load("jobRepo.groovy");

  ws("${WORKSPACE}/ws") {

  stage('checkout'){

    checkout([$class: 'GitSCM',
    branches: [[name: dataSet.get('seleniumBranch')]],
    extensions: [[$class: 'CleanCheckout']],
    userRemoteConfigs: [[credentialsId: dataSet.get('git-id'), url: dataSet.get('seleniumRepoUrl')]]]);

     }


  stage('BuildGeneration'){

           sh 'mvn install -DskipTests'
          }

      parallel(
                  "serverRunning ":
                          {
                              stage('Server up'){
                                  sh ' mvn exec:java -Dexec.mainClass="APPLICATION_SERVER.App"'
                              }

                          },
                       "serial ":
                               {

                                   stage('Selenium_Automation'){

                                       sh 'mvn test'
                                       junit '**/target/surefire-reports/TEST-*.xml'

                                   }


                               }
             )








   } //end of workspace

}




return this;
