#!/bin/bash

mainClass=cc.pp.CrmApiApplication

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
}

case $1 in
  (start)
    shift
    start
    ;;
  (stop)
    echo "stop"
    ;;
  (restart)
    echo "restart"
    ;;
  (*)
    echo >&2 "$PRG: error: unknown command '$1'"
    usage
    ;;
esac
