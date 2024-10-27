// vars/deployDocker.groovy
def call(String imageName) {
    echo "Deploying Docker Image ${imageName}..."
    sh """
        # Stop and remove any currently running 'datetime-app' container
        docker rm -f datetime-app || true

        # Deploy the new container
        docker run -d -p 5000:5000 --name datetime-app ${imageName}
    """
}
