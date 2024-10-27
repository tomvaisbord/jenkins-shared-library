// vars/pushDocker.groovy
def call(String imageName, String buildNumber) {
    echo "Pushing Docker Image ${imageName} to DockerHub with tags ${buildNumber} and latest..."
    sh """
        docker tag ${imageName}:${buildNumber} ${imageName}:${buildNumber}
        docker tag ${imageName}:${buildNumber} ${imageName}:latest
        docker push ${imageName}:${buildNumber}
        docker push ${imageName}:latest
    """
}
