// vars/testDocker.groovy
def call(String imageName) {
    echo "Testing Docker Image ${imageName}..."
    sh """
        docker run --name test-container -d ${imageName}
        sleep 5  # Allow time for the container to start
        docker exec test-container curl -f http://localhost:5000
        docker stop test-container
        docker rm test-container
    """
}
