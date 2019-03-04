#!/bin/bash
path=/home/weixun/PycharmProjects/somedemo/src
source /usr/local/bin/virtualenvwrapper.sh
workon ml
python $path/predic.py $3 $4 $5
res=$?
if [[ ${res} == "0" ]];
then curl $1;
else curl $2;
fi
