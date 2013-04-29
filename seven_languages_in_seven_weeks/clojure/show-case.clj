
; ----Part 1: Calling basic functions
; (println (+ 1 1))
; (println (* 3 2))
; (println "Give me some Clojure!")
; (println (- 8 1 2))
; (println (< 1 2 3))
; (println (str "hello ," "World !"))
; (println (= 1 1.0))
; (println (class true))
; (if true (println (+ 2 (* 2 3))) (println "false"))


; ----Part 2: Lists, Maps, Sets, and Vectors

; Vectors
; (println [:hutt :wookie :ewok])

; (println (first [:hutt :wookie :ewok]))
; (println (rest [:hutt :wookie :ewok]))

; Maps
; (println {:chewie "wookie" :lea "human"})
(def mentors {:darth-vader "obi wan", :luke "yoda"})
; (println (mentors :luke))
; (println (merge-with + {:y-wing 2, :x-wing 4} {:tie-fighter 2 :x-wing 3}))

; ----Part 3: Defining Functions
; (defn force-it
;  (str "Use the force," "liuzhuo"))
; (println (force-it))

; ----Part 4: Binding and destructuring
(def line [[0 0] [10 20]])
; (defn line-end [ln] (last line))
(defn line-end [[_ second]] second)
;  (println (line-end line))

(def person {:name "Jabba" :profession "Gangster"})
; (println (let [{name :name} person] (str "The person's name is " name)))

; ----Part 5: Anonymous Functions
(def people ["Lea ", "Han Solo"])
(defn twice-count [w] (* 2 (count w)))
; (println (twice-count "Lando"))
; (println (map twice-count people))

(map (fn [w] (* 2 (count w))) people)
(map #(* 2 (count %)) people)

; (println (reduce str people))
; (println (reduce + [1 2 3 4 5]))

; ----Part 6: Recursion 
(defn size [v]
  (if (empty? v)
    0
    (inc (size (rest v)))))

(size [1 2 3])


; ----Part 7: Sequences
(every? number? [1 2 3 :four])

(def words ["luke" "chewie" "han" "lando"])
(filter (fn [word] (> (count word) 4)) words)

(def colors ["red" "blue"])
(def toys ["block" "car"])
(defn small-word? [w] (< (count w) 4))

; (println (for [x colors, y toys, :when (small-word? y)]
;  (str "I like " x " " y "s")) )

; ----Part 8: Lazy Evaluation
(range 1 10)
(take 5 (cycle [:lather :rinse :repeat]))
(take 5 (drop 2 (cycle [:lather :rinse :repeat])))
(->> [:lather :rinse :repeat] (cycle) (drop 2) (take 5))

(defn fib-pair [[a b]] [b (+ a b)])
; (println (take 10 (map first (iterate fib-pair [1 1]))))

(defn factorial [n] (apply * (take n (iterate inc 1))))
(factorial 5)

; ----Part 9: Macros 

(defmacro unless [test body]
  (list 'if (list 'not test) body))

; ----Part 10: Concurrence Programming
; Ref (STM)
; Atoms
; Agents

(def danger (atom "Split at your own risk."))
 (println danger)
 (println @danger)

