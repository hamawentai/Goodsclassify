#!/usr/bin/env bash
spark_home=/home/hadoop/apps/spark/
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

