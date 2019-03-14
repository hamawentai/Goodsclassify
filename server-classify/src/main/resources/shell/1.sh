#!/usr/bin/env bash
spark_home=/home/hadoop/apps/spark/
jar_home=/home/weixun/IdeaProjects/data-analysis/out/artifacts/wx
${spark_home}/bin/spark-submit \
--class com.lab.analysis.RedistributionAnalysiy2HDFS \
--master spark://wx:7077 \
--executor-memory 512m \
--total-executor-cores 2 \
/home/weixun/IdeaProjects/data-analysis/out/artifacts/wx/wx.jar 
res=$?
if [[ ${res} == "0" ]]; 
then wget -qO- $1; 
else wget -qO- $2; 
fi

spark-submit --class com.lab.analysis.HotWordAnalysis --master spark://wx:7077 --executor-memory 512m --total-executor-cores 2 --jars mysql-connector-java-5.1.45.jar,c3p0-0.9.5.2.jar,commons-dbutils-1.6.jar,ansj_seg-5.1.6.jar,nlp-lang-1.7.7.jar,mchange-commons-java-0.2.11.jar wx.jar spark://wx:7077 hdfs://wx:9000/mock

spark-submit --driver-class-path mysql-connector-java-5.1.45.jar --class com.lab.analysis.LabelAnalysis --master spark://wx:7077 --executor-memory 512m --total-executor-cores 2 --jars mysql-connector-java-5.1.45.jar,commons-dbutils-1.6.jar,c3p0-0.9.5.2.jar,mchange-commons-java-0.2.11.jar,spark-sql_2.11-2.3.2.jar wx.jar spark://wx:7077 hdfs://wx:9000/mock

spark-submit --class com.lab.analysis.ThreeCatalog --master spark://wx:7077 --executor-memory 512m --total-executor-cores 2 --jars mysql-connector-java-5.1.45.jar,c3p0-0.9.5.2.jar,commons-dbutils-1.6.jar wx.jar spark://wx:7077 hdfs://wx:9000/mock

spark-submit --driver-class-path mysql-connector-java-5.1.45.jar --class com.lab.analysis.LabelNameAnalysis --master spark://wx:7077 --executor-memory 512m --total-executor-cores 2 --jars mysql-connector-java-5.1.45.jar,commons-dbutils-1.6.jar,c3p0-0.9.5.2.jar,mchange-commons-java-0.2.11.jar,spark-sql_2.11-2.3.2.jar wx.jar spark://wx:7077 hdfs://wx:9000/train.tsv

spark-submit --class com.lab.analysis.PriceAnalysis --master spark://wx:7077 --executor-memory 512m --total-executor-cores 2 --jars mysql-connector-java-5.1.45.jar,commons-dbutils-1.6.jar,c3p0-0.9.5.2.jar,mchange-commons-java-0.2.11.jar,spark-sql_2.11-2.3.2.jar wx.jar spark://wx:7077 hdfs://wx:9000/mock


# $3 类名 $4 所需要的jar包字符串形式 $5 运行的driver(master or loacl[*]) $6 数据输入路径

spark-submit \
--class $3 \
--executor-memory 512m \
--total-executor-cores 2 \
--jars $4
wx.jar $5 $6
res=$?
if [[ ${res} == "0" ]];
then wget -qO- $1;
else wget -qO- $2;
fi
