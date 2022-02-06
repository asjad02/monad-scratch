ThisBuild / scalaVersion := "2.13.8"

ThisBuild / triggeredMessage := Watched.clearWhenTriggered

ThisBuild / autoStartServer := false

ThisBuild / scalacOptions ++= Seq(
    "-feature",
    "-deprecation",
    "-language:implicitConversions",
    "-language:higherKinds"
)

ThisBuild / shellPrompt := (_ => fancyPrompt(name.value))

def fancyPrompt(projectName: String): String =
    s"""|
        |[info] My fancy project prompt $projectName !!
        |""".stripMargin

lazy val `fp-library` = 
    project
    .in(file("./1-fp-library"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))

lazy val `application-library` = 
    project
    .in(file("./2-application-library"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))
    .dependsOn(`fp-library`)

lazy val `end-of-the-world` = 
    project
    .in(file("./3-end-of-the-world"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))
    .dependsOn(`application-library`)
    