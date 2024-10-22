// vars/testDocker.groovy
def call(String imageName) {
    echo "Testing Docker Image ${imageName}..."
    // Here we run the container and test it
    sh """
    docker run --name test-container -d ${imageName}
    docker exec test-container curl http://localhost:5000
    docker stop test-container
    docker rm test-container
    """
}
