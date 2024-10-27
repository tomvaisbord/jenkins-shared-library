// vars/testDocker.groovy
def call(String imageName) {
    echo "Testing Docker Image ${imageName}..."
    try {
        sh """
            docker run --name test-container -d ${imageName}
            sleep 5
            docker exec test-container curl -f http://localhost:5000
            docker stop test-container
            docker rm test-container
        """
        currentBuild.result = 'SUCCESS'
    } catch (Exception e) {
        currentBuild.result = 'FAILURE'
        throw e
    }
}
