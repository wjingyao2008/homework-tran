package yang.alarm

import org.mockito.Mockito

/**
  * Created by y28yang on 2/3/2016.
  */
trait mockAlarmOperationService {
  val mockAlarmOperationService=Mockito.mock(classOf[AlarmFmServiceInterface])

  def beforeUsingMock()={

  }


}
