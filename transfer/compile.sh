#!bin/sh
javac -classpath /vagrant/transfer/lib/mysql-connector-java-5.1.34-bin.jar -d classes *.java
cd classes
ls
java Transfer -cp /vagrant/transfer/lib/mysql-connector-java-5.1.34-bin.jar
