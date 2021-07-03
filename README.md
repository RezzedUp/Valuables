# Valuables

[![](https://img.shields.io/maven-central/v/com.rezzedup.util/valuables?color=ok&label=Maven%20Central)](https://search.maven.org/artifact/com.rezzedup.util/valuables "Maven Central")
[![](https://img.shields.io/badge/License-MPL--2.0-blue)](./LICENSE "Project License: MPL-2.0")
[![](https://img.shields.io/badge/Java-11-orange)](# "Java Version: 11")
[![javadoc](https://javadoc.io/badge2/com.rezzedup.util/valuables/javadoc.svg?label=Javadoc&color=%234D7A97)](https://javadoc.io/doc/com.rezzedup.util/valuables "View Javadocs") 

Utilities for getting, setting, and composing values.

## Maven *(coming soon!)*

```xml
<dependency>
    <groupId>com.rezzedup.util</groupId>
    <artifactId>valuables</artifactId>
    <version><!--release--></version>
</dependency>
```

### Versions

Find available versions on the releases page of this repository.

Maven Central: https://search.maven.org/artifact/com.rezzedup.util/valuables

<details id="note-snapshot-versions">
<summary><b>Note:</b> <i>Snapshot Versions</i></summary>

> [ℹ️](#note-snapshot-versions)
> Snapshot releases are available at the following repository:
>
> ```xml
> <repositories>
>     <repository>
>         <id>ossrh-snapshots</id>
>         <url>https://s01.oss.sonatype.org/content/repositories/snapshots</url>
>     </repository>
> </repositories>
> ```
</details>

<details>
<summary><b>Note:</b> <i>Shading</i></summary>

> [ℹ️](#note-shading)
> If you intend to shade this library, please consider **relocating** the packages
> to avoid potential conflicts with other projects. This library also utilizes
> nullness annotations, which may be undesirable in a shaded uber-jar. They can
> safely be excluded, and you are encouraged to do so.
</details>

### Documentation

Javadoc: https://javadoc.io/doc/com.rezzedup.util/valuables
