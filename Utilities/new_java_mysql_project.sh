#!/bin/bash

UTILITY_PATH=/Users/kristinf/Desktop/DojoAssignments/Utilities
TEMPLATE_PATH=$UTILITY_PATH/java_mysql_project_templates/template

echo $1 $2

cd $1/$2
cp $TEMPLATE_PATH/pom.xml .

cd src/main
mkdir webapp
cd webapp
mkdir WEB-INF
cd WEB-INF
cp -r $TEMPLATE_PATH/src/main/webapp/WEB-INF/* .

cd ../../resources
cp -r $TEMPLATE_PATH/src/main/resources/* .

cd ../java/com/codingdojo/$2
pwd
cp -r $TEMPLATE_PATH/src/main/java/com/codingdojo/project/controllers .
cp -r $TEMPLATE_PATH/src/main/java/com/codingdojo/project/models .
cp -r $TEMPLATE_PATH/src/main/java/com/codingdojo/project/repositories .
cp -r $TEMPLATE_PATH/src/main/java/com/codingdojo/project/services .
