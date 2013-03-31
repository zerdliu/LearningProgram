(import '(java.util Calendar GregorianCalendar))
(let [gc (GregorianCalendar.)
      day-of-week (.get gc Calendar/DAY_OF_WEEK)
      is-weekend (or (= day-of-week Calendar/SATURDAY) (= day-of-week Calendar/SUNDAY))]
  (if is-weekend
    (println "play")
    (do (println "work")
        (println "sleep")))
  (when is-weekend (println "play"))
  (when-not is-weekend (println "work") (println "sleep")))


(defn process-next [waiting-line]
  (if-let [name (first waiting-line)]
    (println name "is next")
    (println "no waiting")))
(process-next '("Jeremy" "Amanda" "Tami")) ; -> Jeremy is next
(process-next '()) ; -> no waiting
(process-next ["Jeremy" "Amanda" "Tami"]) ; -> Jeremy is next


(defn summarize
  "prints the first item in a collection
  followed by a period for each remaining item"
  [coll]
  ; Execute the when-let body only if the collection isn't empty.
  (when-let [head (first coll)]
    (print head)
    ; Below, dec subtracts one (decrements) from
    ; the number of items in the collection.
; 这块儿没看懂，不知道_是什么意思, 后面输出-1个"."
    (dotimes [_ (dec (count coll))] (print \.))
    (println)))
(summarize ["Moe" "Larry" "Curly"]) ; -> Moe..
(summarize []) ; -> no output



(print "Enter a number: ") (flush) ; stays in a buffer otherwise
; 从标准输入读数据 
(let [reader (java.io.BufferedReader. *in*) ; stdin
      line (.readLine reader)
      value (try
              (Integer/parseInt line)
              (catch NumberFormatException e line))] ; use string value if not integer
  (println
    (condp = value
      1 "one"
      2 "two"
      3 "three"
      (str "unexpected value, \"" value \")))
  (println
    (condp instance? value
      Number (* value 2)
      String (* (count value) 2))))




(print "Enter water temperature in Celsius: ") (flush)
(let [reader (java.io.BufferedReader. *in*)
      line (.readLine reader)
      temperature (try
        (Float/parseFloat line)
        (catch NumberFormatException e line))] ; use string value if not float
  (println
    (cond
      (instance? String temperature) "invalid temperature"
      (<= temperature 0) "freezing"
      (>= temperature 100) "boiling"
      true "neither")))

