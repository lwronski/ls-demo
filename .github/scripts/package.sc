//> using scala "3.1.2"
//> using lib "com.lihaoyi::os-lib:0.8.1"
import scala.util.Properties

val platformSuffix: String = {
  val os =
    if (Properties.isWin) "pc-win32"
    else if (Properties.isLinux) "pc-linux"
    else if (Properties.isMac) "apple-darwin"
    else sys.error(s"Unrecognized OS: ${sys.props("os.name")}")
  os
}
val artifactsPath = os.Path("artifacts", os.pwd)
val destPath =
  if (Properties.isWin) artifactsPath / s"ls-$platformSuffix.exe"
  else artifactsPath / s"ls-$platformSuffix"
val scalaCLILauncher =
  if (Properties.isWin) "scala-cli.bat" else "scala-cli"

os.makeDir(artifactsPath)
os.proc(scalaCLILauncher, "package", ".", "-o", destPath, "--native-image")
  .call(cwd = os.pwd)

// clean up after native build
os.remove(os.Path(s"${destPath.toString.stripSuffix(".exe")}.build_artifacts.txt"))
