
; The dotimes macro executes the expressions in its body a given number of times, assigning values from zero to one less than that number to a specified local binding.
; card-number 是一个local binding, 是一个迭代的变量
(dotimes [card-number 3]
  (println "deal card number" (inc card-number))) ; adds one to card-number


(defn my-fn [ms]
  (println "entered my-fn")
  (Thread/sleep ms)
  (println "leaving my-fn"))

(let [thread (Thread. #(my-fn 100))]
; .start 是java里面的方法
  (.start thread)
  (println "started thread")
  (while (.isAlive thread)
    (print ".")
; flush 是什么意思？
    (flush))
  (println "thread stopped"))


(def cols "ABCD")
(def rows (range 1 4)) ; purposely larger than needed to demonstrate :while

(println "for demo")
; 了解一下dorun 和 for, 包括 [] 里面的迭代
; dorun is used to force evaluation of the lazy sequence returned by the for macro. 
(dorun
  (for [col cols :when (not= col \B)
        row rows :while (< row 3)]
    (println (str col row))))

(println "\ndoseq demo")
; 了解一下doseq 的作用
(doseq [col cols :when (not= col \B)
        row rows :while (< row 3)]
  (println (str col row)))

