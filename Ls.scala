//> using scala "3.1.3"
//> using lib "com.lihaoyi::os-lib::0.7.8"

object Ls:
  def main(args: Array[String]) =
    val path = args.headOption match {
      case Some(p) => os.Path(p, os.pwd)
      case _       => os.pwd
    }

    if (os.isDir(path)) println(listFiles(path).mkString(","))
    else System.err.println("Expected directory")

  def listFiles(path: os.Path): List[os.Path] = os.list(path).toList
