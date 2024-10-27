// vars/pushDocker.groovy
def call(String imageName, String dockerCredentialsId) {
    echo "Pushing Docker Image ${imageName} to DockerHub..."
    withCredentials([usernamePassword(credentialsId: dockerCredentialsId, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
        sh """
            docker login -u "$DOCKER_USERNAME" -p "$DOCKER_PASSWORD"
            docker tag ${imageName} ${DOCKER_USERNAME}/${imageName}:latest
            docker push ${DOCKER_USERNAME}/${imageName}:latest
            docker logout
        """
    }
}
