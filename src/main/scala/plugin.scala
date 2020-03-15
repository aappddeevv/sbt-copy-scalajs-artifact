package ttg
package scalajs

import sbt._
import Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

// this does not adjust the content of the source map at all!
object CopyJSPlugin extends AutoPlugin {
  override def requires = ScalaJSPlugin

  final object autoImport {
    val copyTarget = SettingKey[File]("copyTarget", "scala.js linker artifact copy target directory")
    val copyJS     = TaskKey[Unit]("copyJS", "Copy scala.js linker artifacts to another location after linking.")
  }
  import autoImport._

  override lazy val projectSettings = Seq(
    copyJS := copyJSTask.value,
    fastOptJS / copyJS := (copyJS triggeredBy fastOptJS.in(Compile)).value,
    fullOptJS / copyJS := (copyJS triggeredBy fullOptJS.in(Compile)).value
  )
  //define inline in autoImport via `copyJSTask := {` or separately like this
  private def copyJSTask = Def.task {
    val logger = streams.value.log
    val odir = copyTarget.value
    // 0.6
    //logger.info(s"Copying artifact ${scalaJSLinkedFile.in(Compile).value.path} to [${odir}]")
    // 1.0.0
    logger.info(s"Copying artifact ${scalaJSLinkedFile.in(Compile).value} to [$odir]")
    // 0.6
    //val src = file(scalaJSLinkedFile.in(Compile).value.path)
    // 1.0.0
    val src = scalaJSLinkedFile.in(Compile).value.data
    IO.copy(
      Seq(
        (src, odir / src.name),
        (file(src.getCanonicalPath() + ".map"), odir / (src.name + ".map"))
      ),
      CopyOptions(true, true, true)
    )
  }
}
