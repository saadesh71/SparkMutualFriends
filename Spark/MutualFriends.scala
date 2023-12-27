// 1st question 
val noOfLines = sc.getConf.get("spark.driver.args")

  def parse(line: String): (String, (Long, List[String])) = {
    val fields = line.split("\t")
    if (fields.length == 2) {
      val person = fields(0)
      val friendsList = fields(1).split(",").toList
      return (person, (1L, friendsList))
    } else {
      //
      return ("-1", (-1L, List()))
    }
  } 
    val startTime = System.currentTimeMillis()
    val lines = sc.textFile("hdfs://localhost:9000/user/vu2yb/FriendsInput/soc-LiveJournal1Adj.txt") // reading file
    val friends = sc.parallelize(lines.take(noOfLines.toInt)).map(parse).filter(line => line._1 != "-1") // comparing the first attribute
    
    val frndpair = friends.flatMap(x => x._2._2.map(y => if (x._1.toLong < y.toLong) { ((x._1, y), x._2) } else { ((y, x._1), x._2) }))
    val mutualFriends = frndpair.reduceByKey((x, y) => ((x._1 + y._1), x._2.intersect(y._2))).filter(v => v._2._1 != 1)

    val finalMutualFriends = mutualFriends.map(r => (r._1, r._2._2)).filter(l=>l._2!=List())
    val endTime = System.currentTimeMillis()
	val runtime = endTime - startTime
    
    val sortedKeys = finalMutualFriends.map {
  	case ((t1, t2), t3) =>
    val sortedParams = Array(t1.toInt, t2.toInt).sorted
    ((sortedParams(0), sortedParams(1)), t3)
	}
	// Sort the entire RDD based on t1 and t2
	val sortedKeyPairs = sortedKeys.sortBy {
	  case ((t1, t2), _) =>
	    (t1.toInt, t2.toInt)
	}
	val sortedMutualFriends = sortedKeyPairs.map {
	  case ((t1, t2), t3) =>
	    t1 + "\t" + t2 + "\t" + t3.mkString(",")
	}
	sortedMutualFriends.saveAsTextFile("hdfs://localhost:9000/user/vu2yb/Spark"+noOfLines+"/output")


 
val startTime1 = System.currentTimeMillis() 
// 2nd question part 1
val friendsCounts=sortedMutualFriends.map(x=>x.split("\t")).filter(x => (x.size == 3)).map(parts => s"${parts(0)}\t${parts(1)}\t${parts(2).split(",").length}")
val maxfriendsCount = friendsCounts.map(_.split("\t")(2).toInt).reduce(Math.max)
val high_mutual_frnds = friendsCounts.map(x => x.split('\t')).filter(line => line(2).toInt == maxfriendsCount).map(parts => s"${parts(0)}\t${parts(1)}\t${parts(2)}")
high_mutual_frnds.saveAsTextFile("hdfs://localhost:9000/user/vu2yb/Spark"+noOfLines+"/p2/")

// 2nd question part 2
val p3=sortedMutualFriends.map(x=>x.split("\t")).filter(x => (x.size == 3)).map{parts => 
	val Friends = parts(2).split(",").filter(frnd_name => frnd_name.startsWith("1") || frnd_name.startsWith("5")).mkString(",")
	s"${parts(0)}\t${parts(1)}\t${Friends}"
	}
val p4=p3.map(x=>x.split("\t")).filter(x => (x.size == 3)).map(parts=>s"${parts(0)}\t${parts(1)}\t${parts(2)}")
p4.saveAsTextFile("hdfs://localhost:9000/user/vu2yb/Spark"+noOfLines+"/p21/")
val endTime1 = System.currentTimeMillis()
val runtime1 = endTime1 - startTime1


val startTime2 = System.currentTimeMillis() 
// 3rd question part 1
val total_Friends = friendsCounts.map(_.split("\t")(2).toInt).reduce(_ + _)
val count =friendsCounts.count()
val avg = total_Friends.toDouble / count.toDouble
val ans="Average: " + avg.toString
println(ans)
val answer=sc.parallelize(Seq(ans))
answer.saveAsTextFile("hdfs://localhost:9000/user/vu2yb/Spark"+noOfLines+"/p3/")

// 3rd question part 2
val moreThanAvg = friendsCounts.map(x => x.split('\t')).filter(line => line(2).toInt > avg.toInt).map(parts => s"${parts(0)}\t${parts(1)}\t${parts(2)}")
moreThanAvg.saveAsTextFile("hdfs://localhost:9000/user/vu2yb/Spark"+noOfLines+"/p31/")
val endTime2 = System.currentTimeMillis()
val runtime2 = endTime2 - startTime2
println(runtime)
println(runtime1)
println(runtime2)
	

   
  