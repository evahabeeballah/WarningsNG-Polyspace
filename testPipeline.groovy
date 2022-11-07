pipeline{
    agent {
    node('java11-agent')
    }

    environment {
        repo = "C:/Users/eva.allah/Desktop"
    }

    stages {

    stage ('Checkout') {
        checkout scm
    }
    stage ('Git mining') {
        discoverGitReferenceBuild()
        mineRepository()
        gitDiffStat()
    }

        stage ('Static Analysis') {
            steps{
            recordIssues enabledForFailure: true, tool: Polyspace(pattern: '**/*.csv')
            
            }
        }

    }
}