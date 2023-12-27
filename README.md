# Spark

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
### Spark Commands:
```
spark-shell
```
### How to run a scala file
Run a scala file using the following command 
spark-shell -i '/home/user/test_scala.scala'

Please replace the paths according to your file locations. If you are facing issues please make sure you are giving the correct paths while loading the hadoop file from the HDFS

### Hadoop Commands:
```
hadoop fs -mkdir InputFolder                                      //to create a new input folder
hadoop fs -copyFromLocal <input file> InputFolder                  //to copy a file from local directory to hadoop environment
hadoop fs -ls InputFolder                                          //to see the files inside "InputFolder"
hadoop jar <jar file name> <class name> InputFolder OutputFolder   //running mapreduce operation
hadoop fs -ls OutputFolder                                        //to see the files inside "OutputFolder"
hadoop fs -cat OutputFolder/part-r-00000                          //to see the content inside "OutputFolder/part-r-00000" file
hadoop fs -rm -r OutputFolder                                     //to remove "OutputFolder" directory and all its files
```

- remove OutputFolder before generating the next results.
- remove/clean InputFolder if you want to use a different file as input.


### Common Errors:
Error 1: mkdir: Call From user/127.0.1.1 to localhost:9000 failed on connection exception: java.net.ConnectException: Connection refused  
Explanation and Fix: In general this error comes if you are running hadoop first time on your VM after a reset. The below commands will fix it.
```
stop-all.sh
hadoop namenode -format
start-all.sh
```
You can use the below command to check if namenode, datanode and nodemanager are running.
```
jps
```

Error 2: mkdir: `hdfs://localhost:9000/user/<username>': No such file or directory  
Explanation and Fix: The error comes when there is no directory /user and /user/<username> in hdfs and you are trying to create a folder using "hadoop fs -mkdir InputFolder ".   
Below command will create the directory structure if required and solves the problem.
```
hdfs dfs -mkdir -p InputFolder
```

Warning 1: WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable  
Fix: You can just ignore this warning.

# Question
![0001](https://github.com/saadesh71/SparkMutualFriends/assets/43541169/d32b7deb-ce35-41db-8d58-8a5e6e640a38)
![0002](https://github.com/saadesh71/SparkMutualFriends/assets/43541169/43e54a7f-07ad-4e3d-8bb6-5f23b26775f5)
![0003](https://github.com/saadesh71/SparkMutualFriends/assets/43541169/6ef81b55-63b7-4338-aa39-57e5099aec4a)

Dataset: [soc-LiveJournal1Adj.txt](https://mailmissouri-my.sharepoint.com/:t:/g/personal/mrpk9_umsystem_edu/EVTVE564f_RCsylgfdEd46UByQMIu_NrjqNAoSfqevxBhw?e=7XGmZK)

