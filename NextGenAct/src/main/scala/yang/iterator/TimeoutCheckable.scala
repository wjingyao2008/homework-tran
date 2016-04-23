package yang.iterator

/**
  * Created by y28yang on 3/1/2016.
  */
trait TimeoutCheckable {

  @volatile
  var lastTimeStamp=System.currentTimeMillis()
  def touch()={
    lastTimeStamp=System.currentTimeMillis()
  }

  def isTimeOut(maxUnusedTime:Long):Boolean={
    val currentTime=System.currentTimeMillis()
    val timeHasPast=currentTime-lastTimeStamp
    maxUnusedTime<timeHasPast
  }

}
