

(println (. java.util.Calendar APRIL)) ; -> 3
(println (java.util.Calendar/APRIL)) ; -> 3
; (. Calendar APRIL) ; works if the Calendar class was imported
; java.util.Calendar/APRIL

(import
  '(java.util Calendar))
(println Calendar/APRIL) ; works if the Calendar class was imported

; method
;  invoke a static method in a Java class
(println (. Math pow 2 4)) ; -> 16.0
(println (Math/pow 2 4))


; constructor
;  invoke a constructor to create a Java object
(import '(java.util Calendar GregorianCalendar))
; 两种binding的方式，一个是用new，一个使用xx.
(def calendar (new GregorianCalendar 2008 Calendar/APRIL 16)) ; April 16, 2008
(println calendar)
(def calendar (GregorianCalendar. 2008 Calendar/APRIL 16))
(println calendar)

; JANUARY 是0，序号从0开始
; invoke an instance method on a Java object
(println (.get calendar Calendar/MONTH)) ; -> 3
(. calendar add Calendar/MONTH 2)
(. calendar get Calendar/MONTH) ; -> 5
(.add calendar Calendar/MONTH 2)
(println (.get calendar Calendar/MONTH)) ; -> 7


(println (. (. calendar getTimeZone) getDisplayName)) ; long way
(println (.. calendar getTimeZone getDisplayName)) ; -> "Central Standard Time"


; The doto macro is used to invoke many methods on the same object. It returns the value of its first argument which is the target object.
(doto calendar
  (.set Calendar/YEAR 1981)
  (.set Calendar/MONTH Calendar/AUGUST)
  (.set Calendar/DATE 1))
(def formatter (java.text.DateFormat/getDateInstance))
(println (.format formatter (.getTime calendar))) ; -> "Aug 1, 1981"

; The memfn macro expands to code that allows a Java method to be treated as a first class function. It is an alternative to using an anonymous function for calling a Java method. When using memfn to invoke Java methods that take arguments, a name for each argument must be specified. This indicates the arity of the method to be invoked. These names are arbitrary, but they must be unique because they are used in the generated code.
(println (map #(.substring %1 %2)
           ["Moe" "Larry" "Curly"] [1 2 3])) ; -> (oe rry ly)

; 了解了意思，但是还是没有彻底明白
(println (map (memfn substring beginIndex)
           ["Moe" "Larry" "Curly"] [1 2 3])) ; -> same


; Thread 

(defn delayed-print [ms text]
  (Thread/sleep ms)
  (println text))

; Pass an anonymous function that invokes delayed-print
; to the Thread constructor so the delayed-print function
; executes inside the Thread instead of
; while the Thread object is being created.
; .start 是什么意思？
(.start (Thread. #(delayed-print 1000 ", World!"))) ; prints 2nd
(print "Hello") ; prints 1st
; output is "Hello, World!"



(defn collection? [obj]
  (println "obj is a" (class obj))
  ; Clojure collections implement clojure.lang.IPersistentCollection.
  (or (coll? obj) ; Clojure collection?
      (instance? java.util.Collection obj))) ; Java collection?

(defn average [coll]
; when-not 和 when, 再了解throw
  (when-not (collection? coll)
    (throw (IllegalArgumentException. "expected a collection")))
  (when (empty? coll)
    (throw (IllegalArgumentException. "collection is empty")))
  ; Apply the + function to all the items in coll,
  ; then divide by the number of items in it.
  (let [sum (apply + coll)]
    (/ sum (count coll))))

; try catch finally
(try
  (println "list average =" (average '(2 3))) ; result is a clojure.lang.Ratio object
  (println "vector average =" (average [2 3])) ; same
  (println "set average =" (average #{2 3})) ; same
; 理解
  (let [al (java.util.ArrayList.)]
    (doto al (.add 2) (.add 3))
    (println "ArrayList average =" (average al))) ; same
  (println "string average =" (average "1 2 3 4")) ; illegal argument
  (catch IllegalArgumentException e
    (println e)
    ;(.printStackTrace e) ; if a stack trace is desired
  )
  (finally
    (println "in finally")))




