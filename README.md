Download the vagrant environment from https://github.com/suenchunhui/fabric-tutorial-vagrant.
Ensure that ports 3001-3009 are mapped in the vagrantfile, if not, edit the vagrantfile to add the port mappings.
Start vagrant with the command "vagrant up" in the directory with the vagrant file.

In the IDE available at "http://localhost:8181" (Username: "root" Password: "secret"), cd to ~/composer-playground and drop the hxblockchain.bna BNA file into the composer-playground folder. Then, use the console to copy the file into the composer-playground/mnt folder with the command "sudo cp hxblockchain.bna mnt"

run "./playground.sh -f docker-compose-deploy.yaml up"

Copy and paste the following commands to create a new admin:
docker exec -it ca.org1.example.com fabric-ca-client enroll -M registrar -u http://admin:adminpw@localhost:7054

docker exec -it ca.org1.example.com fabric-ca-client register -M registrar -u http://localhost:7054 --id.name kenneth --id.affiliation org1 --id.attrs '"hf.Registrar.Roles=client"' --id.type user

Enter the CLI container using:
docker exec -it cli bash

Using the password for the newly registered admin, deploy the network BNA using:
composer network deploy --archiveFile /mnt/hxblockchain.bna -A kenneth -c PeerAdmin@hlfv1 -S <admin enrol password>

Import the created admin card using:
composer card import -f kenneth\@is4302-hx-network.card

Create a new terminal in the IDE and cd to composer-playground.Start the admin rest server with:
./start_rest_server.sh -i 1 -p 3001 -c kenneth@is4302-hx-network

In the java project, cd to the folder with pom.xml, run the project with "mvn spring-boot:run". If you do not have maven, you can alternatively cd to the target folder and run the backend server with the java command "java -jar healthcare-blockchain-0.0.1-SNAPSHOT.jar". When "Application started successfully!"appears, the server has created some static data in the blockchain and is running at http://localhost:9191

In the CLI container, run the following commands to create the identity cards of the users.

composer identity issue -c kenneth@is4302-hx-network -u PATIENT001 -a org.acme.model.Patient#PATIENT001
composer card import -f PATIENT001\@is4302-hx-network.card
composer identity issue -c kenneth@is4302-hx-network -u PATIENT002 -a org.acme.model.Patient#PATIENT002
composer card import -f PATIENT002\@is4302-hx-network.card
composer identity issue -c kenneth@is4302-hx-network -u DOCTOR001 -a org.acme.model.Practitioner#DOCTOR001
composer card import -f DOCTOR001\@is4302-hx-network.card
composer identity issue -c kenneth@is4302-hx-network -u EM001 -a org.acme.model.Employer#EM001
composer card import -f EM001\@is4302-hx-network.card
composer identity issue -c kenneth@is4302-hx-network -u PHARM001 -a org.acme.model.Pharmacy#PHARM001
composer card import -f PHARM001\@is4302-hx-network.card
composer identity issue -c kenneth@is4302-hx-network -u DOCTOR002 -a org.acme.model.Practitioner#DOCTOR002
composer card import -f DOCTOR002\@is4302-hx-network.card

In the terminal in composer-playground, run the following commands to start the rest server for each user identity.

./start_rest_server.sh -i 2 -p 3002 -c PATIENT001@is4302-hx-network
./start_rest_server.sh -i 3 -p 3003 -c PHARM001@is4302-hx-network
./start_rest_server.sh -i 4 -p 3004 -c DOCTOR001@is4302-hx-network
./start_rest_server.sh -i 6 -p 3006 -c EM001@is4302-hx-network
./start_rest_server.sh -i 7 -p 3007 -c PATIENT002@is4302-hx-network
./start_rest_server.sh -i 8 -p 3008 -c DOCTOR002@is4302-hx-network

In a new terminal in your host OS, start the front end server by going into the Hx-Blockchain-Frontend folder, then run "npm install" then "npm run build", followed by "npm start".

The HxBlockchain application can be accessed at http://localhost:3010

Usernames:
Doctor001
Doctor002
patient001
patient002
pharm001
emp001

Password is "pp" for all users.


