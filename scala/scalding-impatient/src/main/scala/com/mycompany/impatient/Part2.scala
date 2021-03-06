package com.mycompany.impatient

import com.twitter.scalding._

/**
 * Scalding version of "Cascading for the Impatient, Part II"
 * Article: "http://www.cascading.org/2012/07/09/cascading-for-the-impatient-part-2/"
 * Run: scald.rb --local src/main/scala/com/mycompany/impatient/Part2.scala --input data/rain.txt --output data/output1.txt
 */
class Part2(args : Args) extends Job(args) {
  val input = Tsv(args("input"), ('docId, 'text))
  val output = Tsv(args("output"))
  input.read.
    flatMap('text -> 'word) { text : String => text.split("""\s+""") }.
    groupBy('word) { group => group.size }.
    write(output)
}