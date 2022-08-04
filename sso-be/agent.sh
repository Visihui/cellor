#!/bin/sh
#启动参数
JAVA_OPTS="-server -Xms1g -Xmx1g -Xmn300m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xverify:none -XX:+DisableExplicitGC -Djava.awt.headless=true -Duser.timezone=GMT+08 -Dspring.config.location=file:config/"

## java env
export JAVA_HOME=/opt/java/jdk1.8.0_201
export JRE_HOME=$JAVA_HOME/jre

API_NAME=job-demo-0.0.1
JAR_NAME=$API_NAME\.jar
#PID  代表是PID文件
PID=$API_NAME\.pid

#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh 执行脚本.sh [start|stop|restart|status]"
    exit 1
}

#检查程序是否在运行
is_exist(){
  pid=`ps -ef|grep $JAR_NAME|grep -v grep|awk '{print $2}' `
  #如果不存在返回1，存在返回0     
  if [ -z "${pid}" ]; then
   return 1
  else
    return 0
  fi
}

#启动方法
start(){
  is_exist
  if [ $? -eq "0" ]; then 
    echo ">>> ${JAR_NAME} is already running PID=${pid} <<<" 
  else
    # echo "$JRE_HOME/bin/java $JAVA_OPTS -jar $JAR_NAME >/dev/null 2>&1 &"
    nohup $JRE_HOME/bin/java $JAVA_OPTS -Dloader.path=lib/ -jar $JAR_NAME >/dev/null 2>&1 &
    echo $! > $PID
    echo ">>> start $JAR_NAME successed PID=$! <<<" 
   fi
  }

#停止方法
stop(){
  #is_exist
  pidf=$(cat $PID)
  #echo "$pidf"  
  echo ">>> api PID = $pidf begin kill $pidf <<<"
  kill $pidf
  rm -rf $PID
  sleep 2
  is_exist
  if [ $? -eq "0" ]; then 
    echo ">>> api 2 PID = $pid begin kill -9 $pid  <<<"
    kill -9  $pid
    sleep 2
    echo ">>> $JAR_NAME process stopped <<<"  
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi  
}

#输出运行状态
status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> ${JAR_NAME} is running PID is ${pid} <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

#远程调试
debug(){
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> ${JAR_NAME} is already running PID=${pid} <<<"
  else
#    java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar target/springboot-0.0.1-SNAPSHOT.jar
    nohup $JRE_HOME/bin/java $JAVA_OPTS -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=5005,suspend=n -Dloader.path=lib/ -jar $JAR_NAME >/dev/null 2>&1 &
    echo $! > $PID
    echo ">>> start $JAR_NAME successed PID=$! <<<"
   fi
}

#重启运行
restart(){
  stop
  start
}

#重启调试
redebug(){
  stop
  debug
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  "debug")
    debug
    ;;
  "redebug")
    redebug
    ;;
  *)
  usage
  ;;
esac
exit 0