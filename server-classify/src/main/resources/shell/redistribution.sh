#!/usr/bin/env bash
spark_home=/home/hadoop/apps/spark/
jar_home=/home/weixun/IdeaProjects/data-analysis/out/artifacts/wx
${spark_home}/bin/spark-submit \
--class $3 \
--master spark://wx:7077 \
--executor-memory 512m \
--total-executor-cores 2 \
${jar_home}/wx.jar $4 $5
res=$?
if [[ ${res} == "0" ]];
then wget -qO- $1;
else wget -qO- $2;
fi

