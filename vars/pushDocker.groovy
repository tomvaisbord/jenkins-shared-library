// vars/pushDocker.groovy
def call(String imageName, String dockerCredentialsId) {
    echo "Pushing Docker Image ${imageName} to DockerHub..."
    withCredentials([string(credentialsId: dockerCredentialsId, variable: 'DOCKER_HUB_API_TOKEN')]) {
        sh """
        echo "$DOCKER_HUB_API_TOKEN" | docker login -u "your-dockerhub-username" --password-stdin
        docker tag ${imageName} your-dockerhub-username/${imageName}:latest
        docker push your-dockerhub-username/${imageName}:latest
        """
    }
}
