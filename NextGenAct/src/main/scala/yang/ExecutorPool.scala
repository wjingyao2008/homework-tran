package yang

import java.util.concurrent.{Executors, ExecutorService}

/**
  * Created by y28yang on 2/17/2016.
  */
trait ExecutorPool {
  val EXECUTOR_SERVICE: ExecutorService = Executors.newCachedThreadPool
  def submit(runable: Runnable): Unit = {
    println(runable)
    EXECUTOR_SERVICE.execute(runable)
  }
}
