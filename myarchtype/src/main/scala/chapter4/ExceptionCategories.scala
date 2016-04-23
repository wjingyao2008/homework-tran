package chapter4

import java.io.File

/**
  * Created by y28yang on 1/29/2016.
  */
@SerialVersionUID(1L)
class DiskError(msg: String)
  extends Error(msg) with Serializable
@SerialVersionUID(1L)
class CorruptedFileException(msg: String, val file: File)
  extends Exception(msg) with Serializable
@SerialVersionUID(1L)
class DbBrokenConnectionException(msg: String)
  extends Exception(msg) with Serializable
