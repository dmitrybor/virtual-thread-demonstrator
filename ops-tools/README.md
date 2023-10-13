# Deploying a service to AWS EC2

All services can be deployed as systemd services on a Linux machine.

To launch an EC2 instance:

1. Open the EC2 creation wizard in AWS console and configure at least
    - SSH key
    - Network security to access the service and SSH
2. Copy and paste the contents of [aws-user-data.sh](aws-user-data.sh) into the User Data field
   in the Advanced Details section.
3. Uncomment relevant section in the User Data script to install and start particular service.
4. Launch the EC2 instance.
5. Wait till the service is installed
6. Check that service is running on the EC2 instance.
