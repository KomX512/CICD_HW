
set -e

echo "Starting deployment..."

# Variables
APP_NAME="CICD_HW"
DOCKER_IMAGE="CICD_HW:latest"
SSH_HOST="user@176.226.254.34"
APP_PORT="8080"

# Build the application
echo "Building application..."
mvn clean package

# Build Docker image
echo "Building Docker image..."
docker build -t $DOCKER_IMAGE .

# Save image to tar file
echo "Saving Docker image..."
docker save -o $APP_NAME.tar $DOCKER_IMAGE

# Transfer to server
echo "Transferring image to server..."
scp $APP_NAME.tar $SSH_HOST:/tmp/

# Deploy on server
echo "Deploying on server..."
ssh $SSH_HOST << EOF
    set -e
    cd /tmp

    # Load Docker image
    docker load -i $APP_NAME.tar

    # Stop and remove old container if exists
    docker stop $APP_NAME || true
    docker rm $APP_NAME || true

    # Run new container
    docker run -d \
        --name $APP_NAME \
        -p $APP_PORT:8080 \
        --restart unless-stopped \
        $DOCKER_IMAGE

    # Cleanup
    rm -f $APP_NAME.tar
    docker system prune -f
EOF

# Local cleanup
rm -f $APP_NAME.tar

echo "Deployment completed successfully!"