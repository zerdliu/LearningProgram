
(defn big 
  [st n]
  (if (> (count st) n) true false))

(println (big "liuzhuo" 2))
(println (big "liuzhuo" 20))



(defn collection-type
  [col]
  (println (cond
             (map? col) :map
             (list? col) :list
             (vector? col) :vector)))

(collection-type ["liuzhuo", "wangwei"])
(collection-type {:name "liuzhuo", :phone "15801462951" })
(collection-type '("liuzhuo", "wangwei"))


(defmacro unless [test body-a body-b]
    (list 'if (list 'not test) body-a body-b))

(unless false (println "liuzhuo") (println "wangwei"))
(unless true (println "liuzhuo") (println "wangwei"))
