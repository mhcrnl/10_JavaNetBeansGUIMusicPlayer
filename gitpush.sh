#!/bin/bash

DATE=`date`

git add .
git commit -m "$DATE"
git push -u origin main
