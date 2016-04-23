package Corba

import java.applet.Applet
import java.util.Properties

import akka.actor.Actor
import akka.actor.Actor.Receive
import org.omg.CORBA._
import org.omg.CORBA.portable.OutputStream

/**
  * Created by y28yang on 1/29/2016.
  */
class ObjectImpl extends ORB{
  override def get_primitive_tc(tcKind: TCKind): TypeCode = ???

  override def create_sequence_tc(bound: Int, element_type: TypeCode): TypeCode = ???

  override def create_wstring_tc(bound: Int): TypeCode = ???

  override def create_enum_tc(id: String, name: String, members: Array[String]): TypeCode = ???

  override def create_string_tc(bound: Int): TypeCode = ???

  override def create_named_value(s: String, any: Any, flags: Int): NamedValue = ???

  override def create_exception_tc(id: String, name: String, members: Array[StructMember]): TypeCode = ???

  override def list_initial_services(): Array[String] = ???

  override def get_default_context(): Context = ???

  override def string_to_object(str: String): Object = ???

  override def create_exception_list(): ExceptionList = ???

  override def send_multiple_requests_oneway(req: Array[Request]): Unit = ???

  override def object_to_string(obj: Object): String = ???

  override def create_recursive_sequence_tc(bound: Int, offset: Int): TypeCode = ???

  override def poll_next_response(): Boolean = ???

  override def create_list(count: Int): NVList = ???

  override def create_array_tc(length: Int, element_type: TypeCode): TypeCode = ???

  override def get_next_response(): Request = ???

  override def resolve_initial_references(object_name: String): Object = ???

  override def create_union_tc(id: String, name: String, discriminator_type: TypeCode, members: Array[UnionMember]): TypeCode = ???

  override def set_parameters(args: Array[String], props: Properties): Unit = {

  }

  override def set_parameters(app: Applet, props: Properties): Unit = ???

  override def create_context_list(): ContextList = ???

  override def create_alias_tc(id: String, name: String, original_type: TypeCode): TypeCode = ???

  override def create_any(): Any = ???

  override def create_interface_tc(id: String, name: String): TypeCode = ???

  override def create_struct_tc(id: String, name: String, members: Array[StructMember]): TypeCode = ???

  override def send_multiple_requests_deferred(req: Array[Request]): Unit = ???

  override def create_environment(): Environment = ???

  override def create_output_stream(): OutputStream = ???

  //override def receive: Receive = ???
}
