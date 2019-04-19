#!/bin/sh 
# ---------------------------------------------------------------------------
# tc Server Admin Script
#
# Copyright (c) 2010-2014 Pivotal Software, Inc.  All rights reserved.
# ---------------------------------------------------------------------------
# version: 3.1.2.RELEASE
# build date: 20150910194706

if [ -z "$JAVA_HOME" ]
then
    echo The JAVA_HOME environment variable is not defined
    exit 1
fi

SCRIPT="$0"

# SCRIPT may be an arbitrarily deep series of symlinks. Loop until we have the concrete path.
while [ -h "$SCRIPT" ] ; do
  ls=`ls -ld "$SCRIPT"`
  # Drop everything prior to ->
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    SCRIPT="$link"
  else
    SCRIPT=`dirname "$SCRIPT"`/"$link"
  fi
done

RUNTIME_DIR=`dirname "$SCRIPT"`
INSTANCE_DIR=$PWD

CLASSPATH=""
CONF_DIR=`dirname "$SCRIPT"`/conf

LIB_DIR=`dirname "$SCRIPT"`/lib
for file in "$LIB_DIR"/*
do
	suffix=`echo "${file##*.}"`
	if [ $suffix = jar ]
	then
	    if [ "$CLASSPATH" ]
		then	
	        CLASSPATH=$CLASSPATH:$file
	    else
	        CLASSPATH=$file
	    fi
	fi
done

$JAVA_HOME/bin/java $JAVA_OPTS "-Dtcs.version=3.1.2.RELEASE" "-Djava.util.logging.config.file=$CONF_DIR/commons-logging.properties" "-Druntime.directory=$RUNTIME_DIR" "-Ddefault.instance.directory=$INSTANCE_DIR" -classpath "$CLASSPATH" com.springsource.tcruntime.instance.TcRuntimeAdmin "$@"
EXIT_CODE=$?

exit $EXIT_CODE
