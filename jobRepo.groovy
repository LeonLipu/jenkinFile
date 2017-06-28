
def checkout(Map<String,String> dataSet){



  ws("./workspace/${JOB_NAME}/ws") {

    checkout([$class: 'GitSCM',
    branches: [[name: dataSet.get('seleniumBranch')]],
    extensions: [[$class: 'CleanCheckout']],
    userRemoteConfigs: [[credentialsId: dataSet.get('git-id'), url: dataSet.get('seleniumRepoUrl')]]]);

  }



}

def build(Map<String,String> dataSet){

  ws("./workspace/${JOB_NAME}/ws") {

    sh 'mvn install -DskipTests'

  }



}

def selenium(Map<String,String> dataSet){

  ws("./workspace/${JOB_NAME}/ws") {

    sh 'mvn test'

  }



}

return this;
