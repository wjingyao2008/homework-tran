package chapter4

import java.io.File

/**
  * Created by y28yang on 1/29/2016.
  */
object LogProcessingProtocl {

  case class LogFile(file:File)
  case class Line(time:Long,message:String,messageType:String)
}
