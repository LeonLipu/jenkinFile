
def pipeline(Map<String,String> dataSet){

  def jobs=load("jobRepo.groovy");

  ws("/Users/brahmanandakar/Desktop/jenkin/gitrepo1") {

  stage('checkout'){
      jobs.checkout(dataSet)
      }
  stage('BuildGeneration'){
           jobs.build(dataSet)
          }

  stage('Selenium_Automation'){
          jobs.selenium(dataSet)
        }
   }

}




return this;
