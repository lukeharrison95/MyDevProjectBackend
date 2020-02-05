
#!/bin/bash
sudo apt-get update -y

sudo apt install docker.io -y
sudo apt install mysql-client-core-5.7 -y

mysql -h dartsbuddydb.cann5ibdk3ld.eu-west-2.rds.amazonaws.com -P 3306 -u admin -ppassword -e "create database dartsbuddydb"

mkdir mygitstuff
cd mygitstuff

git clone --single-branch --branch DockerReady https://github.com/lukeharrison95/DartsManagementProject.git

sudo systemctl start docker
sudo systemctl enable docker
sudo setfacl -m user:ubuntu:rw /var/run/docker.sock
sudo usermod -a -G docker $USER


cd src/main/resources/
mv static ~/mygitstuff/

cd ~/mygitstuff/DartsManagementProject

docker network create projectnetwork

docker build -t mybackendimg .
docker run --network projectnetwork --restart unless-stopped -d -p 8080:8080 --name backendcont mybackendimg 
