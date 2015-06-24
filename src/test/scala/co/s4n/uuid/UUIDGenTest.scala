package co.s4n.uuid

import java.nio.ByteBuffer
import org.scalatest.FunSuite

class UUIDGenTest extends FunSuite {

  test( "Verify ordering" ) {
    val one = UUIDGen.getTimeUUID()
    val two = UUIDGen.getTimeUUID()
    assert( one.timestamp() < two.timestamp() )
  }

  test("Decompose and raw") {
    val a = UUIDGen.getTimeUUID();
    val decomposed = UUIDGen.decompose(a);
    val b = UUIDGen.getUUID(ByteBuffer.wrap(decomposed));
    assert( a.equals(b) )
  }

  test( "UUIDTimeStamp" ) {
    val now = System.currentTimeMillis()
    val uuid = UUIDGen.getTimeUUID()
    val tstamp = UUIDGen.getAdjustedTimestamp(uuid)
    assert( now <= tstamp )
  }
}
