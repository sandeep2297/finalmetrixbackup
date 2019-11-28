#!/bin/bash

set -f

server=$DEPLOY_SERVER
user=$DEPLOY_USER
branch=$DEPLOY_BRANCH
gittoken=$DEPLOY_GITLAB_TOKEN
gituser=$DEPLOY_GITLAB_USER

echo "Deploying project on server ${server} as ${user} from branch ${branch}"

apt-get update && apt-get install -y openssh-client

command="ls -ltr && \
 cd /home/devuser/metrix && \
 cd /home/devuser && \
 rm -rf /home/devuser/metrix && \
 git clone https://${gituser}:${gittoken}@gitlab.stackroute.in/metrix-itc-wave-02/metrix.git -b ${branch} && \
 cd /home/devuser/metrix && \
 echo 'Deploying the Application' && \
 docker-compose up --build -d --remove-orphans && \
 echo 'DONE DEPLOYING'"

echo "About to run the command: " $command

ssh $user@$server $command