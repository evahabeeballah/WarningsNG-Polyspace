pipeline{
    agent {
    node('java11-agent')
    }

    environment {
        repo = "C:/Users/eva.allah/Desktop"
    }

    stages {

    stage ('Checkout') {
        steps{
        checkout scm
        }
    }
    stage ('Git mining') {
        steps{
        discoverGitReferenceBuild()
        mineRepository()
        gitDiffStat()
        }
    }

        stage ('Static Analysis') {
            steps{
            recordIssues enabledForFailure: true, tool: io.jenkins.plugins.analysis.warnings.Polyspace(pattern: '**/*.csv')
            
            }
        }

    }
}
