# Spark

### Spark Commands
Follow the instructions from commands.txt file.

### Install Spark
DO NOT Install Spark again if it is already installed in your VM.  
You can consider the below section as a self-note.  
We consider that Java and Hadoop are already installed.
Follow the below steps:
1. Open a terminal and enter the below commands
```
sudo apt update
```
```
wget https://dlcdn.apache.org/spark/spark-3.3.3/spark-3.3.3-bin-hadoop3.tgz
tar xvf spark-3.3.3-bin-hadoop3.tgz
rm spark-3.3.3-bin-hadoop3.tgz
sudo mv spark-3.3.3-bin-hadoop3/ /opt/spark 
```
```
sudo tee /etc/profile.d/spark.sh <<'EOF'
export SPARK_HOME=/opt/spark
export PATH=$PATH:$SPARK_HOME/bin:$SPARK_HOME/sbin
EOF
```
2. Go to the top right corner and select the power button -> Log Out [Don't power off]
3. Log in to the remote desktop again. Now your spark path is set and you can type "spark-shell" in the terminal to access spark.
You can access spark by typing absolute path "/opt/spark/bin/spark-shell" also.

### Run a scala file
Run a scala file using the following command 
spark-shell -i '/home/user/test_scala.scala'

Please replace the paths according to your file locations. If you are facing issues please make sure you are giving the correct paths while loading the hadoop file from the HDFS



# Question
![0001](https://github.com/saadesh71/SparkMutualFriends/assets/43541169/d32b7deb-ce35-41db-8d58-8a5e6e640a38)
![0002](https://github.com/saadesh71/SparkMutualFriends/assets/43541169/43e54a7f-07ad-4e3d-8bb6-5f23b26775f5)
![0003](https://github.com/saadesh71/SparkMutualFriends/assets/43541169/6ef81b55-63b7-4338-aa39-57e5099aec4a)


