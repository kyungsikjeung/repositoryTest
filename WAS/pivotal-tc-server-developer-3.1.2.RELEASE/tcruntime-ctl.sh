#!/bin/sh

# ---------------------------------------------------------------------------
# tc Runtime Control Script
#
# Copyright (c) 2010-2015 Pivotal Software, Inc.  All rights reserved.
# ---------------------------------------------------------------------------
# version: 3.1.2.RELEASE
# build date: 20150910194706

TCRUNTIME_VER=3.1.2.RELEASE

### don't muddy the whole environment
MY_INSTANCE_BASE="$INSTANCE_BASE"
unset INSTANCE_BASE
export INSTANCE_BASE
INSTANCE_BASE="$MY_INSTANCE_BASE"
RUN_FROM_INSTANCE="$INSTANCE_BASE"
# Uncomment to change the location of where runtime instances will be installed
# and override any possible setting via the instance script
#INSTANCE_BASE=setme

#function that prints out usage syntax
syntax () {
  if [ -z "$RUN_FROM_INSTANCE" ]; then
    echo "Usage from tc Runtime installation directory:"
    echo "./tcruntime-ctl.sh instance_name cmd [options]"
    echo " "
    echo "  [options]"
    echo "     -i <dir>, -n <dir>  - The full path to the instances directory [default:"
    echo "                           current working directory]"
    echo "     -d <dir>            - The full path to the tc Runtime installation"
    echo "                           directory [default: location of this script]"
    echo " "
  else
    echo "Usage from tc Runtime instance directory:"
    echo "./tcruntime-ctl.sh cmd"
    echo " "
  fi
    echo "  cmd is one of start | run | stop | restart | status | install"
    echo "    start             - starts a tc Runtime instance as a daemon process"
    echo "    run               - starts a tc Runtime instance as a foreground process"
    echo "    stop [timeout]    - stops a running tc Runtime instance, forcing termination"
    echo "                        of the process if it does not exit gracefully within"
    echo "                        timeout seconds [default: 5 seconds]"
    echo "    restart [timeout] - restarts a running tc Runtime instance, forcing"
    echo "                        termination of the process if it does not exit"
    echo "                        gracefully within timeout seconds [default: 5 seconds]"
    echo "    status            - reports the status of a tc Runtime instance"
    echo "    verbose-status    - gives a tc Runtime instance details and status"
    echo "    install           - installs an init.d script for the instance and sets"
    echo "                        instance to start on boot"
    echo " "
    echo " "
}

#find out the absolute path of the script
setupdir () {
  PRG="$0"

  while [ -h "$PRG" ]; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
      PRG="$link"
    else
      PRG=`dirname "$PRG"`/"$link"
    fi
  done
  # Get standard environment variables
  PRGDIR=`dirname "$PRG"`

  #Absolute path
  PRGDIR=`cd "$PRGDIR" ; pwd -P`
  SCRIPT_DIRECTORY="$PRGDIR"

  while [ $# -gt 0 ]; do
    case $1 in
      -d )
        PRGDIR="$2"
        break
        ;;
    esac
    shift
  done

  if [ ! -r "$PRGDIR" ]; then
    echo "ERROR: tc Runtime directory $PRGDIR does not exist or is not readable."
    exit 1
  fi
}

setupINSTANCE_NAMEandCOMMANDandTIMEOUT() {
  while [ $# -gt 0 ]; do
    case $1 in
      -* )
        shift
        ;;
      * )
        if [ $# -lt 2 ]; then
          echo "ERROR: Invalid number of arguments on command-line."
          syntax
          exit 1
        fi
        INSTANCE_NAME=`echo $1 | sed 's/\/$//g'`
        COMMAND=$2
        if [ "$3" != -* ]; then
          TIMEOUT=$3
        fi
        break
        ;;
    esac
    shift
  done
}

setupINSTANCE_BASE() {
  while [ $# -gt 0 ]; do
    case $1 in
      -n )
        INSTANCE_BASE="$2"
        break
        ;;
      -i )
        INSTANCE_BASE="$2"
        break
        ;;
    esac
    shift
  done


  # The default directory where instances of tc Runtime will be created
  [ -z "$INSTANCE_BASE" ] && INSTANCE_BASE=`pwd -P`
}

setupCATALINA_BASE () {
  if [ "$COMMAND" = "create" ]; then
    if [ ! -x "$INSTANCE_BASE" ]; then
      echo "ERROR: Instance directory not writeable (${INSTANCE_BASE})."
      exit 1
    else
      return
    fi
  fi

  CATALINA_BASE=$INSTANCE_BASE/$INSTANCE_NAME

  if [ ! -r "$CATALINA_BASE" ]; then
    echo "ERROR: CATALINA_BASE directory $CATALINA_BASE does not exist or is not readable."
    exit 1
  fi
  if [ ! -d "$CATALINA_BASE" ]; then
    echo "ERROR: CATALINA_BASE $CATALINA_BASE is not a directory."
    exit 1
  fi

  CATALINA_BASE=`cd "$CATALINA_BASE"; pwd -P`
  INSTANCE_NAME=`basename "$CATALINA_BASE"`
}

testVersion() {
    TC_MAJOR_VERSION=`echo $1 | awk '{ print substr($1, 1, 1); }'`
    if ([ "$TC_MAJOR_VERSION" != "7" ] && [ "$TC_MAJOR_VERSION" != "8" ]); then
      return 1
    fi
    return 0
}

getTC_MAJOR_VERSION() {
    TC_FILE_VERSION=`cat "$CATALINA_BASE/conf/tomcat.version"`
	CURRENT_TC_VERSION="$TC_FILE_VERSION"
    if ([ "$TC_FILE_VERSION" = *"/"* ] || [ "$TC_FILE_VERSION" = *"\\"* ]); then
      TEMP_TOMCAT_VER=`echo $TC_FILE_VERSION | sed -e 's/^.*tomcat-//'`
      CURRENT_TC_VERSION="$TEMP_TOMCAT_VER"
    elif [ -n "$TC_FILE_VERSION" ]; then
	  TEMP_TOMCAT_VER="$TC_FILE_VERSION"
    fi
	testVersion "$TEMP_TOMCAT_VER"
    if [ $? -eq 1 ]; then
      TC_MAJOR_VERSION="7"
    fi
}

getTOMCAT_VERSION () {
  # tomcat.version can contain just the version number (eg: 6.0.20.A)
  # or the full pathname (eg: /foo/bar/tomcat-6.0.20.A), so we need
  # to handle both. If TOMCAT_VER is already provided, just use that
  if [ -z "$TOMCAT_VER" ]; then
    if [ -r "$CATALINA_BASE/conf/tomcat.version" ]; then
      # read TOMCAT_VER2 < "$CATALINA_BASE/conf/tomcat.version"
      TOMCAT_VER2=`cat "$CATALINA_BASE/conf/tomcat.version"`
      if [ -d "$PRGDIR/tomcat-$TOMCAT_VER2" ]; then
        CATALINA_HOME=`cd "$PRGDIR/tomcat-$TOMCAT_VER2" ; pwd -P`
        TOMCAT_VER="$TOMCAT_VER2"
      elif [ -d "$TOMCAT_VER2" ]; then
        CATALINA_HOME=`cd $TOMCAT_VER2 ; pwd -P`
        TOMCAT_VER=`echo $TOMCAT_VER2 | sed -e 's/^.*tomcat-//'`
      fi
    fi
    testVersion "$TOMCAT_VER"
    if [ $? -eq 1 ]; then
	  getTC_MAJOR_VERSION
      TOMCAT_VER=`ls -d -r "$PRGDIR/tomcat-$TC_MAJOR_VERSION"* | head -1 | sed -e 's/^.*tomcat-//'`
      echo "Warning: Unable to locate tomcat runtime version "$CURRENT_TC_VERSION", using the latest version "$TOMCAT_VER"."
      echo "Warning: Please update the tomcat.version to a valid version."
    fi
  fi
}

getJAVA_VERSION () {
  if [ -n "$JRE_HOME" ] && [ "$JRE_HOME" != "$JAVA_HOME" ] ; then
    echo "WARNING: JRE_HOME environment variable has been set. This will override JAVA_HOME set by the instance configuration"
  fi
  if [ -n $JAVA_HOME ]; then
    # This will generate the java version as an integer for gt and lt testing. Java 1.6.x will be 16, 1.7 will be 17 and so forth
    JAVA_VERSION=`$JAVA_HOME/bin/java -cp "$CATALINA_HOME/../lib/tc-runtime-instance-3.1.2.RELEASE.jar" com.springsource.tcruntime.instance.internal.util.TcJavaVersionTester --getVersionAsInteger`
    testJAVA_VERSION
  else
    echo "ERROR: Unable to find JAVA_HOME environment variable!"
    exit 1
  fi
}

testJAVA_VERSION () {
  if [ $TC_MAJOR_VERSION -eq "8" -a $JAVA_VERSION -lt 17 ]; then
    echo "ERROR: tc Runtime version 8 requires Java 7 or newer"
    exit 1
 elif [ $JAVA_VERSION -eq 16 ]; then
    echo "WARNING: Java version 6 is only supported if you have extended support"
 elif [ $JAVA_VERSION -lt 16 ]; then
    echo "ERROR: Your java version is unsupported"
    exit 1
 fi
}

getLAYOUT() {
  LAYOUT=`cat "$CATALINA_BASE/.layout"`
}

getCATALINA_PID() {
  if [ -z "$CATALINA_PID" ]; then
    CATALINA_PID="$CATALINA_BASE/logs/tcserver.pid"
  fi
}

setupCATALINA_HOME () {
  #Setup CATALINA_HOME to point to our binaries
  if [ "$LAYOUT" = "combined" ]; then
    CATALINA_HOME=$CATALINA_BASE
  else
    [ -z "$CATALINA_HOME" ] && CATALINA_HOME=`cd "$PRGDIR/tomcat-$TOMCAT_VER" ; pwd -P`
    if [ ! -r "$CATALINA_HOME" ]; then
      echo "ERROR: CATALINA_HOME directory $CATALINA_HOME does not exist or is not readable."
      exit 1
    fi
  fi
}

noop () {
  echo -n ""
}

isrunning() {
  #returns 0 if the process is running
  #returns 1 if the process is not running, or file was removed.
  #returns 2 if the process state is unknown
  if [ -f "$CATALINA_PID" ]; then
        # The process file exists, make sure the process is not running
        PID=`cat "$CATALINA_PID"`
        if [ -z $PID ]; then
            return 2;
        fi
        LINES=`ps -p $PID`
        PIDRET=$?
        if [ $PIDRET -eq 0 ];
        then
            export PID
            return 0;
        fi
        rm -f "$CATALINA_PID"
    fi
    return 1
}


instance_start() {
    isrunning
    if [ $? -eq 0 ]; then
      echo "ERROR: Instance is already running as PID=$PID."
      exit 1
    fi
    "$SCRIPT_TO_RUN" start
    sleep 2
    isrunning
    evaluateStatusAndExit $?
}

instance_run() {
    isrunning
    if [ $? -eq 0 ]; then
      echo "ERROR: Instance is already running as PID=$PID."
      exit 1
    fi
    # catalina.sh won't create a PID file when using the run command
    if [ ! -f "$CATALINA_PID" ]; then
      echo $$ > "$CATALINA_PID"
    fi
    exec "$SCRIPT_TO_RUN" run
}

instance_stop() {
    if [ -z "$TIMEOUT" ]; then
      WAIT_FOR_SHUTDOWN=60
    else
      # Add a leading zero to $TIMEOUT else -n may be treated as a switch for echo
      echo "0$TIMEOUT" | grep "[^0-9]" > /dev/null 2>&1
      if [ "$?" -eq "0" ]; then
        # If the grep found something other than 0-9
        # then it's not an integer.
        WAIT_FOR_SHUTDOWN=5
      else
        WAIT_FOR_SHUTDOWN=$TIMEOUT
      fi
    fi

    isrunning
    RUNNING=$?
    if [ $RUNNING -eq 0 ]; then
        #tomcat process is running
        echo "Instance is running as PID=$PID, shutting down..."
        kill $PID
    elif [ $RUNNING -eq 2 ]; then
        echo "No action taken. Unable to stop Tomcat instance that was started as a foreground process. Use CTRL+C in the console of the process to stop the instance"
        return 2
    else
        #if the log file is not there then it is likely someone may have clean out the contents of the log directory, including the pid file 
        #or they are trying to stop a new instance.
        CATALINA_LOG="${CATALINA_BASE}/logs/catalina.out"
        if [ -f "${CATALINA_LOG}" ]; then
            echo "Instance is not running. No action taken"
        else
            echo "Running state is unknown. Expected pid file '${CATALINA_PID}' was not found."
            echo "This could mean you are attempting to stop an instance which has never been started or the logs directory has been purged."
            return 2
        fi
        return 1
    fi
    isrunning
    if [ $? -eq 0 ]; then
        #process still exists
        echo "Instance is running PID=$PID, sleeping for up to $WAIT_FOR_SHUTDOWN seconds waiting for shutdown"
        i=0
        while [ $i -lt $WAIT_FOR_SHUTDOWN ]; do
            sleep 1
            isrunning
            if [ $? -eq 1 ]; then
                break
            fi
            i=`expr $i + 1`
        done
    fi

    # CAN_DELETE_PID = 0 is Yes, any other number is no.
    CAN_DELETE_PID=1
    isrunning
    if [ $? -eq 0 ];
    then
        echo "Instance is still running PID=$PID, forcing a shutdown"
        kill -9 $PID
        CAN_DELETE_PID=$?
    else
        echo "Instance shut down gracefully"
        CAN_DELETE_PID=0
    fi
    if [ ${CAN_DELETE_PID} -eq 0 ]; then
       if [ -f "$CATALINA_PID" ]; then
          rm -f "$CATALINA_PID"
       fi
    else
       echo "Unable to stop process PID=${PID}."
       exit 1
    fi
}

instance_restart() {
    instance_stop $@
    if [ $? -eq 0 ]; then
        instance_start
    fi
    exit $?

}

instance_status() {
    echo "Instance name:         ${INSTANCE_NAME}"
    echo "Runtime version:       ${TOMCAT_VER}"
    echo "tc Runtime Base:       ${CATALINA_BASE}"
    isrunning
    evaluateStatusAndExit
}

evaluateStatusAndExit() {
    RUNNING=$?
    if [ $RUNNING -eq 0 ]; then
      echo "Status:                RUNNING as PID=$PID"
    elif [ $RUNNING -eq 2 ]; then
      echo "Status:                UNKNOWN due to Tomcat instance being started as a foreground process"
    else
      echo "Status:                NOT RUNNING"
    fi
    if [ -z "$1" ]; then
      exit 0
    else
      exit $1
    fi
}

instance_verbose_status() {
    echo "Instance name:         ${INSTANCE_NAME}"
    echo "Runtime version:       ${TOMCAT_VER}"
    echo "tc Runtime Base:       ${CATALINA_BASE}"
    echo "tc Runtime Home:       ${CATALINA_HOME}"
    echo "tc Runtime Install dir:${PRGDIR}"
    echo "Instances directory:   ${INSTANCE_BASE}"
    echo "Script directory:      ${SCRIPT_DIRECTORY}"
    echo "Script version:        ${TCRUNTIME_VER}"
    isrunning
    evaluateStatusAndExit
}

instance_install() {
    if [ -x /sbin/chkconfig ] || [ -x /usr/sbin/chkconfig ]; then
        instance_install_initd
    else
        echo "Unable to locate chkconfig. Service installation is not supported on this OS at this time."
    fi
}

instance_install_initd() {
    INSTANCE_HOME="${INSTANCE_BASE}/${INSTANCE_NAME}"
    INITD_SCRIPT="${INSTANCE_HOME}/bin/init.d.sh"
    if [ -x /sbin/chkconfig ]; then
        CHKCONFIG=/sbin/chkconfig
    elif [ -x /usr/sbin/chkconfig ]; then
        CHKCONFIG=/usr/sbin/chkconfig
    else
        echo "Unable to find chkconfig at either /usr/sbin/chkconfig or /sbin/chkconfig"
        exit 1
    fi
    INITD_PATH="/etc/init.d/tcserver-${INSTANCE_NAME}"
    if [ "$(id -u)" != "0" ]; then
        echo "Must be root to install the init.d script"
        exit 1
    fi

    echo "Installing init.d script for ${INSTANCE_NAME}"
    /bin/sed -i "s|<INSTANCE_LOCATION>|${INSTANCE_HOME}|" $INITD_SCRIPT
    /bin/cp -f "${INITD_SCRIPT}" "${INITD_PATH}"
    $CHKCONFIG --add "tcserver-${INSTANCE_NAME}"
    echo "Service tcserver-${INSTANCE_NAME} has been installed."
}

instance_uninstall() {
    instance_uninstall_initd
}

instance_uninstall_initd() {
    if [ "$(id -u)" != "0" ]; then
        echo "Must be root to install the init.d script"
        exit 1
    fi

    if [ -x /sbin/chkconfig ]; then
        CHKCONFIG=/sbin/chkconfig
    elif [ -x /usr/sbin/chkconfig ]; then
        CHKCONFIG=/usr/sbin/chkconfig
    else
        echo "Unable to find chkconfig at either /usr/sbin/chkconfig or /sbin/chkconfig"
        exit 1
    fi

    INITD_PATH="/etc/init.d/tcserver-${INSTANCE_NAME}"
    if [ -x $INITD_PATH ]; then
        echo -n "Are you sure you want to delete $INITD_PATH? (y/N)"
        read confirm
        if [ "$confirm" == "y" ] || [ "$confirm" == "Y" ]; then
            $CHKCONFIG --del "tcserver-${INSTANCE_NAME}"
            echo "Removing $INITD_PATH"
            rm -f $INITD_PATH
        else
            echo "Aborting."
        fi
    else 
        echo "$INITD_PATH doesn't exist, nothing to do"
        RETVAL=255
    fi
}

# MAIN SCRIPT EXECUTION
setupdir "$@"
setupINSTANCE_NAMEandCOMMANDandTIMEOUT "$@"
if [ -z "$INSTANCE_NAME" ] || [ -z "$COMMAND" ]; then
	echo "ERROR: Instance name or command was not specified"
	syntax
	exit 1
fi

setupINSTANCE_BASE "$@"
setupCATALINA_BASE "$@"
getTOMCAT_VERSION "$@"
#This is needed for CATALINA_PID and finding JAVA_HOME
. "$CATALINA_BASE/bin/setenv.sh"
getJAVA_VERSION "$@"
getLAYOUT "$@"
getCATALINA_PID "$@"
setupCATALINA_HOME "$@"


#CATALINA_PID="$CATALINA_BASE/logs/tcserver.pid"
SCRIPT_TO_RUN="$CATALINA_HOME/bin/catalina.sh"
[ -z "$LOGGING_MANAGER" ] && LOGGING_MANAGER="-Djava.util.logging.manager=com.springsource.tcserver.serviceability.logging.TcServerLogManager"

if [ -z "$LOGGING_CONFIG" ]; then
  if [ -r "$CATALINA_BASE"/conf/logging.properties ]; then
    LOGGING_CONFIG="-Djava.util.logging.config.file=$CATALINA_BASE/conf/logging.properties"
  else
    # Bugzilla 45585
    LOGGING_CONFIG="-Dnop"
  fi
fi

export CATALINA_HOME
export CATALINA_BASE
export CATALINA_PID
export SCRIPT_TO_RUN
export LOGGING_CONFIG
export LOGGING_MANAGER
export INSTANCE_NAME

# Check cmd is expected
if [ "$COMMAND" != "start" ] && [ "$COMMAND" != "run" ] && [ "$COMMAND" != "stop" ] && [ "$COMMAND" != "restart" ] && [ "$COMMAND" != "status" ] && [ "$COMMAND" != "verbose-status" ] && [ "$COMMAND" != "install" ] && [ "$COMMAND" != "uninstall" ]; then
    echo " "
    echo "ERROR: Invalid command $COMMAND"
    echo " "
    syntax
    exit 1
fi
if [ "$COMMAND" = "verbose-status" ]; then
	instance_verbose_status $@
else
    #execute the correct function
    instance_$COMMAND $@
fi
