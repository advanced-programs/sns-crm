#!/bin/bash

mainClass=zx.soft.sns.api.driver.SnsApiDriver

# resolve links - $0 may be a softlink
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

PROJECT_DIR=`cd "$PRGDIR/.." >/dev/null; pwd`
echo PROJECT_DIR=$PROJECT_DIR

CLASSPATH="$CLASSHPATH:$PROJECT_DIR/conf"

for jar in "$PROJECT_DIR/lib"/*.jar; do
  CLASSPATH="$CLASSPATH:$jar"
done
echo CLASSPATH=$CLASSPATH

JVMARGS="${JVMARGS} -Dproject_dir=${PROJECT_DIR}"
echo JVMARGS=$JVMARGS

usage() {
  echo >&2 "usage: $PRG <command> [args]"
  echo 'Valid commands: start, stop'
  exit 1
}

start() {
  JAVA=${JAVA-'java'}
  exec $JAVA $JVMARGS -classpath "$CLASSPATH" $mainClass "$@" &
  echo $! > main.pid
}

stop() {
  kill `cat main.pid` > /dev/null
}

case $1 in
  (start)
    shift
    start $@
    ;;
  (stop)
    stop
    ;;
  (restart)
    stop
    shift
    start $@
    ;;
  (*)
    echo >&2 "$PRG: error: unknown command '$1'"
    usage
    ;;
esac
