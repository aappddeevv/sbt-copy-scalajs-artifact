# Copy scala.js artifacts

Copy the scala.js artifacts, js source and map file, to a different directory.

To use, add the following:

```scala
// plugins.sbt
resolvers += Resolver.bintrayIvyRepo("aappddeevv", "sbt-plugins")

addSbtPlugin("ttg" % "sbt-copy-scalajs-artifact" % "<latest version here>")
```

Then in your build.sbt:

```scala
// build.sbt

lazy val subproject = project.in(file("subproject"))
	.enablePlugin(ScalaJSPlugin, CopyJSPlugi)
	.setting(copyTarget := baseDirectory / "someotherdir")
```

# License

MIT license.
