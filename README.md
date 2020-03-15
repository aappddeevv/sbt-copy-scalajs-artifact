# Copy scala.js artifacts

Copy the scala.js artifacts, js source and map file, to a different directory.
This plugin does *not* adjust the source map content in case the source map
contains relative directory location in the `sources` member.

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

Works with scala.js 1+ but not 0.6.

# License

MIT license.
