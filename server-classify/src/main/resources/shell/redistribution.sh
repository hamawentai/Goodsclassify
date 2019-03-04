#!/usr/bin/env bash
spark_home=/home/hadoop/apps/spark/
${spark_home}/bin/spark-submit \
--class $3 \
--master spark://wx:7077 \
--executor-memory 512m \
--total-executor-cores 2 \
/home/weixun/IdeaProjects/data-analysis/out/artifacts/wx/wx.jar $4 $5
res=$?
if [[ ${res} == "0" ]];
then curl $1;
else curl $2;
fi

