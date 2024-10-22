// vars/deployDocker.groovy
def call(String imageName) {
    echo "Deploying Docker Image ${imageName}..."
    sh """
    docker run -d -p 5000:5000 --name datetime-app ${imageName}
    """
}
