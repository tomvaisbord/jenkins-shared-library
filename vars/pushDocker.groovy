// vars/pushDocker.groovy
def call(String imageName, String dockerCredentialsId, String buildNumber) {
    echo "Pushing Docker Image ${imageName} to DockerHub with tags ${buildNumber} and latest..."
    withCredentials([usernamePassword(credentialsId: dockerCredentialsId, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
        sh """
            docker login -u "$DOCKER_USERNAME" -p "$DOCKER_PASSWORD"
            docker tag ${imageName}:${buildNumber} ${DOCKER_USERNAME}/${imageName}:${buildNumber}
            docker tag ${imageName}:${buildNumber} ${DOCKER_USERNAME}/${imageName}:latest
            docker push ${DOCKER_USERNAME}/${imageName}:${buildNumber}
            docker push ${DOCKER_USERNAME}/${imageName}:latest
            docker logout
        """
    }
}
