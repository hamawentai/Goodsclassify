#!/bin/bash
path=/home/weixun/PycharmProjects/somedemo/src
source /usr/local/bin/virtualenvwrapper.sh
workon ml
python $path/predic.py $3 $4 $5
res=$?
if [[ ${res} == "0" ]];
then wget -qO- $1;
else wget -qO- $2;
fi
