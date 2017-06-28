
def checkout(Map<String,String> dataSet){

sh "mkdir -p ~/workspace"

  ws("~/workspace") {

    checkout([$class: 'GitSCM',
    branches: [[name: dataSet.get('seleniumBranch')]],
    extensions: [[$class: 'CleanCheckout']],
    userRemoteConfigs: [[credentialsId: dataSet.get('git-id'), url: dataSet.get('seleniumRepoUrl')]]

  }



}

def build(Map<String,String> dataSet){

  ws("~/workspace") {

    sh 'mvn install -DskipTests'

  }



}

def selenium(Map<String,String> dataSet){

  ws("~/workspace") {

    sh 'mvn test'

  }



}

return this;
