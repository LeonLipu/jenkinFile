
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

                                   sh '''
                                      ssh http://localhost:8080
                                    while test $? -gt 0
                                    do
                                       sleep 5 # highly recommended - if it's in your local network, it can try an awful lot pretty quick...
                                       echo "Trying again..."
                                       ssh $1
                                    done


                                      '''


                                   stage('Selenium_Automation'){

                                       sh 'mvn test'
                                       junit '**/target/surefire-reports/TEST-*.xml'

                                   }
                               }
             )








   } //end of workspace

}




return this;
