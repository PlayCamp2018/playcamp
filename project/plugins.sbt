// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.19")

// https://github.com/sbt/sbt-jacoco
addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.1.0")

// https://github.com/etsy/sbt-checkstyle-plugin
addSbtPlugin("com.etsy" % "sbt-checkstyle-plugin" % "3.1.1")
//dependencyOverrides += "com.puppycrawl.tools" % "checkstyle" % "6.15"

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// https://github.com/sbt/sbt-testng
//addSbtPlugin("de.johoop" % "sbt-testng-plugin" % "3.1.1")

// https://github.com/scoverage/sbt-scoverage
// addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")

// addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")

// http://www.scalastyle.org/sbt.html
// addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
