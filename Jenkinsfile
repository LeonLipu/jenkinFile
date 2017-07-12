node("master"){

    def dataMap=["git-id":"d1bcd55f-a227-4802-a76e-f5ae347a33d4",
                  "seleniumBranch":"*/master",
                  "seleniumRepoUrl":"git@github.com:LeonLipu/ATF.git"
                 ];

     checkout([$class: 'GitSCM',
     branches: [[name: '*/master']], 
     extensions: [[$class: 'CleanCheckout']],
     userRemoteConfigs: [[credentialsId: 'd1bcd55f-a227-4802-a76e-f5ae347a33d4', url: 'git@github.com:LeonLipu/jenkinFile.git']]])

    def object = load("flow.groovy");
    object.pipeline(dataMap);
