
def pipeline(Map<String,String> dataSet){

  def jobs=load("jobRepo.groovy");

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




return this;
