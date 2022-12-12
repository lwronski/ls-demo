//> using lib "org.scalatest::scalatest::3.2.13"

import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers._

class TestsLs extends AnyFlatSpec with should.Matchers {
  "ls" should "print files in directory" in {
    // prepare test directory
    val tmpDir = os.temp.dir()
    // create files
    val expectedFiles = Seq("Hello", "Ls").map(tmpDir / _)
    expectedFiles.foreach(os.write(_, "Hello"))

    // check
    val foundFiles = Ls.listFiles(tmpDir)

    assert(expectedFiles == foundFiles)
  }
}
